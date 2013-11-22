package server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.mapping.Collection;

import darkengines.database.DBSessionFactory;
import darkengines.importer.Importer;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException, IOException {
//		Importer importer = new Importer();
//		importer.Import("J:/java/darkengines/data/geo.xlsx");
		Session session = DBSessionFactory.GetSession();
		
		DetachedCriteria sub = DetachedCriteria.forClass(Profile.class, "profile")
		.createAlias("profile.programmingLanguages", "programmingLanguage")
		.add(Restrictions.in("programmingLanguage.id", new HashSet(Arrays.asList(1l,2l))))
		.setProjection(Projections.rowCount())
		.add(Property.forName("profile.id").eqProperty("user.profile.id"));
		
		Criteria criteria = session.createCriteria(User.class, "user")
		.add(Subqueries.eq(2l, sub));
		
		ArrayList<User> p = (ArrayList<User>)criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		System.out.println(p);
		session.close();
	}
}

package server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Collection;

import darkengines.database.DBSessionFactory;
import darkengines.importer.Importer;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException, IOException {
//		Importer importer = new Importer();
//		importer.Import("J:/java/darkengines/data/geo.xlsx");
		Session session = DBSessionFactory.GetSession();
		Criteria criteria = session.createCriteria(Profile.class)
		.createAlias("programmingLanguages", "programmingLanguage")
		.add(Restrictions.in("programmingLanguage.id", new HashSet(Arrays.asList(1l,2l,3l,4l,5l))));
		ArrayList<Profile> p = (ArrayList<Profile>)criteria.list();
		System.out.println(p);
		session.close();
	}
}

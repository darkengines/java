package caller_offerrer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import darkengines.importer.Importer;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException, IOException {
		Importer importer = new Importer();
		importer.Import("J:/java/darkengines/data/data.xlsx");
//		Session session = DBSessionFactory.GetSession();
//		
//		DetachedCriteria sub = DetachedCriteria.forClass(Profile.class, "profile")
//		.createAlias("profile.programmingLanguages", "programmingLanguage")
//		.add(Restrictions.in("programmingLanguage.id", new HashSet(Arrays.asList(1l,2l))))
//		.setProjection(Projections.rowCount())
//		.add(Property.forName("profile.id").eqProperty("user.profile.id"));
//		
//		Criteria criteria = session.createCriteria(User.class, "user")
//		.add(Subqueries.eq(2l, sub));
//		
//		ArrayList<User> p = (ArrayList<User>)criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
//		System.out.println(p);
//		session.close();
	}
}

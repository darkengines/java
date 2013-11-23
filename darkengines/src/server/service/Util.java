package server.service;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import server.User;
import server.UserSession;
import darkengines.database.DBSessionFactory;

public class Util {
	public static User getUserByToken(String token) {
		Session session = DBSessionFactory.GetSession();
		UserSession userSession = (UserSession)session.createCriteria(UserSession.class).add(Restrictions.eq("token", token)).uniqueResult();
		session.close();
		return userSession.getUser();
	}
}

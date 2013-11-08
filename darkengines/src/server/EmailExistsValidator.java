package server;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import darkengines.database.DBSessionFactory;

public class EmailExistsValidator implements IValidator {

	@Override
	public ValidatorResult validate(String[] strings,
			Map<String, String[]> parameters) {
		String email = strings[0];
		Session session = DBSessionFactory.GetSession();
		User user = (User)session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
		if (user != null) {
			return new ValidatorResult("Ce courriel est déjà utilisé");
		}
		return null;
	}

}

package server;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import darkengines.database.DBSessionFactory;

public class GoodCredentialsValidator implements IValidator {

	@Override
	public ValidatorResult validate(String[] strings,
			Map<String, String[]> parameters) {
		try {
			if (DBSessionFactory.GetSession().createCriteria(User.class).add(Restrictions.eq("email", parameters.get("email")[0])).add(Restrictions.eq("password", User.hashPassword(strings[0]))).uniqueResult() == null) {
				return new ValidatorResult("Mot de passe incorrect");
			}
		} catch (NoSuchAlgorithmException e) {
			return new ValidatorResult("Mot de passe incorrect");
		}
		return null;
	}

}

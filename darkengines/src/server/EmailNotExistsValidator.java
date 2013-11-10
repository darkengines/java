package server;

import java.util.Map;

import org.hibernate.criterion.Restrictions;

import darkengines.database.DBSessionFactory;

public class EmailNotExistsValidator implements IValidator {

	@Override
	public ValidatorResult validate(String[] strings,
			Map<String, String[]> parameters) {
		if (DBSessionFactory.GetSession().createCriteria(User.class).add(Restrictions.eq("email", strings[0])).uniqueResult() == null) {
			return new ValidatorResult("Courriel innexistant");
		}
		return null;
	}

}

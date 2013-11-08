package server;

import java.util.Map;

public class UserTypeValidator implements IValidator {

	@Override
	public ValidatorResult validate(String[] strings,
			Map<String, String[]> parameters) {
		try {
			UserType type = UserType.valueOf(strings[0]);
		} catch (Exception e) {
			return new ValidatorResult("Type utilisateur inconnu");
		}
		return null;
	}
	
}

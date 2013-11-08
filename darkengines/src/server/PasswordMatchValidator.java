package server;

import java.util.Map;

public class PasswordMatchValidator implements IValidator {

	@Override
	public ValidatorResult validate(String[] strings,
			Map<String, String[]> parameters) {
		String password = strings[0];
		String rePassword = parameters.get("password_confirmation")[0];
		if (!password.equals(rePassword)) {
			return new ValidatorResult("Les mots de passe de correspondent pas");
		}
		return null;
	}

}

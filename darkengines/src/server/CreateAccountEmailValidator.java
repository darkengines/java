package server;

import java.util.Map;

public class CreateAccountEmailValidator implements IValidator {
	EmailValidator emailValidator = new EmailValidator();
	EmailExistsValidator emailExistsValidator = new EmailExistsValidator();
	@Override
	public ValidatorResult validate(String[] strings,
			Map<String, String[]> parameters) {
		ValidatorResult result = emailValidator.validate(strings, parameters);
		if (result != null) {
			return result;
		}
		result = emailExistsValidator.validate(strings, parameters);
		return result;
	}
	
}

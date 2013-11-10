package server;

import java.util.Map;

public class LoginEmailValidator implements IValidator {
	
	private EmailValidator emailValidator;
	private EmailNotExistsValidator emailExistsValidator;
	
	public LoginEmailValidator() {
		emailValidator = new EmailValidator();
		emailExistsValidator = new EmailNotExistsValidator();
	}
	
	@Override
	public ValidatorResult validate(String[] strings,
			Map<String, String[]> parameters) {
		ValidatorResult result = null;
		result = emailValidator.validate(strings, parameters);
		if (result == null) {
			result = emailExistsValidator.validate(strings, parameters);
		}
		return result;
	}

}

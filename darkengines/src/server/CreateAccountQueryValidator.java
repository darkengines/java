package server;

public class CreateAccountQueryValidator extends QueryValidator {

	@Override
	protected void setValidators() {
		validators.put("email", new CreateAccountEmailValidator());
		validators.put("password", new PasswordMatchValidator());
		validators.put("type", new UserTypeValidator());
	}

}

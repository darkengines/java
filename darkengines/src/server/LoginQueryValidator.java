package server;

public class LoginQueryValidator extends QueryValidator {

	@Override
	protected void setValidators() {
		validators.put("email", new LoginEmailValidator());
		validators.put("password", new GoodCredentialsValidator());
	}

}

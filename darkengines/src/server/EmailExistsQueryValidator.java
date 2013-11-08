package server;

public class EmailExistsQueryValidator extends QueryValidator {

	@Override
	protected void setValidators() {
		validators.put("email", new EmailValidator());		
	}

}

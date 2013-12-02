package offerer_caller.model;

import darkengines.model.MatchesEmail;
import darkengines.model.NotNull;
import darkengines.model.Validator;
import darkengines.model.Validators;

public class LoginInputModel {
	@Validators({
		@Validator(rule=NotNull.class, errorText="email.null"),
		@Validator(rule=MatchesEmail.class, errorText="email.format")
	})
	private String email;
	@Validators({
		@Validator(rule=NotNull.class, errorText="password.null"),
	})
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

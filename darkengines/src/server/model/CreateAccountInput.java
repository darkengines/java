package server.model;

import server.UserType;
import darkengines.model.MatchesEmail;
import darkengines.model.NotNull;
import darkengines.model.Validator;
import darkengines.model.Validators;

public class CreateAccountInput {
	@Validators({
		@Validator(errorText = "email.null", rule = NotNull.class),
		@Validator(errorText = "email.format", rule = MatchesEmail.class)
	})
	private String email;
	@Validators({
		@Validator(errorText = "password.null", rule = NotNull.class)
	})
	private String password;
	@Validators({
		@Validator(errorText = "type.null", rule = NotNull.class)
	})
	private UserType type;
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
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	
}

package caller_offerrer.model;

import caller_offerrer.Caller;
import caller_offerrer.Offerrer;
import caller_offerrer.User;
import caller_offerrer.UserType;
import darkengines.model.MatchesEmail;
import darkengines.model.NotNull;
import darkengines.model.Validator;
import darkengines.model.Validators;

public class CreateAccountInputModel {
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
	public User toUser() throws Exception {
		User user = null;
		switch (type) {
			case Offerrer: {
				user = new Offerrer();
				break;
			}
			case Caller: {
				user = new Caller();
				break;
			}
			default: {
				throw new Exception("user.type.invalid");
			}
		}
		user.setEmail(email);
		user.setPassword(User.hashPassword(password));
		return user;
	}
}

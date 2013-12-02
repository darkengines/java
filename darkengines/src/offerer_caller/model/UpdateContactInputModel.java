package offerer_caller.model;

import darkengines.model.NotNull;
import darkengines.model.Validator;
import darkengines.model.Validators;
import offerer_caller.Contact;

public class UpdateContactInputModel {
	@Validators({
		@Validator(rule=NotNull.class, errorText="token.null"),
	})
	private String token;
	private String phone;
	private String email;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Contact toContact() {
		return new Contact(email, phone);
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}

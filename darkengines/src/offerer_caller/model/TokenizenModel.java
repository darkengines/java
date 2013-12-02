package offerer_caller.model;

import darkengines.model.NotNull;
import darkengines.model.Validator;
import darkengines.model.Validators;

public class TokenizenModel {
	@Validators({
		@Validator(rule=NotNull.class, errorText="token.null"),
	})
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

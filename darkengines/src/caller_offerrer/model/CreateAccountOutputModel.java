package caller_offerrer.model;

import caller_offerrer.UserType;

public class CreateAccountOutputModel {
	private String token;
	private long userId;
	private UserType type;

	public String getToken() {
		return token;
	}

	public void setToken(String sessionToken) {
		this.token = sessionToken;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
	
}

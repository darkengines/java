package caller_offerrer.model;

import caller_offerrer.UserType;

public class LoginOutputModel {
	private String token;
	private long userId;
	private UserType userType;

	public UserType getType() {
		return userType;
	}

	public void setType(UserType type) {
		this.userType = type;
	}

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
}

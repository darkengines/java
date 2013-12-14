package caller_offerrer.model;

import caller_offerrer.User;
import caller_offerrer.UserType;

public class CreateAccountOutputModel {
	private String token;
	private long userId;
	private UserType userType;
	
	public CreateAccountOutputModel(User user, String token) {
		userId = user.getId();
		userType = user.getType();
		this.token = token;
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

	public UserType getType() {
		return userType;
	}

	public void setType(UserType type) {
		this.userType = type;
	}
	
}

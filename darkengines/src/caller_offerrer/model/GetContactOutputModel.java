package caller_offerrer.model;

import caller_offerrer.User;

public class GetContactOutputModel {
	private String phone;
	private String email;
	public GetContactOutputModel(User user) {
		phone = user.getContact().getPhone();
		email = user.getContact().getEmail();
	}
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
	
}

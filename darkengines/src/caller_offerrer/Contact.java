package caller_offerrer;

import javax.persistence.Entity;

import darkengines.database.IdentifiedEntity;

@Entity
public class Contact extends IdentifiedEntity {
	private String phone;
	private String email;
	public Contact() {
		
	}
	public Contact(String email, String phone) {
		this.email = email;
		this.phone = phone;
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

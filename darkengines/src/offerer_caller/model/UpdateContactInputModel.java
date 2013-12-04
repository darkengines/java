package offerer_caller.model;

import offerer_caller.Contact;

public class UpdateContactInputModel extends TokenizenModel {
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
	public Contact mergeContact(Contact contact) {
		contact.setEmail(email);
		contact.setPhone(phone);
		return contact;
	}
}

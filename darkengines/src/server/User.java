package server;

import javax.persistence.Entity;

import darkengines.database.IdentifiedEntity;

@Entity
public class User extends IdentifiedEntity {
	
	private String email;
	private String password;
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

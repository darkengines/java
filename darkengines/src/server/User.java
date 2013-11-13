package server;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import darkengines.database.IdentifiedEntity;

@Entity
public class User extends IdentifiedEntity {
	
	private String email;
	private String password;
	private UserType type;
	@OneToOne
	private Identity identity;
	@OneToOne
	private Profile profile;
	
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
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public static String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(password.getBytes());
		return new BigInteger( 1, md.digest() ).toString(16);
	}
}

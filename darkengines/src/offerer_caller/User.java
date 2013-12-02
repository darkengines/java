package offerer_caller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import darkengines.database.IdentifiedEntity;

@Entity
public abstract class User extends IdentifiedEntity {
	private String email;
	private String password;
	private Contact contact;
	
	public User() {
		contact = new Contact();
		contact.setEmail(email);
	}
	
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
	public static String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(password.getBytes());
		return new BigInteger( 1, md.digest() ).toString(16);
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public abstract UserType getType();
	public static User getUserByToken(String token, Session session) {
		UserSession userSession = (UserSession)session.createCriteria(UserSession.class).add(Restrictions.eq("token", token)).uniqueResult();
		return userSession.getUser();
	}
}

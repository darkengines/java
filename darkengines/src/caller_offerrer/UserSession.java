package caller_offerrer;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import darkengines.database.IdentifiedEntity;

@Entity
public class UserSession extends IdentifiedEntity {
	@ManyToOne
	private User user;
	private String token;
	private Date openedOn;
	private long length;
	public UserSession() {
		
	}
	public UserSession(User user, long length) throws NoSuchAlgorithmException {
		this.user = user;
		openedOn = new Date();
		Long timestamp = openedOn.getTime();
		Double rnd = Math.random();
		token = User.hashPassword(user.getPassword()+timestamp.toString()+rnd.toString());
		this.length = length; 
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getOpenedOn() {
		return openedOn;
	}
	public long getLength() {
		return length;
	}
	public String getToken() {
		return token;
	}
}

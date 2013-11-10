package server;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import darkengines.database.IdentifiedEntity;

@Entity
public class UserSession extends IdentifiedEntity {
	@ManyToOne
	private User user;
	private Date openedOn;
	private long length;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getOpenedOn() {
		return openedOn;
	}
	public void setOpenedOn(Date openedOn) {
		this.openedOn = openedOn;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
}

package server;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Offerer extends User {
	@OneToOne
	private Profile profile;
	@OneToOne
	private Identity identity;
	
	public Offerer() {
		
	}
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
}

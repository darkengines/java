package caller_offerrer;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import darkengines.database.IdentifiedEntity;

@Entity
public class Offer extends IdentifiedEntity {
	@OneToOne
	private Profile profile;
	private String description;
	
	public Offer() {
		profile = new Profile();
	}
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}

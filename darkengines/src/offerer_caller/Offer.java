package offerer_caller;

import javax.persistence.Entity;

import darkengines.database.IdentifiedEntity;

@Entity
public class Offer extends IdentifiedEntity {
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

package server;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import darkengines.database.IdentifiedEntity;

@Entity
public class City extends IdentifiedEntity {
	private String name;
	@ManyToOne
	private PostalCode postalCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PostalCode getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(PostalCode postalCode) {
		this.postalCode = postalCode;
	}
}

package offerer_caller;

import javax.persistence.Entity;

import darkengines.database.IdentifiedEntity;

@Entity
public class PostalCode extends IdentifiedEntity {
	String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String toString() {
		return code;
	}
}

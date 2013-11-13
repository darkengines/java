package darkengines.database;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ListItem extends IdentifiedEntity {
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

package caller_offerrer;

import javax.persistence.Entity;

import darkengines.database.IdentifiedEntity;

@Entity
public abstract class Call extends IdentifiedEntity {
	public abstract Class<? extends Call> getType();
}

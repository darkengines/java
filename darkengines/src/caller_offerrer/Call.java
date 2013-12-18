package caller_offerrer;

import javax.persistence.Entity;
import javax.persistence.Table;

import darkengines.database.IdentifiedEntity;

@Entity
@Table(name="`call`")
public abstract class Call extends IdentifiedEntity {
	public abstract CallType getCallType();
}

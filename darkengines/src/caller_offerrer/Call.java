package caller_offerrer;

import javax.persistence.Entity;
import javax.persistence.Table;

import darkengines.database.IdentifiedEntity;

@Entity
@Table(name="`call`")
public abstract class Call extends IdentifiedEntity {
	private String title;
	public abstract CallType getCallType();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}

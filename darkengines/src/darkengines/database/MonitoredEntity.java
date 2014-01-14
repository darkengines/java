package darkengines.database;

import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass()
public abstract class MonitoredEntity extends IdentifiedEntity {
	protected Date createdOn;
	protected Date updatedOn;
	public MonitoredEntity() {
		createdOn = new Date();
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}

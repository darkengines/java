package caller_offerrer;

import javax.persistence.Entity;
import javax.persistence.Lob;

import darkengines.database.IdentifiedEntity;

@Entity
public class Image extends IdentifiedEntity {
	@Lob
	private byte[] data;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
}

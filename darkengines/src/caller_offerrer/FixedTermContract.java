package caller_offerrer;

import javax.persistence.Entity;

@Entity
public class FixedTermContract extends Contract {
	private int length;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}

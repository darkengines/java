package caller_offerrer;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Offerrer extends User {
	@OneToOne
	private Offer offer;
	
	public Offerrer() {
		offer = new Offer();
	}
	
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public UserType getType() {
		return UserType.Offerrer;
	}
}

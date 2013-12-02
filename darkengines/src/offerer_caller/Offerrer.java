package offerer_caller;

import javax.persistence.Entity;

@Entity
public class Offerrer extends User {
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

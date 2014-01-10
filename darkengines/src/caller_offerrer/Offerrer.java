package caller_offerrer;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Offerrer extends User {
	@OneToOne
	private Offer offer;
	@OneToOne
	private SearchCallQuery searchCallQuery;
	
	public Offerrer() {
		offer = new Offer();
		searchCallQuery = new SearchCallQuery();
	}
	
	public SearchCallQuery getSearchCallQuery() {
		return searchCallQuery;
	}

	public void setSearchCallQuery(SearchCallQuery searchCallQuery) {
		this.searchCallQuery = searchCallQuery;
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

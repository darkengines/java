package caller_offerrer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Caller extends User {
	
	@ManyToMany
	private Set<Call> calls;
	@OneToOne
	private SearchOfferrerQuery searchOfferrerQuery;
	public void setSearchOfferrerQuery(SearchOfferrerQuery searchOfferrerQuery) {
		this.searchOfferrerQuery = searchOfferrerQuery;
	}

	public Caller() {
		calls = new HashSet<Call>();
		searchOfferrerQuery = new SearchOfferrerQuery();
	}
	
	public Set<Call> getCalls() {
		return calls;
	}

	public void setCalls(Set<Call> calls) {
		this.calls = calls;
	}
	
	public UserType getType() {
		return UserType.Caller;
	}

	public SearchOfferrerQuery getSearchOfferrerQuery() {
		return searchOfferrerQuery;
	}
}

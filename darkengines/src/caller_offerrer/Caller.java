package caller_offerrer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Caller extends User {
	@ManyToMany
	private Set<Call> calls;
	public Caller() {
		calls = new HashSet<Call>();
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
}

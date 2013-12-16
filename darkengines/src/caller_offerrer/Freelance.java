package caller_offerrer;

import javax.persistence.Entity;

@Entity
public class Freelance extends Call {
	private Float budget;

	public Float getBudget() {
		return budget;
	}

	public void setBudget(Float budget) {
		this.budget = budget;
	}

	@Override
	public CallType getCallType() {
		return CallType.Freelance;
	}
	
}

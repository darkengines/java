package caller_offerrer;

import javax.persistence.Entity;

@Entity
public class PermanentContract extends Contract{

	@Override
	public CallType getCallType() {
		return CallType.PermanentContract;
	}

	@Override
	public float getRemuneration() {
		return salary;
	}
	
}

package caller_offerrer;

import javax.persistence.Entity;

@Entity
public class PermanentContract extends Contract{

	@Override
	public Class<? extends Call> getType() {
		return PermanentContract.class;
	}
	
}

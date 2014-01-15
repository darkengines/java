package caller_offerrer;

import javax.persistence.Entity;

@Entity
public abstract class Contract extends Call {
	protected float salary;

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
}

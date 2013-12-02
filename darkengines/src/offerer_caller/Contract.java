package offerer_caller;

import javax.persistence.Entity;

@Entity
public abstract class Contract extends Call {
	private float salary;

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
}

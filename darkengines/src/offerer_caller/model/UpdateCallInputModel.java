package offerer_caller.model;

import darkengines.model.NotNull;
import darkengines.model.Validators;
import darkengines.model.Validator;
import offerer_caller.Call;
import offerer_caller.CallType;
import offerer_caller.Contract;
import offerer_caller.FixedTermContract;
import offerer_caller.Freelance;
import offerer_caller.PermanentContract;

public class UpdateCallInputModel extends TokenizenModel {
	@Validators(
		@Validator(rule=NotNull.class, errorText="callId.null")
	)
	private long callId;
	private CallType type;
	private float salary;
	private float budget;
	private int length;
	
	public long getCallId() {
		return callId;
	}
	public void setCallId(long callId) {
		this.callId = callId;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public float getBudget() {
		return budget;
	}
	public void setBudget(float budget) {
		this.budget = budget;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public CallType getType() {
		return type;
	}
	public void setType(CallType type) {
		this.type = type;
	}
	public Call mergeCall(Call call) throws Exception {
		switch(type) {
			case FixedTermContract: {
				if (call == null) call = new FixedTermContract();
				return mergeFixedTermContract((FixedTermContract)call);
			}
			case PermanentContract: {
				if (call == null) call = new PermanentContract();
				return mergePermanentContract((PermanentContract)call);
			}
			case Freelance: {
				if (call == null) call = new Freelance();
				return mergeFreelance((Freelance)call);
			}
			default: {
				throw new Exception("type.invalid");
			}
		}
	}
	protected Freelance mergeFreelance(Freelance freelance) {
		freelance.setBudget(budget);
		return freelance;
	}
	protected Contract mergeContract(Contract contract) {
		contract.setSalary(salary);
		return contract;
	}
	protected PermanentContract mergePermanentContract(PermanentContract contract) {
		contract = (PermanentContract)mergeContract(contract);
		return contract;
	}
	protected FixedTermContract mergeFixedTermContract(FixedTermContract contract) {
		contract = (FixedTermContract)mergeContract(contract);
		contract.setLength(length);
		return contract;
	}
}

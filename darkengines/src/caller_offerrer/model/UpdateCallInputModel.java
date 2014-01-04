package caller_offerrer.model;

import caller_offerrer.Call;
import caller_offerrer.CallType;
import caller_offerrer.Contract;
import caller_offerrer.FixedTermContract;
import caller_offerrer.Freelance;
import caller_offerrer.PermanentContract;

public class UpdateCallInputModel extends TokenizenModel {
	private Long callId;
	private CallType type;
	private float salary;
	private float budget;
	private int length;
	
	public Long getCallId() {
		return callId;
	}
	public void setCallId(Long callId) {
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
	public Call toCall() throws Exception {
		Call call = null;
		switch(type) {
			case FixedTermContract: {
				call = mergeFixedTermContract(new FixedTermContract());
				break;
			}
			case PermanentContract: {
				call = mergePermanentContract(new PermanentContract());
				break;
			}
			case Freelance: {
				call = mergeFreelance(new Freelance());
				break;
			}
			default: {
				throw new Exception("type.invalid");
			}
		}
		if (callId != null) {
			call.setId(callId);
		}
		return call;
	}
	public Call mergeCall(Call call) throws Exception {
		call.setId(callId);
		
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

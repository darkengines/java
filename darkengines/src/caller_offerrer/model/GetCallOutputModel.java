package caller_offerrer.model;

import caller_offerrer.Call;
import caller_offerrer.CallType;
import caller_offerrer.Contract;
import caller_offerrer.FixedTermContract;
import caller_offerrer.Freelance;
import caller_offerrer.PermanentContract;

public class GetCallOutputModel {
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
	public GetCallOutputModel(Call call) throws Exception {
		type = call.getCallType(); 
		switch(type) {
			case FixedTermContract: {
				fillFixedTermContract((FixedTermContract)call);
			}
			case PermanentContract: {
				fillPermanentContract((PermanentContract)call);
			}
			case Freelance: {
				fillFreelance((Freelance)call);
			}
			default: {
				throw new Exception("type.invalid");
			}
		}
	}
	protected void fillFreelance(Freelance freelance) {
		budget = freelance.getBudget();
	}
	protected void fillContract(Contract contract) {
		salary = contract.getSalary();
	}
	protected void fillPermanentContract(PermanentContract contract) {
		
	}
	protected void fillFixedTermContract(FixedTermContract contract) {
		length = contract.getLength();
	}
}

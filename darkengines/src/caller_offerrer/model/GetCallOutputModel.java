package caller_offerrer.model;

import java.util.Map;

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
	private String title;
	private Map<Long, String> programmingLanguageIds;
	private Map<Long, String> frameworkIds;
	private Map<Long, String> languageIds;
	private Integer seniority;
	private Integer diploma;
	private String description;
	
	public Map<Long, String> getProgrammingLanguageIds() {
		return programmingLanguageIds;
	}

	public void setProgrammingLanguageIds(Map<Long, String> programmingLanguageIds) {
		this.programmingLanguageIds = programmingLanguageIds;
	}

	public Map<Long, String> getFrameworkIds() {
		return frameworkIds;
	}

	public void setFrameworkIds(Map<Long, String> frameworkIds) {
		this.frameworkIds = frameworkIds;
	}

	public Map<Long, String> getLanguageIds() {
		return languageIds;
	}

	public void setLanguageIds(Map<Long, String> languageIds) {
		this.languageIds = languageIds;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}

	public void setDiploma(Integer diploma) {
		this.diploma = diploma;
	}

	public Map<Long, String> getProgrammingLanguages() {
		return programmingLanguageIds;
	}

	public void setProgrammingLanguages(Map<Long, String> programmingLanguages) {
		this.programmingLanguageIds = programmingLanguages;
	}

	public Map<Long, String> getFrameworks() {
		return frameworkIds;
	}

	public void setFrameworks(Map<Long, String> frameworks) {
		this.frameworkIds = frameworks;
	}

	public Map<Long, String> getLanguages() {
		return languageIds;
	}

	public void setLanguages(Map<Long, String> languages) {
		this.languageIds = languages;
	}

	public int getSeniority() {
		return seniority;
	}

	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}

	public int getDiploma() {
		return diploma;
	}

	public void setDiploma(int diploma) {
		this.diploma = diploma;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
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
		programmingLanguageIds = darkengines.set.Util.toMap(call.getProgrammingLanguages());
		frameworkIds = darkengines.set.Util.toMap(call.getFrameworks());
		languageIds = darkengines.set.Util.toMap(call.getLanguages());
		seniority = call.getSeniority();
		diploma = call.getDiploma();
		type = call.getCallType();
		title = call.getTitle();
		callId = call.getId();
		description = call.getDescription();
		switch(type) {
			case FixedTermContract: {
				fillFixedTermContract((FixedTermContract)call);
				break;
			}
			case PermanentContract: {
				fillPermanentContract((PermanentContract)call);
				break;
			}
			case Freelance: {
				fillFreelance((Freelance)call);
				break;
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
		fillContract(contract);
	}
	protected void fillFixedTermContract(FixedTermContract contract) {
		fillContract(contract);
		length = contract.getLength();
	}
}

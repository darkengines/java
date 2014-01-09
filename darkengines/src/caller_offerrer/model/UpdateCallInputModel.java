package caller_offerrer.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import caller_offerrer.Call;
import caller_offerrer.CallType;
import caller_offerrer.Contract;
import caller_offerrer.FixedTermContract;
import caller_offerrer.Framework;
import caller_offerrer.Freelance;
import caller_offerrer.Language;
import caller_offerrer.PermanentContract;
import caller_offerrer.ProgrammingLanguage;

public class UpdateCallInputModel extends TokenizenModel {
	private Long callId;
	private CallType type;
	private float salary;
	private float budget;
	private int length;
	private String title;
	private Set<Long> programmingLanguageIds;
	private Set<Long> frameworkIds;
	private Set<Long> languageIds;
	private Integer diploma;
	private Integer seniority;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Long> getProgrammingLanguageIds() {
		return programmingLanguageIds;
	}
	public void setProgrammingLanguageIds(Set<Long> programmingLanguageIds) {
		this.programmingLanguageIds = programmingLanguageIds;
	}
	public Set<Long> getFrameworkIds() {
		return frameworkIds;
	}
	public void setFrameworkIds(Set<Long> frameworkIds) {
		this.frameworkIds = frameworkIds;
	}
	public Set<Long> getLanguageIds() {
		return languageIds;
	}
	public void setLanguageIds(Set<Long> languageIds) {
		this.languageIds = languageIds;
	}
	public Integer getDiploma() {
		return diploma;
	}
	public void setDiploma(Integer diplomaId) {
		this.diploma = diplomaId;
	}
	public Integer getSeniority() {
		return seniority;
	}
	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Call toCall(Session session) throws Exception {
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
		call.setTitle(title);
		if (callId != null) {
			call.setId(callId);
		}
		if (programmingLanguageIds != null) {
			call.getProgrammingLanguages().clear();
			if (programmingLanguageIds.size() > 0) {
				Collection<ProgrammingLanguage> programmingLanguages = session.createCriteria(ProgrammingLanguage.class)
						.add(Restrictions.in("id", programmingLanguageIds))
						.list();
				call.getProgrammingLanguages().addAll((Collection)programmingLanguages);
			}
		}
		if (frameworkIds != null) {
			call.getFrameworks().clear();
			if (frameworkIds.size() > 0) {
				Set<Framework> frameworks = new HashSet<Framework>(session.createCriteria(Framework.class)
						.add(Restrictions.in("id", frameworkIds))
						.list());
				call.getFrameworks().addAll((Collection)frameworks);
			}
		}
		if (languageIds != null) {
			call.getLanguages().clear();
			if (languageIds.size() > 0) {
				Set<Language> languages = new HashSet<Language>(session.createCriteria(Language.class)
						.add(Restrictions.in("id", languageIds))
						.list());
				call.getLanguages().addAll((Collection)languages);
			}
		}
		call.setDiploma(diploma);
		call.setSeniority(seniority);
		call.setDescription(description);
		return call;
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

package caller_offerrer.model;

import java.util.Set;

public class SearchOfferInputModel {
	private Set<Long> programmingLanguageIds;
	private Set<Long> frameworkIds;
	private Set<Long> languageIds;
	private Integer diploma;
	private Integer seniority;
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
}

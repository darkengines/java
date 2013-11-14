package server.model;

import java.util.Set;

public class ProfileOutput {
	private Set<ListValueModel> programmingLanguages;
	private Set<ListValueModel> frameworks;
	private Set<ListValueModel> languages;
	private ListValueModel diploma;
	private Integer seniority;
	public Set<ListValueModel> getProgrammingLanguageIds() {
		return programmingLanguages;
	}
	public void setProgrammingLanguage(Set<ListValueModel> programmingLanguageIds) {
		this.programmingLanguages = programmingLanguageIds;
	}
	public Set<ListValueModel> getFrameworks() {
		return frameworks;
	}
	public void setFrameworks(Set<ListValueModel> frameworkIds) {
		this.frameworks = frameworkIds;
	}
	public Set<ListValueModel> getLanguages() {
		return languages;
	}
	public void setLanguages(Set<ListValueModel> languageIds) {
		this.languages = languageIds;
	}
	public ListValueModel getDiploma() {
		return diploma;
	}
	public void setDiploma(ListValueModel diploma) {
		this.diploma = diploma;
	}
	public Integer getSeniority() {
		return seniority;
	}
	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}
}

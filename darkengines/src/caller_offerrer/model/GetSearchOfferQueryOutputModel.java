package caller_offerrer.model;

import java.util.Map;

import caller_offerrer.Caller;

public class GetSearchOfferQueryOutputModel {
	private Map<Long, String> programmingLanguageIds;
	private Map<Long, String> frameworkIds;
	private Map<Long, String> languageIds;
	private Integer seniority;
	private Integer diploma;
	
	public GetSearchOfferQueryOutputModel(Caller caller) {
		programmingLanguageIds = darkengines.set.Util.toMap(caller.getSearchOfferrerQuery().getProgrammingLanguages());
		frameworkIds = darkengines.set.Util.toMap(caller.getSearchOfferrerQuery().getFrameworks());
		languageIds = darkengines.set.Util.toMap(caller.getSearchOfferrerQuery().getLanguages());
		seniority = caller.getSearchOfferrerQuery().getSeniority();
		diploma = caller.getSearchOfferrerQuery().getDiploma();
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
	
}

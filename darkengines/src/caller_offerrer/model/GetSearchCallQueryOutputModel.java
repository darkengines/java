package caller_offerrer.model;

import java.util.Map;

import caller_offerrer.Caller;
import caller_offerrer.Offerrer;

public class GetSearchCallQueryOutputModel {
	private Map<Long, String> programmingLanguageIds;
	private Map<Long, String> frameworkIds;
	private Map<Long, String> languageIds;
	private Integer seniority;
	private Integer diploma;
	
	public GetSearchCallQueryOutputModel(Offerrer offerrer) {
		programmingLanguageIds = darkengines.set.Util.toMap(offerrer.getSearchCallQuery().getProgrammingLanguages());
		frameworkIds = darkengines.set.Util.toMap(offerrer.getSearchCallQuery().getFrameworks());
		languageIds = darkengines.set.Util.toMap(offerrer.getSearchCallQuery().getLanguages());
		seniority = offerrer.getSearchCallQuery().getSeniority();
		diploma = offerrer.getSearchCallQuery().getDiploma();
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

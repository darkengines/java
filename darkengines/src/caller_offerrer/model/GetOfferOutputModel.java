package caller_offerrer.model;

import java.util.Map;

import caller_offerrer.Offerrer;

public class GetOfferOutputModel {
	private Long offerrerId;
	private Long photoId;
	private Map<Long, String> programmingLanguageIds;
	private Map<Long, String> frameworkIds;
	private Map<Long, String> languageIds;
	private String description;
	private Integer seniority;
	private Integer diploma;
	private String email;
	private String phone;
	
	public GetOfferOutputModel(Offerrer offerrer) {
		offerrerId = offerrer.getId();
		photoId = offerrer.getOffer().getProfile().getImage().getId();
		programmingLanguageIds = darkengines.set.Util.toMap(offerrer.getOffer().getProfile().getProgrammingLanguages());
		frameworkIds = darkengines.set.Util.toMap(offerrer.getOffer().getProfile().getFrameworks());
		languageIds = darkengines.set.Util.toMap(offerrer.getOffer().getProfile().getLanguages());
		description = offerrer.getOffer().getDescription();
		seniority = offerrer.getOffer().getProfile().getSeniority();
		diploma = offerrer.getOffer().getProfile().getDiploma();
		email = offerrer.getContact().getEmail();
		phone = offerrer.getContact().getPhone();
	}
	
	public long getOfferrerId() {
		return offerrerId;
	}

	public void setOfferrerId(long offerrerId) {
		this.offerrerId = offerrerId;
	}

	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}

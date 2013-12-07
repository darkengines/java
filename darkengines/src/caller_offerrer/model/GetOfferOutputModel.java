package caller_offerrer.model;

import java.util.Map;

import caller_offerrer.Offerrer;

public class GetOfferOutputModel {
	private Long offerrerId;
	private Long photoId;
	private Map<Long, String> programmingLanguages;
	private Map<Long, String> frameworks;
	private Map<Long, String> languages;
	private String description;
	private Integer seniority;
	private Integer diploma;
	private String email;
	private String phone;
	
	public GetOfferOutputModel(Offerrer offerrer) {
		offerrerId = offerrer.getId();
		photoId = offerrer.getOffer().getProfile().getImage().getId();
		programmingLanguages = darkengines.set.Util.toMap(offerrer.getOffer().getProfile().getProgrammingLanguages());
		frameworks = darkengines.set.Util.toMap(offerrer.getOffer().getProfile().getFrameworks());
		languages = darkengines.set.Util.toMap(offerrer.getOffer().getProfile().getLanguages());
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
		return programmingLanguages;
	}

	public void setProgrammingLanguages(Map<Long, String> programmingLanguages) {
		this.programmingLanguages = programmingLanguages;
	}

	public Map<Long, String> getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(Map<Long, String> frameworks) {
		this.frameworks = frameworks;
	}

	public Map<Long, String> getLanguages() {
		return languages;
	}

	public void setLanguages(Map<Long, String> languages) {
		this.languages = languages;
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

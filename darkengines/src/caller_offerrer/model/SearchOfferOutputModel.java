package caller_offerrer.model;

import caller_offerrer.Offerrer;

public class SearchOfferOutputModel {
	private Long offerrerId;
	private String displayName;
	private Long photoId;
	public Long getOfferrerId() {
		return offerrerId;
	}
	public void setOfferrerId(Long offerrerId) {
		this.offerrerId = offerrerId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Long getphotoId() {
		return photoId;
	}
	public void setphotoId(Long photoId) {
		this.photoId = photoId;
	}
	public SearchOfferOutputModel(Offerrer offerrer) {
		offerrerId = offerrer.getId();
		displayName = offerrer.getEmail();
		photoId = offerrer.getOffer().getProfile().getImage().getId();
	}
}

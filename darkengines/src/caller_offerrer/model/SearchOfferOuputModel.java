package caller_offerrer.model;

public class SearchOfferOuputModel {
	private Long offerrerId;
	private String displayName;
	private String photoUrl;
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
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}	
}

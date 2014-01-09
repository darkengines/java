package caller_offerrer.model;


public class ReadOfferInputModel {
	private String token;
	private Long offerrerId;

	public Long getOfferrerId() {
		return offerrerId;
	}

	public void setOfferrerId(Long offerrerId) {
		this.offerrerId = offerrerId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}

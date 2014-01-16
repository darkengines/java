package caller_offerrer.model;


public class ReadCallInputModel {
	private String token;
	private Long callId;

	public Long getCallId() {
		return callId;
	}

	public void setCallId(Long callId) {
		this.callId = callId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}

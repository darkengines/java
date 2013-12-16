package caller_offerrer.model;

public class GetCallInputModel {
	private long userId;
	private long callId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getCallId() {
		return callId;
	}
	public void setCallId(long callId) {
		this.callId = callId;
	}
	
}

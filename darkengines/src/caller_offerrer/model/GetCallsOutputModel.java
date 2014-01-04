package caller_offerrer.model;

import caller_offerrer.Call;
import caller_offerrer.CallType;

public class GetCallsOutputModel {
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	private String title;
	private CallType type;
	
	public GetCallsOutputModel(Call call) {
		id = call.getId();
		title = call.getTitle();
		type = call.getCallType();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public CallType getType() {
		return type;
	}
	public void setType(CallType type) {
		this.type = type;
	}
}

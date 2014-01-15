package caller_offerrer.model;

import java.util.Date;

import caller_offerrer.Call;
import caller_offerrer.CallType;

public class SearchCallOutputModel {
	private Long callerId;
	private Long callId;
	private String title;
	private String description;
	private CallType type;
	private Date createdOn;
	private float remuneration;
	
	public float getRemuneration() {
		return remuneration;
	}

	public void setRemuneration(float remuneration) {
		this.remuneration = remuneration;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getCallerId() {
		return callerId;
	}

	public void setCallerId(Long callerId) {
		this.callerId = callerId;
	}

	public Long getCallId() {
		return callId;
	}

	public void setCallId(Long callId) {
		this.callId = callId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CallType getType() {
		return type;
	}

	public void setType(CallType type) {
		this.type = type;
	}

	public SearchCallOutputModel(Call call, long callerId) {
		this.callerId = callerId;
		callId = call.getId();
		title = call.getTitle();
		description = call.getDescription();
		if (description.length() > 256) {
			description = String.format("%s...", description.substring(0, 255));
		}
		createdOn = call.getCreatedOn();
		type = call.getCallType();
		remuneration = call.getRemuneration();
	}
}

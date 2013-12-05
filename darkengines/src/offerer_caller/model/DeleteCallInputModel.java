package offerer_caller.model;

import darkengines.model.NotNull;
import darkengines.model.Validators;
import darkengines.model.Validator;

public class DeleteCallInputModel extends TokenizenModel {
	@Validators(
			@Validator(rule=NotNull.class, errorText="callId.null")
	)
	private Long callId;
	
	public Long getCallId() {
		return callId;
	}
	public void setCallId(Long callId) {
		this.callId = callId;
	}
}

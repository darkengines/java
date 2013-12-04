package offerer_caller.service;

import offerer_caller.model.UpdateCallInputModel;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class UpdateCall extends JSonService<UpdateCallInputModel, Object> {

	@Override
	public Class<UpdateCallInputModel> getInputType() {
		return null;
	}

	@Override
	public Class<Object> getOutputType() {
		return null;
	}

	@Override
	public Object processJsonRequest(UpdateCallInputModel data)
			throws Exception {
		return null;
	}

}

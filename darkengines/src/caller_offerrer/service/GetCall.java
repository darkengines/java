package caller_offerrer.service;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import caller_offerrer.Call;
import caller_offerrer.Caller;
import caller_offerrer.model.GetCallInputModel;
import caller_offerrer.model.GetCallOutputModel;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class GetCall extends JSonService<GetCallInputModel, GetCallOutputModel> {

	@Override
	public Class<GetCallInputModel> getInputType() {
		return GetCallInputModel.class;
	}

	@Override
	public Class<GetCallOutputModel> getOutputType() {
		return GetCallOutputModel.class;
	}

	@Override
	public GetCallOutputModel processJsonRequest(GetCallInputModel data)
			throws Exception {
		Session session = DBSessionFactory.GetSession();
		Caller caller = (Caller)session.createCriteria(Caller.class).add(Restrictions.eq("id", data.getUserId())).uniqueResult();
		if (caller == null) {
			throw new Exception("userId.invalid");
		}
		Call call = null;
		if (caller.getCalls().size() > 0) {
			Call[] calls = (Call[])caller.getCalls().toArray();
			int i = calls.length;
			while (call == null && i-- > 0) {
				if (calls[i].getId() == data.getCallId()) call = calls[i];
			}
		}
		return call == null ? null : new GetCallOutputModel(call);
	}

}

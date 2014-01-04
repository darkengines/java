package caller_offerrer.service;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import caller_offerrer.Call;
import caller_offerrer.Caller;
import caller_offerrer.model.GetCallsInputModel;
import caller_offerrer.model.GetCallsOutputModel;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings({ "serial", "rawtypes" })
public class GetCalls extends JSonService<GetCallsInputModel, Set> {

	@Override
	public Class<GetCallsInputModel> getInputType() {
		return GetCallsInputModel.class;
	}

	@Override
	public Class<Set> getOutputType() {
		return Set.class;
	}

	@Override
	public Set processJsonRequest(GetCallsInputModel data)
			throws Exception {
		Session session = DBSessionFactory.GetSession();
		Caller caller = (Caller)session.createCriteria(Caller.class).add(Restrictions.eq("id", data.getUserId())).uniqueResult();
		if (caller == null) {
			throw new Exception("userId.invalid");
		}
		Set<Call> calls = caller.getCalls();
		HashSet<GetCallsOutputModel> callsModel = new HashSet<GetCallsOutputModel>();
		for (Call call: calls) {
			callsModel.add(new GetCallsOutputModel(call));
		}
		return callsModel;
	}

}

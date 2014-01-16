package caller_offerrer.service;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import caller_offerrer.Call;
import caller_offerrer.Caller;
import caller_offerrer.User;
import caller_offerrer.model.ReadCallInputModel;
import caller_offerrer.model.ReadCallOutputModel;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class ReadCall extends JSonService<ReadCallInputModel, ReadCallOutputModel> {

	@Override
	public Class<ReadCallInputModel> getInputType() {
		return ReadCallInputModel.class;
	}

	@Override
	public Class<ReadCallOutputModel> getOutputType() {
		return ReadCallOutputModel.class;
	}

	@Override
	public ReadCallOutputModel processJsonRequest(ReadCallInputModel data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		Call call = (Call)session.createCriteria(Call.class).add(Restrictions.eq("id", data.getCallId())).uniqueResult();
		if (call == null) {
			throw new Exception("call.id.invalid");
		}
		Caller caller = (Caller)session.createCriteria(Caller.class)
				.createAlias("calls", "call")
				.add(Restrictions.eq("call.id", call.getId()))
				.uniqueResult(); 
		Boolean fillContact = false;
		if (data.getToken() != null) {
			User user = (User)User.getUserByToken(data.getToken(), session);
			fillContact = user != null;
		}
		ReadCallOutputModel model = new ReadCallOutputModel(call, caller, fillContact);
		session.close();
		return model;
	}
	
}

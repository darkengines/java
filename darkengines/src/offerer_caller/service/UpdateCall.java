package offerer_caller.service;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import offerer_caller.Call;
import offerer_caller.Caller;
import offerer_caller.User;
import offerer_caller.model.UpdateCallInputModel;
import darkengines.database.DBSessionFactory;
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
		Boolean isNew = true;
		Session session = DBSessionFactory.GetSession();
		Caller user = (Caller)User.getUserByToken(data.getToken(), session);
		if (user == null) {
			throw new Exception("token.inavlid");
		}
		Call call = null;
		if (data.getCallId() != null) {
			call = getUserCallById(user, data.getCallId());
			isNew = call == null;
		}
		call = data.mergeCall(call);
		if (isNew) {
			user.getCalls().add(call);
		}
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(user);
		session.flush();
		transaction.commit();
		session.close();
		return null;
	}
	private Call getUserCallById(Caller caller, long id) {
		Set<Call> calls = caller.getCalls();
		for (Call call: calls) {
			if (call.getId() == id) return call;
		}
		return null;
	}

}

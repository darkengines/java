package caller_offerrer.service;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import caller_offerrer.Call;
import caller_offerrer.Caller;
import caller_offerrer.User;
import caller_offerrer.model.UpdateCallInputModel;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class DeleteCall extends JSonService<UpdateCallInputModel, Object> {

	@Override
	public Class<UpdateCallInputModel> getInputType() {
		return UpdateCallInputModel.class;
	}

	@Override
	public Class<Object> getOutputType() {
		return Object.class;
	}

	@Override
	public Object processJsonRequest(UpdateCallInputModel data)
			throws Exception {
		Session session = DBSessionFactory.GetSession();
		Caller user = (Caller)User.getUserByToken(data.getToken(), session);
		if (user == null) {
			throw new Exception("token.inavlid");
		}
		Call call = getUserCallById(user, data.getCallId());
		if (call == null) {
			throw new Exception("callId.Invalid");
		}
		user.getCalls().remove(call);
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

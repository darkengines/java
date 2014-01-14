package caller_offerrer.service;

import java.util.Date;
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
public class UpdateCall extends JSonService<UpdateCallInputModel, Object> {

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
			if (isNew)  {
				data.setCallId(null);
			}
		}
		if (call != null) {
			session.evict(call);
		}
		call = data.toCall(session);
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(call);
		if (isNew) {
			call.setCreatedOn(new Date());
			user.getCalls().add(call);
		} else {			
			session.createSQLQuery("update `call` set DTYPE = :dtype where id = :id")
			.setString("dtype", call.getClass().getSimpleName())
			.setLong("id", call.getId())
			.executeUpdate();
		}
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

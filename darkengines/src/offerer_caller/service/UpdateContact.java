package offerer_caller.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import offerer_caller.User;
import offerer_caller.model.UpdateContactInputModel;
import server.service.Util;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings({ "serial", "unused" })
public class UpdateContact extends JSonService<UpdateContactInputModel, Object> {

	@Override
	public Class<UpdateContactInputModel> getInputType() {
		return UpdateContactInputModel.class;
	}

	@Override
	public Class<Object> getOutputType() {
		return Object.class;
	}

	@Override
	public Object processJsonRequest(UpdateContactInputModel data)
			throws Exception {
		Session session = DBSessionFactory.GetSession();
		User user = User.getUserByToken(data.getToken(), session);
		if (user == null) {
			throw new Exception("token.inavlid");
		}
		user.setContact(data.mergeContact(user.getContact()));
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(user);
		session.flush();
		transaction.commit();
		session.close();
		return null;
	}

}

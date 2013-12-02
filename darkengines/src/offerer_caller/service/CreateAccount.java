package offerer_caller.service;

import offerer_caller.User;
import offerer_caller.UserSession;
import offerer_caller.model.CreateAccountInputModel;
import offerer_caller.model.CreateAccountOutputModel;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class CreateAccount extends JSonService<CreateAccountInputModel, CreateAccountOutputModel> {

	@Override
	public Class<CreateAccountInputModel> getInputType() {
		return CreateAccountInputModel.class;
	}

	@Override
	public Class<CreateAccountOutputModel> getOutputType() {
		return CreateAccountOutputModel.class;
	}

	@Override
	public CreateAccountOutputModel processJsonRequest(CreateAccountInputModel data)
			throws Exception {
		User user = data.toUser();
		Session session = DBSessionFactory.GetSession();
		if (session.createCriteria(User.class)
				.add(Restrictions.eq("email", user.getEmail())).uniqueResult() != null) {
			throw new Exception("email.exists");
		}
		Transaction transaction = session.beginTransaction();
		session.save(user);
		UserSession userSession = new UserSession(user, 0);
		session.save(userSession);
		session.flush();
		transaction.commit();
		session.close();
		CreateAccountOutputModel out = new CreateAccountOutputModel();
		out.setSessionToken(userSession.getToken());
		out.setUserId(user.getId());
		out.setType(user.getType());
		return out;
	}

}

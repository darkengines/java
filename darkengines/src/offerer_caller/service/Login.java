package offerer_caller.service;

import offerer_caller.User;
import offerer_caller.UserSession;
import offerer_caller.model.LoginInputModel;
import offerer_caller.model.LoginOutputModel;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class Login extends JSonService<LoginInputModel, LoginOutputModel> {

	@Override
	public Class<LoginInputModel> getInputType() {
		return LoginInputModel.class;
	}

	@Override
	public Class<LoginOutputModel> getOutputType() {
		return LoginOutputModel.class;
	}

	@Override
	public LoginOutputModel processJsonRequest(LoginInputModel data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		
		User user = (User)session.createCriteria(User.class)
		.add(Restrictions.eq("email", data.getEmail()).ignoreCase())
		.add(Restrictions.eq("password", User.hashPassword(data.getPassword())))
		.uniqueResult();
		
		if (user == null) {
			throw new Exception("credential.bad");
		}
		
		UserSession userSession = new UserSession(user, 0);
		
		Transaction transaction = session.beginTransaction();
		session.save(userSession);
		session.flush();
		transaction.commit();
		session.close();
		
		LoginOutputModel output = new LoginOutputModel();
		output.setSessionToken(userSession.getToken());
		output.setUserId(user.getId());
		output.setType(user.getType());
		
		return output;
	}

}

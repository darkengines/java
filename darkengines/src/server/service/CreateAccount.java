package server.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import server.User;
import server.UserSession;
import server.model.CreateAccountInput;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

public class CreateAccount extends JSonService<CreateAccountInput, String> {

	@Override
	public Class getInputType() {
		return CreateAccountInput.class;
	}

	@Override
	public Class getOutputType() {
		return String.class;
	}

	@Override
	public String processJsonRequest(CreateAccountInput data)
			throws Exception {
		User user = new User();
		user.setEmail(data.getEmail());
		user.setPassword(User.hashPassword(data.getPassword()));
		user.setType(data.getType());
		Session session = DBSessionFactory.GetSession();
		if (session.createCriteria(User.class)
				.add(Restrictions.eq("email", data.getEmail())).uniqueResult() != null) {
			throw new Exception("email.exists");
		}
		Transaction transaction = session.beginTransaction();
		session.save(user);
		UserSession userSession = new UserSession();
		userSession.setUser(user);
		userSession.setOpenedOn(new Date());
		userSession.setLength(0);
		session.save(userSession);
		session.flush();
		transaction.commit();
		session.close();
		return Long.toString(userSession.getId());
	}

}

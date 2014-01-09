package caller_offerrer.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import caller_offerrer.Caller;
import caller_offerrer.Offerrer;
import caller_offerrer.User;
import caller_offerrer.UserSession;
import caller_offerrer.UserType;
import caller_offerrer.model.CreateAccountInputModel;
import caller_offerrer.model.CreateAccountOutputModel;
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
		session.save(user.getContact());
		if (data.getType() == UserType.Offerrer) {
			Offerrer offerrer = (Offerrer)user;
			session.save(offerrer.getOffer().getProfile().getImage());
			session.save(offerrer.getOffer().getProfile());
			session.save(offerrer.getOffer());
		}
		if (data.getType() == UserType.Caller) {
			Caller caller = (Caller)user;
			session.save(caller.getSearchOfferrerQuery());
		}
		session.save(user);
		UserSession userSession = new UserSession(user, 0);
		session.save(userSession);
		session.flush();
		transaction.commit();
		CreateAccountOutputModel out = new CreateAccountOutputModel(user, userSession.getToken());
		session.close();
		return out;
	}

}

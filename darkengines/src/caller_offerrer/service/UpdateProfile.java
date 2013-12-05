package caller_offerrer.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import caller_offerrer.Offerrer;
import caller_offerrer.Profile;
import caller_offerrer.User;
import caller_offerrer.model.UpdateProfileInputModel;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class UpdateProfile extends JSonService<UpdateProfileInputModel, Object> {

	@Override
	public Class<UpdateProfileInputModel> getInputType() {
		return UpdateProfileInputModel.class;
	}

	@Override
	public Class<Object> getOutputType() {
		return Object.class;
	}

	@Override
	public Object processJsonRequest(UpdateProfileInputModel data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		Transaction transaction = session.beginTransaction(); 
		Offerrer user = (Offerrer)User.getUserByToken(data.getToken(), session);
		if (user == null) {
			session.close();
			throw new Exception("token.invalid");
		}
		Profile profile = user.getOffer().getProfile();
		profile = data.mergeProfile(profile);
		user.getOffer().setProfile(profile);
		session.saveOrUpdate(user);
		session.flush();
		transaction.commit();
		session.close();
		return null;
	}

}

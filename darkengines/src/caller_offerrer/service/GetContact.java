package caller_offerrer.service;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import caller_offerrer.User;
import caller_offerrer.model.GetContactInputModel;
import caller_offerrer.model.GetContactOutputModel;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class GetContact extends JSonService<GetContactInputModel, GetContactOutputModel> {

	@Override
	public Class<GetContactInputModel> getInputType() {
		return GetContactInputModel.class;
	}

	@Override
	public Class<GetContactOutputModel> getOutputType() {
		return GetContactOutputModel.class;
	}

	@Override
	public GetContactOutputModel processJsonRequest(GetContactInputModel data)
			throws Exception {
		Session session = DBSessionFactory.GetSession();
		User user = (User)session.createCriteria(User.class).add(Restrictions.eq("id", data.getUserId())).uniqueResult();
		if (user == null) {
			throw new Exception("userId.invalid");
		}
		GetContactOutputModel model = new GetContactOutputModel(user);
		session.close();
		return model;
	}

}

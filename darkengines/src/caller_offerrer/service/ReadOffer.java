package caller_offerrer.service;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import caller_offerrer.Caller;
import caller_offerrer.Offerrer;
import caller_offerrer.User;
import caller_offerrer.model.ReadOfferInputModel;
import caller_offerrer.model.ReadOfferOutputModel;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class ReadOffer extends JSonService<ReadOfferInputModel, ReadOfferOutputModel> {

	@Override
	public Class<ReadOfferInputModel> getInputType() {
		return ReadOfferInputModel.class;
	}

	@Override
	public Class<ReadOfferOutputModel> getOutputType() {
		return ReadOfferOutputModel.class;
	}

	@Override
	public ReadOfferOutputModel processJsonRequest(ReadOfferInputModel data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		Offerrer user = (Offerrer)session.createCriteria(Offerrer.class).add(Restrictions.eq("id", data.getOfferrerId())).uniqueResult();
		if (user == null) {
			throw new Exception("user.id.invalid");
		}
		Boolean fillContact = false;
		if (data.getToken() != null) {
			Caller caller = (Caller)User.getUserByToken(data.getToken(), session);
			fillContact = caller != null;
		}
		ReadOfferOutputModel model = new ReadOfferOutputModel(user, fillContact);
		session.close();
		return model;
	}
	
}

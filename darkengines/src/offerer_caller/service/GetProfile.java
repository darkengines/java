package offerer_caller.service;

import offerer_caller.Offerrer;
import offerer_caller.model.GetOfferInputModel;
import offerer_caller.model.GetOfferOutputModel;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class GetProfile extends JSonService<GetOfferInputModel, GetOfferOutputModel> {

	@Override
	public Class<GetOfferInputModel> getInputType() {
		return GetOfferInputModel.class;
	}

	@Override
	public Class<GetOfferOutputModel> getOutputType() {
		return GetOfferOutputModel.class;
	}

	@Override
	public GetOfferOutputModel processJsonRequest(GetOfferInputModel data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		Offerrer user = (Offerrer)session.createCriteria(Offerrer.class).add(Restrictions.eq("id", data.getOfferrerId())).uniqueResult();
		if (user == null) {
			throw new Exception("user.id.invalid");
		}
		GetOfferOutputModel model = new GetOfferOutputModel(user);
		session.close();
		return model;
	}
	
}

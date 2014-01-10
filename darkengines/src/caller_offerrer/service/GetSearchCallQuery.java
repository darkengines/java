package caller_offerrer.service;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import caller_offerrer.Caller;
import caller_offerrer.Offerrer;
import caller_offerrer.User;
import caller_offerrer.model.GetOfferInputModel;
import caller_offerrer.model.GetOfferOutputModel;
import caller_offerrer.model.GetSearchCallQueryInputModel;
import caller_offerrer.model.GetSearchCallQueryOutputModel;
import caller_offerrer.model.GetSearchOfferQueryInputModel;
import caller_offerrer.model.GetSearchOfferQueryOutputModel;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class GetSearchCallQuery extends JSonService<GetSearchCallQueryInputModel, GetSearchCallQueryOutputModel> {

	@Override
	public Class<GetSearchCallQueryInputModel> getInputType() {
		return GetSearchCallQueryInputModel.class;
	}

	@Override
	public Class<GetSearchCallQueryOutputModel> getOutputType() {
		return GetSearchCallQueryOutputModel.class;
	}

	@Override
	public GetSearchCallQueryOutputModel processJsonRequest(GetSearchCallQueryInputModel data) throws Exception {
		
		GetSearchCallQueryOutputModel model = null;
		if (data.getToken() != null) {
			Session session = DBSessionFactory.GetSession();
			Offerrer offerrer = (Offerrer)User.getUserByToken(data.getToken(), session);
			if (offerrer != null) {
				model = new GetSearchCallQueryOutputModel(offerrer);
			}		
			session.close();
		}
		return model;
	}
	
}

package caller_offerrer.service;

import org.hibernate.Session;

import caller_offerrer.Caller;
import caller_offerrer.User;
import caller_offerrer.model.GetSearchOfferQueryInputModel;
import caller_offerrer.model.GetSearchOfferQueryOutputModel;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class GetSearchOfferQuery extends JSonService<GetSearchOfferQueryInputModel, GetSearchOfferQueryOutputModel> {

	@Override
	public Class<GetSearchOfferQueryInputModel> getInputType() {
		return GetSearchOfferQueryInputModel.class;
	}

	@Override
	public Class<GetSearchOfferQueryOutputModel> getOutputType() {
		return GetSearchOfferQueryOutputModel.class;
	}

	@Override
	public GetSearchOfferQueryOutputModel processJsonRequest(GetSearchOfferQueryInputModel data) throws Exception {
		
		GetSearchOfferQueryOutputModel model = null;
		if (data.getToken() != null) {
			Session session = DBSessionFactory.GetSession();
			Caller caller = (Caller)User.getUserByToken(data.getToken(), session);
			if (caller != null) {
				model = new GetSearchOfferQueryOutputModel(caller);
			}		
			session.close();
		}
		return model;
	}
	
}

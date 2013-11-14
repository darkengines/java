package server.service;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import server.Identity;
import server.User;
import server.model.CityModel;
import server.model.UserIdentityOutput;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

public class GetIdentity extends JSonService<Long, UserIdentityOutput> {

	@Override
	public Class<Long> getInputType() {
		return Long.class;
	}

	@Override
	public Class<UserIdentityOutput> getOutputType() {
		return UserIdentityOutput.class;
	}

	@Override
	public UserIdentityOutput processJsonRequest(Long data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		User user = (User)session.createCriteria(User.class).add(Restrictions.eq("id", data)).uniqueResult();
		if (user == null) {
			throw new Exception("user.notFound");
		}
		UserIdentityOutput model = new UserIdentityOutput();
		Identity identity = user.getIdentity();
		session.close();
		if (identity != null) {
			model.setAddress(identity.getAddress());
			model.setBirthDate(identity.getBirthDate().getTime());
			if (identity.getCity() != null) {
				model.setCity(new CityModel(identity.getCity()));
			}
			model.setFirstName(identity.getFirstName());
			model.setLastName(identity.getLastName());
		}
		return model;
	}
	
}

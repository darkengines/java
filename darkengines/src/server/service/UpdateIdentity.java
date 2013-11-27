package server.service;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import server.City;
import server.Identity;
import server.User;
import server.UserSession;
import server.model.UserIdentityInput;
import server.model.UserIdentityOutput;
import darkengines.database.DBSessionFactory;
import darkengines.serialization.SerializerFactory;
import darkengines.service.JSonService;

public class UpdateIdentity extends JSonService<UserIdentityInput, UserIdentityOutput> {

	@Override
	public Class<UserIdentityInput> getInputType() {
		return UserIdentityInput.class;
	}

	@Override
	public Class<UserIdentityOutput> getOutputType() {
		return UserIdentityOutput.class;
	}

	@Override
	public UserIdentityOutput processJsonRequest(UserIdentityInput data)
			throws Exception {
		Session session = DBSessionFactory.GetSession();
		User user = Util.getUserByToken(data.getToken());
		if (user == null) {
			throw new Exception("token.invalid");
		}
		City city = null;
		if (data.getCityId() != null) {
			city = (City)session.createCriteria(City.class).add(Restrictions.eq("id", data.getCityId())).uniqueResult();
			if (city == null) {
				throw new Exception("cityId.invalid");
			}
		}
		Identity userIdentity = user.getIdentity();
		if (userIdentity == null) {
			userIdentity = new Identity();
		}
		userIdentity.setAddress(data.getAddress());
		userIdentity.setBirthDate(data.getBirthDate());
		userIdentity.setCity(city);
		userIdentity.setFirstName(data.getFirstName());
		userIdentity.setLastName(data.getLastName());
		userIdentity.setPhone(data.getPhone());
		userIdentity.setUser(user);
		user.setIdentity(userIdentity);
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(userIdentity);
		session.saveOrUpdate(user);
		session.flush();
		transaction.commit();
		session.close();
		
		return null;
	}

}

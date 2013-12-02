package server.service;

import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import server.Identity;
import server.Profile;
import server.User;
import server.UserType;
import server.model.CityModel;
import server.model.ListValueModel;
import server.model.ListValuesModel;
import server.model.ProfileModel;
import server.model.ProfileOutput;
import server.model.UserIdentityOutput;
import darkengines.database.DBSessionFactory;
import darkengines.database.ListItem;
import darkengines.service.JSonService;

public class GetProfile extends JSonService<Long, ProfileModel> {

	@Override
	public Class<Long> getInputType() {
		return Long.class;
	}

	@Override
	public Class<ProfileModel> getOutputType() {
		return ProfileModel.class;
	}

	@Override
	public ProfileModel processJsonRequest(Long data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		User user = (User)session.createCriteria(User.class).add(Restrictions.eq("id", data)).uniqueResult();
		if (user == null) {
			throw new Exception("user.id.invalid");
		}
		if (user.getType() != UserType.Dev) {
			throw new Exception("user.type.invalid");
		}
		ProfileModel model = new ProfileModel(user);
		session.close();
		return model;
	}
	
}

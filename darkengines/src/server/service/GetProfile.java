package server.service;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import server.Identity;
import server.Profile;
import server.User;
import server.UserType;
import server.model.CityModel;
import server.model.ListValueModel;
import server.model.ListValuesModel;
import server.model.ProfileOutput;
import server.model.UserIdentityOutput;
import darkengines.database.DBSessionFactory;
import darkengines.database.ListItem;
import darkengines.service.JSonService;

public class GetProfile extends JSonService<Long, ProfileOutput> {

	@Override
	public Class<Long> getInputType() {
		return Long.class;
	}

	@Override
	public Class<ProfileOutput> getOutputType() {
		return ProfileOutput.class;
	}

	@Override
	public ProfileOutput processJsonRequest(Long data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		User user = (User)session.createCriteria(User.class).add(Restrictions.eq("id", data)).uniqueResult();
		if (user == null) {
			throw new Exception("user.id.invalid");
		}
		if (user.getType() != UserType.Dev) {
			throw new Exception("user.type.invalid");
		}
		Profile profile = user.getProfile();
		session.close();
		if (profile != null) {
			ProfileOutput model = new ProfileOutput();
			if (profile.getDiploma() != null) {
				model.setDiploma(new ListValueModel(profile.getDiploma()));
			}
			model.setSeniority(profile.getSeniority());
			model.setProgrammingLanguage(new ListValuesModel(profile.getProgrammingLanguages()).getItems());
			model.setFrameworks(new ListValuesModel(profile.getFrameworks()).getItems());
			model.setLanguages(new ListValuesModel(profile.getLanguages()).getItems());
			return model;
		} else {
			return null;
		}
	}
	
}

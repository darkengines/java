package server.service;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.PropertiesSubqueryExpression;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import server.Diploma;
import server.Framework;
import server.Language;
import server.Profile;
import server.ProgrammingLanguage;
import server.User;
import server.UserType;
import server.model.ProfileInput;
import server.model.ProfileOutput;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class SearchDev extends JSonService<ProfileInput, Set> {

	@Override
	public Class<ProfileInput> getInputType() {
		return ProfileInput.class;
	}

	@Override
	public Class<Set> getOutputType() {
		return Set.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ProfileOutput> processJsonRequest(ProfileInput data) throws Exception {
		User user = Util.getUserByToken(data.getToken());
		if (user == null) {
			throw new Exception("token.invalid");
		}
		if (user.getType() != UserType.Dev) {
			throw new Exception("user.type.invalid");
		}
//		Profile profile = user.getProfile();
//		if (profile == null) {
//			profile = new Profile();
//		}
		Session session = DBSessionFactory.GetSession();
		Criteria criteria = session.createCriteria(Profile.class);
		if (data.getProgrammingLanguageIds() != null) {
			criteria.createAlias("ProgrammingLanguages", "pl")
			.createAlias("ProgramingLanguages", "ProgrammingLanguage")
			.add(Restrictions.in("ProgrammingLanguage.Id", data.getProgrammingLanguageIds()));
//			List<ProgrammingLanguage> programmingLanguages = session.createCriteria(ProgrammingLanguage.class)
//					.add(Restrictions.in("id", data.getProgrammingLanguageIds()))
//					.list();
//			profile.getProgrammingLanguages().clear();
//			profile.getProgrammingLanguages().addAll(programmingLanguages);
		}
		if (data.getFrameworkIds() != null) {
//			List<Framework> frameworks = session.createCriteria(Framework.class)
//					.add(Restrictions.in("id", data.getFrameworkIds()))
//					.list();
//			profile.getFrameworks().clear();
//			profile.getFrameworks().addAll(frameworks);
		}
		if (data.getLanguageIds() != null) {
//			List<Language> languages = session.createCriteria(Language.class)
//					.add(Restrictions.in("id", data.getLanguageIds()))
//					.list();
//			profile.getLanguages().clear();
//			profile.getLanguages().addAll(languages);
		}
		if (data.getDiplomaId() != null) {
//			Diploma diploma = (Diploma)session.createCriteria(Diploma.class).add(Restrictions.eq("id", data.getDiplomaId())).uniqueResult();
//			profile.setDiploma(diploma);
		}
//		profile.setSeniority(data.getSeniority());
//		profile.setUser(user);
		
		Transaction transaction = session.beginTransaction();
//		session.saveOrUpdate(profile);
//		user.setProfile(profile);
		session.saveOrUpdate(user);
		session.flush();
		transaction.commit();
		session.close();
		
		return null;
	}

}

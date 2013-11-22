package server.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.PropertiesSubqueryExpression;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import server.Diploma;
import server.Framework;
import server.Language;
import server.Profile;
import server.ProgrammingLanguage;
import server.User;
import server.UserType;
import server.model.ListValueModel;
import server.model.ListValuesModel;
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
		Criteria criteria = session.createCriteria(User.class, "user");
		
		if (data.getProgrammingLanguageIds() != null) {
			DetachedCriteria sub = DetachedCriteria.forClass(Profile.class, "profile")
				.createAlias("profile.programmingLanguages", "programmingLanguage")
				.add(Restrictions.in("programmingLanguage.id", data.getProgrammingLanguageIds()))
				.setProjection(Projections.rowCount())
				.add(Property.forName("profile.id").eqProperty("user.profile.id"));
			criteria.add(Subqueries.eq(new Long(data.getProgrammingLanguageIds().size()), sub));
//			List<ProgrammingLanguage> programmingLanguages = session.createCriteria(ProgrammingLanguage.class)
//					.add(Restrictions.in("id", data.getProgrammingLanguageIds()))
//					.list();
//			profile.getProgrammingLanguages().clear();
//			profile.getProgrammingLanguages().addAll(programmingLanguages);
		}
		if (data.getFrameworkIds() != null) {
			DetachedCriteria sub = DetachedCriteria.forClass(Profile.class, "profile")
				.createAlias("profile.frameworks", "framework")
				.add(Restrictions.in("framework.id", data.getFrameworkIds()))
				.setProjection(Projections.rowCount())
				.add(Property.forName("profile.id").eqProperty("user.profile.id"));
			criteria.add(Subqueries.eq(new Long(data.getFrameworkIds().size()), sub));
//			List<Framework> frameworks = session.createCriteria(Framework.class)
//					.add(Restrictions.in("id", data.getFrameworkIds()))
//					.list();
//			profile.getFrameworks().clear();
//			profile.getFrameworks().addAll(frameworks);
		}
		if (data.getLanguageIds() != null) {
			DetachedCriteria sub = DetachedCriteria.forClass(Profile.class, "profile")
				.createAlias("profile.languages", "language")
				.add(Restrictions.in("language.id", data.getLanguageIds()))
				.setProjection(Projections.rowCount())
				.add(Property.forName("profile.id").eqProperty("user.profile.id"));
			criteria.add(Subqueries.eq(new Long(data.getLanguageIds().size()), sub));
//			List<Language> languages = session.createCriteria(Language.class)
//					.add(Restrictions.in("id", data.getLanguageIds()))
//					.list();
//			profile.getLanguages().clear();
//			profile.getLanguages().addAll(languages);
		}
		if (data.getDiplomaId() != null) {
			criteria.add(Restrictions.ge("user.diploma.id", data.getDiplomaId()));
//			Diploma diploma = (Diploma)session.createCriteria(Diploma.class).add(Restrictions.eq("id", data.getDiplomaId())).uniqueResult();
//			profile.setDiploma(diploma);
		}
		if (data.getSeniority() != null) {
			criteria.add(Restrictions.ge("user.seniority", data.getSeniority()));
		}
//		profile.setSeniority(data.getSeniority());
//		profile.setUser(user);
		
//		Transaction transaction = session.beginTransaction();
//		session.saveOrUpdate(profile);
//		user.setProfile(profile);
//		session.saveOrUpdate(user);
//		session.flush();
//		transaction.commit();
		ArrayList<User> users = (ArrayList<User>)criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		Set<ProfileOutput> model = new HashSet<ProfileOutput>();
		for (User u: users) {
			Profile profile = u.getProfile();
			ProfileOutput profileModel = new ProfileOutput();
			profileModel.setProgrammingLanguage(new ListValuesModel(profile.getProgrammingLanguages()).getItems());
			profileModel.setFrameworks(new ListValuesModel(profile.getFrameworks()).getItems());
			profileModel.setLanguages(new ListValuesModel(profile.getLanguages()).getItems());
			if (profile.getDiploma() != null) {
				profileModel.setDiploma(new ListValueModel(profile.getDiploma()));
			}
			profileModel.setSeniority(profile.getSeniority());
			model.add(profileModel);
		}
		session.close();
		
		return model;
	}

}

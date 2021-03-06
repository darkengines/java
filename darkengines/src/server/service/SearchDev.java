package server.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import server.Profile;
import server.User;
import server.UserType;
import server.model.ProfileInput;
import server.model.ProfileModel;
import server.model.SearchInput;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class SearchDev extends JSonService<SearchInput, Set> {

	@Override
	public Class<SearchInput> getInputType() {
		return SearchInput.class;
	}

	@Override
	public Class<Set> getOutputType() {
		return Set.class;
	}
	private boolean isListNullOrEmpty(List list) {
		return list == null || list.size() == 0;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Set<ProfileModel> processJsonRequest(SearchInput data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		if (data != null && data.getToken() != null) {
			User user = Util.getUserByToken(data.getToken(), session);
			if (user == null) {
				session.close();
				throw new Exception("token.invalid");
			}
			if (user.getType() != UserType.Dev) {
				session.close();
				throw new Exception("user.type.invalid");
			}
		}
//		Profile profile = user.getProfile();
//		if (profile == null) {
//			profile = new Profile();
//		}
		Criteria criteria = session.createCriteria(User.class, "user");
		DetachedCriteria subQuery = DetachedCriteria.forClass(Profile.class, "profile")
			.add(Property.forName("profile.id").eqProperty("user.profile.id"))
			.setProjection(Projections.property("profile.id"));
		
		if (!isListNullOrEmpty(data.getProgrammingLanguageIds())) {
			DetachedCriteria sub = DetachedCriteria.forClass(Profile.class, "profile")
				.createAlias("profile.programmingLanguages", "programmingLanguage")
				.add(Restrictions.in("programmingLanguage.id", data.getProgrammingLanguageIds()))
				.add(Property.forName("profile.id").eqProperty("user.profile.id"))
				.setProjection(Projections.rowCount());
			criteria.add(Subqueries.eq(new Long(data.getProgrammingLanguageIds().size()), sub));
//			List<ProgrammingLanguage> programmingLanguages = session.createCriteria(ProgrammingLanguage.class)
//					.add(Restrictions.in("id", data.getProgrammingLanguageIds()))
//					.list();
//			profile.getProgrammingLanguages().clear();
//			profile.getProgrammingLanguages().addAll(programmingLanguages);
		}
		if (!isListNullOrEmpty(data.getFrameworkIds())) {
			DetachedCriteria sub = DetachedCriteria.forClass(Profile.class, "profile")
				.createAlias("profile.frameworks", "framework")
				.add(Restrictions.in("framework.id", data.getFrameworkIds()))
				.add(Property.forName("profile.id").eqProperty("user.profile.id"))
				.setProjection(Projections.rowCount());
			criteria.add(Subqueries.eq(new Long(data.getFrameworkIds().size()), sub));
//			List<Framework> frameworks = session.createCriteria(Framework.class)
//					.add(Restrictions.in("id", data.getFrameworkIds()))
//					.list();
//			profile.getFrameworks().clear();
//			profile.getFrameworks().addAll(frameworks);
		}
		if (!isListNullOrEmpty(data.getLanguageIds())) {
			DetachedCriteria sub = DetachedCriteria.forClass(Profile.class, "profile")
				.createAlias("profile.languages", "language")
				.add(Restrictions.in("language.id", data.getLanguageIds()))
				.add(Property.forName("profile.id").eqProperty("user.profile.id"))
				.setProjection(Projections.rowCount());
			criteria.add(Subqueries.eq(new Long(data.getLanguageIds().size()), sub));
//			List<Language> languages = session.createCriteria(Language.class)
//					.add(Restrictions.in("id", data.getLanguageIds()))
//					.list();
//			profile.getLanguages().clear();
//			profile.getLanguages().addAll(languages);
		}
		if (data.getDiploma() != null) {
			subQuery.add(Restrictions.ge("diploma", data.getDiploma()));
//			Diploma diploma = (Diploma)session.createCriteria(Diploma.class).add(Restrictions.eq("id", data.getDiplomaId())).uniqueResult();
//			profile.setDiploma(diploma);
		}
		if (data.getSeniority() != null) {
			subQuery.add(Restrictions.ge("seniority", data.getSeniority()));
		}
//		profile.setSeniority(data.getSeniority());
//		profile.setUser(user);
		
//		Transaction transaction = session.beginTransaction();
//		session.saveOrUpdate(profile);
//		user.setProfile(profile);
//		session.saveOrUpdate(user);
//		session.flush();
//		transaction.commit();
		
		criteria.add(Subqueries.exists(subQuery));
		ArrayList<User> users = (ArrayList<User>)criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		Set<ProfileModel> model = new HashSet<ProfileModel>();
		for (User u: users) {
			model.add(new ProfileModel(u));
		}
		session.close();
		
		return model;
	}

}

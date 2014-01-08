package caller_offerrer.service;

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

import caller_offerrer.Caller;
import caller_offerrer.Offerrer;
import caller_offerrer.Profile;
import caller_offerrer.User;
import caller_offerrer.model.SearchOfferInputModel;
import caller_offerrer.model.SearchOfferOutputModel;

import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class SearchOffer extends JSonService<SearchOfferInputModel, Set> {

	@Override
	public Class<SearchOfferInputModel> getInputType() {
		return SearchOfferInputModel.class;
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
	public Set<SearchOfferOutputModel> processJsonRequest(SearchOfferInputModel data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		
		Criteria criteria = session.createCriteria(Offerrer.class, "user");
		DetachedCriteria subQuery = DetachedCriteria.forClass(Profile.class, "profile")
			.add(Property.forName("profile.id").eqProperty("user.offer.profile.id"))
			.setProjection(Projections.property("profile.id"));

			DetachedCriteria sub = DetachedCriteria.forClass(Profile.class, "profile")
				.createAlias("profile.programmingLanguages", "programmingLanguage")
				.add(Restrictions.in("programmingLanguage.id", data.getProgrammingLanguageIds()))
				.add(Property.forName("profile.id").eqProperty("user.profile.id"))
				.setProjection(Projections.rowCount());
			criteria.add(Subqueries.eq(new Long(data.getProgrammingLanguageIds().size()), sub));

			sub = DetachedCriteria.forClass(Profile.class, "profile")
				.createAlias("profile.frameworks", "framework")
				.add(Restrictions.in("framework.id", data.getFrameworkIds()))
				.add(Property.forName("profile.id").eqProperty("user.profile.id"))
				.setProjection(Projections.rowCount());
			criteria.add(Subqueries.eq(new Long(data.getFrameworkIds().size()), sub));

			sub = DetachedCriteria.forClass(Profile.class, "profile")
				.createAlias("profile.languages", "language")
				.add(Restrictions.in("language.id", data.getLanguageIds()))
				.add(Property.forName("profile.id").eqProperty("user.profile.id"))
				.setProjection(Projections.rowCount());
			criteria.add(Subqueries.eq(new Long(data.getLanguageIds().size()), sub));

		if (data.getDiploma() != null) {
			subQuery.add(Restrictions.ge("diploma", data.getDiploma()));
		}
		if (data.getSeniority() != null) {
			subQuery.add(Restrictions.ge("seniority", data.getSeniority()));
		}
		
		criteria.add(Subqueries.exists(subQuery));
		ArrayList<Offerrer> users = (ArrayList<Offerrer>)criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		Set<SearchOfferOutputModel> model = new HashSet<SearchOfferOutputModel>();
		for (Offerrer u: users) {
			model.add(new SearchOfferOutputModel(u));
		}
		if (data.getToken() != null) {
			Caller caller = (Caller)User.getUserByToken(data.getToken(), session);
			if (caller != null) {
				caller.setSearchOfferrerQuery(data.mergeQuery(caller.getSearchOfferrerQuery(), session));
			}
		}
		session.close();
		
		return model;
	}

}

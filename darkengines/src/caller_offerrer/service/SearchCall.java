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

import caller_offerrer.Call;
import caller_offerrer.Caller;
import caller_offerrer.Offerrer;
import caller_offerrer.User;
import caller_offerrer.model.SearchCallInputModel;
import caller_offerrer.model.SearchCallOutputModel;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings({ "serial", "rawtypes" })
public class SearchCall extends JSonService<SearchCallInputModel, Set> {

	@Override
	public Class<SearchCallInputModel> getInputType() {
		return SearchCallInputModel.class;
	}

	@Override
	public Class<Set> getOutputType() {
		return Set.class;
	}
	@SuppressWarnings("unused")
	private boolean isListNullOrEmpty(List list) {
		return list == null || list.size() == 0;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Set<SearchCallOutputModel> processJsonRequest(SearchCallInputModel data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		
		Criteria criteria = session.createCriteria(Call.class, "callMain");
	    if (data.getProgrammingLanguageIds() != null && data.getProgrammingLanguageIds().size() > 0) {
			DetachedCriteria sub = DetachedCriteria.forClass(Call.class, "call")
				.createAlias("call.programmingLanguages", "programmingLanguage")
				.add(Restrictions.in("programmingLanguage.id", data.getProgrammingLanguageIds()))
				.add(Property.forName("call.id").eqProperty("callMain.id"))
				.setProjection(Projections.rowCount());
			criteria.add(Subqueries.lt(0l, sub));
	    }
	    if (data.getFrameworkIds() != null && data.getFrameworkIds().size() > 0) {
			DetachedCriteria sub = DetachedCriteria.forClass(Call.class, "call")
				.createAlias("call.frameworks", "framework")
				.add(Restrictions.in("framework.id", data.getFrameworkIds()))
				.add(Property.forName("call.id").eqProperty("callMain.id"))
				.setProjection(Projections.rowCount());
			criteria.add(Subqueries.lt(0l, sub));
	    }
		if (data.getLanguageIds() != null && data.getLanguageIds().size() > 0) {
			DetachedCriteria sub = DetachedCriteria.forClass(Call.class, "call")
				.createAlias("call.languages", "language")
				.add(Restrictions.in("language.id", data.getLanguageIds()))
				.add(Property.forName("call.id").eqProperty("callMain.id"))
				.setProjection(Projections.rowCount());
			criteria.add(Subqueries.lt(0l, sub));
		}
		if (data.getDiploma() != null) {
			criteria.add(Restrictions.le("diploma", data.getDiploma()));
		}
		if (data.getSeniority() != null) {
			criteria.add(Restrictions.le("seniority", data.getSeniority()));
		}
		
		ArrayList<Call> calls = (ArrayList<Call>)criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		Set<SearchCallOutputModel> model = new HashSet<SearchCallOutputModel>();
		for (Call call: calls) {
			Caller caller = (Caller)session.createCriteria(Caller.class, "caller")
			.createAlias("caller.calls", "call")
			.add(Restrictions.eq("call.id", call.getId()))
			.uniqueResult();
			model.add(new SearchCallOutputModel(call, caller.getId()));
		}
		if (data.getToken() != null) {
			Offerrer offerrer = (Offerrer)User.getUserByToken(data.getToken(), session);
			if (offerrer != null) {
				offerrer.setSearchCallQuery(data.mergeQuery(offerrer.getSearchCallQuery(), session));
				session.beginTransaction();
				session.saveOrUpdate(offerrer.getSearchCallQuery());
				session.saveOrUpdate(offerrer);
				session.flush();
				session.getTransaction().commit();
			}
		}
		session.close();
		
		return model;
	}

}

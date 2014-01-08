package caller_offerrer.model;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import caller_offerrer.Framework;
import caller_offerrer.Language;
import caller_offerrer.ProgrammingLanguage;
import caller_offerrer.SearchOfferrerQuery;

public class SearchOfferInputModel {
	private String token;
	private Set<Long> programmingLanguageIds;
	private Set<Long> frameworkIds;
	private Set<Long> languageIds;
	private Integer diploma;
	private Integer seniority;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Set<Long> getProgrammingLanguageIds() {
		return programmingLanguageIds;
	}
	public void setProgrammingLanguageIds(Set<Long> programmingLanguageIds) {
		this.programmingLanguageIds = programmingLanguageIds;
	}
	public Set<Long> getFrameworkIds() {
		return frameworkIds;
	}
	public void setFrameworkIds(Set<Long> frameworkIds) {
		this.frameworkIds = frameworkIds;
	}
	public Set<Long> getLanguageIds() {
		return languageIds;
	}
	public void setLanguageIds(Set<Long> languageIds) {
		this.languageIds = languageIds;
	}
	public Integer getDiploma() {
		return diploma;
	}
	public void setDiploma(Integer diplomaId) {
		this.diploma = diplomaId;
	}
	public Integer getSeniority() {
		return seniority;
	}
	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}
	@SuppressWarnings("unchecked")
	public SearchOfferrerQuery mergeQuery(SearchOfferrerQuery query, Session session) throws IOException {
		if (programmingLanguageIds != null) {
			query.getProgrammingLanguages().clear();
			if (programmingLanguageIds.size() > 0) {
				Collection<ProgrammingLanguage> programmingLanguages = session.createCriteria(ProgrammingLanguage.class)
						.add(Restrictions.in("id", programmingLanguageIds))
						.list();
				query.getProgrammingLanguages().addAll((Collection)programmingLanguages);
			}
		}
		if (frameworkIds != null) {
			query.getFrameworks().clear();
			if (frameworkIds.size() > 0) {
				Set<Framework> frameworks = new HashSet<Framework>(session.createCriteria(Framework.class)
						.add(Restrictions.in("id", frameworkIds))
						.list());
				query.getFrameworks().addAll((Collection)frameworks);
			}
		}
		if (languageIds != null) {
			query.getLanguages().clear();
			if (languageIds.size() > 0) {
				Set<Language> languages = new HashSet<Language>(session.createCriteria(Language.class)
						.add(Restrictions.in("id", languageIds))
						.list());
				query.getLanguages().addAll((Collection)languages);
			}
		}
		query.setDiploma(diploma);
		query.setSeniority(seniority);
		return query;
	}
}

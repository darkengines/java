package caller_offerrer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import caller_offerrer.Language;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings({ "rawtypes", "serial" })
public class Languages extends JSonService<String, Map> {

	@Override
	public Class<String> getInputType() {
		return String.class;
	}

	@Override
	public Class<Map> getOutputType() {
		return Map.class;
	}

	@Override
	public Map<Long, String> processJsonRequest(String data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		@SuppressWarnings("unchecked")
		List<Language> languages = session.createCriteria(Language.class)
			.add(Restrictions.like("name", String.format("%%%s%%", data)))
			.list();
		Map<Long, String> result = new HashMap<Long, String>();
		for (Language language: languages) {
			result.put(language.getId(), language.getName());
		}
		session.close();
		return result;
	}

}

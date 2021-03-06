package server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import server.City;
import server.PostalCode;
import server.ProgrammingLanguage;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

public class ProgrammingLanguages extends JSonService<String, Map> {

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
		List<ProgrammingLanguage> programmingLanguages = session.createCriteria(ProgrammingLanguage.class)
			.add(Restrictions.like("name", String.format("%%%s%%", data)))
			.addOrder(Order.desc("name"))
			.list();
		Map<Long, String> result = new HashMap<Long, String>();
		for (ProgrammingLanguage programmingLanguage: programmingLanguages) {
			result.put(programmingLanguage.getId(), programmingLanguage.getName());
		}
		session.close();
		return result;
	}

}

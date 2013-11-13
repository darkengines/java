package server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import server.City;
import server.PostalCode;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

public class Cities extends JSonService<String, Map> {

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
		List<City> cities = session.createCriteria(City.class)
			.add(Restrictions.like("name", String.format("%%%s%%", data)))
			.list();
		Map<Long, String> result = new HashMap<Long, String>();
		for (City city: cities) {
			result.put(city.getId(), city.getName());
		}
		session.close();
		return result;
	}

}

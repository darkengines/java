package server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import server.Diploma;
import server.PostalCode;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

public class Diplomas extends JSonService<Object, Map> {

	@Override
	public Class<Object> getInputType() {
		return null;
	}

	@Override
	public Class<Map> getOutputType() {
		return Map.class;
	}

	@Override
	public Map<Long, String> processJsonRequest(Object data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		List<Diploma> diplomas = session.createCriteria(Diploma.class).list();
		Map<Long, String> result = new HashMap<Long, String>();
		for (Diploma diploma: diplomas) {
			result.put(diploma.getId(), diploma.getName());
		}
		session.close();
		return result;
	}

}

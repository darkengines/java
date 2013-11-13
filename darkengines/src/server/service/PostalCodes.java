package server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import server.PostalCode;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

public class PostalCodes extends JSonService<Object, Map> {

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
		List<PostalCode> postalCodes = session.createCriteria(PostalCode.class).list();
		Map<Long, String> result = new HashMap<Long, String>();
		for (PostalCode postalCode: postalCodes) {
			result.put(postalCode.getId(), postalCode.getCode());
		}
		session.close();
		return result;
	}

}

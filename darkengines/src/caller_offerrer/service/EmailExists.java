package caller_offerrer.service;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import caller_offerrer.User;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class EmailExists extends JSonService<String, Boolean> {
	@Override
	public Class<String> getInputType() {
		return String.class;
	}
	@Override
	public Class<Boolean> getOutputType() {
		return Boolean.class;
	}
	@Override
	public Boolean processJsonRequest(String data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		Boolean userExists = session.createCriteria(User.class).add(Restrictions.eq("email", data)).uniqueResult() != null;
		return userExists;
	}
}

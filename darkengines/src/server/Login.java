package server;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import darkengines.database.DBSessionFactory;
import darkengines.serialization.SerializerFactory;
import darkengines.service.JSonService;

public class Login extends JSonService {
	protected LoginQueryValidator validator;
	
	public Login() {
		validator = new LoginQueryValidator();
	}
	
	@Override
	public void processRequest(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> parameters = request.getParameterMap();
		Map<String, String> errors = validator.validate(parameters).asMap();
		if (errors.isEmpty()) {
			try {
				Session session = DBSessionFactory.GetSession();
				User user = (User)session.createCriteria(User.class)
						.add(Restrictions.eq("email", parameters.get("email")[0]))
						.add(Restrictions.eq("password", User.hashPassword(parameters.get("password")[0]))).uniqueResult();
				UserSession userSession = new UserSession();
				userSession.setUser(user);
				userSession.setOpenedOn(new Date());
				userSession.setLength(0);
				Transaction transaction = session.beginTransaction();
				session.save(userSession);
				transaction.commit();
				session.flush();
				session.close();
				response.getWriter().write(SerializerFactory.getSerializer().toJson(userSession));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		} else {
			((HttpServletResponse)response).setStatus(500);
			response.getWriter().write(SerializerFactory.getSerializer().toJson(errors));
		}
	}

}

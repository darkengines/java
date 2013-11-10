package server;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import darkengines.database.DBSessionFactory;
import darkengines.serialization.SerializerFactory;
import darkengines.service.JSonService;

public class CreateAccount extends JSonService {
	
	protected CreateAccountQueryValidator validator;
	
	public CreateAccount() {
		super();
		validator = new CreateAccountQueryValidator();
	}
	
	@Override
	public void processRequest(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> params = request.getParameterMap();
		QueryValidatorResult result = validator.validate(params);
		if (result.isEmpty()) {
			User user = new User();
			user.setEmail(params.get("email")[0]);
			try {
				user.setPassword(User.hashPassword(params.get("password")[0]));
			} catch (NoSuchAlgorithmException e) {
				((HttpServletResponse)response).setStatus(500);
			}
			user.setType(UserType.valueOf(params.get("type")[0]));
			Session session = DBSessionFactory.GetSession();
			Transaction transaction = session.beginTransaction();
			session.save(user);
			session.flush();
			transaction.commit();
			session.close();
			response.getWriter().flush();
		} else {
			((HttpServletResponse)response).setStatus(500);
			response.getWriter().write(SerializerFactory.getSerializer().toJson(result.asMap()));
		}
	}
	
}

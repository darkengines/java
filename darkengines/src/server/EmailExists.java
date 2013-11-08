package server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import darkengines.database.DBSessionFactory;
import darkengines.serialization.SerializerFactory;
import darkengines.service.JSonService;

public class EmailExists extends JSonService {
	private EmailExistsQueryValidator validator;
	public EmailExists() {
		validator = new EmailExistsQueryValidator();
	}
	@Override
	public void processRequest(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> params = request.getParameterMap();
		Map<String, String> result = validator.validate(params).asMap();
		String answer = null;
		if (result.isEmpty()) {
			String email = params.get("email")[0];
			User user = (User)DBSessionFactory.GetSession().createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
			answer = SerializerFactory.getSerializer().toJson(user != null);
		} else {
			((HttpServletResponse)response).setStatus(500);
			answer = SerializerFactory.getSerializer().toJson(result);
		}
		response.getWriter().write(answer);
	}
}

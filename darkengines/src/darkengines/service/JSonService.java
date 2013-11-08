package darkengines.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class JSonService extends Service {

	@Override
	public final void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			processRequest(request, response);
	}
}

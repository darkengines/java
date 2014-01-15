package darkengines.service;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public abstract class Service extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		processRequest(request, response);
	}
	public abstract void processRequest(ServletRequest request, ServletResponse response) throws ServletException, IOException;
}

package application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import darkengines.service.Service;

public class Index extends Service {
	@Override
	public void processRequest(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/view/Index.jsp").forward(request,response);
	}
}

package application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import darkengines.service.Service;

public class Fuck extends Service {

	@Override
	public void processRequest(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/view/FuckYou.jsp").forward(request,response);
		
	}

}

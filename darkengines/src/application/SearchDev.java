package application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import darkengines.service.Service;

public class SearchDev extends Service {

	@Override
	public void processRequest(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/view/SearchDev.jsp").forward(request,response);
	}

}

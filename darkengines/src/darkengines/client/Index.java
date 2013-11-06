package darkengines.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import darkengines.service.Service;

public class Index extends Service {
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/view/Index.jsp").forward(request,response);
	}
}

package darkengines.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import darkengines.service.Service;

public class EditDevIdentity extends Service {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/view/EditDevIdentity.jsp").forward(request,response);
	}

}

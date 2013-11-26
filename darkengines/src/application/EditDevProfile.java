package application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import darkengines.service.Service;

public class EditDevProfile extends Service {

	@Override
	public void processRequest(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		if (!Util.hasTokenCookie(((HttpServletRequest)request).getCookies())) {
			((HttpServletResponse)response).sendRedirect("login?url=edit_dev_profile");
		}
		getServletContext().getRequestDispatcher("/WEB-INF/view/EditDevProfile.jsp").forward(request,response);
		
	}

}

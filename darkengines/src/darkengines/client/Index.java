package darkengines.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.hibernate.Session;
import org.hibernate.Transaction;

import darkengines.database.DBSessionFactory;
import darkengines.service.Service;
import darkengines.user.User;

public class Index extends Service {
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		Session s = DBSessionFactory.GetSession();
		getServletContext().getRequestDispatcher("/WEB-INF/view/Index.jsp").forward(request,response);
	}
}

package offerer_caller.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import offerer_caller.Image;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import darkengines.database.DBSessionFactory;
import darkengines.service.Service;

@SuppressWarnings("serial")
public class GetImage extends Service {

	@Override
	public void processRequest(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		((HttpServletResponse)response).setContentType("image/png");
		Long imageId = Long.parseLong(request.getParameter("id"));
		Session session = DBSessionFactory.GetSession();
		Image image = (Image)session.createCriteria(Image.class).add(Restrictions.eq("id", imageId));
		byte[] bytes = image.getData();
		session.close();
		response.getOutputStream().write(bytes);
		response.flushBuffer();
	}

}

package darkengines.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import darkengines.serialization.SerializerFactory;

public abstract class JSonService<IT, OT> extends Service {

	@Override
	public final void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		processRequest(request, response);
	}
	@Override
	public final void processRequest(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		Gson gson = SerializerFactory.getSerializer();
		try {
			IT input = (IT)gson.fromJson(data, getInputType());
			OT output = processJsonRequest(input);
			response.getWriter().write(gson.toJson(output));
		} catch (JsonParseException e) {
			((HttpServletResponse)response).setStatus(500);
			response.getWriter().write(e.getLocalizedMessage());
		} catch (Exception e) {
			((HttpServletResponse)response).setStatus(500);
			response.getWriter().write(e.getLocalizedMessage());
		}
	}
	public abstract Class<?> getInputType();
	public abstract Class<?> getOutputType();
	public abstract OT processJsonRequest(IT data);
}

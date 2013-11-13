package darkengines.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import darkengines.model.ModelValidator;
import darkengines.serialization.SerializerFactory;

@SuppressWarnings("serial")
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
			IT input = null;
			if (data != null) {
				input = (IT)gson.fromJson(data, getInputType());
				ModelValidator<IT> modelValidator = new ModelValidator<IT>(getInputType());
				modelValidator.validate(input);
			}
			OT output = processJsonRequest(input);
			response.getWriter().write(gson.toJson(output));
		} catch (Exception e) {
			error(e.getLocalizedMessage(), response);
		}
	}
	protected void error(String message, ServletResponse response) throws IOException {
		((HttpServletResponse)response).setStatus(500);
		response.getWriter().write(message);
	}
	public abstract Class<IT> getInputType();
	public abstract Class<OT> getOutputType();
	public abstract OT processJsonRequest(IT data) throws Exception;
}

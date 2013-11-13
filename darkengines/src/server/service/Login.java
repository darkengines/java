package server.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import server.User;
import server.UserSession;
import server.model.CreateAccountInput;
import server.model.CreateAccountOutput;
import server.model.LoginInput;
import server.model.LoginOutput;

import com.google.gson.Gson;

import darkengines.database.DBSessionFactory;
import darkengines.serialization.SerializerFactory;
import darkengines.service.JSonService;

public class Login extends JSonService<LoginInput, LoginOutput> {

	@Override
	public Class getInputType() {
		return LoginInput.class;
	}

	@Override
	public Class getOutputType() {
		return LoginOutput.class;
	}

	@Override
	public LoginOutput processJsonRequest(LoginInput data) throws Exception {
		Session session = DBSessionFactory.GetSession();
		
		User user = (User)session.createCriteria(User.class)
		.add(Restrictions.eq("email", data.getEmail()).ignoreCase())
		.add(Restrictions.eq("password", User.hashPassword(data.getPassword())))
		.uniqueResult();
		
		if (user == null) {
			throw new Exception("credential.bad");
		}
		
		UserSession userSession = new UserSession();
		userSession.setUser(user);
		userSession.setLength(0);
		userSession.setOpenedOn(new Date());
		
		Transaction transaction = session.beginTransaction();
		session.save(userSession);
		session.flush();
		transaction.commit();
		session.close();
		
		LoginOutput output = new LoginOutput();
		output.setSessionId(userSession.getId());
		
		return output;
	}

}

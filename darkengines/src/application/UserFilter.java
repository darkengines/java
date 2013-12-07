package application;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import darkengines.serialization.SerializerFactory;

/**
 * Servlet Filter implementation class Listener
 */
public class UserFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Cookie[] cookies = ((HttpServletRequest)request).getCookies();
		Cookie cookie = null;
		Boolean found = false;
		int i = cookies.length;
		while (!found && i-- > 0) {
			cookie = cookies[i];
			found = cookie.getName().equals("userInfo");
		}
		if (found) {
			UserInfo userInfo = SerializerFactory.getSerializer().fromJson(URLDecoder.decode(cookie.getValue(), "UTF-8"), UserInfo.class);
			if (userInfo.getToken() != null) {
				request.setAttribute("token", userInfo.getToken());
				request.setAttribute("userId", userInfo.getUserId());
			} else {
				((HttpServletResponse)response).sendRedirect(String.format("login?url=%s", ((HttpServletRequest)request).getRequestURI()));
			}
		} else {
			((HttpServletResponse)response).sendRedirect(String.format("login?url=%s", ((HttpServletRequest)request).getRequestURI()));
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

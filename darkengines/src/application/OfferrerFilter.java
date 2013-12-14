package application;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import caller_offerrer.UserType;

/**
 * Servlet Filter implementation class OfferrerFilter
 */
@WebFilter("/OfferrerFilter")
public class OfferrerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public OfferrerFilter() {
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
		HttpServletRequest r = (HttpServletRequest)request;
		if (r.getAttribute("userType") != UserType.Offerrer) {
			((HttpServletResponse)response).sendRedirect(String.format("login?url=%s", ((HttpServletRequest)request).getRequestURI()));
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

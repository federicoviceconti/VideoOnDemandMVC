package com.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(filterName="myFilter", urlPatterns={"*.jsp", "/AddServlet", "/ListServlet"}, dispatcherTypes= {DispatcherType.REQUEST})
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
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
		HttpServletRequest toFilter = (HttpServletRequest)request;

		if(!toFilter.getServletPath().equals("/LoginServlet")
				&& !toFilter.getServletPath().equals("/login.jsp")
				&& toFilter.getSession().getAttribute("user") == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		} else if(
				(toFilter.getServletPath().equals("/LoginServlet")
						|| toFilter.getServletPath().equals("/login.jsp"))
				&& toFilter.getSession().getAttribute("user") != null) {
			request.getRequestDispatcher("home.jsp").forward(request, response);
			
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

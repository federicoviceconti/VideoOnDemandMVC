package com.videoondemand.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletUtils {

	public static void goToPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		request.getRequestDispatcher(page).forward(request, response);
	}
	
	public static void sessionUserExists(
			HttpSession session, HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException{
		if(session.getAttribute("user") == null) {
			page = "login.jsp";
		}

		if(!page.isEmpty()) {
			goToPage(request, response, page);
		}
	}
	
	public static String getCookieValue(HttpServletRequest request, String param) {
		if(request.getCookies() != null) {
			for(Cookie cookie: request.getCookies()) {

				if(cookie.getName().equalsIgnoreCase(param)) {					
					return cookie.getValue();
				}
			}
		}		
		
		return "";
	}
}
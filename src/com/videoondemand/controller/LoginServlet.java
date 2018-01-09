package com.videoondemand.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.Field;
import com.dao.to.UserDto;
import com.facade.FacadeFactory;
import com.facade.FacadeUser;
import com.facade.FacadeFactory.FacadeType;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("user") == null) {
			String username = Validate.checkParams(request, Field.DB.USERNAME, Field.EMPTY);
			String password = Validate.checkParams(request, Field.DB.PASSWORD, Field.EMPTY);
			System.out.println(username + password);

			if (!username.isEmpty() && !password.isEmpty()) {
				FacadeUser facadeUser = (FacadeUser) FacadeFactory.getFacade(FacadeType.USER);
				UserDto userDto = facadeUser.login(username, password);
				System.out.println(userDto);


				if (userDto != null) {
					HttpSession session = request.getSession();
					session.setAttribute("user", userDto);
				}
			}
		}
		
		ServletUtils.sessionUserExists(request.getSession(), request, response, "home.jsp");
	}
}

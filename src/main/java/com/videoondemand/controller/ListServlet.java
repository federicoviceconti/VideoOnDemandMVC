package com.videoondemand.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.Field;
import com.facade.FacadeFactory;
import com.facade.FacadeFactory.FacadeType;
import com.facade.FacadeFilm;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FacadeFilm facade = (FacadeFilm) FacadeFactory.getFacade(FacadeType.FILM);

		String type = Validate.checkParams(request, Field.TYPE, "");
		int id = 0;
		try {
			id = Integer.parseInt(Validate.checkParams(request, Field.DB.ID, Field.EMPTY));
		} catch (NumberFormatException exception) {}
		
		switch (type) {
			case Field.DELETE:
				FacadeFilm film = (FacadeFilm) FacadeFactory.getFacade(FacadeType.FILM);
				film.deleteFilm(id);
				break;
			case Field.ORDER:
				System.out.println( Validate.checkParams(request, Field.ORDER, ""));
				String[] orderArrayValue = request.getParameterValues("ordinamento");

				if(orderArrayValue != null) {
					for(String hasSaved: orderArrayValue) {
						if(hasSaved.equals("save")) {
							response.addCookie(new Cookie(Field.ORDER, Validate.checkParams(request, Field.ORDER, "")));
						}
					}
				}

				break;
		}

        //Setting film to attribute
        request.setAttribute(
                "films", facade.returnFilmInformation(Validate.checkParams(request, Field.ORDER, "asc")));
		request.setAttribute("lenghtFilms", facade.returnSizeFilm());
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

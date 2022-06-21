package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import mvcproject.persistant.dao.UserDAO;
import mvcproject.persistant.dto.UserResponseDTO;

/**
 * Servlet implementation class SearchUserController
 */
@WebServlet("/SearchUserController")
public class SearchUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchUserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		List<UserResponseDTO> list = dao.selectAll();
		request.setAttribute("userList", list);
		request.getRequestDispatcher("USR003.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserBean uBean = new UserBean();
		UserDAO dao = new UserDAO();
		String searchId = request.getParameter("searchId");
		String searchMail = request.getParameter("searchMail");

		List<UserResponseDTO> showList = new ArrayList<>();
		if (searchId.isBlank() && searchMail.isBlank()) {
			showList = dao.selectAll();
			request.setAttribute("userList", showList);

			request.getRequestDispatcher("USR003.jsp").include(request, response);
		} else {
			if (searchId.isBlank()) {
				showList.add(dao.selectMail(searchMail));
			} else if (searchMail.isBlank()) {
				showList.add(dao.selectId(searchId));
			} else {
				showList.add(dao.selectIdAndMail(searchId, searchMail));

			}

			request.setAttribute("userList", showList);
			request.getRequestDispatcher("USR003.jsp").forward(request, response);
		}

	}

}

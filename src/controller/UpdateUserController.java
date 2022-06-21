package controller;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import mvcproject.persistant.dao.UserDAO;

import mvcproject.persistant.dto.UserResponseDTO;
import mvcproject.persistant.dto.UserRequestDTO;


/**
 * Servlet implementation class UpdateUserRegisterController
 */
@WebServlet("/UpdateUserController")
public class UpdateUserController extends HttpServlet {
	private static final long serialVersionuserId = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		List<UserResponseDTO> list =  new ArrayList<>();
		list.add(dao.selectId(request.getParameter("userId")));
		request.setAttribute("userlist", list);
		request.getRequestDispatcher("USR002.jsp").forward(request, response);
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
		String userId = request.getParameter("userId");
		uBean.setUserId(userId);
		uBean.setUserMail(request.getParameter("userMail"));
		uBean.setUserPassword(request.getParameter("userPassword"));
		uBean.setUserConPassword(request.getParameter("userConPassword"));
		uBean.setUserRole(request.getParameter("userRole"));
		
		if (uBean.getUserMail().isBlank() || uBean.getUserPassword().isBlank() || uBean.getUserConPassword().isBlank()
				|| uBean.getUserRole().isBlank()) {
			request.setAttribute("errorFill", "Fill the Blank!!!");
			request.setAttribute("uBean", uBean);
			request.getRequestDispatcher("USR002.jsp").forward(request, response);
		} else {
			

			UserResponseDTO res = new UserResponseDTO();
			UserRequestDTO dto = new UserRequestDTO();
			List<UserResponseDTO> userList = new ArrayList<>();

			dto.setUserId(uBean.getUserId());
			dto.setUserMail(uBean.getUserMail());
			dto.setUserPassword(uBean.getUserPassword());
			dto.setUserRole(uBean.getUserRole());
			dao.updateUserData(dto);
			userList = dao.selectAll();
			
			request.getServletContext().setAttribute("userList", userList);
			response.sendRedirect("SearchUserController");
			
			
			
		}

	}

}

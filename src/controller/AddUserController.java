package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseBean;
import model.UserBean;
import mvcproject.persistant.dao.CourseDAO;
import mvcproject.persistant.dao.UserDAO;
import mvcproject.persistant.dto.CourseRequestDTO;
import mvcproject.persistant.dto.CourseResponseDTO;
import mvcproject.persistant.dto.UserRequestDTO;
import mvcproject.persistant.dto.UserResponseDTO;

/**
 * Servlet implementation class AddUserRegiterController
 */
@WebServlet("/AddUserRegisterController")
public class AddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AtomicInteger autoStuId = new AtomicInteger(1);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
	
		int i = dao.getId();
		String stuIdString = String.format("%03d", i );
		String finalString = "USR" + stuIdString;
		uBean.setUserId(finalString);
		uBean.setUserMail(request.getParameter("userMail"));
		uBean.setUserPassword(request.getParameter("userPassword"));
		uBean.setUserConPassword(request.getParameter("userConPassword"));
		uBean.setUserRole(request.getParameter("userRole"));
		if (uBean.getUserMail().isBlank() || uBean.getUserPassword().isBlank() || uBean.getUserConPassword().isBlank()
				|| uBean.getUserRole().isBlank()) {
			request.setAttribute("errorFill", "Fill the Blank!!!");
			request.setAttribute("uBean", uBean);
			request.getRequestDispatcher("USR001.jsp").forward(request, response);
		} else {
			UserResponseDTO res = new UserResponseDTO();
			UserRequestDTO dto = new UserRequestDTO();
			

			dto.setUserId(uBean.getUserId());
			dto.setUserMail(uBean.getUserMail());
			dto.setUserPassword(uBean.getUserPassword());
			dto.setUserRole(uBean.getUserRole());
			dao.insertUserData(dto);
			
			request.setAttribute("errorFill", "Success Add");
			request.getRequestDispatcher("USR001.jsp").forward(request, response);

		}

	}

}

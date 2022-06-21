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
import mvcproject.persistant.dao.StudentDAO;
import mvcproject.persistant.dao.UserDAO;
import mvcproject.persistant.dto.StudentRequestDTO;
import mvcproject.persistant.dto.UserRequestDTO;

/**
 * Servlet implementation class DeleteUserController
 */
@WebServlet("/DeleteUserController")
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean uBean = new UserBean();
		UserDAO dao = new UserDAO();
		UserRequestDTO dto = new UserRequestDTO();
		String userId = request.getParameter("uId");
		dto.setUserId(userId);
		dao.deleteUserData(dto);
		
			
			request.setAttribute("errorFill", "Success delete");
			response.sendRedirect("SearchUserController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
//	StudentDAO dao = new StudentDAO();
//	StudentRequestDTO dto = new StudentRequestDTO();
//	dto.setStuId(request.getParameter("stuId"));
//
//	dao.deleteStudnetData(dto);

}

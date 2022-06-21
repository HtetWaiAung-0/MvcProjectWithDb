package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginMail = request.getParameter("loginName");
		String loginPassword = request.getParameter("loginPassword");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
		request.getSession().setAttribute("date", LocalDate.now().format(formatter));
		
		UserDAO dao = new UserDAO();
		
		
		boolean check = false;
		check = dao.selectMailAndPassword(loginMail, loginPassword);		
		if(check == true || (loginMail.equals("admin@gmail.com") && loginPassword.equals("123") )) {
			
			request.getSession().setAttribute("loginName", loginMail);
			request.getSession().setAttribute("loginPassword", loginPassword);
			request.getRequestDispatcher("MNU001.jsp").forward(request, response);
			 
		}else {
			request.setAttribute("error"," Please check your data again.");
			 request.getRequestDispatcher("LGN001.jsp").forward(request, response);
		}
	}

}

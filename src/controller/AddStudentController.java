package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseBean;
import model.StudentBean;
import model.UserBean;
import mvcproject.persistant.dao.CourseStudentDAO;
import mvcproject.persistant.dao.StudentDAO;
import mvcproject.persistant.dao.UserDAO;
import mvcproject.persistant.dto.CourseStudentRequestDTO;
import mvcproject.persistant.dto.StudentRequestDTO;
import mvcproject.persistant.dto.StudentResponseDTO;
import mvcproject.persistant.dto.UserRequestDTO;
import mvcproject.persistant.dto.UserResponseDTO;

/**
 * Servlet implementation class AddStudentController
 */
@WebServlet("/AddStudentController")
public class AddStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AtomicInteger autoStuId = new AtomicInteger(1);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentController() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentBean stuBean = new StudentBean();
		StudentDAO dao = new StudentDAO();
		CourseStudentDAO csdao = new CourseStudentDAO();
		CourseStudentRequestDTO csdto = new CourseStudentRequestDTO();
		int i = dao.getId();
		String stuIdString = String.format("%03d", i );
		String finalString = "STU" + stuIdString;
	
		stuBean.setStuId(request.getParameter("stuId"));
		stuBean.setStuName(request.getParameter("stuName"));
		stuBean.setStuDob(request.getParameter("stuDob"));
		stuBean.setStuGender(request.getParameter("stuGender"));
		stuBean.setStuPhone(request.getParameter("stuPhone"));
		stuBean.setStuEducation(request.getParameter("stuEducation"));
		
		String [] attendArray = request.getParameterValues("stuAttend");
		
		
		
		if (stuBean.getStuName().isBlank() || stuBean.getStuDob().isBlank() || stuBean.getStuGender().isBlank()||stuBean.getStuPhone().isBlank() ||stuBean.getStuEducation().isBlank()) {
			request.setAttribute("errorFill","Fill the Blank!!!" );
			request.setAttribute("stuBean", stuBean);
			request.getRequestDispatcher("STU001.jsp").forward(request, response);
		}else {
			StudentResponseDTO res = new StudentResponseDTO();
			StudentRequestDTO dto = new StudentRequestDTO();
			
			for(int j = 0; j<attendArray.length;j++) {
				csdto.setStuId(stuBean.getStuId());
				csdto.setCourseName(attendArray[j]);
				csdao.insertCourseStudnetData(csdto);
			}
			dto.setStuId(stuBean.getStuId());
			dto.setStuName(stuBean.getStuName());
			dto.setStuDob(stuBean.getStuDob());
			dto.setStuGender(stuBean.getStuGender());
			dto.setStuPhone(stuBean.getStuPhone());
			dto.setStuEducation(stuBean.getStuEducation());
			dao.insertStudentData(dto);
			
			request.setAttribute("errorFill", "Success Add");			
			request.getRequestDispatcher("STU001.jsp").forward(request, response);
			
		}
		
	}
	
//	UserBean uBean = new UserBean();
//	UserDAO dao = new UserDAO();
//	int i = dao.getId();
//	String stuIdString = String.format("%03d", i + 1);
//	String finalString = "USR" + stuIdString;
//	uBean.setUserId(finalString);
//	uBean.setUserMail(request.getParameter("userMail"));
//	uBean.setUserPassword(request.getParameter("userPassword"));
//	uBean.setUserConPassword(request.getParameter("userConPassword"));
//	uBean.setUserRole(request.getParameter("userRole"));
//	if (uBean.getUserMail().isBlank() || uBean.getUserPassword().isBlank() || uBean.getUserConPassword().isBlank()
//			|| uBean.getUserRole().isBlank()) {
//		request.setAttribute("errorFill", "Fill the Blank!!!");
//		request.setAttribute("uBean", uBean);
//		request.getRequestDispatcher("USR001.jsp").forward(request, response);
//	} else {
//		UserResponseDTO res = new UserResponseDTO();
//		UserRequestDTO dto = new UserRequestDTO();
//		List<UserBean> userList = new ArrayList<>();
//
//		dto.setUserId(uBean.getUserId());
//		dto.setUserMail(uBean.getUserMail());
//		dto.setUserPassword(uBean.getUserPassword());
//		dto.setUserRole(uBean.getUserRole());
//		dao.insertUserData(dto);
//		userList.add(uBean);
//		request.getServletContext().setAttribute("userList", userList);
//		request.setAttribute("errorFill", "Success Add");
//		request.getRequestDispatcher("USR001.jsp").forward(request, response);
//
//	}
	

}

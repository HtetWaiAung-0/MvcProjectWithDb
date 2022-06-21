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
import model.StudentBean;
import mvcproject.persistant.dao.CourseDAO;
import mvcproject.persistant.dto.CourseRequestDTO;
import mvcproject.persistant.dto.CourseResponseDTO;


/**
 * Servlet implementation class AddCourseController
 */
@WebServlet("/AddCourseController")
public class AddCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AtomicInteger autoCourseId = new AtomicInteger(1);
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCourseController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CourseBean cBean = new CourseBean();
		cBean.setCourseId(request.getParameter("cId"));
		cBean.setCourse(request.getParameter("cName"));
		if (cBean.getCourse().isBlank()) {

			request.setAttribute("errorFill", "Fill the Blank!!!");
			request.setAttribute("cBean", cBean);

			request.getRequestDispatcher("BUD003.jsp").forward(request, response);
		} else {
			CourseDAO dao = new CourseDAO();
			CourseResponseDTO res = new CourseResponseDTO();
			CourseRequestDTO dto = new CourseRequestDTO();
			dto.setCourseName(cBean.getCourse());
			dto.setCourseId(cBean.getCourseId());
			dao.insertCourseData(dto);
			ArrayList<CourseResponseDTO> courseList = dao.selectAll();
	
			request.getServletContext().setAttribute("courseList", courseList);
			request.setAttribute("errorFill", "Success Add");			
			request.getRequestDispatcher("BUD003.jsp").forward(request, response);
		}

	}

}

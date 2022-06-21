package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudentBean;
import mvcproject.persistant.dao.CourseStudentDAO;
import mvcproject.persistant.dao.StudentDAO;
import mvcproject.persistant.dto.CourseStudentRequestDTO;
import mvcproject.persistant.dto.StudentRequestDTO;


/**
 * Servlet implementation class DeleteStudentController
 */
@WebServlet("/DeleteStudentController")
public class DeleteStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StudentDAO dao = new StudentDAO();
		StudentRequestDTO dto = new StudentRequestDTO();
		CourseStudentDAO csdao = new CourseStudentDAO(); 
		CourseStudentRequestDTO csdto = new CourseStudentRequestDTO();
		
		String stuId = request.getParameter("stuId");
		
		dto.setStuId(stuId);
		csdto.setStuId(stuId);
		dao.deleteStudnetData(dto);
		csdao.deleteData(csdto);
			request.setAttribute("errorFill", "Success delete");
			response.sendRedirect("SearchStudentController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

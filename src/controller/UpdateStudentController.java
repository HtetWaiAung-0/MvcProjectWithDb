package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudentBean;
import mvcproject.persistant.dao.CourseStudentDAO;
import mvcproject.persistant.dao.StudentDAO;
import mvcproject.persistant.dto.StudentResponseDTO;
import mvcproject.persistant.dto.CourseStudentRequestDTO;
import mvcproject.persistant.dto.StudentRequestDTO;


/**
 * Servlet implementation class UpdateStudentController
 */
@WebServlet("/UpdateStudentController")
public class UpdateStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDAO dao = new StudentDAO();
		CourseStudentDAO csdao = new CourseStudentDAO();
		List<StudentResponseDTO> list = new ArrayList<>();
		list.add(dao.selectId(request.getParameter("stuId")));
		
		for(StudentResponseDTO a : list) {
			List<String> clist = csdao.selectOne(a.getStuId());
			a.setStuAttend(clist);   
		}
		
	
		request.setAttribute("stuList", list);
		request.getRequestDispatcher("STU002.jsp").forward(request, response);
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
		String stuId = request.getParameter("stuId");
		stuBean.setStuId(stuId);
		stuBean.setStuName(request.getParameter("stuName"));
		stuBean.setStuDob(request.getParameter("stuDob"));
		stuBean.setStuGender(request.getParameter("stuGender"));
		stuBean.setStuPhone(request.getParameter("stuPhone"));
		stuBean.setStuEducation(request.getParameter("stuEducation"));
		String [] attendArray = request.getParameterValues("stuAttend");
		
		
//		|| ststuBean.getStuPassword().equals("") || ststuBean.getStuConPassword().equals("")|| ststuBean.getStuRole().equals("")
		if (stuBean.getStuName().isBlank() ) {
			request.setAttribute("errorFill", "Fill the Blank!!!");
			request.setAttribute("ststuBean", stuBean);
			request.getRequestDispatcher("USR003.jsp").forward(request, response);
		} else {
			csdto.setStuId(stuId);
			csdao.deleteData(csdto);
			StudentResponseDTO res = new StudentResponseDTO();
			StudentRequestDTO dto = new StudentRequestDTO();
			List<StudentBean> StudentList = new ArrayList<>();
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
			
			dao.updateStudentData(dto);
			StudentList.add(stuBean);
			request.getServletContext().setAttribute("stuList", StudentList);
			response.sendRedirect("SearchStudentController");

		}
	}

}

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

import model.StudentBean;
import model.UserBean;
import mvcproject.persistant.dao.CourseStudentDAO;
import mvcproject.persistant.dao.StudentDAO;
import mvcproject.persistant.dto.CourseStudentResponseDTO;
import mvcproject.persistant.dto.StudentResponseDTO;

/**
 * Servlet implementation class SearchStudentController
 */
@WebServlet("/SearchStudentController")
public class SearchStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchStudentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentDAO dao = new StudentDAO();
		CourseStudentDAO csdao = new CourseStudentDAO();
		List<StudentResponseDTO> list = dao.selectAll();
		for(StudentResponseDTO a : list) {
			List<String> clist = csdao.selectOne(a.getStuId());
			a.setStuAttend(clist);   
		}
		request.setAttribute("stuList", list);
		request.getRequestDispatcher("STU003.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentBean stuBean = new StudentBean();
		StudentDAO dao = new StudentDAO();
		CourseStudentDAO csdao = new CourseStudentDAO();
		
		String searchId = request.getParameter("searchId");
		String searchName = request.getParameter("searchName");
		String searchCourse = request.getParameter("searchAttend");
		
		List<CourseStudentResponseDTO> csList = new ArrayList<>();
;		List<StudentResponseDTO> showList = new ArrayList<>();
		if (searchId.isBlank() && searchName.isBlank() && searchCourse.isBlank()) {
			showList = dao.selectAll();
			for(StudentResponseDTO a : showList) {
				List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
				a.setStuAttend(clist);   
			}
			request.setAttribute("stuList", showList);
			request.getRequestDispatcher("STU003.jsp").forward(request, response);
		} else {

			if (searchId.isBlank() && searchName.isBlank()) {
//				csList.add(csdao.selectOne(searchId));
				List<String> revList = (List<String>) csdao.selectReverse(searchCourse);
				for(String v : revList) {
					showList.add(dao.selectId(v));
				}
				for(StudentResponseDTO a : showList) {
					List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
					a.setStuAttend(clist);   
				}
				
			} else if (searchId.isBlank() && searchCourse.isBlank()) {
				showList.add(dao.selectName(searchName));
				for(StudentResponseDTO a : showList) {
					List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
					a.setStuAttend(clist);   
				}
				
			} else if (searchName.isBlank() && searchCourse.isBlank()) {
				showList.add(dao.selectId(searchId));
				for(StudentResponseDTO a : showList) {
					List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
					a.setStuAttend(clist);   
				}
			} else if (searchName.isBlank()) {
				List<String> revList = (List<String>) csdao.selectReverse(searchCourse);
				for(String v : revList) {
					showList.add(dao.selectId(v));
				}
				showList.add(dao.selectId(searchId));
				for(StudentResponseDTO a : showList) {
					List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
					a.setStuAttend(clist);   
				}
			} else if (searchId.isBlank()) {
				List<String> revList = (List<String>) csdao.selectReverse(searchCourse);
				for(String v : revList) {
					showList.add(dao.selectId(v));
				}
				showList.add(dao.selectName(searchName));
				for(StudentResponseDTO a : showList) {
					List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
					a.setStuAttend(clist);   
				}
				
			} else if (searchCourse.isBlank()) {
				showList.add(dao.selectIdAndName(searchId, searchName));
				for(StudentResponseDTO a : showList) {
					List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
					a.setStuAttend(clist);   
				}
			} else {
				List<String> revList = (List<String>) csdao.selectReverse(searchCourse);
				for(String v : revList) {
					showList.add(dao.selectId(v));
				}
				showList.add(dao.selectIdAndName(searchId, searchName));
				for(StudentResponseDTO a : showList) {
					List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
					a.setStuAttend(clist);   
				}
			}

			request.setAttribute("stuList", showList);
			

			request.getRequestDispatcher("STU003.jsp").forward(request, response);
		}
	}

}

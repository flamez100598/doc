/**
 * Copy right (C), 2020 Luvina
 * AddEditUserController.java, Feb 24, 2020
 */
package manageruser.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageruser.entities.mst_group;
import manageruser.entities.mst_japan;
import manageruser.logics.MstGroupLogic;
import manageruser.logics.MstJapanLogic;
import manageruser.logics.impl.MstGroupLogicImpl;
import manageruser.logics.impl.MstJapanLogicImpl;
import manageruser.utils.Contants;
import manageruser.validates.FormValidate;
import manageruser.validates.Validator;

/**
 * Validate Add and Edit form
 * @author DungPham
 *
 */
public class AddEditUserController extends HttpServlet {
	/**
	 * @param req resquest servlet
	 * @param resp response servlet
	 * validate form add and edit
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ArrayList<mst_group> listAllGroup = null;
			ArrayList<mst_japan> listAllJapanLevel = null;
			ArrayList<String> listErr = new ArrayList<String>();
			MstGroupLogic mstGr = new MstGroupLogicImpl();
			MstJapanLogic mjl = new MstJapanLogicImpl();
			// set request current time
			req.setAttribute("currentYear", Contants.CURRENT_YEAR);
			req.setAttribute("currentMonth", Contants.CURRENT_MONTH);
			req.setAttribute("currentDate", Contants.CURRENT_DATE);
			//--- lấy giá trị của group đổ lên view ----
			// get list all group form database
			listAllGroup = mstGr.getAllGroup();
			// set listAllGroup to JSP
			req.setAttribute("listAllGroup", listAllGroup);
			//---end lấy giá trị của group đổ lên view ----
			// -- lấy giá trị của trình độ tiếng nhật
			listAllJapanLevel = mjl.getAllListJapanLevel();
			req.setAttribute("listAllJapanLevel", listAllJapanLevel);
			// -- end lấy giá trị của trình độ tiếng nhật
			// -- validate form --
			String loginName = req.getParameter("login_name");
			String group_id = req.getParameter("group_id");
			String fullName = req.getParameter("fullName");
			String fullNameKata = req.getParameter("nameKata");
			// get param time birth day
			int yearBirth = Integer.parseInt(req.getParameter("yearBirth"));
			int monthBirth = Integer.parseInt(req.getParameter("monthBirth"));
			int dateBirth = Integer.parseInt(req.getParameter("dateBirth"));
			// get param time start day
			int startYear = Integer.parseInt(req.getParameter("startYear"));
			int startMonth = Integer.parseInt(req.getParameter("startMonth"));
			int startDate = Integer.parseInt(req.getParameter("startDate"));
			// get param end date
			int endYear = Integer.parseInt(req.getParameter("endYear"));
			int endMonth = Integer.parseInt(req.getParameter("endMonth"));
			int endDate = Integer.parseInt(req.getParameter("endDate"));
			String email = req.getParameter("email");
			String tel = req.getParameter("tel");
			String password = req.getParameter("password");
			String reWritePass = req.getParameter("reWritePass");
			String nameLevel = req.getParameter("code_level");
			String total = req.getParameter("total");
			System.out.println("name_level" + nameLevel);
			listErr = FormValidate.checkFormAddEdit(loginName, group_id, fullName, fullNameKata,
					yearBirth, monthBirth, dateBirth,
					email, tel, password, reWritePass, 
					nameLevel, startYear, startMonth, startDate, 
					endYear, endMonth, endDate, total);
			// -- end validate form --
			if(listErr.isEmpty()) {
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM003);
				requestDispatcher.forward(req, resp);
			} else {
				req.setAttribute("listErr", listErr);
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM003);
				requestDispatcher.forward(req, resp);
			}
		} catch (Exception e) {
			System.out.println("Class Add edit controller:" + e.getMessage());
			try {
				resp.sendRedirect(req.getContextPath() + Contants.URL_ERROR_DO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<mst_group> listAllGroup = null;
		ArrayList<mst_japan> listAllJapanLevel = null;
		MstGroupLogic mstGr = new MstGroupLogicImpl();
		MstJapanLogic mjl = new MstJapanLogicImpl();
		// set request current time
		req.setAttribute("currentYear", Contants.CURRENT_YEAR);
		req.setAttribute("currentMonth", Contants.CURRENT_MONTH);
		req.setAttribute("currentDate", Contants.CURRENT_DATE);
		int groupId = 0;
		//--- lấy giá trị của group đổ lên view ----
		// get list all group form database
		listAllGroup = mstGr.getAllGroup();
		// set listAllGroup to JSP
		req.setAttribute("listAllGroup", listAllGroup);
		//---end lấy giá trị của group đổ lên view ----
		// -- lấy giá trị của trình độ tiếng nhật
		listAllJapanLevel = mjl.getAllListJapanLevel();
		req.setAttribute("listAllJapanLevel", listAllJapanLevel);
		// -- end lấy giá trị của trình độ tiếng nhật
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM003);
		requestDispatcher.forward(req, resp);
	}
}

/**
 * Copy right (C), 2020 Luvina
 * AddEditUserController.java, Feb 24, 2020
 */
package manageruser.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageruser.entities.UserInfo;
import manageruser.entities.mst_group;
import manageruser.entities.mst_japan;
import manageruser.logics.MstGroupLogic;
import manageruser.logics.MstJapanLogic;
import manageruser.logics.Tbl_UserLogic;
import manageruser.logics.impl.MstGroupLogicImpl;
import manageruser.logics.impl.MstJapanLogicImpl;
import manageruser.logics.impl.Tbl_UserLogicImpl;
import manageruser.utils.Common;
import manageruser.utils.Contants;
import manageruser.validates.FormValidate;
import manageruser.validates.Validator;

/**
 * Validate Add and Edit form
 * @author DungPham
 *
 */
public class AddEditUserInputController extends HttpServlet {
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
			//get param from client
			String email = req.getParameter("email");
			String tel = req.getParameter("tel");
			String password = req.getParameter("password");
			String reWritePass = req.getParameter("reWritePass");
			String nameLevel = req.getParameter("code_level");
			String total = req.getParameter("total");
			String userId = req.getParameter("userId");
			System.out.println(userId);
			int user_id = 0;
			boolean isUpdate = false;
			if (Validator.isNotNull(userId)) {
				Tbl_UserLogic tblu = new Tbl_UserLogicImpl();
				UserInfo userInfo = new UserInfo();
				userInfo = tblu.getUserById(userId);
				user_id = Integer.parseInt(userId);
				isUpdate = true;
				req.setAttribute("userInfo", userInfo);
			}
			System.out.println(user_id);
			// check validate
			listErr = FormValidate.checkFormAddEdit(loginName, group_id, fullName, fullNameKata,
					yearBirth, monthBirth, dateBirth,
					email, tel, password, reWritePass, 
					nameLevel, startYear, startMonth, startDate, 
					endYear, endMonth, endDate, total, user_id, isUpdate);
			// -- end validate form --
			if(listErr.isEmpty()) {
				req.setAttribute("userId", userId);
				req.setAttribute("login_name", loginName);
				req.setAttribute("group_id", group_id);
				req.setAttribute("fullName", fullName);
				req.setAttribute("nameKata", fullNameKata);
				req.setAttribute("email", email);
				req.setAttribute("tel", tel);
				req.setAttribute("password", password);
				req.setAttribute("code_level", nameLevel);
				Date birthDay = new Date(yearBirth - 1900, monthBirth, dateBirth);
				Date startDateCodeLevel = new Date(startYear - 1900, startMonth, startDate);
				Date endDateCodeLevel = new Date(endYear - 1900, endMonth, endDate);
				mst_group mstGroup = new mst_group();
				mstGroup = mstGr.getGroupById(Integer.parseInt(group_id));
				req.setAttribute("groupName", mstGroup.getGroup_name());
				req.setAttribute("birthDay", birthDay);
				if (Validator.isNotNull(nameLevel)) {
					req.setAttribute("startDateCodeLevel", startDateCodeLevel);
					req.setAttribute("endDateCodeLevel", endDateCodeLevel);
					req.setAttribute("total", total);
				}
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM004);
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
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
			String user_id = req.getParameter("userId");
			req.setAttribute("userId", user_id);
			if (Validator.isNotNull(user_id)) {
				Tbl_UserLogic tblu = new Tbl_UserLogicImpl();
				UserInfo userInfo = new UserInfo();
				userInfo = tblu.getUserById(user_id);
				req.setAttribute("userInfo", userInfo);
				// set time birth day
				int yearBirth = Common.getYear(userInfo.getBirthday());
				int monthBirth = Common.getMonth(userInfo.getBirthday());
				int dateBirth = Common.getDate(userInfo.getBirthday());
				req.setAttribute("yearBirth", yearBirth);
				req.setAttribute("monthBirth", monthBirth);
				req.setAttribute("dateBirth", dateBirth);
				if (Validator.isNotNull(userInfo.getCode_level())) {
					// set time start day
					int startYear = Common.getYear(userInfo.getStart_date());
					int startMonth = Common.getMonth(userInfo.getStart_date());
					int startDate = Common.getDate(userInfo.getStart_date());
					req.setAttribute("startYear", startYear);
					req.setAttribute("startMonth", startMonth);
					req.setAttribute("startDate", startDate);
					// set end date
					int endYear = Common.getYear(userInfo.getEnd_date());
					int endMonth = Common.getMonth(userInfo.getEnd_date());
					int endDate = Common.getMonth(userInfo.getEnd_date());
					req.setAttribute("endYear", endYear);
					req.setAttribute("endMonth", endMonth);
					req.setAttribute("endDate", endDate);
				}
			}
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM003);
			requestDispatcher.forward(req, resp);
		} catch (Exception e) {
			System.out.println("Class Add edit controller:" + e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher(Contants.URL_ERROR_DO);
			try {
				rd.forward(req, resp);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}

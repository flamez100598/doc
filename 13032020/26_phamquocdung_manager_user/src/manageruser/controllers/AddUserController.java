/**
 * Copy right (C), 2020 Luvina
 * AddUserController.java, Feb 24, 2020
 */
package manageruser.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageruser.entities.mst_group;
import manageruser.logics.MstGroupLogic;
import manageruser.logics.Tbl_UserLogic;
import manageruser.logics.impl.MstGroupLogicImpl;
import manageruser.logics.impl.Tbl_UserLogicImpl;
import manageruser.utils.Contants;
import manageruser.validates.Validator;

/**
 * add user API 
 * @author DungPham
 *
 */
public class AddUserController extends HttpServlet {
	/**
	 * @param req resquest servlet
	 * @param resp response servlet
	 * validate form add and edit
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			boolean isAddUser = false;
			Object addUser = req.getParameter("isAddUser");
			if (addUser != null) {
				isAddUser = true;
			}
			MstGroupLogic mstGrLg = new MstGroupLogicImpl();
			mst_group mstGr = new mst_group();
			String groupId = groupId = req.getParameter("group_id");
			int grId = Integer.parseInt(groupId);
			mstGr = mstGrLg.getGroupById(grId);
			req.setAttribute("groupName", mstGr.getGroup_name());
			if (!isAddUser) {			
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM004);
				requestDispatcher.forward(req, resp);
				return;
			}	
			String loginName = req.getParameter("login_name");
			String fullName = req.getParameter("fullName");
			String nameKata = req.getParameter("nameKata");
			String email = req.getParameter("email");
			String tel = req.getParameter("tel");
			String password = req.getParameter("password");
			String nameLevel = req.getParameter("code_level");
			String birthDay = req.getParameter("birthDay");
			System.out.println(birthDay);
			Date bd = null;
			Date startDate = null;
			Date endDate = null;
			if (Validator.isNotNull(birthDay)) {
				bd = Date.valueOf(birthDay);
			}
//			 SimpleDateFormat formatter = new SimpleDateFormat();

//			String birthDayFormat = birthDay.toString("dd/MM/YYYY");
			String startDateParam =  req.getParameter("startDateCodeLevel");
			if (Validator.isNotNull(startDateParam)) {
				startDate = Date.valueOf(startDateParam); 
			}
			String endDateParam = req.getParameter("endDateCodeLevel");
			if (Validator.isNotNull(endDateParam)) {
				endDate = Date.valueOf(endDateParam);
			}
			String total = req.getParameter("total");

			System.out.println(isAddUser);
			if (isAddUser) {
				Tbl_UserLogic tblUl = new Tbl_UserLogicImpl();
				int rowInset = tblUl.AddUser(loginName, groupId, fullName, nameKata, 
							bd, email, tel, password, nameLevel, 
							startDate, endDate, total);
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM006);
				requestDispatcher.forward(req, resp);
				return;
			}

		} catch (Exception e) {
			System.out.println("Class Add user controller:" + e.getMessage());
			try {
				resp.sendRedirect(req.getContextPath() + Contants.URL_ERROR_DO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}

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
import manageruser.utils.MessageErrorProperties;
import manageruser.validates.Validator;

/**
 * add user API 
 * @author DungPham
 *
 */
public class AddEditUserConfirmController extends HttpServlet {
	/**
	 * @param req resquest servlet
	 * @param resp response servlet
	 * validate form add and edit
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			boolean isAddUser = false;
			String userId = req.getParameter("user_id");
			System.out.println("user_id" + userId);
			// if add user
			if (Validator.isNull(userId)) {
				isAddUser = true;
			}
			MstGroupLogic mstGrLg = new MstGroupLogicImpl();
			mst_group mstGr = new mst_group();
			String groupId = groupId = req.getParameter("group_id");
			int grId = Integer.parseInt(groupId);
			mstGr = mstGrLg.getGroupById(grId);
			req.setAttribute("groupName", mstGr.getGroup_name());
			String loginName = req.getParameter("login_name");
			String fullName = req.getParameter("fullName");
			String nameKata = req.getParameter("nameKata");
			String email = req.getParameter("email");
			String tel = req.getParameter("tel");
			String password = req.getParameter("password");
			String code_level = req.getParameter("code_level");
			String birthDay = req.getParameter("birthDay");
			Date bd = null;
			Date startDate = null;
			Date endDate = null;
			if (Validator.isNotNull(birthDay)) {
				bd = Date.valueOf(birthDay);
			}
			String total = "";
//			 SimpleDateFormat formatter = new SimpleDateFormat();

//			String birthDayFormat = birthDay.toString("dd/MM/YYYY");
			if (Validator.isNotNull(code_level)) {
				String startDateParam =  req.getParameter("startDateCodeLevel");
				if (Validator.isNotNull(startDateParam)) {
					startDate = Date.valueOf(startDateParam); 
				}
				String endDateParam = req.getParameter("endDateCodeLevel");
				if (Validator.isNotNull(endDateParam)) {
					endDate = Date.valueOf(endDateParam);
				}
				total = req.getParameter("total");
			}
			if (isAddUser) {
				Tbl_UserLogic tblUl = new Tbl_UserLogicImpl();
				int rowInset = tblUl.AddUser(loginName, groupId, fullName, nameKata, 
							bd, email, tel, password, code_level, 
							startDate, endDate, total);
				if (rowInset > 0) {
					req.setAttribute("message", Contants.SUCCESS_MESSAGE_ADD);
					RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.SUCCESS_DO);
					requestDispatcher.forward(req, resp);
				}
				else {
					RequestDispatcher rd = req.getRequestDispatcher(Contants.URL_ERROR_DO);
					rd.forward(req, resp);
				}
			}

		} catch (Exception e) {
			System.out.println("Class add eidt  user confirm controller:" + e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher(Contants.URL_ERROR_DO);
			try {
				rd.forward(req, resp);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}

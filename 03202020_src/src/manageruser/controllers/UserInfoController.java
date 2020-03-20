/**
 * Copy right (C) 2020 Luvina
 * AddEditInfoController.java, 12 Mar 2020 DungPham
 */
package manageruser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageruser.entities.UserInfo;
import manageruser.logics.Tbl_UserLogic;
import manageruser.logics.impl.Tbl_UserLogicImpl;
import manageruser.utils.Contants;
import manageruser.validates.Validator;

/**
 * show info user by id
 * @author DungPham
 */
public class UserInfoController extends HttpServlet {
	/**
	 * get user by user id
	 * @param req requset servlet
	 * @param resp response servlet
	 */ 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// lấy id từ param truyền vào
			String user_id = req.getParameter("userId");
			// kiểm tra user_id khác null
			if (Validator.isNotNull(user_id)) {
				// lấy id từ param truyền vào
				String isDelete = req.getParameter("isDelete");
				Tbl_UserLogic tblu = new Tbl_UserLogicImpl();
				UserInfo userInfo = new UserInfo();
				int userIdParse =  Integer.parseInt(user_id);
				if (Validator.isNotNull(isDelete)) {
					int checkDelete = tblu.deleteUserByUserId(userIdParse);
					req.setAttribute("message", Contants.SUCCESS_MESSAGE_DELETE);
					RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.SUCCESS_DO);
					requestDispatcher.forward(req, resp);
					return;
				}
				userInfo = tblu.getUserById(user_id);
				req.setAttribute("userInfo", userInfo);
				RequestDispatcher rd = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM005);
				rd.forward(req, resp);
			} else {
				req.setAttribute("message", "User không tồn tại!");
				RequestDispatcher rd = req.getRequestDispatcher(Contants.URL_ERROR_DO);
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			System.out.println("User info controller:" + e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher(Contants.URL_ERROR_DO);
			try {
				rd.forward(req, resp);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}

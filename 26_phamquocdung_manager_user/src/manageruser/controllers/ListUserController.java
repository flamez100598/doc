/**
 * Copy right (C), 2020 Luvina
 * ListUserController.java, Feb 24, 2020
 */
package manageruser.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageruser.dao.MstGroupDao;
import manageruser.dao.Tbl_UserDao;
import manageruser.dao.impl.MstGroupDaoImpl;
import manageruser.dao.impl.Tbl_UserDaoImpl;
import manageruser.entities.UserInfo;
import manageruser.entities.mst_group;
import manageruser.utils.Common;
import manageruser.utils.Contants;

/**
 * servlet get list user
 * @author DungPham
 *
 */
public class ListUserController extends HttpServlet {
	/**
	 * action send Redirect AMD002
	 * 
	 */ 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ArrayList<UserInfo> listUserInfo = null;
			ArrayList<mst_group> listAllGroup = null;
			Tbl_UserDao tud = new Tbl_UserDaoImpl();
			MstGroupDao mstGr = new MstGroupDaoImpl();
			HttpSession session = req.getSession();
			if ((Common.checkLogin(session))) {
				String fullName = "";
				int groupId = 0;
				if (req.getParameter("keyWord") != null) {
					fullName = req.getParameter("keyWord");
//					session.setAttribute("keyWord", fullName);
				}
				if (req.getParameter("group_id") != null) {
					groupId = Integer.parseInt(req.getParameter("group_id"));
//					session.setAttribute("group_id", groupId);
				}
				int totalPage = tud.getTotalUser(groupId, fullName);
				ArrayList<Integer> listPaging = Common.getListPaging(totalPage, 0, 10);
				System.out.println(listPaging);
				listAllGroup = mstGr.getAllGroup();
				listUserInfo = tud.getListUser(0, 0, groupId, fullName, "", "", "", "");
				req.setAttribute("listUserInfo", listUserInfo);
				req.setAttribute("listAllGroup", listAllGroup);
				req.getRequestDispatcher(Contants.FILE_JSP_PATH + "/ADM002.jsp").forward(req, resp);
			} else {
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(req.getContextPath() + "/logout.do");
			}
		} catch (Exception e) {
			System.out.println("Class: ListUserController: " + e.getMessage());
			// Chuyển đến trang system error
			resp.sendRedirect(req.getContextPath() +"/"+ Contants.FILE_JSP_PATH + Contants.URL_ERROR);
		}
	}
	/**
	 * action Login when submit form login
	 * method post
	 */ 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.sendRedirect(req.getContextPath() + "/listUser.do");
		} catch (Exception e) {
			System.out.println("Class: ListUserController: " + e.getMessage());
			// Chuyển đến trang system error
			resp.sendRedirect(req.getContextPath()+ "/" + Contants.FILE_JSP_PATH + Contants.URL_ERROR);
		}
	}
}

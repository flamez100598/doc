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
import manageruser.utils.MessageErrorProperties;

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
				String fullName = req.getParameter("keyWord");
				System.out.println(Integer.parseInt(req.getParameter("group_id")));
				String messError = "";
				int groupId = 0;
				int offSet = Contants.OFFSET;
				int currentPage = 1;
				int limit = Contants.LIMIT;
				if (req.getParameter("keyWord") != null && !"".equals(req.getParameter("keyWord"))) {
					fullName = req.getParameter("keyWord");
//					session.setAttribute("keyWord", fullName);
				}
				if (req.getParameter("group_id") != null && !"".equals(req.getParameter("group_id"))) {
					groupId = Integer.parseInt(req.getParameter("group_id"));
//					session.setAttribute("group_id", groupId);
				}
				if (req.getParameter("currentPage") != null && !"".equals(req.getParameter("currentPage"))) {
					currentPage = Integer.parseInt(req.getParameter("currentPage"));
//					session.setAttribute("group_id", groupId);
				}
				offSet = Common.getOffset(currentPage, limit);
//				System.out.println("offset:"+offSet);
				int totalUser = tud.getTotalUser(groupId, fullName);
				if (totalUser == 0) {
					messError = MessageErrorProperties.getValueByKey("MSG005");
					req.setAttribute("messError", messError);
				} else {
					ArrayList<Integer> listPaging = Common.getListPaging(totalUser, 0, currentPage);
					req.setAttribute("listPaging", listPaging);
					System.out.println(listPaging);
					System.out.println("total :"+totalUser);
					int totalPage = Common.calcTotalPage(totalUser, limit);
					System.out.println("total page:"+totalPage);
					int maxInListPage = listPaging.get(listPaging.size() - 1);
					int minInListPage = listPaging.get(0);
					if (totalPage > maxInListPage) {
						req.setAttribute("isEnableNext", maxInListPage + 1);
					} else {
						req.removeAttribute("isEnableNext");
					}
					if (maxInListPage > 3) {
						req.setAttribute("isEnablePrev", minInListPage - 1);
					} else {
						req.removeAttribute("isEnablePrev");
					}
					listAllGroup = mstGr.getAllGroup();
					listUserInfo = tud.getListUser(offSet, limit, groupId, fullName, "", "", "", "");
					req.setAttribute("listUserInfo", listUserInfo);
					req.setAttribute("listAllGroup", listAllGroup);
					req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM002).forward(req, resp);
				}
				
			} else {
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(req.getContextPath() + Contants.URL_LOGOUT);
			}
		} catch (Exception e) {
			System.out.println("Class: ListUserController: " + e.getMessage());
			// Chuyển đến trang system error
//			RequestDispatcher requestDispatcher = req.getRequestDispatcher(req.getContextPath() + Contants.URL_ERROR_DO);
			resp.sendRedirect(req.getContextPath() + Contants.URL_ERROR_DO);
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
			resp.sendRedirect(req.getContextPath() + Contants.URL_ERROR_DO);
		}
	}
}

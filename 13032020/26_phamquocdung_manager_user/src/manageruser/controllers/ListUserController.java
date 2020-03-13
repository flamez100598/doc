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
import manageruser.logics.MstGroupLogic;
import manageruser.logics.Tbl_UserLogic;
import manageruser.logics.impl.MstGroupLogicImpl;
import manageruser.logics.impl.Tbl_UserLogicImpl;
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
	 * get list user has search, sort , paging
	 * @param req requset servlet
	 * @param resp response servlet
	 */ 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String contextPath = req.getContextPath();
		try {
			// khởi tạo session
			HttpSession session = req.getSession();
			if ((Common.checkLogin(session))) {
				// khoi tao các class cần dùng
				Tbl_UserLogic tud = new Tbl_UserLogicImpl();
				MstGroupLogic mstGr = new MstGroupLogicImpl();
				// khởi tạo các variable cần dùng
				ArrayList<UserInfo> listUserInfo = null;
				ArrayList<mst_group> listAllGroup = null;
				String fullName = "";
				String messError = "";
				int groupId = 0;
				int offSet = Contants.OFFSET_DEFAULT;
				int currentPage = 1;
				int limit = Contants.LIMIT;
				String sortByFullName = Contants.ASC;
				String sortByCodeLevel = Contants.ASC;
				String sortByEndDate = Contants.DESC;
				String sortType = Contants.SORT_TYPE_DEFAULT;
				req.setAttribute(Contants.ASC, Contants.ASC);
				req.setAttribute(Contants.DESC, Contants.DESC);
				// param need to Search
				// get parameter keyword for search
				// --define object parameter
				Object fullNameParam = req.getParameter("keyWord");
				Object groupIdParam = req.getParameter("group_id");
				Object currentPageParam = req.getParameter("currentPage");
				Object sortTypeParam = req.getParameter("sortType");
				Object sortByFullNameParam = req.getParameter("sortByFullName");
				Object sortByCodeLevelParam = req.getParameter("sortByCodeLevel");
				Object sortByEndDateParam = req.getParameter("sortByEndDate");
				// --end define object parameter --
				if (fullNameParam != null && !"".equals(fullNameParam)) {
					fullName = fullNameParam.toString();
				}
				// get parameter group_id for search
				if (groupIdParam != null && !"".equals(groupIdParam)) {
					groupId = Integer.parseInt(groupIdParam.toString());
				}
				// end param need to Search
				//Sort
				// get parameter currentPage for search
				if (currentPageParam != null && !"".equals(currentPageParam)) {
					currentPage = Integer.parseInt(currentPageParam.toString());
				}
				// get parameter sortType for sort
				if (sortTypeParam != null && !"".equals(sortTypeParam)) {
					sortType = sortTypeParam.toString();
				}
				// get parameter sortType for sort
				if (sortByFullNameParam != null && !"".equals(sortByFullNameParam)) {
					sortByFullName = sortByFullNameParam.toString();			
				}
				// get sortByCodeLevel for sort
				if (sortByCodeLevelParam != null && !"".equals(sortByCodeLevelParam)) {					
					sortByCodeLevel = sortByCodeLevelParam.toString();					
				}
				// get sortByEndDate for sort
				if (sortByEndDateParam != null && !"".equals(sortByEndDateParam)) {		
					sortByEndDate = sortByEndDateParam.toString();						
				}
				// tính giá trị offset vị trí hiển thị
				offSet = Common.getOffset(currentPage, limit);
				// replace full_name search before response
				fullName = Common.replaceWhiteCard(fullName);
				//--- lấy giá trị của group đổ lên view ----
				// get list all group form database
				listAllGroup = mstGr.getAllGroup();
				// set listAllGroup to JSP
				req.setAttribute("listAllGroup", listAllGroup);
				//---end lấy giá trị của group đổ lên view ----
				// tổng só record hiển thị
				int totalUser = tud.getTotalUser(groupId, fullName);
				// nếu số tổng số record = 0
				if (totalUser == 0) {
					// hiển thị câu thông báo 
					messError = MessageErrorProperties.getValueByKey("MSG005");
					// set messeage to JSP
					req.setAttribute("messError", messError);
					// truyền req sang ADM002
					req.getRequestDispatcher(Contants.FILE_JSP_PATH + "/ADM002.jsp").forward(req, resp);
				// nếu tổng số record > 0
				} else {
					// khởi tạo listS list để sort
					String[] listS = Contants.LIST_SORT;
					// gắn list sort lên JSP
					req.setAttribute("listSort", listS);
					// ---set default sort in JSP---
					req.setAttribute("sortType", sortType);
					req.setAttribute("currentPage", currentPage);
					req.setAttribute("sortByFullName", sortByFullName);
					req.setAttribute("sortByCodeLevel", sortByCodeLevel);
					req.setAttribute("sortByEndDate", sortByEndDate);
					//--- end set default sort in JSP ----
					//--- get List Paing to show in JSP------
					ArrayList<Integer> listPaging = Common.getListPaging(totalUser, 0, currentPage);
					// set listPaging to JSP
					req.setAttribute("listPaging", listPaging);
					// tính tông page
					int totalPage = Common.calcTotalPage(totalUser, limit);
					// lấy giá trị lớn nhất trong chuỗi paging hiển thị
					int maxInListPage = listPaging.get(listPaging.size() - 1);
					// lấy giá trị nhỏ nhất trong chuỗi paging hiển thị
					int minInListPage = listPaging.get(0);
					// nếu số page lớn hơn chuỗi paiging
					if (totalPage > maxInListPage) {
						// set giá trị khi click button next page
						req.setAttribute("nextPage", maxInListPage + 1);
						// hiển thị nút prev
					} else {
						// nếu không có remove button next
						req.removeAttribute("nextPage");
					}
					// tính vị trí của current page và set button prev
					if (maxInListPage > 3) {
						// set giá trị page khi lick button prev page
						req.setAttribute("prevPage", minInListPage - 3);
					} else {
						// nếu không có remove button prePage
						req.removeAttribute("prevPage");
					}
					// ---end get List Paing to show in JSP----
					// get list user
					listUserInfo = tud.getListUser(offSet, limit, groupId, fullName, sortType, sortByFullName, sortByCodeLevel, sortByEndDate);
					// truyển list user lên JSP
					req.setAttribute("listUserInfo", listUserInfo);
					req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM002).forward(req, resp);
				}
				// nếu checklogin false
			} else {
				// điều hướng sang màn login
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(contextPath + Contants.URL_LOGOUT);
			}
		} catch (Exception e) {
			System.out.println("Class: ListUserController: " + e.getMessage());
			// Chuyển đến trang system error
			try {
				resp.sendRedirect(contextPath + Contants.URL_ERROR_DO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * action Login when submit form login
	 * method post
	 */ 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.sendRedirect(req.getContextPath() + "/listUser.do");
		} catch (Exception e) {
			System.out.println("Class: ListUserController: " + e.getMessage());
			// Chuyển đến trang system error
			try {
				resp.sendRedirect(req.getContextPath() + Contants.URL_ERROR_DO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}

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
import manageruser.utils.StringPool;
import manageruser.validates.Validator;

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
				// get uri cho trường hợp back
				StringBuilder uri = new StringBuilder();
				uri.append(req.getScheme());
				uri.append("://");
				uri.append(req.getServerName());
				uri.append(":");
				uri.append(req.getServerPort());
				uri.append(req.getRequestURI());
				uri.append("?");
				uri.append(req.getQueryString());
				//set uri vào session cho TH back
				session.setAttribute("uri_list_user", uri);
				// khoi tao các class cần dùng
				Tbl_UserLogic tud = new Tbl_UserLogicImpl();
				MstGroupLogic mstGr = new MstGroupLogicImpl();
				// khởi tạo các variable cần dùng
				ArrayList<UserInfo> listUserInfo = null;
				ArrayList<mst_group> listAllGroup = null;
				String fullName = StringPool.BLANK;
				String messError = StringPool.BLANK;
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
				req.setAttribute(Contants.MODE_SORT, Contants.MODE_SORT);
				req.setAttribute(Contants.MODE_SEARCH, Contants.MODE_SEARCH);
				req.setAttribute(Contants.MODE_PAGING, Contants.MODE_PAGING);
				// khởi tạo listS list để sort
				String[] listS = Contants.LIST_SORT;
				// param need to Search
				// --define parameter
				Object fullNameParam = req.getParameter("keyWord");
				Object groupIdParam = req.getParameter("group_id");
				Object currentPageParam = req.getParameter("currentPage");
				Object typeParam = req.getParameter("type");
				Object type = Common.checkIsNotNullObject(typeParam) ? typeParam.toString() : StringPool.BLANK;
				Object sortByFullNameParam = req.getParameter("sortByFullName");
				Object sortByCodeLevelParam = req.getParameter("sortByCodeLevel");
				Object sortByEndDateParam = req.getParameter("sortByEndDate");
				Object sortTypeParam = req.getParameter("sortType");
				// -- end define parameter
				switch (type.toString()) {
					case Contants.MODE_SEARCH : {
						// --end define object parameter --
						if (Common.checkIsNotNullObject(fullNameParam)) {
							fullName = fullNameParam.toString(); 
						}
						// get parameter group_id for search
						if (Common.checkIsNotNullObject(groupIdParam)) {
							groupId = Integer.parseInt(groupIdParam.toString());
						}
						// end param need to Search
						break;
					}
					case Contants.MODE_SORT : {
						//Sort
						// get parameter sortType for sort
						if (Common.checkIsNotNullObject(sortTypeParam)) {
							sortType = sortTypeParam.toString();
							switch (sortType) {
							case  "full_name": {	
								// get parameter sort theo full_name for sort
								if (Common.checkIsNotNullObject(sortByFullNameParam)) {
									sortByFullName = sortByFullNameParam.toString();	
								}
								break;
							}
							case  "name_level": {
								// get sortByCodeLevel for sort
								if (Common.checkIsNotNullObject(sortByCodeLevelParam)) {					
									sortByCodeLevel = sortByCodeLevelParam.toString();	
								}
								break;
							}
							case  "end_date": {
								// get sortByEndDate for sort
								if (Common.checkIsNotNullObject(sortByEndDateParam)) {		
									sortByEndDate = sortByEndDateParam.toString();
								}
								break;
							}
							}
						}
					}
					break;
					case Contants.MODE_PAGING : {
						// get parameter currentPage
						if (Common.checkIsNotNullObject(currentPageParam)) {
							currentPage = Integer.parseInt(currentPageParam.toString());
							req.setAttribute("currentPage", currentPage);
						}
					}
					break;
				}
				if (!type.equals(Contants.MODE_SEARCH)) {
					fullName = Common.checkIsNotNullObject(fullNameParam) ? fullNameParam.toString() : fullName;
					groupId = Common.checkIsNotNullObject(groupIdParam) ? Integer.parseInt(groupIdParam.toString()) : groupId;
					sortType = Common.checkIsNotNullObject(sortTypeParam) ? sortTypeParam.toString() : sortType;
					sortByFullName = Common.checkIsNotNullObject(sortByFullNameParam) ? sortByFullNameParam.toString() : sortByFullName;
					sortByCodeLevel = Common.checkIsNotNullObject(sortByCodeLevelParam) ? sortByCodeLevelParam.toString() : sortByCodeLevel;
					sortByEndDate = Common.checkIsNotNullObject(sortByEndDateParam) ? sortByEndDateParam.toString() : sortByEndDate;
					if (type.equals(Contants.MODE_PAGING)) {
						currentPage = Common.checkIsNotNullObject(currentPageParam) ? Integer.parseInt(currentPageParam.toString()) : currentPage;
					}

				}
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
					req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM002).forward(req, resp);
					// nếu tổng số record > 0
				} else {
					// tính giá trị offset vị trí hiển thị
					offSet = Common.getOffset(currentPage, limit);
					// gắn list sort lên JSP
					req.setAttribute("listSort", listS);

					//--- get List Paing to show in JSP------
					ArrayList<Integer> listPaging = Common.getListPaging(totalUser, limit, currentPage);
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
					//set param lên request
					req.setAttribute("keyWord", fullName);
					req.setAttribute("groupId", groupId);
					req.setAttribute("sortType", sortType);
					req.setAttribute("currentPage", currentPage);
					req.setAttribute("sortByFullName", sortByFullName);
					req.setAttribute("sortByCodeLevel", sortByCodeLevel);
					req.setAttribute("sortByEndDate", sortByEndDate);
					// -- end set param lên request
					// --set session for parameter
					session.setAttribute("fullName", fullName);
					session.setAttribute("groupId", groupId);
					session.setAttribute("sortType", sortType);
					session.setAttribute("currentPage", currentPage);
					session.setAttribute("sortByFullName", sortByFullName);
					session.setAttribute("sortByCodeLevel", sortByCodeLevel);
					session.setAttribute("sortByEndDate", sortByEndDate);
					// -- end set session for parameter
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
			resp.sendRedirect(req.getContextPath() + Contants.URL_LIST_USER);
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

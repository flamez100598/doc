/**
 *  Copy right (C) 2020 Luvina
 * LoginController.java, Feb 23, 2020 DungPham
 */
package manageruser.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageruser.logics.Tbl_UserLogic;
import manageruser.logics.impl.Tbl_UserLogicImpl;
import manageruser.utils.MessageErrorProperties;
import manageruser.utils.Contants;
import manageruser.validates.Validator;
import manageruser.validates.loginValidate;

/**
 * Servlet Login 
 * @author DungPham
 *
 */
public class LoginController extends HttpServlet {
	/**
	 * action Login when submit form login
	 * 
	 */ 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String contextPath = req.getContextPath();
		try {
			// gắn biến username để lấy dữ liệu loginname từ form
			String username = req.getParameter("loginName");
			// gắn biến password để lấy dữ liệu password từ form
			String password = req.getParameter("password");
			// khởi tạo biến session
			HttpSession session = req.getSession(false);
			// biến check xem có rỗng textbox không
			boolean isNullTextBox = false;
			// biến check trường hợp nhập sai tên đăng nhập hoặc mật khẩu
			boolean checkRedirect = false;
			// khởi tạo biến lưu message
			ArrayList<String> listMessError = new ArrayList<String>();
			// kiểm tra biến nhập user va password chưa
			listMessError = loginValidate.checkNullOrEmpty(username, password);
			// kiểm tra nhập các trường dữ liệu trong textbox chưa
	    	if (listMessError.isEmpty()) {
    			// gắn biến redirect = true
    			checkRedirect = true;
    			// set sesion cho login_user
    			session.setAttribute("login_name", username);
	    	} else {
		    	// set attribute cho lỗi để hiển thị ra JSP
		    	req.setAttribute("messageError", listMessError);
		    	System.out.println(listMessError.get(0));
	    	}
	    	// kiểm tra nếu k thỏa mãn điều kiện chuyển hướng 
	    	if (!checkRedirect) {
	    		// điều hướng lại trang ADM001 
	        	RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM001);
	        	// truyền response cho ADM001
	            requestDispatcher.forward(req, resp);
	         // kiểm tra nếu  thỏa mãn điều kiện chuyển hướng 
	    	} else {
	    		// điều hướng đến servlet ADM002 và truyền response cho ADM002
	    		resp.sendRedirect(contextPath + Contants.URL_LIST_USER);
	    	}

		} catch (Exception e) {
			System.out.println("Class: LoginController: " + e.getMessage());
			// Chuyển đến trang system error
			try {
				resp.sendRedirect(contextPath + Contants.URL_ERROR_DO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}

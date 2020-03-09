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
	// khai báo biến tbl_uli
	Tbl_UserLogic tbl_uli;
	/**
	 * action Login when submit form login
	 * 
	 */ 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			String messError = "";
			// kiểm tra biến nhập user va password chưa
			messError = loginValidate.checkNullOrEmpty(username, password);
			// kiểm tra nhập các trường dữ liệu trong textbox chưa
	    	if ("".equals(messError)) {
	    		// bắt lỗi execption
	    		try {
	    			// khởi tạo giá trị cho biến tbl_uli
	        		tbl_uli = new Tbl_UserLogicImpl();
	        		// kiểm tra nếu có tài khoản trong db
	        		if(tbl_uli.existLogin(username, password)) {
	        			// gắn biến redirect = true
	        			checkRedirect = true;
	        			// set sesion cho login_user
	        			session.setAttribute("login_name", username);
	        		}
	        		// kiểm tra nếu tài khoản k tồn tại trong db
	        		else {
	        			// gắn message từ file MessageError.Properties
	        			messError = MessageErrorProperties.getValueByKey("ER016");
	        		}
	    		} catch (Exception e) {
	    			// in ra lỗi nếu có ngoại lệ
	    			System.out.println(e.getMessage());
	    		}
	    	}
	    	// set attribute cho lỗi để hiển thị ra JSP
	    	req.setAttribute("messageError", messError);
	    	// kiểm tra nếu k thỏa mãn điều kiện chuyển hướng 
	    	if (!checkRedirect) {
	    		// điều hướng lại trang ADM001 
	        	RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.FILE_JSP_PATH + "/ADM001.jsp");
	        	// truyền response cho ADM001
	            requestDispatcher.forward(req, resp);
	         // kiểm tra nếu  thỏa mãn điều kiện chuyển hướng 
	    	} else {
	    		// điều hướng đến servlet ADM002
	        	RequestDispatcher requestDispatcher = req.getRequestDispatcher("./listUser.do");
	        	// truyền response cho ADM002
	            requestDispatcher.forward(req, resp);
	    	}

		} catch (Exception e) {
			System.out.println("Class: LoginController: " + e.getMessage());
			// Chuyển đến trang system error
			resp.sendRedirect(req.getContextPath()+ "/" + Contants.FILE_JSP_PATH + Contants.URL_ERROR);
		}
	}
}

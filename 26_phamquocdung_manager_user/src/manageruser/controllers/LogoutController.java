/**
 *  Copy right (C) 2020 Luvina
 * LogoutController.java, Feb 26, 2020 DungPham
 */
package manageruser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageruser.utils.Contants;

/**
 * Description
 * @author DungPham
 *
 */
public class LogoutController extends HttpServlet {
	// overright 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String contextPath = req.getContextPath();
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("login_name");
			resp.sendRedirect(contextPath);
		} catch (Exception e) {
			System.out.println("Class: LogoutController: " + e.getMessage());
			// Chuyển đến trang system error
			try {
				resp.sendRedirect(contextPath + Contants.URL_ERROR_DO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}

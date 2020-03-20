/**
 * Copy right (C), 2020 Luvina
 * AddEditUserController.java, Feb 24, 2020
 */
package manageruser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.javafx.scene.layout.region.RepeatStruct;

import manageruser.utils.Contants;
import manageruser.validates.Validator;

/**
 * Servlet BackPageListUserController 
 * @author DungPham
 *
 */
public class BackPageListUserController extends HttpServlet {
	/**
	 * action to back page list user
	 * 
	 */ 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			String uri = session.getAttribute("uri_list_user").toString();
			System.out.println(uri);
			if (Validator.isNotNull(uri)) {
				resp.sendRedirect(uri);
			}
		} catch (Exception e) {
			System.out.println("BackPageListUserController: " + e.getMessage());
			// Chuyển đến trang system error
			try {
				resp.sendRedirect(req.getContextPath() + Contants.URL_ERROR_DO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}
}

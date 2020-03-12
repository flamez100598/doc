/**
 * 
 */
package manageruser.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageruser.utils.Contants;

/**
 * @author Admin
 *
 */
public class AddEditUserController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ArrayList<String> listErr = new ArrayList<String>();
			if(listErr.isEmpty()) {
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM004);
				requestDispatcher.forward(req, resp);
			} else {
				req.setAttribute("listErr", listErr);
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM003);
				requestDispatcher.forward(req, resp);
			}
		} catch (Exception e) {
			System.out.println("Class Add edit controller:" + e.getMessage());
		}
	}
}

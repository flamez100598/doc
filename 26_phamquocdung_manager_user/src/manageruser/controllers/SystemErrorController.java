/**
 * 
 */
package manageruser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.HttpServer;

import manageruser.utils.Contants;

/**
 * @author Admin
 *
 */
public class SystemErrorController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.sendRedirect(req.getContextPath() + Contants.FILE_JSP_PATH + Contants.URL_ERROR_PAGE);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

/**
 * Copy right (C), 2020 Luvina
 * SystemErrorController.java, Feb 24, 2020
 */
package manageruser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.HttpServer;

import manageruser.utils.Contants;

/**
 * System error controller 
 * @author DungPham
 *
 */
public class SystemErrorController extends HttpServlet {
	/**
	 * @param req resquest servlet
	 * @param resp response servlet
	 * send message to system error page
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setAttribute("errorSystem", Contants.SYSTEM_ERROR);
			RequestDispatcher rd = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ERROR_PAGE);
			rd.forward(req, resp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

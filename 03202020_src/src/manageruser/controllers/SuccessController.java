package manageruser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageruser.utils.Contants;

/**
 * Copy right (C) 2020 Luvina
 * SuccessController.java, 17 Mar 2020 DungPham
 */

/**
 * Successfully controller
 * @author DungPham
 */
public class SuccessController extends HttpServlet {
	/**
	 * method get /success.do
	 * @param req requset servlet
	 * @param resp response servlet
	 */ 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			RequestDispatcher rd = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM006);
			rd.forward(req, resp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * method post /success.do
	 * @param req requset servlet
	 * @param resp response servlet
	 */ 
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			RequestDispatcher rd = req.getRequestDispatcher(Contants.FILE_JSP_PATH + Contants.URL_ADM006);
			rd.forward(req, resp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}
}

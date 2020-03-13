/**
 * Copyright(C) 2020  Luvina
 * LoginFilter.java, Feb 24, 2020 PhuongAnhTT
 */
package manageruser.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageruser.utils.Contants;
import manageruser.utils.Common;
/**
 * Filter để đảm bảo các màn hình đã login
 * 
 * @author DungPham
 */
public class LoginFilter implements Filter {

	/**
	 * destroy filter
	 */
	@Override
	public void destroy() {
	}
	/**
	 * Hàm xử lí logic kiểm tra login
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			// Ép kiểu HttpServlet
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			// Lấy về session
			HttpSession httpSession = req.getSession();
			// Khởi tạo biến checkLogin kiểm tra user đã đăng nhập hay chưa
			boolean checkLogin = Common.checkLogin(httpSession);
//			System.out.println(checkLogin);
//			boolean checkLogin = false;
			// Khởi tạo biến contextPath
			String contextPath = req.getContextPath();

			// Khởi tạo biến checkLoginRequest để kiểm tra xem đường dẫn hiện
			// Nếu đã login và đường dẫn là login
			if (checkLogin) {
				chain.doFilter(request, response);
				// Ngược lại chuyển đến màn hình login
			} else {
				// Redirect sang trang login
				resp.sendRedirect(contextPath + Contants.URL_LOGOUT);
			}
		} catch (Exception e) {
			System.out.println("Class: LoginFilter " + e.getMessage());
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			// Chuyển đến trang system error
			resp.sendRedirect(req.getContextPath() + Contants.URL_ERROR_DO);
		}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}

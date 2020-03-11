/**
 *  Copy right (C) 2020 Luvina
 * Contants.java, Feb 24, 2020 DungPham
 */
package manageruser.utils;

/**
 * Lớp chứa các hằng số
 * @author DungPham
 *
 */
public class Contants {
	// path link to jsp page
	public static final String FILE_JSP_PATH = "/View/jsp";
	// get link system_error
	public static final String URL_ERROR_DO = "/error.do";
	//get link path to logout 
	public static final String URL_LOGOUT = "/logout.do";
	//get link path ADM002
	public static final String URL_ADM002 = "/ADM002.jsp";
	//get link path ADM001
	public static final String URL_ADM001 = "/ADM001.jsp";
	//get link path ADM003
	public static final String URL_ADM003 = "/ADM003.jsp";
	//get link path ADM004
	public static final String URL_ADM004 = "/ADM004.jsp";
	//get link path ADM005
	public static final String URL_ADM005 = "/ADM005.jsp";
	//get link path ADM006
	public static final String URL_ADM006 = "/ADM006.jsp";
	// file path link to servlet get list user 
	public static final String URL_LIST_USER = "/listUser.do";
	// file path  System_Error.jsp
	public static final String URL_ERROR_PAGE = "/System_Error.jsp";
	// file path database properties
	public static final String PROPERTIES_DATABASE_PATH = "Database.properties";
	// file path message error properties
	public static final String PROPERTIES_ERROR_MESSAGE_PATH = "MessageErrorProperties.properties";
	// file path paging
	public static final String PROPERTIES_PAGING_PATH = "Paging.properties";
	// define limit
	public static final int LIMIT = Integer.parseInt(PagingProperties.getValueByKey("limit"));
	// set offset 
	public static final int OFFSET = 1;
	// sort tăng dần
	public static final String ASC = "ASC";
	// sort giảm dần
	public static final String DESC = "DESC";
}

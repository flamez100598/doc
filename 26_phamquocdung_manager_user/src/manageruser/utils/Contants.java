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
	public static final String FILE_JSP_PATH = "View/jsp";
	// file path database properties
	public static final String PROPERTIES_DATABASE_PATH = "Database.properties";
	// file path message error properties
	public static final String PROPERTIES_ERROR_MESSAGE_PATH = "MessageErrorProperties.properties";
	// file path paging
	public static final String PROPERTIES_PAGING_PATH = "Paging.properties";
	// file path link to servlet get list user 
	public static final String URL_LIST_USER = "/listUser.do";
	// file path  System_Error.jsp
	public static final String URL_ERROR = "/System_Error.jsp";
	// file path  System_Error.jsp
	public static final int LIMIT = Integer.parseInt(PagingProperties.getValueByKey("limit"));
	// set offset 
	public static final int OFFSET = 1;
	// sort tăng dần
	public static final String ASC = "ASC";
	// sort giảm dần
	public static final String DESC = "DESC";
}

/**
 *  Copy right (C) 2020 Luvina
 * Contants.java, Feb 24, 2020 DungPham
 */
package manageruser.utils;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

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
	// file path addEdit.do 
	public static final String ADD_EDIT_FORM_URL = "/addEdit.do";
	// file path addEdit.do 
	public static final String ADD_USER = "/addUser.do";
	// file path database properties
	public static final String PROPERTIES_DATABASE_PATH = "Database.properties";
	// file path message error properties
	public static final String PROPERTIES_ERROR_MESSAGE_PATH = "MessageErrorProperties.properties";
	// file path paging
	public static final String PROPERTIES_PAGING_PATH = "Paging.properties";
	// define limit
	public static final int LIMIT = Integer.parseInt(PagingProperties.getValueByKey("limit"));
	// set offset 
	public static final int OFFSET_DEFAULT = 1;
	// sort tăng dần
	public static final String ASC = "ASC";
	// sort giảm dần
	public static final String DESC = "DESC";
	// list sort 
	public static final String[] LIST_SORT = {"full_name" , "name_level", "end_date"};
	// default sort_type.
	public static final String SORT_TYPE_DEFAULT = "full_name";
	// default ESCAPE SQL 
	public static final String ESCAPE_SQL = "#";
	// rule ADMIn 
	public static final int RULE_ADMIN = 0;
	// rule clinet
	public static final int RULE_CLIENT = 1;
	// message error system
	public static final String SYSTEM_ERROR = MessageErrorProperties.getValueByKey("ER015");
	// get time now
	static Calendar calendar = Calendar.getInstance();
	// get year now
	public static final int YEAR_SET = 1900;
	// get current year
	public static final int CURRENT_YEAR = calendar.get(Calendar.YEAR);
	// get current month 
	public static final int CURRENT_MONTH = calendar.get(Calendar.MONTH);
	// get current day
	public static final int CURRENT_DATE = calendar.get(Calendar.DAY_OF_MONTH);
}

/**
 *  Copy right (C) 2020 Luvina
 * Common.java, Feb 24, 2020 DungPham
 */
package manageruser.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import com.oracle.wls.shaded.org.apache.xalan.xsltc.compiler.sym;

import manageruser.dao.Tbl_UserDao;
import manageruser.dao.impl.Tbl_UserDaoImpl;
import manageruser.entities.tbl_user;

/**
 * common static function
 * @author DungPham
 *
 */
public class Common {
	/**
	 * 
	 * @param str string has symbol need replace
	 * @return String was replaced
	 */
	public static String replaceSymbol(String str) {
		String[] symbol = {"&", "\"", "'", "/", "<", ">"};
		String[] replaceSymbol = {"&amp;", "&quot;", "&#x27;", "&#x2F;", "&lt;", "&gt;"};
		for (int i = 0; i < symbol.length; i++) {
			str.replaceAll(symbol[i], replaceSymbol[i]);
		}
		return str;
	}
	/**
	 * get salt 
	 * @param
	 * @return String random salt
	 */
	public static String getSalt() throws NoSuchAlgorithmException
	{
		SecureRandom sr1 = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr1.nextBytes(salt);
		return salt.toString();
	}
	/**
	 * hash code encypt pass + salt
	 * @Param  passwordToHash password need to encrypt
	 * @Param  salt salt need to encrypt
	 * @return String code encrypt 
	 */
	public static String get_SHA_1_SecurePassword(String passwordToHash, String salt)
	{
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		return generatedPassword;
	}
	/**
	 * @param sess
	 * @return true if logined
	 * false if login false
	 * @throws Exception
	 */
	public static boolean checkLogin(HttpSession sess) throws Exception {
		Tbl_UserDao userDao = new Tbl_UserDaoImpl();
		Object sessionLoginName = sess.getAttribute("login_name");
		
		if (sessionLoginName == null || "".equals(sessionLoginName)) {
			return false;
		} else {
			tbl_user user = new tbl_user();
			String userName = sessionLoginName.toString();
			user = userDao.getTblUserByLoginName(userName);
			if (user == null) {
				return false;
			} 
			return true;
		}
	}
	/**
	 * @param str1 chuỗi đầu cần so sánh
	 * @param str2 chuỗi thứ 2 cần so sánh
	 * @return true nếu bằng nhau
	 * false nếu khác nhau
	 */
	public static boolean CompareString(String str1, String str2) {
		return str1.equals(str2);
	}
	/**
	 * calcular total page
	 * @param totalUser tổng số user
	 * @param limit sô record trên 1 page
	 * @return int tổng số page
	 */
	public static int calcTotalPage(int totalUser, int limit) {
		int totalPage = totalUser / limit;
		float pageSurplus = totalUser % limit;
		if (pageSurplus > 0) {
			totalPage = totalPage + 1;
		}
		return totalPage;
	}
	/**
	 * Tạo chuỗi paging
	 * @param int totalUser tổng sô user
	 * @param int limit số lượng cần hiển thị trên 1 trang
	 * @return int[] chuỗi paging cần hiển thị
	 */
	public static ArrayList<Integer> getListPaging(int totalUser, int limit, int currentPage) {
		limit = Contants.LIMIT;
		int limitPage = Contants.LIMIT_PAGE;
		ArrayList<Integer> listPaging = new ArrayList<Integer>();
		int pageShow = currentPage / limitPage;
		int surPlus = currentPage % limitPage;
		int totalPage = calcTotalPage(totalUser, limit);
		int pageStart = (currentPage <= limitPage) ? 1 : limitPage + 1;
		int endPage = (totalPage <= pageStart + (limitPage - 1)) ? totalPage : pageStart + (limitPage - 1);
		if (currentPage > limitPage)  {			
			if (surPlus > 0) {
				pageStart = (pageShow * limitPage) + 1;
			} else {
				pageStart = currentPage - (limitPage - 1);
			}
		}
		for (int i = pageStart; i <= endPage; i++) {
			listPaging.add(i);
		}
		return listPaging;
	}
	/**
	 * get vị trí bắt đầu hiển thị
	 * @param currentPage page hiện tại
	 * @param limit số page hiển thị trên 1 record
	 * @return int vị trí hiển thị 
	 */
	public static int getOffset(int currentPage, int limit) {
		return ((currentPage - 1) *  limit);
	}	
	/**
	 * @param str need replace
	 * @return str replace
	 */
	public static String replaceWhiteCard(String str) {	
		String[] symbol = {"!", "@", "$", "%" , "&", "<" , ">", ":", "=", ";"};
		String[] symbolReplace = {"#!", "#@", "#$", "#%" , "#&", "#<" , "#>", "#:", "#=", "#;"};
		if (!"".equals(str)) {
			for (int i = 0; i < symbol.length; i++) {
				str = str.replaceAll("/" + symbol[i] + "/", symbolReplace[i]);
			}
		}
		return str;
	}
	/**
	 * get year by date sql
	 * @param  d 
	 * @return int
	 */
	public static int getYear(Date d) {
		return d.getYear() + 1900;
	}
	/**
	 * get month by date sql
	 * @param  d 
	 * @return int
	 */
	public static int getMonth(Date d) {
		return d.getMonth() + 1;
	}
	/**
	 * get date by date sql
	 * @param  d 
	 * @return int
	 */
	public static int getDate(Date d) {
		return d.getDate();
	}
	/**
	 * check is not null object
	 * @param obj
	 * @return true if oject not equals null
	 * false if object equals null
	 */
	public static boolean checkIsNotNullObject(Object obj) {
		if (obj != null && !"".equals(obj)) {
			return true;
		}
		return false;
	}
}

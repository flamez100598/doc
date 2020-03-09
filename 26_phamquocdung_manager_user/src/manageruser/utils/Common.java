/**
 *  Copy right (C) 2020 Luvina
 * Common.java, Feb 24, 2020 DungPham
 */
package manageruser.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import manageruser.dao.Tbl_UserDao;
import manageruser.dao.impl.Tbl_UserDaoImpl;
import manageruser.entities.tbl_user;

/**
 * common static function
 * @author DungPham
 *
 */
/**
 * @author Admin
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
	private static String getSalt() throws NoSuchAlgorithmException
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
		if (sess.getAttribute("login_name") == null) {
			return false;
		} else {
			tbl_user user = new tbl_user();
			String userName = sess.getAttribute("login_name").toString();
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
	 * Tạo chuỗi paging
	 * @param int totalUser tổng sô user
	 * @param int limit số lượng cần hiển thị trên 1 trang
	 * @return int[] chuỗi paging cần hiển thị
	 */
	public static ArrayList<Integer> getListPaging(int totalUser, int limit, int currentPage) {
		limit = Contants.LIMIT;
		ArrayList<Integer> listPaging = new ArrayList<Integer>();
		int totalPage = totalUser / limit;
		float pageSurplus = totalUser % limit;
		if (pageSurplus > 0) {
			totalPage += totalPage;
		}
		if (totalPage <= 3 || currentPage <= 3) {
			for (int i = 1; i < 3; i++) {
				listPaging.add(i);
			}
			if (totalPage >= 3 || (totalPage == 2 && pageSurplus > 0)) {
				listPaging.add(3);
			}
		} else {
			int pageStart = 4;
			int pageEnd = 4;
			int pageShow = currentPage / 3;
			int surPlus = currentPage % 3;
			if (surPlus > 0) {
				if (pageShow > 1) {
					pageStart = (pageShow * 4) - 2;
				}
				else {
					pageStart = pageShow * 4;
				}
			} else {
				pageStart = currentPage - 2;
			}
			if (((totalPage - pageStart) >= 2)) {
				for (int i = pageStart; i <= pageStart + 2; i++) {
					listPaging.add(i);
				}
			} else {
				for (int i = pageStart; i <= totalPage + 2; i++) {
					listPaging.add(i);
				}
			}

		}

		return listPaging;
	}
	public static int getOffset(int currentPage, int limit) {
		limit = Contants.LIMIT;
		if(currentPage == 1) {
			return 1;
		}
		return ((currentPage-1) *  limit) + 1;
	}
}

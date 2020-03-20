/**
 *  Copy right (C) 2020 Luvina
 * Tbl_UserLogicImpl.java, Feb 23, 2020 DungPham
 */
package manageruser.logics.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import manageruser.dao.Tbl_UserDao;
import manageruser.dao.impl.Tbl_UserDaoImpl;
import manageruser.entities.UserInfo;
import manageruser.entities.tbl_user;
import manageruser.logics.Tbl_UserLogic;
import manageruser.utils.Common;
import manageruser.validates.Validator;

/**
 * user logic implement
 * @author DungPham
 *
 */
public class Tbl_UserLogicImpl  implements Tbl_UserLogic {
	Tbl_UserDao userDao;
	public Tbl_UserLogicImpl() {
		userDao = new Tbl_UserDaoImpl(); 
	}
	// overright 
	@Override
	/**
	 * init function exist Login
	 * @param username username to check 
	 * @param password pass to check 
	 * @return true if exist user
	 * false if not exist user
	 */
	public boolean existLogin(String username, String password) throws SQLException {
		boolean check = false;
		tbl_user tbl_user;
		try {
			tbl_user = userDao.getTblUserByLoginName(username);
			if (tbl_user != null) {
				String passHas = Common.get_SHA_1_SecurePassword(password, tbl_user.getSal());
//				System.out.println(passHas);
				if (Common.CompareString(passHas, tbl_user.getPass())) {
					check = true;
				} 
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			return check;
		}
	}

	/* (non-Javadoc)
	 * @see manageruser.logics.Tbl_UserLogic#getListUser(int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<UserInfo> getListUser(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) {
		fullName = Common.replaceWhiteCard(fullName);
		ArrayList<UserInfo> listUserInfo = new ArrayList<UserInfo>();
		listUserInfo = userDao.getListUser(offset, limit, groupId, fullName, sortType, sortByFullName, sortByCodeLevel, sortByEndDate);
		return listUserInfo;
	}
	/**
	 * @param groupId
	 * @param FullName
	 * @return total rows record user form db
	 */
	@Override
	public int getTotalUser(int groupId, String FullName) {
		return userDao.getTotalUser(groupId, FullName);
	}
	/**
	 * Validate from add or edit 
	 * @param loginName login_name nhập từ bàn phím
	 * @param groupId groupId chọn từ ô pulldown
	 * @param fullName fullName nhập từ bàn phím
	 * @param fullNameKata fullNameKata nhập từ bàn phím
	 * @param birthDay dateBirth chọn từ pulldown của hạng mục sinh nhật
	 * @param email email nhập từ bàn phím
	 * @param tel tel nhập từ bàn phím
	 * @param password password nhập từ bàn phím
	 * @param reWritePass nhập lại của hạng mục password nhập từ bàn phím
	 * @param nameLevel nameLevel chọn từ pulldown hạng mục trình độ tiếng nhật
	 * @param startDate chọn từ pulldown của hạng mục start date
	 * @param endDate chọn từ pulldown của hạng mục end date
	 * @param total total nhập từ bàn phím
	 * @return int
	 * @throws NoSuchAlgorithmException 
	 */
	@Override
	public int AddUser(String loginName, String groupId, String fullName, String fullNameKata,
			Date birthDay, String email, String tel, 
			String password, String nameLevel, 
			Date startDate,
			Date endDate, String total) throws NoSuchAlgorithmException {
		int grId = Integer.parseInt(groupId);
//		Date birthDay = new Date(yearBirth - 1900, monthBirth, dateBirth);
//		Date startDate = new Date(yearStartDate - 1900, monthStartDate, dateStartDate);
//		Date endDate = new Date(yearEndDate - 1900, monthEndDate, dateEndDate);
		String salt = Common.getSalt();
		int totalParse = 0;
		if (Validator.isNotNull(nameLevel)) {
			totalParse = Integer.parseInt(total);
		}
		String passwordHas = Common.get_SHA_1_SecurePassword(password, salt);
		int isInsert = userDao.AddUser(loginName, grId, fullName, fullNameKata, 
				birthDay, email, tel, passwordHas, 
				nameLevel, startDate, endDate, totalParse, salt);
		return isInsert;
	}
	/**
	 * get user by user_id
	 * @param userId need check
	 * @return tbl_userE
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public UserInfo getUserById(String userId) throws SQLException, Exception {
		int user_id = Integer.parseInt(userId);
		return userDao.getUserById(user_id);
	}
	/**
	 * delete user by user_id
	 * @param userId need to delete user
	 * @return int 0 if delete false
	 *  1 if delete succes
	 */
	public int deleteUserByUserId(int userId) {
		return userDao.deleteUser(userId);
	}
}

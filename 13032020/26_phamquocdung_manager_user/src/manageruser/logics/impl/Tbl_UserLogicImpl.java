/**
 *  Copy right (C) 2020 Luvina
 * Tbl_UserLogicImpl.java, Feb 23, 2020 DungPham
 */
package manageruser.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import manageruser.dao.Tbl_UserDao;
import manageruser.dao.impl.Tbl_UserDaoImpl;
import manageruser.entities.UserInfo;
import manageruser.entities.tbl_user;
import manageruser.logics.Tbl_UserLogic;
import manageruser.utils.Common;

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
}

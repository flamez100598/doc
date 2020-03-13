/**
 *  Copy right (C) 2020 Luvina
 * Tbl_UserLogic.java, Feb 23, 2020 DungPham
 */
package manageruser.logics;
import java.sql.SQLException;
import java.util.ArrayList;

import manageruser.entities.UserInfo;

/**
 * user logic implement
 * @author DungPham
 *
 */
public interface Tbl_UserLogic {
	/**
	 * init function exist Login
	 * @param username username to check 
	 * @param password pass to check 
	 * @return true if exist user
	 * false if not exist user
	 * @throws SQLException 
	 */
	public boolean existLogin(String username, String password) throws SQLException;
	/**
	 * @param offset
	 * @param limit
	 * @param groupId
	 * @param fullName
	 * @param sortType
	 * @param sortByFullName
	 * @param sortByCodeLevel
	 * @param sortByEndDate
	 * @return list user info
	 */
	public ArrayList<UserInfo> getListUser(int offset, int limit, int groupId, String fullName, String sortType, String sortByFullName, String sortByCodeLevel, String sortByEndDate);
	/**
	 * @param groupId
	 * @param FullName
	 * @return total rows record user form db
	 */
	public int getTotalUser(int groupId, String FullName);
}

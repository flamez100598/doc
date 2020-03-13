/**
 *  Copy right (C) 2020 Luvina
 * Tbl_UserDao.java, Feb 23, 2020 DungPham
 */
package manageruser.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import manageruser.entities.UserInfo;
import manageruser.entities.tbl_user;

/**
 * user data access interface
 * @author DungPham
 *
 */
public interface Tbl_UserDao {
	/**
	 * get user by login_name
	 * @param username username need check
	 * @return tbl_userEntity
	 * @throws Exception 
	 */
	public tbl_user getTblUserByLoginName(String username) throws SQLException, Exception;
	/**
	 * @param groupId
	 * @param FullName
	 * @return total rows record user form db
	 */
	public int getTotalUser(int groupId, String FullName);
	/**
	 * get list user from db
	 * @param keyWord key word search by username
	 * @param offset vị trí data cần lấy
	 * @param limit số record/ 1 page
	 * @param groupId groupId cần lấy để search
	 * @param fullName fullname cần lấy dể search
	 * @param sortType kiểu sắp xếp
	 * @param sortByFullName sắp xếp theo fullname
	 * @param sortByCodeLevel sắp xếp theo codelevel
	 * @param sortByEndDate sắp xếp theo ngày hết hạn
	 * @return list user
	 */
	public ArrayList<UserInfo> getListUser(int offset, int limit, int groupId, String fullName, String sortType, String sortByFullName, String sortByCodeLevel, String sortByEndDate);
	/**
	 * check email exist
	 * @param email email need check 
	 * @return boolean
	 */
	public int checkExistEmail(String email);
	

}

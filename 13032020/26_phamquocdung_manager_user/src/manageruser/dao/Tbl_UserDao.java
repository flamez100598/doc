/**
 *  Copy right (C) 2020 Luvina
 * Tbl_UserDao.java, Feb 23, 2020 DungPham
 */
package manageruser.dao;

import java.sql.Date;
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
	tbl_user getTblUserByLoginName(String username) throws SQLException, Exception;
	/**
	 * @param groupId
	 * @param FullName
	 * @return total rows record user form db
	 */
	int getTotalUser(int groupId, String FullName);
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
	ArrayList<UserInfo> getListUser(int offset, int limit, int groupId, String fullName, String sortType, String sortByFullName, String sortByCodeLevel, String sortByEndDate);
	/**
	 * check email exist
	 * @param email email need check 
	 * @return boolean
	 */
	int checkExistEmail(String email, int UserId);
	/**
	 * Validate from add or edit 
	 * @param loginName login_name nhập từ bàn phím
	 * @param groupId groupId chọn từ ô pulldown
	 * @param fullName fullName nhập từ bàn phím
	 * @param fullNameKata fullNameKata nhập từ bàn phím
	 * @param birthDay birthDay thêm mới
	 * @param email email nhập từ bàn phím
	 * @param tel tel nhập từ bàn phím
	 * @param password password nhập từ bàn phím
	 * @param reWritePass nhập lại của hạng mục password nhập từ bàn phím
	 * @param nameLevel nameLevel chọn từ pulldown hạng mục trình độ tiếng nhật
	 * @param startDate startDate thêm mới
	 * @param endDate endDate thêm mới
	 * @param total total nhập từ bàn phím
	 * @return int 1 is insert success
	 */
	int AddUser(String loginName, int groupId, String fullName, String fullNameKata,
			Date birthDay, String email, String tel, String password, String nameLevel, 
			Date startDate,
			Date endDate, int total, String salt);
	/**
	 * get user by user_id
	 * @param userId need check
	 * @return tbl_userE
	 * @throws Exception 
	 */
	UserInfo getUserById(int userId) throws SQLException, Exception;
	tbl_user getUserByLoginName(int userId, String loginName);
	
}

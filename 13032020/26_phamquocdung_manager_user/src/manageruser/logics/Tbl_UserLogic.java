/**
 *  Copy right (C) 2020 Luvina
 * Tbl_UserLogic.java, Feb 23, 2020 DungPham
 */
package manageruser.logics;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import manageruser.entities.UserInfo;
import manageruser.entities.tbl_user;

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
	boolean existLogin(String username, String password) throws SQLException;
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
	ArrayList<UserInfo> getListUser(int offset, int limit, int groupId, String fullName, String sortType, String sortByFullName, String sortByCodeLevel, String sortByEndDate);
	/**
	 * @param groupId
	 * @param FullName
	 * @return total rows record user form db
	 */
	int getTotalUser(int groupId, String FullName);
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
	int AddUser(String loginName, String groupId, String fullName, String fullNameKata,
			Date birthDay, String email, String tel, 
			String password, String nameLevel, 
			Date startDate,
			Date endDate, String total) throws NoSuchAlgorithmException;
}

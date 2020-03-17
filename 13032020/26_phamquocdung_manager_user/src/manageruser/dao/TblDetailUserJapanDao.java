/**
 *  Copy right (C) 2020 Luvina
 * TblDetailUserJapanDao.java, March 10, 2020 DungPham
 */
package manageruser.dao;

import java.sql.Date;

/**
 * tbl detail user japan data access
 * @author DungPham
 *
 */
public interface TblDetailUserJapanDao {
	/**
	 * add detail user japan
	 * @param userId user_id cần thêm vào bảng
	 * @param nameLevel nameLevel chọn từ pulldown hạng mục trình độ tiếng nhật
	 * @param startDate startDate thêm mới
	 * @param endDate endDate thêm mới
	 * @param total total nhập từ bàn phím
	 */
	int addDetailUserJapan(int userId, String nameLevel, Date startDate, Date endDate, int total);
	/**
	 * delete detail user japan by user_id
	 * @param userId
	 * @return int > 0 if delete success 
	 * 0 if delete false
	 */
	int deleteDetailUserJapan(int userId);
	
}

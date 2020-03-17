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
	 * Desciption
	 * @param
	 * @return int
	 */
	int addDetailUserJapan(int userId, String nameLevel, Date startDate, Date endDate, int total);
}

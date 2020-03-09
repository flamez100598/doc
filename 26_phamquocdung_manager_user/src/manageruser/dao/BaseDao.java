/**
 * Copyright(C) 2020  Luvina
 * BaseDao.java, Feb 23, 2020 DungPham
 */
package manageruser.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface định nghĩa các phương thức của BaseDaoImpl
 * @author DungPham
 */
public interface BaseDao {
	/**
	 * Hàm mở kết nối đến DB 
	 * 
	 * @throws SQLException
	 * 			Ngoại lệ sai lệnh SQl
	 * @throws ClassNotFoundException
	 * 			Ngoại lệ không tìm thấy class
	 */
	void openConnect() throws SQLException, ClassNotFoundException;
	/**
	 * Hàm đóng kết nối đến DB 
	 * 
	 * @throws SQLException
	 * 			Ngoại lệ sai lệnh SQl
	 */
	
	void closeConnect() throws SQLException;
	/**
	 * Hàm set giá trị cho connection
	 * 
	 * @param connect
	 * 			Conection kết nối với DB
	 */
	void setConnect(Connection connect);

	/**
	 * Lấy connection
	 * 
	 * @return connect kết nối với DB
	 */
	Connection getConnect();

	/**
	 * Hàm commit dữ liệu vào DB
	 * 
	 * @throws SQLException
	 * 			Ngoại lệ sai lệnh SQl
	 * 
	 */
	void commit() throws SQLException;

	/**
	 * Hàm rollback lại dữ liệu khi bị lỗi
	 * 
	 * @throws SQLException
	 * 			Ngoại lệ sai lệnh SQl
	 * 
	 */
	void rollback() throws SQLException;

	/**
	 * Hàm set giá trị để không tự động commit lên DB
	 * 
	 * @param isCommit
	 * 			giá trị truyền vào (false để ngắt tự động commit, true để bật
	 *            tự động commit)
	 * @throws SQLException
	 * 			Ngoại lệ sai lệnh SQl
	 */
	public void setAutoCommit(boolean isCommit) throws SQLException;
}

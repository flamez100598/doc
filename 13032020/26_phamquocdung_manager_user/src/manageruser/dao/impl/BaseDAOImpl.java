/**
 *  Copy right (C) 2020 Luvina
 * BaseDAOImpl.java, Feb 23, 2020 DungPham
 */
package manageruser.dao.impl;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import manageruser.dao.BaseDao;
import manageruser.utils.DatabaseProperties;

/**
 * Base Dao Impl
 * @author DungPham
 *
 */
public abstract class BaseDAOImpl implements BaseDao {
	// khởi biến url lấy giá trị từ file Database.Properties
	private String url = DatabaseProperties.getValueByKey("url").replaceAll("'", "");
	// khởi biến user lấy giá trị từ file Database.Properties
	private String user = DatabaseProperties.getValueByKey("user");
	// khởi biến pass lấy giá trị từ file Database.Properties
	private String pass = DatabaseProperties.getValueByKey("password");
	// khởi biến driver lấy giá trị từ file Database.Properties
	private String driver = DatabaseProperties.getValueByKey("driver");
	// khai báo biến connection
	protected Connection connect;
	/**
	 * Hàm mở kết nối đến DB 
	 * 
	 * @throws SQLException
	 * 				Lỗi SQL
	 * @exception ClassNotFoundException
	 * 			Ngoại lệ không tìm thấy class
	 */
	@Override
	public void openConnect() throws SQLException {
		try {
			// khai báo class for JDBC
			Class.forName(driver);
			// khởi tạo kết nối gắn vào biến connect
			connect = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			// xử lý ngoại lệ khi xảy ra lỗi
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
		}
	}
	
	/**
	 * Hàm đóng kết nối đến DB 
	 * 
	 * @exception SQLException
	 * 				Lỗi SQL
	 */
	@Override
	public void closeConnect() {
		try {
			// Nếu connect khác null hoặc chưa đóng
			if (connect != null && !connect.isClosed()) {
				// đóng kết nối
				connect.close();
			}
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.err.println("Class:BaseDaoImpl , Function: closeConnect, " + e.getMessage());
		}
	}
	
	/**
	 * Lấy connection
	 * 
	 * @return connect kết nối với DB
	 */
	@Override
	public Connection getConnect() {
		return connect;
	}
	/* (non-Javadoc)
	 * @see manageuser.dao.BaseDao#setConnect(java.sql.Connection)
	 */
	@Override
	public void setConnect(Connection connect) {
		this.connect = connect;
	}
	/* (non-Javadoc)
	 * @see manageuser.dao.BaseDao#commit()
	 */
	@Override
	public void commit() {
		try {
			// Nếu chuỗi kết nối khác null
			if (connect != null) {
				// Thực hiện commit
				connect.commit();
			}
		} catch (SQLException e) {
			System.out.println("BaseDaoImpl.commit: " + e.getMessage());
		}
	}
	/* (non-Javadoc)
	 * @see manageuser.dao.BaseDao#rollback()
	 */
	@Override
	public void rollback() {
		try {
			// Nếu chuỗi kết nối khác null
			if (connect != null) {
				// Thực hiện rollback dữ liệu
				connect.rollback();
			}
		} catch (SQLException e) {
			System.out.println("BaseDaoImpl.rollback: " + e.getMessage());
		}
	}
	/* (non-Javadoc)
	 * @see manageuser.dao.BaseDao#setAutoCommit(boolean)
	 */
	@Override
	public void setAutoCommit(boolean isCommit) {
		try {
			// Nếu chuỗi kết nối khác null
			if (connect != null) {
				// Thực hiện autoCommit
				connect.setAutoCommit(isCommit);
			}
		} catch (SQLException e) {
			// xử lý ngoại lệ khi xảy ra lỗi
			System.out.println("BaseDaoImpl.setAutoCommit: " + e.getMessage());
		}
	}
}

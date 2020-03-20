/**
 *  Copy right (C) 2020 Luvina
 * TblDetailUserJapanDaoImpl.java, March 10, 2020 DungPham
 */
package manageruser.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import manageruser.dao.TblDetailUserJapanDao;
import manageruser.dao.Tbl_UserDao;

/**
 * Implement tbl_detail_user_japan 
 * @author DungPham
 *
 */
public class TblDetailUserJapanDaoImpl extends BaseDAOImpl implements TblDetailUserJapanDao {

	@Override
	public int addDetailUserJapan(int userId, String nameLevel, Date startDate, Date endDate, int total) {
		int insertDetail = 0;
		try {
			// mở kết nối
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO tbl_detail_user_japan(user_id, code_level, start_date, end_date, total)");
				sql.append(" VALUES(? , ? , ?, ?, ?);");
				// tạo statement thực hiện query
				PreparedStatement ps = con.prepareStatement(sql.toString());
				int index = 1;
				ps.setInt(index, userId);
				ps.setString(++index, nameLevel);
				ps.setDate(++index, startDate);
				ps.setDate(++index, endDate);
				ps.setInt(++index, total);		
				insertDetail = ps.executeUpdate();
				if (insertDetail == 0) {
					setAutoCommit(false);
					//rollback data
					rollback();
				} 
			} else {
				System.out.println("Connect fail!");
			}
		} catch (SQLException e1) {
			//rollback data
			rollback();
			// in ra ngoại lệ
			System.out.println("Lỗi insert user:" + e1.getMessage());
			// xử lý ngoại lệ
			throw e1;
			// giá trị cuối cùng trả về
		} finally {
			// đóng kết nối
			closeConnect();
			// trả về biến user
			return insertDetail;
		}
	}
	/**
	 * delete detail user japan by user_id
	 * @param userId
	 * @return int > 0 if delete success 
	 * 0 if delete false
	 */
	@Override
	public int deleteDetailUserJapan(int userId) {
		int checkDelte = 0;
		// bắt lỗi
		try {
			// mở kết nối
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {
				// query delete user
				String sql = "DELETE FROM tbl_detail_user_japan WHERE user_id = ?";
				// tạo statement thực hiện query
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, userId);
				setAutoCommit(false);
				checkDelte = ps.executeUpdate();
				if (checkDelte != 0) {
					setAutoCommit(true);
				} else {
					//rollback data
					rollback();
				}
				// kiểm tra nếu kết nối = null
			} else {
				// in ra console thông báo lỗi
				System.out.println("connect fail");
			}
		} catch (SQLException e1) {
			
			// in ra ngoại lệ
			e1.printStackTrace();
			// xử lý ngoại lệ
			throw e1;
			// giá trị cuối cùng trả về
		} finally {
			// đóng kết nối
			closeConnect();
			// trả về biến user
			return checkDelte;
		}
	}
	/**
	 * update detail user japan
	 * @param userId user_id cần thêm vào bảng
	 * @param nameLevel nameLevel chọn từ pulldown hạng mục trình độ tiếng nhật
	 * @param startDate startDate thêm mới
	 * @param endDate endDate thêm mới
	 * @param total total nhập từ bàn phím
	 */
	@Override
	public int updateDetailUserJapan(int userId, String nameLevel, Date startDate, Date endDate, int total) {
		int updateDetail = 0;
		try {
			// mở kết nối
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE tbl_detail_user_japan SET user_id = ?, code_level = ?, start_date = ?, end_date = ?, total = ?");
				sql.append(" WHERE user_id = ?;");
				// tạo statement thực hiện query
				PreparedStatement ps = con.prepareStatement(sql.toString());
				int index = 1;
				ps.setInt(index, userId);
				ps.setString(++index, nameLevel);
				ps.setDate(++index, startDate);
				ps.setDate(++index, endDate);
				ps.setInt(++index, total);
				ps.setInt(++index, userId);
				updateDetail = ps.executeUpdate();
				if (updateDetail == 0) {
					setAutoCommit(false);
					//rollback data
					rollback();
				} 
			} else {
				System.out.println("Connect fail!");
			}
		} catch (SQLException e1) {
			//rollback data
			rollback();
			// in ra ngoại lệ
			System.out.println("Lỗi insert user:" + e1.getMessage());
			// xử lý ngoại lệ
			throw e1;
			// giá trị cuối cùng trả về
		} finally {
			// đóng kết nối
			closeConnect();
			// trả về biến user
			return updateDetail;
		}
	}
		
}

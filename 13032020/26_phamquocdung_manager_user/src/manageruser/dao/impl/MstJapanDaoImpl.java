/**
 * Copy right (C) 2020 Luvina
 * MstJapanDaoImpl.java, 12 Mar 2020 DungPham
 */
package manageruser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import manageruser.dao.MstJapanDao;
import manageruser.entities.mst_group;
import manageruser.entities.mst_japan;

/**
 * Description
 * @author PG
 */
public class MstJapanDaoImpl extends BaseDAOImpl implements MstJapanDao {
	/**
	 * get all list japan level
	 * @return ArrayList<mst_japan>
	 */
	@Override
	public ArrayList<mst_japan> getAllListJapanLevel() {
		ArrayList<mst_japan> listJp = new ArrayList<mst_japan>();
		try {
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {			
				String sql = "SELECT code_level, name_level FROM mst_japan ORDER BY code_level;";
				PreparedStatement ps = con.prepareStatement(sql);
				// khởi tạo biến resultSet để lưu giá trị sau khi thực thi câu query
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// khởi tạo biến mst_japan lưu các giá trị 
					mst_japan item = new mst_japan();
					item.setCode_level(rs.getString("code_level"));
					item.setName_level(rs.getString("name_level"));
					listJp.add(item);
				}
			}
		} catch (SQLException e) {
			System.out.println("Loi:" + e.getMessage());
			throw e;
		} finally {
			closeConnect();
			return listJp;
		}
	}
}

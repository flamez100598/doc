/**
 *  Copy right (C) 2020 Luvina
 * LogoutController.java, Feb 26, 2020 DungPham
 */
package manageruser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import manageruser.dao.MstGroupDao;
import manageruser.entities.mst_group;

/**
 * Cài đặt lớp mst group dao 
 * @author DungPham
 *
 */
public class MstGroupDaoImpl extends BaseDAOImpl implements MstGroupDao {
	/**
	 * @return list group
	 */
	@Override
	public ArrayList<mst_group> getAllGroup() {
		ArrayList<mst_group> listGroup = new ArrayList<mst_group>();
		try {
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {			
				String sql = "SELECT group_id, group_name FROM mst_group order by group_id;";
				PreparedStatement ps = con.prepareStatement(sql);
				// khởi tạo biến resultSet để lưu giá trị sau khi thực thi câu query
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					mst_group item = new mst_group();
					item.setGroup_id(rs.getInt("group_id"));
					item.setGroup_name(rs.getString("group_name"));
					listGroup.add(item);
				}
			}
		} catch (Exception e) {
			System.out.println("Loi get all group:" + e.getMessage());
			throw e;
		} finally {
			closeConnect();
			return listGroup;
		}
	}
	/**
	 * get group by id
	 * @param grId id group need to get
	 * @return mst_group
	 */
	public mst_group getMstGroupById(int grId) {
		mst_group mstGr = null;
		try {
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {			
				mstGr = new mst_group();
				String sql = "SELECT group_id, group_name FROM mst_group WHERE group_id = ?;";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(0, grId);
				// khởi tạo biến resultSet để lưu giá trị sau khi thực thi câu query
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					mstGr.setGroup_id(rs.getInt("group_id"));
					mstGr.setGroup_name(rs.getString("group_name"));
				}
			}
		} catch (SQLException e) {
			System.out.println("Loi get group:" + e.getMessage());
			throw e;
		} finally {
			closeConnect();
			return mstGr;
		}
	}
}

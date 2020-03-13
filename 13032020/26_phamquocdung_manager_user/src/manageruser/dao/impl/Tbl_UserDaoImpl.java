/**
 *  Copy right (C) 2020 Luvina
 * UserDAO.java, Feb 23, 2020 DungPham
 */
package manageruser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Constants;

import manageruser.entities.SortField;
import manageruser.entities.UserInfo;
import manageruser.entities.tbl_user;
import manageruser.utils.Contants;
import manageruser.dao.BaseDao;
import manageruser.dao.Tbl_UserDao;
/**
 * User data access 
 * @author DungPham
 *
 */
public class Tbl_UserDaoImpl extends BaseDAOImpl implements Tbl_UserDao {
	/**
	 * get user by login_name
	 * @param username username need check
	 * @return tbl_userEntity
	 * @throws Exception 
	 */
	@Override
	public tbl_user getTblUserByLoginName(String username) throws Exception {
		// khai báo entity tbl_userEntity
		tbl_user user = null; 
		// bắt lỗi
		try {
			// mở kết nối
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {
				// khởi tạo object tbl_userEntity
				user = new tbl_user();
				// query lấy giá trị login_name, pass, salt
				String sql = "select login_name, password, salt from tbl_user where login_name = ? and rule = ?";
				// tạo statement thực hiện query
				PreparedStatement ps = con.prepareStatement(sql);
				//gắn param cho query
				int index = 1;
				ps.setString(index, username);
				ps.setInt(++index, Contants.RULE_ADMIN);
				// khởi tạo biến resultSet để lưu giá trị sau khi thực thi câu query
				ResultSet rs = ps.executeQuery();
				// duyệt biến rs để lấy giá trị
				while (rs.next()) {
					//check rule admin
					// lưu giá trị login_name lấy được từ db vào biến user
					user.setLogin_name(rs.getString("login_name"));
					// lưu giá trị pass lấy được từ db vào biến user
					user.setPass(rs.getString("password"));
					// lưu giá trị salt lấy được từ db vào biến user
					user.setSal(rs.getString("salt"));
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
			return user;
		}
	}
	/**
	 * @param groupId
	 * @param FullName
	 * @return total rows record user form db
	 */
	@Override
	public int getTotalUser(int groupId, String FullName) {
		int totalUser = 0;
		try {
			StringBuilder fullN = new StringBuilder();
			fullN.append("%");
			fullN.append(FullName);
			fullN.append("%");
			System.out.println(groupId);
			// mở kết nối
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("select  count(*) from tbl_user tu INNER JOIN mst_group mg ON tu.group_id = mg.group_id where tu.rule = 1 ");
				sql.append(" AND tu.full_name LIKE ? ESCAPE '#'");
				if (groupId == 0) {
					sql.append(" AND tu.group_id <> ? ");
				} else {
					sql.append(" AND tu.group_id = ? ");
				}
				// tạo statement thực hiện query
				PreparedStatement ps = con.prepareStatement(sql.toString());
				int index = 1;
				ps.setString(index, fullN.toString());
				ps.setInt(++index, groupId);
				// khởi tạo biến resultSet để lưu giá trị sau khi thực thi câu query
				ResultSet rs = ps.executeQuery();
				// duyệt biến rs để lấy giá trị
				rs.next();
				// lấy giá trị count(*) của câu truy vấn
				totalUser = rs.getInt("count(*)");
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
			return totalUser;
		}
	}
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
	@Override
	public ArrayList<UserInfo> getListUser(int offset, int limit, int groupId, String fullName, String sortType, String sortByFullName, String sortByCodeLevel, String sortByEndDate) {
		StringBuilder fullN = new StringBuilder();
		fullN.append("%");
		fullN.append(fullName);
		fullN.append("%");
		String[] listS = Contants.LIST_SORT;
		ArrayList<SortField> listSort = new ArrayList<SortField>();
		for (int i = 0; i < listS.length; i++) {
			SortField sf = new SortField();
			if (i == 0) {
				sf.setSortName(" tu.full_name ");
				sf.setSortType(sortByFullName);
				listSort.add(sf);
			}
			if (i == 1) {
				sf.setSortName(" mj.name_level ");
				sf.setSortType(sortByCodeLevel);
				listSort.add(sf);
			}
			if (i == 2) {
				sf.setSortName(" tduj.end_date ");
				sf.setSortType(sortByEndDate);
				listSort.add(sf);
			}
		}
		// khai báo entity mảng chứa các user
		ArrayList<UserInfo> listUser = new ArrayList<UserInfo>();
		// bắt lỗi
		try {
			// mở kết nối
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {
				// query lấy giá trị login_name, pass, salt
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT tu.user_id, tu.full_name, birthday, mg.group_name, email, tel, mj.name_level, tduj.end_date, tduj.total FROM tbl_user tu");
				sql.append(" INNER JOIN mst_group mg ON tu.group_id=mg.group_id");
				sql.append(" LEFT JOIN tbl_detail_user_japan tduj ON tduj.user_id = tu.user_id");
				sql.append(" LEFT JOIN mst_japan mj ON mj.code_level = tduj.code_level");
				sql.append(" WHERE tu.rule = 1 ");
				sql.append(" AND tu.full_name LIKE ?");
				if (groupId == 0) {
					sql.append(" AND mg.group_id <> ? ");
				} else {
					sql.append(" AND mg.group_id = ? ");
				}
				sql.append(" ORDER BY ");
				for (int i = 0 ; i < listSort.size(); i++) {
					if (listSort.get(i).getSortName().contains(sortType)) {
						sql.append(listSort.get(i).getSortName());
						sql.append(listSort.get(i).getSortType());
						sql.append(",");
						listSort.remove(i);
						break;
					}
				}
				for (int i = 0 ; i < listSort.size(); i++) {
					sql.append(listSort.get(i).getSortName());
					sql.append(listSort.get(i).getSortType());
					if (i != listSort.size() - 1) {
						sql.append(",");
					}
				}
				sql.append(" LIMIT ? OFFSET ?");
				// tạo statement thực hiện query
				PreparedStatement ps = con.prepareStatement(sql.toString());
				//gắn param cho query
				int index = 1;
				ps.setString(index, fullN.toString());
				ps.setInt(++index, groupId);
				ps.setInt(++index, limit);
				ps.setInt(++index, offset);
				// khởi tạo biến resultSet để lưu giá trị sau khi thực thi câu query
				ResultSet rs = ps.executeQuery();
				// duyệt biến rs để lấy giá trị
				System.out.println(sql.toString());
				while (rs.next()) {
					// khởi tạo object UserInfo
					UserInfo user = new UserInfo();
					// lưu giá trị full_name lấy được từ db vào biến user
					user.setFull_name(rs.getString("full_name"));
					// lưu giá trị user_id lấy được từ db vào biến user
					user.setUser_id(rs.getInt("user_id"));
					// lưu giá trị full_name lấy được từ db vào biến user
					user.setBirthday(rs.getDate("birthday"));
					// lưu giá trị email lấy được từ db vào biến user
					user.setEmail(rs.getString("email"));
					// lưu giá trị end_date lấy được từ db vào biến user
					user.setEnd_date(rs.getDate("end_date"));
					// lưu giá trị group_name lấy được từ db vào biến user
					user.setGroup_name(rs.getString("group_name"));
					// lưu giá trị tel lấy được từ db vào biến user
					user.setTel(rs.getString("tel"));
					// lưu giá trị total lấy được từ db vào biến user
					user.setTotal(rs.getInt("total"));
					// lưu giá trị name_level lấy được từ db vào biến user
					user.setName_level(rs.getString("name_level"));
					listUser.add(user);
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
			return listUser;
		}

	}
	/**
	 * check email exist
	 * @param email email need check 
	 * @return boolean
	 */
	@Override
	public int checkExistEmail(String email) {
		// đếm số bản ghi thu được
		int count = 0;
		// bắt lỗi
		try {
			// mở kết nối
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {
				String sql = "SELECT email FROM tbl_user WHERE email = ?;";
				// tạo statement thực hiện query
				PreparedStatement ps = con.prepareStatement(sql);
				//gắn param cho query
				ps.setString(1, email);
				// khởi tạo biến resultSet để lưu giá trị sau khi thực thi câu query
				ResultSet rs = ps.executeQuery();
				// duyệt biến rs để lấy giá trị
				System.out.println(sql);
				while (rs.next()) {	
					count++;
				}
				// kiểm tra nếu kết nối = null
			} else {
				// in ra console thông báo lỗi
				System.out.println("connect fail");
			}
		} catch (SQLException e1) {
			// in ra ngoại lệ
			System.out.println("Lỗi get email:" + e1.getMessage());
			e1.printStackTrace();
			// xử lý ngoại lệ
			throw e1;
			// giá trị cuối cùng trả về
		} finally {
			// đóng kết nối
			closeConnect();
			// trả về biến user
			return count;
		}
	}

}

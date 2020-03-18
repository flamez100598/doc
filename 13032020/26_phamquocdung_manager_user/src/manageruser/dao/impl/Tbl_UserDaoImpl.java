/**
 *  Copy right (C) 2020 Luvina
 * UserDAO.java, Feb 23, 2020 DungPham
 */
package manageruser.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Constants;
import com.mysql.jdbc.Statement;

import manageruser.entities.SortField;
import manageruser.entities.UserInfo;
import manageruser.entities.tbl_user;
import manageruser.utils.Common;
import manageruser.utils.Contants;
import manageruser.validates.Validator;
import manageruser.dao.BaseDao;
import manageruser.dao.TblDetailUserJapanDao;
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
		// begin-- khởi tạo giá trị fullName
		StringBuilder fullN = new StringBuilder();
		fullN.append("%");
		fullN.append(fullName);
		fullN.append("%");
		// end-- khởi tạo giá trị fullName
		// begin-- khởi tạo các trường được sort
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
		// end-- khởi tạo các trường được sort
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
				// query lấy giá trị UserInfo
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
				// begin-- sắp xếp thứ tự sort ưu tiên theo sortType
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
				// end-- sắp xếp thứ tự sort ưu tiên theo sortType
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
	public int checkExistEmail(String email, int userId) {
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
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT email FROM tbl_user WHERE email = ?");
				if (userId != 0) {
					sql.append(" AND user_id <> ?;");
				}
				// tạo statement thực hiện query
				PreparedStatement ps = con.prepareStatement(sql.toString());
				//gắn param cho query
				ps.setString(1, email);
				if (userId != 0) {
					ps.setInt(2, userId);
				}
				// khởi tạo biến resultSet để lưu giá trị sau khi thực thi câu query
				ResultSet rs = ps.executeQuery();
				// duyệt biến rs để lấy giá trị
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
			// trả về biến count
			return count;
		}
	}
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
	 * @param nameLevel nameLevel chọn từ pulldown hạng mục trình độ tiếng nhật
	 * @param startDate startDate thêm mới
	 * @param endDate endDate thêm mới
	 * @param total total nhập từ bàn phím
	 * @return tbl_user user nếu insert thành công
	 */
	@Override
	public int AddUser(String loginName, int groupId, String fullName, String fullNameKata, Date birthDay,
			String email, String tel, String password, String nameLevel, Date startDate,
			Date endDate, int total, String salt) {
		// khởi tạo insert count
		int insertCount = 0;
		// bắt lỗi
		try {
			// mở kết nối
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO `tbl_user`(`group_id`,`login_name`,`password`,`full_name`,`full_name_kana`,`email`,`tel`,`birthday`,`salt`,`rule`)");
				sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				// tạo statement thực hiện query
				PreparedStatement ps = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				//gắn param cho query
				int index = 1;
				ps.setInt(index, groupId);
				ps.setString(++index, loginName);
				ps.setString(++index, password);
				ps.setString(++index, fullName);
				ps.setString(++index, fullNameKata);
				ps.setString(++index, email);
				ps.setString(++index, tel);
				ps.setDate(++index, birthDay);
				ps.setString(++index, salt);
				ps.setInt(++index, Contants.RULE_CLIENT);
				setAutoCommit(false);
				insertCount = ps.executeUpdate();
				int userIdInserted = 0;
				// nếu insert thành công
				if (insertCount == 1) {
					setAutoCommit(true);
					ResultSet rs = ps.getGeneratedKeys();
					if(rs.next()) {
						userIdInserted = rs.getInt(1); 
					}
					if (Validator.isNotNull(nameLevel)) {
						TblDetailUserJapanDao tblDUJ = new TblDetailUserJapanDaoImpl();
						int updateUserJapan = tblDUJ.addDetailUserJapan(userIdInserted, nameLevel, startDate, endDate, total);
					}
				} else {
					setAutoCommit(false);
					//rollback data
					rollback();
				}
				// kiểm tra nếu kết nối = null
			} else {
				// in ra console thông báo lỗi
				System.out.println("connect fail");
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
			return insertCount;
		}
	}
	/**
	 * get user by user_id
	 * @param userId need check
	 * @return tbl_userE
	 * @throws Exception 
	 */
	@Override
	public UserInfo getUserById(int userId) throws SQLException, Exception {
		// khai báo entity tbl_userEntity
		UserInfo user = new UserInfo();
		// bắt lỗi
		try {
			// mở kết nối
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {
				// query lấy giá trị UserInfo
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT mj.code_level, tu.group_id, tu.full_name_kana, tu.login_name, tu.user_id, tu.full_name, birthday, mg.group_name, email, tduj.start_date, tel, mj.name_level, tduj.end_date, tduj.total, tduj.detail_user_japan_id FROM tbl_user tu");
				sql.append(" INNER JOIN mst_group mg ON tu.group_id = mg.group_id");
				sql.append(" LEFT JOIN tbl_detail_user_japan tduj ON tduj.user_id = tu.user_id");
				sql.append(" LEFT JOIN mst_japan mj ON mj.code_level = tduj.code_level");
				sql.append(" WHERE tu.rule = 1 ");
				sql.append(" AND tu.user_id = ?");
				// tạo statement thực hiện query
				PreparedStatement ps = con.prepareStatement(sql.toString());
				ps.setInt(1, userId);
				// khởi tạo biến resultSet để lưu giá trị sau khi thực thi câu query
				ResultSet rs = ps.executeQuery();
				// duyệt biến rs để lấy giá trị
				while (rs.next()) {
					user.setUser_id(rs.getInt("user_id"));
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
					// lưu giá trị start_date lấy được từ db
					user.setStart_date(rs.getDate("start_date"));
					// lưu giá trị login_name
					user.setLogin_name(rs.getString("login_name"));
					// luư giá trị full_name_kana
					user.setFull_name_kana(rs.getString("full_name_kana"));
					// luư giá trị group_id
					user.setGroup_id(rs.getInt("group_id"));
					// luư giá trị code_level
					user.setCode_level(rs.getString("code_level"));
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
	 * get user by login_name
	 * @param userId check for updateUser
	 * @param loginName loginName need to get user
	 * @return tbl_user
	 */
	@Override
	public tbl_user getUserByLoginName(int userId, String loginName) {
		userId = 0;
		tbl_user user = new tbl_user();
		// bắt lỗi
		try {
			// mở kết nối
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {
				// query lấy giá trị UserInfo
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT login_name FROM tbl_user WHERE loginName = ?");
				if (userId != 0) {
					sql.append(" AND userId <> ?");
				}
				// tạo statement thực hiện query
				PreparedStatement ps = con.prepareStatement(sql.toString());
				int index = 1;
				ps.setString(1, loginName);
				if (userId != 0) {
					ps.setInt(++index, userId);
				}
				// khởi tạo biến resultSet để lưu giá trị sau khi thực thi câu query
				ResultSet rs = ps.executeQuery();
				// duyệt biến rs để lấy giá trị
				while (rs.next()) {
					user.setLogin_name(rs.getString("login_name"));
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
	 * delete user by user_id
	 * @param userId need to delete user
	 * @return int 0 if delete false
	 *  1 if delete succes
	 */
	@Override
	public int deleteUser(int userId) {
		int checkDelte = 0;
		// bắt lỗi
		try {
			// mở kết nối
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {
				TblDetailUserJapanDao tblDUJ = new TblDetailUserJapanDaoImpl();
				tblDUJ.deleteDetailUserJapan(userId);	
				// query delete user
				String sql = "DELETE FROM tbl_user WHERE user_id = ?";
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
	 * update user 
	 * @param userId user_id cuar user can update
	 * @param groupId groupId chọn từ ô pulldown
	 * @param fullName fullName nhập từ bàn phím
	 * @param fullNameKata fullNameKata nhập từ bàn phím
	 * @param birthDay birthDay thêm mới
	 * @param email email nhập từ bàn phím
	 * @param tel tel nhập từ bàn phím
	 * @return int 1 is insert success
	 * 0 if insert false
	 */
	@Override
	public int updateUser(int userId, int groupId, String fullName, String fullNameKata,
			Date birthDay, String email, String tel) {
		int isUpdate = 0;
		// bắt lỗi
		try {
			// mở kết nối
			openConnect();
			// lấy giá trị connection sau khi kết nối
			Connection con = (Connection) getConnect();
			// kiểm tra nếu kết nối khác null
			if (con != null) {
				// query delete user
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE tbl_user SET group_id = ?, full_name = ?, email = ?,");
				sql.append(" full_name_kata = ?, tel = ?, birthDay = ? WHERE user_id = ?");
				// tạo statement thực hiện query
				PreparedStatement ps = con.prepareStatement(sql.toString());
				int index = 1;
				ps.setInt(index, groupId);
				ps.setString(++index, fullName);
				ps.setString(++index, email);
				ps.setString(++index, fullNameKata);
				ps.setString(++index, tel);
				ps.setDate(++index, birthDay);
				ps.setInt(++index, userId);
				setAutoCommit(false);
				isUpdate = ps.executeUpdate();
				if (isUpdate != 0) {
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
			return isUpdate;
		}
	}
}

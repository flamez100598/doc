/**
 *  Copy right (C) 2020 Luvina
 * loginValidate.java, Feb 24, 2020 DungPham
 */
package manageruser.validates;

import java.sql.SQLException;
import java.util.ArrayList;

import manageruser.logics.Tbl_UserLogic;
import manageruser.logics.impl.Tbl_UserLogicImpl;
import manageruser.utils.MessageErrorProperties;

/**
 * Validate login 
 * @author DungPham
 *
 */
public class loginValidate {
	public static ArrayList<String> checkNullOrEmpty(String username, String password) {
		Tbl_UserLogic tbl_uli = new Tbl_UserLogicImpl();
		ArrayList<String> listError = new ArrayList<String>();
		if("".equals(username)) {
			// thêm message lỗi MSG006 trong file MessageError.Properties vào messError
			listError.add(MessageErrorProperties.getValueByKey("ER00101"));
		} 
		if ("".equals(password)) {
			listError.add(MessageErrorProperties.getValueByKey("ER00102"));
		}
		if (listError.size() == 0) {
			// gắn message từ file MessageError.Properties
			try {
				// kiểm tra nếu có tài khoản trong db
				if(!tbl_uli.existLogin(username, password)) {
					// set sesion cho login_user
					listError.add(MessageErrorProperties.getValueByKey("ER016"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		// tra ve chuoi loi~
		return listError;
	}
}

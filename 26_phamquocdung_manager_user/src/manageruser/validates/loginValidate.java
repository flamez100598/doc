/**
 *  Copy right (C) 2020 Luvina
 * MessageErrorProperties.java, Feb 24, 2020 DungPham
 */
package manageruser.validates;

import manageruser.utils.MessageErrorProperties;

/**
 * Validate login 
 * @author DungPham
 *
 */
public class loginValidate {
	public static String checkNullOrEmpty(String username, String password) {
		StringBuilder messError = new StringBuilder();
		if("".equals(username)) {
			// thêm message lỗi MSG006 trong file MessageError.Properties vào messError
			messError.append("<p style='margin:0;'>" + MessageErrorProperties.getValueByKey("ER00101") + "</p>");
		} else if ("".equals(password)) {
			messError.append("<p style='margin:0;'>" + MessageErrorProperties.getValueByKey("ER00102") + "</p>");
		}
		// tra ve chuoi loi~
		return messError.toString();
	}
}

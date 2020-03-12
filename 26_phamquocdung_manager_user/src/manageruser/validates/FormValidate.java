/**
 * Copy right (C) 2020 Luvina
 * ValidateForm.java, 12 Mar 2020 DungPham
 */
package manageruser.validates;

import java.util.ArrayList;
import java.util.Date;

import manageruser.dao.MstGroupDao;
import manageruser.dao.impl.MstGroupDaoImpl;
import manageruser.utils.MessageErrorProperties;

/**
 * validate form
 * @author DungPham
 */
public class FormValidate {
	public static ArrayList<String> checkFormAddEdit(String loginName, String groupId, String fullName, String fullNameKata,
			int yearBirth, int monthBirth, int dateBirth, String email, String tel, String password, String reWritePass, String nameLevel, 
			Date startDate, Date endDate, int total) {
		ArrayList<String> listErr = new ArrayList<String>();
		// -- validate login_name
		if (Validator.isNull(loginName)) {
			listErr.add(MessageErrorProperties.getValueByKey("ER001_LOGIN_NAME"));
		} else {
			char[] c = loginName.toCharArray();
			if (Validator.isDigit(c[0]) || Validator.isDigitAndChar(fullName)) {
				listErr.add(MessageErrorProperties.getValueByKey("ER019"));
			} 
			if(c.length > 15 || c.length < 4) {
				listErr.add(MessageErrorProperties.getValueByKey("ER007_LOGIN_NAME"));
			}
		}
		// -- end validate login_name
		// -- validate group name
		MstGroupDao mstGrd = new MstGroupDaoImpl();
		if (Validator.isNull(groupId)) {
			listErr.add(MessageErrorProperties.getValueByKey("ER001_GROUP_NAME"));
		} else if (mstGrd.getMstGroupById(Integer.parseInt(groupId)) != null) {
			listErr.add(MessageErrorProperties.getValueByKey("ER004_GROUP_NAME"));
		}
		// -- end validate group name
		// -- validate full name
		if (Validator.isNull(fullName)) {
			listErr.add(MessageErrorProperties.getValueByKey("ER001_FULL_NAME"));
		} else if (fullName.length() > 255) {
			listErr.add(MessageErrorProperties.getValueByKey("ER006_FULL_NAME"));
		}
		// -- end validate group name
		// -- validate katakana name
		if (Validator.isNull(fullNameKata)) {
			listErr.add(MessageErrorProperties.getValueByKey("ER001_FULL_NAME_KATA"));
		} else {
			if (fullName.length() > 255) {
				listErr.add(MessageErrorProperties.getValueByKey("ER006_FULL_NAME"));
			}
			if (Validator.isKatakana(fullNameKata)) {
				listErr.add(MessageErrorProperties.getValueByKey("ER009_FULL_NAME_KATA"));
			}
		}
		if (monthBirth == 2 || monthBirth == 4 || monthBirth == 6 || monthBirth == 9 || monthBirth == 11) {
			if (dateBirth > 30) {
				listErr.add(MessageErrorProperties.getValueByKey("ER011_BIRTH_DAY"));		
			}
			else if (monthBirth == 2) {
				if ((yearBirth % 4 != 0 && yearBirth % 100 == 0) && (yearBirth % 400 != 0)) {
					if (dateBirth > 28) {
						listErr.add(MessageErrorProperties.getValueByKey("ER011_BIRTH_DAY"));	
					}
				}
			}
		} 
		// -- end validate katakana name
		return listErr;

	}
}

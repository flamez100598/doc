/**
 * Copy right (C) 2020 Luvina
 * ValidateForm.java, 12 Mar 2020 DungPham
 */
package manageruser.validates;

import java.util.ArrayList;
import java.util.Date;

import manageruser.dao.MstGroupDao;
import manageruser.dao.impl.MstGroupDaoImpl;
import manageruser.utils.Common;
import manageruser.utils.MessageErrorProperties;

/**
 * validate form
 * @author DungPham
 */
public class FormValidate {
	/**
	 * Validate from add or edit 
	 * @param loginName login_name nhập từ bàn phím
	 * @param groupId groupId chọn từ ô pulldown
	 * @param fullName fullName nhập từ bàn phím
	 * @param fullNameKata fullNameKata nhập từ bàn phím
	 * @param yearBirth yearBirth chọn từ pulldown của hạng mục sinh nhật
	 * @param monthBirth monthBirth chọn từ pulldown của hạng mục sinh nhật
	 * @param dateBirth dateBirth chọn từ pulldown của hạng mục sinh nhật
	 * @param email email nhập từ bàn phím
	 * @param tel tel nhập từ bàn phím
	 * @param password password nhập từ bàn phím
	 * @param reWritePass nhập lại của hạng mục password nhập từ bàn phím
	 * @param nameLevel nameLevel chọn từ pulldown hạng mục trình độ tiếng nhật
	 * @param yearStartDate chọn từ pulldown của hạng mục start date
	 * @param monthStartDate chọn từ pulldown của hạng mục start date
	 * @param dateStartDate chọn từ pulldown của hạng mục start date
	 * @param yearEndDate chọn từ pulldown của hạng mục end date
	 * @param monthEndDate chọn từ pulldown của hạng mục end date
	 * @param dateEndDate chọn từ pulldown của hạng mục end date
	 * @param total total nhập từ bàn phím
	 * @return ArrayList<String> listError nếu sai validate
	 */
	public static ArrayList<String> checkFormAddEdit(String loginName, String groupId, String fullName, String fullNameKata,
			int yearBirth, int monthBirth, int dateBirth, String email, String tel, String password, String reWritePass, String nameLevel, 
			int yearStartDate, int monthStartDate, int dateStartDate,
			int yearEndDate, int monthEndDate, int dateEndDate, String total, int userId, boolean isUpdate) {
		ArrayList<String> listErr = new ArrayList<String>();
		// -- validate login_name
		if (!isUpdate) {
			if (Validator.isNull(loginName)) {
				listErr.add(MessageErrorProperties.getValueByKey("ER001_LOGIN_NAME"));
			} else {
				char[] c = loginName.toCharArray();
				if (Validator.isDigit(c[0]) && !Validator.isDigitAndChar(fullName)) {
					listErr.add(MessageErrorProperties.getValueByKey("ER019"));
				} 
				if (c.length > 15 || c.length < 4) {
					listErr.add(MessageErrorProperties.getValueByKey("ER007_LOGIN_NAME"));
				}
			}
		}
		// -- end validate login_name
		// -- validate group name
		MstGroupDao mstGrd = new MstGroupDaoImpl();
		int grId = Integer.parseInt(groupId);
		if (grId == 0) {
			listErr.add(MessageErrorProperties.getValueByKey("ER001_GROUP_NAME"));
		} else if (mstGrd.getMstGroupById(grId) == null) {
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
		// -- end validate katakana name
		// -- validate Date of birth
		String messErrorDOB = Validator.checkMonthAndDate(yearBirth, monthBirth, dateBirth, "BIRTH_DAY");
		if(Validator.isNotNull(messErrorDOB)) {
			listErr.add(messErrorDOB);
		}
		// --end validate Date of birth
		// -- validate email
		if (Validator.isNull(email)) {
			listErr.add(MessageErrorProperties.getValueByKey("ER001_EMAIL"));
		} else {
			// check tồn tại
			if (email.length() > 100) {
				listErr.add(MessageErrorProperties.getValueByKey("ER006_EMAIL"));
			}		
			// check exist
			if (!Validator.checkExistEmail(email, userId)) {
				listErr.add(MessageErrorProperties.getValueByKey("ER003_EMAIL"));
			}
			// check format
			if (!Validator.checkFormatEmail(email)) {
				listErr.add(MessageErrorProperties.getValueByKey("ER005_EMAIL"));
			}
		}
		// -- end validate email
		// -- validate tel
		// check không tồn tại
		if (Validator.isNull(tel)) {
			listErr.add(MessageErrorProperties.getValueByKey("ER001_TEL"));
		} else {		
			// check format
			if (!Validator.checkFormatTel(tel)) {
				listErr.add(MessageErrorProperties.getValueByKey("ER005_TEL"));
			}
		}
		// -- end validate tel
		// -- end validate katakana name
		// -- validate password
		if (!isUpdate) {
			if (Validator.isNull(password)) {
				listErr.add(MessageErrorProperties.getValueByKey("ER001_PASSWORD"));
			} else {
				if (Validator.isHalfsize(password)) {
					listErr.add(MessageErrorProperties.getValueByKey("ER008_PASSWORD"));
				}
				if (password.length() > 15 || password.length() < 4) {
					listErr.add(MessageErrorProperties.getValueByKey("ER007_PASSWORD"));
				}
				// -- validate re-password
				if (!Common.CompareString(password, reWritePass)) {
					listErr.add(MessageErrorProperties.getValueByKey("ER001_RE_PASSWORD"));
				} 
				// -- end validate re-password
			}
		}
		// -- end validate password
		if (Validator.isNotNull(nameLevel)) {
			// -- validate Start Date
			String messErrorStartDate = Validator.checkMonthAndDate(yearBirth, monthBirth, dateBirth, "START_DATE");
			if(Validator.isNotNull(messErrorStartDate)) {
				listErr.add(messErrorStartDate);
			}
			// --end validate Start Date
			// -- validate Start Date
			String messErrorEndDate = Validator.checkMonthAndDate(yearBirth, monthBirth, dateBirth, "END_DATE");
			if(Validator.isNotNull(messErrorEndDate)) {
				listErr.add(messErrorEndDate);
			}
			// --end validate Start Date
			// -- validate total point
			if(Validator.isNull(total)) {
				listErr.add(MessageErrorProperties.getValueByKey("ER001_TOTAL"));
			} else {
				if (!Validator.isHalfsize(total)) {
					listErr.add(MessageErrorProperties.getValueByKey("ER018_TOTAL"));
				}
				if (total.length() > 11) {
					listErr.add(MessageErrorProperties.getValueByKey("ER006_TOTAL"));
				}
			}
			// --end validate total point
		}
		return listErr;

	}
}

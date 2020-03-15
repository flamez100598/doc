/**
 *  Copy right (C) 2020 Luvina
 * Validator.java, Feb 24, 2020 DungPham
 */
package manageruser.validates;

import java.util.regex.Pattern;

import manageruser.dao.Tbl_UserDao;
import manageruser.dao.impl.Tbl_UserDaoImpl;
import manageruser.utils.MessageErrorProperties;
import manageruser.utils.StringPool;

/**
 * Validate form add and edit
 * @author DungPham
 */
public class Validator {

	/**
	 * kiểm tra kí tự có phải là chữ không
	 * 
	 * @param c kí tự cần kiểm tra
	 * @return true nếu là chữ
	 * false nếu không phải là chữ
	 */
	public static boolean isChar(char c) {
		return Character.isLetter(c);
	}

	/**
	 * kiểm tra có phải là chuỗi kí tự không
	 * 
	 * @param s chuỗi cần kiểm tra
	 * @return true nếu chuỗi là chuỗi kí tự
	 * false nếu chuỗi không phải chuỗi kí tự
	 */  
	public static boolean isChar(String s) {
		if (isNull(s)) {
			return false;
		} 
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++) {
			// kiểm tra xem kí tự [i] trong chuỗi không là kí tự chữ 
			if (!isChar(c[i])) {
				// trả về false nếu không phải là kí tự chữ
				return false;
			}
		}
		return true;
	}

	/**
	 * Kiểm tra xem kí tự có phải là số không
	 * 
	 * @param c kí tự cần kiểm tra
	 * @return true nếu là kí tự số
	 * false nếu không phải kí tự số
	 */
	public static boolean isDigit(char c) {
		// ép kiểu về số thứ tự trong bảng mã ASCII
		int x = (int) c;
		// kiểm tra xem có phải thuộc mã tự '0' đến '9' trong ASCII không
		if ((x >= 48) && (x <= 57)) {
			return true;
		}
		return false;
	}

	/**
	 * Kiểm tra xem chuỗi có phải là chuỗi số không
	 * 
	 * @param s chuỗi cần kiểm tra
	 * @return true nếu chuỗi là số
	 * flase nếu chuỗi không phải là số
	 */
	public static boolean isDigit(String s) {
		if (isNull(s)) {
			return false;
		}
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++) {
			// nếu kí tự trong chuỗi không phải là số
			if (!isDigit(c[i])) {
				// trả về false
				return false;
			}
		}
		// nếu chuỗi là số trả về true
		return true;
	}
	/**
	 * Kiểm tra xem chuỗi có phải là chuỗi có chứa kí tự đặc biệt không
	 * 
	 * @param s chuỗi cần kiểm tra
	 * @return true nếu chuỗi không chứa kí tự đặc biệt
	 * flase nếu chuỗi chứa kí tự đặc biệt
	 */
	public static boolean isDigitAndChar(String s) {
		if (isNull(s)) {
			return false;
		}
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++) {
			// nếu kí tự trong chuỗi không phải là số và chữ
			if (!isDigit(c[i]) && !isChar(c[i])) {
				// trả về false
				return false;
			}
		}
		// nếu chuỗi là số trả về true
		return true;
	}
	/**
	 * kiểm tra chuỗi có phải là chuỗi rỗng hoặc null không
	 * 
	 * @param s chuỗi cần kiểm tra
	 * @return true nếu là chuỗi rỗng hoặc null
	 * false nếu chuỗi không rỗng hoặc khác null 
	 */
	public static boolean isNull(String s) {
		if (s == null) {
			return true;
		}
		s = s.trim();
		if ((s.equals(StringPool.NULL)) || (s.equals(StringPool.BLANK))) {
			return true;
		}
		return false;
	}
	/**
	 * kiểm tra chuỗi có khác null không
	 * 
	 * @param s chuỗi cần kiểm tra
	 * @return true nếu chuỗi khác null hoặc rỗng
	 */
	public static boolean isNotNull(String s) {
		return !isNull(s);
	}

	/**
	 * kiểm tra xem có phải chuỗi katakana không
	 * 
	 * @param s chuỗi cần kiểm tra
	 * @return true nếu là chuỗi katakana
	 * false nếu không phải là chuỗi katakana
	 */    
	public static boolean isKatakana(String text) {
		char[] c = text.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if ((Character.UnicodeBlock.of(c[i]) != Character.UnicodeBlock.KATAKANA) && (!isDigit(c[i]))
					&& (!Character.isWhitespace(c[i]))) {

				return false;
			}
		}
		return true;
	}

	/**
	 * kiểm tra chuỗi haftsize
	 * @param text chuỗi cân
	 * @return true nếu là chuỗi haft size
	 * false nếu không phải chuỗi haft size
	 */    
	public static boolean isHalfsize(String text) {
		return Pattern.matches("[0-9]+", text);
	}


	/**
	 * kiểm tra chuỗi có kí tự 2 byte
	 * @param text chuỗi cần kiểm tra
	 * @return true nếu chuỗi có k
	 */    
	public static boolean isTwoByteCharater(String text) {
		char[] c = text.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (!isJapanese(c[i]) &&
					/*(!isDigit(c[i])) &&*/
					(!Character.isWhitespace(c[i]))) {

				return false;
			}
		}
		return true;
	}

	/**
	 * Kiểm tra có phải là kí tự 2 byte không
	 * 
	 * @param c kí tự cần kiểm tra
	 * @return true nếu là kí tự 2 byte
	 * false nếu không phải kí tự 2 byte
	 */    
	public static boolean isJapanese(char c) {
		// katakana:
		if (c >= '\u30a0' && c <= '\u30ff')
			return true;
		if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.KATAKANA)
			return true;
		// hiragana
		if (c >= '\u3040' && c <= '\u309f')
			return true;
		if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HIRAGANA)
			return true;
		// CJK Unified Ideographs
		if (c >= '\u4e00' && c <= '\u9fff')
			return true;
		// CJK symbols & punctuation
		if (c >= '\u3000' && c <= '\u303f')
			return true;
		// KangXi (kanji)
		if (c >= '\u2f00' && c <= '\u2fdf')
			return true;
		// KanBun
		if (c >= '\u3190' && c <= '\u319f')
			return true;
		// CJK Unified Ideographs Extension A
		if (c >= '\u3400' && c <= '\u4db5')
			return true;
		// CJK Compatibility Forms
		if (c >= '\ufe30' && c <= '\ufe4f')
			return true;
		// CJK Compatibility
		if (c >= '\u3300' && c <= '\u33ff')
			return true;
		// CJK Radicals Supplement
		if (c >= '\u2e80' && c <= '\u2eff')
			return true;
		// other character..  return false;
		return false;
	}
	/**
	 * Check exist email
	 * @param str email need check
	 * @return true if email not exist
	 * false if email exist
	 */
	public static boolean checkExistEmail(String str) {
		Tbl_UserDao tud = new Tbl_UserDaoImpl();
		if (tud.checkExistEmail(str) > 0) {
			return false;
		}
		return true;
	}
	/**
	 * Check format email
	 * @param str email need check
	 * @return true if email format right
	 * false if format wrong
	 */
	public static boolean checkFormatEmail(String str) {
		char[] c = str.toCharArray();
		int d = 0;
		for (int i = 0; i < c.length - 1; i++) {
			if (!isDigit(c[i]) && !isChar(c[i]) && c[i] != '@') {
				return false;
			}
			if (c[i] == '@') {
				int j = i + 1;
				while (c[j] != '.' && j < c.length - 2) { 
					if (!isDigit(c[j]) && !isChar(c[j])) {
						return false;
					}
					j++;
				}
				i = j;
			}
			if (c[i] == '.') {
				for (int j = i + 1; j < c.length; j++) {
					if (!isDigit(c[j]) && !isChar(c[j])) {
						return false;
					}
				}
				break;
			}
		}
		return true;
	}
	/**
	 * check format tel
	 * @param tel need check
	 * @return true if format right
	 * false if format wrong
	 */
	public static boolean checkFormatTel(String tel) {
		char[] c = tel.toCharArray();
		int d = 0, countChar = 0;
		for (int i = 0; i < c.length; i++) {
			if (isDigit(c[i])) {
				d++;
			} else if (c[i] == '-') {
				if (d != 4) {
					return false;
				}
				countChar++;
				d = 0;
			} else {
				return false;
			}
		}
		if (d == 4 && countChar > 1) {
			return true;
		}
		return false;
	}
	/**
	 * Message check month and date 
	 * @param y year need check
	 * @pram m month need check
	 * @param d day need check
	 * @param codeErr code error 
	 * @return String error message if have error
	 */
	public static String checkMonthAndDate(int y, int m, int d, String codeErr) {
		StringBuilder errorMes = new StringBuilder();
		if (m == 2 || m == 4 || m == 6 || m == 9 || m == 11) {
			if (d > 30) {
				errorMes.append(MessageErrorProperties.getValueByKey("ER011_" + codeErr));		
			}
			else if (m == 2) {
				if ((y % 4 != 0 && y % 100 == 0) && (y % 400 != 0)) {
					if (d > 28) {
						errorMes.append(MessageErrorProperties.getValueByKey("ER011_" + codeErr));	
					}
				}
			}
		} 
		return errorMes.toString();
	}

}

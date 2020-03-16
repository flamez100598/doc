/**
 *  Copy right (C) 2020 Luvina
 * tbl_user.java, Feb 23, 2020 DungPham
 */
package manageruser.entities;

import java.util.Date;

/**
 * Lớp để lưu trữ thông tin nhân viên
 * @author DungPham
 *
 */
public class UserInfo {
	private int user_id;
	private String login_name;
	private String full_name;
	private Date birthday;
	private String group_name;
	private String email;
	private String tel;
	private String name_level;
	private Date end_date;
	private int total;
	private int detail_user_japan_id;
	private Date start_date;
	private String full_name_kana;
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}
	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	/**
	 * @return the full_name
	 */
	public String getFull_name() {
		return full_name;
	}
	/**
	 * @param full_name the full_name to set
	 */
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	/**
	 * @return the group_name
	 */
	public String getGroup_name() {
		return group_name;
	}
	/**
	 * @param group_name the group_name to set
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	/**
	 * @return the name_level
	 */
	public String getName_level() {
		return name_level;
	}
	/**
	 * @param name_level the name_level to set
	 */
	public void setName_level(String name_level) {
		this.name_level = name_level;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the detail_user_japan_id
	 */
	public int getDetail_user_japan_id() {
		return detail_user_japan_id;
	}
	/**
	 * @param detail_user_japan_id the detail_user_japan_id to set
	 */
	public void setDetail_user_japan_id(int detail_user_japan_id) {
		this.detail_user_japan_id = detail_user_japan_id;
	}
	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	/**
	 * @return the start_date
	 */
	public Date getStart_date() {
		return start_date;
	}
	/**
	 * @return the login_name
	 */
	public String getLogin_name() {
		return login_name;
	}
	/**
	 * @param login_name the login_name to set
	 */
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	/**
	 * @return the full_name_kana
	 */
	public String getFull_name_kana() {
		return full_name_kana;
	}
	/**
	 * @param full_name_kana the full_name_kana to set
	 */
	public void setFull_name_kana(String full_name_kana) {
		this.full_name_kana = full_name_kana;
	}
}

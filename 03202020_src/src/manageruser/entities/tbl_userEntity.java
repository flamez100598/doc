/**
 *  Copy right (C) 2020 Luvina
 * tbl_user.java, Feb 23, 2020 DungPham
 */
package manageruser.entities;

import java.util.Date;

/**
 * entity tbl_user in db
 * @author DungPham
 *
 */
public class tbl_userEntity {
	private int user_id;
	private int group_id;
	private int rule;
	private String login_name;
	private String pass;
	private String full_name;
	private String full_name_kana;
	private String email;
	private String tel;
	private Date birthday;
	private String sal;
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the full_name
	 */
	public String getFull_name() {
		return full_name;
	}
	/**
	 * @return the full_name_kana
	 */
	public String getFull_name_kana() {
		return full_name_kana;
	}
	/**
	 * @return the group_id
	 */
	public int getGroup_id() {
		return group_id;
	}
	/**
	 * @return the login_name
	 */
	public String getLogin_name() {
		return login_name;
	}
	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}/**
	 * @return the rule
	 */
	public int getRule() {
		return rule;
	}/**
	 * @return the sal
	 */
	public String getSal() {
		return sal;
	}/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}/**
	 * @param full_name the full_name to set
	 */
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}/**
	 * @param full_name_kana the full_name_kana to set
	 */
	public void setFull_name_kana(String full_name_kana) {
		this.full_name_kana = full_name_kana;
	}/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}/**
	 * @param login_name the login_name to set
	 */
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}/**
	 * @param rule the rule to set
	 */
	public void setRule(int rule) {
		this.rule = rule;
	}/**
	 * @param sal the sal to set
	 */
	public void setSal(String sal) {
		this.sal = sal;
	}/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}

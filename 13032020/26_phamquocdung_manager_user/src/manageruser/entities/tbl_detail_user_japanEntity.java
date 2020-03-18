/**
 *  Copy right (C) 2020 Luvina
 * tbl_detail_user_japanEntity.java, Feb 23, 2020 DungPham
 */
package manageruser.entities;

import java.util.Date;

/**
 * entity tbl_detail_user_japan in db
 * @author DungPham
 *
 */
public class tbl_detail_user_japanEntity {
	private int detail_user_japan_id;
	private int user_id;
	private Date start_date;
	private Date end_date;
	private int total;
	/**
	 * @return the detail_user_japan_id
	 */
	public int getDetail_user_japan_id() {
		return detail_user_japan_id;
	}/**
	 * @return the end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}/**
	 * @return the start_date
	 */
	public Date getStart_date() {
		return start_date;
	}/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}/**
	 * @param detail_user_japan_id the detail_user_japan_id to set
	 */
	public void setDetail_user_japan_id(int detail_user_japan_id) {
		this.detail_user_japan_id = detail_user_japan_id;
	}/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}

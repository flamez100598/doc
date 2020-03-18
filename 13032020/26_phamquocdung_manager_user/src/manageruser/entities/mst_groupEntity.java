/**
 *  Copy right (C) 2020 Luvina
 * mst_group.java, Feb 23, 2020 DungPham
 */
package manageruser.entities;

/**
 * mst_group in db
 * @author DungPham
 *
 */
public class mst_groupEntity {
	private int group_id;
	private String group_name;
	/**
	 * @return the group_id
	 */
	public int getGroup_id() {
		return group_id;
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
	}/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
}

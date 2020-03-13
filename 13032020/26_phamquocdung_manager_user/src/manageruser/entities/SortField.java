/**
 *  Copy right (C) 2020 Luvina
 * mst_japan.java, Feb 23, 2020 DungPham
 */
package manageruser.entities;
/**
 * SortField set sort field
 * @author DungPham
 *
 */
public class SortField {
	private String sortName;
	private String sortType;
	/**
	 * @return the sort name
	 */
	public String getSortName() {
		return sortName;
	}
	/**
	 * @return the sort type
	 */
	public String getSortType() {
		return sortType;
	}
	/**
	 * @param sortName the sortName to set
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
}

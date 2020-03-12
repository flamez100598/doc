/**
 *  Copy right (C) 2020 Luvina
 * LogoutController.java, Feb 26, 2020 DungPham
 */
package manageruser.dao;
import java.util.ArrayList;

import manageruser.entities.mst_group;;
/**
 * @author DungPham
 *
 */
public interface MstGroupDao {
	/**
	 * @return list group
	 */
	ArrayList<mst_group> getAllGroup();
	/**
	 * get group by id
	 * @param grId id group need to get
	 * @return mst_group
	 */
	mst_group getMstGroupById(int grId);
}

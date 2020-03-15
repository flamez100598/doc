/**
 *  Copy right (C) 2020 Luvina
 * Tbl_UserLogic.java, Feb 23, 2020 DungPham
 */
package manageruser.logics;

import java.util.ArrayList;

import manageruser.entities.mst_group;

/**
 * MstGroupLogic interface
 * @author DungPham
 *
 */
public interface MstGroupLogic {
	/**
	 * @return list group
	 */
	ArrayList<mst_group> getAllGroup();
	/**
	 * get mst_group by id
	 * @param groupId
	 * @return mst_group
	 */
	mst_group getGroupById(int groupId);
}

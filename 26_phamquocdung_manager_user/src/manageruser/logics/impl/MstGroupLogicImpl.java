/**
 *  Copy right (C) 2020 Luvina
 * MstGroupLogicImpl.java, Feb 23, 2020 DungPham
 */
package manageruser.logics.impl;

import java.util.ArrayList;

import manageruser.dao.MstGroupDao;
import manageruser.dao.impl.MstGroupDaoImpl;
import manageruser.entities.mst_group;
import manageruser.logics.MstGroupLogic;

/**
 * user logic implement
 * @author DungPham
 *
 */
public class MstGroupLogicImpl implements MstGroupLogic {
	MstGroupDao mstGr;
	public MstGroupLogicImpl( ) {
		mstGr = new MstGroupDaoImpl();
	}
	/**
	 * @return list group
	 */
	@Override
	public ArrayList<mst_group> getAllGroup() {
		return mstGr.getAllGroup();
	}
}

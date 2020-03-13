/**
 * Copy right (C) 2020 Luvina
 * MstJapanLogicImpl.java, 12 Mar 2020 DungPham
 */
package manageruser.logics.impl;

import java.util.ArrayList;

import manageruser.dao.MstJapanDao;
import manageruser.dao.impl.MstJapanDaoImpl;
import manageruser.entities.mst_japan;
import manageruser.logics.MstJapanLogic;

/**
 * MstJapanLogic 
 * @author PG
 */
public class MstJapanLogicImpl implements MstJapanLogic{
	/**
	 * get all list japan level
	 * @return ArrayList<mst_japan>
	 */
	@Override
	public ArrayList<mst_japan> getAllListJapanLevel() {
		MstJapanDao mjd = new MstJapanDaoImpl();
		return mjd.getAllListJapanLevel();	
	}
}

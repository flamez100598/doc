/**
 * Copy right (C) 2020 Luvina
 * MstJapanDao.java, 12 Mar 2020 DungPham
 */
package manageruser.dao;
import java.util.ArrayList;
import manageruser.entities.mst_japan;;
/**
 * interface Mst japan dao 
 * @author DungPham
 */
public interface MstJapanDao {
	/**
	 * get all list japan level
	 * @return ArrayList<mst_japan>
	 */
	ArrayList<mst_japan> getAllListJapanLevel();
}

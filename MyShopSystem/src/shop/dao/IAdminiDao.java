package shop.dao;

import java.sql.SQLException;

import shop.pojo.Admini;

/**
 * 管理员接口
 * @author 73556
 *
 */

public interface IAdminiDao {
	//查询管理员
	public Admini select(Admini admini) throws SQLException; 

}

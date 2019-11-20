package shop.dao;
/**
 * 对登录用户的操作接口
 */

import java.sql.SQLException;

import shop.pojo.User;

public interface IUserDao {
	//增加用户
	public void add(User user) throws SQLException;
	//删除用户
	public void delete(User user) throws SQLException;
	//修改用户信息
	public void update(User user) throws SQLException;

}

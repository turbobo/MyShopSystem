package shop.dao;

import java.sql.SQLException;
import java.util.List;

import shop.pojo.OrderEntity;
import shop.pojo.User;

/**
 * 订单类接口
 * @author 73556
 *
 */

public interface IOrderEntityDao {
	//添加订单
	public void addOrder(OrderEntity orderentity) throws SQLException;
	//根据订单号删除订单
	public void deleteOrder(OrderEntity orderentity) throws SQLException;
	//修改订单状态
	public void updateOrderStatus(OrderEntity orderentity) throws SQLException;
	//根据订单号查找单个订单
	public OrderEntity selectOrderByoid(OrderEntity orderentity) throws SQLException;
	//根据用户id查找所有订单
	public List<OrderEntity> selectOrderByuid(User user) throws SQLException;
	//根据用户id和商品名称模糊查找订单
	public List<OrderEntity> selectOrderByParam(User user,String pname) throws SQLException;
}

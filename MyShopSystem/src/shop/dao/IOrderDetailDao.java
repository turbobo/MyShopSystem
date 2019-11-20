package shop.dao;

import java.sql.SQLException;
import java.util.List;
import shop.pojo.OrderDetail;
import shop.pojo.OrderEntity;

/**
 * 订单详情接口
 * @author 73556
 *
 */

public interface IOrderDetailDao {
	//增加订单详情
	public void addOrderDetail(OrderDetail orderdetail) throws SQLException; 
	//根据订单号查找订单详情
	public List<OrderDetail> select(OrderEntity orderentity) throws SQLException;
}

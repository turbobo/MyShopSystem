package shop.dao;
/**
 * 订单详情实现类
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shop.pojo.OrderDetail;
import shop.pojo.OrderEntity;
import shop.pojo.Product;
import shop.utils.JDBCUtils;

public class OrderDetailDao implements IOrderDetailDao{

	@Override
	public void addOrderDetail(OrderDetail orderdetail) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("insert into orderdetail values(null,?,?,?,?,?,?)");
			ps.setObject(1, orderdetail.getOid());
			ps.setObject(2, orderdetail.getPname());
			ps.setObject(3, orderdetail.getPrice());
			ps.setObject(4, orderdetail.getSdesc());
			ps.setObject(5, orderdetail.getScategory());
			ps.setObject(6, orderdetail.getBuyamount());
			//执行sql语句
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			JDBCUtils.close(conn, ps, null);
		}
	}

	//根据oid订单号查询详情
	public List<OrderDetail> select(OrderEntity orderentity) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//获得数据库连接
		conn=JDBCUtils.getConnection();
		ps=conn.prepareStatement("select * from orderdetail where oid=?");
		ps.setObject(1, orderentity.getOid());
		List<OrderDetail> ods=new ArrayList<OrderDetail>();
		
		//执行sql语句
		rs=ps.executeQuery();
		//查出来只有一个,就要if判断，不为空就返回;查询多个就要while
		while(rs.next()){
			//将查出来的商品添加到prods中
			ods.add(new OrderDetail(rs.getString("oid"),rs.getString("pname"),rs.getFloat("price"),rs.getString("sdesc"),rs.getString("scategory"),rs.getInt("buyamount")));
		}
		
		return ods;
	
	}

}

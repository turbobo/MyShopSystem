package shop.dao;
/**
 * 订单实现类
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.pojo.OrderEntity;
import shop.pojo.Product;
import shop.pojo.User;
import shop.utils.JDBCUtils;

public class OrderEntityDao implements IOrderEntityDao{

	public void addOrder(OrderEntity orderentity) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("insert into orderentity values(?,?,?,?,?,null,null)");
			ps.setObject(1, orderentity.getOid());
			ps.setObject(2, orderentity.getUid());
			ps.setObject(3, orderentity.getTotalNum());
			ps.setObject(4, orderentity.getTotalPrice());
			ps.setObject(5, orderentity.getStatus());
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

	//根据订单号删除订单
	public void deleteOrder(OrderEntity orderentity) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("delete from orderentity where oid=?");
			//根据用户的id删除
			ps.setObject(1, orderentity.getOid());
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
	
	//修改订单状态
	public void updateOrderStatus(OrderEntity orderentity) throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("update orderentity set status=? where oid=?");
			ps.setObject(1, "已支付");
			ps.setObject(2, orderentity.getOid());
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
	
	//根据订单号查询单个订单
	public OrderEntity selectOrderByoid(OrderEntity orderentity) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("select * from orderentity where oid=?");
			ps.setObject(1, orderentity.getOid());
			//执行sql语句
			rs=ps.executeQuery();
			//查出来只有一个,就要if判断，不为空就返回;查询多个就要while
			if(rs.next()){
				//将查出来的订单返回
				return new OrderEntity(rs.getString("oid"),rs.getInt("uid"),rs.getInt("totalNum"),rs.getFloat("totalPrice"),rs.getString("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			JDBCUtils.close(conn, ps, null);
		}
		return null;
	
	}

	//根据用户id查找所有订单
	public List<OrderEntity> selectOrderByuid(User user) throws SQLException {

		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<OrderEntity> oes=new ArrayList<OrderEntity>();
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("select * from orderentity where uid=?");
			ps.setObject(1, user.getId());
			//执行sql语句
			rs=ps.executeQuery();
			while(rs.next()){
				//将查出来的商品添加到prods中
				oes.add(new OrderEntity(rs.getString("oid"),rs.getInt("uid"),rs.getInt("totalNum"),rs.getFloat("totalPrice"),rs.getString("status")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			JDBCUtils.close(conn, ps, null);
		}
		
		return oes;	
	}
	
	//根据用户id和商品名称模糊查找订单
	public List<OrderEntity> selectOrderByParam(User user, String pname) throws SQLException {

		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<OrderEntity> oes=new ArrayList<OrderEntity>();
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("select * from orderentity where oid in (select oid from orderdetail where pname like ?) and uid=?");
			ps.setObject(1, "%"+pname+"%");
			ps.setObject(2, user.getId());
			//System.out.println("OrderEntityDao.selectOrderByParam()"+ps);
			//执行sql语句
			rs=ps.executeQuery();
			while(rs.next()){
				//将查出来的商品添加到prods中
				oes.add(new OrderEntity(rs.getString("oid"),rs.getInt("uid"),rs.getInt("totalNum"),rs.getFloat("totalPrice"),rs.getString("status")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			JDBCUtils.close(conn, ps, null);
		}
		
		return oes;	
		
	
	}
	//测试根据用户和商品名称模糊查询订单
//	public static void main(String[] args) {
//		try {
//			List<OrderEntity> o=(List<OrderEntity>) new OrderEntityDao().selectOrderByParam(UserDao.getInstance().selectByName("jack"),"reno");
//			System.out.println("OrderEntityDao.main()"+o.get(0).getOid());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}	


}

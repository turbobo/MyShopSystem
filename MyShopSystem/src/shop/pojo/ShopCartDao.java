package shop.pojo;
/**
 * 购物车操作实现类
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.dao.IShopCartDao;
import shop.utils.JDBCUtils;

public class ShopCartDao implements IShopCartDao {
		//单例模式
		//创建 ShopCartDao 的一个对象
	   private static ShopCartDao instance = new ShopCartDao();
	 
	   //让构造函数为 private，这样该类就不会被实例化
	   private ShopCartDao(){}
	 
	   //获取唯一可用的对象
	   public static ShopCartDao getInstance(){
	      return instance;
	   }
	
	@Override
	public void addTocart(ShopCart shopcart) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("insert into shopcart values(null,?,?,?,?,?)");
			ps.setObject(1, shopcart.getUid());
			ps.setObject(2, shopcart.getPid());
			ps.setObject(3, shopcart.getPrice());
			ps.setObject(4, shopcart.getBuyamount());
			ps.setObject(5, shopcart.getTotalPrice());  //初始数量为1，总价就是单价
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

	
	//根据购物车id删除购物车内容
	public void deletecart(ShopCart shopcart) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("delete from shopcart where cart_id=?");
			//根据用户的id删除
			ps.setObject(1, shopcart.getCart_id());
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
	
	//根据用户名和商品名删除购物车内容
		public void deletecartByParam(int uid,int pid) throws SQLException {
			Connection conn=null;
			PreparedStatement ps=null;
			try {
				//获得数据库连接
				conn=JDBCUtils.getConnection();
				ps=conn.prepareStatement("delete from shopcart where uid=? and pid=?");
				//根据用户的id删除
				ps.setObject(1, uid);
				ps.setObject(2, pid);
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


	//修改商品数量和总价
	public void increasecart(ShopCart shopcart) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("update shopcart set buyamount=? and totalPrice=? where cart_id=?");
			//加1之后的数量
			int increase_amount=shopcart.getBuyamount()+1;
			//赋值给该对象
			shopcart.setBuyamount(increase_amount);
			shopcart.setTotalPrice(increase_amount*shopcart.getPrice());
		//	System.out.println("ShopCartDao.increasecart(原有数量)--"+shopcart.getBuyamount()+"加1后--"+increase_amount+"总价--"+increase_amount*shopcart.getPrice());
			ps.setObject(1, increase_amount);
			ps.setObject(2, shopcart.getBuyamount());
			ps.setObject(3, shopcart.getTotalPrice());
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


	@Override
	public void decreasecart(ShopCart shopcart) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("update shopcart set buyamount=? where cart_id=?");
			//System.out.println("ShopCartDao.decreasecart(原有数量)"+shopcart.getBuyamount());
			int decrease_amount=shopcart.getBuyamount()-1;
			//System.out.println("ShopCartDao.increasecart()"+shopcart.getCart_id()+"--"+decrease_amount+"--"+decrease_amount*shopcart.getPrice()+"--"+shopcart.getPrice());
			ps.setObject(1, decrease_amount);
			ps.setObject(2, decrease_amount*shopcart.getPrice());
			ps.setObject(3, shopcart.getCart_id());
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

	@Override
	public List<ShopCart> selectAll(User user) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ShopCart> shopcarts=new ArrayList<ShopCart>();
		//获得数据库连接
		conn=JDBCUtils.getConnection();
		ps=conn.prepareStatement("select * from shopcart where uid=?");
		ps.setObject(1, user.getId());
		//执行sql语句
		rs=ps.executeQuery();
		while(rs.next()){
			//将查出来的商品添加到购物车集合中
			shopcarts.add(new ShopCart(rs.getInt("cart_id"),rs.getInt("uid"),rs.getInt("pid"),rs.getFloat("price"),rs.getInt("buyamount"),rs.getFloat("totalPrice")));
		}
	
		return shopcarts;
	}

	@Override
	public ShopCart select(ShopCart shopcart) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ShopCart> shopcarts=new ArrayList<ShopCart>();
		//获得数据库连接
		conn=JDBCUtils.getConnection();
		ps=conn.prepareStatement("select * from shopcart where cart_id=?");
		ps.setObject(1, shopcart.getCart_id());
		//执行sql语句
		rs=ps.executeQuery();
		if(rs.next()){
			//将查出来的商品添加到购物车中
			//System.out.println("ShopCartDao.select(查到的商品为)"+rs.getInt("cart_id")+"-"+rs.getInt("buyamount")+"-"+rs.getFloat("totalPrice"));
			return new ShopCart(rs.getInt("cart_id"),rs.getInt("uid"),rs.getInt("pid"),rs.getFloat("price"),rs.getInt("buyamount"),rs.getFloat("totalPrice"));
		}
		return null;
	
	}
	
	

}

package shop.dao;
/**
 * 用户操作实现类
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shop.pojo.User;
import shop.utils.JDBCUtils;

public class UserDao implements IUserDao {
	//单例模式，提供接口
	private static UserDao instance=new UserDao();
	
	//只需要获得单例模式即可
	public static UserDao getInstance() {
		return instance;
	}
	
	//私有化
	private UserDao(){
		
	}

	@Override
	public void add(User user) throws SQLException {

		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("insert into user values(null,?,?,?,?,?)");
			ps.setObject(1, user.getSname());
			ps.setObject(2, user.getSpassword());
			ps.setObject(3, user.getSsex());
			ps.setObject(4, user.getMobile());
			ps.setObject(5, user.getSaddress());
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
	public void delete(User user) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("delete from user where id=?");
			//根据用户的id删除
			ps.setObject(1, user.getId());
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
	public void update(User user) throws SQLException {

		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("update user set sname=?,spassword=?,ssex=?,mobile=?,saddress=? where id=?");
			
			ps.setObject(1, user.getSname());
			ps.setObject(2, user.getSpassword());
			ps.setObject(3, user.getSsex());
			ps.setObject(4, user.getMobile());
			ps.setObject(5, user.getSaddress());
			ps.setObject(6, user.getId());
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
	
	
	public User selectByName(String sname) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("select * from user where sname=?");
			ps.setObject(1, sname);
			//执行sql语句
			rs=ps.executeQuery();
			if(rs.next()){
				//将查出来的员工返回
				return new User(rs.getInt("id"),rs.getString("sname"));
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
	
	/*
	 * 登录用户匹配
	 */
	//因为登录时只要验证用户名和密码，所以只要传入用户和密码就行，不用传入对象（除非查询对象较多且条件不定时）
		public User findUser(String sname, String spassword) throws SQLException {
			
			String sql="select * from user where sname=? and spassword=?";

			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			List<User> emps=new ArrayList<User>();
			try {
				//获得数据库连接
				conn=JDBCUtils.getConnection();
				ps=conn.prepareStatement(sql);
//				ps.setObject(1, sname+"");
				ps.setObject(1, sname);
				ps.setObject(2, spassword);
				//执行sql语句
				rs=ps.executeQuery();
				if(rs.next())   //查到的结果不为空
					return new User(rs.getInt("id"),rs.getString("sname"),rs.getString("spassword"),rs.getString("ssex"),rs.getInt("mobile"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//关闭数据库连接
				JDBCUtils.close(conn, ps, null);
			}
			return null;
			
		}

}

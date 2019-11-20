package shop.dao;
/**
 * 管理员实现类
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.pojo.Admini;
import shop.pojo.User;
import shop.utils.JDBCUtils;

public class AdminiDao implements IAdminiDao {
	
	//单例模式，提供接口
	private static AdminiDao instance=new AdminiDao();
	
	//只需要获得单例模式即可
	public static AdminiDao getInstance() {
		return instance;
	}
	
	//私有化
	private AdminiDao(){
		
	}

	@Override
	public Admini select(Admini admini) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("select * from admini where sname=?");
			ps.setObject(1, admini.getSname());
			//执行sql语句
			rs=ps.executeQuery();
			if(rs.next()){
				//将查出来的员工返回
				return new Admini(rs.getString("sname"),rs.getString("spassword"),rs.getString("sdesc"));
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
		public Admini findAdmini(String sname, String spassword) throws SQLException {
			
			String sql="select * from admini where sname=? and spassword=?";

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
					return new Admini(rs.getString("sname"),rs.getString("spassword"),rs.getString("sdesc"));
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

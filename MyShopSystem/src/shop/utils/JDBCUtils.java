package shop.utils;

/**
 *  抽取访问数据公共部分
 *  1、注册驱动
 *  2、获得数据库连接
 *  3、关闭资源
 *  
 *  
 *                              记得把驱动放到lib目录下
 * 
 * @author 73556
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils { 
	//数据库地址      指定数据库的编码格式为UTF-8
	static String url="jdbc:mysql://localhost:3306/myshop?useUnicode=true&characterEncoding=UTF-8&generateSimpleParameterMetadata=true";
	//用户名
	static String user="root";
	//密码
	static String password="12345";
	
	//注册驱动只需要第一次注册即可，调用类时便加载好
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//设为静态，就不用创建对象，直接调用
	public static Connection getConnection() throws SQLException {
		Connection conn=DriverManager.getConnection(url,user,password);
		return conn;
	}
	
	
	//关闭资源
	//Statement st 由 conn 创建出来，要先关闭st
	//结果集ResultSet ss 来自 st,要先关闭结果集ss
	public static void close(Connection conn,Statement st,ResultSet rs) throws SQLException {
//		conn.close();
		
		
		try {
			if(rs!=null)
				rs.close();  //第一步都没关闭，程序不再执行，下面资源也不会关闭
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(st!=null)
					st.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				if(conn!=null)
					conn.close();  //该异常抛出，让程序员知道可能有数据库关闭异常
			}
			//前两者都要捕获，保证最后一个资源能够关闭
		}
		
	}
	
	
	
	


}

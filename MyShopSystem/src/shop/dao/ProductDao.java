package shop.dao;
/**
 *  商品操作实现类
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import shop.dao.IProductDao;
import shop.pojo.Product;
import shop.utils.JDBCUtils;
import shop.utils.PageModel;
import shop.utils.StringUtils;

public class ProductDao implements IProductDao {

	@Override
	public void add(Product product) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("insert into product values(null,?,?,?,?)");
			ps.setObject(1, product.getSname());
			ps.setObject(2, product.getPrice());
			ps.setObject(3, product.getSdesc());
			ps.setObject(4, product.getScategory());
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

	@Override   //传过来的商品中已经在数据库中查到了id
	public void delete(Product product) throws SQLException {

		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("delete from product where id=?");
			//根据用户的id删除
			ps.setObject(1, product.getId());
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
	public void update(Product product) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("update product set sname=?,price=?,sdesc=?,scategory=? where id=?");
			
			ps.setObject(1, product.getSname());
			ps.setObject(2, product.getPrice());
			ps.setObject(3, product.getSdesc());
			ps.setObject(4, product.getScategory());
			ps.setObject(5, product.getId());
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
	

	//查单个商品
	public Product select(Product product) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement("select * from product where id=?");
			ps.setObject(1, product.getId());
			//执行sql语句
			rs=ps.executeQuery();
			//查出来只有一个,就要if判断，不为空就返回;查询多个就要while
			if(rs.next()){
				//将查出来的员工返回
				return new Product(rs.getInt("id"),rs.getString("sname"),rs.getFloat("price"),rs.getString("sdesc"),rs.getString("scategory"));
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

	
	//查询所有商品
	public List<Product> selectAll() throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<Product> prods=new ArrayList<Product>();
		//获得数据库连接
		conn=JDBCUtils.getConnection();
		ps=conn.prepareStatement("select * from product");
		//执行sql语句
		rs=ps.executeQuery();
		while(rs.next()){
			//将查出来的商品添加到prods中
			prods.add(new Product(rs.getInt("id"),rs.getString("sname"),rs.getFloat("price"),rs.getString("sdesc"),rs.getString("scategory")));
		}
		
		return prods;
	}
	
	//根据条件查询
	public List<Product> selectByParaProds(Product p) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<Product> prods=new ArrayList<Product>();
		StringBuilder sb=new StringBuilder();
		//存放查询条件
		List values=new ArrayList();
		//当没有查询条件时，查询所有员工
		sb.append("select * from product where 1=1 ");
		
		if(p!=null){
			//判断name和sex是否为空后者空字符串
			if(StringUtils.isNullorBlank(p.getSname())){
				sb.append(" and sname=? ");
				values.add(p.getSname());
			}
			/*
			 *  list数组可以同时存放float和String类型
			 */
			/*
			 * 模糊查询  SELECT * FROM product where 1=1 and sdesc like '%华%机%' ;
			 * 描述可以模糊查询
			 */
			if(p.getLowerPrice()>=0){   //商品价格大于0     
				sb.append(" and price>=? ");
				values.add(p.getLowerPrice());
			}
			if(p.getUpperPrice()>0){   //商品价格大于0     
				sb.append(" and price<=? ");
				values.add(p.getUpperPrice());
			}
			if(StringUtils.isNullorBlank(p.getSdesc())){      
				sb.append(" and sdesc like ? ");
				//处理描述的模糊查询
				//先将字符串去掉中间空格，再转为数组
				char[] str = (p.getSdesc().replace(" ", "")).toCharArray();
				//再给每个字符前后都加上%
				//StringBuilder sb2=null;   Null与任何字符串拼接后的结果还是Null
				//new StringBuilder()<==>new StringBuilder("")
				StringBuilder sb2=new StringBuilder();   
				//数组用下角标遍历  不是集合
				for(int i=0;i<str.length;i++){   
					sb2.append("%"+str[i]);
				}
				sb2.append("%");
				values.add(sb2.toString());
			}
			if(StringUtils.isNullorBlank(p.getScategory())){   
				sb.append(" and scategory=? ");
				values.add(p.getScategory());
			}
		}
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement(sb.toString());
			//不确定查询条件有1个还是两个，用数组将查询条件封装
			for(int i=0;i<values.size();i++){
				ps.setObject(i+1, values.get(i));
			}
			//执行sql语句
			rs=ps.executeQuery();
			while(rs.next()){
				//将查出来的商品添加到prods中
				prods.add(new Product(rs.getInt("id"),rs.getString("sname"),rs.getFloat("price"),rs.getString("sdesc"),rs.getString("scategory")));
			}

			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			//关闭数据库连接
			JDBCUtils.close(conn, ps, null);
		}
		//System.out.println("EmpDao.select()"+emps);
		return prods;
	}

	//查询符合条件的总记录数
	public int totalNumByParam(Product p) throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<Product> prods=new ArrayList<Product>();
		StringBuilder sb=new StringBuilder();
		List values=new ArrayList();
		//查询符合条件的员工数量
		sb.append("select count(*) from product where 1=1 ");
		
		if(p!=null){
			//判断name和sex是否为空后者空字符串
			if(StringUtils.isNullorBlank(p.getSname())){
				sb.append(" and sname=? ");
				values.add(p.getSname());
			}
			/*
			 *  list数组可以同时存放float和String类型
			 */
			/*
			 * 模糊查询  SELECT * FROM product where 1=1 and sdesc like '%华%机%' ;
			 * 描述可以模糊查询
			 */
			if(p.getLowerPrice()>=0){   //商品价格大于0     
				sb.append(" and price>=? ");
				values.add(p.getLowerPrice());
			}
			if(p.getUpperPrice()>0){   //商品价格大于0     
				sb.append(" and price<=? ");
				values.add(p.getUpperPrice());
			}
			if(StringUtils.isNullorBlank(p.getSdesc())){      
				sb.append(" and sdesc like ? ");
				//处理描述的模糊查询
				//先将字符串去掉中间空格，再转为数组
				char[] str = (p.getSdesc().replace(" ", "")).toCharArray();
				//再给每个字符前后都加上%
				//StringBuilder sb2=null;   Null与任何字符串拼接后的结果还是Null
				//new StringBuilder()<==>new StringBuilder("")
				StringBuilder sb2=new StringBuilder();   
				//数组用下角标遍历  不是集合
				for(int i=0;i<str.length;i++){   
					sb2.append("%"+str[i]);
				}
				sb2.append("%");
				values.add(sb2.toString());
			}
			if(StringUtils.isNullorBlank(p.getScategory())){   
				sb.append(" and scategory=? ");
				values.add(p.getScategory());
			}
		}
			try {
				//获得数据库连接
				conn=JDBCUtils.getConnection();
				ps=conn.prepareStatement(sb.toString());
				//不确定查询条件有1个还是两个，用数组将查询条件封装
				for(int i=0;i<values.size();i++){
					ps.setObject(i+1, values.get(i));
				}
				
				//执行sql语句
				rs=ps.executeQuery();
				if(rs.next()){  
					//结果集只有一个数字
					return rs.getInt(1);
				}
				/*
				 * 先将所有员工都查出来，再按爱好过滤（如有爱好作为条件时）
				 */
			
				//回选、条件为空时
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				//关闭数据库连接
				JDBCUtils.close(conn, ps, null);
			}
		return 0;
	}
	
	/*
	 * 根据参数和页面查询        e中包含员工查询条件，page中包含分页的各种信息
	 * 无论查询多么复杂，都是在最后加上limit ?,?
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public List<Product> selectByParametersAndPages(Product p,PageModel page) throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<Product> prods=new ArrayList<Product>();
		StringBuilder sb=new StringBuilder();
		List values=new ArrayList();
		//不管有没有条件，先查询所有
		sb.append("select * from product where 1=1 ");
		
		int start=(page.getCurrentPage()-1)*page.getPageContent();  //获得第几条开始
		
		if(p!=null){
			//判断name和sex是否为空后者空字符串
			if(StringUtils.isNullorBlank(p.getSname())){
				sb.append(" and sname=? ");
				values.add(p.getSname());
			}
			/*
			 *  list数组可以同时存放float和String类型
			 */
			/*
			 * 模糊查询  SELECT * FROM product where 1=1 and sdesc like '%华%机%' ;
			 * 描述可以模糊查询
			 */
			if(p.getLowerPrice()>=0){   //商品价格大于0     
				sb.append(" and price>=? ");
				values.add(p.getLowerPrice());
			}
			if(p.getUpperPrice()>0){   //商品价格大于0     
				sb.append(" and price<=? ");
				values.add(p.getUpperPrice());
			}
			if(StringUtils.isNullorBlank(p.getSdesc())){      
				sb.append(" and sdesc like ? ");
				//处理描述的模糊查询
				//先将字符串去掉中间空格，再转为数组
				char[] str = (p.getSdesc().replace(" ", "")).toCharArray();
				//再给每个字符前后都加上%
				//StringBuilder sb2=null;   Null与任何字符串拼接后的结果还是Null
				//new StringBuilder()<==>new StringBuilder("")
				StringBuilder sb2=new StringBuilder();   
				//数组用下角标遍历  不是集合
				for(int i=0;i<str.length;i++){   
					sb2.append("%"+str[i]);
				}
				sb2.append("%");
				values.add(sb2.toString());
			}
			if(StringUtils.isNullorBlank(p.getScategory())){   
				sb.append(" and scategory=? ");
				values.add(p.getScategory());
			}
		}
		
		//不管有没有查询条件,分页显示照样限制
		sb.append(" limit ?,? ");     //放在外面***********************************************
		
		try {
			//获得数据库连接
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement(sb.toString());
			//不确定查询条件有1个还是两个，用数组将查询条件封装
			for(int i=0;i<values.size();i++){
				ps.setObject(i+1, values.get(i));
			}
			//设置分页条件*************************************************************************
			ps.setObject(values.size()+1, start);   //第几条记录开始
			ps.setObject(values.size()+2, page.getPageContent());   //每页显示多少条记录
			//执行sql语句
			rs=ps.executeQuery();
			while(rs.next()){
				//将查出来的员工返回
				prods.add(new Product(rs.getInt("id"),rs.getString("sname"),rs.getFloat("price"),rs.getString("sdesc"),rs.getString("scategory")));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			//关闭数据库连接
			JDBCUtils.close(conn, ps, null);
		}
		//System.out.println("EmpDao.select()"+emps);
		return prods;
	}

}

package shop.control;
/**
 * 根据购物提交的所有商品-数量。生成订单
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.OrderDetailDao;
import shop.dao.OrderEntityDao;
import shop.dao.ProductDao;
import shop.pojo.OrderDetail;
import shop.pojo.OrderEntity;
import shop.pojo.Product;
import shop.pojo.ShopCart;
import shop.pojo.ShopCartDao;
import shop.pojo.User;

public class MakeOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids[]=request.getParameterValues("pid");
		String buycounts[]=request.getParameterValues("buycount");
		/*
		 * session中获得用户！！！！！！！！！！！！！！！！！！
		 */
		User user=(User) request.getSession().getAttribute("user");
		
		
		//创建一个Map集合，商品-数量键值对
		Map<Product,Integer> map=new HashMap<Product,Integer>();
		for(int i=0;i<ids.length;i++){
			//将每个商品-数量放入map集合中
			//map.put(new Product(Integer.parseInt(ids[i])), Integer.parseInt(buycounts[i]));
			/*
			 * 不是新建商品，而是去找到该商品，否则后面无法获得商品的其他信息
			 */
			Product p;
			try {
				p = new ProductDao().select(new Product(Integer.parseInt(ids[i])));
				map.put(p, Integer.parseInt(buycounts[i]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//获得map集合的键值对,放到Set类型集合中
		Set<Map.Entry<Product,Integer>> maps=map.entrySet();
		int totalNum=0;
		float totalPrice=0f;
		//总数量和总价
		for(Map.Entry<Product, Integer> me:maps) {
			totalNum+=me.getValue();
			totalPrice+=me.getKey().getPrice()*me.getValue();
		}
		//创建一个日历对象
		Calendar c=Calendar.getInstance();
		//根据日期生成订单编号
		String oid=c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH)+1)+""+c.get(Calendar.DATE)+""+c.get(Calendar.HOUR)+""+c.get(Calendar.MINUTE)+""+c.get(Calendar.SECOND);		
		//加入商品信息，生成订单  默认未支付unpaid
		OrderEntity o=new OrderEntity(oid,user.getId(),totalNum,totalPrice,"未支付",map);
		//订单写到数据库中
		try {
			new OrderEntityDao().addOrder(o);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//写入订单详情
		for(Map.Entry<Product, Integer> me:maps) {
			OrderDetail od=new OrderDetail(o.getOid(),me.getKey().getSname(),me.getKey().getPrice(),me.getKey().getSdesc(),me.getKey().getScategory(),me.getValue());	
			//写入数据库
			try {
				new OrderDetailDao().addOrderDetail(od);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		//只会将本页面新添加的订单给该用户，而不会去数据库查已经存在的订单
		//订单添加到用户！！！！（先获得所有订单集合，再添加订单到集合中）
		//user.AddOrder(o);
		
		
		/*
		 * 重新创建集合（清空），只要生成订单后，清空map集合
		 */
		
		map=new HashMap<Product,Integer>();
		//数据库中购物车记录删除
		for(int i=0;i<ids.length;i++){
			//找到该商品
			try {
				//去数据库删除该用户的购物车（用户名和商品名标识购物车）
				ShopCartDao.getInstance().deletecartByParam(user.getId(),Integer.parseInt(ids[i]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//响应页面
		response.sendRedirect("/MyShopSystem/ShowOrderServlet.do");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	
	}

	
}

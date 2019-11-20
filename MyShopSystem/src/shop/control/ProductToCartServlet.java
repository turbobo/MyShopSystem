package shop.control;
/**
 *  从list.jsp传过来，统计添加到购物车的商品,值确定将本商品加入到购物车，初试数量为1
 *  只做统计,不跳转页面
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ProductDao;
import shop.pojo.Product;
import shop.pojo.ShopCart;
import shop.pojo.ShopCartDao;
import shop.pojo.User;

public class ProductToCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得登录的用户
		//User user=(User) request.getSession().getAttribute("user");
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			response.sendRedirect("/MyShopSystem/user_login.jsp");
			return ;   //程序结束
		}
		//统计加入购物车的商品
		String bid=request.getParameter("bid");
		//查询该商品
		Product p;
		try {
			p = new ProductDao().select(new Product(Integer.parseInt(bid)));
			//从数据库查登录用户的所有购物车集合 
			List<ShopCart> shopcarts=ShopCartDao.getInstance().selectAll(user);
			//生成该商品的购物车   首次加入购物车时数量为1,总计即为单价
			ShopCart shopcart=new ShopCart(user.getId(),p.getId(),p.getPrice(),1,p.getPrice());
			if(shopcarts.indexOf(shopcart)==-1){  //购物车中没有该商品时
				//把本次点击的商品加到购物车中，首次添加数量为默认为1
				ShopCartDao.getInstance().addTocart(shopcart);
			}
			//响应页面
			response.sendRedirect("/MyShopSystem/ListServlet.do");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}

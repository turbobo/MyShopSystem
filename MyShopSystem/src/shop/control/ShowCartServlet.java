package shop.control;
/**
 * 从ProductToCartServlet传过来,显示购物车的所有内容,可以修改商品购买数量
 * 发送到显示页面shopcart.jsp,显示购物车的所有内容
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

public class ShowCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得用户登录信息
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			response.sendRedirect("/MyShopSystem/user_login.jsp");
			return ;   //程序结束
		}
		List<ShopCart> shopcarts;
		try {
			//去数据库查询登录用户的所有购物车
			shopcarts = ShopCartDao.getInstance().selectAll(user);
			//设置到共享空间,方便jsp页面的EL表达式获得
			request.getSession().setAttribute("shopcarts", shopcarts);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		//获得共享空间中的已添加的商品  形成的购物车
//		List<ShopCart> carts=(List<ShopCart>) request.getSession().getAttribute("carts");
//		//没有购物车存在
//		if(carts==null)
//			carts=new ArrayList<ShopCart>();
//		//获得页面添加到购物车的商品集合		
		response.sendRedirect("/MyShopSystem/shopcart.jsp");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

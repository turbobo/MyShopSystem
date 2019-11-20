package shop.control;
/**
 *  删除购物中某个商品
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.pojo.ShopCart;
import shop.pojo.ShopCartDao;

public class DeleteCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String cart_id=request.getParameter("cart_id");
		//System.out.println("DeleteCartServlet.doGet()------"+cart_id);
		try {
			//找到该商品
			ShopCart sc=ShopCartDao.getInstance().select(new ShopCart(Integer.parseInt(cart_id)));
			//去数据库删除
			ShopCartDao.getInstance().deletecart(sc);
			//响应页面
			response.sendRedirect("/MyShopSystem/ShowCartServlet.do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

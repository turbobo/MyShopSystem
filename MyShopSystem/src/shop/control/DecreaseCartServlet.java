package shop.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.pojo.ShopCart;
import shop.pojo.ShopCartDao;

public class DecreaseCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		//获得商品id
		String cart_id=request.getParameter("cart_id");
		try {
			//找到该商品对应购物车,查询已确定的购买数量
			ShopCart sc=ShopCartDao.getInstance().select(new ShopCart(Integer.parseInt(cart_id)));
			//数量加1
			ShopCartDao.getInstance().decreasecart(sc);
			//响应页面,先到数据库中查该用户的全部购物车信息        查询更新后的购物车信息
			response.sendRedirect("/MyShopSystem/ShowCartServlet.do");
			//request.getRequestDispatcher("/ShowCartServlet.do").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cart_id2=request.getParameter("cart_id2");
		System.out.println("DecreaseCartServlet.doPost()"+cart_id2);
		
		
		//doGet(request, response);
	}
}

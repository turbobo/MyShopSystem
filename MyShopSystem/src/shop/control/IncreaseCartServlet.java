package shop.control;
/**
 * 购物车商品数量增加
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

public class IncreaseCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置响应编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		String cart_id=request.getParameter("cart_id");
		//找到该商品对应购物车,查询已确定的购买数量
		try {
			ShopCart sc=ShopCartDao.getInstance().select(new ShopCart(Integer.parseInt(cart_id)));
			System.out.println("IncreaseCartServlet.doPost(前)"+sc.getBuyamount());
			//去数据库给该商品数量加1
			ShopCartDao.getInstance().increasecart(sc);
			//再获得修改后的数量和总价
//			int buyamount=sc.getBuyamount()+1;
//			float totalProce=buyamount*sc.getPrice();
//			System.out.println("IncreaseCartServlet.doPost(后)"+cart_id+"-"+buyamount+"-"+totalProce);
			//System.out.println("IncreaseCartServlet.doPost(增加后)"+sc.getBuyamount()+"--"+sc.getTotalPrice());
			//响应页面
			response.sendRedirect("/MyShopSystem/ShowCartServlet.do");
			
			//将数据传到jsp
			//response.getWriter().write(sc.getBuyamount()+"-"+sc.getTotalPrice());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}

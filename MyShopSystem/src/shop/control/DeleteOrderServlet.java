package shop.control;
/**
 *   删除订单
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.OrderEntityDao;
import shop.dao.ProductDao;
import shop.pojo.OrderEntity;
import shop.pojo.Product;

public class DeleteOrderServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得删除的订单id 
		String oid=request.getParameter("oid");
		
		try {
			//数据库删除
			new OrderEntityDao().deleteOrder(new OrderEntity(oid));
			//响应数据到页面
			response.sendRedirect("/MyShopSystem/ShowOrderServlet.do");
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}

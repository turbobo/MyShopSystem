package shop.control;
/**
 *  订单支付
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.OrderEntityDao;
import shop.pojo.OrderEntity;

public class OrderPayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得删除的订单id 
		String oid=request.getParameter("oid");
		try {
			//去数据库更新订单状态
			new OrderEntityDao().updateOrderStatus(new OrderEntity(oid));
			//响应页面
			response.sendRedirect("/MyShopSystem/ShowOrderServlet.do");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package shop.control;
/**
 * 显示订单详情，先获得订单的map集合  再获得商品详情
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

public class OrderdetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uoid=request.getParameter("oid");
		//去数据库查该订单
		try {
			OrderEntity o=new OrderEntityDao().selectOrderByoid(new OrderEntity(uoid));
			//得到详情中的所有商品
			List<OrderDetail> ods=new OrderDetailDao().select(o);
			//设置分享数据
			request.getSession().setAttribute("uoid", uoid);
			request.getSession().setAttribute("ods", ods);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//响应到showorderdetail.jsp页面展示
		response.sendRedirect("/MyShopSystem/showorderdetail.jsp");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

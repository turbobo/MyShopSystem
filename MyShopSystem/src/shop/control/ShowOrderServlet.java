package shop.control;
/**
 * 接收用户的所有订单，显示到jsp页面上
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.OrderEntityDao;
import shop.pojo.OrderEntity;
import shop.pojo.User;

public class ShowOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//判断用户是否登录
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			response.sendRedirect("/MyShopSystem/user_login.jsp");
			return ;   //程序结束
		}
		List<OrderEntity> orders;
		try {
			orders = new OrderEntityDao().selectOrderByuid(user);
			//设置到session范围
			request.getSession().setAttribute("orders", orders);
			//显示到showorder.jsp页面
			response.sendRedirect("/MyShopSystem/showorder.jsp");
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

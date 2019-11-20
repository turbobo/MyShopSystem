package shop.control;
/**
 * 将查到的数据处理，再传到list.jsp页面展示
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ProductDao;
import shop.pojo.Product;

public class ListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//去数据库查询所有商品
		try {
			List<Product> prods=new ProductDao().selectAll();
			//设置共享范围
			request.getSession().setAttribute("prods", prods);
			//响应页面
			response.sendRedirect("/MyShopSystem/DoFirstPageServlet.do");
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

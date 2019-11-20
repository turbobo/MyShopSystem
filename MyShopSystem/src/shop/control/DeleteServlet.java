package shop.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ProductDao;
import shop.pojo.Product;

public class DeleteServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查询管理员是否登录成功，登录成功才可操作                                Session范围
		Object admini=request.getSession().getAttribute("admini");
		if(admini==null){
			response.sendRedirect("/MyShopSystem/admini_login.jsp");
			return ;   //程序结束
		}
			
		
		//获得删除的商品id    注意数据库中id是int型
		String did=request.getParameter("did");
		
		try {
			//数据库删除
			new ProductDao().delete(new Product(Integer.parseInt(did)));
			//响应数据到页面
			response.sendRedirect("/MyShopSystem/ListServlet.do");
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

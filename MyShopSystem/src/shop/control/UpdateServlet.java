package shop.control;
/**
 * 获得要修改的原数据，发给update.jsp页面展示，然后在update.jsp页面修改，再发给UpdateSubmitServlet提交ListServlet来查询所有
 * 接收update.jsp页面修改后的数据
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ProductDao;
import shop.pojo.Product;

public class UpdateServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查询管理员是否登录成功，登录成功才可操作                                Session范围
		Object admini=request.getSession().getAttribute("admini");
		if(admini==null){
			response.sendRedirect("/MyShopSystem/admini_login.jsp");
			return ;   //程序结束
		}
		
		//获得修改的商品id
		String uid=request.getParameter("uid");
		//数据库中找该商品信息
		try {
			Product p=new ProductDao().select(new Product(Integer.parseInt(uid)));
			//设置到共享范围
			request.setAttribute("p", p);
			//响应数据到页面
//			response.sendRedirect("/MyShopSystem/update.jsp");
			request.getRequestDispatcher("/update.jsp").forward(request, response);
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

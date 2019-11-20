package shop.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ProductDao;
import shop.pojo.Product;

public class AdminiAddProServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查询管理员是否登录成功，登录成功才可操作                                Session范围
		Object admini=request.getSession().getAttribute("admini");
		if(admini==null){
			response.sendRedirect("/MyShopSystem/admini_login.jsp");
			return ;   //程序结束
		}
		response.sendRedirect("/MyShopSystem/add.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

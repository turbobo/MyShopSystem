package shop.control;
/**
 * 接收add.html页面的数据，添加商品到数据
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

public class AddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查询管理员是否登录成功，登录成功才可操作                                Session范围
		Object admini=request.getSession().getAttribute("admini");
		if(admini==null){
			response.sendRedirect("/MyShopSystem/admini_login.jsp");
			return ;   //程序结束
		}
				
		//获得add.html页面中各个参数的值
		String sname=new String(request.getParameter("sname").getBytes("ISO-8859-1"),"UTF-8");
		//单价要转换为float   传过来的是数字
		String price=request.getParameter("price");
		String sdesc=new String(request.getParameter("sdesc").getBytes("ISO-8859-1"),"UTF-8");
		String scategory=new String(request.getParameter("scategory").getBytes("ISO-8859-1"),"UTF-8");
		//写入数据库
		try {
			new ProductDao().add(new Product(sname,Float.parseFloat(price),sdesc,scategory));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//响应数据
		response.sendRedirect("/MyShopSystem/ListServlet.do");	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

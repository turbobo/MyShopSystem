package shop.control;
/**
 *  接收update.jsp修改后的数据，更新至数据库，再传到ListServlet页面显示
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

public class UpdateSubmitServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得修改的员工id
		String id=request.getParameter("id");
		String sname=new String(request.getParameter("sname").getBytes("ISO-8859-1"),"UTF-8");
		String price=request.getParameter("price");		
		String sdesc=new String(request.getParameter("sdesc").getBytes("ISO-8859-1"),"UTF-8");
		String scategory=new String(request.getParameter("scategory").getBytes("ISO-8859-1"),"UTF-8");
		
		//更新数据库
		try {
			new ProductDao().update(new Product(Integer.parseInt(id),sname,Float.parseFloat(price),sdesc,scategory));
			//响应页面
			response.sendRedirect("/MyShopSystem/ListServlet.do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

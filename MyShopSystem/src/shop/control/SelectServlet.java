package shop.control;
/**
 *  查询符合条件的商品，没有输入条件是就查询所有商品;
 *  将查到的商品显示到list.jsp页面
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
import shop.utils.StringUtils;

public class SelectServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收请求数据
		String sname=new String(request.getParameter("sname").getBytes("ISO-8859-1"),"UTF-8");
		String upperPrice=request.getParameter("upperPrice");	
		String lowerPrice=request.getParameter("lowerPrice");	
		String sdesc=new String(request.getParameter("sdesc").getBytes("ISO-8859-1"),"UTF-8");
		String scategory=new String(request.getParameter("scategory").getBytes("ISO-8859-1"),"UTF-8");
		//所有查询条件都为空时，直接显示所有员工
		//将所有查询条件放入一个商品p中当作参数
		Product p=new Product();
		p.setSname(sname);
		//price为null或空字符串时不能转换
		if(StringUtils.isNullorBlank(lowerPrice)&&StringUtils.isNullorBlank(upperPrice)){
			p.setUpperPrice(Float.parseFloat(upperPrice));
			p.setLowerPrice(Float.parseFloat(lowerPrice));	
		}
		p.setSdesc(sdesc);
		p.setScategory(scategory);
		//把查询条件p设置到共享范围，会写到页面
		request.getSession().setAttribute("p", p);
		try {
			//根据条件p查询商品
			List<Product> prods=new ProductDao().selectByParaProds(p);
			//将查到的商品数组prods放到共享范围 !!!!!!!!!!!!!!!!session!!!!!!!!!!!!!!!!!!!!!!!!!!
			request.getSession().setAttribute("prods", prods);
			//响应数据到list展示页面
			response.sendRedirect("/MyShopSystem/DoFirstPageServlet.do");
			//request.getRequestDispatcher("/list.jsp").forward(request, response);
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

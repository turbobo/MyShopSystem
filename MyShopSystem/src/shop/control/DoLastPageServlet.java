package shop.control;
/**
 *  显示末页
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
import shop.utils.PageModel;

public class DoLastPageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//查询用户是否登录成功，登录成功才可翻页
//		Object user=request.getSession().getAttribute("user");
//		if(user==null){
//			response.sendRedirect("/EmpManager_ELJSTL/login.jsp");
//			return ;   //程序结束
//		}
		PageModel pm=(PageModel) request.getSession().getAttribute("pagemodel");     //getSession()范围
		if(pm==null){ 
			 //如果pm页面为空了,则创建一个新的
			pm=new PageModel(6);  
			//把创建好的pm页面设置到共享范围
			request.getSession().setAttribute("pagemodel", pm);    //与上面获得的页面 名称相同
		}
		try {
			
			//获得共享范围中的查询条件e
			Product p=(Product) request.getSession().getAttribute("p");
			//查询符合条件e的总记录数,防止首页和末页超出总页数
			pm.setTotalContent(new ProductDao().totalNumByParam(p));
			//设置末页
			pm.setCurrentPage(pm.getLastPage());    //如果能获得pm，pm肯定就有getCurrentPage
			//查找数据
			List<Product> prods = new ProductDao().selectByParametersAndPages(p,pm);
		
			//设置共享范围
			request.getSession().setAttribute("prods", prods);
			//响应页面
			response.sendRedirect("/MyShopSystem/list.jsp");
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}

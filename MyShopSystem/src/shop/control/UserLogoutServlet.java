package shop.control;
/**
 *  注销用户，重新登录
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//移除session范围中的员工
		request.getSession().removeAttribute("user");
		//使整个session失效，其他数据也失效
		//request.getSession().invalidate();
		response.sendRedirect("/MyShopSystem/ListServlet.do");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

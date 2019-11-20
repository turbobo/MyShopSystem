package shop.control;
/** 
 * 仅仅判断注册时是否重名
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.UserDao;
import shop.pojo.User;

public class UserNameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置响应编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//获得注册页面的数据
		String sname=request.getParameter("sname");  //未输入时,sname获得的是空字符串""
		if(sname==""||sname==null)
			response.getWriter().write("请输入用户名");
		
//		if(sname==null||spassword==null||ssex==null||mobile==null){
//			request.setAttribute("inputError", "请输入完整信息！");
//			response.sendRedirect("/MyShopSystem/user_regist.jsp");
//		}
		
		//先根据姓名查重,数据库中没有该用户名存在
		try {
			if(UserDao.getInstance().selectByName(sname)!=null){  //该昵称不重复时才注册新用户	
				response.getWriter().write("该昵称已被占用，请重新输入");	
			}
			//不重名时不发任何信息
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}


	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}


}

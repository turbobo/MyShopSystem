package shop.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.UserDao;
import shop.pojo.User;

public class UserRegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置响应编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//获得注册页面的数据
		String sname=request.getParameter("sname");  //未输入时,sname获得的是空字符串""
//		if(sname==""||sname==null)
//			response.getWriter().write("请输入用户名");
		String spassword=request.getParameter("spassword"); 
//		if(spassword=="")
//			response.getWriter().write("请输入密码");
		String ssex=request.getParameter("ssex");
		//从页面获得是字符串类型数据
		String mobile=request.getParameter("mobile");
		
//		if(sname==null||spassword==null||ssex==null||mobile==null){
//			request.setAttribute("inputError", "请输入完整信息！");
//			response.sendRedirect("/MyShopSystem/user_regist.jsp");
//		}
		
		//先根据姓名查重,数据库中没有该用户名存在
		try {
			if(UserDao.getInstance().selectByName(sname)==null){  //该昵称不重复时才注册新用户	
				try {  
					//写入数据库    
					if(spassword!=""&&ssex!=""&&mobile!=""){
						UserDao.getInstance().add(new User(sname,spassword,ssex,Long.parseLong(mobile)));
						response.getWriter().write("注册成功，请登录！");
						//request.getSession().setAttribute("registSuccess", "注册成功，请登录！");
					}
					//跳转到登录页面
					//response.sendRedirect("/MyShopSystem/user_login.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
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

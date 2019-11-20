package shop.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.UserDao;
import shop.pojo.User;


public class UserLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置响应编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//获得参数  统一字符集编码后，不要再转换
		String sname=request.getParameter("sname");
		//String sname=new String(request.getParameter("sname").getBytes("ISO-8859-1"),"UTF-8");
		String spassword=request.getParameter("spassword"); 
		//判断验证码输入是否正确
		String validateCode=request.getParameter("validateCode");   //输入的验证码
		if(validateCode!=null&&!"".equals(validateCode.trim())){
			String code=(String) request.getSession().getAttribute("code");  //生成的验证码
			if(code.equalsIgnoreCase(validateCode)){				
				try {
						//先去数据库查找该用户
						User user=UserDao.getInstance().findUser(sname, spassword);
						if(user!=null){
							//登录成功后，要把用户放到共享范围作为登录凭证，也方便后续读取用户登录信息
							request.getSession().setAttribute("user", user);
							response.getWriter().write("登录成功!");
							//response.sendRedirect("/MyShopSystem/ListServlet.do");
							}
							else{
								//将错误信息传到jsp
								response.getWriter().write("登录失败！用户名或密码错误！");
								//设置数据共享范围
								//request.getSession().setAttribute("loginError", "登录失败！用户名和密码错误！");
								//重新登录和上次登录没关系，可以用客户端跳转
								//response.sendRedirect("/MyShopSystem/user_login.jsp");
							}
				
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}else{
				//将错误信息传到jsp
				response.getWriter().write("验证码错误!");
				//验证码错误，重新登录
				//request.getSession().setAttribute("validateError", "验证码错误!");
				//request.getRequestDispatcher("/user_login.jsp").forward(request, response);
				//response.sendRedirect("/MyShopSystem/user_login.jsp");
			}	
		}else{   //注意和if匹配
			//验证码为空
			response.getWriter().write("请输入验证码");
			//request.getSession().setAttribute("validateError", "请输入验证码");
			//response.sendRedirect("/MyShopSystem/user_login.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}


}

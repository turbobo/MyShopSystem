<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userlogin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
	<link type="text/css" rel="stylesheet" href="bootstrap/bootstrap.min.css">
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script type="text/javascript" src="bootstrap/jquery-3.3.1.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>

  </head>
  
  <body>
    <form action="/MyShopSystem/UserLoginServlet.do" method="get">
  		<table class="table table-bordered table-hover " style="margin-left:20%;width:600px;">
	  		<tr><td align="center" colspan="2"><strong>用户登录</strong></td></tr>
	  		<tr><td>用户名</td><td><input class="btn btn-default" type="text" name="sname" id="sname" /></td></tr>
	  		<tr><td>密码</td><td><input class="btn btn-default" type="password" name="spassword" id="spassword" /></td></tr>
	  		<tr><td>验证码</td><td><img id="imgObj" alt="验证码"  src="/MyShopSystem/user_login.jsp"  />&nbsp;</td></tr>
	  		<tr><td>输入验证码</td><td><input class="btn btn-default" type="text" name="validateCode" id="validateCode" /></td></tr>
  			<tr><td align="center" colspan="2"><input id="user_login" class="btn btn-success" type="button" value="登录" />
  			&nbsp;&nbsp;&nbsp;<a href="/MyShopSystem/user_regist.jsp">新用户，请注册</a>
  			&nbsp;&nbsp;&nbsp;&nbsp;<a href="/MyShopSystem/ListServlet.do">全部商品</a></td></tr>
  		</table>
  	</form>

  
  </body>
  
  <script type="text/javascript">
    //页面加载后 执行函数
    window.onload = function(){
   	   $("#imgObj").attr("src","/MyShopSystem/ValidateCodeServlet.do?ndate="+new Date());
   }; 
   
   
  </script>
  
  <script type="text/javascript">
  $("#imgObj").click(function() {  
	        //document.getElementById("imgObj").src="/MyShopSystem/ValidateCodeServlet.do?ndate="+new Date();
	        $("#imgObj").attr("src","/MyShopSystem/ValidateCodeServlet.do?ndate="+new Date());
	    });

	
   $("#user_login").click(function() {  	    
	  $.ajax({
				 type: "POST", 
				 url: "UserLoginServlet.do", //servlet地址
				 data: {"sname" : $("#sname").val(),"spassword" : $("#spassword").val(),"validateCode" : $("#validateCode").val() }, 	//发送数据给servlet
				 async : true,                  //异步
				 dataType : "text",				//接收servlet数据类型
				 success: function(result){     //接受的数据为result
				 	if("登录成功!"==result)      //登录成功才跳转
				 		window.location.href="/MyShopSystem/ListServlet.do";
				 	alert(result);	
				}
			});
    });
    
    
   </script>
</html>

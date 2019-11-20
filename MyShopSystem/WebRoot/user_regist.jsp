<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_regist.jsp' starting page</title>
    
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
  <form action="" method="get">
  		<table class="table table-bordered table-hover " style="margin-left:20%;width:600px;">
	  		<tr><td align="center" colspan="2"><strong>用户注册</strong></td></tr>
	  		<tr><td>用户名</td><td><input id="sname" class="btn btn-default" type="text" name="sname" /></td></tr>
	  		<tr><td>密码</td><td><input id="spassword"  class="btn btn-default" type="password" name="spassword"/></td></tr>
	  		<tr><td>性别</td><td><input id="ssex"  class="radio-inline" type="radio" name="ssex" value="男" />男<input class="radio-inline" type="radio" name="ssex" value="女" />女</td></tr>
	  		<tr><td>手机号</td><td><input id="mobile"  class="btn btn-default" type="text" name="mobile"/></td></tr>
	  		<tr><td align="center" colspan="2"><input id="userregist" class="btn btn-success" type="button" value="注册" />&nbsp;&nbsp;&nbsp;<a href="/MyShopSystem/user_login.jsp">我已注册，去登录</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/MyShopSystem/ListServlet.do">全部商品</a></td></tr>
  		</table>
  	</form>
  </body>
  
  
  
  
  <script type="text/javascript">
  //判断昵称是否重复
  $("#sname").blur(function(){
    if($("#sname").val()!=""){
    	//判断昵称是否重复
  		$.ajax({
			 type: "POST", 
			 url: "UserNameServlet.do", //servlet地址
			 data: {"sname" : $("#sname").val()},
			 async : true,                  //异步
			 dataType : "text",				//接收servlet数据类型
			 success: function(result){     //接受的数据为result
				 if(result!=null&&result!="")
				 	alert(result);
				 return ;
			 }		 
		});
		
		}
	});
		  
/*  $("#spassword").blur(function(){
    if($("#spassword").val()==''){
    	alert("请输入密码！");
   		return ;
   	 } 
    });  */
  
  //判断输入信息是否正确
   $("#userregist").click(function() {
   		var sname=$("#sname").val();
		var spassword=$("#spassword").val();
		var mobile=$("#mobile").val();
		var sexs=document.getElementsByName("ssex");
		var flag=false;
		if(sname.length==0){
			alert("请输入用户名！");
		}
		if(spassword.length<6){
			alert("请输入至少6位数密码！");
		}
		for(var i=0;i<sexs.length;i++){
			if(sexs[i].checked){    //该标记是否选中
				flag=true;
				break;
			}
		}
		if(!flag){
			alert('请选择性别');
		} 
		if(mobile.length!=11){
			flag4=false;
			alert("请输入11位手机号！");
			return ;
		} 
		
		//输入信息都不为空时才发到servlet验证
		if($("#sname").val()!=""&&$("#spassword").val()!=""&&$("#ssex").val()!=""&&$("#mobile").val()!="")
		{
			$.ajax({
				 type: "POST", 
				 url: "UserRegistServlet.do", //servlet地址
				 data: {"sname" : $("#sname").val(),"spassword" : $("#spassword").val(),"ssex" : $("#ssex").val(),"mobile" : $("#mobile").val() }, 	//发送数据给servlet
				 async : true,                  //异步
				 dataType : "text",				//接收servlet数据类型
				 success: function(result){     //接受的数据为result
				 	alert(result);
				 	if("注册成功，请登录！"==result)
				 		window.location.href="/MyShopSystem/user_login.jsp"; 	
					}
				});
		}	
			
			
	  	});  
  </script>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<!-- 最新版本的 Bootstrap 核心 CSS 文件-->
		<link type="text/css" rel="stylesheet" href="bootstrap/bootstrap.min.css"> 
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script type="text/javascript" src="bootstrap/jquery-3.3.1.min.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>

  </head>
  
  <body>
  
  <input id="one" type="button" name="Submit" value="删除" style="width:80px">
  <a id="two" href="/MyShopSystem/user_login.jsp">链接 登录</a>
  
    	<div class="input-group">
	  <span class="input-group-addon" id="basic-addon1">名称</span>
	  <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
	</div>
	<div class="input-group">
	  <span class="input-group-addon" id="basic-addon1">名称</span>
	  <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
	</div>
	<div class="dropdown">
  <a class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    Dropdown
    <span class="caret"></span>
  </a>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
    <li><a href="#">Action</a></li>
    <li><a href="#">Another action</a></li>
    <li><a href="#">Something else here</a></li>
    <li role="separator" class="divider"></li>
    <li><a href="#">Separated link</a></li>
  </ul>
</div>

<ul class="nav nav-tabs">
  <li role="presentation" class="active"><a href="/MyShopSystem/ListServlet.do">商品列表</a></li>
  <li role="presentation"><a href="/MyShopSystem/user_login.jsp">登录</a></li>
  <li role="presentation"><a href="/MyShopSystem/user_regist.jsp">注册</a></li>
</ul>




      <ul class="nav ">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">管理员 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">登录</a></li>
            <li><a href="#">注销</a></li>

          </ul>
        </li>
      </ul>

      <ul class="nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">顾客 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">登录</a></li>
            <li><a href="#">注销</a></li>
          </ul>
        </li>
      </ul>




  
  <script type="text/javascript">
	//function delcfm() {
	$("#one").click(function(){
		if (window.confirm("您确定要删除吗？")) {
            //window.event.returnValue = false;
            alert("确认");
            window.location.href='/MyShopSystem/user_login.jsp';
        }
        else
        	window.location.href='/MyShopSystem/user_regist.jsp';
	});
	$("#two").click(function(){
		if (window.confirm("您确定要跳转吗？")) {
            //window.event.returnValue = false;
            alert("确认");
            window.location.href='/MyShopSystem/user_login.jsp';
        }
        else{
    	      event.preventDefault(); //阻止默认跳转
        }
	});
    
        
 /*   var x;
    var r=confirm("按下按钮!");
    if (r==true){
        x="你按下了\"确定\"按钮!";
    }
    else{
        x="你按下了\"取消\"按钮!";
    }
    alert(x);  */
	</script>
  </body>
</html>

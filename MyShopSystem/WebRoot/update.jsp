<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update.jsp' starting page</title>
    
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
   <form action="/MyShopSystem/UpdateSubmitServlet.do">
  		<table class="table table-bordered table-hover " style="margin-left:20%;width:600px;">
	  		<tr><td align="center" colspan="2"><strong>购物系统·修改</strong></td></tr>
	  		<tr><td>商品名称</td><td><input type="text" name="sname" value="${p.sname}"/></td></tr>
	  		<tr><td>商品单价</td><td><input type="text" name="price" value="${p.price}"/></td></tr>
	  		<tr><td>商品描述</td><td><input type="text" name="sdesc" value="${p.sdesc}"/></td></tr>
	  		<tr><td>商品类别</td><td><input type="text" name="scategory" value="${p.scategory}"/></td></tr>
	  	<!--οnclick="javascript:window.location.href='/MyShopSystem/ListServlet.do'"    直接在input上跳转无效  
	  		 可以用<a href="/MyShopSystem/ListServlet.do">back</a> -->
	  		<tr><td align="center" colspan="2"><input type="submit" value="修改"/>&nbsp;<input type="reset" value="重置" />&nbsp;<input id="backToList" type="button" value="返回" /></td></tr>
  		</table>
  	<!--  将要修改的商品id值获得,方便到数据库更改是需要id值  -->
    <input type="hidden" value="${p.id }" name="id" />
  	</form>
  </body>
  <script type="text/javascript">
  	$("input").addClass("btn btn-default");
  	$("#backToList").click(function(){
  		window.location.href='/MyShopSystem/ListServlet.do';
  	});
  </script>

</html>

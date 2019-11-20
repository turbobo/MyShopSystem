<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showorderdetail.jsp' starting page</title>
    
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
	<style type="text/css">
		td,th{
			text-align:center;
		}
	</style>
  </head>
  
  <body>
   <div class="container">
	  	<table class="table table-bordered table-hover " >
	  		<tr class="info"><th colspan="6">订单号：${uoid} <a style="position:absolute; margin-left:310px;" href="/MyShopSystem/ListServlet.do">商品列表</a><a style="position:absolute; margin-left:400px;" href="/MyShopSystem/ShowOrderServlet.do">订单中心</a></tr>
	  		<tr><th>商品名称</th><th>商品单价</th><th>商品描述</th><th>商品类别</th><th>商品数量</th></tr>
	  		<c:forEach items="${ods}" var="od">
		    	<tr>
		    		<td>${od.pname }</td>
		    		<td>${od.price }</td>	
		    		<td>${od.sdesc }</td>
		    		<td>${od.scategory }</td>
		    		<td>${od.buyamount }</td>
		    	</tr>
	    	</c:forEach>	    	
		</table>
	</div>
  </body>
</html>

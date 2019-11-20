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
    
    <title>My JSP 'showorder.jsp' starting page</title>
    
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
		a{
			cursor:pointer;
		}
	</style>
	</head>
  
  <body>
    <div class="container">
   	 <form method="get">
	  	<table class="table table-bordered table-hover " >
	  		<tr class="info"><th colspan="6">订单中心 <a style="position:absolute; margin-left:380px;" href="/MyShopSystem/ListServlet.do">商品列表</a><a style="position:absolute; margin-left:470px;" href="/MyShopSystem/ShowCartServlet.do">购物车</a></tr>
	  		<tr><th>订单编号</th><th>订单总数</th><th>订单总价</th><th>订单状态</th><th>操作</th></tr>
	  		<c:forEach items="${orders }" var="o">
		    	<tr>
		    		<td>${o.oid }</td>
		    		<td>${o.totalNum }</td>	
		    		<td>${o.totalPrice }</td>
		    		<td>${o.status }</td>
		    		<td>
		    		<c:if test="${o.status=='已支付' }">
		    			<span>已支付</span>
		    		</c:if>
		    		<c:if test="${o.status=='未支付' }">
		    			<a id="pay${o.oid }" onclick="pay(${o.oid })">支付</a>&nbsp;
		    		</c:if>
		    		<a href="/MyShopSystem/OrderdetailServlet.do?oid=${o.oid }">订单详情</a>&nbsp;
		    		<a href="/MyShopSystem/DeleteOrderServlet.do?oid=${o.oid }">删除</a>&nbsp;
		    		</td>
		    	</tr>
	    	</c:forEach>	    	
		</table>
	  </form>
	</div>
  </body>
  <script type="text/javascript">
	function pay(oid){
		//确认支付才跳转
		if(window.confirm("立即支付？")) {
				alert("支付成功！");
				//$("#pay"+oid).innerText="已支付";
				//$("#pay"+oid).attr("disabled",true); 
	            window.location.href='/MyShopSystem/OrderPayServlet.do?oid='+oid;    
	        }
	}
  </script>
  
</html>

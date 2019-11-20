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
    
    <title>My JSP 'shopcart.jsp' starting page</title>
    
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
    <form method="get" action="/MyShopSystem/MakeOrderServlet.do">
	  	<table class="table table-bordered table-hover " >
	  		<tr class="info"><th colspan="6">购物车 <a style="position:absolute; margin-left:410px;" href="/MyShopSystem/ListServlet.do">商品列表</a><a style="position:absolute; margin-left:480px;" href="/MyShopSystem/ShowOrderServlet.do">订单中心</a></th></tr>
	  		<tr><th>商品名称</th><th>商品单价</th><th>商品描述</th><th>数量</th></tr>
	  		<!--  无论是只查询所有还是条件查询，都会返回prods数组     将prods数组中的每个商品赋值为p -->
	  		<c:forEach items="${shopcarts }" var="sc">
	    	<tr>
	    		<td>${sc.pname }</td>
	    		<td>
		    		<c:if test="${0==sc.price}">	
				  	</c:if>
				  	<c:if test="${0!=sc.price}">
				  		${sc.price }
				  	</c:if>	
				</td>	
	    		<td>${sc.pdesc }</td>
	    		<td>
	    		<!--  
		    		<button onclick="minus(${sc.cart_id })" id="minus_${sc.cart_id }" class="minus btn btn-default" ><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button>
		    		<input class="btn disabled" id="input_amount_${sc.cart_id }" style="width:50px;" type="text" value="${sc.buyamount }" />
		    		<button onclick="plus(${sc.cart_id })" id="plus_${sc.cart_id }" class="btn btn-default"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
		    		-->
		    		<input name="pid" type="hidden" value="${sc.pid }"/>
		    		<input class="btn" name="buycount" id="input_amount_${sc.cart_id }" style="width:50px;" type="text" value="${sc.buyamount }" />
		    		<a style="cursor:pointer;" onclick="deletecart(${sc.cart_id })">删除</a>
		    	</td>
	    		<!-- <td>${sc.totalPrice }</td> -->
	    	</tr>
	    	</c:forEach>	    	
		</table>
		<input style="margin-left:1080px;" type="submit" value="结算"/>
		</form>
	</div>
  </body>
  
  
    <script type="text/javascript">
  		$("input").addClass("btn btn-default");
  		
  		function deletecart(cart_id){
  			window.location.href="/MyShopSystem/DeleteCartServlet.do?cart_id="+cart_id;
  		}
 
  	</script>	
  	
  	<script type="text/javascript">
  	/*
  		function minus(cart_id){
  			//转为int后就可以直接加减
  			var amount=parseInt($("#input_amount_"+cart_id).val());
	  		//购买数量最少为1
	  		if(amount>1){ 
	  			$("#input_amount_"+cart_id).val(amount-1);		
	  		//window.location.href='/MyShopSystem/DecreaseCartServlet.do?cart_id='+cart_id;
	  		}		
  		}
  		
  		function plus(cart_id){
	  		var amount=parseInt($("#input_amount_"+cart_id).val());
  			$("#input_amount_"+cart_id).val(amount+1);
  			window.location.href='/MyShopSystem/IncreaseCartServlet.do?cart_id='+cart_id;
  			/*$.ajax({
				 type: "POST", 
				 url: "IncreaseCartServlet.do", //servlet地址
				 data: {"cart_id" : cart_id }, 	//发送数据给servlet
				 async : true,                  //异步
				 dataType : "text",				//接收servlet数据类型
				 success: function(result){     //接受的数据为result
				 	alert(result);
				}
			}); 
  		}  */
  	</script>
  	
</html>

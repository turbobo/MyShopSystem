<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
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
		<style type="text/css">
			td,th{
				text-align:center;
			}
			a{
				cursor:pointer;
				text-decoration:none;
			}
			ul{
				list-style:none;
				display:inline-block;
			}
		</style>
		
  </head>
  
  <body>
  
  	<form class="form-inline" action="/MyShopSystem/SelectServlet.do" style="margin-left:38px;">
	  <button type="button" class="btn btn-default btn-default active">请输入查询条件</button>
	  <div class="form-group">
	    <label>名称</label>
	    <input class="in_search" name="sname" type="text" placeholder="商品名称" value="${p.sname }" />
	  </div>
	  <div class="form-group">
	    <label>单价</label>
	    	<!-- 上限为0  都不显示 -->	
	    	<c:if test="${0==p.upperPrice}">
	    		<input style="width:90px;" class="in_search" name="lowerPrice" type="text" placeholder="商品单价" />--	
	  			<input style="width:90px;" class="in_search" name="upperPrice" type="text" placeholder="商品单价" />	    		
	    	</c:if>
	    	<c:if test="${0!=p.upperPrice}">
		  		<input style="width:90px;" class="in_search" name="lowerPrice" type="text" placeholder="商品单价" value="${p.lowerPrice }" />--	
		  		<input style="width:90px;" class="in_search" name="upperPrice" type="text" placeholder="商品单价" value="${p.upperPrice }" />
			</c:if>
	  </div> 
	  <div class="form-group">
	    <label>描述</label>
	    <input class="in_search" name="sdesc" type="text" placeholder="商品描述" value="${p.sdesc }" />
	  </div>
	   <div class="form-group">
	    <label>类别</label>
	    <input class="in_search" name="scategory" type="text" class="form-control" placeholder="商品类别" value="${p.scategory }" />
	  </div>
	  <div class="btn-group" role="group">
  		 <input type="submit" value="查询"/>
  		 <input type="button" value="重置" id="in_clear"/>
  		</div>
	</form>
	
	<div class="container">
		<form method="get">
	  	<table class="table table-bordered table-hover table-condensed" >
	  		<tr class="info"><th colspan="6">
	  		
	<span style="position: absolute; margin-left:-560px;" >	 
			<ul>
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">管理员 <span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a href="/MyShopSystem/admini_login.jsp">登录</a></li>
		            <li><a href="/MyShopSystem/AdminiLogoutServlet.do">注销</a></li>
		
		          </ul>
		        </li>
		      </ul>
	  	<!--  	<a style="position: absolute; margin-left:-530px;" href="/MyShopSystem/admini_login.jsp">管理员登录</a>
	  		<a style="position:absolute; margin-left:-445px;" href="/MyShopSystem/AdminiLogoutServlet.do">注销</a>
	  		-->
	  		<ul>
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">顾客 <span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a href="/MyShopSystem/user_login.jsp">登录</a></li>
		             <li><a href="/MyShopSystem/user_regist.jsp">注册</a></li>
		            <li><a href="/MyShopSystem/UserLogoutServlet.do">注销</a></li>
			
		          </ul>
		        </li>
		      </ul> 
	 </span>    
		      商品详情</tr>
		      
	  		<!--<a style="position:absolute; margin-left:410px;" href="/MyShopSystem/user_login.jsp">顾客登录</a> 
	  		<a style="position:absolute; margin-left:480px;" href="/MyShopSystem/UserLogoutServlet.do">注销</a></th></tr>
	  		-->
	  		<tr><th>商品名称</th><th>商品单价</th><th>商品描述</th><th>商品类别</th><th>管理员操作<span>&nbsp;</span><a id="add">添加商品</a></th><th><div class="btn-group" role="group"><a href="/MyShopSystem/ShowCartServlet.do">购物车</a>&nbsp;<a href="/MyShopSystem/ShowOrderServlet.do">订单中心</a></div></th></tr>
	  		<!--  无论是只查询所有还是条件查询，都会返回prods数组     将prods数组中的每个商品赋值为p -->
	  		<c:forEach items="${prods }" var="p">
	    		<tr><td>${p.sname }</td><td>${p.price }</td><td>${p.sdesc }</td><td>${p.scategory }</td><td><div class="btn-group" role="group"><input type="button" value="修改" onclick="update(${p.id })" /><input type="button" value="删除" onclick="dele(${p.id })" /></div></td>
	    		<td><input type="button" value="加入购物车" onclick="buy(${p.id})" /></td></tr>
	    	</c:forEach>
		</table>
		</form>
		<nav aria-label="Page navigation" style="margin-left:40%;">
		  <ul class="pagination">
		  <li>
		      <a href="/MyShopSystem/DoFirstPageServlet.do" aria-label="First">
		        <span aria-hidden="true">首页</span>
		      </a>
		    </li>
		    <li>
		      <a id="previous" href="/MyShopSystem/DoPreviousPageServlet.do" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <li> <span aria-hidden="true">${pagemodel.currentPage }</span></li>
		    <li>
		      <a id="next" href="/MyShopSystem/DoNextPageServlet.do" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		    <li>
		      <a href="/MyShopSystem/DoLastPageServlet.do" aria-label="Last">
		        <span aria-hidden="true">末页</span>
		      </a>
		    </li>		    
		  &nbsp;&nbsp;到第<input type="text" id="goPage" style="width:48px;" value="${pagemodel.currentPage }" />页<a style="cursor:pointer;" id="dogoPage" onclick="dogoPage()">GO</a>
		  &nbsp;共<span id="totalPage"><font color="red" >${pagemodel.lastPage }</font></span>页  
		  </ul>
		</nav>
	</div>
	
  </body>
  <script type="text/javascript">
  	$("input").addClass("btn btn-default");
  	$("#add").click(function(){
  		window.location.href='/MyShopSystem/AdminiAddProServlet.do';
  	});
  	//input的class加了btn btn-default
  	$("#in_clear").click(function(){
  		//$("#ca").val("");
  		$(".in_search").val("");	
  	});
  </script>
  <script type="text/javascript">
    	function dele(did){
    		window.location.href='/MyShopSystem/DeleteServlet.do?did='+did;
    	}
    	function update(uid){
    		window.location.href='/MyShopSystem/UpdateServlet.do?uid='+uid;
    	}
    	function buy(bid){
    		window.location.href='/MyShopSystem/ProductToCartServlet.do?bid='+bid;
    	}
    	
    	function dogoPage(){
    	//$("#dogoPage").click(function(){
    		//目标页面也不能大于总页面
    		var totalPage=document.getElementById("totalPage").innerText;    //从数据库获得总页数
    		var goPage=document.getElementById("goPage").value;  //目标页面
    		if(goPage>totalPage||goPage<=0)
    			return ;
    		else
    			window.location.href='/MyShopSystem/DoGoPageServlet.do?goPage='+goPage;
    	//});
    	}
    	
    	//当前页面是首页时,无法前进,禁用按钮
    	if(${pagemodel.currentPage }<=1){
    		$("#previous").addClass("btn btn-primary disabled");
    	}
    	//前端页面判断是否超出范围
    	/*已经禁用按钮，不用你再加onclick判断,直接用a标签href跳转即可
    	function dopreviousPage(){
    		//当前页面已经第一页时，无法前进
    		if(${pagemodel.currentPage }<=1){
    			return ;
    		}
    		else{
    		alert(1);
    			window.location.href='/MyShopSystem/DoPreviousPageServlet.do';
    		}
    	}*/
    	
    	//当前页面是末页时,无法前进,禁用按钮
    	if(${pagemodel.currentPage }>=${pagemodel.lastPage }){
    		$("#next").addClass("btn btn-primary disabled");
    	}
    	/*已经禁用按钮，不用你再加onclick判断,直接用a标签href跳转即可   
    	function donextPage(){ 
    		//当前页面已经是  末页lastPage   
    		if(${pagemodel.currentPage }>=${pagemodel.lastPage }){
    			return ;
    		}
    		else{
    			window.location.href='/EmpManager_ELJSTL/DoNextPageServlet.do';
    		}
    	}*/
    	
    </script>
</html>

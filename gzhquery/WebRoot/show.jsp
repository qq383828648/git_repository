<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'show.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

 	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="plugin/showloading/jquery.showLoading.js"></script>
    <script type="text/javascript">
    $(function(){
    	jQuery('#sl').showloading();
    });
    	/* function tests(){
    		var ss = $( "#sl" );
			console.log( ss );
			jQuery('#sl').hideLoading();
    	} */
    </script>
   </head>
  
  <body>
  	<dir>
  		<!-- <input type="button" onclick="tests();"/> -->
  	</dir>
	<div id="sl">
		hello hello;
	</div>	
  </body>
</html>

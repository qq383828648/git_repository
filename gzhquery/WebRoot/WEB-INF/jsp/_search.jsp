<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		
		<title>冠字号结果查询</title>
		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/blue/button.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/blue/query.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugin/jeazyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugin/jeazyui/themes/icon.css">
		<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
		<script src="${pageContext.request.contextPath}/js/resultlist.js"></script>
		<script src="${pageContext.request.contextPath}/plugin/jeazyui/jquery.easyui.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugin/jeazyui/easyui-lang-zh_CN.js"></script>
		
		<script>
		function showInfo(){
	  	 	var info = document.getElementById("errorInfo");
			info.innerHTML = "<font style='color:#999999; font-size:12px;'>请输入1-20位的冠字号！</font>";
		}

		function searchResult(){
			var dealNo = document.getElementById("dealNo").value;
	  	 	var errInfo = document.getElementById("errorInfo");
			if(dealNo == ""){
				errInfo.innerHTML = "<font style='color:red; font-size:12px;'>冠字号不能为空，请输入冠字号！</font>";
				return false;
			} else if (/\s/.test(dealNo)) {
				errInfo.innerHTML = "<font style='color:red; font-size:12px;'>冠字号不能包含空格，请重新输入！</font>";
				return false;
			} else {
				errInfo.innerHTML = "";
				document.getElementById("search").disabled = true;
				document.getElementById("running").style.display = 'block';
		 		resultList(dealNo);
		 		resultDiv.style.display = 'block';
				document.getElementById("search").disabled = false;
				document.getElementById("running").style.display = 'none';
		 		
		 	/* 	$.ajax({                                             
		  	 	  type: "POST",                                 
		  	 	  url: "${pageContext.request.contextPath}/GZHQueryServlet?method=queryResult",
		  	 	  data: "dealNo="+dealNo,
		  	 	  success: function(data){
	  	 			var resultDiv = document.getElementById("datagrid");
		  	 	    if( data != "notfound" ){
						resultList(dealNo);
						resultDiv.style.display = 'block';
						document.getElementById("search").disabled = false;
						document.getElementById("running").style.display = 'none';
					}else{
						resultDiv.innerHTML = "<font style='color:#0000FF; font-size:25px;'>对不起，您要查找的冠字号不存在！</font>";
						resultDiv.style.display = 'block';
						document.getElementById("search").disabled = false;
						document.getElementById("running").style.display = 'none';
						}
					}
	  	 	 	});  */
			}
		}
		</script>
	</head>

	<body>
		<div style="background-color:#F3F9FD;margin:0 auto;width: 1000px;">
			<div id="resultDiv">
				<table width="100%">
					<tr>
						<td height="60" width="273px">
							<img src="${pageContext.request.contextPath}/style/blue/images/banklogo.jpg" >									
						</td>
						<td align="center">
							<font style="font-size: 35px;font-family: 'Arial', 'Tahoma', '微软雅黑', '雅黑'">冠字号结果查询</font>
						</td>
					    <td align="right" width="273px">
					    	<span style="font-size:13px;">
					    		<a href="<%=basePath %>index.jsp" style="text-decoration:none;">尊敬的${sessionScope.user.username},您好</a>
					    		<font style="color: #999999;">|</font>
					    		<a href="${pageContext.request.contextPath}/GZHQueryServlet?method=exit" style="text-decoration:none;">退出登录</a>
					    	</span>
						</td>
					</tr>
				</table>
			</div>
			<div style="border: 1px solid graytext; background: url('${pageContext.request.contextPath}/style/blue/images/bg.png') repeat-x ; height: 540px;">
				<form id="form" name="form" method="post" style="padding-top: 100px;">
					<table style="" align="center" cellpadding="0">
						<tr>
							<td>
								<font size="5" >冠 字 号</font>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td>
								<input type="text" id="dealNo" name="dealNo" maxlength="20" style="border: 1px solid rgb(115, 155, 192);background:transparent;" onfocus="showInfo();" size="24">&nbsp;
							</td>
							<td width="35">
								<div id="running" style="display: none;">
									<img src="${pageContext.request.contextPath}/style/blue/images/loading.gif"></img>
								</div>
							</td>
							<td>
								<input type="button" id="search" class="btn26 submit_btn" value="冠字号查询" onClick="searchResult();">
								</div>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div id="errorInfo"></div>
							</td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</form>
				<div style="width: 900px;height: auto;display: none; margin-left: 50px;margin-right: 50px" align="center">
					<table id="datagrid" cellspacing="0" cellpadding="0"> 
						
					</table>
				</div>
			</div>
			<br />
			<div align="center">
				<hr style="border: 1 dashed;border-bottom: 1px solid graytext;">
				<span style="color:#999999; font-family:'宋体'; font-size: 13;">粤ICP备06107890号  版权所有:深圳宝嘉电子设备有限公司 
				<br /> Copyright (c) 2004-2008 www.poka.com.cn All rigths reserved. 
				</span>
			</div>
		</div>
	</body>
</html>

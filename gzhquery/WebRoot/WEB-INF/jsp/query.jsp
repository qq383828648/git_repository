<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		
		<title>宝嘉人民币冠字号追踪系统</title>
		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/blue/button.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/blue/query.css">
		<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
		
		<script>
		 
		function date2str(x,y) {
			 var z = {M:x.getMonth()+1,d:x.getDate(),h:x.getHours(),m:x.getMinutes(),s:x.getSeconds()};
			 y = y.replace(/(M+|d+|h+|m+|s+)/g,function(v) {return ((v.length>1?"0":"")+eval('z.'+v.slice(-1))).slice(-2)});
			 return y.replace(/(y+)/g,function(v) {return x.getFullYear().toString().slice(-v.length)});
		}
		
		//聚焦冠字号文本框
		window.onload = function(){
			document.getElementById("dealNo").focus();
			
		}
    

		
		function toUpp(){
			 document.getElementById("dealNo").value = document.getElementById("dealNo").value.toUpperCase(); 
		}
		
		function showInfo(){
	  	 	var info = document.getElementById("errorInfo");
	  	 	var dealNo = (document.getElementById("dealNo").value).replace(/\s+/g,""); 
			document.getElementById("dealNo").value = dealNo;
			info.innerHTML = "<font style='color:#999999; font-size:12px;'>请输入完整的冠字号！冠字号长度为10-12位,可以用‘*’代替冠字号中的字符</font>";
		}

		function searchResult(){
			var dealNo = (document.getElementById("dealNo").value).replace(/\s+/g,""); 
			document.getElementById("dealNo").value = dealNo;
	  	 	var errInfo = document.getElementById("errorInfo");
	  	 
			if(dealNo == ""){
				errInfo.innerHTML = "<font style='color:red; font-size:12px;'>冠字号不能为空，请输入冠字号！</font>";
				document.getElementById("dealNo").focus();
				return false;
			}/* else if (/\s/.test(dealNo)) {
				errInfo.innerHTML = "<font style='color:red; font-size:12px;'>冠字号不能包含空格，请重新输入！</font>";
				return false;
			}*/ else {
				errInfo.innerHTML = "";
				document.getElementById("search").disabled = true;
				document.getElementById("running").style.display = 'block';
		 	    var login= document.getElementById("login").value;
		 		$.ajax({                                             
		  	 	  type: "POST",                                 
		  	 	  url: "${pageContext.request.contextPath}/GZHQueryServlet?method=queryResult&login="+login,
		  	 	  data: "dealNo="+dealNo,
		  	 	  success: function(data){
	  	 			var resultDiv = document.getElementById("searchResult");
		  	 	    if( data != "notfound" ){
			  	 	  	var resultStr = eval(data);
			  	 		var contentStr = "";
			  	 		var resultStrLength = resultStr.length;
			  	 		for( var i = 0;i< resultStr.length;i++ ){
			  	 			if( resultStr[i].imagepath == undefined ){
			  	 				resultStr[i].imagepath = "poka";
			  	 			}
			  	 			if( resultStr[i].trueflag == "1" ){
			  	 				resultStr[i].trueflag = "真";
			  	 			}else{
			  	 				resultStr[i].trueflag = "假";
			  	 			}

			  	 			//-------------------------------------------------update_lb-------------------------------------------
			  	 			//这里代码的修改是用于商行Mysql数据库
			  	 			if( resultStr[i].pertype == "00" ){
			  	 				resultStr[i].pertype = "点钞机";
			  	 			}
			  	 			if( resultStr[i].pertype == "01" ){
			  	 				resultStr[i].pertype = "ATM";
			  	 			}
			  	 			if( resultStr[i].pertype == "02" ){
			  	 				resultStr[i].pertype = "清分机";
			  	 			}
			  	 			
			  	 			if( resultStr[i].businesstype == "0" ){
			  	 				resultStr[i].businesstype = "清分";
			  	 			}
			  	 			if( resultStr[i].businesstype == "1" ){
			  	 				resultStr[i].businesstype = "存款";
			  	 			}
			  	 			if( resultStr[i].businesstype == "2" ){
			  	 				resultStr[i].businesstype = "取款";
			  	 			}
			  	 			if( resultStr[i].businesstype == "3" ){
			  	 				resultStr[i].businesstype = "加钞";
			  	 			}
			  	 			if( resultStr[i].businesstype == "4" ){
			  	 				resultStr[i].businesstype = "回收";
			  	 			}

			  	 			if( resultStr[i].businesstype == undefined ){
			  	 				resultStr[i].businesstype = "-";
			  	 			}
			  	 			
			  	 			//-------------------------------------------------update_lb-------------------------------------------
			  	 			if( resultStr[i].montype == "CNY" ){
			  	 				resultStr[i].montype = "人民币";
			  	 			}else{
			  	 				resultStr[i].montype = "其它";
			  	 			}
			  	 			//修改时间2014年8月29日 14:55:06
			  	 			//这里获取字符串webapps的长度
			  	 			var str = "webapps";
			  	 			
			  	 			var num = resultStr[i].imagepath.indexOf("webapps");
							//alert("http://"+"localhost:8080"+resultStr[i].imagepath.substring(num+str.length));
			  	 			//alert("http://"+resultStr[i].ipaddr+":8081"+resultStr[i].imagepath.substring(num+str.length));
			  	 			contentStr= contentStr + "<tr align='middle'>" 
			  	 					//+"<td>"+resultStr[i].coltime+"</td>"
			  	 					//+"<td width='200px'><div ><img class='queryResult' width='25' height='190' src='${pageContext.request.contextPath}/B1H7216138_20131011101152.bmp'></div></td>"
			  	 					//+"<td width='150px'><div ><img  height='20' src='http://"+resultStr[i].ipaddr+":8080"+resultStr[i].imagepath.substring(num+str.length)+"'></div></td>"
			  	 					+"<td width='180px'><div ><img  height='20' src='"+resultStr[i].imagepath.substring(num+str.length)+"'></div></td>"
			  	 					+"<td width='80px'>"+resultStr[i].mon+"</td>"
			  	 					//+"<td>"+resultStr[i].montype+"</td>"
			  	 					+"<td width='40px'>"+resultStr[i].monval+"</td>"
			  	 					+"<td width='40px'>"+resultStr[i].monver+"</td>"
			  	 					//+"<td>"+resultStr[i].trueflag+"</td>"
			  	 					//+"<td>"+resultStr[i].quanlity+"</td>"
			  	 					//+"<td>"+resultStr[i].imagesno+"</td>"
			  	 					//+"<td>"+resultStr[i].operatorid+"</td>"
			  	 					+"<td width='80px'>"+resultStr[i].operdate+"</td>"
			  	 					+"<td width='80px'>"+resultStr[i].percode+"</td>"
			  	 					//+"<td width='80px'>"+resultStr[i].pertype+"</td>"
			  	 					//+"<td>"+resultStr[i].atmcode+"</td>"
			  	 					//+"<td>"+resultStr[i].scantime+"</td>"
			  	 					+"<td width='80px'>"+resultStr[i].bankno+"</td>"
			  	 					+"<td width='130px'>"+resultStr[i].bankname+"</td>"
			  	 					+"<td width='80px'>"+resultStr[i].agencyno+"</td>"
			  	 					+"<td width='130px'>"+resultStr[i].branchname+"</td>"
			  	 					//	+"<td>"+resultStr[i].bundleid+"</td>"
			  	 					+"<td width='100px'>"+resultStr[i].businesstype+"</td>"
			  	 					+"</tr>"
			  	 			
			  	 		}
			  	 		//<div style="width:100%;height:100;overflow-x:hidden;overflow-y:scroll">
			   			resultDiv.innerHTML = "<div style='border:1px solid #9db3c5;width:90%;height:300px;overflow-x:hidden;overflow-y:scroll' id='contentDiv'><table class='t2'  id='contentValue'>"
			  	 					+"<thead>"
			  	 					//+"<th>记录时间</th>"
			  	 					+"<th>冠字号图片</th>"
			  	 					+"<th>冠字号</th>"
			  	 					//+"<th>币种</th>"
			  	 					+"<th>币值</th>"
			  	 					+"<th>版别</th>"
			  	 					//+"<th>真假</th>"
			  	 					//+"<th>成色</th>"
			  	 					//+"<th>冠字号图片</th>"
			  	 					//+"<th>操作员ID</th>"
			  	 					+"<th>日期</th>"
			  	 					+"<th>设备编号</th>"
			  	 					//+"<th>处理类型</th>"
			  	 					//+"<th>ATM编号</th>"
			  	 					//+"<th>加钞时间</th>"
			  	 					+"<th>银行编号</th>"
			  	 					+"<th>银行名称</th>"
			  	 					+"<th>网点编号</th>"
			  	 					+"<th>网点名称</th>"
			  	 					//+"<th>捆编号</th>"
			  	 					+"<th>业务类型</th>"
			  	 				
			  	 					+"</thead>"
			  	 					+contentStr
			  	 					+"<tr>"
			  	 					+"<td colspan='12' align='center'>"
			  	 					+"</td>"
			  	 					+"</tr>"
									+"<table></div>";

							
				  	  	   var table = document.getElementById("contentValue");//根据table的 id 属性值获得对象    
				  	       var rows = table.getElementsByTagName("tr");//获取table类型的tr元素的列表  
				  	       for(var i = 0; i < rows.length; i++){  
				  	           if(i % 2 == 0){  
				  	               rows[i].style.backgroundColor = "#e8f3fd";//偶数行时背景色为#e8f3fd  
				  	           }  
				  	           else{  
				  	               rows[i].style.backgroundColor = "White";//单数行时背景色为white  
				  	           }  
				  	       }  
							resultDiv.style.display = 'block';
							document.getElementById("search").disabled = false;
							document.getElementById("running").style.display = 'none';
							
							var bannerDiv = document.getElementById("bannerDiv");
							var contentDiv =  document.getElementById("contentDiv");
							var form =  document.getElementById("form");
							if(resultStrLength > 13){
								contentDiv.style.height = "550px";
								bannerDiv.style.height = "800px";
								form.style.cssText = "padding-top: 50px;"
								
							}
							
							if(resultStrLength < 13){
								contentDiv.style.height = "400px";
								bannerDiv.style.height = "650px";
								form.style.cssText = "padding-top: 50px;"
							}
							errInfo.innerHTML = "<font style='color:#999999; font-size:13px;'>人民币追踪系统帮您查找到</font>"+resultStrLength+"<font style='color:#999999; font-size:13px;'>条相关记录</font>";
						}else{
							resultDiv.innerHTML = "<font style='color:#0000FF; font-size:25px;'>对不起，您要查找的冠字号不存在！</font>";
							resultDiv.style.display = 'block';
							document.getElementById("search").disabled = false;
							document.getElementById("running").style.display = 'none';
						}
					}
	  	 	 	}); 
			}
		}
		
		
 		$(document).keyup(function(event){
		  if(event.keyCode ==13){
			searchResult();
			document.getElementById("dealNo").blur();
		  }
		});  
		</script>
	</head>

	<body>
	<input type="hidden" value=${login }  id = "login" name="login"/>
			<div style="border: 1px solid graytext; background: url('${pageContext.request.contextPath}/style/blue/images/banner.jpg') repeat-x ; height: 600px;" id="bannerDiv" >
				<div  style="padding-top: 15px;">
					<h1 ><font size="6"  color="#FFFFFF" face="微软雅黑">&nbsp;POKA&nbsp;</font><font size="4"  color="#FFFFFF"  face="华文楷体">人民币冠字号追踪系统欢迎您</font></h1>
				</div>
				
				<form action="" id="form" name="form" method="post" style="padding-top: 230px;">
					<div  style='width:100%' align = "center">
					<table style="height: 70px; " align="center" cellpadding="0" >
						<tr>
							<td  style="text-overflow:ellipsis;word-break:keep-all; white-space:nowrap; height: 30px;">
								<font size="4"><b>请输入冠字号：</b></font>&nbsp;
								<input type="text" id="dealNo" name="dealNo" maxlength="20" style="border: 1px solid rgb(115, 155, 192);background:transparent; height:30px;" onblur="showInfo();toUpp();" size="30">&nbsp;
							</td>
							<td width="35px">
								<div id="running" style="display: none;">
									<img src="${pageContext.request.contextPath}/style/blue/images/loading.gif"></img>
								</div>
							</td>
							<td>
								<input type="button" id="search" class="btn26" value="查询" onClick="return searchResult();" />
							</td>
							<td>
								<input type="text" style="display: none" id="searchCount" name="searchCount">
							</td>
						</tr>
						<tr>
							<td>
								<div id="errorInfo" style="width: 280px;height: 40px;float: right;"></div>
							</td>
						</tr>
					</table>
					</div>
				</form>
				<h3></h3>
				<div id="searchResult" style="overflow: auto;width: auto;height: auto;display: none; margin-left: 5px;margin-right: 5px" align="center" >
				</div>
			</div>
			<br />
			<div align="center">
				<hr style="border: 1px dashed;border-bottom: 1px solid graytext;">
				<span style="color:#999999; font-family:'宋体'; font-size: 13;">版权所有:深圳宝嘉电子设备有限公司 
				<br /> Copyright (c) 2008-2015 www.poka.com.cn All rigths reserved. 
				</span>
			</div>
	
	</body>
</html>

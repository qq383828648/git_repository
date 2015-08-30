<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugin/jeazyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugin/jeazyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/blue/button.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/blue/query.css">
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script src="${pageContext.request.contextPath}/js/resultlist.js"></script>
<script src="${pageContext.request.contextPath}/plugin/jeazyui/jquery.easyui.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/jeazyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" charset="utf-8">
	/* var dataGrid;
	$( function(){
		dataGrid = $('#dataGrid').datagrid({
				url : '${pageContext.request.contextPath}/IndexServlet',
				fit : true,
				fitColumns : false,
				border : false,
				pagination : true,
				idField : 'id',
				pageSize : 20,
				pageList : [ 20, 40, 60, 80, 100 ],
				checkOnSelect : false,
				selectOnCheck : false,
				striped : true,
				nowrap : true,
				frozenColumns : [ [ {
					field : 'mon',
					title : '冠字号',
				}, {
					field : 'monval',
					title : '币值',
					sortable : true
				} ] ],
				columns : [ [ {
					field : 'monver',
					title : '版别',
				}, {
					field : 'operdate',
					title : '日期',
					sortable : true
				}, {
					field : 'percode',
					title : '设备编号',
					sortable : true
				}, {
					field : 'pertype',
					title : '设备类型',
					width : 0,
				}, {
					field : 'bankno',
					title : '银行编号',
				}, {
					field : 'bankname',
					title : '银行名称',
					width : 0,
				}, {
					field : 'agencyno',
					title : '网点编号',
				}, {
					field : 'branchname',
					title : '网点名称',
				}, {
					field : 'bundleid',
					title : '捆标签',
				} ] ],
			}); 
	}); */
</script>
<script>
		function date2str(x,y) {
			 var z = {M:x.getMonth()+1,d:x.getDate(),h:x.getHours(),m:x.getMinutes(),s:x.getSeconds()};
			 y = y.replace(/(M+|d+|h+|m+|s+)/g,function(v) {return ((v.length>1?"0":"")+eval('z.'+v.slice(-1))).slice(-2)});
			 return y.replace(/(y+)/g,function(v) {return x.getFullYear().toString().slice(-v.length)});
		}
		
		
		function showInfo(){
	  	 	var info = document.getElementById("errorInfo");
			info.innerHTML = "<font style='color:#999999; font-size:12px;'>请输入完整的冠字号！</font>";
		}

		function searchResult(){
			var dealNo = document.getElementById("dealNo").value;
	  	 	var errInfo = document.getElementById("errorInfo");
			if(dealNo == ""){
				errInfo.innerHTML = "<font style='color:red; font-size:12px;'>冠字号不能为空，请输入冠字号！</font>";
				return false;
			} else {
				errInfo.innerHTML = "";
				document.getElementById("search").disabled = true;
				document.getElementById("running").style.display = 'block';
		 		
		 	/* 	$.ajax({                                             
		  	 	  type: "POST",                                 
		  	 	  url: "${pageContext.request.contextPath}/GZHQueryServlet?method=queryResult",
		  	 	  data: "dealNo="+dealNo,
		  	 	  success: function(data){
	  	 			
					}
	  	 	 	});  */
			  	 	 $('#dataGrid').datagrid({
						url : '${pageContext.request.contextPath}/IndexServlet',
						fitColumns : false,
						width:1100,
						align:'center',
						border : true,
						pagination : true,
						idField : 'id',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50 ],
						checkOnSelect : false,
						selectOnCheck : false,
						striped : true,
						nowrap : true,
						frozenColumns : [ [ {
							field : 'mon',
							title : '冠字号',
						}, {
							field : 'monval',
							title : '币值',
							sortable : true
						} ] ],
						columns : [ [ {
							field : 'monver',
							title : '版别',
						}, {
							field : 'operdate',
							title : '日期',
							sortable : true
						}, {
							field : 'percode',
							title : '设备编号',
							sortable : true
						}, {
							field : 'pertype',
							title : '设备类型',
							width : 0,
						}, {
							field : 'bankno',
							title : '银行编号',
						}, {
							field : 'bankname',
							title : '银行名称',
							width : 0,
						}, {
							field : 'agencyno',
							title : '网点编号',
						}, {
							field : 'branchname',
							title : '网点名称',
						}, {
							field : 'bundleid',
							title : '捆标签',
						} ] ],
					}); 
				document.getElementById("search").disabled = false;
				document.getElementById("running").style.display = 'none';
			}
		}
		
		
 		$(document).keyup(function(event){
		  if(event.keyCode ==13){
			searchResult();
		  }
		});  
		</script>
</head>
<body>
	<div style="background-color:#F3F9FD;margin:0 auto;">
			<%-- <div id="resultDiv" style="background-color: #FFFFFF">
				<table width="100%">
					<tr>
						<td height="60" width="273px">
							<img src="${pageContext.request.contextPath}/style/blue/images/banklogo.jpg" >									
						</td>
						<td align="center">
							<font style="font-size: 35px;fofont-family: 'Arial', 'Tahoma', '宋体', '雅黑'">冠字号结果查询</font>
						</td>
					    <td align="right" width="273px">
					    	<span style="font-size:13px;">
					    		<font style="text-decoration:none;" color="#999999">尊敬的  <font>${sessionScope.user.username}</font>, 您好</font>
					    		<font style="color: #999999;">|</font>
					    		<a href="${pageContext.request.contextPath}/GZHQueryServlet?method=exit" style="text-decoration:none;">退出登录</a>
					    	</span>
						</td>
					</tr>
				</table>
			</div> --%>
			<div style="border: false; background: url('${pageContext.request.contextPath}/style/blue/images/bg.png') repeat-x; height: 640px;">
				<form action="" id="form" name="form" method="post" style="padding-top: 100px;">
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
								<input type="button" id="search" class="btn26" value="冠字号查询" onClick="return searchResult();" />
							</td>
							<td>
								<input type="text" style="display: none">
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
				<!-- <div id="searchResult" style="overflow: auto;width: auto;height: auto;display: none; margin-left: 5px;margin-right: 5px" align="center" > 
				</div>-->
				<div style="margin-top: 50px" align="center">
					<table id="dataGrid"></table>
				</div>
			</div>
		</div>
</body>
</html>
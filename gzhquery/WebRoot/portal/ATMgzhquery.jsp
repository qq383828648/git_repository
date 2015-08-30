<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugin/jeazyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugin/jeazyui/themes/icon.css">
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script src="${pageContext.request.contextPath}/js/resultlist.js"></script>
<script src="${pageContext.request.contextPath}/plugin/jeazyui/jquery.easyui.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/jeazyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" charset="utf-8">
	var dataGrid;
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
	});
</script>
</head>
<body>
		<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 160px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" >
					<tr>
						<th>ATM机编号</th>
						<td><input name="name" placeholder="可以模糊查询ATM机编号" class="span2" /></td>
					</tr>
					<tr>
						<th>加钞时间</th>
						<td><input class="span2" name="createdatetimeStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至<input class="span2" name="createdatetimeEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /></td>
					</tr>
					<tr>
						<th>最后加钞时间</th>
						<td><input class="span2" name="modifydatetimeStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至<input class="span2" name="modifydatetimeEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<!-- <div class="easyui-layout" data-options="fit : true,border : false" >
		<div data-options="region:'north',border:false">
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div> -->
</body>
</html>
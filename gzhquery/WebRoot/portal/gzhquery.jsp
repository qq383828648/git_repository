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
	<div class="easyui-layout" data-options="fit : true,border : false" >
		<div data-options="region:'north',border:false">
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
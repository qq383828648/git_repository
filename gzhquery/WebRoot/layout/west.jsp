<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugin/jeazyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugin/jeazyui/themes/icon.css">
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script src="${pageContext.request.contextPath}/js/resultlist.js"></script>
<script src="${pageContext.request.contextPath}/plugin/jeazyui/jquery.easyui.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/jeazyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function addTab(url,title,iconCls) {
		var iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>';
		var t = $('#index_tabs');
		var opts = {
			title : title,
			closable : true,
			iconCls : iconCls,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
			parent.$.messager.progress('close');
		} else {
			t.tabs('add', opts);
		}
	}
</script>
<div class="easyui-accordion" data-options="fit:true,border:false">
	<div title="系统菜单" style="padding: 5px;">
		<li style="margin: 10px">
			<a href="javascript:addTab('../portal/gzhquery.jsp','人民银行冠字号查询'，'');" src="demo/crud.jsp" class="cs-navi-tab">冠字号信息查询</a>
			</p>
		</li>
		<li style="margin: 10px">
			<a href="javascript:void(0);" src="../portal/gzhquery.jsp" class="cs-navi-tab">ATM冠字号信息查询</a>
			</p>
		</li>
	</div>
</div>
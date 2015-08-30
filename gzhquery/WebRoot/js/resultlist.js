function resultList(dealNo) {
	alert("eazyuisdsdfsf");
	$('#datagrid').datagrid({
		iconCls : 'icon-search',// 表格图标
		nowrap : true,// 是否只显示一行，即文本过多是否省略部分。
		striped : true,
		url : '${pageContext.request.contextPath}/GZHQueryServlet', // action地址
		//sortName : 'frequencyId',
		//sortOrder : 'asc',
		//idField : 'frequencyId',
		loadMsg : '查询中,请稍等！',
		fitColumns : false,
		fit : true,
		striped : true,// 是否纹理化表
		nowrap : false,// 是否把文本显示在一行里
		frozenColumns : [ [] ],
		columns : [ [ {
			checkbox : true
		}, {
			field : 'percode',
			title : '清钞机具编号',
			width : 0,
			align : 'center',
			sortable : true
		}, {
			field : 'coltime',
			title : '记录时间',
			width : 0,
			align : 'center',
			sortable : true
		},{
			field : 'mon',
			title : '冠字号',
			width : 0,
			align : 'center',
			sortable : true
		},{
			field : 'montype',
			title : '币种',
			width : 0,
			align : 'center',
			sortable : true
		},{
			field : 'monval',
			title : '币值',
			width : 0,
			align : 'center',
			sortable : true
		},{
			field : 'monver',
			title : '版别',
			width : 0,
			align : 'center',
			sortable : true
		},{
			field : 'trueflag',
			title : '记录时间',
			width : 0,
			align : 'center',
			sortable : true,
			formatter : function(val) {
				if (val == "1") {
					return "真";
				} else {
					return "假";
				}
			}
		},{
			field : 'quality',
			title : '成色',
			width : 0,
			align : 'center',
			sortable : true
		}, {
			field : 'agentchid',
			title : '网点ID',
			width : 0,
			align : 'left',
			sortable : true
		}, {
			field : 'operatorid',
			title : '操作员ID',
			width : 0,
			align : 'left',
			sortable : true
		}, {
			field : 'operdate',
			title : '日期',
			width : 0,
			align : 'left',
			sortable : true
		} ] ],
		pagination : true, // 包含分页
		rownumbers : true,
		singleSelect : true,
		onLoadSuccess : function(data) {
			if (data.result == "error") {
				$.messager.alert('提示信息', data.errorMsg, 'error');
			}
			;
		}
	});

	$(".submit_btn").click(function() {
		var url;
		$('#form').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(msg) {
				$('#dlg').dialog('close');
				$('#datagrid').datagrid('reload');
				/*$.messager.show({
					title : '提示信息',
					msg : msg,
					timeout : 2000
				});*/
			}
		});
	});

	$('#datagrid').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 50, 100 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}


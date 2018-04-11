function sectionAdmin(){
	$('#admin-table')
	.bootstrapTable(
			{
				url : '/bbs/admin/section',
				method : 'post',
				contentType : "application/x-www-form-urlencoded",
				//striped : true,
				pagination : true,
				pageSize : 2,
				pageNumber : 1,
				search : true,
				//dataField: 'data',
				queryParams : queryParamsSection,//请求服务器时所传的参数
				sidePagination : 'server',//指定服务器端分页
				//pageList : [ 10, 20, 50, 100, 200, 500 ],
				toolbar : '#toolbar',
				columns : [
						{
							field : 'id',
							title : 'id'
						},
						{
							field : 'section',
							title : '版块名'
						},
						{
							field : 'postAmount',
							title : '帖子数量'
						},
						{
							filed : "tool",
							title : '操作',
							formatter : function(value, row, index) {
								if (row.status === 1) {
									var element = "<input class='btn btn-sm btn-danger' onclick='hideSection("
											+ row.id
											+ ","
											+ row.status
											+ ")' type='button' value='恢复'>"
								} else {
									var element = "<input class='btn btn-sm btn-danger' onclick='hideSection("
											+ row.id
											+ ","
											+ row.status
											+ ")' type='button' value='隐藏'>"
								}

								return element;
							}
						} ],
				silent : true, //刷新事件必须设置  
				sidePagination : 'server',//指定服务器端分页
			//responseHandler:responseHandler,//请求数据成功后，渲染表格前的方法
			});
}
	//请求服务数据时所传参数
	function queryParamsSection(params) {
		return {
			pageSize : params.limit, //每一页的数据行数，默认是上面设置的10(pageSize)
			pageIndex : params.offset / params.limit + 1, //当前页面,默认是上面设置的1(pageNumber)
			param : params.search //这里是其他的参数，根据自己的需求定义，可以是多个
		}
	}

	
	function hideSection(id, value) {
		var handle = "show";
		if (value == 0) {
			handle = 'hide'
		}
		var url = '/bbs/' + handle + '/sections/' + id
		$.post(url, function(data) {
			$("#admin-table").bootstrapTable('refresh')
		})
	}

	$('#add-row-btn').click(function() {
		if ($('#title-h3').html() == '版块管理') {

		}
	})
	
	$('#section-input').bind(
				'input propertychange',
				function() {
					if ($(this).val() != '') {
						$.post('/bbs/verify/section/' + $(this).val(),
								function(data) {
									if (data == 0) {
										$('#hint-span').html('可以添加')
										$('#section-btn').prop('disabled',
												false)
									} else {
										$('#hint-span').html('该版块已存在')
										$('#section-btn')
												.prop('disabled', true)
									}
								})
					} else {
						$('#section-btn').prop('disabled', true)
					}

					$('#section-btn').unbind('click')
					$('#section-btn').click(
							function() {
								$.post('/bbs/post/section', 'section='
										+ $('#section-input').val(), function(
										data) {
									console.log(data)
									$("#admin-table").bootstrapTable('refresh')
								})

							})

				})

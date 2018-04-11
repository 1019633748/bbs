$('#admin-table')
		.bootstrapTable(
				{
					url : '/bbs/admin/post',
					method : 'post',
					contentType : "application/x-www-form-urlencoded",
					//striped : true,
					pagination : true,
					pageSize : 2,
					pageNumber : 1,
					search : true,
					queryParams : queryParamsPost,//请求服务器时所传的参数
					sidePagination : 'server',//指定服务器端分页
					//pageList : [ 10, 20, 50, 100, 200, 500 ],
					toolbar : '#toolbar',
					columns : [
							{
								field : 'id',
								title : 'id'
							},
							{
								field : 'post',
								title : '帖子'
							},{
								field : 'author',
								title : '作者'
							},
							
							{
								field : 'createDate',
								title : '创建日期'
							},{
								field : 'lastReply',
								title : '最后回复'
							},{
								field : 'replyAmount',
								title : '回复量'
							},
							{
								filed : "tool",
								title : '操作',
								formatter : function(value, row, index) {
									if (row.status === 1) {
										var element = "<input class='btn btn-sm btn-danger' onclick='hidePost("
												+ row.id
												+ ","
												+ row.status
												+ ")' type='button' value='恢复'>"
									} else {
										var element = "<input class='btn btn-sm btn-danger' onclick='hidePost("
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
		
		//请求服务数据时所传参数
		function queryParamsPost(params) {
			return {
				pageSize : params.limit, //每一页的数据行数，默认是上面设置的10(pageSize)
				pageIndex : params.offset / params.limit + 1, //当前页面,默认是上面设置的1(pageNumber)
				param : params.search //这里是其他的参数，根据自己的需求定义，可以是多个
			}
		}
		
		hidePost=function (id, value) {
			var handle = "show";
			if (value == 0) {
				handle = 'hide'
			}
			var url = '/bbs/' + handle + '/posts/' + id
			$.post(url, function(data) {
				$("#admin-table").bootstrapTable('refresh')
			})
		}
		
		
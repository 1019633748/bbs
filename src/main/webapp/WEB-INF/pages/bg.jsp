<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>网络论坛后台管理</title>
<link href="/bbs/css/bootstrap.min.css" rel="stylesheet">
<link href="/bbs/css/bootstrap-table.css" rel="stylesheet">
<link href="/bbs/css/common.css" rel="stylesheet">
<script src="/bbs/js/jquery-3.2.1.min.js"></script>
<script src="/bbs/js/bootstrap.min.js"></script>
<script src="/bbs/js/bootstrap-table.js"></script>
<script src="/bbs/js/bootstrap-table-zh-CN.min.js"></script>
<script src="/bbs/js/section-admin.js"></script>
<script src="/bbs/js/Chart.bundle.js"></script>
<script src="/bbs/js/utils.js"></script>
<style type="text/css">
body, html {
	height: 90%;
	width: 100%;
}

#main-div {
	display: flex;
	width: 100%;
	height: 100%;
}

#left-div, #right-div {
	width: 85%
}

#left-div {
	height: 100%;
	width: 200px;
	background:#F8F8F8
}

.left-first {
	height: 30px;
	background: #F8F8F8;
	line-height: 30px;
	cursor: pointer;
	border-top: 1px solid #E7E7E7;
	user-select:none;
}

.left-first:hover {
	background: #EEE
}

.left-second {
	cursor: pointer;
	display: none;
}

li{
list-style:none;
}

li:hover {
	background: #EEE;
	user-select:none;
}
.add-div{
display:none
}

a:hover{
text-decoration:none;
color:#333
}
a{
color:#333
}
</style>
</head>

<body>
	<nav class="navbar navbar-default" role="navigation" style="margin-bottom:0">
	<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">网络论坛</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="/bbs/get/bg">后台管理</a></li>
		</ul>
	</div>
	<div class="pull-right" id="user-info">
			<img class="img-circle logined"
				src="/images/avatar/${bbs.url}"> <span id="username"
				class="logined"><a href="/bbs/get/users/${bbs.id }">${bbs.nickname }</a></span> <a class="logout"
				href="/bbs/get/login">登录</a><a class="logined" href="/bbs/logout">退出</a>
		</div>
	</div>
	</nav>
	<div id="main-div">
		<div id="left-div">
			<div>
				<div class="left-first">&emsp;<a href="/bbs/get/bg">📟首页</a></div>
				<div class="left-first">&emsp;📊图表</div>
				<div class="left-second">
					<ul>
						<li><a href="/bbs/get/chart/user">用户</a></li>
						<li><a href="/bbs/get/chart/content">内容</a></li>
					</ul>
				</div>
				<div id="admin-div" class="left-first">&emsp;📇管理</div>
				<div class="left-second">
					<ul>
						<li><a href="/bbs/admin/section">版块管理</a></li>
						<li><a href="/bbs/admin/post">帖子管理</a></li>
						<li><a href="/bbs/admin/reply">回复管理</a></li>
						<li><a href="/bbs/admin/user">用户管理</a></li>
						<li><a href="/bbs/admin/sensitive">特殊管理</a></li>
						<li><a href="/bbs/admin/advice">通告管理</a></li>
					</ul>
				</div>
				<div class="left-first">&emsp;📌审核</div>
				<div class="left-second">
					<ul>
						<li><a href="/bbs/audit/avatar">头像审核</a></li>
						<li><a href="/bbs/audit/content">内容审核</a></li>
					</ul>
				</div>

			</div>
		</div>
		<div id="right-div">
			<h3 id="title-h3" align="center">首页</h3>
			
			<!-- /.row -->
            <div class="row" style="margin-top:0px;margin-left:20px">
                <div class="col-lg-2 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-alert"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${auditAmount }</div>
                                    <div>未处理举报</div>
                                </div>
                            </div>
                        </div>
                        <a href="/bbs/audit/content">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                
                 <div class="col-lg-2 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-tasks"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${sectionAmount }</div>
                                    <div>版块</div>
                                </div>
                            </div>
                        </div>
                        <a href="/bbs/admin/section">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
               
               <div class="col-lg-2 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-tags"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${postAmount }</div>
                                    <div>帖子</div>
                                </div>
                            </div>
                        </div>
                        <a href="/bbs/admin/post">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                
                <div class="col-lg-2 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-comment"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${ replyAmount}</div>
                                    <div>回复</div>
                                </div>
                            </div>
                        </div>
                        <a href="/bbs/admin/reply">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                
                <div class="col-lg-2 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-user"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${ userAmount}</div>
                                    <div>用户</div>
                                </div>
                            </div>
                        </div>
                        <a href="/bbs/admin/user">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
               
            </div>
            <!-- /.row -->
			
			
			<div style="width:600px;margin:0 auto">
		<canvas id="chart-post"></canvas>
	</div>
	
	<div style="width:600px;margin:0 auto">
		<canvas id="chart-line"></canvas>
	</div>
			
			<table id="admin-table"></table>
			<div class="" id=""></div>
			<div id="add-section-div" class="add-div">
				版块：<input id="section-input" class="">
				<button id="section-btn" class="btn btn-sm btn-secondary"
					disabled="true" type="submit">提交</button>
				<span id="hint-span"></span>
			</div>
		</div>
	</div>
	
	
	 <script>
		var chartDataPost = {
			labels: [],
			datasets: [{
				type: 'line',
				label: '帖子',
				borderColor: window.chartColors.blue,
				borderWidth: 2,
				fill: false,
				data: [
				]
			}]
		};
	
			var ctxPost = document.getElementById('chart-post').getContext('2d');
			
			$.post('/bbs/get/post/create',function(data){
				for(var i=0;i<data.length;i++){
					chartDataPost.labels.push(data[i].cd)
					chartDataPost.datasets[0].data.push(data[i].amount)
				}
				
				
				window.myLine = new Chart(ctxPost, {
					type: 'line',
					data: chartDataPost,
		            pointDot : true,        //是否显示点
					options: {
						responsive: true,
						title: {
							display: true,
							text: ''
						},
						tooltips: {
							mode: 'index',
							intersect: true
						},
						scales: {
			                xAxes: [{
			                    display: true,
			                    scaleLabel: {
			                        display: true,
			                        labelString: '日期',
			                    }
			                }],
			                yAxes: [{
			                    display: true,
			                    scaleLabel: {
			                        display: true,
			                        labelString: '帖子（条）'
			                    },
			                    ticks: {
			                        min: 0,
			                        //max: 8,
			                        stepSize: 5
			                    }
			                }]
			            },
						 elements: {
					            line: {
					                tension: 0, // disables bezier curves
					            }
					        }

					}
				});
			})
			
		
		

	
	</script>
	
	
	<script>
		var chartData = {
			labels: [],
			datasets: [{
				type: 'line',
				label: '注册人数',
				borderColor: window.chartColors.blue,
				borderWidth: 2,
				fill: false,
				data: [
				]
			}]
		};
		window.onload = function() {
			var ctx = document.getElementById('chart-line').getContext('2d');
			
			$.post('/bbs/get/user/create',function(data){
				for(var i=0;i<data.length;i++){
					chartData.labels.push(data[i].cd)
					chartData.datasets[0].data.push(data[i].amount)
				}
				
				
				window.myLine = new Chart(ctx, {
					type: 'line',
					data: chartData,
		            pointDot : true,        //是否显示点
					options: {
						responsive: true,
						title: {
							display: true,
							text: ''
						},
						tooltips: {
							mode: 'index',
							intersect: true
						},
						scales: {
			                xAxes: [{
			                    display: true,
			                    scaleLabel: {
			                        display: true,
			                        labelString: '日期',
			                    }
			                }],
			                yAxes: [{
			                    display: true,
			                    scaleLabel: {
			                        display: true,
			                        labelString: '人数（人）'
			                    },
			                    ticks: {
			                        //min: 0,
			                        //max: 8,
			                        stepSize: 1
			                    }
			                }]
			            },
						 elements: {
					            line: {
					                tension: 0, // disables bezier curves
					            }
					        }

					}
				});
			})
			
		
		};

	
	</script>
	
	<script src="/bbs/js/common.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		
		$('.left-first').click(function() {
			$(this).css('background', '#EEE')
			$('.left-first').not(this).css('background', '#F8F8F8')
			if ($(this).index() != 0) {
				if ($(this).next().css('display') == 'none') {
					$(this).next().show()
				} else {
					$(this).next().hide()
				}
			} 
		})

		

		
	})
	</script>
</body>

</html>

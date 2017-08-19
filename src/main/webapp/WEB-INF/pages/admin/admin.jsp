<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/layui.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/global.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin-layout.css">
</head>
<body>
	<script src="<%=request.getContextPath()%>/layui/layui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/copy1/index.js"></script>
	<script>
		layui.use('element', function() {
			var $ = layui.jquery, element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块
			
			//触发事件
			var active = {
				tabAdd : function() {
					//新增一个Tab项
					element.tabAdd('demo', {
						title : '新选项' + (Math.random() * 1000 | 0) //用于演示
						,
						content : '内容' + (Math.random() * 1000 | 0),
						id : new Date().getTime()
					//实际使用一般是规定好的id，这里以时间戳模拟下
					})
				},
				tabDelete : function(othis) {
					//删除指定Tab项
					element.tabDelete('demo', '44'); //删除：“商品管理”
					othis.addClass('layui-btn-disabled');
				},
				tabChange : function() {
					//切换到指定Tab项
					element.tabChange('demo', '22'); //切换到：用户管理
				}
			};
			$('.site-demo-active').on('click', function() {
				var othis = $(this), type = othis.data('type');
				active[type] ? active[type].call(this, othis) : '';
			});
		});
	</script>

	<script>
		layui
				.use(
						[ 'form', 'laypage', 'layer', 'common','bodyTab' ],
						function() {
							var $ = layui.jquery, form = layui.form(), laypage = layui.laypage, layer = layui.layer, common = layui.common, element = layui
									.element(), tab = layui.bodyTab();
							$("body")
									.on(
											"click",
											".layui-left-nav .layui-nav-item a",
											function() {
												tab.tabAdd($(this));
												$(this)
														.parent("li")
														.siblings()
														.removeClass(
																"layui-nav-itemed");
											});
						});
	</script>
	
	<script type="text/javascript">
	
	
	</script>

	<div class="admin-header">
		<%@ include file="../global/top.jsp"%>
	</div>
	<div class="admin-menu">
		<ul class="layui-nav layui-nav-tree" lay-filter="test">
			<!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
			<li class="layui-nav-item layui-nav-itemed"><a
				href="javascript:;">组织管理</a>
				<dl class="layui-nav-child">
					<dd>
						<a href="javascript:;" data-url="/user/user_list.do">团队管理</a>
					</dd>
					<dd>
						<a href="javascript:;">用户管理</a>
					</dd>
				</dl></li>

		</ul>
	</div>
	<div class="admin-content">

		<div class="layui-tab" lay-filter="demo" lay-allowclose="true">
			<ul class="layui-tab-title">
				<li class="layui-this" lay-id="11">网站设置</li>
				<li lay-id="22">用户管理</li>
				<li lay-id="33">权限分配</li>
				<li lay-id="44">商品管理</li>
				<li lay-id="55">订单管理</li>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">内容1</div>
				<div class="layui-tab-item">内容2</div>
				<div class="layui-tab-item">内容3</div>
				<div class="layui-tab-item">内容4</div>
				<div class="layui-tab-item">内容5</div>
			</div>
		</div>
		<div class="site-demo-button" style="margin-bottom: 0;">
			<button class="layui-btn site-demo-active" data-type="tabAdd">新增Tab项</button>
			<button class="layui-btn site-demo-active" data-type="tabDelete">删除：商品管理</button>
			<button class="layui-btn site-demo-active" data-type="tabChange">切换到：用户管理</button>
		</div>
	</div>
</body>
</html>
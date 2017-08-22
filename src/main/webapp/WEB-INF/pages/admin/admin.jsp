<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../global/layui.jsp"%>
</head>
<body>

	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<%@ include file="../global/top.jsp"%></div>
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<ul class="layui-nav layui-nav-tree">
					<li class="layui-nav-item layui-nav-itemed">
						<a href="javascript:;">组织管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="../admin/team/add">添加团队</a>
							</dd>
							<dd>
								<a href="javascript:;">团队查询</a>
							</dd>
							<dd>
								<a href="javascript:;">添加用户</a>
							</dd>
							<dd>
								<a href="javascript:;">用户查询</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item  layui-nav-itemed">
						<a href="javascript:;">配置管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">服务管理</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item  layui-nav-itemed">
						<a href="javascript:;">排班管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">排班规则</a>
							</dd>
							<dd>
								<a href="javascript:;">团队排班</a>
							</dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<div class="layui-body">
		<%
		String path = request.getRequestURI(); 
		System.out.println("jhd:"+path);
		if(path.contains("team/add")){
			%>
			<%@ include file="admin-team-add.jsp"%>
		<%
		}
		%>
		</div>
		</div>
		<div class="layui-footer"></div>
	</div>


	<script src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
	<script>
		//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
		;
		!function() {
			var element = layui.element;
			var form = layui.form;

		}();
	</script>
</body>
</html>
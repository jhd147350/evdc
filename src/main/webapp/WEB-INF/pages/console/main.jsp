<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="evdc.vianet.auth.entity.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EvDc</title>
<%@ include file="../global/layui_css.jsp"%>
</head>
<body>

	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<%@ include file="../global/top.jsp"%>
		</div>

		<%@ include file="console.jsp"%>
		欢迎您<%=((User) session.getAttribute("user")).getEmail()%>
		登录EvDC工单系统 <br> <a href="<%=request.getContextPath()%>/ticket/create" class="layui-btn">创建工单</a>
	</div>
	<!-- 你的HTML代码 -->
	<script src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
	<script>
		//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
		;
		!function() {
			var element = layui.element;
			var form = layui.form;
			var table = layui.table;

		}();
	</script>
</body>
</html>
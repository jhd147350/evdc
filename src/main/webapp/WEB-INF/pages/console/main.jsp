<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="evdc.vianet.auth.entity.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EvDc</title>
</head>
<body>

	欢迎您<%=((User) session.getAttribute("user")).getEmail()%>
	登录EvDC工单系统
	<br>
	<a href="/" class="layui-btn">创建工单</a>
	<!-- 你的HTML代码 -->
	<script src="<%=request.getContextPath()%>/layui/layui.js"></script>
	<script>
		//一般直接写在一个js文件中
		layui.use([ 'layer', 'form' ], function() {
			var layer = layui.layer, form = layui.form();
			layer.msg('Hello <%=((User) session.getAttribute("user")).getEmail()%>');
		});
	</script>

</body>
</html>
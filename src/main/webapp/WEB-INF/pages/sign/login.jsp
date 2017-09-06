<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>evdc-login</title>
<%@ include file="../global/layui_css.jsp"%>
<style type="text/css">
#form {
	width: 400px;
	/*	height: 300px;*/
	position: relative;
	left: 50%;
	top: 50%;
	margin-left: -200px;
	margin-top: -150px;
	border-style: solid;
	border-width: 1px;
}

html, body {
	height: 100%;
	border-style: solid;
	border-width: 1px;
}
</style>
</head>
<body text-align:center>
	
	<div id="form">
		<form action="dologin" class="layui-form" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">账户</label>
				<div class="layui-input-inline">
					<input name="loginId" required lay-verify="required"
						placeholder="请输入账户" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">辅助文字</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-inline">
					<input type="password" name="password" required
						lay-verify="required" placeholder="请输入密码" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">辅助文字</div>
			</div>
			<input type="submit" class="layui-btn" value="提交">

		</form>
	</div>
</body>
</html>
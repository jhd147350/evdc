<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建排班计划</title>

<style type="text/css">
.mytablehead th {
	text-align: center;
}

table>tbody>tr>th {
	background-color: #f2f2f2;
	/*居中样式不生效*/
}
</style>
<%@ include file="../../global/layui_css.jsp"%>

</head>
<body>

<form class="layui-form" action="">
	<div class="layui-form-item">
		<label class="layui-form-label">值班团队</label>
		<div class="layui-input-block">
			<input type="text" name="title" required lay-verify="required"
				placeholder="请输入班次名称" autocomplete="off" class="layui-input"
				value="${teamName}">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">班次名称</label>
		<div class="layui-input-block">
			<select id="shift" name="shift" lay-verify="required">
				<option value=""></option>
				<c:forEach items="${shifts}" var="temp">
					<option value="${temp.id}">${temp.name}</option>
				</c:forEach>
			</select>
		</div>

	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">起始日期</label>
		<div class="layui-input-block">
			<input id="begindate" type="text" name="title2" required
				lay-verify="required" autocomplete="off" placeholder="请选择起始日期"
				class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">循环周期</label>
		<div class="layui-input-block">
			<input id="input_circle" type="text" name="title2" required
				lay-verify="required" autocomplete="off" placeholder="请输入循环周期（整数）"
				class="layui-input">
		</div>
	</div>
</form>

<button id="generateTable" class="layui-btn">生成一个周期的排班表</button>
<table id="table" class="mytablehead layui-table">
</table>
<button id="addRow" class="layui-btn">添加一行次值班人</button>
<button id="exe" class="layui-btn">执行上述计划执行排班</button>
<%@ include file="../js/generateSchedule.jsp" %>

</body>
</html>
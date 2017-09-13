<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>查看排班规则</title>

<%@ include file="../../global/layui_css.jsp"%>
<style type="text/css">
.mycenter {
	text-align: left;
	padding: 9px 15px;
	min-height: 1px;
}
.item {
    margin-bottom: 1px;
}
</style>
</head>
<body>
	<form class="layui-form" action="">
		<div class="layui-form-item item">
			<label class="layui-form-label">班次名称</label>
			<div class="layui-input-block mycenter">${shift.name}</div>
		</div>
		<div class="layui-form-item item">
			<label class="layui-form-label">创建人员</label>
			<div class="layui-input-block mycenter">${shift.createUserId}</div>
		</div>
		<div class="layui-form-item item">
			<label class="layui-form-label">最近更新</label>
			<div class="layui-input-block mycenter">${shift.updateDate}</div>
		</div>
	</form>
	<table id="table" class="layui-table">
		<colgroup>
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="100px">
			<col width="200px">

		</colgroup>
		<thead>
			<tr>
				<th>班次名称</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>班次</th>
				<th>备注信息</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${rules}" var="temp">
				<tr>
					<td>${shift.name}</td>
					<td>${temp.startTime}</td>
					<td>${temp.endTime}</td>
					<td>${temp.order}</td>
					<td>${temp.info}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!--//TODO 重复使用会导致日期选择器出现闪现问题 <script src="${ctx}/static/layui/layui.all.js"></script>  -->
	<!--  <script src="${ctx}/static/js/shift/addrule.js"></script> -->
</body>
</html>
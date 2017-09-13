<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>创建排班规则</title>

<%@ include file="../../global/layui_css.jsp"%>
</head>
<body>
	<form class="layui-form" action="">
		<div class="layui-form-item">
			<label class="layui-form-label">班次名称</label>
			<div class="layui-input-block">
				<input id="shiftname" type="text" name="name" required
					lay-verify="required" placeholder="请输入班次名称" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">开始时间</label>
			<div class="layui-input-block">
				<input id="starttime" type="text" name="title1" required
					lay-verify="required" autocomplete="off" placeholder="请选择开始时间"
					class="layui-input">
			</div>

		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">结束时间</label>
			<div class="layui-input-block">
				<input id="endtime" type="text" name="title2" required
					lay-verify="required" autocomplete="off" placeholder="请选择结束时间"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<a id="add" class="layui-btn">添加</a> <a id="delete" class="layui-btn">清空</a>
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

		</tbody>
	</table>
	<!--//TODO 重复使用会导致日期选择器出现闪现问题 <script src="${ctx}/static/layui/layui.all.js"></script>  -->
	<!--  <script src="${ctx}/static/js/shift/addrule.js"></script> --> 


 
	<%@ include file="../js/addrulescript.jsp"%>



</body>
</html>
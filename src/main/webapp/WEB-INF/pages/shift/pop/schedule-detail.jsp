<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>排班表</title>
<%@ include file="../../global/layui_css.jsp"%>
</head>
<body>

	<button class="layui-btn">上一周</button>
	<button class="layui-btn">下一周</button>
	<table class="layui-table">
		<thead>
			<tr>
				<c:forEach items="${scheduleResults}" var="result">
					<th colspan="${result.orderOfDay}">${result.date.week}(${result.date.month}月${result.date.day}日)</th>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${scheduleResults}" var="result">
					<c:forEach items="${result.times}" var="time">
						<th>${time.startTime}-${time.endTime}</th>
					</c:forEach>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach items="${scheduleResults}" var="result">
					<c:forEach items="${result.staffsP}" var="staff">
						<th id="${staff.id}">${staff.name}</th>
					</c:forEach>
				</c:forEach>

			</tr>
			<tr>
				<c:forEach items="${scheduleResults}" var="result">
					<c:forEach items="${result.staffsS}" var="staff">
						<th id="${staff.id}">${staff.name}</th>
					</c:forEach>
				</c:forEach>
			</tr>
		</tbody>
	</table>
	<script src="${ctx}/static/layui/layui.all.js"></script>
	<script>
		//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
		;
		!function() {
			var form = layui.form;
			var $ = layui.jquery;

			var table = layui.table;

			$('#generateTable').click(function() {
				//执行渲染
				table.render({
					elem : '#table' //指定原始表格元素选择器（推荐id选择器）
					,
					height : 315 //容器高度
					,
					cols : [ {} ]
				//设置表头
				//,…… //更多参数参考右侧目录：基本参数选项
				});
			});
			/*  $("td").ondblclick(function () {
			      alert("jhd");
			  });*/

			$(document).ready(function() {
				$("button").click(function() {
					alert("btn");
				});
				$("td").click(function() {
					console.log("click td");
				});
				$("td").dblclick(function() {
					console.log("double click td");
				});
			});

		}();
	</script>
</body>
</html>
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

	<button id="pre" class="layui-btn">上一周</button>
	<button id="next" class="layui-btn">下一周</button>
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
						<th title="${time.startTime}-${time.endTime}">${time.info}</th>
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
	
		var nextWeek=0;
		//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
		;
		!function() {
			var form = layui.form;
			var $ = layui.jquery;


			$(document).ready(function() {
				$("#pre").click(function() {
					console.log("pre");
					nextWeek--;
					nextWeekQuery();
				});
				$("#next").click(function() {
					console.log("next");
					nextWeek++;
					nextWeekQuery();
				});
				$("td").click(function() {
					console.log("click td");
				});
				$("td").dblclick(function() {
					console.log("double click td");
				});
			});

		}();
		
		function nextWeekQuery() {
			var $ = layui.jquery;
			var teamId=18;
			var index = layer.load(1, {shade: [0.3]});//显示加载图标 shade遮罩透明度为0.3
			$.ajax({
				url : 'detail/week?teamId=' + teamId + '&nextWeek=' + nextWeek,
				type : 'GET',
				success : function(result) {
					layer.close(index);//关闭加载图标
					console.log(result);
					$("table").empty();
					$("table").html(result);
				}
			});			
		}
	</script>
</body>
</html>
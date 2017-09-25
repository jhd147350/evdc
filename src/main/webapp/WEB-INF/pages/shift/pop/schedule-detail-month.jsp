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
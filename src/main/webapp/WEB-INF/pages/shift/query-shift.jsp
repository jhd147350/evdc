<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<button class="layui-btn">上一周</button>
<button class="layui-btn">下一周</button>
<table class="layui-table">
	<thead>
		<tr>
			<th colspan="2">星期一（9月4）</th>
			<th colspan="2">星期二（9月5）</th>
			<th colspan="2">星期三（9月6）</th>
			<th colspan="2">星期四（9月7）</th>
			<th colspan="2">星期五（9月8）</th>
			<th colspan="2">星期六（9月9）</th>
			<th colspan="2">星期日（9月10）</th>
		</tr>
		<tr>
			<th>8:00-20:00</th>
			<th>20:00-8:00</th>
			<th>8:00-20:00</th>
			<th>20:00-8:00</th>
			<th>8:00-20:00</th>
			<th>20:00-8:00</th>
			<th>8:00-20:00</th>
			<th>20:00-8:00</th>
			<th>8:00-20:00</th>
			<th>20:00-8:00</th>
			<th>8:00-20:00</th>
			<th>20:00-8:00</th>
			<th>8:00-20:00</th>
			<th>20:00-8:00</th>

		</tr>
	</thead>
	<tbody>
		<tr>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
		</tr>
		<tr>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
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
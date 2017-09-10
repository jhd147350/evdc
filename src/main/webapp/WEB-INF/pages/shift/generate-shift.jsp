<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
.mytablehead th {
	text-align: center;
}

table>tbody>tr>th {
	background-color: #f2f2f2;
	/*居中样式不生效*/
}
</style>
<form class="layui-form" action="">
	<div class="layui-form-item">
		<label class="layui-form-label">值班团队</label>
		<div class="layui-input-block">
			<input type="text" name="title" required lay-verify="required"
				placeholder="请输入班次名称" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">班次名称</label>
		<div class="layui-input-block">
			<select name="city" lay-verify="required">
				<option value=""></option>
				<option value="0">北京</option>
				<option value="1">上海</option>
				<option value="2">广州</option>
				<option value="3">深圳</option>
				<option value="4">杭州</option>
			</select>
		</div>

	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">循环周期</label>
		<div class="layui-input-block">
			<input id="endtime" type="text" name="title2" required
				lay-verify="required" autocomplete="off" placeholder="请输入循环周期（整数）"
				class="layui-input">
		</div>
	</div>
</form>
<button id="generateTable" class="layui-btn">生成一个周期的排班表</button>
<table id="tabletest"></table>
<table class="layui-table"
	lay-data="{width:1000, height:350, url:'/test/table/demo2.json', page: true, limit: 6}">
	<thead>
		<tr>
			<th lay-data="{field:'u1', width:100}">周期顺序</th>
			<th lay-data="{field:'username', width:80}" colspan="2">1</th>
			<th lay-data="{field:'amount', width:120}" colspan="2">2</th>
			<th lay-data="{align:'center'}" colspan="2">3</th>
		</tr>
		<tr>
			<th lay-data="{field:'u2', width:100}">班次顺序</th>
			<th lay-data="{field:'province', width:80}">1</th>
			<th lay-data="{field:'city', width:80}">2</th>
			<th lay-data="{field:'province', width:80}">1</th>
			<th lay-data="{field:'city', width:80}">2</th>
			<th lay-data="{field:'province', width:80}">1</th>
			<th lay-data="{field:'city', width:80}">2</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th class="mytablehead">主值班人</th>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
		</tr>
	</tbody>
</table>
<table class="mytablehead layui-table">
	<thead>
		<tr>
			<th>周期顺序</th>
			<th colspan="2">1</th>
			<th colspan="2">2</th>
			<th colspan="2">3</th>
		</tr>
		<tr>
			<th>班次顺序</th>
			<th>1</th>
			<th>2</th>
			<th>1</th>
			<th>2</th>
			<th>1</th>
			<th>2</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th align="center" class="mytablehead">主值班人*</th>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
		</tr>
		<tr>
			<th>次值班人</th>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
			<td>jhd</td>
		</tr>
	</tbody>
</table>
<button id="addRow" class="layui-btn">添加一行次值班人</button>

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

	}();
</script>
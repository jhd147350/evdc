<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
  	<a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
 	<a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
<form class="layui-form" action="">
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" name="create" value="create">创建排班规则</button>
		</div>
	</div>
</form>
<div class="">
	<table class="layui-table"
		lay-data="{height:315, url:'/demo/table/user/', page:true, id:'test'}"
		lay-filter="test">
		<thead>
			<tr>
				<th lay-data="{field:'id', width:80, sort: true}">ID</th>
				<th lay-data="{field:'username', width:80}">名称</th>
				<th lay-data="{field:'sex', width:100, sort: true}">创建人</th>
				<th lay-data="{field:'sign', width:177, sort:true}">修改时间</th>
				<th
					lay-data="{fixed:'right', width:160, align:'center', toolbar:'#barDemo'}" />
			</tr>
		</thead>
	</table>
</div>

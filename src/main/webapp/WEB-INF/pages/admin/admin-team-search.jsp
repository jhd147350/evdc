<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
  	<a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
 	<a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
<form class="layui-form" action="">
	<div class="layui-form-item">

		<div class="layui-inline">
			<input type="text" name="title" required lay-verify="required"
				placeholder="请输入搜索内容" autocomplete="off" class="layui-input">
		</div>

		<div class="layui-inline">
			<button class="layui-btn" lay-submit lay-filter="formDemo">搜索</button>
		</div>

	</div>
</form>
<table class="layui-table" lay-data="{height:400, url:'<%=request.getContextPath()%>/admin/test', page:true, id:'test', limits:[10,15,20,40], limit:15}" lay-filter="test">
    <thead>
    <tr>
        <th lay-data="{field:'id', width:80, sort: true, checkbox:true}">ID</th>
        <th lay-data="{field:'username', width:80}">用户名</th>
        <th lay-data="{field:'sex', width:80, sort: true}">性别</th>
        <th lay-data="{field:'city', width:80}">城市</th>
        <th lay-data="{field:'sign', width:177}">签名</th>
        <th lay-data="{field:'experience', width:80, sort: true}">积分</th>
        <th lay-data="{field:'score', width:80, sort: true}">评分</th>
        <th lay-data="{field:'classify', width:80}">职业</th>
        <th lay-data="{field:'wealth', width:135, sort: true}">财富</th>
        <th lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
</table>

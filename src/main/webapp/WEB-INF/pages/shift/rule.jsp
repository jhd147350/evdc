<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>排班规则</title>
<%@ include file="../global/layui_css.jsp"%>
</head>
<body>

	<form class="layui-form" action="">
		<div class="layui-form-item">
			<div class="layui-input-block">
				<a class="layui-btn" id="create">创建排班规则</a>
			</div>
		</div>
	</form>
	<div class="">
		<!--  -->
		<table id="shifttable" class="layui-table"
			lay-data="{height:400, url:'shiftdata',response : {statusCode : 200}, id:'table'}"
			lay-filter="shifttable">

			<thead>
				<tr>
					<th lay-data="{field:'id', width:55, sort: true}">ID</th>
					<th lay-data="{field:'name', width:150}">名称</th>
					<th lay-data="{field:'createUser', width:150, sort: true}">创建人</th>
					<th lay-data="{field:'updateDate', width:177, sort:true}">修改时间</th>
					<th
						lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#toolbar'}"></th>
				</tr>
			</thead>

		</table>
	</div>


	<script type="text/html" id="toolbar">
    <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
  	<a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
 	<a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
	</script>

	<%@ include file="./js/createshift.jsp"%>


</body>
</html>
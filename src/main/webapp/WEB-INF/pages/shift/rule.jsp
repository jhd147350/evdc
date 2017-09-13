<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<!--  
<script src="${ctx}/static/layui/layui.all.js"></script>-->
<!-- <script src="${ctx}/static/js/shift/createshift.js"></script> -->
<%@ include file="./js/createshift.jsp"%>

<!--  
<script>

	;
	!function() {
		var table = layui.table;
		console.log('start');
		table.render({ //其它参数在此省略
			elem : '#shifttable',
			response : {
				// statusName: 'status' //数据状态的字段名称，默认：code
				statusCode : 200
			//成功的状态码，默认：0
			//,msgName: 'hint' //状态信息的字段名称，默认：msg
			//  ,countName: 'total' //数据总数的字段名称，默认：count
			//  ,dataName: 'rows' //数据列表的字段名称，默认：data
			},
			cols : [ [ //标题栏
			{
				checkbox : true
			}, {
				field : 'id',
				title : 'ID',
				width : 80
			}, {
				field : 'name',
				title : '用户名',
				width : 120
			}, {
				field : 'createUserId',
				title : '创建者id',
				width : 120
			}, {
				field : 'updateDate',
				title : '更新时间',
				width : 120
			} ] ],
			url : 'shiftdata',
			height : 600,
			loading : true,
			method : 'get',
			done : function(res, curr, count) {
				console.log(res);

				console.log('in done');
				console.log(curr);

				console.log(count);
			}
		});

	}();
</script>
-->
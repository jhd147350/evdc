<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>没有排班计划的支持团队</div>

<table id="table1" class="layui-table"
	lay-data="{height:315, url:'teamschedule?schedule=false', id:'noSchedule',response : {statusCode : 200}}"
	lay-filter="filter1">
	<thead>
		<tr>
			<th lay-data="{field:'id', width:80, sort: true}">ID</th>
			<th lay-data="{field:'name', width:80}">团队名</th>
			<th
				lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#toolbar1'}"></th>
		</tr>
	</thead>
</table>
<div>有排班计划的支持团队</div>

<table id="table2" class="layui-table"
	lay-data="{height:315, url:'teamschedule?schedule=true', id:'haveSchedule',response : {statusCode : 200}}"
	lay-filter="filter2">
	<thead>
		<tr>
			<th lay-data="{field:'id', width:80, sort: true}">ID</th>
			<th lay-data="{field:'name', width:80}">团队名</th>
			<th
				lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#toolbar2'}"></th>
		</tr>
	</thead>
</table>
<script type="text/html" id="toolbar1">
    <a class="layui-btn layui-btn-mini" lay-event="create">创建排班计划</a>
</script>
<script type="text/html" id="toolbar2">
    <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
<script src="${ctx}/static/layui/layui.all.js"></script>
<script>
	//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
	;
	!function() {
		var table = layui.table;
		//监听工具条
		table.on('tool(filter1)', function(obj) {
			console.log('table1');
			var data = obj.data;
			if (obj.event === 'create') {
				layer.msg('teamId：' + data.id);
				createSchedule();
			}
		});

		//监听工具条
		table.on('tool(filter2)', function(obj) {
			console.log('shifttable');
			var data = obj.data;
			if (obj.event === 'detail') {
				layer.msg('ID：' + data.id + ' 的查看操作');
				detailSchedule();
			} else if (obj.event === 'del') {
				layer.confirm('真的删除行么', function(index) {
					obj.del();
					layer.close(index);
				});
			} else if (obj.event === 'edit') {
				layer.msg('编辑行：<br>' + JSON.stringify(data))
				editSchedule();
			}
		});
	}();

	function createSchedule() {
		layer.open({
			type : 2,
			title : '创建排班计划',
			skin : 'layui-layer-rim', // 加上边框
			area : [ '1024px', '550px' ], // 宽高
			content : 'schedule/create',
			btn : [ '关闭', '取消' ],
			yes : function(index, layero) {
				layer.close(index);
			},
			cancel : function() {
			}
		});
	}
	
	function detailSchedule() {
		layer.open({
			type : 2,
			title : '排班表',
			skin : 'layui-layer-rim', // 加上边框
			area : [ '1024px', '550px' ], // 宽高
			content : 'schedule/detail',
			btn : [ '关闭', '取消' ],
			yes : function(index, layero) {
				layer.close(index);
			},
			cancel : function() {
			}
		});
	}
	
	function editSchedule() {
		layer.open({
			type : 2,
			title : '编辑排班规则',
			skin : 'layui-layer-rim', // 加上边框
			area : [ '1024px', '550px' ], // 宽高
			content : 'schedule/create',
			btn : [ '关闭', '取消' ],
			yes : function(index, layero) {
				layer.close(index);
			},
			cancel : function() {
			}
		});
	}
	
	function deleteSchedule($,id) {
		$.ajax({
		    url: 'delete?shiftid='+id,
		    type: 'DELETE',
		    success: function(result) {
		    	var data=JSON.parse(result);
		    	console.log(result);
		        console.log(data);
		        if(data.code === 200){
		        	layer.msg("删除成功");
		        }
		    }
		});
	}
</script>
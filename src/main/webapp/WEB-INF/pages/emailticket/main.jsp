<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮件列表</title>
<%@ include file="../global/layui_css.jsp"%>
</head>
<body>
	<table id="table2" class="layui-table"
		lay-data="{url:'emaildata', id:'test',response : {statusCode : 200}, page:true, limits:[20,30,40,50,60], limit:20}"
		lay-filter="email">
		<thead>
			<tr>
				<th lay-data="{field:'id', width:80, sort: true}">ID</th>
				<th lay-data="{field:'fromInbox', width:80}">收件</th>
				<th lay-data="{field:'subject', width:600}">主题</th>
				
				<th
					lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#emailtoolbar'}"></th>
			</tr>
		</thead>
	</table>
	<script type="text/html" id="emailtoolbar">
    <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
	<a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
	</script>
	
		<script src="${ctx}/static/layui/layui.all.js"></script>
	<script>
		//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
		;
		!function() {
			var table = layui.table;
			var $ = layui.jquery;
			//监听工具条
			table.on('tool(email)', function(obj) {
				var data = obj.data;
				var teamId = data.id;
				var teamName = data.name;
				if (obj.event === 'detail') {
					layer.msg('Id：' + data.id);
					console.log(data.subject);
					
					window.open("detail?id=" + data.id);
				} else if (obj.event === 'del') {
					layer.confirm('真的删除行么', function(index) {
						obj.del();
						//var teamId = data.id;
						//deleteSchedule($, teamId);
						layer.close(index);
					});
				}
			});
		}();

	</script>

</body>
</html>
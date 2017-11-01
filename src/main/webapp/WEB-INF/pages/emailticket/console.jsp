<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮件工单列表</title>
<%@ include file="../global/layui_css.jsp"%>
</head>
<body>
	<div class="layui-form layui-form-pane">
		所有工单
		<div class="layui-form-item">
			<div class="layui-input-inline">
				<input id="key" type="text" name="title" required
					lay-verify="required" placeholder="ID/关键字" autocomplete="off"
					class="layui-input">
			</div>

			<div class="layui-inline">
				<label class="layui-form-label"> 状态 </label>
				<div class="layui-input-block">
					<select id="status" lay-verify="required" name="cid">
						<option value="all">所有</option>
						<option value="open">open</option>
						<option value="close">close</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"> 服务 </label>
				<div class="layui-input-block">
					<select id="service" lay-verify="required" name="cid">
						<option value="all">所有</option>
						<c:forEach items="${services}" var="service">
							<option value="${service.name}">${service.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="layui-inline">
				<button class="layui-btn" onclick="searchOrReport(true)">
					<i class="layui-icon">&#xe615;</i>搜索
				</button>
			</div>
			<div class="layui-inline">
				<button class="layui-btn" onclick="searchOrReport(false)">
					<i class="layui-icon">&#xe629;</i>报表
				</button>
			</div>
		</div>
		
		<div class="layui-form-item">
		 	<label class="layui-form-label">日期</label>
			<div class="layui-input-inline" style="width: 100px;">
				<input id="startdate" type="text" name="title" required
					lay-verify="required" placeholder="开始日期" autocomplete="off"
					class="layui-input">
			</div>
			<div class="layui-form-mid">-</div>
			<div class="layui-input-inline" style="width: 100px;">
				<input id="enddate" type="text" name="title" required
					lay-verify="required" placeholder="结束日期" autocomplete="off"
					class="layui-input">
			</div>
		</div>
	</div>
	<table class="layui-table"
		lay-data="{url:'ticketdata', id:'ticket',response : {statusCode : 200}, page:true, limits:[20,30,40,50,60], limit:10}"
		lay-filter="ticket">
		<thead>
			<tr>
				<th lay-data="{field:'id', width:80, sort: true}">ID</th>
				<th lay-data="{field:'title', width:550}">标题</th>
				<th lay-data="{field:'status', width:100}">状态</th>
				<th lay-data="{field:'client', width:100}">客户</th>
				<th lay-data="{field:'service', width:100}">服务</th>
				<th lay-data="{field:'timestamp', width:200}">时间</th>

				<th
					lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#ticketToolbar'}"></th>
			</tr>
		</thead>
	</table>
	<div>所有未处理的邮件</div>
	<table class="layui-table"
		lay-data="{url:'emaildata', id:'test',response : {statusCode : 200}, page:true, limits:[20,30,40,50,60], limit:20}"
		lay-filter="email">
		<thead>
			<tr>
				<th lay-data="{field:'id', width:80, sort: true}">ID</th>
				<th lay-data="{field:'subject', width:550}">主题</th>
				<th lay-data="{field:'fromInbox', width:80}">收件</th>

				<th
					lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#emailToolbar'}"></th>
			</tr>
		</thead>
	</table>
	<script type="text/html" id="ticketToolbar">
    <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
	<a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
	</script>
	<script type="text/html" id="emailToolbar">
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
			
			var laydate = layui.laydate;
			
			//执行一个laydate实例
			laydate.render({
				elem: '#startdate' //指定元素
			});
			
			laydate.render({
				elem: '#enddate' //指定元素
			});
			
			//监听工具条
			table.on('tool(ticket)', function(obj) {
				var data = obj.data;
				var teamId = data.id;
				var teamName = data.name;
				if (obj.event === 'detail') {
					layer.msg('Id：' + data.id);
					console.log(data.subject);

					window.open("ticketdetail?id=" + data.id);
				} else if (obj.event === 'del') {
					layer.confirm('真的删除行么', function(index) {
						obj.del();
						//var teamId = data.id;
						//deleteSchedule($, teamId);
						deleteTicket($, data.id, obj);
						layer.close(index);
					});
				}
			});

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
						//obj.del();
						var id = data.id;
						deleteEmail($, id, obj);
						layer.close(index);
					});
				}
			});
		}();

		function deleteEmail($, id, obj) {
			$.ajax({
				url : 'deletemail?id=' + id,
				type : 'DELETE',
				success : function(result) {
					console.log(result);
					var data = JSON.parse(result);

					console.log(data);
					if (data.code === 200) {
						layer.msg("删除成功");
						obj.del();
					}
				}
			});
		}

		function deleteTicket($, id, obj) {
			$.ajax({
				url : 'deleteticket?id=' + id,
				type : 'DELETE',
				success : function(result) {
					console.log(result);
					var data = JSON.parse(result);

					console.log(data);
					if (data.code === 200) {
						layer.msg("删除成功");
						obj.del();
					}
				}
			});
		}

		//search == true 表示查询数据
		//search == false 表示导出数据为Excel
		function searchOrReport(search) {
			console.log('searchOrReport');
			var table = layui.table;
			var $ = layui.$;

			var idOrKey = $("#key").val();
			var status = $("#status").val();

			var service = $("#service").val();
			
			var startdate = $("#startdate").val();
			
			var enddate = $("#enddate").val();

			var params = 'idorkey=' + idOrKey + '&status=' + status
					+ '&service=' + service + '&startdate=' + startdate + '&enddate=' + enddate;

			console.log(params);
			if(search){
				table.reload('ticket', {
					url : 'ticketdata?' + params
				});
			}else{
				window.open("export?" + params);
			}
			
		}

		function jointParam() {

		}
		
		//给被打开页面使用的
		var test = 'ceshi';
		function myrefresh()
		{
			window.location.reload();//刷新浏览器
		}
		
		
	</script>

</body>
</html>
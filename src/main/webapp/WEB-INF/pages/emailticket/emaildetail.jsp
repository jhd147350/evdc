<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮件详情</title>
<%@ include file="../global/layui_css.jsp"%>

<style type="text/css">
.merge {
	padding-left: 10px;
	padding-right: 10px;
	width: 60px;
}

.block {
	margin-left: 80px;
	margin-right: 60px;
}
</style>
</head>
<body>
	<div>
		<button id="merge" class="layui-btn">合并到已有工单</button>
		<button id="new" class="layui-btn">新建工单</button>
	</div>

	<div>时间：${m.cdate}</div>
	<div>主题：${m.subject}</div>
	<div>来自：${fn:escapeXml(m.from)}</div>
	<div>收件人：${fn:escapeXml(m.to)}</div>
	<div>抄送：${fn:escapeXml(m.cc)}</div>
	<div>正文：</div>
	<div>
		<pre>${m.body}</pre>
	</div>

	<script src="${ctx}/static/layui/layui.all.js"></script>

	<script>
		//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
		;
		!function() {
			var $ = layui.jquery;

			$("#merge").click(function() {
				openLayer(true);
			});

			$("#new").click(function() {
				openLayer(false);
			});
		}();

		function openLayer(isMerge) {
			var layer = layui.layer;
			var $ = layui.jquery;
			var title;
			var closeBtnStr;
			var contentHtml;
			var emailId = '${emailId}';
			var timestamp = '${m.cdate}';
			console.log(emailId);
			if (isMerge) {
				title = '合并到';
				closeBtnStr = '合并';
				contentHtml = $('#onlyId').html() + $('#onlyNote').html();
			} else {
				title = '新建';
				closeBtnStr = '新建';
				contentHtml = $('#ticketInfo').html() + $('#onlyNote').html();
			}

			layer.open({
				type : 1,
				title : title + '工单',
				skin : 'layui-layer-rim', // 加上边框
				area : '500px', // 宽,默认自适应
				content : contentHtml,
				btn : [ closeBtnStr, '取消' ],
				yes : function(index, layero) {

					if (isMerge) {
						var ticketId = $("#ticketId").val();
						mergeEmailTicket(emailId, ticketId);
					} else {
						createEmailTicket(emailId, timestamp);
					}
					layer.close(index);

				},
				cancel : function() {
					console.log('iframecancel');
				},
				end : function() {
					console.log('iframe end');
				}
			});
		}
		function mergeEmailTicket(emailId, ticketId) {
			var $ = layui.$;
			var layer = layui.layer;

			var note = $("#note").val();

			var json = {};
			json.emailId = emailId;
			json.ticketId = ticketId;
			json.note = note;
			console.log(JSON.stringify(json));
			$.ajaxSetup({
				contentType : "application/json; charset=utf-8"
			});

			$.post("merge", JSON.stringify(json), function(data, status) {
				console.log('post -> merge');
				console.log(data, status);
				if (data.code == 200) {
					layer.msg('合并成功,即将关闭本页面' + data.info, function() {
						window.close();
					});

					//parent.reload(parent.table);//重新加载表格
				} else {
					layer.msg('合并失败' + data.info);
				}

			});
		}

		function createEmailTicket(emailId, timestamp) {
			var $ = layui.$;
			var layer = layui.layer;

			var title = $("#title").val();
			var client = $("#client").val();
			var service = $("#service").val();
			var note = $("#note").val();

			var json = {};
			json.emailId = emailId;
			json.timestamp = timestamp;
			json.title = title;
			json.client = client;
			json.service = service;
			json.note = note;
			console.log(JSON.stringify(json));
			$.ajaxSetup({
				contentType : "application/json; charset=utf-8"
			});

			$.post("create", JSON.stringify(json), function(data, status) {
				console.log('post -> create');
				console.log(data, status);
				if (data.code == 200) {
					layer.msg('创建成功，即将关闭本页面' + data.info, function() {
						window.close();
					});
				} else {
					layer.msg('创建失败' + data.info);
				}

			});
		}
	</script>

</body>
<script id="onlyId" type="text/html">
	<div class="layui-form-item" style="margin-top: 15px">
		<label class="layui-form-label merge">ID</label>
		<div class="layui-input-block block">
			<input id="ticketId" type="text" name="title" required lay-verify="required"
				placeholder="请输入ID" autocomplete="off" class="layui-input">
		</div>
	</div>
</script>

<script id="ticketInfo" type="text/html">
	<div class="layui-form-item" style="margin-top: 15px">
		<label class="layui-form-label merge">标题</label>
		<div class="layui-input-block block">
			<input id="title" type="text" name="title" required lay-verify="required"
				placeholder="请输入标题" autocomplete="off" class="layui-input" value="${m.subject}">
		</div>
	</div>
	<div class="layui-form-item" style="margin-top: 15px">
		<label class="layui-form-label merge">客户</label>
		<div class="layui-input-block block">
			<input id="client" type="text" name="title" required lay-verify="required"
				placeholder="请输入客户" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item" style="margin-top: 15px">
		<label class="layui-form-label merge">服务</label>
		<div class="layui-input-block block">
			<input id="service" type="text" name="title" required lay-verify="required"
				placeholder="请输入服务" autocomplete="off" class="layui-input">
		</div>
	</div>
</script>

<script id="onlyNote" type="text/html">
	<div class="layui-form-item layui-form-text" style="margin-top: 15px">
		<label class="layui-form-label merge">备注</label>
		<div class="layui-input-block block">
			<textarea id="note" name="desc" placeholder="请输入备注" class="layui-textarea"
				rows="7"></textarea>
		</div>
	</div>
</script>

</html>
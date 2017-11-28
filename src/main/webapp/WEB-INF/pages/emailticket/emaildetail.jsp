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
		<!-- 合并过后 ticketId肯定不为0 所以也不需要显示了-->
		<c:if test="${m.ticketId == 0}">
			<button id="merge" class="layui-btn">合并到已有工单</button>
			<button id="new" class="layui-btn">新建工单</button>
		</c:if>
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



	<!--<script src="${ctx}/static/layui/layui.all.js"></script>  -->
	<script src="${ctx}/static/layui/layui.all.js"></script>

	<script>
		//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
		;
		!function() {
			var $ = layui.jquery;
			//var form = layui.form;
			//form.render();
			var element = layui.element;

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
				contentHtml = $('#onlyId').html() + $('#comment').html();
			} else {
				title = '新建';
				closeBtnStr = '新建';
				contentHtml = $('#myform').html() + $('#description').html()
						+ $('#mys').html();
				//contentHtml = '<div id="mydiv"></div>' //使用模板 可以后续渲染内容
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

			//可以使用模板引擎渲染
			//var laytpl = layui.laytpl;
			//var gettpl = $('#myform').html() + $('#onlyNote').html();
			//var view = document.getElementById('mydiv');
			//laytpl(gettpl).render({}, function(html) {
			//	view.innerHTML = html;
			//});

		}

		function mergeEmailTicket(emailId, ticketId) {
			var $ = layui.$;
			var layer = layui.layer;

			var comm = $("#comm").val();

			var json = {};
			json.emailId = emailId;
			json.ticketId = ticketId;
			json.comm = comm;
			console.log(JSON.stringify(json));
			$.ajaxSetup({
				contentType : "application/json; charset=utf-8"
			});

			$.post("merge", JSON.stringify(json), function(data, status) {
				console.log('post -> merge');
				console.log(data, status);
				if (data.code == 200) {
					layer.msg('合并成功,即将关闭本页面' + data.info, function() {

						console.log(opener.test);
						opener.myrefresh();
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
			var severity = $("#severity").val();
			var service = $("#service").val();
			var desc = $("#desc").val();

			console.log(title);
			console.log(severity);
			console.log(service);
			console.log(desc);

			var json = {};
			json.emailId = emailId;
			json.timestamp = timestamp;
			json.title = title;
			json.severity = severity;
			json.service = service;
			json.desc = desc;
			console.log(JSON.stringify(json));
			$.ajaxSetup({
				contentType : "application/json; charset=utf-8"
			});

			$.post("create", JSON.stringify(json), function(data, status) {
				console.log('post -> create');
				console.log(data, status);
				if (data.code == 200) {
					layer.msg('创建成功，即将关闭本页面' + data.info, function() {
						console.log(opener.test);
						opener.myrefresh();//opener是打开这个页面的那个页面 刷新原有页面
						window.close();
					});
				} else {
					layer.msg('创建失败' + data.info);
				}

			});
		}
	</script>

</body>

<script id="myform" type="text/html">
	<form class="layui-form" action="">
			<div class="layui-form-item" style="margin-top: 15px">
				<label class="layui-form-label merge">标题</label>
				<div class="layui-input-block block">
					<input id="title" type="text" name="title" required
						lay-verify="required" placeholder="请输入标题" autocomplete="off"
						class="layui-input" value="${m.subject}">
				</div>
			</div>

			<!-- 客户 目前还不符合原有数据库字段设计
			<div class="layui-form-item" style="margin-top: 15px">
				<label class="layui-form-label merge">客户</label>
				<div class="layui-input-block block">
					<input id="client" type="text" name="title" required
						lay-verify="required" placeholder="请输入客户" autocomplete="off"
						class="layui-input">
				</div>
			</div> -->

			<div class="layui-form-item" style="margin-top: 15px">
				<label class="layui-form-label merge">服务</label>
				<div class="layui-input-block block">
					<select id="service" lay-verify="required" name="cid">
						<c:forEach items="${services}" var="service">
							<c:choose>
								<c:when test="${service.name=='other'}">
									<option value="${service.id}" selected="selected">${service.name}</option>
   								</c:when>
   
   								<c:otherwise>
									<option value="${service.id}">${service.name}</option>
  								 </c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="layui-form-item" style="margin-top: 15px">
				<label class="layui-form-label merge">严重等级</label>
				<div class="layui-input-block block">
					<select id="severity" lay-verify="required" name="severity">
						<option value="Sev1">1-严重</option>
                        <option value="Sev2">2-高级</option>
                        <option value="Sev3" selected="selected">3-一般</option>
                        <option value="Sev4">4-最低</option>
					</select>
				</div>
			</div>
	</form>		
</script>
<div style="display: none" id="mys">
	<script type="text/javascript">
		;
		!function() {
			var form = layui.form;
			form.render();
		}();
	</script>
</div>
<script id="onlyId" type="text/html">
	<div class="layui-form-item" style="margin-top: 15px">
		<label class="layui-form-label merge">ID</label>
		<div class="layui-input-block block">
			<input id="ticketId" type="text" name="title" required lay-verify="required"
				placeholder="请输入ID" autocomplete="off" class="layui-input">
		</div>
	</div>
</script>



<script id="description" type="text/html">
	<div class="layui-form-item layui-form-text" style="margin-top: 15px">
		<label class="layui-form-label merge">描述</label>
		<div class="layui-input-block block">
			<textarea id="desc" name="desc" placeholder="请输入描述" class="layui-textarea"
				rows="7"></textarea>
		</div>
	</div>
</script>

<script id="comment" type="text/html">
	<div class="layui-form-item layui-form-text" style="margin-top: 15px">
		<label class="layui-form-label merge">评论</label>
		<div class="layui-input-block block">
			<textarea id="comm" name="desc" placeholder="请输入评论" class="layui-textarea"
				rows="7"></textarea>
		</div>
	</div>
</script>

</html>
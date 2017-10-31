<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮件详情</title>
<link rel="stylesheet" href="${ctx}/static/css/emailticketdetail.css">
</head>
<body>
	<div class="ticketContainer">
		<ul class="space"></ul>
		<div class="ticketTop">
			<div class="ticketId">${ticket.id}</div>
			<div class="ticketTitle">(${ticket.status})${ticket.title}</div>
			<button class="closeButton"><c:choose>
					<c:when test="${ticket.status=='close'}">重开</c:when>
					<c:otherwise>关闭</c:otherwise>
				</c:choose>
			</button>		
		</div>
		<c:forEach items="${emails}" var="email">
			<div class="email">
				<div class="event">
					<span class="action"><c:choose>
					<c:when test="${email.fromInbox==true}">Received</c:when>
					<c:otherwise>Sent</c:otherwise>
					</c:choose></span> 
					<span class="date">${email.cdate}</span>
					<button class="deleteButton" id="${email.id}">删除</button>
				</div>

				<div>
					<div>
						<div class="emailHeader">
							<div class="emailHeaderName">From:</div>
							<div class="emailHeaderValue">${email.from}</div>
							<div class="emailHeaderName">Date:</div>
							<div class="emailHeaderValue">${email.cdate}</div>
							<div class="emailHeaderName">To:</div>
							<div class="emailHeaderValue">${email.to}</div>
							<div class="emailHeaderName">Cc:</div>
							<div class="emailHeaderValue">${email.cc}</div>
							<br>
							<div class="emailHeaderName">Subject:</div>
							<div class="emailHeaderValue">${email.subject}</div>
							<div class="emailHeaderName">Note:</div>
							<div class="emailHeaderValue">${email.note}</div>
						</div>
						<div class="emailBody">
							<div>备注：${email.note}</div>
							<button class="flip">展开邮件内容</button>
							<pre style="display: none">${email.body}</pre>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>


	<script src="${ctx}/static/layui/layui.all.js"></script>
	<script>
		//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
		
		var flip = false;
		;
		!function() {
			var $ = layui.jquery;

			$("button.deleteButton").click(function() {
				var id = $(this).attr("id");
				console.log(id);
				deleteMail(id,this);

			});

			$("button.closeButton").click(function() {
				var text = $(this).text();
				
				if(text == "关闭"){
					console.log("shi");
					closeTicket();
				}else{
					reopenTicket();
				}

				console.log(text);

			});
			
			//动画
			$(".flip").click(function(){
				if(flip){
					$(this).text("展开邮件内容");
				}else{
					$(this).text("隐藏邮件内容");
				}
				flip = !flip;
				//jquery的动画
				$(this).next().slideToggle("slow");
			});

		}();

		function deleteMail(id,thiss) {
			var $ = layui.$;
			$.ajax({
				url : 'reset?id=' + id,
				type : 'DELETE',
				success : function(result) {
					var data = JSON.parse(result);
					console.log(result);
					console.log(data);
					if (data.code === 200) {
						layer.msg("删除成功");
						$(thiss).parent().parent().remove();
					}
				}
			});

		}

		function closeTicket() {
			var $ = layui.$;
			var ticketId = '${ticket.id}';
			$.ajax({
				url : 'closeticket?id=' + ticketId,
				type : 'DELETE',
				success : function(result) {
					var data = JSON.parse(result);
					console.log(result);
					console.log(data);
					if (data.code === 200) {
						layer.msg('关单成功，即将关闭本页面' + data.info, function() {
							window.close();
						});
					}
				}
			});
		}
		
		function reopenTicket() {
			var $ = layui.$;
			var ticketId = '${ticket.id}';
			$.ajax({
				url : 'reopenticket?id=' + ticketId,
				type : 'DELETE',
				success : function(result) {
					var data = JSON.parse(result);
					console.log(result);
					console.log(data);
					if (data.code === 200) {
						layer.msg('重开成功');
					}
				}
			});
		}
		
	</script>

</body>
</html>
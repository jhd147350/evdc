<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮件详情</title>
<link rel="stylesheet"
	href="${ctx}/static/css/emailticketdetail.css">
</head>
<body>
	<div class="ticketContainer">
		<ul class="space"></ul>
		<div class="ticketTop">
			<div class="ticketId">${ticket.id}</div>
			<div class="ticketTitle">(${ticket.status})${ticket.title}</div>
		</div>
		<c:forEach items="${emails}" var="email">
			<div class="email">
				<div class="event">
					<span class="action">Received</span> 
					<span class="date">2017/6/26 上午 07:44</span>
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
							<div class="emailHeaderValue">${email.cc}</div><br>
							<div class="emailHeaderName">Subject:</div>
							<div class="emailHeaderValue">${email.subject}</div>
							<div class="emailHeaderName">Note:</div>
							<div class="emailHeaderValue">${email.note}</div>
						</div>
						<div class="emailBody">
							<pre>${email.body}</pre>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>
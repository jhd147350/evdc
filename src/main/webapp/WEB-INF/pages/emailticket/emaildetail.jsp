<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮件详情</title>
</head>
<body>

<div>时间：${m.cdate}</div>
<div>主题：${m.subject}</div>
<div>来自：${fn:escapeXml(m.from)}</div>
<div>收件人：${fn:escapeXml(m.to)}</div>
<div>抄送：${fn:escapeXml(m.cc)}</div>
<div>正文：</div>
<div>
<pre>${m.body}</pre></div>

</body>
</html>
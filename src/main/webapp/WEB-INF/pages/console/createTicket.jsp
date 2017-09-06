<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建工单</title>
<%@ include file="../global/layui_css.jsp"%>
<link rel="stylesheet"
          href="<%=request.getContextPath() %>/static/css/create-ticket.css">
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<%@ include file="../global/top.jsp"%>
		</div>
		<%@ include file="createTicket-create.jsp"%>
	</div>

	<%@ include file="../global/layui_js.jsp"%>
	<script type="text/javascript">
	
	;
	!function() {
		var $ = layui.jquery;
		 $("#create_ticket").click(function () {
			 alert("tijiaole");
	         $.post("<%=request.getContextPath()%>/ticket/create1",
	             {
	                 title:"Donald Duck",
	                 detail:"Donald Duck",
	                 type:"Donald Duck",
	                 attachment:"null",
	                 severity:"Donald Duck"
	             },
	             function(data,status){
	                 alert("Data: " + data + "\nStatus: " + status);
	             });

	     });

	}();
	
	</script>
</body>
</html>
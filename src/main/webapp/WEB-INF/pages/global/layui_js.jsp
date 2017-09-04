<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>
<script>
	//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
	;
	!function() {
		var element = layui.element;
		var form = layui.form;
		var table = layui.table;

	}();
</script>
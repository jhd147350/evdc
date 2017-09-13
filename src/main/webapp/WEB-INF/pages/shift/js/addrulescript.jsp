<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="${ctx}/static/layui/layui.all.js"></script>
<script>
	;
	!function() {
		var laydate = layui.laydate;
		// 执行一个laydate实例
		laydate.render({
			elem : '#starttime' // 指定元素
			,
			type : 'time'
		});
		laydate.render({
			elem : '#endtime' // 指定元素
			,
			type : 'time'
		});

		var i = 1;
		var $ = layui.jquery;
		$("#add").click(
				function() {
					var shiftname = $("#shiftname").val();
					var starttime = $("#starttime").val();
					var endtime = $("#endtime").val();
					var list = [ name, starttime, endtime ];
					/*
					 * for(var j=0;j<list.size();j++){ if(list[j]==null){
					 * layer.msg(list[j]+"不能为空"); } }
					 */
					if (shiftname.length === 0 || starttime.length === 0
							|| endtime.length === 0) {

						layer.msg("所有选项都为必填项");
						return;

					}
					$("#table").append(
							"<tr>\n" + "        <td>" + name + "</td>\n"
									+ "        <td>" + starttime + "</td>\n"
									+ "        <td>" + endtime + "</td>\n"
									+ "        <td>" + i + "</td>\n"
									+ "        <td>暂不支持</td>\n" + "    </tr>");
					i++;
				});
		$("#delete").click(function() {
			$("#table tbody").empty();
			i = 1;
		});
	}();
</script>
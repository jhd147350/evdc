<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="${ctx}/static/layui/layui.all.js"></script>
<script>
	;
	!function() {
		var laydate = layui.laydate;
		
		var $ = layui.jquery;
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

		$("#create").click(function() {
			//注意：parent 是 JS 自带的全局对象，可用于操作父页面
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			//console.log(index);
			
			var tableTr = $("#table tbody").children();

			//console.log(tableTr);
			var rules = new Array();
			for (var n = 0; n < tableTr.length; n++) {
				console.log(tableTr[n]);
				var tdArr = tableTr.eq(n).find("td");
				var name = tdArr.eq(0).text();
				var starttime = tdArr.eq(1).text();
				var endtime = tdArr.eq(2).text();
				var order = tdArr.eq(3).text();
				var info = tdArr.eq(4).text();

				var rule = {

				};
				rule.shiftname = name;
				rule.starttime = starttime;
				rule.endtime = endtime;
				rule.order = parseInt(order);
				rule.info = info;
				rules.push(rule);

			}

			// console.log(table);

			var shiftname = $("#shiftname").val();
			var createuserid = 1;
			var json = {};
			json.shiftname = shiftname;
			json.createuserid = createuserid;
			json.rules = rules;

			//console.log(JSON.stringify(json));
			console.log(json);
			$.ajaxSetup({
				contentType : "application/json; charset=utf-8"
			});

			$.post("../create", JSON.stringify(json), function(
					data, status) {
				console.log('post -> ../create');
				console.log(data, status);
				if (data.code == 200) {
					parent.layer.msg('创建成功' + data.info);
					console.log(parent);
					console.log(parent.layer);
					console.log(parent.table);
					
					//parent.reload(parent.table);//重新加载表格
				} else {
					parent.layer.msg('创建失败' + data.info);
				}

			});
			parent.layer.close(index);// 关闭
		});

		var i = 1;
		
		$("#add").click(
				function() {
					var shiftname = $("#shiftname").val();
					console.log($("#shiftname").val());
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
							"<tr>\n" + "        <td>" + shiftname + "</td>\n"
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
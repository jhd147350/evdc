<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="${ctx}/static/layui/layui.all.js"></script>
<script>
	;
	!function() {
		var form = layui.form;
		var layer = layui.layer;
		var $ = layui.jquery;

		var table = layui.table;

		//日期选择
		var laydate = layui.laydate;

		// 执行一个laydate实例
		laydate.render({
			elem : '#begindate' // 指定元素
			,
			type : 'date'
		});

		var circle;
		var order = 2;

		$('#generateTable').click(
				function() {
					var select = $('#staff_select').text();
					var td = "<td contenteditable='true'>" + select + "</td>";
					circle = parseInt($('#input_circle').val());
					console.log(circle);
					if (circle.length == 0) {
						layer.msg('循环周期不能为空');
					} else if (isNaN(circle)) {
						layer.msg('循环周期请输入整数');
					} else {
						$('#table').empty();
						$('#table').append($('#staff_table').text());

						for (var i = 0; i < circle; i++) {
							$('#schedule_c').append(
									"<th colspan='" + order + "'>" + (i + 1)
											+ "</th>");
							for (var j = 0; j < order; j++) {
								$('#schedule_o').append(
										"<th>" + (j + 1) + "</th>");
								$('#data_p').append(td);
								$('#data_s').append(td);
							}
						}
					}
				});

		$('#addRow').click(function() {
			$('#table').append("<tr></tr>");
			console.log('click addRow');

		});
		$('#exe').click(
				function() {
					//$('#table_body').append("<tr></tr>");
					console.log('click exe');
					var shiftIdStr=$("#shift").val();
					var shiftId=parseInt(shiftIdStr);
					//注意：parent 是 JS 自带的全局对象，可用于操作父页面									
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					//console.log(index);

					var tableTr = $("#table tbody").children();

					var staffs = new Array();

					for (var n = 0; n < tableTr.length; n++) {
						
						var tdArr = tableTr.eq(n).find("td");
						//var name = tdArr.eq(0).text();

						for (var c = 1; c <= circle; c++) {
							for (var o = 1; o <= order; o++) {
								var userTd = tdArr.eq((c - 1) * order + o - 1)
										.find('select');
								
								var staff = {};
								
								var userIdStr=userTd.val();

								staff.userId = parseInt(userIdStr);
								//var selectedText = userTd.find("option:selected").text();
								staff.shiftId = shiftId;
								staff.orderOfCircle = c;
								staff.orderOfDay = o;
								staff.isPrimary = n === 0 ? true : false;
								if (userIdStr.length !== 0){
									staffs.push(staff);
								}
							}
						}
					}
					
				
					var json={};
					json.teamId=${teamId};
					json.begainDate=$("#begindate").val();
					json.circle=circle;
					json.shiftId = shiftId;
					
					json.staffs=staffs;
					
					console.log(JSON.stringify(json));
				
					/*var shiftname = $("#shiftname").val();
					var createuserid = 6;
					var json = {};
					json.shiftname = shiftname;
					json.createuserid = createuserid;
					json.rules = rules;

					//console.log(JSON.stringify(json));
					console.log(json);
					$.ajaxSetup({
						contentType : "application/json; charset=utf-8"
					});

					$.post("../create", JSON.stringify(json), function(data, status) {
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
					parent.layer.close(index);// 关闭*/

				});
	}();
</script>
<script type="text/html" id="staff_select">
	<select name="user" lay-verify="required">
		<option value=""></option>
		<c:forEach items="${users}" var="temp">
			<option value="${temp.id}">${temp.name}</option>
		</c:forEach>
	</select>
</script>
<script type="text/html" id="staff_table">
    <thead>
    <tr id="schedule_c">
        <th>周期顺序</th>
    </tr>
    <tr id="schedule_o">
        <th>班次顺序</th>
    </tr>
    </thead>
    <tbody id="table_body">
    <tr id="data_p">
        <th align="center" class="mytablehead">主值班人*</th>
    </tr>
    <tr id="data_s">
        <th>次值班人</th>
    </tr>
    </tbody>
</script>
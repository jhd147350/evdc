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
		//var circle = 4;
		var order = 2;

		$('#generateTable').click(
				function() {
					var select = $('#staff_select').text();
					var td = "<td contenteditable='true'>" + select + "</td>";
					var circle = $('#input_circle').val();
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
			$('#table_body').append("<tr></tr>");

		});
	}();
</script>
<script type="text/html" id="staff_select">
	<select name="user" lay-verify="required">
		<option value=""></option>
		<c:forEach items="${users}" var="temp">
			<option value="${temp.id}">${temp.nickname}</option>
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
//弃用，放到jsp中，不用考虑更新js后，浏览器还是用缓存js
//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
;
!function() {
	var layer = layui.layer;
	var $ = layui.jquery;
	var table =layui.table;

	var Str;
	$(document).ready(function() {
		$.get("createshiftrule", function(data, status) {
			Str = data;
		});

	});

	$("#create").click(function() {
		layer.open({
			type : 1,
			title : '创建排班规则',
			skin : 'layui-layer-rim', // 加上边框
			area : [ '600px', '500px' ], // 宽高
			content : Str,
			btn : [ '创建', '取消' ],
			yes : function(index, layero) {

				console.log(index);
				console.log(layero);
				var tableTr = layero.find("#table tbody").children();

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

				var shiftname = $("#name").val();
				var createuserid = 1;
				var json = {};
				json.shiftname = shiftname;
				json.createuserid = createuserid;
				json.rules = rules;

				console.log(JSON.stringify(json));
				console.log("${ctx}");
				$.ajaxSetup({
					contentType : "application/json; charset=utf-8"
				});

				$.post("create ", JSON.stringify(json), function(data, status) {
					console.log(data, status);
					if(data.code==200){
						layer.msg('创建成功'+data.info);
						reload(table);//重新加载表格
					}else{
						layer.msg('创建失败'+data.info);
					}
					
				});

				layer.close(index);// 关闭
				

			},
			cancel : function() {

			}
		});
	});

}();

function reload(table) {
	table.reload('table', {
		url: 'shiftdata'
	});
}
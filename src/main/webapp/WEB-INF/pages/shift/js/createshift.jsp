<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="${ctx}/static/layui/layui.all.js"></script>
<script>
	//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
	;
	!function() {
		var layer = layui.layer;
		var $ = layui.jquery;
		var table = layui.table;		
		//监听工具条
		table.on('tool(shifttable)', function(obj){
			console.log('shifttable');
		  var data = obj.data;
		  if(obj.event === 'detail'){
		    layer.msg('ID：'+ data.id + ' 的查看操作');
		    shiftDetail(data.id);
		  } 
		  else if(obj.event === 'del'){
		    layer.confirm('真的删除行么', function(index){
		      obj.del();
		      deleteShift($,data.id);
			  layer.close(index);
			});
		  } 
		  else if (obj.event === 'edit') {
				layer.alert('编辑行：<br>' + JSON.stringify(data))
		  }
		});

		$("#create").click(
				function() {
					layer.open({
						type : 2,
						title : '创建排班规则',
						skin : 'layui-layer-rim', // 加上边框
						area : [ '600px', '600px' ], // 宽高
						content : 'rule/create',
						btn : [ '创建', '取消' ],
						yes : function(index, layero) {

							console.log(index);
							console.log(layero);
							var tableTr = layero.find("#table tbody")
									.children();

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

							var shiftname = layero.find("#shiftname").val();
							var createuserid = 1;
							var json = {};
							json.shiftname = shiftname;
							json.createuserid = createuserid;
							json.rules = rules;

							console.log(JSON.stringify(json));
							console.log(json);
							console.log("${ctx}");
							$.ajaxSetup({
								contentType : "application/json; charset=utf-8"
							});

							/*$.post("create", JSON.stringify(json), function(
									data, status) {
								console.log(data, status);
								if (data.code == 200) {
									layer.msg('创建成功' + data.info);
									reload(table);//重新加载表格
								} else {
									layer.msg('创建失败' + data.info);
								}

							});*/

							//layer.close(index);// 关闭

						},
						cancel : function() {

						}
					});
				});

	}();

	function reload(table) {
		table.reload('table', {
			url : 'shiftdata'
		});
	}
	function shiftDetail(id) {
		//var shiftDetailPage;
		/*$.get("detailshiftrule", function(data, status) {
			//shiftDetailPage = data;
		});*/
		console.log(id);
		layer
				.open({
					type : 2,//2表示是iframe
					title : '查看排班规则',
					skin : 'layui-layer-rim', // 加上边框
					area : [ '600px', '550px' ], // 宽高
					content : 'rule/detail?shiftid='
							+ id,
					btn : [ '确定', '关闭' ],
					yes : function(index, layero) {
						layer.close(index);// 关闭

					},
					cancel : function() {

					}
				});

	}
	function deleteShift($,id) {
		$.ajax({
		    url: 'rule/delete?shiftid='+id,
		    type: 'DELETE',
		    success: function(result) {
		    	var data=JSON.parse(result);
		    	console.log(result);
		        console.log(data);
		        if(data.code === 200){
		        	layer.msg("删除成功");
		        }
		    }
		});
	}
</script>

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
		table.on('tool(shifttable)', function(obj) {
			console.log('shifttable');
			var data = obj.data;
			if (obj.event === 'detail') {
				layer.msg('ID：' + data.id + ' 的查看操作');
				shiftDetail(data.id);
			} else if (obj.event === 'del') {
				layer.confirm('真的删除行么', function(index) {
					obj.del();
					deleteShift($, data.id);
					layer.close(index);
				});
			} else if (obj.event === 'edit') {
				layer.alert('编辑行：<br>' + JSON.stringify(data))
			}
		});

		$("#create").click(function() {
			layer.open({
				type : 2,
				title : '创建排班规则',
				skin : 'layui-layer-rim', // 加上边框
				area : [ '600px', '600px' ], // 宽高
				content : 'rule/create',
				btn : [ '关闭', '取消' ],
				yes : function(index, layero) {

					layer.close(index);

				},
				cancel : function() {
					console.log('iframecancel');
				},
				end : function() {
					console.log('iframe end');
					reload(table);
				}
			});
		});

	}();

	function reload(table) {
		console.log('table->'+table);
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
		layer.open({
			type : 2,//2表示是iframe
			title : '查看排班规则',
			skin : 'layui-layer-rim', // 加上边框
			area : [ '600px', '550px' ], // 宽高
			content : 'rule/detail?shiftid=' + id,
			btn : [ '确定', '关闭' ],
			yes : function(index, layero) {
				layer.close(index);// 关闭

			},
			cancel : function() {

			}
		});

	}
	function deleteShift($, id) {
		$.ajax({
			url : 'rule/delete?shiftid=' + id,
			type : 'DELETE',
			success : function(result) {
				var data = JSON.parse(result);
				console.log(result);
				console.log(data);
				if (data.code === 200) {
					layer.msg("删除成功");
				}
			}
		});
	}
</script>

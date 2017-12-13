<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            客户工单控制台
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="../static/css/x-admin.css" media="all">
    </head>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>工单控制台
              </cite></a>
          
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            <div class="layui-form layui-form-pane">
                
                  <div class="layui-form-item">


					<div class="layui-input-inline">
                      <input type="text" id="keyword" name="keyword"  placeholder="ticketId/关键字" autocomplete="off" class="layui-input">
                    </div>
					<div class="layui-inline">
                        <label class="layui-form-label">
                            服务类型
                        </label>
                   
                        <div class="layui-input-block">
                            <select id="serviceType" lay-verify="required" name="cid">
									<option value=".*" >所有</option>
                                    <c:forEach items="${ticketServices}" var="item" varStatus="status">  
										<option name="ticketService[]" value="^${item.id }$" > ${item.name}</option>
									</c:forEach>

                            </select>
                        </div>
                    </div>
                    
                    
                    <div class="layui-inline">
                        <label class="layui-form-label">
                            严重等级
                        </label>
                   
                        <div class="layui-input-block">
                            <select id="severity" lay-verify="required" name="cid">
                            		<option value=".*">所有</option>
                                 <!-- <optgroup label="Layui相关">-->
                                    <option value="Sev1">1-严重</option>
                                    <option value="Sev2">2-高级</option>
                                    <option value="Sev3">3-一般</option>
                                    <option value="Sev4">4-最低</option>
                                <!--</optgroup>-->
                            </select>
                        </div>
                    </div>
                    
                    
                    
                    <div class="layui-inline">
                        <label class="layui-form-label">
                            状态
                        </label>
                   
                        <div class="layui-input-block">
                            <select id="ticketStatus" lay-verify="required" name="cid">
                            		<option value=".*">所有</option>
                                 <!-- <optgroup label="Layui相关">-->
                                    <option value="New">新建</option>
                                    <option value="In_Process">已受理</option>
                                    <option value="Resolved">已解决</option>
                                    <option value="Closed">已关闭</option>
                                <!--</optgroup>-->
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button id="customerTicketSreachBut" class="layui-btn" onclick="customerTicket_sreach(this,'1')"><i class="layui-icon">&#xe615;</i></button>
                    </div>
                  </div>
                
            </div>
            <xblock><button class="layui-btn" onclick="customerTicket_create('创建工单','./customerTicketCreatePage','600','500')"><i class="layui-icon">&#xe608;</i>创建工单</button>
            </xblock>
			<table id="ticketList" lay-filter="ticketListFilter"></table>
            <div id="page"></div>
        </div>
        <script src="../static/layui/layui.js" charset="utf-8"></script>
        <script src="../static/js/x-layui.js" charset="utf-8"></script>
        <script type="text/html" id="showTicket">
  			<a class="layui-btn layui-btn-mini" lay-event="detail">查看详情</a>
		</script>
        <script>
            layui.use(['table','laydate','element','layer','form'], function(){
                $ = layui.jquery;//jquery
				var form = layui.form;
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              //table = layui.table;
              $('#ticketSreachBut').click();    
            });
            
            /*查询*/
            function customerTicket_sreach(obj,id){
            	obj.disabled=true;
            	obj.setAttribute("class" , "layui-btn layui-btn-disabled");
            	var keyword = document.getElementById("keyword").value;
            	var serviceType = document.getElementById("serviceType").value;
            	var severity = document.getElementById("severity").value;
                var ticketStatus = document.getElementById("ticketStatus").value;
                
                table = layui.table;
                table.render({
            	  	elem: '#ticketList'
            	    ,page: true //开启分页
            	    ,limits:[10,20,30,40,50,60]
                	,limit:10
            	    ,cols: [[ //表头
            	      {field: 'id', title: 'ticketId', width:100, sort: true, fixed: 'left'}
            	      ,{field: 'title', title: '标题', width:250}
            	      ,{field: 'serviceName', title: '服务', width:100, sort: true}
            	      ,{field: 'severity', title: '严重等级', width:100} 
            	      ,{field: 'status', title: '状态', width: 100}
            	      ,{field: 'customerUserName', title: '客户', width: 100, sort: true}
            	      ,{field: 'updateDate', title: '更新时间', width: 100, sort: true}
            	      ,{field: 'submitDate', title: '提交日期', width: 100, sort: true}
            	      ,{fixed: 'right', width:150, align:'center', toolbar: '#showTicket'}
            	    ]]
              		,url: './findTicketByCustomerTeamAndKeyword'
            	  	,where: {"keyword": "%"+keyword+"%", "service": serviceType, "severity": severity, "status": ticketStatus} //如果无需传递额外参数，可不加该参数
            	  	,method: 'post' //如果无需自定义HTTP类型，可不加该参数
            	  	//,request: {} //如果无需自定义请求参数，可不加该参数
            	  	,response: {
            	  		statusCode : 200
						,dataName: 'ticketViewList' //数据列表的字段名称，默认：data
            	  	  } //如果无需自定义数据响应名称，可不加该参数
            	  	,done: function(res, curr, count){
            	  	    //如果是异步请求数据方式，res即为你接口返回的信息。
            	  	    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            	  		obj.disabled=false;
                   		obj.setAttribute("class" , "layui-btn");
            	  	  }
            	});
              	//监听工具条
    			table.on('tool(ticketListFilter)', function(obj) {
    				var data = obj.data;
    				var ticketId = data.id;
    				if (obj.event === 'detail') {
    					x_admin_show('工单详情','./customerTicketShowPage?ticketId='+ticketId);
    				}
    			});
            }
             /*创建工单*/
            function customerTicket_create(title,url,w,h){
                x_admin_show(title,url,w,h);
            }
            //编辑 
           function question_edit (title,url,id,w,h) {
                x_admin_show(title,url,w,h); 
            }

            /*删除*/
            function question_del(obj,id){
                layer.confirm('确认要删除吗？',function(index){
                    //发异步删除数据
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!',{icon:1,time:1000});
                });
            }
            </script>
            <script>
        var _hmt = _hmt || [];
        (function() {
          var hm = document.createElement("script");
          hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
          var s = document.getElementsByTagName("script")[0]; 
          s.parentNode.insertBefore(hm, s);
        })();
        </script>
    </body>
</html>
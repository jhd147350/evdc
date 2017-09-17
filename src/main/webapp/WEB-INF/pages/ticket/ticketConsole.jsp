<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            工单控制台
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
                        <label class="layui-form-label">
                            查询方法
                        </label>
                   
                        <div class="layui-input-block">
                            <select id="findMethod" lay-verify="required" name="cid">
                                    <c:forEach items="${authoritys}" var="item" varStatus="status">  
										<option name="findMethod[]" value="${item.path }" > ${item.authName }</option>
									</c:forEach>
                            </select>
                        </div>
                    </div>




                    
                    <div class="layui-inline">
                        <button class="layui-btn" onclick="ticket_sreach(this,'1')"><i class="layui-icon">&#xe615;</i></button>
                    </div>
                  </div>
                
            </div>
            <xblock><button class="layui-btn" onclick="ticket_create('创建工单','./ticketCreatePage','600','500')"><i class="layui-icon">&#xe608;</i>创建工单</button><span class="x-right" style="line-height:40px">共有工单：1 条</span></xblock>
            <table class="layui-table">
                <thead>
                    <tr>
                        
                        <th>
                            TicketID
                        </th>
                        <th>
                            标题
                        </th>
                        <th>
                            服务
                        </th>
                        <th>
                            严重等级
                        </th>
                        <th>
                            状态
                        </th>
                        <th>
                            更新时间
                        </th>
                        <th>
                            提交人
                        </th>
                        <c:if test="${fn:length(authoritys) > 1}">  
							<td>最后操作人</td>
							<td>指派人</td>  
						</c:if> 
                    </tr>
                </thead>
                <tbody id="ticketList">
                    <c:if test="${tickets==null || fn:length(tickets) == 0}">  
					<tr>  
					  <td >查询结果为空</td>
					</tr>   
					</c:if>  
					<c:forEach items="${tickets}" var="item" varStatus="status">  
					  <tr >  
					    <td >${item.id}</td>  
					    <td style="cursor:pointer" onclick="question_show()">${item.title}</td>  
					    <td>${item.service}</td>  
					    <td>${item.severity}</td> 
					    <td>${item.status}</td>
					    <td>${item.updateDate}</td>
					    <td>${item.submitUser}</td>
					    <c:if test="${fn:length(authoritys) > 1}">  
							<td>${item.updateUser}</td>
							<td>${item.assignUser}</td>  
						</c:if> 	
					    
					  </tr>  
					</c:forEach>
                </tbody>
            </table>

            <div id="page"></div>
        </div>
        <script src="../static/layui/layui.js" charset="utf-8"></script>
        <script src="../static/js/x-layui.js" charset="utf-8"></script>
        <script>
            layui.use(['laydate','element','layer','form'], function(){
                $ = layui.jquery;//jquery
			var form = layui.form;
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层

              //以上模块根据需要引入
              /*laypage({
                cont: 'page'
                ,pages: 100
                ,first: 1
                ,last: 100
                ,prev: '<em><</em>'
                ,next: '<em>></em>'
              }); */
              
	
             

            });
            
            /*查询*/
            function ticket_sreach(obj,id){
            	
            	var ticketList = document.getElementById("ticketList");
            	
            	var n = ticketList.firstChild;
                while(n) {
                    var m = n.nextSibling;
                    ticketList.removeChild(n);
                    n = m;
                }
            	
            	
            	var findMethod = document.getElementById("findMethod").value;
            	var keyword = document.getElementById("keyword").value;
            	var serviceType = document.getElementById("serviceType").value;
            	var severity = document.getElementById("severity").value;
                var ticketStatus = document.getElementById("ticketStatus").value;
            	$.ajax({  
              	  url: '..'+findMethod, 
                  type: 'POST',  
                  dataType: 'json',
                  data: {
                  	"keyword": keyword, "serviceId": serviceType, "severity": severity, "status": ticketStatus
                  },
                  timeout: 1000,  
                  cache: false,     
               	}).done(function(data) { 

               		$.each(data, function(tickets){
               			$.each(tickets, function(ticket){
               				var $tr = $("<tr><")
               				var $td = $("<td>"+ticket.id+"</td>")
               				$tr.append($td);
               				$td = $('<td style="cursor:pointer" onclick="question_show()">'+ticket.title+'</td>');
               				$tr.append($td);
               				$td = $("<td>"+ticket.service+"</td>")
               				$tr.append($td);
               				$td = $("<td>"+ticket.severity+"</td>")
               				$tr.append($td);
               				$td = $("<td>"+ticket.status+"</td>")
               				$tr.append($td);
               				$td = $("<td>"+ticket.updateDate+"</td>")
               				$tr.append($td);
               				$td = $("<td>"+ticket.submitUser+"</td>")
               				$tr.append($td);
               				var finds = document.getElementsByName("findMethod[]");
               				if(finds.length>1){
               					$td = $("<td>"+ticket.updateUser+"</td>")
                   				$tr.append($td);
               					$td = $("<td>"+ticket.assignUser+"</td>")
                   				$tr.append($td);
               				}
               			})
               			
               		})
                  }); 
            }
            
       
            
            
            

            //批量删除提交
             function delAll () {
                layer.confirm('确认要删除吗？',function(index){
                    //捉到所有被选中的，发异步进行删除
                    layer.msg('删除成功', {icon: 1});
                });
             }

             function question_show (argument) {
                layer.msg('可以跳到前台具体问题页面',{icon:1,time:1000});
             }
             /*创建工单*/
            function ticket_create(title,url,w,h){
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
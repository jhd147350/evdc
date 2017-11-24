<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            工单报表
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
              <a><cite>工单报表
              </cite></a>
          
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            <div class="layui-form layui-form-pane">
                
                  <div class="layui-form-item">


					<!-- <div class="layui-input-inline">
                      <input type="text" id="keyword" name="keyword"  placeholder="ticketId" autocomplete="off" class="layui-input">
                    </div> -->
					
                    <div class="layui-inline">
                        <button id="reportSreachAdd" class="layui-btn" onclick="report_sreach_add('新建查询','./reportSearchAddPage','600','500')"><i class="layui-icon">&#xe608;</i></button>
                    </div>
                    <div class="layui-inline x-right">
                    	<button id="reportExport" class="layui-btn layui-btn-disabled" disalbed="true" onclick="">导出</button>
                    </div>
                  </div>
                
            </div>
            <!-- <xblock>
           </xblock> -->
            <table class="layui-table">
                <thead>
                    <tr>   
                        <th>
                           	ID
                        </th>
                        <th>
                      	报表名称     
                        </th>
                        <th>
                     	描述       
                        </th>
                        <th>
                               	操作
                        </th>
                    </tr> 
                </thead>
                <tbody id="reportSearchList">
                <c:forEach items="${reportSearchs}" var="item" varStatus="status">  
					  <tr >  
					    <td>${item.id}</td>  
					    <td>${item.name}</td>  
					    <td>${item.describe}</td>  
					    
								<td class="td-manage">
	                            
	                            <a title="执行" href="javascript:;" onclick='report_sreach(this, "${item.id}")' 
	                            style="text-decoration:none">
	                                <i class="layui-icon">&#xe615;</i>
	                            </a>
	                            <a title="编辑" href="javascript:;" onclick="report_sreach_edit('编辑报表查询','./reportSreachEditPage?id=${item.id}','4','','510')"
	                            class="ml-5" style="text-decoration:none">
	                                <i class="layui-icon">&#xe642;</i>
	                            </a>
	                            <a title="删除" href="javascript:;" onclick="report_sreach_delete('删除报表查询','./reportSreachDelete?id=${item.id}','4','','510')"
	                            class="ml-5" style="text-decoration:none">
	                                <i class="layui-icon">&#xe640;</i>
	                            </a>
                        		</td> 
					    
					  </tr>  
				</c:forEach> 
                </tbody>
            </table>
			<table id="ticketList" lay-filter="ticketListFilter"></table>
        </div>
        <script src="../static/layui/layui.js" charset="utf-8"></script>
        <script src="../static/js/x-layui.js" charset="utf-8"></script>
        <script>
            layui.use(['table','laydate','element','layer','form'], function(){
                $ = layui.jquery;//jquery
			var form = layui.form;
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
            });
            var searchId = "";
            /*查询*/
            function report_sreach(obj,id){
            	$=layui.jquery;
            	$("#reportExport").attr("disabled",false);
           		$("#reportExport").attr("class" , "layui-btn layui-btn-disabled");
            	obj.disabled=true;
            	searchId=id;
            	table = layui.table;
                table.render({
            	  	elem: '#ticketList'
            	    ,page: true //开启分页
            	    ,limits:[10,20,30,40,50,60]
                	,limit:10
            	    ,cols: [[ //表头
            	      {field: 'id', title: 'ticketId', width:100, sort: true, fixed: 'left'}
            	      ,{field: 'title', title: '标题', width:250}
            	      ,{field: 'source', title: '来源', width:100, sort: true}
            	      ,{field: 'serviceName', title: '服务', width:100, sort: true}
            	      ,{field: 'severity', title: '严重等级', width:100} 
            	      ,{field: 'status', title: '状态', width: 100}
            	      ,{field: 'customerUserName', title: '客户', width: 100, sort: true}
            	      ,{field: 'resolveDate', title: '解决时间', width: 100, sort: true}
            	      ,{field: 'satisfation', title: '评分', width: 100}
            	      ,{field: 'updateDate', title: '更新时间', width: 100, sort: true}
            	      ,{field: 'submitUserName', title: '提交人', width: 100, sort: true}
            	      ,{field: 'submitTeamName', title: '提交组', width: 100, sort: true}
            	      ,{field: 'updateUserName', title: '最后操作人', width: 100}
            	      ,{field: 'assignUserName', title: '指派人', width: 100, sort: true}
            	      ,{field: 'assignTeamName', title: '指派组', width: 100, sort: true}
            	      ,{field: 'submitDate', title: '提交时间', width: 100, sort: true}
            	    ]]
              		,url: './ticketReport'
            	  	,where: {"searchId": id} //如果无需传递额外参数，可不加该参数
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
                   		$("#reportExport").attr("disabled",false);
                   		$("#reportExport").attr("class" , "layui-btn");
                   		$("#reportExport").attr("onclick" , "location.href='./exportTicketReport?searchId="+searchId+"'");
            	  	  }
            	});
            }
            /*添加新的查询*/
            function report_sreach_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }
            /*修改查询*/
            function report_sreach_edit(title,url,w,h){
                x_admin_show(title,url,w,h);
            }
            /*删除查询*/
            function report_sreach_delete(title,url,w,h){
                x_admin_show(title,url,w,h);
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
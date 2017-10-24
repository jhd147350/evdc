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


					<div class="layui-input-inline">
                      <input type="text" id="keyword" name="keyword"  placeholder="ticketId" autocomplete="off" class="layui-input">
                    </div>
					
                    <div class="layui-inline">
                        <button id="ticketSreachBut" class="layui-btn" onclick="ticket_sreach(this,'1')"><i class="layui-icon">&#xe615;</i></button>
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
                            TicketID
                        </th>
                        <th>
                           操作
                        </th>
                        <th>
                            值
                        </th>
                        <th>
                            操作人
                        </th>
                        <th>
                            时间
                        </th>
                    </tr>
                </thead>
                <tbody id="ticketList"> 
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
            });
            var exportTicketId = "";
            /*查询*/
            function ticket_sreach(obj,id){
            	obj.disabled=true;
            	obj.setAttribute("class" , "layui-btn layui-btn-disabled");
            	var ticketList = document.getElementById("ticketList");
            	
            	var n = ticketList.firstChild;
                while(n) {
                    var m = n.nextSibling;
                    ticketList.removeChild(n);
                    n = m;
                }
            	var ticketId = document.getElementById("keyword").value;
            	$.ajax({  
              	  url: './ticketReport', 
                  type: 'POST',  
                  dataType: 'json',
                  data: {
                  	"ticketId": ticketId
                  },
                  timeout: 10000,  
                  cache: false,     
               	}).done(function(data) {
               		$.each(data, function(ticketChangeRecordIndex){
               			var ticketChangeRecord = data[ticketChangeRecordIndex];
               				exportTicketId = ticketChangeRecord.ticketId;
               				var $tr = $("<tr></tr>")
               				var $td = $("<td>"+ticketChangeRecord.ticketId+"</td>")
               				$tr.append($td);
               				$td = $('<td>'+ticketChangeRecord.filed+'</td>');
               				$tr.append($td);
               				$td = $("<td>"+ticketChangeRecord.newValue+"</td>")
               				$tr.append($td);
               				$td = $("<td>"+ticketChangeRecord.name+"</td>")
               				$tr.append($td);
               				var timestamp = ticketChangeRecord.timestamp;
               				var newDate = new Date();
               				newDate.setTime(timestamp);
               				$td = $("<td>"+newDate.toLocaleString()+"</td>")
               				$tr.append($td);
               				$(ticketList).append($tr);
               		})
               		obj.disabled=false;
               		obj.setAttribute("class" , "layui-btn");
               		$("#reportExport").attr("disabled",false);
               		$("#reportExport").attr("class" , "layui-btn");
               		$("#reportExport").attr("onclick" , "location.href='./exportTicketReport?ticketId="+exportTicketId+"'");
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
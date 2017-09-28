<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            创建工单
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
        <div class="x-body">
			<form class="layui-form" action="">
				<div class="layui-form-item">
			 				<select id="nonSubscribeList" lay-filter="nonSubscribeTeam" name="nonSubscribeTeam">	
                                    <%-- <c:forEach items="${nonSubscribeTeam}" var="item" varStatus="status">  
										<option name="nonSubscribeTeam[]" value="${item.id}" > ${item.name}</option>
									</c:forEach> --%>
                            </select>				     	 
				</div>    	
				<div class="layui-form-item">
					<%-- <textarea id="subscribeList" name="" class="layui-textarea" disabled="true">${ticket.description}</textarea> --%>
					<table class="layui-table">
						<tbody id="subscribeList" >
						
						</tbody>
					</table>
				</div>
				<div class="layui-form-item"> 
                    <input type="submit"  id="saveChange" class="layui-btn layui-btn-disabled" lay-filter="save" lay-submit="" disabled="true" value="保存">
                </div>
			</form>
			</div>
			<script src="../static/layui/layui.js" charset="utf-8"></script>
	        <script src="../static/js/x-layui.js" charset="utf-8"></script> 
	        <script>
	        layui.use(['form','layer'], function(){
                layui$ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;
              //已订阅组织
              var subscribeTeam = new Map();
              //未订阅组织
              var nonSubscribeTeam = new Map();
			  //增加组Id数组
			  var addSubscribeTeamId = new Array();
			  //减少组Id数组
			  var reduceSubscribeTeamId = new Array();
              /* 初始化subscribeTeam 并在 subscribeList 表中添加已订阅组织信息*/
              <c:forEach items="${subscribes}" var="item" varStatus="status">
              	subscribeTeam["${item.id}"] = "${item.name}";
              	var $tr = $("<tr></tr>");
              	$tr.append($('<td  colspan="9">${item.name}</td>'));
              	$del = $('<td><a title="删除" href="javascript:;" onclick="subs_del(this)" style="text-decoration:none"> <i class="layui-icon">&#xe640;</i></a></td>');
            	$tr.append($del);
				$('#subscribeList').append($tr);	
			  </c:forEach>
			  /* 初始化 nonSubscribeTeam */
			  <c:forEach items="${nonSubscribeTeam}" var="item" varStatus="status">
              	subscribeTeam["${item.id}"] = "${item.name}";	
			  </c:forEach>  
			  /* select获取焦点时添加option */
			  
			  
			  /* 新增订阅 */
			  form.on('select', function (data) {
		      	  	$('#saveChange').attr("class", "layui-btn");
		      	  	$('#saveChange').removeAttr("disabled");	
              });
	        });
	        /*删除订阅组*/
            function subs_del(obj){
                x_admin_show(title,url,w,h);
                
            }
	        </script>
		</div> 
</body>
</html>
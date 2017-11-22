<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            工单订阅
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
			<form class="layui-form layui-form-pane">
				<div class="layui-form-item">
			 				<select id="nonSubscribeList" lay-filter="nonSubscribeTeam" name="nonSubscribeTeam">	
                                    <c:forEach items="${nonSubscribeTeams}" var="item" varStatus="status">  
										<option name="nonSubscribeTeam[]" value="${item.id}" > ${item.name}</option>
									</c:forEach>
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
                    <!-- <input type="submit"  id="saveSubscribe" class="layui-btn layui-btn-disabled" lay-filter="saveSubscribe" lay-submit="" disabled="true" value="保存"> -->
                </div>
			</form>
			<input type="submit"  id="saveSubscribe" class="layui-btn layui-btn-disabled" lay-filter="saveSubscribe" lay-submit="" disabled="true" value="保存">
			</div>
			<script src="../static/layui/layui.js" charset="utf-8"></script>
	        <script src="../static/js/x-layui.js" charset="utf-8"></script> 
	        <script>
	      		//已订阅组织
            	var subscribeTeam = new Map();
           		//未订阅组织
            	var nonSubscribeTeam = new Map();
			  	//增加组Id数组
			  	var addSubscribeTeamId = new Map();
			  	//减少组Id数组
			 	var reduceSubscribeTeamId = new Map();
			 	
	        layui.use(['form','layer'], function(){
              
	        	$ = layui.jquery,
				form = layui.form,
              	layer = layui.layer;
              /* 保存提交增加组与减少组 */
              form.on('submit(saveSubscribe)', function(data){
            	  var addArray = new Array();
            	  var reduceArray = new Array();
            	  var test = new Array();
            	  var i = 1;
            	  var j = 1;
            	  addArray[0] = 1;
            	  reduceArray[0] = 1;
            	  for(var key in addSubscribeTeamId){
					  addArray[i] = addSubscribeTeamId[key];
					  console.log(addSubscribeTeamId[key]);
					  console.log(addArray[i]);
					  i++;
				  }
            	  for(var key in reduceSubscribeTeamId){
            		  reduceArray[j] = reduceSubscribeTeamId[key];
            		  j++;
				  }
            	  $.ajax({  
                    	url: './ticketSubcribeTeamChange', 
                        type: 'POST',  
                        dataType: 'json',
                        ContentType: 'application/json',
                        data: {
                        	"test": null,"addArray[]": addArray, "reduceArray[]": reduceArray, "ticketId": "${ticketId}"
                        },
                        timeout: 1000,  
                        cache: false,     
                 		}).done(function(data) { 
                 			
                 		if(data.status==0){
          					layer.alert("修改成功", {icon: 6},function (index) {
          						var index = parent.layer.getFrameIndex(window.name);
          	                    //关闭当前frame
          	                    parent.layer.close(index);
                         });
          				}else{
          					layer.alert("修改失败", {icon: 5},function (index) { 
          						layer.close(index);
          	                });
          				}	
                 });
                 console.log("请求");
              });
              /* 初始化subscribeTeam 并在 subscribeList 表中添加已订阅组织信息*/   
              <c:forEach items="${subscribeTeams}" var="item" varStatus="status">
              	subscribeTeam["${item.id}"] = "${item.name}";
              	var $tr = $("<tr></tr>");
              	$tr.append($('<td  colspan="9">${item.name}</td>'));
              	$del = $('<td><a title="删除" teamId="${item.id}" teamName="${item.name}" href="javascript:;" onclick="subs_del(this)" style="text-decoration:none"> <i class="layui-icon">&#xe640;</i></a></td>');
            	$tr.append($del);
				$('#subscribeList').append($tr);	
			  </c:forEach>
			  /* 初始化 nonSubscribeTeam */ 
			  <c:forEach items="${nonSubscribeTeams}" var="item" varStatus="status">
			  	nonSubscribeTeam["${item.id}"] = "${item.name}"; 	
			  </c:forEach>		  
			  /* 新增订阅组 */
			  form.on('select', function (data) {
				  	var teamId = $('#nonSubscribeList').find('option:selected').attr('value');
				  	console.log("teamid "+teamId);
				  	var teamName = $('#nonSubscribeList').find('option:selected').html();
				  	//如果不在原始订阅组则加入到add数组，如果在原始数组，则删除减少列表 
		      	  	if($.isEmptyObject(subscribeTeam[teamId])){
		      	  		addSubscribeTeamId[teamId] = teamId;
		      	  	}else{
		      	  		delete reduceSubscribeTeamId[teamId];
		      	  	}
		      	  	//删除非订阅组添加指定对象
		      	  	console.log(addSubscribeTeamId[teamId]);
		      	  	delete nonSubscribeTeam[teamId];
		      	  	console.log(addSubscribeTeamId);
		      	  	
		      	  	
		      	  	var $tr = $("<tr></tr>");
	              	$tr.append($('<td  colspan="9">'+teamName+'</td>'));
	              	$del = $('<td><a title="删除" teamId="'+teamId+'" teamName="'+teamName+'" href="javascript:;" onclick="subs_del(this)" style="text-decoration:none"> <i class="layui-icon">&#xe640;</i></a></td>');
	            	$tr.append($del);
					$('#subscribeList').append($tr);
					
				  	$('#saveSubscribe').attr("class", "layui-btn");
		      	  	$('#saveSubscribe').removeAttr("disabled");	
		      	  	
		      		//更新非订阅组select
		      	  	nonSub_update();
		      	  	
              });
	        });
	        /* 删除订阅组 */
            function subs_del(obj){
                var teamId = $(obj).attr("teamId");
                var teamName = $(obj).attr("teamName");
                /* 如果不在原始数组则删除增加数组的对象，如果在原始数组，则在减少数组加入对象 */
                if($.isEmptyObject(subscribeTeam[teamId])){
                	delete addSubscribeTeamId[teamId];
                }else{
                	reduceSubscribeTeamId[teamId] = teamId;;
                }
                /* 添加非订阅组指定对象 */
                nonSubscribeTeam[teamId] = teamName;
                /* 更新非订阅组select */
                nonSub_update();
                obj.parentNode.parentNode.remove();
                $('#saveSubscribe').attr("class", "layui-btn");
	      	  	$('#saveSubscribe').removeAttr("disabled");	
            }
	        /* 更新非订阅组下拉列表 */
	        function nonSub_update(){
	        	form = layui.form;
	        	$('#nonSubscribeList').find("option").remove();
	        	for(var key in nonSubscribeTeam){
					  var $option = $('<option name="nonSubscribeTeam[]" value="'+key+'" > '+nonSubscribeTeam[key]+'</option>');
					  $('#nonSubscribeList').append($option);
				}
	        	form.render('select');
            }
	        </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            
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
				<div class="layui-form-item layui-form-text"> 
            		<div class="layui-inline">
	                <label class="layui-form-label">
	                        	信息
	                    </label>
	                    </div>
	                  
             <div class="layui-input-block">      
             <div>
                        <textarea id="L_content" name="comment" 
                        placeholder="请输入内容" class="layui-textarea fly-editor" style="height: 260px;"></textarea>
              </div>      
                    </div>
                </div>
                
			</form>
			<div class="layui-form-item"> 
            		<div class="layui-inline">
            			<button id="changeTicketStatus" class="layui-btn" lay-filter="changeTicketStatus" lay-submit="" >保存</button>
            		</div>
            	</div>
			</div>
			<script src="../static/layui/layui.js" charset="utf-8"></script>
	        <script src="../static/js/x-layui.js" charset="utf-8"></script> 
	        <script>
	      		
	        layui.use(['form','layer','layedit'], function(){
              
	        	$ = layui.jquery,
				form = layui.form,
              	layer = layui.layer
	        	,layedit = layui.layedit;
	        	 
	            //创建一个编辑器
	            editIndex = layedit.build('L_content',{
	            	tool: [
					  'strong' //加粗
					  ,'italic' //斜体
					  ,'underline' //下划线
					  ,'del' //删除线
					  
					  ,'|' //分割线
					  
					  ,'left' //左对齐
					  ,'center' //居中对齐
					  ,'right' //右对齐
					  ,'face' //表情
					  
					]
	            });
	            form.on('submit(changeTicketStatus)', function(data){
	            	console.log("提交");
	          
	            	$.ajax({  
                    	url: './changeTicketStatus', 
                        type: 'POST',  
                        dataType: 'json',
                        ContentType: 'application/json',
                        data: {
                        	"ticketId": "${ticketId}" , "setStatus": "${setStatus}" , "comment": layedit.getContent(editIndex)
                        },
                        timeout: 1000,  
                        cache: false,     
                 		}).done(function(data) { 
                 			
                 		if(data.status==0){
          					layer.alert("修改成功", {icon: 6},function (index) {
          						var index = parent.layer.getFrameIndex(window.name);
          	                    //关闭当前frame
          	                    parent.location.reload();
                         });
          				}else{
          					layer.alert("修改失败", {icon: 5},function (index) { 
          						layer.close(index);
          	                });
          				}	
                 });
	            });
	        });
	       
	        </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
           编辑查询
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
                    <label for="L_title" class="layui-form-label">
                        名称
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="title" lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                <label for="L_content" class="layui-form-label">
                        SQL
                    </label>
                    <div class="layui-input-block">
                        <textarea id="L_SQL" name="sql" 
                        placeholder="请输入内容" class="layui-textarea fly-editor" style="height: 260px;"></textarea>
                    </div>
                    
                </div>
                <div class="layui-form-item layui-form-text">
                <label for="L_content" class="layui-form-label" style="top: -2px;">
                        描述
                    </label>
                    <div class="layui-input-block">
                        <textarea id="L_content" name="descrbe" 
                        placeholder="请输入内容" class="layui-textarea fly-editor" style="height: 260px;"></textarea>
                    </div>
                    
                </div>
                <div class="layui-form-item"> 
                    <input type="submit" class="layui-btn" lay-filter="add" lay-submit="" value="提交">
                </div> 
           </form>     
	</div>  
	<script src="../static/layui/layui.js" charset="utf-8"></script>
    <script src="../static/js/x-layui.js" charset="utf-8"></script>
    <script>
		layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;

            //监听提交
              form.on('submit(add)', function(data){
                 $.ajax({  
           	url: './reportTicketSearchAdd', 
               type: 'POST',  
               dataType: 'json',
               data: {
               	"title": data.field.title, "describe": data.field.describe, "sql": data.field.sql
               },
               timeout: 1000,  
               cache: false,     
        		}).done(function(data) { 
        			
        		if(data.status==0){
 					layer.alert("提交成功", {icon: 6},function (index) {
 						/* var index = parent.layer.getFrameIndex(window.name);
 	                    //关闭当前frame
 	                    parent.layer.close(index); */
 	                   	var a_refresh = parent.document.getElementById('refresh');
	               		a_refresh.click();
                });
 				}else{
 					layer.alert("提交失败", {icon: 5},function (index) { 
 						layer.close(index);
 	                });
 				}	
           	 });
                 return false;
           });
         });
	 </script>
</body>
</html>
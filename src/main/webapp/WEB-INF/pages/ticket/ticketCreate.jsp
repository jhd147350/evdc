<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            二当家的Lay1.0
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
                        标题
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="title" required lay-verify="title"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                <div class="layui-form-item layui-form-text">
                    <div class="layui-input-block">
                        <textarea id="L_content" name="content" 
                        placeholder="请输入内容" class="layui-textarea fly-editor" style="height: 260px;"></textarea>
                    </div>
                    <label for="L_content" class="layui-form-label" style="top: -2px;">
                        描述
                    </label>
                </div>
               
               
               <div class="layui-upload">
						  <button type="button" class="layui-btn layui-btn-normal" id="addFileList">选择多文件</button> 
						  <div class="layui-upload-list">
						    <table class="layui-table">
						      <thead>
						        <th>文件名</th>
						        <th>大小</th>
						        <th>状态</th>
						        <th>操作</th>
						      </thead>
						      <tbody id="demoList"></tbody>
						    </table>
						  </div>
						 
					</div> 
               
            
                <div class="layui-form-item">
                    <button class="layui-btn" id="createTicketBut" lay-filter="add" lay-submit>
                        提交
                    </button>
                </div>
                <!-- 文件上传 -->
                
            </form>
            
                      
               
         </div>                   	     	
        
        <script src="../static/layui/layui.js" charset="utf-8"></script>
        <script src="../static/js/x-layui.js" charset="utf-8"></script>
        
        
        <script>
        	
            layui.use(['form','layer','layedit','upload'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,layedit = layui.layedit
              ,upload = layui.upload;

                /*layedit.set({
                  uploadImage: {
                    url: "./upimg.json" //接口url
                    ,type: 'post' //默认post
                  }
                })*/
  
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
            
              

              //监听提交
              form.on('submit(add)', function(data){
                console.log(data);
                //发异步，把数据提交给php
                layer.alert("增加成功", {icon: 6},function () {
                    // 获得frame索引
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭当前frame
                    parent.layer.close(index);
                });
                return false;
              });
              
              
              //upload 为对象o
              
              var demoListView = $('#demoList')
		  ,uploadListIns = upload.render({
		    elem: '#addFileList'
		    ,url: './uploadTicketFile'
		    ,accept: 'file'
		    ,multiple: true
		    ,auto: false
		    ,bindAction: '#uploadList'
		    ,choose: function(obj){ 
		    	
		      //读取本地文件
		      
		      obj.preview(function(index, file, result){
		        var tr = $(['<tr id="upload-'+ index +'">'
		          ,'<td id="">'+ file.name +'</td>'
		          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
		          ,'<td id="uploadStatus">正在上传</td>'
		          ,'<td>'
		          
		            ,'<button  serFileName="" class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
		          ,'</td>'
		        ,'</tr>'].join(''));
		    //添加完文件后直接上传 
			obj.upload(index, file);      
        /*//单个重传
        tr.find('.demo-reload').on('click', function(){
          obj.upload(index, file);
        });
        */
       
        //删除
        tr.find('.demo-delete').on('click', function(){
          //var a =files[index];
          /*files.splice(index,1);*/
          var but = this;
          var deleteFileName = but.getAttribute("serFileName");
		  $.ajax({  
          	url: './deleteTicketFile', 
              type: 'POST',  
              dataType: 'json',
              data: {
              	"serFileName": deleteFileName
              },
              timeout: 1000,  
              cache: false,     
       		}).done(function(data) { 
       		if(data.status==0){
       			
					layer.alert("删除成功", {closeBtn: 1},function (index) {
						console.log(but);
						but.parentNode.parentNode.remove();
						layer.close(index);
               });
				}else{
					layer.alert("删除失败", {icon: 5},function () { 
	                });
				}	
          }); 		  
        });
        
        demoListView.append(tr);
      });
    }
    ,done: function(res, index, upload){
      if(res.status == 0){ //上传成功
        var tr = demoListView.find('tr#upload-'+ index)
        ,tds = tr.children();
        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
        var button = tds.eq(3).children().eq(0);
        button[0].setAttribute("serFileName",res.ticketFilePath);
      	return;
      }
      this.error(index, upload);
    }
    ,error: function(index, upload){
      var tr = demoListView.find('tr#upload-'+ index)
      ,tds = tr.children();
      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
    }
  });   
            });
            
            
       
            
            
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
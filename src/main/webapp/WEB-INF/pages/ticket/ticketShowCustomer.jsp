<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            工单详情
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
            <form action="./createTicket" class="layui-form layui-form-pane" method="POST">
                <div class="layui-form-item">
                    <label ticketId="${ticket.id}" for="L_title" class="layui-form-label">
                        标题
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name=""
                        autocomplete="off" class="layui-input" disabled="disabled" value="${ticket.title}">
                    </div>
                </div>
                
                <div class="layui-form-item layui-form-text">
	                <label for="L_content" class="layui-form-label" style="top: -2px;">
	                        描述
	                </label>
                    <div class="layui-input-block">
                        <textarea name="" class="layui-textarea" disabled="disabled">${ticket.description}</textarea>
                    </div>     
               </div>
		  		<!-- <div class="layui-upload">
						  <input type="button" class="layui-btn layui-btn-normal" id="addFileList" value="选择多文件"/> 
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
						 
				</div>  -->
               <div class="layui-form-item">
               <div class="layui-inline">
		                <label class="layui-form-label">
		                            状态
		                </label>        
	                    <div class="layui-input-block">
		                    <select id="ticketStatus" lay-verify="required" name="cid" value="${ticket.status}" disabled="true">
			                    <option value="New">新建</option>
			                    <option value="In_Process">已受理</option>
			                    <option value="Resolved">已解决</option>
			                    <option value="Closed">已关闭</option>                                
		                    </select>        
	                    </div>
	           </div>
               	<div class="layui-inline">
	               <label class="layui-form-label">
	                            服务类型
	               </label>         
                   <div class="layui-input-block">
                            <select id="serviceType" lay-filter="serviceType" name="serviceType" value="${ticket.serviceId}" disabled="true">	
                                    <c:forEach items="${ticketServices}" var="item" varStatus="status">  
										<option name="ticketService[]" value="${item.id}" > ${item.name}</option>
									</c:forEach>
                            </select>
                    </div>
                    
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">
                            严重等级
                        </label>
                   
                        <div class="layui-input-block">
                            <select id="severity" lay-filter="severity" name="severity" value="${ticket.severity}" disabled="true">
                                    <option value="Sev1">1-严重</option>
                                    <option value="Sev2">2-高级</option>
                                    <option value="Sev3">3-一般</option>
                                    <option value="Sev4">4-最低</option>
                            </select>
                        </div>
                    </div>
            	</div>
                <!-- <div class="layui-form-item"> 
                    <input type="submit"  id="saveChange" class="layui-btn layui-btn-disabled" lay-filter="save" lay-submit="" disabled="true" value="保存">
                </div> -->
                
            </form>
            <div class="layui-inline">
            	<button id="changeTicketStatus" class="layui-btn" lay-filter="changeTicketStatus" lay-submit="">状态更改</button>
            </div>
            <!-- <div class="layui-inline">
            	<button id="subscribeTicket" class="layui-btn" lay-filter="subscribeTicket" onclick="ticket_subscribe(this)" lay-submit="">订阅</button>          
            </div> -->
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
            	//实际上传文件数
              	var buttonNum = 0;
                /*layedit.set({
                  uploadImage: {
                    url: "./upimg.json" //接口url
                    ,type: 'post' //默认post
                  }
                })*/
              
                form.on('select', function (data) {
  		      	  	$('#saveChange').attr("class", "layui-btn");
  		      	  	$('#saveChange').removeAttr("disabled");	
                });
            
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
                //发异步，把数据提交给php
                console.log("data is "+data.field.serviceType);
                var fileNameArray = new Array();
                var serFileNameArray = new Array();
                var fileNameInps = document.getElementsByName("fileNameArr");
                var serFileNameInps = document.getElementsByName("serFileNameArr");
                for(var i = 0; i < buttonNum; i++){
                	fileNameArray[i] = fileNameInps[i].getAttribute("value");
                	serFileNameArray[i] = serFileNameInps[i].getAttribute("value");
                }
                fileNameArray[buttonNum]='填充';
                serFileNameArray[buttonNum]='填充';
                $.ajax({  
          	url: './createTicket', 
              type: 'POST',  
              dataType: 'json',
              data: {
              	"title": data.field.title, "description": layedit.getContent(editIndex), "serviceType": data.field.serviceType, "severity": data.field.severity, "fileName[]": fileNameArray, "serFileName[]": serFileNameArray 
              },
              timeout: 1000,  
              cache: false,     
       		}).done(function(data) { 
       			
       		if(data.status==0){
					layer.alert("提交成功", {icon: 6},function (index) {
						var index = parent.layer.getFrameIndex(window.name);
	                    //关闭当前frame
	                    parent.layer.close(index);
               });
				}else{
					layer.alert("提交失败", {icon: 5},function (index) { 
						layer.close(index);
	                });
				}	
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
		          ,'<div>'
		            ,'<button  serFileName="" class="layui-btn layui-btn-mini layui-btn-danger demo-delete" >删除</button>'
		          ,'</div>'
		            ,'</td>'
		        ,'</tr>'].join(''));
		    //添加完文件后直接上传 
			obj.upload(index, file);          
        //删除
        tr.find('.demo-delete').on('click', function(){
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
					layer.alert("删除成功", {icon: 6},function (index) {
						buttonNum--;
						console.log(but);
						but.parentNode.parentNode.parentNode.remove();
						layer.close(index);
               });
				}else{
					layer.alert("删除失败", {icon: 5},function (index) { 
						layer.close(index);
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
        var buttonDiv = tds.eq(3).children().eq(0);
        buttonNum++;
        var button = buttonDiv.children().eq(0);
        button[0].setAttribute("serFileName",res.ticketFilePath);
        buttonDiv.prepend($("<input type='text' name='fileNameArr' value='"+res.fileName+"' hidden/>"));
        buttonDiv.prepend($("<input type='text' name='serFileNameArr' value='"+res.ticketFilePath+"' hidden/>"));
      	return;
      }
      this.error(index, upload);
    }
    ,error: function(index, upload){
      var tr = demoListView.find('tr#upload-'+ index)
      ,tds = tr.children();
      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
      tds.eq(3).find('.demo-reload').removeClass('layui-hide');
    }
  });   
            });
          //订阅
            function ticket_subscribe (argument) {

           	 console.log(argument);
           	 x_admin_show('工单订阅','./ticketSubcribePage?ticketId=${ticket.id}','500','400');
            };
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
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
 
                                    <option value="Sev1">1-严重</option>
                                    <option value="Sev2">2-高级</option>
                                    <option value="Sev3">3-一般</option>
                                    <option value="Sev4">4-最低</option>

                            </select>
                        </div>
                    </div>
                 </div>
                
                 <!-- <div class="layui-form-item">
	                <div class="layui-inline">
	                        <button class="layui-btn" onclick="ticket_sreach(this,'1')"><i class="layui-icon">&#xe615;</i></button>
	                </div>
                 </div> -->
                
                
                
                
            
                <div class="layui-form-item">
                    <button class="layui-btn" lay-filter="create" lay-submit>
                        创建
                    </button>
                </div>
            </form>
        </div>
        <script src="../static/layui/layui.js" charset="utf-8">
        </script>
        <script src="../static/js/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer','layedit'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,layedit = layui.layedit;


                layedit.set({
                  uploadImage: {
                    url: "./upimg.json" //接口url
                    ,type: 'post' //默认post
                  }
                })
  
            //创建一个编辑器
            editIndex = layedit.build('L_content');
            
              

              //监听提交
              form.on('submit(create)', function(data){
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            人员添加
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
            <form class="layui-form">
                <div class="layui-form-item">
                    <label for="teamUserName" class="layui-form-label">
                        <span class="x-red">*</span>人员姓名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="teamUserName" name="teamUserName" required="" lay-verify="required"
                        autocomplete="off" class="layui-input" teamId="${userTeamId }">
                    </div>
                    <!-- <div class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span>将会成为您唯一的登入名
                    </div> -->
                </div>
                <div class="layui-form-item">
                    <label for="role" class="layui-form-label">
                        <span class="x-red">*</span>人员角色
                    </label>
                    <div class="layui-input-inline">
                      <select id="userRole_select">
                        <!--<option value="">请选择角色</option>-->
                        
                        <c:forEach items="${userRoles}" var="item" varStatus="status">  
							<option name="userRole[]" value="${item.id }" roleValue="${item.id }"> ${item.roleName }</option>
						</c:forEach>
                      </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="phone" class="layui-form-label">
                        <span class="x-red">*</span>手机
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="phone" name="phone" required="" lay-verify="phone"
                        autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label">
                        <span class="x-red">*</span>邮箱
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="userEmail" name="userEmail" required="" lay-verify="email"
                        autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_pass" class="layui-form-label">
                        <span class="x-red">*</span>密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="password" id="userPassword" name="pass" required="" lay-verify="pass"
                        autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">
                        6到16个字符
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                        <span class="x-red">*</span>确认密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="password" id="L_repass" name="repass" required="" lay-verify="repass"
                        autocomplete="off" class="layui-input">
                 	</div>
				</div>
                <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label">
                        <span class="x-red">*</span>登录名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="loginId" name="loginId" required="" lay-verify="email"
                        autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span>
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <input type="button" name="add" id="addUser" value="增加" class="layui-btn"/>
                </div>
            </form>
        </div>
        <script src="../static/layui/layui.js" charset="utf-8">
        </script>
        <script src="../static/js/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;
            
              //自定义验证规则
              form.verify({
                /* nikename: function(value){
                  if(value.length < 5){
                    return '昵称至少得5个字符啊';
                  }
                }
                , */pass: [/(.+){6,12}$/, '密码必须6到12位']
                ,repass: function(value){
                    if($('#userPassword').val()!=$('#L_repass').val()){
                        return '两次密码不一致';
                    }
                }
              }); 

              //监听提交
              var but = document.getElementById("addUser");
    			but.addEventListener("click", function(){
            		 //发异步，把数据提交给php
            		var teamUserName = document.getElementById("teamUserName");
            		var teamId = teamUserName.getAttribute("teamId");
                	var userRole = document.getElementById("userRole_select").value;
                	var phone = document.getElementById("phone");
                	var userEmail = document.getElementById("userEmail");
                	var userPassword = document.getElementById("userPassword");
                	var loginId = document.getElementById("loginId");
                	/*  if (navigator.userAgent.indexOf('Firefox') >= 0){
                		evdc_sync = false;	 
                	 } */
                	
                	$.ajax({  
                    	url: './addUser', 
                        type: 'POST',  
                        dataType: 'json',
                        data: {
                        	"name": teamUserName.value, "role": userRole, "teamId": teamId, "phone": phone.value, "email": userEmail.value, "password": userPassword.value, "loginId": loginId.value
                        },
                        timeout: 1000,  
                        cache: false,     
                 	}).done(function(data) { 
                 		if(data.status==1){
                 			
    						layer.alert("添加成功", {icon: 6},function () {
     	                    // 获得frame索引
     	                    //var index = parent.layer.getFrameIndex(window.name);
     	                    //关闭当前frame同时父页面刷新
     	                    var a_refresh = parent.document.getElementById('refresh');
     	               		a_refresh.click();
         	               	/* var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index); */
     	                });
    					}else if(data.status==2){
							layer.alert("登录名已被使用", {icon: 5});
    					}else{
    						layer.alert("添加失败", {icon: 5});
    					}	
                    }); 
                  
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
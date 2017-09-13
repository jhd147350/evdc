<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
           人员角色编辑
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
            <form action="" method="post" class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <label for="name" class="layui-form-label">
                        <span class="x-red">*</span>角色名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="roleName" userRoleId="${userRole.id }" name="name" required="" lay-verify="required"
                        autocomplete="off" class="layui-input" value="${userRole.roleName}">
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">
                        拥有权限
                    </label>
                    <table  class="layui-table layui-input-block">
                        <tbody>
                            <tr>
                                <td>
                                    <div class="layui-input-block">
                                        
					                    <c:if test="${authoritys==null || fn:length(authoritys) == 0}">  
										<tr>  
										  <td colspan="4">无可用权限</td>
										</tr>   
										</c:if> 
						
										<c:forEach items="${authoritys}" var="item" varStatus="status">  
											
											<c:choose>
												<c:when test="${userRole.authValue%item.authValue==0}">
													<input name="id[]" type="checkbox" value="2" authValue="${item.authValue }" checked="checked"> ${item.authName }	
												</c:when>
												<c:otherwise>
													<input name="id[]" type="checkbox" value="2" authValue="${item.authValue }"> ${item.authName }	
												</c:otherwise>	
											</c:choose>		
										  <c:if test="${status.count%5==0}">
										  	<br/>
										  </c:if>
										</c:forEach>
                                    </div>
                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label for="desc" class="layui-form-label">
                        描述
                    </label>
                    <div class="layui-input-block">
                        <textarea id="desc" name="desc" class="layui-textarea">${userRole.describe }</textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                <!-- <button id="addTeamRole" class="layui-btn" lay-submit="" lay-filter="add">增加</button> -->
                <input type="button" name="edit" id="editUserRole" value="修改" class="layui-btn"/>
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
            	
            	
            	//监听提交
    			var but = document.getElementById("editUserRole");
    			but.addEventListener("click", function(){
            		 //发异步，把数据提交给php
                	var inputs = document.getElementsByName("id[]");
                	var authValue = 1;
                	for(var i = 0; i < inputs.length; i++){
                		if(inputs[i].checked){
                			authValue = authValue*(inputs[i].getAttribute("authValue"));
                		}
                	}
                	var roleName = document.getElementById("roleName");
                	var describe = document.getElementById("desc");
                	/*  if (navigator.userAgent.indexOf('Firefox') >= 0){
                		evdc_sync = false;	 
                	 } */
                	var userRoleId = roleName.getAttribute("userRoleId");
                	$.ajax({  
                    	url: './updateUserRole', 
                        type: 'POST',  
                        dataType: 'json',
                        data: {
                        	id: userRoleId, "roleName": roleName.value, "authValue": authValue, "describe": describe.value
                        },
                        timeout: 1000,  
                        cache: false,     
                 	}).done(function(data) { 
                 		if(data.status==1){
                 			
    						layer.alert("编辑成功", {icon: 6},function () {
     	                    // 获得frame索引
     	                    //var index = parent.layer.getFrameIndex(window.name);
     	                    //关闭当前frame同时父页面刷新
     	                    var a_refresh = parent.document.getElementById('refresh');
     	               		a_refresh.click();
         	               	/* var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index); */
     	                });
    					}else{
    						layer.alert("编辑失败", {icon: 5},function () {
         	                    
         	                });
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
</body>
</html>
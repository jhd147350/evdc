<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            组织管理
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
              
              <a><cite>系统管理</cite></a>
              <a><cite>组织管理</cite></a>
            </span>
            <a id="refresh" class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            
            <xblock><!--<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除</button>-->
            	<button class="layui-btn" onclick="role_add('添加组织','./teamAddPage','900','500')"><i class="layui-icon">&#xe608;</i>添加</button><span class="x-right" style="line-height:40px">共有组织：${fn:length(teams)}条</span></xblock>
            <table class="layui-table">
                <thead>
                
                    <tr>                    
                        <th>
                            ID
                        </th>
                        <th>
                            组织名
                        </th>
                        <th>
                           组织角色
                        </th>
                        <th>
                            公司
                        </th>
                        <th>
                            操作
                        </th>
                    </tr>
                </thead>
                <tbody>
                
                <c:if test="${teams==null || fn:length(teams) == 0}">  
					<tr>  
					  <td colspan="4">组织为空</td>
					</tr>   
				</c:if>  
					<c:forEach items="${teams}" var="item" varStatus="status">  
					  <tr >  
					    <td >${item.id}</td>  
					    <td>${item.name}</td>  
					    <td>${item.role}</td>  
					    <td>${item.companyName}</td> 
					    
					    <c:choose>
							<c:when test="${item.delete==0}">
								<td class="td-manage">
	                            <a title="编辑" href="javascript:;" onclick="role_edit('编辑','./teamEditPage?id=${item.id}','4','','510')"
	                            class="ml-5" style="text-decoration:none">
	                                <i class="layui-icon">&#xe642;</i>
	                            </a>
	                            <a title="删除" href="javascript:;" onclick="role_del(this,'1')" 
	                            style="text-decoration:none">
	                                <i class="layui-icon">&#xe640;</i>
	                            </a>
	                            <a title="添加人员" href="javascript:;" onclick="teamUser_add('添加人员','../user/userAddPage?teamId=${item.id}','4','','510')" 
	                            class="ml-5" style="text-decoration:none">
	                                <i class="layui-icon">&#xe654;</i>
	                            </a>
                        		</td> 
						    </c:when>
						    <c:otherwise><td></td></c:otherwise>
						</c:choose>
					    
					  </tr>  
					</c:forEach>
         
                </tbody>
            </table>

            <div id="page"></div>
        </div>
        <script src="../static/layui/layui.js" charset="utf-8"></script>
        <script src="../static/js/x-layui.js" charset="utf-8"></script>
        <script>
        	
            layui.use(['laydate','element','laypage','layer'], function(){
                $ = layui.jquery;//jquery
              laydate = layui.laydate;//日期插件
              lement = layui.element;//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层

              //以上模块根据需要引入
            });
			
            //批量删除提交
             function delAll () {
                layer.confirm('确认要删除吗？',function(index){
                    //捉到所有被选中的，发异步进行删除
                    layer.msg('删除成功', {icon: 1});
                });
             }
             /*添加*/
            function role_add(title,url,w,h){
                x_admin_show(title,url,w,h);
                
            }
          	//添加人员
            function teamUser_add (title,url,id,w,h) {
                x_admin_show(title,url,w,h);
            }
             
            //编辑
            function role_edit (title,url,id,w,h) {
                x_admin_show(title,url,w,h);
            }
            /*删除*/
            function role_del(obj,id){
                layer.confirm('确认要删除吗？',function(index){
                    //发异步删除数据
                    var tds = $(obj).parent("td").prevAll("td");
                    $.ajax({  
                  	  url: './deleteTeam', 
                      type: 'POST',  
                      dataType: 'json',
                      data: {
                      	"id": tds[3].innerText
                      },
                      timeout: 1000,  
                      cache: false,     
	               	}).done(function(data) { 
	               		if(data.status==1){
		   	               	$(obj).parents("tr").remove();
		                    layer.msg('删除成功!',{icon:1,time:1000});

	  					}else{
	  						layer.msg('无法删除!',{icon:2,time:1000});
	  					}	
	                  }); 
                    
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
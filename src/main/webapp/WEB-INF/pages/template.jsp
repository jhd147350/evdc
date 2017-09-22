<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

<title>主页面</title>
<link rel="stylesheet" href="../static/layui/css/layui.css">
<link rel="stylesheet" href="../static/css/x-admin.css" media="all">
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">EVDC</div>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <!--<img src="photo.jpg" class="layui-nav-img">-->
          ${user.name }
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">个人配置</a></dd>
          <dd><a href="">修改密码</a></dd>
          <dd><a href="">组织信息</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="logout">注销</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;" _href="../ticket/ticketConsole">工单控制台</a>
        </li>
        <li class="layui-nav-item">
        <a href="javascript:;">值班管理</a>
        	<dl class="layui-nav-child">
	          <dd><a href="javascript:;" _href="../shift/schedule">值班信息</a></dd>
	          <dd><a href="javascript:;" _href="../shift/schedule">值班规则</a></dd>
	          
	        </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">组织管理</a>
          <dl class="layui-nav-child">
          	<dd><a href="javascript:;">组织信息</a></dd>
            <dd><a href="javascript:;" _href="../teamUserManagement/teamUserManagementPage">组织人员管理</a></dd>
            <dd><a href="javascript:;" _href="../userRoleManagement/userRoleManagementPage">人员角色管理</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
        	<a href="javascript:;">系统管理</a>
        	<dl class="layui-nav-child">
            <dd><a href="javascript:;">公告管理</a></dd>
            <dd><a href="javascript:;" _href="../teamManagement/teamManagementPage">组织管理</a></dd>
            <dd><a href="javascript:;" _href="../teamRoleManagement/teamRoleManagementPage">组织角色管理</a></dd>
          </dl>
        </li> 
  		<%-- <c:forEach items="${mainModules}" var="item" varStatus="status">
  			<li class="layui-nav-item">
  			<a href="..${item.path}">${item.authName}</a>
  			<c:if test="${fn:length(item.submodules) > 0}">  
			
	  			<dl class="layui-nav-child"> 
		  			<c:forEach items="${item.submodules}" var="subitem" varStatus="status"> 
							  <dd><a href="..${subitem.path}">${subitem.authName}</a></dd>
		  			</c:forEach>
	  			</dl>  
			</c:if>
			</li> 
  		</c:forEach> --%>			  
  		
      </ul>
    </div>
  </div>
  
  
    <div class="layui-body">
    <iframe frameborder="0" src="" class="x-iframe" width="100%" height="100%"></iframe>
    <!--<div style="padding: 15px;">内容主体区域</div>-->
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    ? layui.com - 底部固定区域
  </div>
</div>
<script src="../static/layui/layui.js"></script>
<script>
//JavaScript代码区域

layui.use('element', function(){
  var element = layui.element;
	element.on('nav(test)', function(elem){
	url = elem.find('a').attr('_href');
  var ifram = document.getElementsByClassName('x-iframe');
  ifram.item(0).setAttribute('src',url);	
  });
  
  
});

</script>
</body>
</html>
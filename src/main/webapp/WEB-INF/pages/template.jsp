<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 后台大布局 - Layui</title>
<link rel="stylesheet" href="../static/layui/css/layui.css">
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">EVDC</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <!--<ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>-->
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <!--<img src="photo.jpg" class="layui-nav-img">-->
          客户1
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">个人配置</a></dd>
          <dd><a href="">修改密码</a></dd>
          <dd><a href="">组织信息</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">注销</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">工单控制台</a>
        </li>
        <li class="layui-nav-item">
        	<dl class="layui-nav-child">
	          <a href="javascript:;">值班管理</a>
	          <dd><a href="javascript:;">值班查询</a></dd>
	          <dd><a href="javascript:;">批量排班</a></dd>
	          <dd><a href="javascript:;">批量删除</a></dd>
	        </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">组织管理</a>
          <dl class="layui-nav-child">
          	<dd><a href="javascript:;">组织信息</a></dd>
            <dd><a href="javascript:;">组织人员管理</a></dd>
            <dd><a href="javascript:;">人员角色管理</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
        	<a href="javascript:;">系统管理</a>
        	<dl class="layui-nav-child">
            <dd><a href="javascript:;">公告管理</a></dd>
            <dd><a href="javascript:;">组织管理</a></dd>
            <dd><a href="javascript:;">组织角色管理</a></dd>
          </dl>
        </li> 
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">内容主体区域</div>
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
  
});
</script>
</body>
</html>
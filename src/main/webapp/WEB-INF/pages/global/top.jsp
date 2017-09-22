<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="evdc.vianet.auth.entity.User"%>

<div class="layui-logo">${title}</div>
<ul class="layui-nav layui-layout-right" lay-filter="">
	<li class="layui-nav-item"><a href="javascript:;"> <img
			src="http://t.cn/RCzsdCq" class="layui-nav-img"> unAuth
	</a>
		<dl class="layui-nav-child">
			<!-- 二级菜单 -->
			<dd>
				<a href="javascript:;">个人信息</a>
			</dd>
			<dd>
				<a href="javascript:;">网站首页</a>
			</dd>
			
				<dd>
					<a href="javascript:;">管理页面</a>
				</dd>
			


			<dd>
				<a href="javascript:;">注销</a>
			</dd>
		</dl></li>
</ul>
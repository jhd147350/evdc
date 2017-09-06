<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <div class="layui-input-block">
            <select name="city" lay-verify="">
                <option value="1">处理中</option>
                <option value="2">已关闭</option>
            </select>

			<a href="<%=request.getContextPath()%>/ticket/create" class="layui-btn">创建工单</a>
        </div>
    </div>
</form>
<div class="">
    <table class="layui-table" lay-data="{height:315, url:'/demo/table/user/', page:true, id:'test'}" lay-filter="test">
        <thead>
        <tr>
            <th lay-data="{field:'id', width:80, sort: true}">ID</th>
            <th lay-data="{field:'username', width:80}">标题</th>
            <th lay-data="{field:'sex', width:80, sort: true}">优先级</th>
            <th lay-data="{field:'city', width:80, sort:true}">状态</th>
            <th lay-data="{field:'sign', width:177, sort:true}">受派者</th>
            <th lay-data="{field:'experience', width:80, sort: true}">受派组</th>
            <th lay-data="{field:'score', width:120, sort: true}">客户名称</th>
            <th lay-data="{field:'classify', width:120, sort:true}">提交日期</th>
            <th lay-data="{field:'wealth', width:135, sort: true}">修改日期</th>
            <th lay-data="{field:'wealth', width:135, sort: true}">服务</th>
            <th lay-data="{field:'wealth', width:135, sort: true}">报告来源</th>
        </tr>
        </thead>
    </table>
</div>

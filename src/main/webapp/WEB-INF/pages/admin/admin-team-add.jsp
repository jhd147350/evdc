<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form class="layui-form" action="">
	<div class="layui-form-item">
		<label class="layui-form-label">团队名称</label>
		<div class="layui-input-block">
			<input type="text" name="teamName" required lay-verify="required"
				placeholder="请输入团队名称" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">公司名称</label>
		<div class="layui-input-block">
			<input type="text" name="companyName" required lay-verify="required"
				placeholder="请输入公司名称" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">公司代号</label>
		<div class="layui-input-block">
			<input type="text" name="code" required lay-verify="required"
				placeholder="请输入公司代号" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">类型</label>
		<div class="layui-input-block">
			<input type="radio" name="sex" value="client" title="客户"> <input
				type="radio" name="sex" value="support" title="支持人员" checked>
		</div>
	</div>
	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">团队描述</label>
		<div class="layui-input-block">
			<textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>

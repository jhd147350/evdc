<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="main well">
    <legend>创建新工单</legend>
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">标题</label>
            <div class="layui-input-block">
                <input id="title" type="text" name="title"  placeholder="请输入标题" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">严重程度</label>
                <div class="layui-input-block">
                <!-- lay-verify="required" -->
                    <select name="city" >
                        <option value=""></option>
                        <option value="0">Sev1</option>
                        <option value="1">Sev2</option>
                        <option value="2">Sev3</option>
                        <option value="3">Sev4</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">服务</label>
                <div class="layui-input-block">
                    <select name="city" >
                        <option value=""></option>
                        <option value="0">修改虚机配置</option>
                        <option value="1">网络问题</option>
                        <option value="2">服务器宕机</option>
                    </select>
                </div>
            </div>
        </div>


        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">详细描述</label>
            <div class="layui-input-block">
                <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
            <!-- lay-submit lay-filter="formDemo"  -->
                <button id="create_ticket" class="layui-btn">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
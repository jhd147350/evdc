<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="layui-layout layui-layout-admin">

    <div class="layui-header header header-demo">
        <div class="layui-main">
            <a class="logo" href="">
                <img src="http://res.layui.com/images/layui/logo.png" alt="layui">
            </a>
            <ul class="layui-nav" lay-filter="" pc>
                <li class="layui-nav-item"><img src="http://admin.erdangjiade.com/images/logo.png" class="layui-circle"
                                                style="border: 2px solid #A9B7B7;" width="35px" alt=""></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">admin</a>
                    <dl class="layui-nav-child"> <!-- 二级菜单 -->
                        <dd><a href="">个人信息</a></dd>
                        <dd><a href="">退出</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
</div>
<script>
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function () {
        var element = layui.element();

        //…
    });
</script>
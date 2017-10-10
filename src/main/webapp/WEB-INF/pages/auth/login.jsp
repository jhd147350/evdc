<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
           	EVDC登录页面
        </title>
        <style>
			.my-box {
				 position: relative;
 				 width: 400px;
 				 height: 450px;
			 	 background: #fff url() 0 0 no-repeat;
 				 border-radius: 20px;
 				 margin: 80px auto;
 				 overflow: hidden;
				}
				.title {
				color:#000;
				text-align:center;
  				line-height:100px;   
  				overflow:hidden;
				font-size:22px;
				height: 100px;
  				width: 100%;
  				position: relative;
				}
		</style>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="../static/css/x-admin.css" media="all">
    </head>
    
    <body style="background-color: #393D49">

            <div class="my-box">
               	<div class="title">
                    	EVDC客户支持系统
                </div>
                <div class="input">
                    <form action="./dologin" class="layui-form" method="post">
                        <div class="layui-form-item x-login-box">
                            <label for="username" class="layui-form-label">
                                <i class="layui-icon">&#xe612;</i>
                            </label>
                            <div class="layui-input-inline">
                                <input type="text" id="loginId" name="loginId" required="" lay-verify="username"
                                autocomplete="off" placeholder="username" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item x-login-box">
                            <label for="pass" class="layui-form-label">
                                <i class="layui-icon">&#xe628;</i>
                            </label>
                            <div class="layui-input-inline">
                                <input type="password" id="password" name="password" required="" lay-verify="pass"
                                autocomplete="off" placeholder="******" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item" id="loginbtn">
                            <input type="submit" class="layui-btn" lay-filter="save" lay-submit="" value="登录">
                            
                        </div>
                    </form>
                </div>
            </div>
        <p style="color:#fff;text-align: center;">Copyright © 2017.vianet </p>
        <script src="../static/layui/layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                layer = layui.layer;

                $('.x-login-right li').click(function(event) {
                    color = $(this).attr('color');
                    $('body').css('background-color', color);
                });
                //监听提交
                /* form.on('submit(save)',
                function(data) {
                    console.log(data);
                    layer.alert(JSON.stringify(data.field), {
                      title: '最终的提交信息'
                    },function  () {
                        location.href = "./dologin";
                    })
                    return false;
                }); */

            });

        </script>
    </body>

</html>
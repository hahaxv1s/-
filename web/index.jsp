<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/4
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        function checkUser(){
            var use = document.getElementById("useInputBox").value;
            var password = document.getElementById("passwordInputBox").value;
            if(use == "" ){
                alert("用户名不能为空");
                return false;
            }
            if(password == "" ){
                alert("密码不能为空");
                return false;
            }else{
                return true;
            }
        }
    </script>
    <style type="text/css">
        body{
            font-family: -apple-system,BlinkMacSystemFont,Helvetica Neue,PingFang SC,Microsoft YaHei,Source Han Sans SC,Noto Sans CJK SC,WenQuanYi Micro Hei,sans-serif;
            font-size: 15px;
            color: #1a1a1a;
            background-image:url(images/login/background.jpg);
            background-repeat: no-repeat;
            background-color: rgba(240, 255, 255, 0.5);
            background-size: cover;
            -webkit-tap-highlight-color: rgba(26,26,26,0);
            display: block;
            line-height: 1.5;
        }
        body, button, p, pre {
            margin: 0;
        }
        #wrapper {
            position: relative;
            display: flex;
            -webkit-box-pack: center;
            justify-content: center;
            margin: 0px auto;
            box-sizing: border-box;

        }
        #wrapperbefore {
            height: 25%;
        }


        div {
            display: block;
        }
        .content {
            width: 400px;
            max-width: 90vw;
            pointer-events: initial;
            opacity: 1;
            transform: unset;
            transform-origin: center center 0px;
            flex: 0 0 auto;
            background-color: rgba(255, 255, 255, 0.5); /* 白色并且阿尔法通道是0.5*/
            border-radius: 5px;
            overflow: hidden;
            transition: all 0.2s ease-out;
            box-sizing: border-box;
        }
        #top{
            position: relative;
            display: flex;
            -webkit-box-align: center;
            align-items: center;
            padding: 10px;

            border-radius: 3px 3px 0px 0px;
        }

        #main {
            max-height: 700px;
            padding: 0px 20px 20px;
            overflow: auto;
        }
        #box {
            max-width: 340px;
            margin: 0px auto;
        }
        #logblock{
            display: flex;
            flex-direction: column;
            -webkit-box-align: stretch;
            align-items: stretch;
        }


        .header {
            height: 45px;
            margin-bottom: 31px;
        }
        .submitblock{
            display: flex;
            flex-direction: column;
            -webkit-box-align: stretch;
            align-items: stretch;
        }
        .inputusename{
            display: inline-block;
            position: relative;
        }
        .inputblock{
            color: rgb(55, 71, 79);
            background: white;
            border-color: rgb(189, 189, 189);
            height: 40px;
            box-sizing: border-box;
            width: 90%;
            padding-left: 10%;
           /* padding-right: 50%;*/

        }
        .erroblock{
            opacity: 0;
            pointer-events: none;
            transition: opacity 0.3s
        }
        .erromessage{
            color: rgb(198, 41, 40);
            font-size: 12px;
            line-height: 18px;
            margin: 10px 0px 20px;
        }
        .inputpassword{
            position: relative;
            width: 100%;
        }
        .normalbuttom{
            display: inline-flex;
            vertical-align: middle;
            -webkit-box-pack: center;
            justify-content: center;
            -webkit-box-align: center;
            align-items: center;
            line-height: 20px;
            font-size: 14px;
            cursor: pointer;
            color: rgb(255, 255, 255);
            box-shadow: rgba(38, 50, 56, 0.2) 0px 0px 0px 1px inset;
            height: 40px;
            border-radius: 3px;
            transition: all 0.18s ease-in-out;
            outline: 0px;
            border: 0px;
            background: rgb(69, 90, 100);
            padding: 5px 12px;
        }
        #useInputBox{
            background: url("icon/login/user.png")  no-repeat ;
            background-position: 2px;
            background-size: 30px 30px;
            padding-left: 35px;
            outline: none;
        }
        #passwordInputBox{
            background: url("icon/login/lock.png")  no-repeat;
            background-position: 2px;
            background-size: 30px 30px;
            padding-left: 35px;
            outline: none;

        }

    </style>
</head>
<body>
<div id="wrapperbefore"></div>
<div id="wrapper">
    <div class="content">
        <div id="top"></div>

        <div id="main">

            <div id="box">
                <div id="logblock">
                <div class="header">
                    <p id="topic">登录</p>
                </div>

                    <form action="servlet/login" method="post" class="submitblock" onsubmit="return checkUser();">

                        <div class="inputusename">
                           <input type="text" name="userName" placeholder="输入你的用户名"  class="inputblock" id="useInputBox">
                        </div>
                        <div class="erroblock">
                            <p class="erromessage"></p>
                        </div>
                        <div class="inputpassword">
                            <input type="password" name="password"  placeholder="输入密码" class="inputblock" id="passwordInputBox">
                        </div>
                        <div class="erroblock">
                            <p class="erromessage"></p>
                        </div>
                                <input type="submit" class="normalbuttom" value="login">
                    </form>
                    <div style="display: inline">
                    <a href="register.html" style="color:darkslategrey;text-decoration:none;">注册新用户</a>
                    <a href="forgetPassword.html" style="float: right;color:darkslategrey;text-decoration:none;">忘记密码</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
</html>

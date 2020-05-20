<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/5
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>   
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <style type="text/css">
        body{
            background: #f6f6f6;
            margin: 0;
            height: 100%;
            font-family: -apple-system,BlinkMacSystemFont,Helvetica Neue,PingFang SC,Microsoft YaHei,Source Han Sans SC,Noto Sans CJK SC,WenQuanYi Micro Hei,sans-serif;
            font-size: 12px!important;
            display: block;
            overflow-x: hidden;
            overflow-y: auto;

        }

        #menuContent{
            margin-left: 0px;
            position: fixed;
            top: 0;
            left: 0;
            width: 100px;
            bottom: 0;
            z-index: 10;
            transition: 0.5s;
            background: #fafafa;
        }
        #menuTop{
            background-position: top 7px left 10px;
            position: absolute;
            top: 0;
            left: 0px;
            height: 37px;
            width: 60px;
            padding-left:40px;
            padding-top: 13px;
            background-color: #10191c;
            /*    background-image: url();              这里放菜单的logo */
            background-repeat: no-repeat;
            background-position: top 7px left 10px;
            cursor: pointer;
            z-index: 10;
            color: #ffffff;
            font-size: 20px;
            line-height: 28px;
            transition: 0.5s;
        }

        #menuDialog{
            background-color: #10191c;
            position: absolute;
            top: 50px;
            left: 0;
            right: 0;
            bottom: 0;
        }
        #menuContainer{
            background-color: rgba(255, 255, 255, 0.05);
            position: absolute;
            top: 50px;
            left: 0;
            right: 0;
            bottom: 0;
            z-index: 11;
            overflow-y: auto;
            overflow-x: hidden;
            padding-bottom: 10px;
        }
        #topContent{
            position: relative;
            height: 50px;
            width: 100%;
            top:-18px;
            background-color: #ffffff;
        }
        #topMenuContent{
            left: 100px;
            position: absolute;
            right: 0;
            height: 50px;

            transition: 0.5s;
        }
        #mainContent{
            position: absolute;
            left: 100px;
            top: 0px;
            right: 0;
            min-height: 530px;
            height: inherit;
            transition: 0.5s;
            overflow-y: auto;
        }
        #mainLayout{
            margin: auto;
        }
        .menuItem{
            height: 50px;
            cursor: pointer;
            position: relative;
            transition: 0.2s;
        }
        .divMenuTitle{
            height: 50px;
            float: left;
            margin-left: 10px;
            color: #9dc6d2;
            font-size: 16px;
            line-height: 50px;
        }

        .topMenuRight{
            display: flex;
            float: right;
            align-items: center;
            font-size: 14px;
        }

        .normalButtom{
            color: #333;
            font-weight: 400;
            padding-left: 0;
            padding-right: 0;
            background: transparent;
            display: inline-block;
            line-height: 1;
            white-space: nowrap;
            cursor: pointer;
            -webkit-appearance: none;
            text-align: center;
            box-sizing: border-box;
            outline: none;
            margin: 0;
            transition: .1s;
            font-size: 14px;
            border-radius: 4px;
        }

        #mainFrame{
            background-color: #f6f6f6;
            overflow: hidden;
            height: 820px;
            width: 100%;
            border: medium none;;
        }
    </style>
    <title id="title"> </title>
</head>
<body>
<div id="board" style="width: 100%;">
    <div id="menuContent">

        <div id="menuTop">
            <div>菜单</div>
        </div>

        <div id="menuDialog"></div>
        <div id="menuContainer">


        </div>
    </div>


<!--本来这个位置打算加个顶栏，但是格式总是对不齐，所以以后闲下来再考虑这个吧
    <div>
            <div id="topContent">
                <div id="topMenuContent">
                    <div class="topMenuLeft" style="align-self: center">
                         //这里是顶部栏的左边，可以加个顶部搜索栏什么的
                    </div>

                    <div class="topMenuRight" >
                        <button class="normalButtom">
                            <span>按钮一</span>
                        </button>
                        <button class="normalButtom" style="margin-left: 24px;">
                            <span>按钮二</span>
                        </button>
                        //这里也可以写成动态添加按钮的，不过我觉得没必要，而且不同的按钮位置似乎需要一点点调整
                        <button class="normalButtom" style="margin-left: 24px; margin-right: 24px;">
                            <span>
                                <img  id="bell" alt="消息" src="../icon/UserPage/bell.png" style="width: 18px; height: 21px;">
                                //虽然我也不知道可以有什么消息，不过这种小图标看起来蛮好玩，我就加上了
                               //而且还会变色
                            </span>
                        </button>
                    </div>
                </div>
            </div>
    </div>
-->


            <div  id="mainContent">
                <div style="margin: auto;">
                    <iframe id="mainFrame"  src="" frameborder="0" height="100%" width="100%" scrolling="no" style="overflow-y: hidden;">
                    </iframe>
                    <div id="mainLayout"></div>
                </div>
            </div>

</div>


<script type="text/javascript">
   /*-----------------左侧栏出入函数，但是感觉动作有些生硬，希望后期找到合适的替代方案---------------------------*/
   /*在找到顺滑出入的方法前，先用固定侧边栏
   var index=0;
    var num=0;
    $(function(){
        $('#menuContent').hover(function(){
            $('#menuContent').eq(num).stop().animate({left:'0px'},500);
            index = $(this).index();
            $('#menuContent').eq(index).stop().animate({left:'100px'},500);
            num=index;
        });
        $('#menuContent').mouseleave(function(){
            $(this).stop().animate({left:'0px'},500);
        })
    })
    */
/*------------------------------------------------------------------------------------------------------------*/
/*使用js模拟form表单提交
   function Post(URL, PARAMTERS) {
       //创建form表单
       var temp_form = document.createElement("form");
       temp_form.action = URL;
       //如需打开新窗口，form的target属性要设置为'_blank'
       temp_form.target = "_self";
       temp_form.method = "post";
       temp_form.style.display = "none";
       //添加参数
       for (var item in PARAMTERS) {
           var opt = document.createElement("textarea");
           opt.name = item;
           opt.value = PARAMTERS[item];
           temp_form.appendChild(opt);
       }
       document.body.appendChild(temp_form);
       //提交数据
       temp_form.submit();
   }
var para={};
--------------------------------------------------------------------------------------*/
 /*--------------------------------------接收内容页面-------------------------------------*/
   /*
   function getMainContain(URL,itemName){
   $.ajax(
       {
           type: "post",
           url:URL,
           data: itemName,
           success: function (result) {
                console.log(result);
                console.log(typeof (result));
              $("#mainFrame",parent.document.body).attr("src","http://localhost:8080/untitled/"+result)

           },
           error: function(XMLHttpRequest, textStatus) {
               alert(XMLHttpRequest.status);
               alert(XMLHttpRequest.readyState);
               alert(textStatus);
           },
       })
   }
   */
/*--------------------------------------------------------------------------------------------*/
var test_data;
   function getUserPage(){
       $.ajax({
           url:"UserJudge",
           type:"post",
           dataType:"json",
           success: function(data) {
               test_data = data;
               console.log(data);
               for(var p in test_data){//遍历json数组时，这么写p为索引，0,1
                 //  alert(test_data[p].text + " " + test_data[p].URL);
                   //这里用来添加从json中获取的菜单栏选项
                   $("#menuContainer").append(
                       "<div  class=\"menuItem\" itemURL=\""+test_data[p].itemURL+"\" " + "itemText=\""+test_data[p].itemText+"\" > " +
                       "<div class=\"divMenuTitle\"> " +
                       test_data[p].itemText+
                       "</div> " +
                       "</div>");
               }
/*只有在菜单栏全部显示完后才添加鼠标出入变色事件*/
               var itemlist = document.getElementsByClassName("menuItem");
               for (var i = 0; i < itemlist.length; i++) {
                   //添加点击事件使得选项栏点击后子页面有跳转
                   $(itemlist[i]).click(function() {
                       $("#mainFrame",parent.document.body).attr("src","http://localhost:8080/untitled/"+this.getAttribute("itemURL"))
                   });

                   //为li注册鼠标进入事件
                   itemlist[i].onmouseover = function () {
                       this.style.backgroundColor = "rgba(255, 255, 255, 0.3)";
                   };
                   //为li注册鼠标离开事件
                   itemlist[i].onmouseout = function () {
                       //恢复到这个标签默认的颜色
                       this.style.backgroundColor = "";
                   };
               }


           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
               alert(XMLHttpRequest.status);
               alert(XMLHttpRequest.readyState);
               alert(textStatus);
           },
       })
   }
/*这个是侧边栏上用的，现在没用了
   function addHover(){
           $('#bell').hover(
               function(){
                   $(this).src="../icon/UserPage/bell-hover.png";
                },
               function(){
               $(this).src="../icon/UserPage/bell.png";
           });
   }
 */

   window.onload = function(){
       getUserPage()       //页面加载完成的时候自动生成菜单选项栏
      // addHover()

   }


</script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  Class.User: Windows Class.User
  Date: 2021-1-24
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="description" content="基于 Java 开发的在线 OJ"/>
  <title>在线 OJ</title>
  <meta name="viewport" content="width=device-width" charset=UTF-8" http-equiv="Content-Type">

  <h1 style="color: #ffffff"></h1>
  <!--Bootstrap 4-->
  <link  href="css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  <!--<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>-->
  <script src="js/jquery-3.5.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/app.js"></script>
  <style>
    .botCenter{width:100%; height:35px; line-height:35px; background:#ccc; position:fixed; bottom:0px; left:0px; font-size:14px; color:#000; text-align:center;}
  </style>

</head>
<body style="background: url() ; background-repeat:no-repeat ; background-size:100% 100%;background-attachment: fixed;" >
<div class="col-sm-12 my-auto text-center">
  <h1>OnlineJudge</h1>
  <p class="lead text-capitalize my-4">
    基于 java 搭建的在线 OJ 平台
  </p>
</div>
<div>
  <form action="Login" method="post">
    <div class="form-group">
      <center>
        <label for="username1"></label><span id="username1span"></span><input type="text" class="form-control" id="username1" name="username"  style="width: 400px;" maxlength="20" placeholder="Please input username">
      </center>
    </div>

    <div class="form-group">
      <center>
        <label for="password1"></label><span id="password1span"></span><input type="password" class="form-control" id="password1" name="password"  placeholder="Please input password" style="width: 400px;" maxlength="20">
      </center>
    </div>
    <div class="form-group" style="text-align: center">
      <input class="btn btn-primary" type="submit" value="登录" />
      <a class="btn btn-default" href="register.jsp" >注册</a>
<%--      <a class="btn btn-default" href="editor.jsp" >注册</a>--%>
      <%--        <a class="btn btn-default" href="editor.jsp" >注册</a>--%>
    </div>
  </form>
</div>
<div class="botCenter">
  Author:HNU牛奶
  Email:kizzy8452@gmail.com
</div>

<script>
  //鼠标点击出现爱心特效
  (function(window,document,undefined){
    var hearts = [];
    window.requestAnimationFrame = (function(){
      return window.requestAnimationFrame ||
              window.webkitRequestAnimationFrame ||
              window.mozRequestAnimationFrame ||
              window.oRequestAnimationFrame ||
              window.msRequestAnimationFrame ||
              function (callback){
                setTimeout(callback,1000/60);
              }
    })();
    init();
    function init(){
      css(".heart{width: 10px;height: 10px;position: fixed;background: #f00;transform: rotate(45deg);-webkit-transform: rotate(45deg);-moz-transform: rotate(45deg);}.heart:after,.heart:before{content: '';width: inherit;height: inherit;background: inherit;border-radius: 50%;-webkit-border-radius: 50%;-moz-border-radius: 50%;position: absolute;}.heart:after{top: -5px;}.heart:before{left: -5px;}");
      attachEvent();
      gameloop();
    }
    function gameloop(){
      for(var i=0;i<hearts.length;i++){
        if(hearts[i].alpha <=0){
          document.body.removeChild(hearts[i].el);
          hearts.splice(i,1);
          continue;
        }
        hearts[i].y--;
        hearts[i].scale += 0.004;
        hearts[i].alpha -= 0.013;
        hearts[i].el.style.cssText = "left:"+hearts[i].x+"px;top:"+hearts[i].y+"px;opacity:"+hearts[i].alpha+";transform:scale("+hearts[i].scale+","+hearts[i].scale+") rotate(45deg);background:"+hearts[i].color;
      }
      requestAnimationFrame(gameloop);
    }
    function attachEvent(){
      var old = typeof window.onclick==="function" && window.onclick;
      window.onclick = function(event){
        old && old();
        createHeart(event);
      }
    }
    function createHeart(event){
      var d = document.createElement("div");
      d.className = "heart";
      hearts.push({
        el : d,
        x : event.clientX - 5,
        y : event.clientY - 5,
        scale : 1,
        alpha : 1,
        color : randomColor()
      });
      document.body.appendChild(d);
    }
    function css(css){
      var style = document.createElement("style");
      style.type="text/css";
      try{
        style.appendChild(document.createTextNode(css));
      }catch(ex){
        style.styleSheet.cssText = css;
      }
      document.getElementsByTagName('head')[0].appendChild(style);
    }
    function randomColor(){
      return "rgb("+(~~(Math.random()*255))+","+(~~(Math.random()*255))+","+(~~(Math.random()*255))+")";
    }
  })(window,document);
</script>
</body>
</html>

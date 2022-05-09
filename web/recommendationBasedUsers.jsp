<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/3/3
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>推荐结果</title>
    <meta charset="utf-8">
    <meta name="description" content="基于 Java 开发的在线 OJ"/>
    <meta name="viewport" content="width=device-width" charset=UTF-8 http-equiv="Content-Type">
    <link  href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/app.js"></script>
    <link rel="stylesheet" href="codemirror-5.62.2/lib/codemirror.css"/>
    <link rel="stylesheet" href="codemirror-5.62.2/addon/hint/show-hint.css">
    <link rel="stylesheet" href="codemirror-5.62.2/theme/material.css">
    <script src="codemirror-5.62.2/lib/codemirror.js"></script>
    <script src="codemirror-5.62.2/mode/clike/clike.js"></script>
    <script src="codemirror-5.62.2/addon/edit/matchbrackets.js"></script>
    <script src="codemirror-5.62.2/addon/hint/show-hint.js"></script>
    <link type="text/css" rel="stylesheet" href="codemirror-5.62.2/theme/dracula.css">
    <script src="codemirror-5.62.2/addon/display/fullscreen.js"></script>
    <link rel="stylesheet" href="codemirror-5.62.2/addon/display/fullscreen.css">
    <script type="text/javascript" src="codemirror-5.62.2/addon/edit/closebrackets.js"></script>
    <script type="text/javascript" src="codemirror-5.62.2/addon/edit/closetag.js"></script>
    <script type="text/javascript" src="codemirror-5.62.2/addon/edit/continuelist.js"></script>
    <script type="text/javascript" src="codemirror-5.62.2/addon/edit/matchbrackets.js"></script>
    <script type="text/javascript" src="codemirror-5.62.2/addon/edit/matchtags.js"></script>
    <script type="text/javascript" src="codemirror-5.62.2/addon/edit/trailingspace.js"></script>
    <script type="text/javascript" src="codemirror-5.62.2/addon/fold/foldcode.js"></script>
    <script type="text/javascript" src="codemirror-5.62.2/addon/fold/foldgutter.js"></script>
    <script type="text/javascript" src="codemirror-5.62.2/addon/fold/brace-fold.js"></script>
    <script type="text/javascript" src="codemirror-5.62.2/addon/fold/comment-fold.js"></script>
    <link rel="stylesheet" href="codemirror-5.62.2/addon/fold/foldgutter.css">
    <script type="text/javascript" src="codemirror-5.62.2/addon/hint/show-hint.js"></script>
    <script type="text/javascript" src="codemirror-5.62.2/addon/hint/javascript-hint.js"></script>
    <link rel="stylesheet" href="codemirror-5.62.2/addon/hint/show-hint.css">
    <link rel="stylesheet" href="codemirror-5.62.2/theme/ssms.css">
    <style>
        .cm-s-dracula .CodeMirror-cursor {
            border-left: solid white !important;
        }
        .text-wrapper {
            word-break: break-all;
            word-wrap: break-word;
        }
        .botCenter{width:100%; height:35px; line-height:35px; background:#ccc; position:fixed; bottom:0px; left:0px; font-size:14px; color:#000; text-align:center;}
    </style>
</head>
<body style="background: url() ; background-repeat:no-repeat ; background-size:100% 100%;background-attachment: fixed;">




<section class="my-5 pt-5">
    <div class="container" id="app">
        <div class="row mb-5"  v-show="status=='list'">
            <div class="col-sm-12">
                <div class="mt-3 mb-5">
                    <h3>基于用户的推荐题目列表</h3>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>题目</th>
                            <th>难度</th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr v-for="problem in problems">
                            <td>{{problem.id}}</td>
                            <td><a v-on:click="getDetail(problem.id)">{{problem.title}}</a></td>
                            <td>{{problem.difficulty}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row mb-5"  v-show="status=='detail'">
            <div class="col-sm-12">
                <div class="jumbotron">
                    <div class="">
                        <h2>{{problem.id}}.{{problem.title}} {{problem.level}}</h2>
                        <h4 style="color: royalblue;">Description</h4>
                        <div class="text-wrapper">{{problem.description}}</div>

                        <h4 style="color: royalblue;">Input_Description</h4>
                        <div class="text-wrapper">{{problem.input_description}}</div>

                        <h4 style="color: royalblue;">Output_Description</h4>
                        <div class="text-wrapper">{{problem.output_description}}</div>

                        <h4 style="color: royalblue;">Input</h4>
                        <div class="text-wrapper">{{problem.sample_input}}</div>
                        <h4 style="color: royalblue;">Output</h4>
                        <div class="text-wrapper">{{problem.sample_output}}</div>
                    </div>
                </div>
                <textarea  class="form-control"  id="code"></textarea>
                <button  class="btn btn-primary" v-on:click="compile()">提交</button>
                <button class="btn btn-primary" type="button" id="back_button">返回题目列表</button>
                <button class="btn btn-primary" type="button" @click="getAcceptProblemsId()">查看已完成的题目</button>
                <a class="btn btn-primary" href="oj.jsp" >返回题目列表</a>
            </div>
        </div>
    </div>
</section>


<script type="text/javascript">
    $(function(){
        $("#back_button").click(function(){
            location.reload(true);
        });
    });
</script>

<script>

    var app = new Vue({
        el: '#app',  // 把当前这个 Vue 对象关联到页面中一个 id 为 app 的 html 元素上
        data: {
            status: "list",
            problems: [
            ],
            recommendProblemBasedUser:[
            ],
            problem: {
                id: 1,
                title: '我是标题',
                difficultly: 1,
                description: '我是题目描述',
                input_description:"",
                output_description:"",
                input:"1 2",
                output:"3",
                src:"1",
            },
            editor:null,
        },
        mounted:function(){
            this.init()
        },
        methods: {
            init(){
                this.editor=CodeMirror.fromTextArea(document.getElementById("code"),{
                    lineNumbers: true,
                    mode: "text/x-c++src",
                    theme:'dracula',
                    indentUnit: 4,
                    autoRefresh: true,
                    matchBrackets:true,
                    autoCloseBrackets:true,
                    fullscreen:true,
                    styleActiveLine:true,
                    tabSize:4,
                    cursorBlinkRate:530,
                    lineWrapping: true, //代码折叠
                    foldGutter: true,
                    autofocus:true,
                    cursorHeight:0.85,
                    gutters: ["CodeMirror-linenumbers", "CodeMirror-foldgutter"],
                    // showCursorWhenSelecting,true,
                    extraKeys:{
                        "Tab": "autocomplete"
                    }
                });
                this.editor.setSize("auto","700px");
                this.refresh();
            },
            refresh(){
                this.editor.refresh();
            },
            getProblems() {
                $.ajax({
                    url: 'RecommendBasedUser',
                    type: 'get',
                    context: this, // this 指向的 是 Vue app 对象, 此处是把 app 对象传到 ajax 方法内部
                    success: function(data, status) {
                        // 得到正确的响应之后, 就会自动调用该函数.
                        // 参数 data 是得到的 HTTP 响应的 body 部分, 预期是一个 json 数组
                        console.log(data);

                        this.problems = data;
                    }
                })
            },
            getRecommendProblemBasedUser(){
                $.ajax({
                    url: 'RecommendBasedProblem',
                    type: 'get',
                    context: this, // this 指向的 是 Vue app 对象, 此处是把 app 对象传到 ajax 方法内部
                    success: function(data, status) {
                        // 得到正确的响应之后, 就会自动调用该函数.
                        // 参数 data 是得到的 HTTP 响应的 body 部分, 预期是一个 json 数组
                        this.recommendProblemBasedUser = data;
                    }
                })
            },
            getDetail(id) {
                $.ajax({
                    url: 'problem?id='+id,
                    type: 'get',
                    context: this,
                    success: function(data, status) {
                        this.problem = data;
                        this.status = 'detail';
                    }
                })
            },
            compile() {
                var question = {
                    id: this.problem.id,
                    // 前面使用 v-model 进行了双向绑定. 当用户修改编辑框代码时,
                    // templateCode 变量也会同步变化
                    code: this.editor.getValue(),
                }
                $.ajax({
                    url: 'judge',
                    type: 'post',
                    context: this,
                    dataType:'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(question),
                    success: function(data, status) {
                        var msg = data.ok + "\n"
                        if (data.reason) {
                            msg += data.reason;
                        }
                        alert(msg);
                    }
                })
            },
            getAcceptProblemsId(){
                $.ajax({
                    url:'accept',
                    type:'get',
                    context:this,
                    success:function (data,status) {
                        var message="已完成的题目："+ data.problems_id;
                        alert(message);

                    }
                })
            },
            refreshPage(){
                $(function(){
                    $("button").click(function(){
                        location.reload(true);
                    });
                });
            }
        },
    });
    app.getProblems();
</script>











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

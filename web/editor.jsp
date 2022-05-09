<%--
  Created by IntelliJ IDEA.
  User: Windows User
  Date: 2021-3-5
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑器</title>
    <meta charset="utf-8">
    <link type="text/css" rel="stylesheet" href="css/codemirror.css"/>
    <script type="text/javascript" src="codemirror-5.62.2/lib/codemirror.js"></script>
    <script type="text/javascript" src="codemirror-5.62.2/mode/clike/clike.js"></script>
    <link type="text/css" rel="stylesheet" href="codemirror-5.62.2/theme/dracula.css">
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <link rel="stylesheet" href="css/dracula.css">
    <style>.CodeMirror {border: 2px inset #dee;}</style>
</head>
<body>
<%--id="cpp-code"--%>
<div id="app" class="col-sm-12">
    <textarea class="form-control"  id="cpp-code"></textarea>

    <button @click="getSrc()">
        dian
    </button>
</div>

<script src="js/jquery-3.5.1.min.js"></script>

<%--<script>--%>
<%--    var cppEditor = CodeMirror.fromTextArea(document.getElementById("cpp-code"), {--%>
<%--        lineNumbers: true,--%>
<%--        matchBrackets: true,--%>
<%--        mode: "text/x-c++src"--%>
<%--    });--%>
<%--    // var mac = CodeMirror.keyMap.default == CodeMirror.keyMap.macDefault;--%>
<%--    // CodeMirror.keyMap.default[(mac ? "Cmd" : "Ctrl") + "-Space"] = "autocomplete";--%>
<%--</script>--%>
<script>
    var app=new Vue({
        el:'#app',
        data:{
            src:12,
            editor:null,
        },
        mounted:function () {
            this.init()
        },
        methods:{
            init(){
                    this.editor = CodeMirror.fromTextArea(document.getElementById("cpp-code"), {
                        lineNumbers: true,
                        mode: "text/x-c++src",
                        theme:'dracula',
                        indentUnit: 4,
                });
            },
            getSrc(){
                alert(this.editor.getValue());
            }
        }
    })
</script>
</body>
</html>

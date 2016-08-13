<%-- 
    Document   : backstagecategory
    Created on : 2015-2-27, 13:36:23
    Author     : liaojm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>无标题文档</title>
        <link href="css/backstage_style.css" rel="stylesheet" type="text/css">
        <link href="css/backstage_bootstrap.min.css" rel="stylesheet" type="text/css" />
        <style type="text/css" media="all">
            a,a:visited {color:#333;text-decoration:none;}
            a:hover {color:#f60;}
            body,td {font:13px "Geneva","宋体", "Arial", "Helvetica",sans-serif;}
            ul,li {margin:0;padding:0;list-style:none;}
            h1,h2,h3,h4,h5,h6 {margin:0;padding:0;}
            h1 {padding:5px;color:#900;font:16px bold "Geneva","宋体", "Arial", "Helvetica",sans-serif;}
            h1 small {font-size:11px;font-weight:normal;color:#660;}
            .TreeWrap {width:200px;}
            .MenuBox .titBox a,
            .MenuBox .titBox a:visited,
            .MenuBox2 .titBox a,
            .MenuBox2 .titBox a:visited {margin-left:10px;padding-left:40px;color:#003;font-size:12px;display:block;}
            .MenuBox .titBox h3 a {background:url(images/ico_folder_open.gif) no-repeat 0 40%;}
            .MenuBox .titBox h3.Fst a {background:url(images/ico_folder_open_fst.gif) no-repeat 0 40%;}
            .MenuBox .titBox h3.Lst a {background:url(images/ico_folder_open_lst.gif) no-repeat 0 40%;}
            .MenuBox2 .titBox h3 a {background:url(images/ico_folder.gif) no-repeat 0 40%;}
            .MenuBox2 .titBox h3.Fst a {background:url(images/ico_folder_fst.gif) no-repeat 0 40%;}
            .MenuBox2 .titBox h3.Lst a {line-height:250%;background:url(images/icon_exit.gif) no-repeat 0 0;}
            .MenuBox2 .txtBox {display:none;}
            .MenuBox .txtBox ul li {padding-left:65px;line-height:150%;}
            .MenuBox .txtBox .num {color:#e00;}
            .MenuBox .txtBox ul {background:url(images/line_y.gif) repeat-y 16px 0;}
            .MenuBox .txtBox ul li {background:url(images/t.gif) no-repeat 28px 50%;}
            .MenuBox .txtBox ul li.Lst {background:url(images/t_lst.gif) no-repeat 28px 50%;}
        </style>
        <script type="text/javascript">
            <!--
         function ExChgClsName(Obj, NameA, NameB) {
                var Obj = document.getElementById(Obj) ? document.getElementById(Obj) : Obj;
                Obj.className = Obj.className == NameA ? NameB : NameA;
            }
            function showMenu(iNo) {
                ExChgClsName("Menu_" + iNo, "MenuBox", "MenuBox2");
            }
-->
        </script>
    </head>
    <script src="js/jquery.min.js" type="text/javascript"></script>

    <script type="text/javascript">
            $(document).ready(function () {
                var data =${jsonarray};
                var str = "<div class='TreeWrap'>";
                for (var i = 0; i < data.length; i++) {
                    var showData = data[i];
                    var category = showData.category;
                    str = str + "<div class='MenuBox2' id='Menu_" + i + "'>" +
                            "<div class='titBox'><h3 class='Fst'><a href='javascript:showMenu(" + i + ");'>" + category + "</a></h3></div>" +
                            "<div class='txtBox'>";
                    for (var key in data[i]) {
                        if (key != "category") {
                            str += "<ul><li>" + showData[key]+"<a href='fileexportcategory.htm?category=" + showData[key] + "'><button type='button'  class='btn btn-danger'  style='float:right;'>导出</button></a></li></ul>";
                        }
                    }
                    str += "</div></div></tr>";
                }
                $("#table").html(str);
            });
    </script>
</head>

<!--<script src="js/jquery.min.js" type="text/javascript"></script>

<script type="text/javascript">
                    $(document).ready(function () {
            var data =${jsonarray};
                    var str = "";
                    for (var i = 0; i < data.length; i++) {
            var showData = data[i];
                    var category = showData.category;
                    str = str + " <div class='subNavq '>标准名称</div>" +
                    "<div class='txtBox'>" +
                    " <ul class='navContentq'><div class='subNavw'>" + category + "</div>";
                    for (var key in data[i]) {
            if (key != "category") {
            str += "<ul class="navContentq">" +
                    "<li><a href="#">" + showData[key] + "<a href='fileexportcategory.htm?category=" + showData[key] + "' class='btn btn - danger'  style='float:right;'>" + 导出 + "</a>" +
                    "</a></li>" +
                    "</ul>";
            }
            }
            str += "</ul>";
            }
            $("#table").html(str);
            });
</script>-->

<body bgcolor="#ECECEC">
    <div class="mttop">
        <div class="capic"></div>
        <div class="calianjie"><a href="importot.html"  target="showframe">导入</a></div>
        <div style="float:left">|</div>
        <div style="float:left;color:red">导出</div>
        <form name="form1" method="post" action="" style="margin:auto; width:25%;">
            <label style="float:left; margin-right: 20%;"><a href="fileexport.htm" target="showframe">
                    <input type="radio" name="RadioGroup1" value="单选" checked="true" id="RadioGroup1_0">
                    按标准导出</a></label>
            <label style="margin:5px 1%;"><a href="exportforcategory.htm" target="showframe">
                    <input type="radio" name="RadioGroup1" value="单选" id="RadioGroup1_1">
                    按类别导出</a></label> 
        </form>
    </div>
    <div class="mtotm">
        <div class="catop">
            <a href="exportall.htm"><button type="button"  class="btn btn-danger">全部导出</button></a>
        </div>
        
        <div class="subNavBoxq" id="table">

        </div>
        
    </div>
</body>
</html>

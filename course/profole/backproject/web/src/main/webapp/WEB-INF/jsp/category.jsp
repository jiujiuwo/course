<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>
    <title>分类系统：Category </title>
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
                       str += "<ul><li><a href='findCategory.htm?category=" + showData[key] + "'>" + showData[key] + "</a></li></ul>";
                    }
                }
                str += "</div></div></tr>";
            }
            $("#table").html(str);
        });
</script>
<body>
    <h1>标准分类查询菜单 <small>BY 分类查询 </small></h1>
    <div id="head" align="right">
        <font size="2px">  <a href='categoryMana.htm'>标准类别管理</a></font>
    </div>
    <div id="table"> 
        
    </div>

    <div class="MenuBox2">
        <div class="titBox"><h3 class="Lst"><a href="homePage.htm">退出系统</a></h3></div>
    </div><!--MenuBox-->
</div>
</body>
</html>
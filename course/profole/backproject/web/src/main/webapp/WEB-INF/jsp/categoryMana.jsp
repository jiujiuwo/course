<%-- 
    Document   : categoryMana
    Created on : 2014-10-10, 10:30:20
    Author     : liaojm
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>
    <title>类别管理：CategoryManager </title>
</head>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var data =${jsonarray};
        var str = "<div float:center><h4>标准类别列表(按确定返回管理系统页面)</h4></div>";
        for (var i = 0; i < data.length; i++) {
            var showData = data[i];
            var category = showData.category;
           
            str += "<tr><td><input type='text' name='tag1' value='" + category + "' Readonly/></td>" +
                    "<td><a href='delCategory.htm?category=" + category + "'>删除总类别</a></td><td></td></tr>";
             for (var key in data[i]) {
                if (key != "category") {
                    str +=  "<tr><td><input type='text' name='tag1' value='" + showData[key] + "' Readonly/></td>"+
                    "<td><a href='delCategory.htm?category=" + showData[key] + "'>删除</a></td><td></td></tr>";
                }
            }
        }
        str += "<td><input type=button name=B1 value='增加类别信息' onClick='add();'></td>" +
                " <tr><td colspan='2'><input type='submit' value='修改' /> <input type='reset' value='重置' /></td></tr>";
        $("#table").html(str);
    });
</script>
<script>
    function add() {
        location.href = "addCategory.htm";
    }
</script>

<body>
    <form action="managerPage.htm" method="get" commandName="org">
        <div id="content">
            <center>
                <table  border="0" cellspacing="0" cellpadding="1" align="center" id="table">


                    <div style="text-align:center;">
                        <tr>
                            <td colspan="2"><input type="submit" value="修改" /> 
                                <input type="reset" value="重置" />
                            </td>
                        </tr>
                    </div>

                </table>
            </center>
        </div>
    </form>
</body>
</html>
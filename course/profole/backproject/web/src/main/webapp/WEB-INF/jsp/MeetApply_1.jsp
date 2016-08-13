<%-- 
    Document   : MeetApply
    Created on : 2015-2-5, 9:56:59
    Author     : liaojm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="meetapply.htm" method="post" ENCTYPE="multipart/form-data">
            <tr>
                <td>会议 名称</td>
                <td><input type="text" name="m_name" id="m_name"/></td>
            </tr>
            <tr>
                <td>会议开始时间</td>
                <td><input type="text" name="m_beginning" id="m_beginning"/></td>
            </tr>
            <tr>
                <td>会议结束时间</td>
                <td><input type="text" name="m_overtime" id="m_overtime"/></td>
            </tr>
            <tr>
                <td>会议地点</td>
                <td><input type="text" name="m_address" id="m_address"/></td>
            </tr>
            <tr>
                <td>会议状态</td>
                <td>
                    <select type="text" name="m_state" id="m_state">
                        <option>待召开</option>
                        <option>待总结</option>
                        <option>已总结</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>会议内容</td>
                <td><input type="text" name="m_info" id="m_info"/></td>
            </tr>
            <tr>
                <td>附件</td>
                <td><input name="file" type="file" /></td>
            </tr>
            <input type="submit"  style=" width:50px; height:30px; margin-bottom:20px;" class="btn btn-primary" value="保存" />
        </form>
    </body>
</html>

<%-- 
    Document   : testchoosepath
    Created on : 2014-10-30, 15:24:17
    Author     : liaojm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
.waikuang{
	width:500px;
	height:300px;
	position:absolute;	
	top:50%;	
	left:50%;
	margin:-150px 0 0 -250px;}
</style>
</head>
<script>
        function browseFolder(path) {
            try {
                var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939";  //选择框提示信息
                var Shell = new ActiveXObject("Shell.Application");
                var Folder = Shell.BrowseForFolder(0, Message, 64, 17);//起始目录为：我的电脑
                //var Folder = Shell.BrowseForFolder(0,Message,0); //起始目录为：桌面
                if (Folder != null) {
                    Folder = Folder.items();  // 返回 FolderItems 对象
                    Folder = Folder.item();  // 返回 Folderitem 对象
                    Folder = Folder.Path;   // 返回路径
                    if (Folder.charAt(Folder.length - 1) != "\\") {
                        Folder = Folder + "\\";
                    }
                    document.getElementById(path).value = Folder;
                    return Folder;
                }
            }
            catch (e) {
                alert(e.message);
            }
        }
    </script> 
<body>

<script language="javascript" type="text/javascript">
    function fn_browse()
    {
        document.test_form.browse.click();
        document.test_form.file.value = document.all.test_form.browse.value;        
        
    }
</script>
<div class="waikuang">
<form action="fileUpload.htm" name="test_form" id="getpath" enctype="multipart/form-data" style="width:300px; height:136px;position:absolute;	top:50%;	left:50%;	margin:-68px 0 0 -150px; " method="post" ENCTYPE="multipart/form-data" onsubmit="alert(document.test_form.browse.value);" >

<input type="file" name="browse" style="display:none;" />
<input type="text" name="file" style="border:0"/>
<a href="javascript:fn_browse();">

<!-- txtfilename和upfilename的size应该一样，完全遮盖 -->
 
    <span class="title" style="width: 80%;">   
      <img src="images/upload.png" width="137" height="136" align="absmiddle"  
        onclick="upfilename.click();" style="z-index: 999;" />  
    </span>
	<td>
    <input type="submit" name="submit" value="提交" >
    <input type="reset" name="reset" value="重置" >
   </td>
	</form>
</div>
</body>
</html>

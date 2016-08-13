<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/homePage.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="main">
	<div class="top">
    	<div class="topleft">
        	<ul style="list-style-type:none">
            	<a href=""><li>高级检索</li></a>
                <a href=""><li>分类检索</li></a>
                <a href=""><li>更新资源仓库</li></a>
            </ul>
        </div>
        <div class="topright">
        	<ul style="list-style-type:none">
            	<a href=""><li>admin</li></a>
                <a href=""><li>系统管理</li></a>
            	<a href="homepage.jsp.html"><li>退出</li></a>	
            </ul>
        </div>
    </div>
    <div class="admiddle">
    	<form action="AdvancedSearch.htm" method="post">
    	<div class="adzhong">
       	  <div class="adneic">
            	<div class="adneid">标准号：</div>
                <div class="adneio"><input class="adborder" name="stdNum" type="text" style="margin-left:10px;" /></div>
                <div class="adneid"></div>
                <div class="adneit"></div>
                <div class="adneid">中文标准名称：</div>
                <div class="adneio"><input class="adborder" name="stdChaName" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">英文标准名称：</div>
                <div class="adneit"><input class="adborder" name="stdEngName" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">发布日期：</div>
                <div class="adneio"><input class="adborder" name="releaseData" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">实施日期：</div>
                <div class="adneit"><input class="adborder" name="ImpleData" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">首次发布日期：</div>
                <div class="adneio"><input class="adborder" name="executeData" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">废止时间：</div>
                <div class="adneit"><input class="adborder" name="replayData" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">替代国标号：</div>
                <div class="adneio"><input class="adborder" name="countryLable" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">被替代国标号：</div>
                <div class="adneit"><input class="adborder" name="byCountryLable" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">采用国际标准号：</div>
                <div class="adneio"><input class="adborder" name="itaStdNum" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">采用国际标准名称：</div>
                <div class="adneit"><input class="adborder" name="byItaStdNum" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">采用程度：</div>
                <div class="adneio"><input class="adborder" name="byAdoption" type="text" style="margin-left:10px;" /></div>
                <div class="adneid"></div>
                <div class="adneit"></div>
                <div class="adneid">国际标准分类号：</div>
                <div class="adneio"><input class="adborder" name="Ics" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">中国标准分类号：</div>
                <div class="adneit"><input class="adborder" name="chaIcs" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">标准页码：</div>
                <div class="adneio"><input class="adborder" name="stdPag" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">标准属性：</div>
                <div class="adneit"><input class="adborder" name="stdAttribute" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">主管部门：</div>
                <div class="adneio"><input class="adborder" name="keyDepart" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">归属单位：</div>
                <div class="adneit"><input class="adborder" name="center" type="text" style="margin-left:10px;" /></div>
                <div class="adneid">起草单位：</div>
                <div class="adneio"><input class="adborder" name="draftCenter" type="text" style="margin-left:10px;" /></div>
                <div class="adneid"></div>
                <div class="adneit"></div>
            </div>
            <div class="jiansuo">
               <input type="submit" name="button" id="button" value="检索" />
            </div>
        </div>
        </form>
    </div>
    <div class="bottom">
          <div class="bottomcss">2014 科迪智标 京ICP证000000号</div>
    </div>
</div>
</body>
</html>

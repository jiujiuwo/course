<%-- 
    Document   : OverSearch
    Created on : 2014-5-21, 9:48:22
    Author     : liubin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/homePage.css" rel="stylesheet" type="text/css" />
</head>
<script src="js/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript">

            function dosearch(url) {
                window.parent.location.href = url;
            }

    $(document).ready(function() {        
                var data =${jsonarray};

                var showData = data[0];
                $("#stdNum").html(showData.stdNum);
                $("#stdChaName").html(showData.stdChaName);
                $("#stdEngName").html(showData.stdEngName);
                $("#releaseData").html(showData.releaseData);
                $("#ImpleData").html(showData.impleData);
                $("#executeData").html(showData.executeData);
                $("#countryLable").html(showData.countryLable);
                $("#byCountryLable").html(showData.byCountryLable);
                $("#replayData").html(showData.replayData);
                
                $("#itaStdNum").html("<a href='#' onclick=\"dosearch('FastAdvancedSearch.htm?itastdNum=" + showData.itastdNum + "')\">" + showData.byItaStdName + "</a>");
                $("#byItaStdNum").html("<a href='#' onclick=\"dosearch('FastAdvancedSearch.htm?byItaStdName=" + showData.byItaStdName + "')\">" + showData.byItaStdName + "</a>");
                $("#byAdoption").html(showData.byAdoption);
                $("#Ics").html(showData.Ics);
                $("#stdPag").html(showData.stdPag);
                $("#keyDepart").html("<a href='#' onclick=\"dosearch('FastAdvancedSearch.htm?keyDepart=" + showData.keyDepart + "')\">" + showData.keyDepart + "</a>");
                $("#center").html("<a href='#' onclick=\"dosearch('FastAdvancedSearch.htm?center=" + showData.center + "')\">" + showData.center + "</a>");
                $("#stdAttribute").html("<a href='#' onclick=\"dosearch('FastAdvancedSearch.htm?stdAttribute=" + showData.stdAttribute + "')\">" + showData.stdAttribute + "</a>");
                var tet = showData.draftCenter.toString();
                var temp = tet.split(",");
                var draftCenter = '';
                var m = 1;
                for (var n = 0; n < temp.length; n++) {
                    if (m % 4 == 0) {
                        draftCenter = draftCenter + "<a href='#' onclick=\"dosearch('FastAdvancedSearch.htm?draftCenter=" + temp[n] + "')\">" + temp[n] + "</a></br>";

                    } else {
                        draftCenter = draftCenter + "<a href='#' onclick=\"dosearch('FastAdvancedSearch.htm?draftCenter=" + temp[n] + "')\">" + temp[n] + "</a>&nbsp;&nbsp;&nbsp;";

                    }
                    m++;
                }
                $("#draftCenter").html(draftCenter);
                var category=showData.category.toString();
                $("#category").html(category);
            });
        </script>
<body>
<div class="main">
	<form action="AdvancedSearch.htm" method="post">
        <div class="biaoge">
                <div class="biaogec">
                        <div class="adneid">标准号：</div>
                        <div class="adneio">
                            <span   name="stdNum" id="stdNum" style="margin-left:10px;"></span>
                        </div>
                        <div class="adneid"></div>
                        <div class="adneit"></div>
                        <div class="adneid">中文标准名称：</div>
                        <div class="adneio">
                            <span   name="stdChaName" id="stdChaName" style="margin-left:10px;">
                        </div>
                        <div class="adneid">英文标准名称：</div>
                        <div class="adneit">
                            <span   name="stdEngName" id="stdEngName" style="margin-left:10px;">
                        </div>
                        <div class="adneid">标准所属类别：</div>
                        <div class="adneio">
                            <span   name="category" id="category" style="margin-left:10px;">
                        </div>
                        <div class="adneid"></div>
                        <div class="adneit"></div>
                        <div class="adneid">发布日期：</div>
                        <div class="adneio">
                             <span   name="releaseData" id="releaseData" style="margin-left:10px;">
                        </div>
                        <div class="adneid">实施日期：</div>
                        <div class="adneit">
                              <span   name="ImpleData" id="ImpleData" style="margin-left:10px;">
                        </div>
                        <div class="adneid">首次发布日期：</div>
                        <div class="adneio">
                            <span   name="executeData" id="executeData" style="margin-left:10px;">
                        </div>
                        <div class="adneid">废止时间：</div>
                        <div class="adneit">
                            <span   name="replayData" id="replayData" style="margin-left:10px;">
                        </div>
                        <div class="adneid">替代国标号：</div>
                        <div class="adneio">
                            <span   name="countryLable" id="countryLable" style="margin-left:10px;">
                        </div>
                        <div class="adneid">被替代国标号：</div>
                        <div class="adneit">
                            <span   name="byCountryLable" id="byCountryLable" style="margin-left:10px;">
                        </div>
                        <div class="adneid">采用国际标准号：</div>
                        <div class="adneio">
                             <span   name="itaStdNum" id="itaStdNum" style="margin-left:10px;">
                        </div>
                        <div class="adneid">采用国际标准名称：</div>
                        <div class="adneit">
                             <span   name="byItaStdNum" id="byItaStdNum" style="margin-left:10px;">
                        </div>
                        <div class="adneid">采用程度：</div>
                        <div class="adneio">
                            <span   name="byAdoption" id="byAdoption" style="margin-left:10px;">
                        </div>
                        <div class="adneid"></div>
                        <div class="adneit"></div>
                        <div class="adneid">国际标准分类号：</div>
                        <div class="adneio">
                            <span   name="Ics" id="Ics" style="margin-left:10px;">
                        </div>
                        <div class="adneid">中国标准分类号：</div>
                        <div class="adneit">
                            <span   name="chaIcs" id="chaIcs" style="margin-left:10px;">
                        </div>
                        <div class="adneid">标准页码：</div>
                        <div class="adneio">
                            <span   name="stdPag" id="stdPag" style="margin-left:10px;">
                        </div>
                        <div class="adneid">标准属性：</div>
                        <div class="adneit">
                            <span   name="stdAttribute" id="stdAttribute" style="margin-left:10px;">
                        </div>
                        <div class="adneid">主管部门：</div>
                        <div class="adneio">
                            <span   name="keyDepart" id="keyDepart" style="margin-left:10px;">
                        </div>
                        <div class="adneid">归属单位：</div>
                        <div class="adneit">
                             <span   name="center" id="center" style="margin-left:10px;">
                        </div>
                        <div class="adneid">起草单位：</div>
                        <div class="changceng">
                           <span  name="draftCenter" id="draftCenter" style="margin-left:10px;">
                        </div>
                        <div class="adneid"></div>
                        <div class="adneit"></div>
              </div>
            </div>
       </form>
    </div>
</div>
</body>
</html>

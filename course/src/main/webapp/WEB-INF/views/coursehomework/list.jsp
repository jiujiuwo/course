<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@page import="java.util.*,java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "courseexperiment");
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>课程系统</title>
<base href="<%=basePath%>" />

<%@ include file="../../shared/importCss.jsp"%>
<%@ include file="../../shared/importdatetimepickercss.jsp"%>


<link href="<c:url value='/css/pages/index.css'/>" rel="stylesheet"
	type="text/css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="home" onLoad="ShowErrMsg()">

	<%@ include file="../../shared/pageHeader.jsp"%>
	<%@ include file="../../shared/CourseTeachingClassInfo.jsp"%>
	<div id="DocumentPageTopSeparatorLine"></div>

	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="../CourseContentNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">



			<ol class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li><a href="#">Library</a></li>
				<li class="active">Data</li>
			</ol>

			<div class="CourseContentHeader">${selectedCourseHomeworkTypeData.name}管理</div>

			<div class="CourseContentHeaderSeparatorLine"></div>



			<table>
				<tr>
					<td><div class="btn-group" role="group" aria-label="...">
							<!-- Split button -->
							<div class="btn-group">
								<button type="button" class="btn btn-default btn-sm">选择</button>
								<button type="button"
									class="btn btn-default dropdown-toggle btn-sm"
									data-toggle="dropdown" aria-expanded="false">
									<span class="caret"></span> <span class="sr-only">Toggle
										Dropdown</span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">全部选择</a></li>
									<li><a href="#">全部不选</a></li>
									<li class="divider"></li>
									<li><a href="#">反选</a></li>
								</ul>
							</div>
						</div></td>
					<td style="width: 10px;"></td>
					<td><div class="btn-group" role="group" aria-label="...">
							<button type="button" class="btn btn-default btn-sm"
								data-toggle="modal" data-target="#addModal">增加</button>

							<button type="button" class="btn btn-default btn-sm"
								data-toggle="modal" data-target="#myModal">删除</button>


						</div></td>
					<td style="width: 10px;"></td>
					<td><div class="input-group input-group-sm">
							<input type="text" class="form-control" id="SearchText"
								placeholder="Search for..." required> <span
								class="input-group-btn">
								<button class="btn btn-default" type="button"
									onclick="onSearch()">搜索</button>
							</span>
						</div></td>
				</tr>
			</table>



			<c:choose>

				<c:when
					test="${pagedCourseTeachingClassHomeworkBaseinfoViewData.totalCount==0}">

					<div class="alert alert-warning alert-dismissible fade in"
						role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>暂未有${selectedCourseHomeworkTypeData.name}作业</strong>
						<p class="text-info">请为<em>${selectedCourseHomeworkTypeData.name}</em>布置作业</p>
					</div>

				</c:when>

				<c:otherwise>
				

			<%
				//显示具体内容
			%>

			<div class="Course-Table">

				<div class="container-fluid" style="overflow: hidden;">
					<div class="gridseparator"></div>
					<div class="row show-grid">
						<div class="col-md-1">
							<strong>#</strong>
						</div>
						<div class="col-md-2">
							<strong>标题</strong>
						</div>

						<div class="col-md-2">
							<strong>附件</strong>
						</div>

						<div class="col-md-3">
							<strong>提交文件要求</strong>
						</div>

						<div class="col-md-1">
							<strong>发布日期</strong>
						</div>

						<div class="col-md-1">
							<strong>截止日期</strong>
						</div>

						<div class="col-md-1">
							<strong>操作</strong>
						</div>

						<div class="col-md-1">
							<strong>查看与批复</strong>
						</div>
					</div>

					<div class="gridseparator"></div>


					<c:set var="index" value="0"></c:set>
					<c:forEach var="data"
						items="${pagedCourseTeachingClassHomeworkBaseinfoViewData.result}">
						<div class="row show-grid">
							<div class="col-md-1">
								<input type="checkbox" value="">
								${pagedCourseTeachingClassHomeworkBaseinfoViewData.totalCount-(pagedCourseTeachingClassHomeworkBaseinfoViewData.currentPageNo-1) * pagedCourseTeachingClassHomeworkBaseinfoViewData.pageSize -index}
							</div>


							<div class="col-md-2">
								<strong>${data.homeworkbaseinfo.title}</strong>
								<p>${data.homeworkbaseinfo.content}</p>
							</div>

							<div class="col-md-2">



								<c:choose>

									<c:when test="${fn:length(data.homeworkFileList)==1}">
										<a
											href="<c:url value="coursehomeworkfile/download-${data.homeworkFileList[0].id}.html"/>">
											${data.homeworkFileList[0].filename}</a>
									</c:when>

									<c:otherwise>
										<ul>
											<c:forEach var="datafile" items="${data.homeworkFileList}">
												<li><a
													href="<c:url value="coursehomeworkfile/download-${datafile.id}.html"/>">
														${datafile.filename}</a></li>
											</c:forEach>
										</ul>
									</c:otherwise>
								</c:choose>



							</div>


							<div class="col-md-3">

								<c:choose>

									<c:when test="${data.homeworkbaseinfo.filecount==0}">
										不需要提交文件
									</c:when>
									<c:when test="${data.homeworkbaseinfo.filecount==-1}">
										提交多个文件（数目不限制）<br>文件类型:${data.homeworkbaseinfo.filetype}<br>文件名称格式:${data.homeworkbaseinfo.filenameformat}
									</c:when>

									<c:otherwise>
										提交${data.homeworkbaseinfo.filecount}个文件<br>文件类型:${data.homeworkbaseinfo.filetype}<br>文件名称格式:${data.homeworkbaseinfo.filenameformat}		
									</c:otherwise>
								</c:choose>


							</div>


							<div class="col-md-1">
								<fmt:formatDate value="${data.homeworkbaseinfo.pubdate}"
									pattern="yyyy-MM-dd HH:mm" />
							</div>

							<div class="col-md-1">
								<fmt:formatDate value="${data.homeworkbaseinfo.enddate}"
									pattern="yyyy-MM-dd HH:mm" />
							</div>




							<div class="col-md-1">
								<button type="button" class="btn btn-default btn-xs"
									onclick="onUpdateSimple('${data.homeworkbaseinfo.id}','${data.homeworkbaseinfo.title}','${data.homeworkbaseinfo.content}','${data.homeworkbaseinfo.enddate}')">简单修改...</button>
									
								<button type="button" class="btn btn-default btn-xs"
									onclick="onUpdate('${data.homeworkbaseinfo.id}','${data.homeworkbaseinfo.title}','${data.homeworkbaseinfo.content}','${data.homeworkbaseinfo.enddate}')">修改...</button>
								<button type="button" class="btn btn-default btn-xs"
									onclick="onDelete('${data.homeworkbaseinfo.id}')">删除</button>
							</div>

							<div class="col-md-1">
								<c:if test="${data.homeworkbaseinfo.canStudentSubmit==true }">
									<button type="button" class="btn btn-default btn-xs"
										onclick="location='<c:url value="/coursehomework/replylist-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.id}.html"/>'">查看</button>
								</c:if>
								
								<c:if test="${data.homeworkbaseinfo.canReply==true }">
									<button type="button" class="btn btn-default btn-xs"
										onclick="location='<c:url value="/coursehomework/statistics-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.id}.html"/>'">统计</button>
									<button type="button" class="btn btn-default btn-xs"
										onclick="location='<c:url value="/coursehomework/replylist-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.id}.html"/>'">批复</button>
								</c:if>
							</div>
						</div>
						<c:set var="index" value="${index + 1}"></c:set>
					</c:forEach>

					<c:if
						test="${pagedCourseTeachingClassHomeworkBaseinfoViewData.totalCount>0}">
						<div class="gridseparator"></div>
					</c:if>

				</div>




				<mathtop:PageBar
					pageUrl="/coursehomework/add-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html"
					pageAttrKey="pagedCourseTeachingClassHomeworkBaseinfoViewData" />

			</div>
			
			</c:otherwise>
											</c:choose>

		</div>
	</div>


	<!-- 添加对话框 -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加${selectedCourseHomeworkTypeData.name}要求</h4>
				</div>



				<form class="form-horizontal "
					action="<c:url value="/coursehomework/add-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html"/>"
					enctype="multipart/form-data" method="post">

					<div class="modal-body"
						style="margin-left: 10px; margin-right: 10px;">

						<div class="form-group">
							<label for="name" class=" control-label">标题</label> <input
								type="text" id="name" class="form-control" name="title" value=""
								placeholder="标题" required>
						</div>


						<div class="form-group">
							<label for="note" class=" control-label">内容</label>
							<textarea class="form-control" rows="5" name="content"></textarea>
						</div>

						<div class="form-group">
							<label for="name" class=" control-label">文件数目</label> <input
								type="radio" name="filecount" value="0"
								onclick="onfilecountradio(0)" />0个 <input type="radio"
								name="filecount" value="1" checked="checked"
								onclick="onfilecountradio(1)" />1个 <input type="radio"
								name="filecount" value="2" onclick="onfilecountradio(2)" />2个 <input
								type="radio" name="filecount" value="3"
								onclick="onfilecountradio(3)" />3个 <input type="radio"
								name="filecount" value="4" onclick="onfilecountradio(4)" />4个 <input
								type="radio" name="filecount" value="5"
								onclick="onfilecountradio(5)" />5个 <input type="radio"
								name="filecount" value="6" onclick="onfilecountradio(6)" />6个 <input
								type="radio" name="filecount" value="7"
								onclick="onfilecountradio(7)" />7个 <input type="radio"
								name="filecount" value="8" onclick="onfilecountradio(8)" />8个 <input
								type="radio" name="filecount" value="9"
								onclick="onfilecountradio(9)" />9个 <input type="radio"
								name="filecount" value="-1" onclick="onfilecountradio(-1)" />不限制个数
						</div>


						<div class="form-group" id="filetypegroup">
							<label for="name" class=" control-label">文件类型</label> <input
								type="radio" name="filetyperadio" checked="checked" value="0"
								onclick="onfiletyperadio(0)" />具体文件类型 <input type="radio"
								value="1" name="filetyperadio" onclick="onfiletyperadio(1)" />不限制文件类型



							<div class="panel panel-default" id="filetypepanel0">
								<div class="panel-heading">${selectedCourseHomeworkTypeData.name}文件类型</div>
								<div class="panel-body" style="margin: 10px;">
									<input type=checkbox name="filetype" value="*.doc;*.docx"
										checked="checked">word文件 <input type=checkbox
										name="filetype" value="*.xls;*.xlsx">excel文件 <input
										type=checkbox name="filetype" value="*.ppt;*.pptx">ppt文件
									<input type=checkbox name="filetype" value="*.txt">文本文件(txt)
									<input type=checkbox name="filetype" value="*.pdf">pdf文件(pdf)
									<input type=checkbox name="filetype" value="*.c;*.h">c文件
									<input type=checkbox name="filetype"
										value="*.cpp;*.c;*.h;*.cc;*.hh;*.hpp;*.hh">c++文件 <input
										type=checkbox name="filetype" value="*.java">java文件
									<p />
									<label for="filetypecustom" class=" control-label">自定义类型(类型之间用分号隔开，例如“*.txt;*.dat”)</label>
									<input type="text" id="filetypecustom" class="form-control"
										name="filetypecustom" value="" placeholder="自定义类型">
								</div>
							</div>


							<div class="panel panel-default" id="filetypepanel1"
								style="display: none;">
								<div class="panel-heading">不限制${selectedCourseHomeworkTypeData.name}附件类型</div>
								<div class="panel-body">${selectedCourseHomeworkTypeData.name}附件可以为任意类型。</div>
							</div>




						</div>

						<div class="form-group" id="filenameformatgroup">
							<label for="filenameformatradio" class=" control-label">文件名称格式</label>

							<input type="radio" name="filenameformatradio" checked="checked"
								value="0" onclick="onfilenameformatradio(0)" />预定义格式 <input
								type="radio" value="1" name="filenameformatradio"
								onclick="onfilenameformatradio(1)" />自定义格式



							<div class="panel panel-default" id="filenameformatpanel0">
								<div class="panel-heading">预定义格式</div>
								<div class="panel-body">
									<input type="radio" name="filenameformat"
										value="{作业类型}_{作业名称}_{自然班}_{学号}_{姓名}" checked="checked" />${selectedCourseHomeworkTypeData.name}_实验名称_班级_学号_姓名
									<br> <input type="radio" name="filenameformat"
										value="{作业类型}_{作业名称}_{学号}_{姓名}" />${selectedCourseHomeworkTypeData.name}_实验名称_学号_姓名
									<br> <input type="radio" name="filenameformat"
										value="{作业类型}_{自然班}_{学号}_{姓名}" />${selectedCourseHomeworkTypeData.name}_班级_学号_姓名
									<br> <input type="radio" name="filenameformat"
										value="{作业名称}_{自然班}_{学号}_{姓名}" />实验名称_班级_学号_姓名
								</div>
							</div>



							<div class="panel panel-default" id="filenameformatpanel1"
								style="display: none;">
								<div class="panel-heading">自定义格式</div>
								<div class="panel-body">
									<p>"{}"为格式控制符。含义如下：</p>

									<dl>
										<dt>{学院}</dt>
										<dd>学生所在的学院</dd>
										<dt>{系别}</dt>
										<dd>学生所在的系别</dd>
										<dt>{课程名}</dt>
										<dd>课程名称</dd>
										<dt>{教学学年}</dt>
										<dd>教学学年，例如：2015-2016学年</dd>
										<dt>{教学学期}</dt>
										<dd>教学学期，例如：第1学期</dd>
										<dt>{教学班授课教师}</dt>
										<dd></dd>
										<dt>{教学班}</dt>
										<dd>学生所在的教学班</dd>

										<dt>{自然班}</dt>
										<dd>学生所在的自然班（行政班）</dd>
										<dt>{学号}</dt>
										<dd>学生的学号</dd>
										<dt>{姓名}</dt>
										<dd>学生的姓名</dd>
										<dt>{作业类型}</dt>
										<dd>本次作业的类型（${selectedCourseHomeworkTypeData.name}）</dd>

										<dt>{作业名称}</dt>
										<dd>本次作业的名称</dd>

										<dt>{数字}</dt>
										<dd>数字，从0开始的整数，不能是负数或小数。</dd>

										<dt>{?}</dt>
										<dd>一个字符</dd>

										<dt>{*}</dt>
										<dd>任意长度的字符</dd>
									</dl>

									<input type="text" id="filenameformatcustom"
										class="form-control" name="filenameformatcustom" value=""
										placeholder="标题">
								</div>
							</div>


						</div>


						<%!Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	String getNewDate() {
		Date d = new Date();
		d = getDateAfter(d, 15);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = format.format(d);
		return dateString;
	}%>

						<div class="form-group">
							<label for="enddate" class="control-label">截止时间</label>

							<div class="input-group date form_date col-sm-5" data-date=""
								data-date-format="yyyy-mm-dd hh:ii "
								data-link-field="dtp_input2" data-link-format="yyyy-mm-dd hh:ii">
								<input class="form-control" size="16" type="text" id="enddate"
									value="<%=getNewDate()%>" name="enddate" readonly required>
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>


						</div>



						<div class="form-group">

							<label for="exampleInputFile" class=" control-label">附件</label>
							<p class="help-block">任意文件类型均可。</p>
							<input type="file" multiple="multiple" id="exampleInputFile"
								name="file" />

						</div>


					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">添加</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</form>

			</div>
		</div>
	</div>


	<!-- 修改对话框 -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改${selectedCourseHomeworkTypeData.name}要求</h4>
				</div>



				<form class="form-horizontal" id="myUpdateForm" name="myUpdateForm"
					enctype="multipart/form-data" method="post">

					<div class="modal-body"
						style="margin-left: 10px; margin-right: 10px;">

						<div class="form-group">
							<label for="name" class=" control-label">标题</label> <input
								type="text" id="inputtitle" class="form-control"
								name="updatetitle" value="" placeholder="标题" required>
						</div>


						<div class="form-group">
							<label for="note" class=" control-label">内容</label>
							<textarea class="form-control" id="inputcontent" rows="5"
								name="updatecontent"></textarea>
						</div>

						<div class="form-group">
							<label for="name" class=" control-label">文件数目</label> <input
								type="radio" name="filecount" value="0"
								onclick="onfilecountradio(0)" />0个 <input type="radio"
								name="filecount" value="1" checked="checked"
								onclick="onfilecountradio(1)" />1个 <input type="radio"
								name="filecount" value="2" onclick="onfilecountradio(2)" />2个 <input
								type="radio" name="filecount" value="3"
								onclick="onfilecountradio(3)" />3个 <input type="radio"
								name="filecount" value="4" onclick="onfilecountradio(4)" />4个 <input
								type="radio" name="filecount" value="5"
								onclick="onfilecountradio(5)" />5个 <input type="radio"
								name="filecount" value="6" onclick="onfilecountradio(6)" />6个 <input
								type="radio" name="filecount" value="7"
								onclick="onfilecountradio(7)" />7个 <input type="radio"
								name="filecount" value="8" onclick="onfilecountradio(8)" />8个 <input
								type="radio" name="filecount" value="9"
								onclick="onfilecountradio(9)" />9个 <input type="radio"
								name="filecount" value="-1" onclick="onfilecountradio(-1)" />不限制个数
						</div>


						<div class="form-group" id="filetypegroup">
							<label for="name" class=" control-label">文件类型</label> <input
								type="radio" name="filetyperadio" checked="checked" value="0"
								onclick="onfiletyperadio(0)" />具体文件类型 <input type="radio"
								value="1" name="filetyperadio" onclick="onfiletyperadio(1)" />不限制文件类型



							<div class="panel panel-default" id="filetypepanel0">
								<div class="panel-heading">${selectedCourseHomeworkTypeData.name}文件类型</div>
								<div class="panel-body" style="margin: 10px;">
									<input type=checkbox name="filetype" value="*.doc;*.docx"
										checked="checked">word文件 <input type=checkbox
										name="filetype" value="*.xls;*.xlsx">excel文件 <input
										type=checkbox name="filetype" value="*.ppt;*.pptx">ppt文件
									<input type=checkbox name="filetype" value="*.txt">文本文件(txt)
									<input type=checkbox name="filetype" value="*.pdf">pdf文件(pdf)
									<input type=checkbox name="filetype" value="*.c;*.h">c文件
									<input type=checkbox name="filetype"
										value="*.cpp;*.c;*.h;*.cc;*.hh;*.hpp;*.hh">c++文件 <input
										type=checkbox name="filetype" value="*.java">java文件
									<p />
									<label for="filetypecustom" class=" control-label">自定义类型(类型之间用分号隔开，例如“*.txt;*.dat”)</label>
									<input type="text" id="filetypecustom" class="form-control"
										name="filetypecustom" value="" placeholder="自定义类型">
								</div>
							</div>


							<div class="panel panel-default" id="filetypepanel1"
								style="display: none;">
								<div class="panel-heading">不限制${selectedCourseHomeworkTypeData.name}附件类型</div>
								<div class="panel-body">${selectedCourseHomeworkTypeData.name}附件可以为任意类型。</div>
							</div>




						</div>

						<div class="form-group" id="filenameformatgroup">
							<label for="filenameformatradio" class=" control-label">文件名称格式</label>

							<input type="radio" name="filenameformatradio" checked="checked"
								value="0" onclick="onfilenameformatradio(0)" />预定义格式 <input
								type="radio" value="1" name="filenameformatradio"
								onclick="onfilenameformatradio(1)" />自定义格式



							<div class="panel panel-default" id="filenameformatpanel0">
								<div class="panel-heading">预定义格式</div>
								<div class="panel-body">
									<input type="radio" name="filenameformat"
										value="{作业类型}_{作业名称}_{自然班}_{学号}_{姓名}" checked="checked" />${selectedCourseHomeworkTypeData.name}_实验名称_班级_学号_姓名
									<br> <input type="radio" name="filenameformat"
										value="{作业类型}_{作业名称}_{学号}_{姓名}" />${selectedCourseHomeworkTypeData.name}_实验名称_学号_姓名
									<br> <input type="radio" name="filenameformat"
										value="{作业类型}_{自然班}_{学号}_{姓名}" />${selectedCourseHomeworkTypeData.name}_班级_学号_姓名
									<br> <input type="radio" name="filenameformat"
										value="{作业名称}_{自然班}_{学号}_{姓名}" />实验名称_班级_学号_姓名
								</div>
							</div>



							<div class="panel panel-default" id="filenameformatpanel1"
								style="display: none;">
								<div class="panel-heading">自定义格式</div>
								<div class="panel-body">
									<p>"{}"为格式控制符。含义如下：</p>

									<dl>
										<dt>{学院}</dt>
										<dd>学生所在的学院</dd>
										<dt>{系别}</dt>
										<dd>学生所在的系别</dd>
										<dt>{课程名}</dt>
										<dd>课程名称</dd>
										<dt>{教学学年}</dt>
										<dd>教学学年，例如：2015-2016学年</dd>
										<dt>{教学学期}</dt>
										<dd>教学学期，例如：第1学期</dd>
										<dt>{教学班授课教师}</dt>
										<dd></dd>
										<dt>{教学班}</dt>
										<dd>学生所在的教学班</dd>

										<dt>{自然班}</dt>
										<dd>学生所在的自然班（行政班）</dd>
										<dt>{学号}</dt>
										<dd>学生的学号</dd>
										<dt>{姓名}</dt>
										<dd>学生的姓名</dd>
										<dt>{作业类型}</dt>
										<dd>本次作业的类型（${selectedCourseHomeworkTypeData.name}）</dd>

										<dt>{作业名称}</dt>
										<dd>本次作业的名称</dd>

										<dt>{数字}</dt>
										<dd>数字，从0开始的整数，不能是负数或小数。</dd>

										<dt>{?}</dt>
										<dd>一个字符</dd>

										<dt>{*}</dt>
										<dd>任意长度的字符</dd>
									</dl>

									<input type="text" id="filenameformatcustom"
										class="form-control" name="filenameformatcustom" value=""
										placeholder="标题">
								</div>
							</div>


						</div>




						<div class="form-group">
							<label for="enddate" class="control-label">截止时间</label>

							<div class="input-group date form_date col-sm-5" data-date=""
								data-date-format="yyyy-mm-dd hh:ii "
								data-link-field="dtp_input2" data-link-format="yyyy-mm-dd hh:ii">
								<input class="form-control" size="16" type="text" id="enddate"
									value="<%=getNewDate()%>" name="enddate" readonly required>
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>


						</div>



						<div class="form-group">

							<label for="exampleInputFile" class=" control-label">附件</label>
							<p class="help-block">任意文件类型均可。</p>
							<input type="file" multiple="multiple" id="exampleInputFile"
								name="file" />

						</div>


					</div>

					<div class="modal-footer">
						<button type="submit" id="updatebtn" class="btn btn-primary">修改</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</form>

			</div>
		</div>
	</div>
	
	
	<!-- 简单修改对话框 -->
	<div class="modal fade" id="updateSimpleModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改${selectedCourseHomeworkTypeData.name}要求</h4>
				</div>



				<form class="form-horizontal" id="updateSimpleModalForm" name="updateSimpleModalForm"
					enctype="multipart/form-data" method="post">

					<div class="modal-body"
						style="margin-left: 10px; margin-right: 10px;">

						<div class="form-group">
							<label for="name" class=" control-label">标题</label> <input
								type="text" id="inputtitle" class="form-control"
								name="updatetitle" value="" placeholder="标题" required>
						</div>


						<div class="form-group">
							<label for="note" class=" control-label">内容</label>
							<textarea class="form-control" id="inputcontent" rows="5"
								name="updatecontent"></textarea>
						</div>
			
						<div class="form-group">
							<label for="enddate" class="control-label">截止时间</label>

							<div class="input-group date form_date col-sm-5" data-date=""
								data-date-format="yyyy-mm-dd hh:ii "
								data-link-field="dtp_input2" data-link-format="yyyy-mm-dd hh:ii">
								<input class="form-control" size="16" type="text" id="enddate"
									value="<%=getNewDate()%>" name="enddate" readonly required>
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>

						</div>						

					</div>

					<div class="modal-footer">
						<button type="submit" id="updatebtn" class="btn btn-primary">修改</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</form>

			</div>
		</div>
	</div>


	<%@ include file="../../shared/dialog.jsp"%>

	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>


	<script type="text/javascript">
		$("#collapseHomework").addClass("in");
		$("#homeworktype${selectedCourseHomeworkTypeData.id}").addClass(
				"active");
	</script>




	<script>
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 0,
			forceParse : 0
		});
	</script>

	<script>
		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onDelete(id) {
			var url = "location='coursehomework/DELETE-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"
					+ id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}

		function onUpdate(id, title, content, enddate) {
			var url = "coursehomework/update-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"
					+ id + ".html";
			$('#updateModal').find('.modal-body #inputid').val(id);
			$('#updateModal').find('.modal-body #inputtitle').val(title);
			$('#updateModal').find('.modal-body #inputcontent').val(content);
			$('#updateModal').find('.modal-body #inputenddate').val(enddate);

			$("#myUpdateForm").attr("action", url);

			$('#updateModal').modal('show');

		}
		
		function onUpdateSimple(id, title, content, enddate) {
			var url = "coursehomework/updatesimple-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"
					+ id + ".html";
			$('#updateSimpleModal').find('.modal-body #inputid').val(id);
			$('#updateSimpleModal').find('.modal-body #inputtitle').val(title);
			$('#updateSimpleModal').find('.modal-body #inputcontent').val(content);
			$('#updateSimpleModal').find('.modal-body #inputenddate').val(enddate);

			$("#updateSimpleModalForm").attr("action", url);

			$('#updateSimpleModal').modal('show');

		}
		
		
		function onfiletyperadio(type){
			if(type==0){
				//具体类型
				$('#filetypepanel0').show();
				$('#filetypepanel1').hide();
			}
			else{
				//不限制类型
				$('#filetypepanel1').show();
				$('#filetypepanel0').hide();
			}
		}
		
		function onfilenameformatradio(type){
			if(type==0){
				//具体类型
				$('#filenameformatpanel0').show();
				$('#filenameformatpanel1').hide();
			}
			else{
				//自定义类型
				$('#filenameformatpanel1').show();
				$('#filenameformatpanel0').hide();
			}
		}
		
		function onfilecountradio(type){
			if(type==0){
				//具体类型
				$('#filetypegroup').hide();
				$('#filenameformatgroup').hide();
			}
			else{
				//自定义类型
				$('#filetypegroup').show();
				$('#filenameformatgroup').show();
			}
		}
	</script>





	<c:if test="${!empty errorMsg}">
		<script>
			function ShowErrMsg() {
				ShowInfoMsg("${errorMsg}");

			}
		</script>

	</c:if>

	<c:set var="errorMsg" value="null" />

</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@page import="java.util.*,java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
<%@ include file="../../shared/importJs.jsp"%>


<link href="<c:url value='/css/pages/index.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/plugins/summernote-master/dist/summernote.css'/>" rel="stylesheet"
	type="text/css" />
<script src="<c:url value='/plugins/summernote-master/dist/summernote.js'/>"></script>
<script src="<c:url value='/plugins/summernote-master/lang/summernote-zh-CN.js'/>"></script>



<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<%@ include file="../../shared/pageHeader.jsp"%>
	<%@ include file="../../shared/CourseTeachingClassInfo.jsp"%>


	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="../CourseContentNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">



			<ol class="breadcrumb">
				<li><a href="#">课程系统</a></li>
				<li><a
						href="<c:url value="/coursecontent/index-${selectedCourseTeachingClassViewData.courseTeachingClass.id}.html"/>">${selectedCourseTeachingClassViewData.course.name}</a></li>
				<li class="active">${selectedCourseHomeworkTypeData.name}</li>
			</ol>

			<div class="CourseContentHeader">修改${selectedCourseTeachingClassViewData.courseTeachingClass.name}-${selectedCourseHomeworkTypeData.name}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}</div>

			<div class="CourseContentHeaderSeparatorLine"></div>




			<form class="form-horizontal" id="myUpdateForm" name="myUpdateForm"
				action="<c:url value="/coursehomework/update-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}.html"/>"
				enctype="multipart/form-data" method="post">

				<!-- 作业要求json -->
				<input type="hidden" name="FileRequirementData" id="FileRequirementData" value="" />

			
				

				<div class="modal-body" style="margin-left: 10px; margin-right: 10px; overflow: hidden;">

					<div class="form-group">
						<label for="title" class=" control-label">标题</label>
						<input type="text" id="title" class="form-control" name="title" value=""
							placeholder="标题" required>
					</div>


					<div class="form-group">
						<label for="content" class="control-label">内容</label>
						<div class="summernote"></div>
						<textarea class="form-control" rows="5" name="content" id="addcontentTextArea"
							style="display: none;"></textarea>
					</div>

					<div class="form-group">
						<label for="homeworkflag" class="control-label">作业类型</label>
						<input type="radio" name="flag"
							<c:if test="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.flagGroup }"> checked="checked"</c:if>
							value="1" />
						小组作业
						<input type="radio" value="0" name="flag"
							<c:if test="${not selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.flagGroup }"> checked="checked"</c:if> />
						个人作业

					</div>




					<div class="form-group">
						<label for="filerequirement" class=" control-label">文件要求</label>
						<div>
							<input type="radio" name="filerequirement_count" value="0" checked="checked"
								onclick="onfilecountradio(0)" />
							上传文件
							<input type="radio" name="filerequirement_count" value="1" onclick="onfilecountradio(1)" />
							不上传文件
						</div>
						<div id="filerequirement_container">

							<div class="panel panel-default" id="filetypepanel0">
								<div class="panel-heading">${selectedCourseHomeworkTypeData.name}文件要求
									<button type="button" class="btn btn-default btn-xs" onclick="onAddRootNode()">增加作业要求</button>

								</div>
								<div class="panel-body" style="margin: 10px;">
									<div class="list-group" id="tree"></div>
								</div>
							</div>
						</div>
					</div>



					<div class="form-group">
						<label for="enddate" class="control-label">截止时间</label>

						<div class="input-group date form_date col-sm-5" data-date=""
							data-date-format="yyyy-mm-dd hh:ii " data-link-field="dtp_input2"
							data-link-format="yyyy-mm-dd hh:ii">
							<input class="form-control" size="16" type="text" id="enddate"
								value='<fmt:formatDate value="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.enddate}"
														pattern="yyyy-MM-dd HH:mm" />'
								name="enddate" readonly required>
							<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						</div>


					</div>



					<div class="form-group">

						<label for="exampleInputFile" class=" control-label">附件</label>
						<p class="help-block">任意文件类型均可。</p>
						<input type="file" multiple="multiple" id="exampleInputFile" name="file" />

					</div>


				</div>

				<div class="modal-footer">
					<button type="submit" id="updatebtn" class="btn btn-primary" onclick="onAdd()">修改</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</form>


		</div>
	</div>





	<%@ include file="../../shared/sysLastInclude.jsp"%>


	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>


	<script type="text/javascript">
		$("#collapseHomework").addClass("in");
		$("#homeworktype${selectedCourseHomeworkTypeData.id}").addClass(
				"active");
	</script>


	<%@ include file="filerequirement.jsp"%>


	<script type="text/javascript">
		//	var sHTML=${selectedCourseForumTopicViewData.forumtopic.content};
		$('#title')
				.val(
						'${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}');
		$('.summernote')
				.summernote('code',
						'${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.content}');
		//	$("#contentDiv").code("adf");
	</script>

	<script type="text/javascript">
	<c:forEach var="data"
		items="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.fileRequirement.array}">
		
var obj = {
"nodeID" : ${data.data.nodeID},
"parentNodeID" :  ${data.data.parentNodeID},
"fileType" :  '${data.data.fileType}',
"fileTypeDescription" : '${data.data.fileTypeDescription}',
"fileTypeData" :  '${data.data.fileTypeData}',
"fileFormat" :  '${data.data.fileFormat}',
"filenameRequirementVal" : '${ data.data.filenameRequirementVal}',
"fileCount" :  '${data.data.fileCount}'
};
ID=obj.nodeID+1;
//添加项
addNode(obj);

	</c:forEach>
	
	</script>










</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<body style="overflow: scroll;">

	<%@ include file="../../shared/pageHeader.jsp"%>
	<%@ include file="../../shared/CourseTeachingClassInfo.jsp"%>


	<div class="DocumentPage" style="max-width: none; width: auto; display: inline;">
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

			<div class="CourseContentHeader">${selectedCourseHomeworkTypeData.name}成绩</div>

			<div class="CourseContentHeaderSeparatorLine"></div>





			<c:choose>

				<c:when test="${selectedCourseTeachingClassHomeworkStudentScoresViewData!=null}">
					<div class="Course-Table">
						<div class="gridseparator"></div>

						<div class="container-fluid">
							<div class="row">
								<div class="col-md-1">
									<strong>#</strong>
								</div>
								<div class="col-md-3">
									<strong>作业名称</strong>
								</div>
								<div class="col-md-2">
									<strong>分数</strong>
								</div>
							</div>

							<div class="gridseparator"></div>

							<c:set var="index" value="0"></c:set>
							<c:forEach var="d"
								items="${selectedCourseTeachingClassHomeworkStudentScoresViewData.lstScore}">
								<div class="row">
									<div class="col-md-1">${fn:length(selectedCourseTeachingClassHomeworkStudentScoresViewData.lstScore) -index}</div>
									<div class="col-md-3">${d.scoreInfo.homeworkBaseinfo.title }</div>
									<div class="col-md-2">
										<c:choose>
											<c:when test="${empty d.scoreStatus}">
												<div style="width: 2em; height: 1em; background-color: red;"></div>
											</c:when>
											<c:otherwise>${d.scoreStatus }</c:otherwise>
										</c:choose>
									</div>
								</div>
								<c:set var="index" value="${index + 1}"></c:set>
							</c:forEach>
						</div>

						<div class="gridseparator"></div>

					</div>



				</c:when>

				<c:otherwise>
					<%
						//没有找到记录
					%>
					<div class="alert alert-warning alert-dismissible fade in" role="alert" style="margin: 10px;">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3>没有找到记录.</h3>


					</div>
				</c:otherwise>
			</c:choose>






		</div>
	</div>










	<%@ include file="../../shared/sysLastInclude.jsp"%>


	<script type="text/javascript">
		$("#collapseHomeworkScore").addClass("in");
		$("#coursehomeworkscore${selectedCourseHomeworkTypeData.id}").addClass(
				"active");
	</script>

	<script type="text/javascript">
		
	</script>




	<script>
		
	</script>




</body>
</html>

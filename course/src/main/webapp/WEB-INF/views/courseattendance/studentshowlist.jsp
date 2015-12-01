<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "courseattendance");
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
				<li><a href="#">课程系统</a></li>
				<li><a href="<c:url value="/coursecontent/index-${selectedCourseTeachingClassViewData.courseteachingclass.id}.html"/>">${selectedCourseTeachingClassViewData.course.name}</a></li>
				<li class="active">${selectedCourseAttendanceTypeData.name}</li>
			</ol>

			<div class="CourseContentHeader">${selectedCourseAttendanceTypeData.name}</div>

			<div class="CourseContentHeaderSeparatorLine"></div>

			<c:choose>

				<c:when test="${pagedAttendanceStateModeViewData.totalCount==0}">

					<div class="alert alert-warning alert-dismissible fade in"
						role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>暂未有${selectedCourseAttendanceTypeData.name}</strong>
						<p class="text-info">
							授课教师尚未对<em>${selectedCourseAttendanceTypeData.name}</em>进行考勤。
						</p>
					</div>

				</c:when>

				<c:otherwise>

					<div class="Course-Table">



						<div class="container-fluid" style="overflow: hidden;">
							<div class="gridseparator"></div>
							<div class="row show-grid">
								<div class="col-md-1">
									<strong>#</strong>
								</div>
								<div class="col-md-2">
									<strong>考勤日期</strong>
								</div>

								<div class="col-md-2">
									<strong>签到情况</strong>
								</div>

								<div class="col-md-2">
									<strong>签到方式</strong>
								</div>

								<div class="col-md-2">
									<strong>签到时间</strong>
								</div>
							</div>

							<div class="gridseparator"></div>


							<c:set var="index" value="0"></c:set>
							<c:forEach var="data"
								items="${pagedAttendanceStateModeViewData.result}">
								<div class="row show-grid">
									<div class="col-md-1">

										${pagedAttendanceStateModeViewData.totalCount-(pagedAttendanceStateModeViewData.currentPageNo-1) * pagedAttendanceStateModeViewData.pageSize -index}
									</div>

									<div class="col-md-2">

										<fmt:formatDate value="${data.attendance.begin_datetime}"
											pattern="yyyy-MM-dd HH:mm" />
										-
										<fmt:formatDate value="${data.attendance.end_datetime}"
											pattern="yyyy-MM-dd HH:mm" />

									</div>
									<div class="col-md-2">${data.state.name}</div>
									<div class="col-md-2">${data.mode.name}</div>
									<div class="col-md-2">
										<fmt:formatDate
											value="${data.attendancestudent.checking_in_datetime}"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</div>
								</div>
								<c:set var="index" value="${index + 1}"></c:set>
							</c:forEach>

							<c:if test="${pagedAttendanceStateModeViewData.totalCount>0}">
								<div class="gridseparator"></div>
							</c:if>

						</div>



						<c:if test="${not empty selectedCourseTeachingClassID}">
							<mathtop:PageBar
								pageUrl="/courseattendance/singlestudentattendance-${selectedCourseTeachingClassID}-${selectedCourseAttendanceTypeData.id}-${selectedStudentID}.html"
								pageAttrKey="pagedAttendanceStateModeViewData" />
						</c:if>

					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>




	<%@ include file="../../shared/dialog.jsp"%>

	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>


	<script type="text/javascript">
		$("#collapseAtendance").addClass("in");
		$("#attendancetype${selectedCourseAttendanceTypeData.id}").addClass(
				"active");
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

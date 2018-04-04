<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
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



<link href="<c:url value='/css/pages/index.css'/>" rel="stylesheet" type="text/css" />

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


			<div class="CourseContentHeader">${selectedCourseTeachingClassViewData.teachingclass.name }
				<mark>
					<fmt:formatDate value="${selectedCourseAttendanceViewData.attendance.begin_datetime}"
						pattern="yyyy-MM-dd HH:mm" />
					-
					<fmt:formatDate value="${selectedCourseAttendanceViewData.attendance.end_datetime}"
						pattern="yyyy-MM-dd HH:mm" />

				</mark>
				${selectedCourseAttendanceTypeData.name}统计情况
			</div>

			<div class="CourseContentHeaderSeparatorLine"></div>




			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingTwo">
					<h4 class="panel-title">
						<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"
							aria-expanded="true" aria-controls="collapseTwo"> 图表 </a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse in" style="overflow: hidden;"
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body">

						<canvas id="myChart" width="400" height="400"></canvas>


					</div>
				</div>
			</div>


			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingThree">
					<h4 class="panel-title">
						<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree"
							aria-expanded="true" aria-controls="collapseThree"> 人数统计 </a>
					</h4>
				</div>
				<div id="collapseThree" class="panel-collapse collapse in" style="overflow: hidden;"
					role="tabpanel" aria-labelledby="headingThree">
					<div class="panel-body">
						<div class="container-fluid" style="overflow: hidden;">
							<div class="gridseparator"></div>


							<c:forEach var="data" items="${SelectedAttendanceSpecificStatistics}">
								<div class="row show-grid">
									<div class="col-md-1">
										<strong>${data.state.name}</strong>
									</div>
									<div class="col-md-1">${data.nCount}</div>

								</div>

							</c:forEach>


						</div>


					</div>
				</div>
			</div>

			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingFour">
					<h4 class="panel-title">
						<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour"
							aria-expanded="true" aria-controls="collapseFour"> 名单 </a>
					</h4>
				</div>
				<div id="collapseFour" class="panel-collapse collapse" style="overflow: hidden;" role="tabpanel"
					aria-labelledby="headingFour">
					<div class="panel-body">
						<div class="container-fluid" style="overflow: hidden;">
							<div class="gridseparator"></div>
							<div class="row show-grid">
								<div class="col-md-1">
									<strong>#</strong>
								</div>
								<div class="col-md-1">
									<strong>班级</strong>
								</div>
								<div class="col-md-1">
									<strong>学号</strong>
								</div>
								<div class="col-md-1">
									<strong>姓名</strong>
								</div>
								<div class="col-md-1">
									<strong>考勤状况</strong>
								</div>
								<div class="col-md-1">
									<strong>签到方式</strong>
								</div>
							</div>


							<c:set var="index" value="1"></c:set>
							<c:forEach var="data" items="${pagedAttendanceStudentViewData.result}">
								<div class="row show-grid">
									<div class="col-md-1">${(pagedAttendanceStudentViewData.currentPageNo-1) * pagedAttendanceStudentViewData.pageSize +index}
									</div>

									<div class="col-md-1">${data.studentviewdata.naturalclass.name}</div>

									<div class="col-md-1">${data.studentviewdata.student.student_num}</div>

									<div class="col-md-1">${data.studentviewdata.userbasicinfo.userBasicInfoName}</div>

									<div class="col-md-1">${data.state.name}</div>





									<div class="col-md-1">${data.mode.name}</div>



								</div>
								<c:set var="index" value="${index + 1}"></c:set>
							</c:forEach>




						</div>


					</div>
				</div>
			</div>



		</div>

	</div>







	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/sysLastInclude.jsp"%>

	<script src="<c:url value='/plugins/Chart.js-master/Chart.min.js'/>" type="text/javascript"></script>



	<script type="text/javascript">
		$("#collapseAtendance").addClass("in");
		$("#attendancetype${selectedCourseAttendanceTypeData.id}").addClass(
				"active");
	</script>

	<script type="text/javascript">
		$(function() {

			var url = "<c:url value='/courseattendance/getChartDataValueofAttendanceSpecificStatistics-${selectedCourseAttendanceID}.json'/>";

			$.get(url, function(data, status) {
				if (status == "success") {

					//Get context with jQuery - using jQuery's .get() method.
					var ctx = $("#myChart").get(0).getContext("2d");

					var myNewChart = new Chart(ctx).Pie(data);
				}
			});

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
	</script>




</body>
</html>

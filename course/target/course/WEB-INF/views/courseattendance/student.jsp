<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "coursestudentlist");
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




<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<%@ include file="../../shared/pageHeader.jsp"%>


	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="../CourseContentNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">




			<div class="CourseContentHeader">
				<span class="text-info"><strong>${selectedCourseTeachingClassViewData.courseTeachingClass.name}</strong></span>教学班学生花名册
			</div>
			<p class="text-muted">
				<strong>课程名称：</strong>${selectedCourseTeachingClassViewData.course.name}</p>

			<p class="text-muted">
				<strong>授课教师：</strong>

				<c:forEach var="t" items="${selectedCourseTeachingClassViewData.teacher}" varStatus="status">
							${t.userbasicinfo.userBasicInfoName}(${selectedCourseTeachingClassViewData.teachingtype[status.index].name})
							</c:forEach>

			</p>

			<p class="text-muted">
				<strong>授课时间：</strong>${selectedCourseTeachingClassViewData.term.term}</p>
			<div class="CourseContentHeaderSeparatorLine"></div>

			<table>
				<tr>





					<td><div class="input-group input-group-sm">
							<input type="text" class="form-control" id="SearchText" placeholder="Search for..." required>
							<span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="onSearch()">搜索</button>
							</span>
						</div></td>
					<td style="width: 10px;"></td>
					<td><div class="btn-group" role="group" aria-label="...">
							<!-- Split button -->
							<div class="btn-group">
								<button type="button" class="btn btn-default btn-sm">每页大小</button>
								<button type="button" class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown"
									aria-expanded="false">
									<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a
											href="courseattendance/student-${selectedCourseTeachingClassID}.html?pageSize=10">
											<c:if test="${sessionScope.USER_CONTEXT.pageSize==10}">
												<i class="icon-ok"></i>
											</c:if>
											默认(10)
										</a></li>
									<li class="divider"></li>
									<li><a
											href="courseattendance/student-${selectedCourseTeachingClassID}.html?pageSize=20">
											<c:if test="${sessionScope.USER_CONTEXT.pageSize==20}">
												<i class="icon-ok"></i>
											</c:if>
											20
										</a></li>
									<li><a
											href="courseattendance/student-${selectedCourseTeachingClassID}.html?pageSize=30">
											<c:if test="${sessionScope.USER_CONTEXT.pageSize==30}">
												<i class="icon-ok"></i>
											</c:if>
											30
										</a></li>
									<li><a
											href="courseattendance/student-${selectedCourseTeachingClassID}.html?pageSize=40">
											<c:if test="${sessionScope.USER_CONTEXT.pageSize==40}">
												<i class="icon-ok"></i>
											</c:if>
											40
										</a></li>
									<li><a
											href="courseattendance/student-${selectedCourseTeachingClassID}.html?pageSize=50">
											<c:if test="${sessionScope.USER_CONTEXT.pageSize==50}">
												<i class="icon-ok"></i>
											</c:if>
											50
										</a></li>
									<li><a
											href="courseattendance/student-${selectedCourseTeachingClassID}.html?pageSize=60">
											<c:if test="${sessionScope.USER_CONTEXT.pageSize==60}">
												<i class="icon-ok"></i>
											</c:if>
											60
										</a></li>
									<li><a
											href="courseattendance/student-${selectedCourseTeachingClassID}.html?pageSize=70">
											<c:if test="${sessionScope.USER_CONTEXT.pageSize==70}">
												<i class="icon-ok"></i>
											</c:if>
											70
										</a></li>
									<li><a
											href="courseattendance/student-${selectedCourseTeachingClassID}.html?pageSize=80">
											<c:if test="${sessionScope.USER_CONTEXT.pageSize==80}">
												<i class="icon-ok"></i>
											</c:if>
											80
										</a></li>
									<li><a
											href="courseattendance/student-${selectedCourseTeachingClassID}.html?pageSize=90">
											<c:if test="${sessionScope.USER_CONTEXT.pageSize==90}">
												<i class="icon-ok"></i>
											</c:if>
											90
										</a></li>
									<li><a
											href="courseattendance/student-${selectedCourseTeachingClassID}.html?pageSize=100">
											<c:if test="${sessionScope.USER_CONTEXT.pageSize==100}">
												<i class="icon-ok"></i>
											</c:if>
											100
										</a></li>
									<li class="divider"></li>
									<li><a
											href="courseattendance/student-${selectedCourseTeachingClassID}.html?pageSize=1000">
											<c:if test="${sessionScope.USER_CONTEXT.pageSize>100}">
												<i class="icon-ok"></i>
											</c:if>
											不分页
										</a></li>
								</ul>
							</div>
						</div></td>
				</tr>
			</table>


			<c:choose>

				<c:when test="${pagedStudentViewData.totalCount >0}">
					<div class="Course-Table">

						<div class="gridseparator"></div>
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-1">
									<strong>#</strong>
								</div>
								<div class="col-md-2">
									<strong>学号</strong>
								</div>
								<div class="col-md-2">
									<strong>姓名</strong>
								</div>
								<div class="col-md-2">
									<strong>系部</strong>
								</div>
								<div class="col-md-2">
									<strong>班级</strong>
								</div>


							</div>
							<div class="gridseparator"></div>
							<c:set var="index" value="1"></c:set>
							<c:forEach var="dataitem" items="${pagedStudentViewData.result}">
								<div class="row">
									<div class="col-md-1">${(pagedStudentViewData.currentPageNo-1) * pagedStudentViewData.pageSize +index}
									</div>
									<div class="col-md-2">${dataitem.student.studentNum}</div>
									<div class="col-md-2">${dataitem.userbasicinfo.userBasicInfoName}</div>
									<div class="col-md-2">${dataitem.department.name}</div>
									<div class="col-md-2">${dataitem.naturalclass.name}</div>


								</div>
								<c:set var="index" value="${index + 1}"></c:set>
							</c:forEach>

							<div class="gridseparator"></div>
							<c:if test="${not empty selectedCourseTeachingClassID}">
								<mathtop:PageBar pageUrl="/courseattendance/student-${selectedCourseTeachingClassID}.html"
									pageAttrKey="pagedStudentViewData" />
							</c:if>

						</div>

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
						<h3>没有找到记录,请选择下列操作之一：</h3>

						<div class="list-group">


							<a href="<c:url value="/teachingclass/add.html"/>" class="list-group-item">添加教学班班</a>
							<a href="<c:url value="/course/add.html"/>" class="list-group-item">添加课程</a>

							<a href="<c:url value="/teacher/add.html"/>" class="list-group-item">添加教师</a>
							<a href="<c:url value="/naturalclass/list.html"/>" class="list-group-item">添加自然班</a>
							<a href="<c:url value="/school/list.html"/>" class="list-group-item">添加学院</a>
							<a href="<c:url value="/department/list.html"/>" class="list-group-item">添加系部</a>

						</div>

					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>




	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/sysLastInclude.jsp"%>


	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>

	<script>
		function onDelete(t_student_id, t_student_name) {
			var url = "location='<c:url value="/teachingclass/"/>"

			url = url
					+ "deletestudent-${selectedCourseTeachingClassViewData.courseTeachingClass.id}-"
					+ t_student_id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(
					t_student_name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}
	</script>



</body>
</html>

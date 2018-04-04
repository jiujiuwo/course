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
				<li><a href="<c:url value="/coursecontent/index-${selectedCourseTeachingClassViewData.courseTeachingClass.id}.html"/>">${selectedCourseTeachingClassViewData.course.name}</a></li>
				<li class="active">${selectedCourseHomeworkTypeData.name}</li>
			</ol>

			<div class="CourseContentHeader">${selectedCourseTeachingClassViewData.courseTeachingClass.name }
				<mark>
					<fmt:formatDate
						value="${selectedCourseAttendanceViewData.attendance.beginDatetime}"
						pattern="yyyy-MM-dd HH:mm" />
					-
					<fmt:formatDate
						value="${selectedCourseAttendanceViewData.attendance.endDatetime}"
						pattern="yyyy-MM-dd HH:mm" />

				</mark>
				${selectedCourseAttendanceTypeData.name}
			</div>

			<div class="CourseContentHeaderSeparatorLine"></div>



			<table>
				<tr>

					<td>

						<div class="btn-group">
							<button type="button" class="btn btn-default btn-sm">设置默认签到情况</button>
							<button type="button"
								class="btn btn-default btn-sm dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
								<span class="caret"></span> <span class="sr-only">设置默认签到情况</span>
							</button>
							<ul class="dropdown-menu">
								<c:forEach var="typeitem" items="${pagedAttendanceState.result}">
									<li><a href="javascript:void(0);"
										onclick="onSetDefaultAttendanceState('${typeitem.id}')">${typeitem.name }</a></li>
								</c:forEach>
							</ul>
						</div>

					</td>
					<td style="width: 10px;"></td>
					<td>

						<div class="btn-group">
							<button type="button" class="btn btn-default btn-sm">设置默认签到方式</button>
							<button type="button"
								class="btn btn-default btn-sm dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
								<span class="caret"></span> <span class="sr-only">设置默认签到方式</span>
							</button>
							<ul class="dropdown-menu">
								<c:forEach var="stateitem" items="${pagedAttendanceMode.result}">
									<li><a href="javascript:void(0);"
										onclick="onSetDefaultAttendanceMode('${stateitem.id}')">${stateitem.name }</a></li>
								</c:forEach>
							</ul>
						</div>


					</td>
				</tr>
			</table>




			<div class="Course-Table">


				<form class="form-horizontal" style="overflow: hidden;"
					action="<c:url value="/courseattendance/addstudentattendance-${selectedCourseTeachingClassID}-${selectedCourseAttendanceTypeData.id}-${selectedCourseAttendanceID}.html"/>"
					method="post">

					<table id="studenttable" class="table table-hover table-bordered">
						<thead>
							<tr>
								<th style="width: 5%;">#</th>
								<th style="width: 10%;">学号</th>
								<th style="width: 10%;">姓名</th>
								<th style="width: 10%;">班级</th>
								<th style="width: 30%;">签到情况</th>
								<th style="width: 35%;">签到方式</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="index" value="1"></c:set>
							<c:forEach var="dataitem" items="${pagedStudentViewData.result}">
								<tr>
									<th scope="row">${(pagedStudentViewData.currentPageNo-1) * pagedStudentViewData.pageSize +index}</th>
									<td>${dataitem.student.studentNum}</td>
									<td>${dataitem.userbasicinfo.userBasicInfoName}</td>
									<td>${dataitem.naturalclass.name}</td>

									<td><c:forEach var="stateitem"
											items="${pagedAttendanceState.result}"
											varStatus="statestatus">


											<label class="radio-inline"> <input type="radio"
												id="stateoption${dataitem.student.id}"
												name="stateoption${dataitem.student.id}" autocomplete="off"
												value="${stateitem.id}"
												<c:forEach var="attendanceitem" items="${pagedAttendanceStudentViewData.result}">
												
													<c:if test="${attendanceitem.studentviewdata.student.id==dataitem.student.id and attendanceitem.state.id==stateitem.id}">
														checked
													</c:if>
												</c:forEach>
												<c:if
												test="${pagedAttendanceStudentViewData.totalCount==0 and statestatus.index==0}">
													checked
													</c:if>>
												${stateitem.name }
											</label>
										</c:forEach></td>

									<td><c:forEach var="typeitem"
											items="${pagedAttendanceMode.result}" varStatus="typestatus">


											<label class="radio-inline"> <input type="radio"
												id="modeoption${dataitem.student.id}"
												name="modeoption${dataitem.student.id}" autocomplete="off"
												value="${typeitem.id}"
												<c:forEach var="attendanceitem" items="${pagedAttendanceStudentViewData.result}">
													<c:if test="${attendanceitem.studentviewdata.student.id==dataitem.student.id and attendanceitem.mode.id==typeitem.id}">
														checked
													</c:if>
												</c:forEach>
												<c:if
												test="${pagedAttendanceStudentViewData.totalCount==0 and typestatus.index==0}">
													checked
													</c:if>>
												${typeitem.name }
											</label>
										</c:forEach></td>

								</tr>
								<c:set var="index" value="${index + 1}"></c:set>
							</c:forEach>
						</tbody>
					</table>


					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">添加</button>
						<button type="button" class="btn btn-default"
							onclick="window.history.back()">取消</button>
					</div>

				</form>

				<c:if test="${not empty selectedCourseTeachingClassID}">
					<mathtop:PageBar
						pageUrl="/courseattendance/studentattendance-${selectedCourseTeachingClassID}-${selectedCourseAttendanceID}.html"
						pageAttrKey="pagedStudentViewData" />
				</c:if>

			</div>
		</div>
	</div>





	

	<%@ include file="../../shared/importJs.jsp"%>
<%@ include file="../../shared/sysLastInclude.jsp"%>

	<script type="text/javascript">
		$("#collapseAtendance").addClass("in");
		$("#attendancetype${selectedCourseAttendanceTypeData.id}").addClass(
				"active");
	</script>


	<script>
		function onSetDefaultAttendanceState(id) {

			var x = $("input[id^='stateoption']").toArray();
			for (i = 0; i < x.length; i++) {

				if (x[i].value == id)
					x[i].checked = true;
			}
		}

		function onSetDefaultAttendanceMode(id) {
			var x = $("input[id^='modeoption']").toArray();
			for (i = 0; i < x.length; i++) {

				if (x[i].value == id)
					x[i].checked = true;
			}
		}
	</script>



</body>
</html>

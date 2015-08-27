<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "teachingclass");
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
<body class="home">

	<%@ include file="../../shared/pageHeader.jsp"%>
	

	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="../CourseNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">




			<div class="CourseContentHeader">
				<span class="text-info"><strong>${pagedTeachingClassViewData.result[0].teachingclass.name}</strong></span>教学班学生管理
			</div>
			<p class="text-muted">
				<strong>课程名称：</strong>${pagedTeachingClassViewData.result[0].course.name}</p>

			<p class="text-muted">
				<strong>授课教师：</strong>

				<c:forEach var="t"
					items="${pagedTeachingClassViewData.result[0].teacher}"
					varStatus="status">
							${t.userbasicinfo.user_basic_info_name}(${pagedTeachingClassViewData.result[0].teachingtype[status.index].name})
							</c:forEach>

			</p>

			<p class="text-muted">
				<strong>授课时间：</strong>${pagedTeachingClassViewData.result[0].courseteachingclass.teaching_year_begin}-${pagedTeachingClassViewData.result[0].courseteachingclass.teaching_year_end}学年第${pagedTeachingClassViewData.result[0].courseteachingclass.teaching_term}学期</p>
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
								onclick="window.location.href='teachingclass/addstudent-${selectedTeachingClassID}.html'">增加学生</button>



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
					<td style="width: 10px;"></td>
					<td><div class="btn-group" role="group" aria-label="...">
							<!-- Split button -->
							<div class="btn-group">
								<button type="button" class="btn btn-default btn-sm">每页大小</button>
								<button type="button"
									class="btn btn-default dropdown-toggle btn-sm"
									data-toggle="dropdown" aria-expanded="false">
									<span class="caret"></span> <span class="sr-only">Toggle
										Dropdown</span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">默认(10)</a></li>
									<li class="divider"></li>
									<li><a href="#">20</a></li>
									<li><a href="#">30</a></li>
									<li><a href="#">40</a></li>
									<li><a href="#">50</a></li>
									<li class="divider"></li>
									<li><a href="#">不分页</a></li>
								</ul>
							</div>
						</div></td>
				</tr>
			</table>


			<c:choose>
				<c:when test="${pagedStudentViewData.totalCount >0}">

					<div class="form-group">



						<div class="col-md-12">


							<div class="Course-Table">


								<table id="studenttable"
									class="table table-hover table-bordered">
									<thead>
										<tr>
											<th style="width: 10%;">#</th>
											<th style="width: 20%;">学号</th>
											<th style="width: 30%;">姓名</th>
											<th style="width: 30%;">系部</th>
											<th style="width: 30%;">班级</th>
											<th style="width: 10%;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="index" value="1"></c:set>
										<c:forEach var="dataitem"
											items="${pagedStudentViewData.result}">
											<tr>
												<th scope="row"><input type="checkbox" value="">
													${(pagedStudentViewData.currentPageNo-1) * pagedStudentViewData.pageSize +index}</th>
												<td>${dataitem.student.student_num}</td>
												<td>${dataitem.userbasicinfo.user_basic_info_name}</td>
												<td>${dataitem.department.name}</td>
												<td>${dataitem.naturalclass.name}</td>

												<td>
													<button type="button" class="btn btn-default btn-xs"
														onclick="onDelete('${dataitem.student.id}','${dataitem.userbasicinfo.user_basic_info_name}')">删除</button>
												</td>
											</tr>
											<c:set var="index" value="${index + 1}"></c:set>
										</c:forEach>
									</tbody>
								</table>

								<c:if test="${not empty selectedTeachingClassID}">
									<mathtop:PageBar
										pageUrl="/teachingclass/student-${selectedTeachingClassID}.html"
										pageAttrKey="pagedStudentViewData" />
								</c:if>

							</div>
						</div>





					</div>

				</c:when>

				<c:otherwise>
					<%
						//没有找到记录
					%>
					<div class="alert alert-warning alert-dismissible fade in"
						role="alert" style="margin: 10px;">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3>没有找到记录,请选择下列操作之一：</h3>

						<div class="list-group">


							<a href="<c:url value="/teachingclass/add.html"/>"
								class="list-group-item">添加教学班班</a> <a
								href="<c:url value="/course/add.html"/>" class="list-group-item">添加课程</a>

							<a href="<c:url value="/teacher/add.html"/>"
								class="list-group-item">添加教师</a> <a
								href="<c:url value="/naturalclass/list.html"/>"
								class="list-group-item">添加自然班</a> <a
								href="<c:url value="/school/list.html"/>"
								class="list-group-item">添加学院</a> <a
								href="<c:url value="/department/list.html"/>"
								class="list-group-item">添加系部</a>

						</div>

					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>





	<%@ include file="../../shared/dialog.jsp"%>


	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>

<script>
		

		function onDelete(id, name) {
			var url = "location='<c:url value="/teachingclass/"/>"

			url = url + "deletestudent-${selectedTeachingClassID}-" + id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			

			$('#deleteModal').modal('show');

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

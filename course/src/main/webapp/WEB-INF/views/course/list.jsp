<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "course");
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


	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="../CourseNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">




			<div class="CourseContentHeader">课程管理</div>

			<div class="CourseContentHeaderSeparatorLine"></div>



			<table>
				<tr>
					<td>
						<div class="input-group input-group-sm">
							<select id="SchoolSelectControl" class="form-control" onchange="OnSelectControlChange(this)">
								<c:forEach var="school" items="${pagedSchool.result}">
									<option value="${school.id}">${school.name}</option>
								</c:forEach>
							</select>




						</div>
					</td>

					<td style="width: 10px;"></td>

					<td><div class="btn-group" role="group" aria-label="...">
							<!-- Split button -->
							<div class="btn-group">
								<button type="button" class="btn btn-default btn-sm">选择</button>
								<button type="button" class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown"
									aria-expanded="false">
									<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
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
								onclick="window.location.href='course/add.html'">增加</button>

							<button type="button" class="btn btn-default btn-sm" data-toggle="modal"
								data-target="#myModal">删除</button>


						</div></td>
					<td style="width: 10px;"></td>
					<td><div class="input-group input-group-sm">
							<input type="text" class="form-control" id="SearchText" placeholder="Search for..." required>
							<span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="onSearch()">搜索</button>
							</span>
						</div></td>
				</tr>
			</table>




			<c:choose>
				<c:when test="${pagedCourseViewData.totalCount >0}">



					<div class="Course-Table">


						<table class="table table-hover table-bordered" style="margin-bottom: -10px;">
							<thead>
								<tr>
									<th>#</th>
									<th>课程编号</th>
									<th>课程名称</th>
									<th>课程英文名称</th>
									<th>课堂授课</th>
									<th>实验学时</th>
									<th>课程性质</th>
									<th>先修课程</th>
									<th>课程专业</th>

									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="index" value="1"></c:set>
								<c:forEach var="dataitem" items="${pagedCourseViewData.result}">
									<tr>
										<th scope="row"><input type="checkbox" value="">
											${(pagedCourseViewData.currentPageNo-1) * pagedCourseViewData.pageSize +index}</th>

										<td>${dataitem.course.num}</td>
										<td>${dataitem.course.name}</td>
										<td>${dataitem.course.englishname}</td>
										<td>${dataitem.course.class_hours}</td>
										<td>${dataitem.course.experiment_hours}</td>
										<td>${dataitem.courseStyle.name}:${dataitem.courseType.name}</td>
										<td><c:forEach var="dataprecourseitem" items="${dataitem.listCoursePrecourse}">
							${dataprecourseitem.coursepre.name}
							</c:forEach></td>

										<td></td>
										<td><button type="button" class="btn btn-default btn-xs"
												onclick="location='<c:url value="/course/update-${dataitem.course.id}.html"/>'">修改...</button>
											<button type="button" class="btn btn-default btn-xs"
												onclick="onDelete('${dataitem.course.id}','${dataitem.course.name}')">删除</button></td>
									</tr>
									<c:set var="index" value="${index + 1}"></c:set>
								</c:forEach>
							</tbody>
						</table>

						<mathtop:PageBar pageUrl="/course/list.html" pageAttrKey="pagedCourseViewData" />

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


							<a href="<c:url value="/course/add.html"/>" class="list-group-item">添加课程</a>
							<a href="<c:url value="/teachingclass/add.html"/>" class="list-group-item">添加教学班班</a>
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




	<%
		//删除对话框、错误信息对话框
	%>

	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/sysLastInclude.jsp"%>

<script>
		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onDelete(id,name) {
			var url = "location='<c:url value="course/delete-"/>"
					+ id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}

		
	</script>



</body>
</html>

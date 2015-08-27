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
	

	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="../CourseNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">




			<div class="CourseContentHeader">教学班管理</div>

			<div class="CourseContentHeaderSeparatorLine"></div>



			<table>
				<tr>
					<td>
						<div class="input-group input-group-sm">
							<select id="SchoolSelectControl" class="form-control"
								onchange="OnSelectControlChange(this)">
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
								onclick="window.location.href='teachingclass/add.html'">增加</button>

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
				<c:when test="${pagedTeachingClassViewData.totalCount >0}">


					<div class="Course-Table">


						<table class="table table-hover table-bordered"
							style="margin-bottom: -10px;">
							<thead>
								<tr>
									<th>#</th>
									<th>教学班名称</th>
									<th>课程</th>
									<th>授课时间</th>
									<th>教师</th>
									<th>学生</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="index" value="1"></c:set>
								<c:forEach var="dataitem"
									items="${pagedTeachingClassViewData.result}">
									<tr>
										<th scope="row"><input type="checkbox" value="">
											${(pagedTeachingClassViewData.currentPageNo-1) * pagedTeachingClassViewData.pageSize +index}</th>

										<!-- 教学班名称 -->
										<td>${dataitem.teachingclass.name}</td>

										<!-- 课程名称 -->
										<td>${dataitem.course.name}</td>

										<!-- 授课时间 -->
										<td>${dataitem.courseteachingclass.teaching_year_begin}-${dataitem.courseteachingclass.teaching_year_end}学年第${dataitem.courseteachingclass.teaching_term}学期</td>

										<!-- 授课教师 -->
										<td>

											<div class="container-fluid" style="margin:0px;padding:0px;">
												<div class="row" style="margin:0px;padding:0px;">
												<div class="col-xs-12 col-md-8" style="margin:0px;padding:0px;">
													<c:forEach var="t" items="${dataitem.teacher}"
														varStatus="status">
														<p>${t.userbasicinfo.user_basic_info_name}(${dataitem.teachingtype[status.index].name})</p>
													</c:forEach>
												</div>
												<div class="col-xs-6 col-md-4" style="margin:0px;padding:0px;">
													<button type="button" class="btn btn-default btn-xs"
														onclick="window.location.href='teachingclass/teacher-${dataitem.teachingclass.id}.html'">详细...</button>
												</div>
												</div>
											</div>



										</td>

										<td><button type="button" class="btn btn-default btn-xs"
												onclick="window.location.href='teachingclass/student-${dataitem.teachingclass.id}.html'">详细...</button></td>

										<td><button type="button" class="btn btn-default btn-xs"
												onclick="onUpdate('${dataitem.courseteachingclass.id}')">修改...</button>
											<button type="button" class="btn btn-default btn-xs"
												onclick="onDelete('${dataitem.courseteachingclass.id}')">删除</button></td>
									</tr>
									<c:set var="index" value="${index + 1}"></c:set>
								</c:forEach>
							</tbody>
						</table>

						<mathtop:PageBar pageUrl="/${pagedURI}/list.html"
							pageAttrKey="pagedNaturalClassSchoolViewData" />

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


	<!-- 添加对话框 -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加自然班</h4>
				</div>



				<form class="form-signin"
					action="<c:url value="/${pagedURI}/add.html"/>" method="post">


					<div class="modal-body">
						<h4 class="form-signin-heading">请输入新加自然班名称</h4>

						<div class="container-fluid" style="overflow: hidden;">
							<div class="row">
								<div class="col-md-2">学院</div>
								<div class="col-md-10">
									<select id="SchoolSelectControlAdd" class="form-control"
										name="t_school_id"
										onchange="OnSchoolSelectControlAddChange(this)">
										<c:forEach var="school" items="${pagedSchool.result}">
											<option value="${school.id}">${school.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="row">
								<div class="col-md-2">系部</div>
								<div class="col-md-10">
									<select id="DepartmentSelectControlAdd" class="form-control"
										name="t_department_id">
										<c:forEach var="d" items="${pagedDepartment.result}">
											<option value="${d.id}">${d.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>


							<div class="row">
								<div class="col-md-2">名称</div>
								<div class="col-md-10">
									<input type="text" id="inputname" class="form-control"
										name="naturalclassname" placeholder="自然班名称" required autofocus>
								</div>
							</div>

							<div class="row">
								<div class="col-md-2">描述</div>
								<div class="col-md-10">
									<input type="text" id="inputnote" class="form-control"
										name="naturalclassnote" placeholder="自然班描述">
								</div>
							</div>

						</div>


					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" onclick="onAdd()">添加</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>

				</form>
			</div>
		</div>
	</div>


	<!-- 修改对话框 -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改自然班</h4>
				</div>

				<form class="form-signin"
					action="<c:url value='/${pagedURI}/update.html'/>" method="post">


					<div class="modal-body">
						<h4 class="form-signin-heading">请修改自然班属性</h4>
						<label for="inputname" class="sr-only">自然班名称</label> <input
							type="text" id="inputid" class="form-control" name="id" value=""
							placeholder="id" style="display: none;"> <input
							type="text" id="inputname" class="form-control" name="name"
							value="" placeholder="自然班名称" required autofocus> <label
							for="inputnote" class="sr-only">自然班描述</label> <input type="text"
							id="inputnote" class="form-control" name="note" value=""
							placeholder="自然班描述">

					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">修改</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>

				</form>
			</div>
		</div>
	</div>


	<%
		//删除对话框、错误信息对话框
	%>
	<%@ include file="../../shared/dialog.jsp"%>

	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>


	<script>
		$(function() {

			var ctrl = document.getElementById("SchoolSelectControl");
			var schooledid = "${selectedt_school_id}";
			var index = 0;

			for (var i = 0; i < ctrl.options.length; i++)
				if (ctrl.options[i].value == (schooledid))
					index = i;
			ctrl.selectedIndex = index;
			var schooltext = (ctrl.options[index].text);

			$('#addModal').find('.modal-body #schoolname').text(schooltext);
			$('#updateModal').find('.modal-body #schoolname').text(schooltext);
		});
	</script>

	<script>
		//学院
		function OnSelectControlChange(sel_obj) {
			var index = sel_obj.selectedIndex;
			var t_school_id = (sel_obj.options[index].value);
			var schooltext = (sel_obj.options[index].text);

			$('#addModal').find('.modal-body #t_school_id').val(t_school_id);
			$('#updateModal').find('.modal-body #t_school_id').val(t_school_id);

			$('#addModal').find('.modal-body #schoolname').text(schooltext);
			$('#updateModal').find('.modal-body #schoolname').text(schooltext);

			var url = "<c:url value='/naturalclass'/>" + "/list.html?t_school_id="
					+ t_school_id;
			window.location.href = url;
		}

		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "${pagedURI}/select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onUpdate(id) {
			var url = "location='${pagedURI}/update-" + id + ".html'";
			$('#updateModal').find('.modal-body #inputid').val(id);

			$('#updateModal').find('.modal-footer #updatebtn').attr("onclick",
					url);

			$('#updateModal').modal('show');

		}

		function onDelete(id, name) {
			var url = "location='<c:url value="/teachingclass/"/>"

			url = url + "DELETE-" + id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			

			$('#deleteModal').modal('show');

		}
	</script>

	<script type="text/javascript">
		$(function() {
			$("#SchoolSelectControlAdd").change(
					function() {
						var selt_school_id = $("#SchoolSelectControlAdd").val();
						var url = "<c:url value="/department/"/>"

						url = url + "selectbyschool-" + selt_school_id + ".json";

						var departmentjson;
						$("#DepartmentSelectControlAdd").empty();
						$.get(url, function(data, status) {
							if (status == "success") {
								for (var i = 0; i < data.length; i++) {
									$("#DepartmentSelectControlAdd").append(
											"<option value='"+data[i].id+"'>"
													+ data[i].name
													+ "</option>");
								}
							}
						});

					});

		});
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

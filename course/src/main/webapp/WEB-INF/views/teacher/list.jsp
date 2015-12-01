<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "teacher");
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




			<div class="CourseContentHeader">教师管理</div>

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
								onclick="onAdd()">增加</button>

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
				<c:when test="${pagedTeacherViewData.totalCount >0}">

					<div class="Course-Table">


						<table class="table table-hover table-bordered"
							style="margin-bottom: -10px;">
							<thead>
								<tr>
									<th style="width: 10%;">#</th>
									<th style="width: 10%;">姓名</th>
									<th style="width: 10%;">工号</th>
									<th style="width: 10%;">部门</th>
									<th style="width: 10%;">性别</th>
									<th style="width: 20%;">联系方式</th>
									<th style="width: 20%;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="index" value="1"></c:set>
								<c:forEach var="dataitem" items="${pagedTeacherViewData.result}">
									<tr>
										<th scope="row"><input type="checkbox" value="">
											${(pagedTeacherViewData.currentPageNo-1) * pagedTeacherViewData.pageSize +index}</th>
										<td>${dataitem.userbasicinfo.user_basic_info_name}</td>
										<td>${dataitem.teacher.teacher_num}</td>
										<td>${dataitem.department.name}</td>
										<td><c:if
												test="${dataitem.userbasicinfo.user_basic_info_sex==0}">
											男
											</c:if> <c:if
												test="${dataitem.userbasicinfo.user_basic_info_sex==1}">
											女
											</c:if></td>
										<td>

											<div class="container-fluid" style="overflow: hidden;">
												<c:forEach var="datauserinfo"
													items="${dataitem.usercontactinfoviewdata}">
													<div class="row">
														<div class="col-md-6">${datauserinfo.usercontacttype.name}</div>
														<div class="col-md-6">${datauserinfo.usercontactinfo.user_contact_value}</div>
													</div>
												</c:forEach>
											</div>





										</td>

										<td><button type="button" class="btn btn-default btn-xs"
												onclick="location='<c:url value="/teacher/update-${dataitem.user.id}.html"/>'">修改...</button>

											<button type="button" class="btn btn-default btn-xs"
												onclick="location='<c:url value="/teacher/setteacherpwd-${selectedt_school_id}-${dataitem.user.id}-${pagedTeacherViewData.currentPageNo }.html"/>'">修改密码...</button>


											<button type="button" class="btn btn-default btn-xs"
												onclick="onDelete('${dataitem.user.id}')">删除</button></td>
									</tr>
									<c:set var="index" value="${index + 1}"></c:set>
								</c:forEach>
							</tbody>
						</table>

						<c:choose>
							<c:when test="${not empty selectedt_school_id }">
								<mathtop:PageBar
									pageUrl="/teacher/list.html?t_school_id=${selectedt_school_id }"
									pageAttrKey="pagedTeacherViewData" />
							</c:when>
							<c:otherwise>
								<mathtop:PageBar pageUrl="/teacher/list.html"
									pageAttrKey="pagedTeacherViewData" />
							</c:otherwise>
						</c:choose>


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

							<a href="<c:url value="/teacher/add.html"/>"
								class="list-group-item">添加教师</a> <a
								href="<c:url value="/student/add.html"/>"
								class="list-group-item">添加单个学生</a> <a
								href="<c:url value="/student/addfromexcel.html"/>"
								class="list-group-item">添加多个学生</a> <a
								href="<c:url value="/permission/"/>" class="list-group-item">为组添加用户</a>

						</div>

					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>










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
		function gett_school_id() {
			var selctrl = document.getElementById("SchoolSelectControl");
			var index = selctrl.selectedIndex;
			var t_school_id = (selctrl.options[index].value);
			return t_school_id;
		}
		//学院
		function OnSelectControlChange(sel_obj) {
			var index = sel_obj.selectedIndex;
			var t_school_id = (sel_obj.options[index].value);
			var schooltext = (sel_obj.options[index].text);

			$('#addModal').find('.modal-body #t_school_id').val(t_school_id);
			$('#updateModal').find('.modal-body #t_school_id').val(t_school_id);

			$('#addModal').find('.modal-body #schoolname').text(schooltext);
			$('#updateModal').find('.modal-body #schoolname').text(schooltext);

			var url = "teacher/list.html?t_school_id=" + t_school_id;
			window.location.href = url;
		}

		function onAdd() {
			var url = "<c:url value='/teacher/add-'/>" + gett_school_id()
					+ ".html";
			window.location.href = url;
		}

		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onUpdate(id) {
			var url = "location='<c:url value="/update-"/>" + id + ".html'";
			$('#updateModal').find('.modal-body #inputid').val(id);
			$('#updateModal').find('.modal-body #inputname').val(name);
			$('#updateModal').find('.modal-body #inputnote').val(note);
			$('#updateModal').find('.modal-footer #updatebtn').attr("onclick",
					url);

			$('#updateModal').modal('show');

		}

		function onDelete(t_department_id) {
			var t_school_id = gett_school_id();
			var url = "location='<c:url value="/DELETE-"/>" + t_school_id + "-"
					+ t_department_id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(
					departmentname);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			alert(url);

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

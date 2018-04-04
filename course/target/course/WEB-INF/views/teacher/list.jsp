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




			<div class="CourseContentHeader">教师管理</div>

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
							<button type="button" class="btn btn-default btn-sm" onclick="onAdd()">增加</button>

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

					<c:if test="${pagedTeacherViewData!=null and pagedTeacherViewData.totalCount>10}">

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
										<li><a href="teacher/list.html?pageSize=10">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==10}">
													<i class="icon-ok"></i>
												</c:if>
												默认(10)
											</a></li>
										<li class="divider"></li>
										<li><a href="teacher/list.html?pageSize=20">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==20}">
													<i class="icon-ok"></i>
												</c:if>
												20
											</a></li>
										<li><a href="teacher/list.html?pageSize=30">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==30}">
													<i class="icon-ok"></i>
												</c:if>
												30
											</a></li>
										<li><a href="teacher/list.html?pageSize=40">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==40}">
													<i class="icon-ok"></i>
												</c:if>
												40
											</a></li>
										<li><a href="teacher/list.html?pageSize=50">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==50}">
													<i class="icon-ok"></i>
												</c:if>
												50
											</a></li>
										<li><a href="teacher/list.html?pageSize=60">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==60}">
													<i class="icon-ok"></i>
												</c:if>
												60
											</a></li>
										<li><a href="teacher/list.html?pageSize=70">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==70}">
													<i class="icon-ok"></i>
												</c:if>
												70
											</a></li>
										<li><a href="teacher/list.html?pageSize=80">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==80}">
													<i class="icon-ok"></i>
												</c:if>
												80
											</a></li>
										<li><a href="teacher/list.html?pageSize=90">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==90}">
													<i class="icon-ok"></i>
												</c:if>
												90
											</a></li>
										<li><a href="teacher/list.html?pageSize=100">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==100}">
													<i class="icon-ok"></i>
												</c:if>
												100
											</a></li>
										<li class="divider"></li>
										<li><a href="teacher/list.html?pageSize=1000">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize>100}">
													<i class="icon-ok"></i>
												</c:if>
												不分页
											</a></li>
									</ul>
								</div>
							</div></td>
					</c:if>
				</tr>
			</table>


			<c:choose>
				<c:when test="${pagedTeacherViewData.totalCount >0}">

					<div class="Course-Table">
						<div class="gridseparator"></div>

						<div class="container-fluid">
							<div class="row">
								<div class="col-md-1">
									<strong>#</strong>
								</div>
								<div class="col-md-2">
									<strong>姓名</strong>
								</div>
								<div class="col-md-2">
									<strong>工号</strong>
								</div>
								<div class="col-md-2">
									<strong>部门</strong>
								</div>
								<div class="col-md-1">
									<strong>性别</strong>
								</div>
								<div class="col-md-2">
									<strong>联系方式</strong>
								</div>
								<div class="col-md-2">
									<strong>操作</strong>
								</div>
							</div>

							<div class="gridseparator"></div>

							<c:set var="index" value="1"></c:set>
							<c:forEach var="dataitem" items="${pagedTeacherViewData.result}">
								<div class="row">
									<div class="col-md-1">
										<input type="checkbox" value="">
										${(pagedTeacherViewData.currentPageNo-1) * pagedTeacherViewData.pageSize +index}
									</div>
									<div class="col-md-2">${dataitem.userbasicinfo.userBasicInfoName}</div>
									<div class="col-md-2">${dataitem.teacher.teacherNum}</div>
									<div class="col-md-2">${dataitem.department.name}</div>
									<div class="col-md-1">
										<c:if test="${dataitem.userbasicinfo.userBasicInfoSex==0}">
											男
											</c:if>
										<c:if test="${dataitem.userbasicinfo.userBasicInfoSex==1}">
											女
											</c:if>
									</div>
									<div class="col-md-2">

										<div class="container-fluid" style="overflow: hidden;">
											<c:forEach var="datauserinfo" items="${dataitem.usercontactinfoviewdata}">
												<div class="row">
													<div class="col-md-6">${datauserinfo.usercontacttype.name}</div>
													<div class="col-md-6">${datauserinfo.usercontactinfo.userContactValue}</div>
												</div>
											</c:forEach>
										</div>

									</div>

									<div class="col-md-2">
										<button type="button" class="btn btn-default btn-xs"
											onclick="location='<c:url value="/teacher/update-${dataitem.user.id}.html"/>'">修改...</button>

										<button type="button" class="btn btn-default btn-xs"
											onclick="location='<c:url value="/teacher/setteacherpwd-${selectedt_school_id}-${dataitem.user.id}-${pagedTeacherViewData.currentPageNo }.html"/>'">修改密码...</button>


										<button type="button" class="btn btn-default btn-xs"
											onclick="onDelete('${dataitem.user.id}','${dataitem.userbasicinfo.userBasicInfoName}','${dataitem.teacher.teacherNum}')">删除</button>
									</div>
								</div>
								<c:set var="index" value="${index + 1}"></c:set>
							</c:forEach>
							<div class="gridseparator"></div>
						</div>


						<c:choose>
							<c:when test="${not empty selectedt_school_id }">
								<mathtop:PageBar pageUrl="/teacher/list.html?t_school_id=${selectedt_school_id }"
									pageAttrKey="pagedTeacherViewData" />
							</c:when>
							<c:otherwise>
								<mathtop:PageBar pageUrl="/teacher/list.html" pageAttrKey="pagedTeacherViewData" />
							</c:otherwise>
						</c:choose>


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

							<a href="<c:url value="/teacher/add.html"/>" class="list-group-item">添加教师</a>
							<a href="<c:url value="/student/add.html"/>" class="list-group-item">添加单个学生</a>
							<a href="<c:url value="/student/addfromexcel.html"/>" class="list-group-item">添加多个学生</a>
							<a href="<c:url value="/permission/"/>" class="list-group-item">为组添加用户</a>

						</div>

					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>












	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/sysLastInclude.jsp"%>


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

		function onDelete(t_user_id, name, teacher_num) {

			var url = "location='<c:url value="teacher/delete-"/>" + t_user_id
					+ ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(
					name + "(" + teacher_num + ")");
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}
	</script>




</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "student");
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




			<div class="CourseContentHeader">学生管理</div>

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
					<td>
						<div class="input-group input-group-sm">
							<select id="departmentSelectControl" name="t_department_id"
								class="form-control input-sm">
								<c:forEach var="d" items="${pagedDepartment.result}">
									<option value="${d.id}">${d.name}</option>
								</c:forEach>

							</select>
						</div>
					</td>

					<td style="width: 10px;"></td>
					<td>
						<div class="input-group input-group-sm">
							<select id="naturalclassSelectControl" name="t_natural_class_id"
								class="form-control input-sm"
								onchange="OnNaturalClassSelectControlChange(this)">
								<c:forEach var="d" items="${pagedNaturalclass.result}">
									<option value="${d.id}">${d.name}</option>
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
								onclick="window.location.href='student/add.html'">增加一个学生</button>
							<button type="button" class="btn btn-default btn-sm"
								onclick="window.location.href='student/addfromexcel.html'">从Excel导入学生</button>

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
				<c:when test="${pagedStudentViewData.totalCount >0}">

					<div class="Course-Table">


						<table class="table table-hover table-bordered"
							style="margin-bottom: -10px;">
							<thead>
								<tr>
									<th style="width: 10%;">#</th>
									<th style="width: 10%;">学号</th>
									<th style="width: 10%;">姓名</th>
									<th style="width: 10%;">系部</th>
									<th style="width: 10%;">班级</th>
									<th style="width: 10%;">性别</th>
									<th style="width: 10%;">生日</th>
									<th style="width: 20%;">联系方式</th>
									<th style="width: 20%;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="index" value="1"></c:set>
								<c:forEach var="dataitem" items="${pagedStudentViewData.result}">
									<tr>
										<th scope="row"><input type="checkbox" value="">
											${(pagedStudentViewData.currentPageNo-1) * pagedStudentViewData.pageSize +index}</th>
										<td>${dataitem.student.student_num}</td>
										<td>${dataitem.userbasicinfo.user_basic_info_name}</td>
										<td>${dataitem.department.name}</td>
										<td>${dataitem.naturalclass.name}</td>
										<td><c:if
												test="${dataitem.userbasicinfo.user_basic_info_sex==0}">
											男
											</c:if> <c:if
												test="${dataitem.userbasicinfo.user_basic_info_sex==1}">
											女
											</c:if></td>
										<td><fmt:formatDate
												value="${dataitem.userbasicinfo.user_basic_info_birthday}"
												pattern="yyyy-MM-dd" /></td>
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
												onclick="location='<c:url value="/student/update-${selectedt_school_id}-${selectedt_department_id}-${selectedt_natural_class_id}-${dataitem.student.id}.html"/>'">修改...</button>
											<button type="button" class="btn btn-default btn-xs"
												onclick="onDelete('${dataitem.student.id}','${dataitem.student.student_num}','${dataitem.userbasicinfo.user_basic_info_name}','${dataitem.naturalclass.name}')">删除</button></td>
									</tr>
									<c:set var="index" value="${index + 1}"></c:set>
								</c:forEach>
							</tbody>
						</table>





						<c:if
							test="${not empty selectedt_school_id and not empty selectedt_department_id and not empty selectedt_natural_class_id}">
							<mathtop:PageBar
								pageUrl="/student/list.html?t_school_id=${selectedt_school_id} &t_department_id=${selectedt_department_id }&t_natural_class_id=${selectedt_natural_class_id}"
								pageAttrKey="pagedStudentViewData" />
						</c:if>



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
								href="<c:url value="/permission/group-user.html"/>"
								class="list-group-item">为组添加用户</a> <a
								href="<c:url value="/naturalclass/list.html"/>"
								class="list-group-item">添加自然班</a>

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
					<h4 class="modal-title" id="myModalLabel">添加系部</h4>
				</div>

				<form class="form-signin"
					action="<c:url value="/department/add.html"/>" method="post">


					<div class="modal-body">
						<h4 class="form-signin-heading">
							请为<span class="text-danger" id="schoolname"></span>输入新加系部名称
						</h4>
						<INPUT type="hidden" id="t_school_id" name="t_school_id"
							value="${selectedt_school_id}"> <label for="inputname"
							class="sr-only">系部名称</label> <input type="text" id="inputname"
							class="form-control" name="name" placeholder="系部名称" required
							autofocus> <label for="inputnote" class="sr-only">系部描述</label>
						<input type="text" id="inputnote" class="form-control" name="note"
							placeholder="系部描述">

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
					<h4 class="modal-title" id="myModalLabel">修改系部</h4>
				</div>

				<form class="form-signin"
					action="<c:url value="/department/update.html"/>" method="post">


					<div class="modal-body">
						<h4 class="form-signin-heading">
							请修改<span class="text-danger" id="schoolname"></span>系部属性
						</h4>

						<INPUT type="hidden" id="t_school_id" name="t_school_id"
							value="${selectedt_school_id}"> <label for="inputname"
							class="sr-only">系部名称</label> <input type="text" id="inputid"
							class="form-control" name="id" value="" placeholder="id"
							style="display: none;"> <input type="text" id="inputname"
							class="form-control" name="name" value="" placeholder="系部名称"
							required autofocus> <label for="inputnote"
							class="sr-only">系部描述</label> <input type="text" id="inputnote"
							class="form-control" name="note" value="" placeholder="系部描述">

					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">修改</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>

				</form>
			</div>
		</div>
	</div>




	<%@ include file="../../shared/dialog.jsp"%>


	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>







	<script>
	
	//学院
	function OnNaturalClassSelectControlChange(sel_obj) {
		var t_school_id = $("#SchoolSelectControl").val();
		var t_department_id = $("#departmentSelectControl").val();
		var t_natural_class_id = $("#naturalclassSelectControl").val();	
		
		var url = "<c:url value='/student'/>"+"/list.html?t_school_id=" + t_school_id+"&t_department_id="+t_department_id+"&t_natural_class_id="+t_natural_class_id;
		
		
	//	var pagebarctrl = document.getElementsByTagName("mathtop:PageBar");
	//	alert(pagebarctrl);
		
	//	pagebarctrl.setAttribute("pageUrl", url);
		
		window.location.href=url;
	}
	
		function gett_school_id() {
			var selctrl = document.getElementById("SchoolSelectControl");
			var index = selctrl.selectedIndex;
			var t_school_id = (selctrl.options[index].value);
			return t_school_id;
		}
		

		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "department/select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		

		function onDelete(studentid, student_num,student_name,student_natural_class_name) {
			var t_school_id = gett_school_id();
			var url ="location='student/DELETE-${selectedt_school_id}-${selectedt_department_id}-${selectedt_natural_class_id}-" + studentid  + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(
					student_natural_class_name+"班学号为"+student_name+"的"+student_num);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			

			$('#deleteModal').modal('show');

		}
	</script>




	<script>
		$(function() {

			var ctrlschool = document.getElementById("SchoolSelectControl");
			var ctrldepartment = document.getElementById("departmentSelectControl");
			var ctrlnaturalclass = document.getElementById("naturalclassSelectControl");
			
			var t_school_id = "${selectedt_school_id}";
			var t_department_id = "${selectedt_department_id}";
			var t_natural_class_id = "${selectedt_natural_class_id}";
			
			
			
			var index = 0;

			for (var i = 0; i < ctrlschool.options.length; i++)
				if (ctrlschool.options[i].value == (t_school_id))
					index = i;
			ctrlschool.selectedIndex = index;
			
			
			
			index = 0;
			
			for (var i = 0; i < ctrldepartment.options.length; i++)
				if (ctrldepartment.options[i].value == (t_department_id))
					index = i;
			ctrldepartment.selectedIndex = index;
			
			
			
			index = 0;
			
			for (var i = 0; i < ctrlnaturalclass.options.length; i++)
				if (ctrlnaturalclass.options[i].value == (t_natural_class_id))
					index = i;
			ctrlnaturalclass.selectedIndex = index;
			
			
			
		//	var url = "<c:url value='/student'/>"+"/list.html?t_school_id=" + t_school_id+"&t_department_id="+t_department_id+"&t_natural_class_id="+t_natural_class_id;
		//	alert(url);
		//	window.location.href=url;
			
			});
	</script>


	<script type="text/javascript">
		$(function() {
			
			<%
			//学院发生变化
			%>
			$("#SchoolSelectControl").change(
					function() {
						var selt_school_id = $("#SchoolSelectControl").val();
						var url = "<c:url value="/department/"/>"

						url = url + "selectbyschool-" + selt_school_id + ".json";

						var departmentjson;
						$("#departmentSelectControl").empty();
						$.get(url, function(data, status) {
							if (status == "success") {

								for (var i = 0; i < data.length; i++) {
									$("#departmentSelectControl").append(
											"<option value='"+data[i].id+"'>"
													+ data[i].name
													+ "</option>");
								}
							}
						});

					});
			
			<%
			//系部发生变化
			%>
			$("#departmentSelectControl").change(
					function() {
						var t_department_id = $("#departmentSelectControl").val();
						var url = "<c:url value="/naturalclass/"/>"

						url = url + "selectbyt_department_id-" + t_department_id + ".json";

						var naturalclassjson;
						$("#naturalclassSelectControl").empty();
						$.get(url, function(data, status) {
							if (status == "success") {

								for (var i = 0; i < data.length; i++) {
									$("#naturalclassSelectControl").append(
											"<option value='"+data[i].id+"'>"
													+ data[i].name
													+ "</option>");
								}
							}
						});

					});

		});
	</script>


	<c:if test="${errorMsg!=null}">
		<script>
			function ShowErrMsg() {
				ShowInfoMsg("${errorMsg}");

			}
			ShowErrMsg();
		
		</script>

	</c:if>

	<c:set var="errorMsg" value="null" />

</body>
</html>

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




			<div class="CourseContentHeader">
				<span class="text-info"><strong>${pagedTeachingClassViewData.result[0].courseTeachingClass.name}</strong></span>教学班教师管理
			</div>
			<p class="text-muted">
				<strong>课程名称：</strong>${pagedTeachingClassViewData.result[0].course.name}</p>

			<p class="text-muted">
				<strong>授课教师：</strong>

				<c:forEach var="t"
					items="${pagedTeachingClassViewData.result[0].teacher}"
					varStatus="status">
							${t.userbasicinfo.userBasicInfoName}(${pagedTeachingClassViewData.result[0].teachingtype[status.index].name})
							</c:forEach>

			</p>

			<p class="text-muted">
				<strong>授课学期：</strong>${pagedTeachingClassViewData.result[0].term.teachingYearBegin}-${pagedTeachingClassViewData.result[0].term.teachingYearEnd}学年第${pagedTeachingClassViewData.result[0].term.teachingTerm}学期</p>
			<div class="CourseContentHeaderSeparatorLine"></div>


			<div style="display: none;" id="teachingtypeContainer">
				<select id="teachingtypeSelectControl" class="form-control input-sm"
					name="teachingtypetypeId">
					<c:forEach var="t" items="${pagedTeachingType.result}">
						<option value="${t.id}">${t.name}</option>
					</c:forEach>
				</select>
			</div>


			<c:choose>
				<c:when test="${pagedTeacherCount >0}">

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
										data-toggle="modal" data-target="#addModal">增加授课教师</button>



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




					<form class="form-horizontal" style="overflow: hidden;" 
						action="<c:url value="/teachingclass/addteacher2teachingclass.html"/>"
						method="post">




						<div class="form-group">

							<input id='selectedTeachingClassID'
								name='selectedTeachingClassID'
								value='${pagedTeachingClassViewData.result[0].courseTeachingClass.id}' type='hidden' />



							<div class="col-md-12">


								<div class="Course-Table">


									<table id="teachertable"
										class="table table-hover table-bordered">
										<thead>
											<tr>
												<th style="width: 10%;">#</th>
												<th style="width: 10%;">学院</th>
												<th style="width: 20%;">姓名</th>
												<th style="width: 30%;">授课类型</th>
												<th style="width: 10%;">操作</th>
											</tr>
										</thead>
										<tbody>
											<c:set var="index" value="1"></c:set>

											<c:forEach var="dataitem"
												items="${pagedTeachingClassViewData.result[0].teacher}"
												varStatus="status">
												<tr id="teacherrow${dataitem.teacher.id}">
													<th scope="row"><input type="checkbox" value="">
														${index}</th>

													<td>${dataitem.school.name}</td>
													<!-- 授课教师 -->
													<td><input id='teacherid' name='teacherid'
														value='${dataitem.teacher.id}' type='hidden' />
														${dataitem.userbasicinfo.userBasicInfoName}(${dataitem.teacher.teacherNum},${dataitem.department.name})</td>

													<td><input id='teachingtypeid' name='teachingtypeid'
														value='${pagedTeachingClassViewData.result[0].teachingtype[status.index].id}'
														type='hidden' />
														${pagedTeachingClassViewData.result[0].teachingtype[status.index].name}


													</td>


													<td><button type="button"
															class="btn btn-default btn-xs"
															onclick="DeleteTeacherRow('${dataitem.teacher.id}')">删除</button></td>
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

						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">确定</button>
							<button type="button" class="btn btn-default"
								onclick="window.history.back()">取消</button>
						</div>
					</form>

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
					<h4 class="modal-title" id="myModalLabel">为教学班添加教师</h4>
				</div>






				<div class="modal-body">
					<h4 class="form-signin-heading">请输入新加教师名称</h4>

					<div class="container-fluid" style="overflow: hidden;">
						<div class="row">
							<div class="col-md-2">学院</div>
							<div class="col-md-10">
								<select id="SchoolSelectControlAdd" class="form-control"
									name="t_school_id" onchange="OnSchoolSelectControlAddChange(this)">
									<c:forEach var="school" items="${pagedSchool.result}">
										<option value="${school.id}">${school.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="row">
							<div class="col-md-2">教师</div>
							<div class="col-md-10">
								<select id="teacherSelectControlAdd" class="form-control"
									name="teacherId">
									<c:forEach var="t" items="${pagedTeacherViewData.result}">
										<option value="${t.id}">${t.teachername}(${t.teachernum},${t.deptname})</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="row">
							<div class="col-md-2">授课类型</div>
							<div class="col-md-10">
								<select id="teachingtypeSelectControlAdd" class="form-control"
									name="teachingtypeId">
									<c:forEach var="t" items="${pagedTeachingType.result}">
										<option value="${t.id}">${t.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>






					</div>


				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary"
						onclick="AddTeacherRow()">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>

			</div>
		</div>
	</div>

<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/sysLastInclude.jsp"%>

	
	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>


	<script>
		var selt_school_id = $("#SchoolSelectControlAdd").val();
		var url = "<c:url value="/teacher/"/>"

		url = url + "selectbyschool-" + selt_school_id + ".json";
		$("#teacherSelectControlAdd").empty();
		$.get(url, function(data, status) {
			if (status == "success") {
				for (var i = 0; i < data.length; i++) {
					$("#teacherSelectControlAdd").append(
							"<option value='"+data[i].teacher.id+"'>"
							+ data[i].userbasicinfo.userBasicInfoName
							 +"(" + data[i].teacher.teacherNum
							+ "," + data[i].department.name
							 + ")</option>");
				}
			}
		});
	</script>
	<script>
	
	    
	    
		function AddTeacherRow() {

			var schoolctl = document.getElementById("SchoolSelectControlAdd");
			var teacher = document.getElementById("teacherSelectControlAdd");
			var teachingtype = document
					.getElementById("teachingtypeSelectControlAdd");

			if (IsExistInTeacherTable(teacher.value, teachingtype.value) == true) {
				$('#addModal').modal('hide');
				ShowInfoMsg("教师在该课程中授课类型已经存在");
				return;
			}

			var table = document.getElementById("teachertable");
			var trCnt = table.tBodies[0].rows.length;
			var row = table.tBodies[0].insertRow(trCnt);

			var td0 = row.insertCell(0);
			var td1 = row.insertCell(1);
			var td2 = row.insertCell(2);
			var td3 = row.insertCell(3);
			var td4 = row.insertCell(4);

			td1.innerHTML = schoolctl.options[schoolctl.selectedIndex].text;
			td2.innerHTML = "<input id='teacherid' name='teacherid' value='"+teacher.value+"' type='hidden' />"
					+ teacher.options[teacher.selectedIndex].text;

			td3.innerHTML = "<input  id='teachingtypeid' name='teachingtypeid' value='"+teachingtype.value+"' type='hidden' /> "
					+ teachingtype.options[teachingtype.selectedIndex].text;

			td4.innerHTML = "<button type='button' class='btn btn-default btn-xs'	onclick='DeleteTeacherRow("
					+ trCnt + ")'>删除</button>";

			trCnt = table.tBodies[0].rows.length;
			for (var i = 0; i < trCnt; i++) {
				table.tBodies[0].rows[i].cells[0].innerHTML = i + 1;

				table.tBodies[0].rows[i].id = "teacherrow" + i;
			}

			$('#addModal').modal('hide');

		}

		function DeleteTeacherRow(i) {
			var table = document.getElementById("teachertable");
			var trCnt = table.tBodies[0].rows.length;
			var rowid = "teacherrow" + i;
			for (var i = 0; i < trCnt; i++) {
				if (table.tBodies[0].rows[i].id == rowid)
					table.tBodies[0].deleteRow(i);
			}

		}
	<%//教师表格中是否存在学号为teacherid的学生，如果存在返回true，否则返回false%>
		function IsExistInTeacherTable(teacherid, teachingtypeid) {

			var x = $("#teachertable #teacherid").toArray();
			var y = $("#teachertable #teachingtypeid").toArray();
			for (i = 0; i < x.length; i++) {

				if (x[i].value == teacherid && y[i].value == teachingtypeid)
					return true;
			}

			return false;
		}
	</script>

	<script type="text/javascript">
		$(function() {
			$("#SchoolSelectControlAdd").change(
					function() {
						var selt_school_id = $("#SchoolSelectControlAdd").val();
						var url = "<c:url value="/teacher/"/>"

						url = url + "selectbyschool-" + selt_school_id + ".json";

						var departmentjson;
						$("#teacherSelectControlAdd").empty();
						$.get(url, function(data, status) {
							
							if (status == "success") {
								for (var i = 0; i < data.length; i++) {
									$("#teacherSelectControlAdd").append(
											"<option value='"+data[i].teacher.id+"'>"
											+ data[i].userbasicinfo.userBasicInfoName
											 +"(" + data[i].teacher.teacherNum
											+ "," + data[i].department.name
											 +")</option>");
								}
							}
						});

					});

		});
	</script>



</body>
</html>

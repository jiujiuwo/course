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
								data-toggle="modal" data-target="#addModal">增加自然班</button>

							<button type="button" class="btn btn-default btn-sm"
								data-toggle="modal" data-target="#addStudentModal">增加单个学生</button>

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


			<form class="form-horizontal" style="overflow: hidden;"
				action="<c:url value="/teachingclass/addstudent2teachingclass.html"/>"
				method="post">


				<div class="form-group">
					<input id='selectedTeachingClassID' name='selectedTeachingClassID'
						value='${selectedTeachingClassID}' type='hidden' />


					<div class="col-md-12">


						<div class="Course-Table">


							<table id="studenttable" class="table table-hover table-bordered">
								<thead>
									<tr>
										<th style="width: 10%;">#</th>
										<th style="width: 20%;">学号</th>
										<th style="width: 30%;">姓名</th>
										<th style="width: 10%;">操作</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>





				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">添加</button>
					<button type="button" class="btn btn-default"
						onclick="window.history.back()">取消</button>
				</div>

			</form>
		</div>
	</div>



	<%
		//添加自然班全体学生对话框
	%>
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

				<div class="modal-body">
					<h4 class="form-signin-heading">请输入新加自然班名称</h4>

					<div class="container-fluid" style="overflow: hidden;">
						<div class="row">
							<div class="col-md-2">学院</div>
							<div class="col-md-10">
								<select id="SchoolSelectControl" class="form-control"
									name="t_school_id">
									<c:forEach var="school" items="${pagedSchool.result}">
										<option value="${school.id}">${school.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="row">
							<div class="col-md-2">系部</div>
							<div class="col-md-10">
								<select id="departmentSelectControl" class="form-control"
									name="t_department_id">
									<c:forEach var="d" items="${pagedDepartment.result}">
										<option value="${d.id}">${d.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class="row">
							<div class="col-md-2">班级</div>
							<div class="col-md-10">
								<table id="naturalclasstable"
									class="table table-hover table-bordered">
									<thead>
										<tr>
											<th style="width: 10%;">#</th>
											<th style="width: 20%;">班级</th>
											<th style="width: 10%;">操作</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>
						</div>



					</div>


				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="onNaturalclassAdd()">添加</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>


			</div>
		</div>
	</div>

	<%
		//添加单个学生对话框
	%>
	<div class="modal fade" id="addStudentModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加学生</h4>
				</div>

				<div class="modal-body">
					<h4 class="form-signin-heading">请选择新加学生</h4>

					<div class="container-fluid" style="overflow: hidden;">
						<div class="row">
							<div class="col-md-2">学院</div>
							<div class="col-md-10">
								<select id="studentSchoolSelectControl" class="form-control"
									name="studentt_school_id">

								</select>
							</div>
						</div>

						<div class="row">
							<div class="col-md-2">系部</div>
							<div class="col-md-10">
								<select id="studentdepartmentSelectControl" class="form-control"
									name="studentt_department_id">

								</select>
							</div>
						</div>

						<div class="row">
							<div class="col-md-2">系部</div>
							<div class="col-md-10">
								<select id="naturalclassSelectControl" class="form-control"
									name="t_natural_class_id">

								</select>
							</div>
						</div>


						<div class="row">
							<div class="col-md-2">学生</div>
							<div class="col-md-10">
								<table id="naturalclassstudenttable"
									class="table table-hover table-bordered">
									<thead>
										<tr>
											<th style="width: 10%;">#</th>
											<th style="width: 20%;">学号</th>
											<th style="width: 10%;">姓名</th>
											<th style="width: 10%;">操作</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>
						</div>



					</div>


				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="onNaturalclassStudentAdd()">添加</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>


			</div>
		</div>
	</div>


	<%@ include file="../../shared/dialog.jsp"%>


	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>






	<script>
	
	//系部
	function OnDepartmentChange() {

		var t_department_id = $("#departmentSelectControl").val();
		var url = "<c:url value="/naturalclass/"/>"

		url = url + "selectbyt_department_id-" + t_department_id + ".json";

		$.get(url, function(data, status) {
			if (status == "success") {

				for (var i = 0; i < data.length; i++) {

					AddNaturalClassRow(data[i].id, data[i].name);
				}
			}
		});
	}
	
		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onUpdate(id, name, note) {
			var url = "location='<c:url value="/update-"/>" + id + ".html'";
			$('#updateModal').find('.modal-body #inputid').val(id);
			$('#updateModal').find('.modal-body #inputname').val(name);
			$('#updateModal').find('.modal-body #inputnote').val(note);
			$('#updateModal').find('.modal-footer #updatebtn').attr("onclick",
					url);

			$('#updateModal').modal('show');

		}

		function onDelete(t_department_id, departmentname) {
			var t_school_id = gett_school_id();
			var url = "location='<c:url value="/DELETE-"/>" + t_school_id + "-" + t_department_id
					+ ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(
					departmentname);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			alert(url);

			$('#deleteModal').modal('show');

		}
	<%//%>
		function onNaturalclassAdd() {

			var c = $("#naturalclasstable #naturalclasscheckbox").toArray();
			var x = $("#naturalclasstable #t_natural_class_id").toArray();
			for (i = 0; i < c.length; i++) {
				if (c[i].checked) {

					var natruralclassid = x[i].value;
					var url = "<c:url value="/student/"/>"

					url = url + "selectbyt_natural_class_id-" + natruralclassid
							+ ".json";

					$.get(url, function(data, status) {
						if (status == "success") {

							for (var i = 0; i < data.length; i++) {

								
								
								AddStudentRow(data[i].student.id,
										data[i].student.student_num, data[i].userbasicinfo.user_basic_info_name);
							}
						}
					});

				}
			}

			$('#addModal').modal('hide');
		}

		function onNaturalclassStudentAdd() {
			var c = $("#naturalclassstudenttable #naturalclassstudentcheckbox")
					.toArray();
			var ids = $("#naturalclassstudenttable #naturalclassstudentid")
					.toArray();
			var nums = $("#naturalclassstudenttable #naturalclassstudentnum")
					.toArray();
			var names = $("#naturalclassstudenttable #naturalclassstudentname")
					.toArray();
			for (i = 0; i < c.length; i++) {
				if (c[i].checked) {

					AddStudentRow(ids[i].value, nums[i].value, names[i].value);

				}
			}

			$('#addStudentModal').modal('hide');
		}

		function 
		AddStudentRow(studentid, studentnum, studentname) {

			if (IsExistInStudentTable(studentid) == true)
				return;

			var table = document.getElementById("studenttable");
			var trCnt = table.tBodies[0].rows.length;
			var row = table.tBodies[0].insertRow(trCnt);

			var td0 = row.insertCell(0);
			var td1 = row.insertCell(1);
			var td2 = row.insertCell(2);
			var td3 = row.insertCell(3);
			var td4 = row.insertCell(4);

			td1.innerHTML = "<input  type='text' class='form-control' value='"+studentnum+"' readonly/>";
			td2.innerHTML = "<input id='studentid' name='studentid' value='"+studentid+"' type='hidden' /> <input  type='text' class='form-control' value='"+studentname+"' readonly/>";

			td3.innerHTML = "<button type='button' class='btn btn-default btn-sm'	onclick='DeleteStudentRow("
					+ trCnt + ")'>删除</button>";

			trCnt = table.tBodies[0].rows.length;
			for (var i = 0; i < trCnt; i++) {
				table.tBodies[0].rows[i].cells[0].innerHTML = i + 1;

				table.tBodies[0].rows[i].id = "studentrow" + i;
			}

		}
	<%//学生表格中是否存在学号为studentid的学生，如果存在返回true，否则返回false%>
		function IsExistInStudentTable(studentid) {

			var x = $("#studenttable #studentid").toArray();
			for (i = 0; i < x.length; i++) {
				if (x[i].value == studentid)
					return true;
			}

			return false;
		}
	<%//删除学生表格中指定的一行%>
		function DeleteStudentRow(i) {
			var table = document.getElementById("studenttable");
			var trCnt = table.tBodies[0].rows.length;
			var rowid = "studentrow" + i;
			for (var i = 0; i < trCnt; i++) {
				if (table.tBodies[0].rows[i].id == rowid)
					table.tBodies[0].deleteRow(i);
			}

		}
	<%/****************************************************************************************************
			 *添加学生对话框
			 */%>
		function AddNaturalClassStudentRow(studentid, studentnum, studentname) {

			if (IsExistInNaturalClassStudentTable(studentid) == true)
				return;
			
			

			var table = document.getElementById("naturalclassstudenttable");
			var trCnt = table.tBodies[0].rows.length;
			var row = table.tBodies[0].insertRow(trCnt);

			var td0 = row.insertCell(0);
			var td1 = row.insertCell(1);
			var td2 = row.insertCell(2);
			var td3 = row.insertCell(3);

			td1.innerHTML = "<input id='naturalclassstudentid' value='"+studentid+"' type='hidden' /> <input  id='naturalclassstudentnum' type='text' class='form-control' value='"+studentnum+"' readonly/>";
			td2.innerHTML = "<input  id='naturalclassstudentname' type='text' class='form-control' value='"+studentname+"' readonly/>";

			td3.innerHTML = "<button type='button' class='btn btn-default btn-sm'	onclick='DeleteNaturalClassStudentRow("
					+ trCnt + ")'>删除</button>";

			trCnt = table.tBodies[0].rows.length;
			for (var i = 0; i < trCnt; i++) {
				table.tBodies[0].rows[i].cells[0].innerHTML = i
						+ 1
						+ "<input id='naturalclassstudentcheckbox' type='checkbox' value=''>";

				table.tBodies[0].rows[i].id = "naturalclassstudentrow" + i;
			}
		}
	<%//判断学生是否在对话框的学生表中存在%>
		function IsExistInNaturalClassStudentTable(studentid) {

			var x = $("#naturalclassstudenttable #naturalclassstudentid")
					.toArray();
			for (i = 0; i < x.length; i++) {
				if (x[i].value == studentid)
					return true;
			}

			return false;
		}
	<%//删除对话框中学生行%>
		function DeleteNaturalClassStudentRow(i) {
			var table = document.getElementById("naturalclassstudenttable");
			var trCnt = table.tBodies[0].rows.length;
			var rowid = "naturalclassstudentrow" + i;
			for (var i = 0; i < trCnt; i++) {
				if (table.tBodies[0].rows[i].id == rowid)
					table.tBodies[0].deleteRow(i);
			}

		}
	<%/****************************************************************************************************
			 *添加自然班学生对话框
			 */%>
		function AddNaturalClassRow(t_natural_class_id, naturalclassname) {
			if (IsExistInNaturalClassTable(t_natural_class_id) == true)
				return;

			var table = document.getElementById("naturalclasstable");
			var trCnt = table.tBodies[0].rows.length;
			var row = table.tBodies[0].insertRow(trCnt);

			var td0 = row.insertCell(0);
			var td1 = row.insertCell(1);
			var td2 = row.insertCell(2);

			td1.innerHTML = "<input id='t_natural_class_id' value='"+t_natural_class_id+"' type='hidden' /> <input  type='text' class='form-control' value='"+naturalclassname+"' readonly/>";

			td2.innerHTML = "<button type='button' class='btn btn-default btn-sm'	onclick='DeleteNaturalClassRow("
					+ trCnt + ")'>删除</button>";

			trCnt = table.tBodies[0].rows.length;
			for (var i = 0; i < trCnt; i++) {
				table.tBodies[0].rows[i].cells[0].innerHTML = i
						+ 1
						+ "<input id='naturalclasscheckbox' type='checkbox' value='' checked>";

				table.tBodies[0].rows[i].id = "naturalclassrow" + i;
			}
		}

		function IsExistInNaturalClassTable(t_natural_class_id) {

			var x = $("#naturalclasstable #t_natural_class_id").toArray();
			for (i = 0; i < x.length; i++) {
				if (x[i].value == t_natural_class_id)
					return true;
			}

			return false;
		}
	<%//删除自然班行%>
		function DeleteNaturalClassRow(i) {
			var table = document.getElementById("naturalclasstable");
			var trCnt = table.tBodies[0].rows.length;
			var rowid = "naturalclassrow" + i;
			for (var i = 0; i < trCnt; i++) {
				if (table.tBodies[0].rows[i].id == rowid)
					table.tBodies[0].deleteRow(i);
			}

		}
	</script>

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


	<script type="text/javascript">
		
	<%//填充学院%>
		function fillschool() {
			var url = "<c:url value="/school/"/>"
			url = url + "getall.json";

			$("#studentSchoolSelectControl").empty();
			$.get(url, function(data, status) {
				if (status == "success") {
					for (var i = 0; i < data.length; i++) {
						if (i == 0)
							filldepartment(data[i].id);

						$("#studentSchoolSelectControl").append(
								"<option value='"+data[i].id+"'>"
										+ data[i].name + "</option>");
					}
				}
			});
		}
	<%//根据学院填充部门%>
		function filldepartment(selt_school_id) {

			if (selt_school_id == null)
				selt_school_id = $("#studentSchoolSelectControl").val();

			if (selt_school_id == null)
				return;

			var url = "<c:url value="/department/"/>"

			url = url + "selectbyschool-" + selt_school_id + ".json";

			var departmentjson;
			$("#studentdepartmentSelectControl").empty();
			$.get(url, function(data, status) {
				if (status == "success") {
					for (var i = 0; i < data.length; i++) {
						if (i == 0)
							fillnaturalclass(data[i].id);
						$("#studentdepartmentSelectControl").append(
								"<option value='"+data[i].id+"'>"
										+ data[i].name + "</option>");
					}
				}
			});

			return true;
		}
	<%//根据部门填充班级%>
		function fillnaturalclass(t_department_id) {

			if (t_department_id == null)
				t_department_id = $("#studentdepartmentSelectControl").val();

			if (t_department_id == null)
				return;

			var url = "<c:url value="/naturalclass/"/>"

			url = url + "selectbyt_department_id-" + t_department_id + ".json";

			$("#naturalclassSelectControl").empty();

			$.get(url, function(data, status) {
				if (status == "success") {

					for (var i = 0; i < data.length; i++) {

						for (var i = 0; i < data.length; i++) {
							if (i == 0)
								fillstudent(data[i].id);
							$("#naturalclassSelectControl").append(
									"<option value='"+data[i].id+"'>"
											+ data[i].name + "</option>");
						}
					}
				}
			});
		}
	<%//根据学生填充学生%>
		function fillstudent(t_natural_class_id) {
			if (t_natural_class_id == null)
				t_natural_class_id = $("#naturalclassSelectControl").val();

			if (t_natural_class_id == null)
				return;

			var url = "<c:url value="/student/"/>"

			url = url + "selectbyt_natural_class_id-" + t_natural_class_id + ".json";

			$("#naturalclassstudenttable tbody").empty();

			$.get(url, function(data, status) {
				if (status == "success") {

					for (var i = 0; i < data.length; i++) {

						for (var i = 0; i < data.length; i++) {

							AddNaturalClassStudentRow(data[i].student.id,
									data[i].student.student_num, data[i].userbasicinfo.user_basic_info_name);
						}
					}
				}
			});
		}
	<%//addModal对话框显示之后执行，填充学院、部门、班级%>
		$('#addModal').on('show.bs.modal', function(e) {
			var t_department_id = $("#departmentSelectControl").val();
			var url = "<c:url value="/naturalclass/"/>"

			url = url + "selectbyt_department_id-" + t_department_id + ".json";

			$.get(url, function(data, status) {
				if (status == "success") {

					for (var i = 0; i < data.length; i++) {

						AddNaturalClassRow(data[i].id, data[i].name);
					}
				}
			});
		});
	<%//addStudentModal对话框显示之后执行，填充学院、部门、班级和学生%>
		$('#addStudentModal').on('show.bs.modal', function(e) {
			fillschool();
		});

		$(function() {
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
								
								if(data.length>0){
									$("#departmentSelectControl").get(0).selectedIndex = 0;
									
									OnDepartmentChange();
								}
							}
						});

					});

			$("#departmentSelectControl").change(function() {
				OnDepartmentChange();

			});

			$("#studentSchoolSelectControl").change(function() {
				filldepartment();

			});

			$("#studentdepartmentSelectControl").change(function() {
				fillnaturalclass(null);

			});

			$("#naturalclassSelectControl").change(function() {
				fillstudent(null);

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

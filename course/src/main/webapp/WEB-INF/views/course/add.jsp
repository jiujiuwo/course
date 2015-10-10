<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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




			<div class="CourseContentHeader">增加课程</div>

			<div class="CourseContentHeaderSeparatorLine"></div>

			<div style="display: none;" id="schoolContainer">
				<select id="schoolSelectControl" class="form-control input-sm"
					name="t_school_id" onchange="OnSelectControlChange(this)">
					<c:forEach var="school" items="${pagedSchool.result}">
						<option value="${school.id}">${school.name}</option>
					</c:forEach>
				</select>
			</div>


			<div style="display: none;" id="departmentContainer">
				<select id="departmentSelectControl" class="form-control input-sm"
					name="t_department_id">
					<c:forEach var="department" items="${pagedDepartment.result}">
						<option value="${department.id}">${department.name}</option>
					</c:forEach>
				</select>
			</div>

			<div style="display: none;" id="precourseContainer">
				<select id="precourseSelectControl" class="form-control input-sm"
					name="precourseId">
					<c:forEach var="precourse" items="${pagedPreCourse.result}">
						<option value="${precourse.id}">${precourse.name}</option>
					</c:forEach>
				</select>
			</div>


			<form class="form-horizontal" style="overflow: hidden;"
				action="<c:url value="/course/addcourse.html"/>" method="post">


				<div class="form-group">
					<label for="course_num" class="col-sm-1 control-label">课程编号</label>
					<div class="col-sm-3">
						<input name="course_num" class="form-control placeholder-no-fix"
							id="course_num" autocomplete="off" placeholder="课程编号" required />
					</div>

					<label for="course_name" class="col-sm-1 control-label">课程名称</label>
					<div class="col-sm-3">
						<input name="course_name" class="form-control placeholder-no-fix"
							id="course_name" autocomplete="off" placeholder="课程名称" required />
					</div>

					<label for="course_english_name" class="col-sm-1 control-label">课程名称(英文)</label>
					<div class="col-sm-3">
						<input name="course_english_name"
							class="form-control placeholder-no-fix" id="course_english_name"
							autocomplete="off" placeholder="课程名称" required />
					</div>

				</div>

				<div class="form-group">

					<label for="naturalclassSelectControl"
						class="col-sm-1 control-label">课程类别</label>
					<div class="col-md-3">
						<select id="naturalclassSelectControl" name="coursestyleID"
							class="form-control input-sm">
							<c:forEach var="d" items="${pagedCourseStyle.result}">
								<option value="${d.id}">${d.name}</option>
							</c:forEach>
						</select>
					</div>

					<label for="course_type" class="col-sm-1 control-label">课程性质</label>
					<div class="col-md-3">
						<select id="naturalclassSelectControl" name="coursetypeID"
							class="form-control input-sm">
							<c:forEach var="d" items="${pagedCourseType.result}">
								<option value="${d.id}">${d.name}</option>
							</c:forEach>
						</select>
					</div>

				</div>

				<div class="form-group">

					<label for="class_hours" class="col-sm-1 control-label">课堂学时</label>
					<div class="col-md-3">
						<input name="class_hours" id="class_hours"
							class="form-control placeholder-no-fix" value="32"
							autocomplete="off" placeholder="课堂学时" />
					</div>
					<label for="experiment_hours" class="col-sm-1 control-label">实验学时</label>
					<div class="col-md-3">
						<input name="experiment_hours" id="experiment_hours"
							class="form-control placeholder-no-fix" value="16"
							autocomplete="off" placeholder="实验学时" />
					</div>


				</div>

				<div class="form-group">
					<label for="fordepartment" class="col-sm-1 control-label">使用专业</label>
					<div class="col-sm-10" id="fordepartment">
						<div class="btn-group" role="group" aria-label="...">
							<button type="button" class="btn btn-default btn-sm"
								onclick="window.location.href='<c:url value="/department/list.html"/>'">增加专业</button>

							<button type="button" class="btn btn-default btn-sm"
								onclick="AddDepartmentRow()">增加使用专业</button>


						</div>


						<div class="Course-Table">


							<table id="departmenttable"
								class="table table-hover table-bordered">
								<thead>
									<tr>
										<th style="width: 10%;">#</th>
										<th style="width: 20%;">专业</th>
										<th style="width: 20%;">内容</th>
										<th style="width: 20%;">操作</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
				</div>

				<c:if test="${pagedPreCourse.result.size()>0}">


				<div class="form-group">



					<label for="forprecourse" class="col-sm-1 control-label">先修课程</label>
					<div class="col-sm-10" id="forprecourse">
						<div class="btn-group" role="group" aria-label="...">

							<button type="button" class="btn btn-default btn-sm"
								onclick="AddPreCourseRow()">增加先修课程</button>


						</div>


						<div class="Course-Table">


							<table id="precoursetable"
								class="table table-hover table-bordered">
								<thead>
									<tr>
										<th style="width: 10%;">#</th>
										<th style="width: 20%;">课程</th>
										<th style="width: 20%;">内容</th>
										<th style="width: 20%;">操作</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>



				</div>
				
				</c:if>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">添加</button>
					<button type="button" class="btn btn-default"
						onclick="window.history.back()">取消</button>
				</div>

			</form>
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
	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>

	<script>
		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onUpdate(id, name, note) {
			var url = "location='<c:url value="/course/update-"/>" + id + ".html'";
			$('#updateModal').find('.modal-body #inputid').val(id);
			$('#updateModal').find('.modal-body #inputname').val(name);
			$('#updateModal').find('.modal-body #inputnote').val(note);
			$('#updateModal').find('.modal-footer #updatebtn').attr("onclick",
					url);

			$('#updateModal').modal('show');

		}

		function onDelete(t_department_id, departmentname) {
			var t_school_id = gett_school_id();
			var url = "location='<c:url value="/course/DELETE-"/>" + t_school_id + "-" + t_department_id
					+ ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(
					departmentname);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			alert(url);

			$('#deleteModal').modal('show');

		}

		function AddDepartmentRow() {
			var table = document.getElementById("departmenttable");
			var trCnt = table.tBodies[0].rows.length;
			var row = table.tBodies[0].insertRow(trCnt);

			var td0 = row.insertCell(0);
			var td1 = row.insertCell(1);
			var td2 = row.insertCell(2);
			var td3 = row.insertCell(3);

			var cc = $("#departmentContainer").html();
			var cd = $("#schoolContainer").html();

			td1.innerHTML = cd+cc;
			td2.innerHTML = "<input type='text' id='inputnote' class='form-control' name='department_note' value='' placeholder='描述' readonly>";
			td3.innerHTML = "<button type='button' class='btn btn-default btn-sm'	onclick='DeleteDepartmentRow("
					+ trCnt + ")'>删除</button>";

			trCnt = table.tBodies[0].rows.length;
			for (var i = 0; i < trCnt; i++) {
				table.tBodies[0].rows[i].cells[0].innerHTML = i + 1;
				table.tBodies[0].rows[i].id = "departmentrow" + i;
			}

		}

		function DeleteDepartmentRow(i) {
			var table = document.getElementById("departmenttable");
			var trCnt = table.tBodies[0].rows.length;
			var rowid = "departmentrow" + i;
			for (var i = 0; i < trCnt; i++) {
				if (table.tBodies[0].rows[i].id == rowid)
					table.tBodies[0].deleteRow(i);
			}

		}
		
		
		function AddPreCourseRow() {
			var table = document.getElementById("precoursetable");
			var trCnt = table.tBodies[0].rows.length;
			var row = table.tBodies[0].insertRow(trCnt);

			var td0 = row.insertCell(0);
			var td1 = row.insertCell(1);
			var td2 = row.insertCell(2);
			var td3 = row.insertCell(3);

			var cc = $("#precourseContainer").html();
			

			td1.innerHTML = cc;
			td2.innerHTML = "<input type='text' id='inputnote' class='form-control' name='course_name' value='' placeholder='描述' readonly>";
			td3.innerHTML = "<button type='button' class='btn btn-default btn-sm'	onclick='DeletePreCourseRow("
					+ trCnt + ")'>删除</button>";

			trCnt = table.tBodies[0].rows.length;
			for (var i = 0; i < trCnt; i++) {
				table.tBodies[0].rows[i].cells[0].innerHTML = i + 1;
				table.tBodies[0].rows[i].id = "courserow" + i;
			}

		}

		function DeletePreCourseRow(i) {
			var table = document.getElementById("departmenttable");
			var trCnt = table.tBodies[0].rows.length;
			var rowid = "courserow" + i;
			for (var i = 0; i < trCnt; i++) {
				if (table.tBodies[0].rows[i].id == rowid)
					table.tBodies[0].deleteRow(i);
			}

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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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




			<div class="CourseContentHeader">修改学生密码</div>

			<div class="CourseContentHeaderSeparatorLine"></div>

			<div style="display: none;" id="contactinfoContainer">
				<select id="contactinfoSelectControl" class="form-control input-sm"
					name="contacttypeId">
					<c:forEach var="contacttype" items="${pagedContactType.result}">
						<option value="${contacttype.id}">${contacttype.name}</option>
					</c:forEach>
				</select>
			</div>

			<div style="display: none;" id="groupContainer">
				<select id="groupSelectControl" name="groupId"
					class="form-control input-sm">
					<c:forEach var="d" items="${pagedGroup.result}">
						<option value="${d.id}">${d.name}</option>
					</c:forEach>

				</select>
			</div>


			<form class="form-horizontal" style="overflow: hidden;"
				action="<c:url value="/student/updatestudentpwd.html"/>"
				method="post">

				<input type="hidden" name="t_school_id"
					value="${selectedt_school_id }"> <input type="hidden"
					name="t_department_id" value="${selectedt_department_id }">


				<input type="hidden" name="t_natural_class_id"
					value="${selectedt_natural_class_id }">
					
					
					<input type="hidden" name="t_user_id"
					value="${selectedStudentViewData.user.id}">
					
					<input type="hidden" name="pageNo"
					value="${selectedtpageNo}">


				<div class="form-group">
					<label for="SchoolSelectControl" class="col-sm-1 control-label">学院</label>
					<div class="col-md-3">
						<p class="form-control-static">${selectedStudentViewData.school.name }</p>
					</div>

					<label for="departmentSelectControl" class="col-sm-1 control-label">系部</label>
					<div class="col-md-3">
						<p class="form-control-static">${selectedStudentViewData.department.name}</p>
					</div>

					<label for="naturalclassSelectControl"
						class="col-sm-1 control-label">班级</label>
					<div class="col-md-3">
						<p class="form-control-static">${selectedStudentViewData.naturalclass.name}</p>
					</div>

				</div>
				<div class="form-group">
					<label for="student_num" class="col-sm-1 control-label">学号</label>
					<div class="col-md-3">
						<p class="form-control-static">${selectedStudentViewData.student.student_num}</p>
					</div>

					<label for="userBasicInfoName" class="col-sm-1 control-label">姓名</label>
					<div class="col-md-3">
						<p class="form-control-static">${selectedStudentViewData.userbasicinfo.userBasicInfoName}</p>
					</div>
				</div>

				<div class="form-group">

					<label for="user_password" class="col-sm-1 control-label">初始密码</label>
					<div class="col-md-3">
						<input name="user_password"
							class="form-control placeholder-no-fix" value="${selectedStudentViewData.student.student_num}"
							autocomplete="off" placeholder="初始密码" />
					</div>
				</div>



				<div class="form-group">
					<label for="user_basic_info_birthday"
						class="col-sm-1 control-label">生日</label>
					<p class="form-control-static">
						<fmt:formatDate
							value="${selectedStudentViewData.userbasicinfo.user_basic_info_birthday}"
							pattern="yyyy-MM-dd" />
					</p>
				</div>

				<div class="form-group">
					<label for="user_basic_info_sex" class="col-sm-1 control-label">性别</label>
					<c:if
						test="${selectedStudentViewData.userbasicinfo.user_basic_info_sex<1}">
						<p class="form-control-static">男</p>
					</c:if>



					<c:if
						test="${selectedStudentViewData.userbasicinfo.user_basic_info_sex>0}">
						<p class="form-control-static">女</p>
					</c:if>

				</div>


				<div class="form-group">


					<label for="teacher_group" class="col-sm-1 control-label">组</label>
					<div class="col-md-6">



						<div class="Course-Table">


							<table id="studentgrouptable"
								class="table table-hover table-bordered">
								<thead>
									<tr>
										<th style="width: 10%;">#</th>
										<th style="width: 20%;">组</th>
									</tr>



								</thead>
								<tbody>
									<c:set var="index" value="1"></c:set>
									<c:forEach var="dataitem" items="${pagedGroup.result}">
										<tr>
											<th scope="row">${index}</th>
											<td>${dataitem.name}</td>

										</tr>
										<c:set var="index" value="${index + 1}"></c:set>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="contacttable" class="col-sm-1 control-label">联系方式</label>
					<div class="col-md-6">



						<div class="Course-Table">


							<table id="contacttable" class="table table-hover table-bordered">
								<thead>
									<tr>
										<th style="width: 10%;">#</th>
										<th style="width: 20%;">联系类型</th>
										<th style="width: 20%;">内容</th>

									</tr>
								</thead>
								<tbody>
									<c:set var="index" value="1"></c:set>
									<c:forEach var="dataitem"
										items="${selectedStudentViewData.usercontactinfoviewdata}">
										<tr id="contactrow${dataitem.usercontacttype.id}">
											<th scope="row">${index}</th>
											<td>${dataitem.usercontacttype.name}</td>
											<td>${dataitem.usercontactinfo.user_contact_value}</td>

										</tr>
										<c:set var="index" value="${index + 1}"></c:set>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>







				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">修改</button>
					<button type="button" class="btn btn-default"
						onclick="window.history.back()">取消</button>
				</div>

			</form>
		</div>
	</div>








	

	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/sysLastInclude.jsp"%>
	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>


	<script>
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
	</script>



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
			var url = "location='<c:url value="update-"/>" + id + ".html'";
			$('#updateModal').find('.modal-body #inputid').val(id);
			$('#updateModal').find('.modal-body #inputname').val(name);
			$('#updateModal').find('.modal-body #inputnote').val(note);
			$('#updateModal').find('.modal-footer #updatebtn').attr("onclick",
					url);

			$('#updateModal').modal('show');

		}

		function onDelete(t_department_id, departmentname) {
			var t_school_id = gett_school_id();
			var url = "location='<c:url value="DELETE-"/>" + t_school_id + "-"
					+ t_department_id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(
					departmentname);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			alert(url);

			$('#deleteModal').modal('show');

		}

		function AddGroupRow() {
			var table = document.getElementById("studentgrouptable");
			var trCnt = table.tBodies[0].rows.length;
			var row = table.tBodies[0].insertRow(trCnt);

			var td0 = row.insertCell(0);
			var td1 = row.insertCell(1);
			var td2 = row.insertCell(2);

			var cc = $("#groupContainer").html();

			td1.innerHTML = cc;

			td2.innerHTML = "<button type='button' class='btn btn-default btn-sm'	onclick='DeleteGroupRow("
					+ trCnt + ")'>删除</button>";

			trCnt = table.tBodies[0].rows.length;
			for (var i = 0; i < trCnt; i++) {
				table.tBodies[0].rows[i].cells[0].innerHTML = i + 1;
				table.tBodies[0].rows[i].id = "grouprow" + i;
			}

		}

		function DeleteGroupRow(i) {
			var table = document.getElementById("studentgrouptable");
			var trCnt = table.tBodies[0].rows.length;
			var rowid = "grouprow" + i;
			for (var i = 0; i < trCnt; i++) {
				if (table.tBodies[0].rows[i].id == rowid)
					table.tBodies[0].deleteRow(i);
			}

		}

		function AddContactRow() {
			var table = document.getElementById("contacttable");
			var trCnt = table.tBodies[0].rows.length;
			var row = table.tBodies[0].insertRow(trCnt);

			var td0 = row.insertCell(0);
			var td1 = row.insertCell(1);
			var td2 = row.insertCell(2);
			var td3 = row.insertCell(3);

			var cc = $("#contactinfoContainer").html();

			td1.innerHTML = cc;
			td2.innerHTML = "<input type='text' id='inputnote' class='form-control' name='user_contact_value' value='' placeholder='联系方式描述'>";
			td3.innerHTML = "<button type='button' class='btn btn-default btn-sm'	onclick='DeleteConcateRow("
					+ trCnt + ")'>删除</button>";

			trCnt = table.tBodies[0].rows.length;
			for (var i = 0; i < trCnt; i++) {
				table.tBodies[0].rows[i].cells[0].innerHTML = i + 1;
				table.tBodies[0].rows[i].id = "contactrow" + i;
			}

		}

		function DeleteConcateRow(i) {
			var table = document.getElementById("contacttable");
			var trCnt = table.tBodies[0].rows.length;
			var rowid = "contactrow" + i;
			for (var i = 0; i < trCnt; i++) {
				if (table.tBodies[0].rows[i].id == rowid)
					table.tBodies[0].deleteRow(i);
			}

		}
	</script>

	<script>
		$(function() {

			var ctrlschool = document.getElementById("SchoolSelectControl");
			var ctrldepartment = document
					.getElementById("departmentSelectControl");
			var ctrlnaturalclass = document
					.getElementById("naturalclassSelectControl");

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

			var groupctrl = document.getElementById("groupSelectControl");

			index = 0;
			for (var i = 0; i < groupctrl.options.length; i++) {

				if (groupctrl.options[i].text.indexOf("学生") >= 0)
					index = i;
			}

			var groupctrlbyname = document.getElementsByName("groupId");
			for (var i = 0; i < groupctrlbyname.length; i++) {

				groupctrlbyname[i].selectedIndex = index;
			}

		});
	</script>

	<script type="text/javascript">
		$(function() {
			$("#SchoolSelectControl").change(
					function() {
						var selt_school_id = $("#SchoolSelectControl").val();
						var url = "<c:url value="/department/"/>"

						url = url + "selectbyschool-" + selt_school_id
								+ ".json";

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

			$("#departmentSelectControl").change(
					function() {
						var t_department_id = $("#departmentSelectControl")
								.val();
						var url = "<c:url value="/naturalclass/"/>"

						url = url + "selectbyt_department_id-"
								+ t_department_id + ".json";

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



</body>
</html>

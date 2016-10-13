<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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




			<div class="CourseContentHeader">修改教师信息</div>

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
				action="<c:url value="/teacher/updateteacher-${selectedTeacherViewData.user.id }.html"/>"
				method="post">





				<div class="form-group">
					<label for="SchoolSelectControl" class="col-sm-1 control-label">学院</label>
					<div class="col-md-3">
						<select id="SchoolSelectControl" class="form-control input-sm"
							name="t_school_id" onchange="OnSelectControlChange(this)">
							<c:forEach var="school" items="${pagedSchool.result}">
								<option value="${school.id}">${school.name}</option>
							</c:forEach>
						</select>
					</div>

					<label for="departmentSelectControl" class="col-sm-1 control-label">系部</label>
					<div class="col-md-3">
						<select id="departmentSelectControl" name="t_department_id"
							class="form-control input-sm">
							<c:forEach var="d" items="${pagedDepartment.result}">
								<option value="${d.id}">${d.name}</option>
							</c:forEach>

						</select>
					</div>

				</div>
				<div class="form-group">
					<label for="teacherNum" class="col-sm-1 control-label">工号</label>
					<div class="col-md-3">
						<input name="teacherNum" class="form-control placeholder-no-fix"
							autocomplete="off" placeholder="工号"
							value="${selectedTeacherViewData.teacher.teacherNum }"
							onblur="SetDefaultPwd(this.value)" required />
					</div>

					<label for="userBasicInfoName" class="col-sm-1 control-label">姓名</label>
					<div class="col-md-3">
						<input name="userBasicInfoName"
							class="form-control placeholder-no-fix" autocomplete="off"
							value="${selectedTeacherViewData.userbasicinfo.userBasicInfoName }"
							placeholder="姓名" required />
					</div>
				</div>

				<div class="form-group">
					<label for="course_num" class="col-sm-1 control-label">生日</label>
					<div class="col-md-3">
						<div class="input-group date form_date" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="dtp_input2"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" size="16" type="text"
								value='<fmt:formatDate value="${selectedTeacherViewData.userbasicinfo.userBasicInfoBirthday}" pattern="yyyy-MM-dd"/>'
								name="user_basic_info_birthday" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>

					</div>
				</div>

				<div class="form-group">
					<label for="user_basic_info_sex" class="col-sm-1 control-label">性别</label>
					<div class="col-md-3">
						<label class="radio-inline"> <input type="radio" value="0"
							<c:if test="${selectedTeacherViewData.userbasicinfo.userBasicInfoSex<1}">
							checked="checked"
							</c:if>
							name="user_basic_info_sex" id="inlineRadio1" value="option1">
							男
						</label> <label class="radio-inline"> <input type="radio"
							<c:if test="${selectedTeacherViewData.userbasicinfo.userBasicInfoSex>0}">
							checked="checked"
							</c:if>
							value="1" name="user_basic_info_sex" id="inlineRadio2"
							value="option2"> 女
						</label>
					</div>
				</div>

				<div class="form-group">


					<label for="teacher_group" class="col-sm-1 control-label">组</label>
					<div class="col-md-6">
						<div class="btn-group" role="group" aria-label="...">
							<button type="button" class="btn btn-default btn-sm"
								onclick="AddGroupRow()">增加组</button>

							<button type="button" class="btn btn-default btn-sm"
								onclick="window.location.href='<c:url value="/permission/group/list.html"/>'">增加组类型</button>


						</div>


						<div class="Course-Table">


							<table id="teachergrouptable"
								class="table table-hover table-bordered">
								<thead>
									<tr>
										<th style="width: 10%;">#</th>
										<th style="width: 20%;">组</th>

										<th style="width: 20%;">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="index" value="1"></c:set>
									<c:forEach var="dataitem" items="${pagedGroupSpecificUserId.result}">
										<tr id="grouprow${dataitem.id}">
											<th scope="row">${index}</th>
											<td><select id="groupSelectControl" name="groupId"
												class="form-control input-sm">
													<c:forEach var="d" items="${pagedGroup.result}">
														<option value="${d.id}"
														<c:if test="${d.id==dataitem.id }">
														selected
														</c:if>
														>${d.name}</option>
													</c:forEach>

											</select></td>
											<td><button type='button' class='btn btn-default btn-sm'
													onclick='DeleteGroupRow("grouprow${dataitem.id}")'>删除</button></td>
										</tr>
										<c:set var="index" value="${index + 1}"></c:set>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="course_num" class="col-sm-1 control-label">联系方式</label>
					<div class="col-md-6">
						<div class="btn-group" role="group" aria-label="...">


							<button type="button" class="btn btn-default btn-sm"
								onclick="AddContactRow()">增加联系方式</button>

							<button type="button" class="btn btn-default btn-sm"
								onclick="window.location.href='<c:url value="/contacttype/list.html"/>'">增加联系类型</button>


						</div>


						<div class="Course-Table">


							<table id="contacttable" class="table table-hover table-bordered">
								<thead>
									<tr>
										<th style="width: 10%;">#</th>
										<th style="width: 20%;">联系类型</th>
										<th style="width: 20%;">内容</th>
										<th style="width: 20%;">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="index" value="1"></c:set>
									<c:forEach var="dataitem"
										items="${selectedTeacherViewData.usercontactinfoviewdata}">
										<tr id="contactrow${dataitem.usercontacttype.id}">
											<th scope="row">${index}</th>
											
											<td><select id="contactinfoSelectControl" name="contacttypeId"
												class="form-control input-sm">
													<c:forEach var="d" items="${pagedContactType.result}">
														<option value="${d.id}"
														<c:if test="${d.id==dataitem.usercontacttype.id }">
														selected
														</c:if>
														>${d.name}</option>
													</c:forEach>

											</select></td>
											
											
											<td><input type="text" name="user_contact_value"
												value="${dataitem.usercontactinfo.user_contact_value}"></td>
											<td><button type='button' class='btn btn-default btn-sm'
													onclick='DeleteContactRow("contactrow${dataitem.usercontacttype.id}")'>删除</button></td>
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
			var url = "location='<c:url value="/DELETE-"/>" + t_school_id + "-"
					+ t_department_id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(
					departmentname);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			alert(url);

			$('#deleteModal').modal('show');

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

		function AddGroupRow() {
			var table = document.getElementById("teachergrouptable");
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
			var table = document.getElementById("teachergrouptable");
			var trCnt = table.tBodies[0].rows.length;
			var rowid = "grouprow" + i;
			for (var i = 0; i < trCnt; i++) {
				if (table.tBodies[0].rows[i].id == rowid)
					table.tBodies[0].deleteRow(i);
			}

		}

		function SetDefaultPwd(val) {

			var pwdctl = document.getElementById("user_password");
			pwdctl.value = val;
			pwdctl.text = val;
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

		});
	</script>


</body>
</html>

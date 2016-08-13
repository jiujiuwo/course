<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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




			<div class="CourseContentHeader">修改教学班</div>

			<div class="CourseContentHeaderSeparatorLine"></div>





			<div style="display: none;" id="teachingtypeContainer">
				<select id="teachingtypeSelectControl" class="form-control input-sm" name="teachingtypetypeId">
					<c:forEach var="t" items="${pagedTeachingType.result}">
						<option value="${t.id}">${t.name}</option>
					</c:forEach>
				</select>
			</div>


			<form class="form-horizontal" style="overflow: hidden;"
				action="<c:url value="/teachingclass/updateteachingclass-${selectedCourseTeachingClassViewData.courseTeachingClass.id}.html"/>"
				method="post">

				<input name="t_course_id" id="t_course_id" type="hidden"
					value="${selectedCourseTeachingClassViewData.course.id}" />
				<input id="course_name" type="hidden" />
				<input id="course_num" type="hidden" />
				<input name="course_teaching_class_term_id" id="course_teaching_class_term_id" type="hidden"
					value="${selectedCourseTeachingClassViewData.term.id}" />
				<input id="teaching_year_begin" type="hidden" />
				<input id="teaching_year_end" type="hidden" />
				<input id="teaching_term" type="hidden" />
				<input id="weeks" type="hidden" />

				<div class="form-group">



					<label for="courseName" class="col-sm-1 control-label">课程名称</label>

					<div class="col-xs-4 col-sm-4 col-md-2">

						<input id="courseName" class="form-control placeholder-no-fix" autocomplete="off"
							value="${selectedCourseTeachingClassViewData.course.name}" placeholder="课程名称" readonly
							required />



					</div>
					<div class="col-xs-2 col-sm-2 col-md-2">
						<button type="button" class="btn btn-default btn-sm" onclick="onShowCourseBtnClick()">选择课程名称</button>
					</div>

				</div>
				<div class="form-group">


					<label for="course_num" class="col-sm-1 control-label">学年-学期</label>

					<div class="col-xs-4 col-sm-4 col-md-2">

						<input id="courseTeachingTerm" class="form-control placeholder-no-fix" autocomplete="off"
							value="${selectedCourseTeachingClassViewData.term.term}" placeholder="学年-学期" readonly
							required />



					</div>
					<div class="col-xs-2 col-sm-2 col-md-2">
						<button type="button" class="btn btn-default btn-sm" onclick="onShowTermBtnClick()">选择学年-学期</button>
					</div>


				</div>
				<div class="form-group">



					<label for="teachingclassname" class="col-sm-1 control-label">教学班名称</label>
					<div class="col-md-5">
						<input id="teachingclassname" name="teachingclass_name"
							class="form-control placeholder-no-fix" autocomplete="off" placeholder="教学班名称"
							value="${selectedCourseTeachingClassViewData.courseTeachingClass.name }" />
					</div>
				</div>


				<div class="form-group">


					<label for="teachingclass_teacher" class="col-sm-1 control-label">授课教师</label>
					<div class="col-md-6">
						<div class="btn-group" role="group" aria-label="...">
							<button type="button" class="btn btn-default btn-sm"
								onclick="window.location.href='<c:url value="/teacher/add.html"/>'">新建教师</button>

							<button type="button" class="btn btn-default btn-sm" data-toggle="modal"
								data-target="#addModal">为课程增加授课教师</button>


						</div>


						<div class="Course-Table">


							<table id="teachertable" class="table table-hover table-bordered">
								<thead>
									<tr>
										<th style="width: 10%;">#</th>
										<th style="width: 20%;">学院</th>
										<th style="width: 30%;">姓名</th>
										<th style="width: 30%;">授课类型</th>
										<th style="width: 10%;">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="index" value="1"></c:set>
									<c:forEach var="teacher" items="${selectedCourseTeachingClassViewData.teacher}">
										<tr id="teacherrow${index-1 }">
											<td scope="row"><input type="checkbox" value=""> ${index}</td>

											<td>${teacher.school.name }</td>
											<td><input name='teacherid' value='${teacher.teacher.id }' type='hidden' />${teacher.userbasicinfo.userBasicInfoName }</td>
											<td><select id="teachingtypeSelectControl" class="form-control input-sm"
												name="teachingtypetypeId">
													<c:forEach var="t" items="${pagedTeachingType.result}">
														<option value="${t.id}"
															<c:if test="${t.id==(selectedCourseTeachingClassViewData.teachingtype[index-1].id)}">
						checked
						</c:if>>${t.name}</option>
													</c:forEach>
											</select></td>
											<td><button type='button' class='btn btn-default btn-sm'
													onclick='DeleteTeacherRow("${index-1}")'>删除</button></td>

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
					<button type="button" class="btn btn-default" onclick="window.history.back()">取消</button>
				</div>

			</form>
		</div>
	</div>



	<!-- 添加对话框 -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
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
								<select id="SchoolSelectControlAdd" class="form-control" name="t_school_id"
									onchange="OnSchoolSelectControlAddChange(this)">
									<c:forEach var="school" items="${pagedSchool.result}">
										<option value="${school.id}">${school.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="row">
							<div class="col-md-2">教师</div>
							<div class="col-md-10">
								<select id="teacherSelectControlAdd" class="form-control" name="teacherId">
									<c:forEach var="t" items="${pagedTeacherViewData.result}">
										<option value="${t.teacher.id}">${t.userbasicinfo.userBasicInfoName}(${t.teacher.teacherNum},${t.department.name})</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class="row">
							<div class="col-md-2">工号</div>
							<div class="col-md-10"></div>
						</div>



					</div>


				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" onclick="AddTeacherRow()">添加</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>

			</div>
		</div>
	</div>

	<!-- 课程对话框 -->
	<div class="modal fade" id="courseModal" tabindex="-1" role="dialog"
		aria-labelledby="mycourseModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="mycourseModalLabel">选择课程</h4>
				</div>


				<div class="modal-body">
					<h4 class="form-signin-heading">请选择课程</h4>


					<p class="bg-danger" id="courseError"></p>



					<div class="list-group" id="courseContainer"></div>


				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" onclick="addCourseClick()">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>

			</div>
		</div>
	</div>




	<!-- 学年对话框 -->
	<div class="modal fade" id="termModal" tabindex="-1" role="dialog"
		aria-labelledby="myTermModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myTermModalLabel">选择学年学期</h4>
				</div>


				<div class="modal-body">
					<h4 class="form-signin-heading">请选择学年学期</h4>


					<p class="bg-danger" id="termError"></p>



					<div class="list-group" id="termsContainer"></div>


				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" onclick="addTermClick()">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>

			</div>
		</div>
	</div>



<%@ include file="../../shared/importJs.jsp"%>

	<%@ include file="../../shared/sysLastInclude.jsp"%>


	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>

	<script>
		$(function() {

			//course 
			var ctrlCourse = document.getElementById("courseSelectControl");
			var courseId = "${selectedCourseTeachingClassViewData.course.id}";

			var index = 0;

			if (ctrlCourse.options.length > 0) {

				for (var i = 0; i < ctrlCourse.options.length; i++)
					if (ctrlCourse.options[i].value == (courseId))
						index = i;
				ctrlCourse.selectedIndex = index;

			}

			//teaching_year_begin
			var ctrlteaching_year_begin = document
					.getElementById("teaching_year_begin");
			var teaching_year_begin = "${selectedCourseViewData.courseTeachingClass.teaching_year_begin}";

			if (ctrlteaching_year_begin.options.length > 0) {

				for (var i = 0; i < ctrlteaching_year_begin.options.length; i++)
					if (ctrlteaching_year_begin.options[i].value == (teaching_year_begin))
						index = i;
				ctrlteaching_year_begin.selectedIndex = index;

			}

			//teachingtermSelectControl
			var ctrlteachingtermSelectControl = document
					.getElementById("teachingtermSelectControl");
			var teaching_term = "${selectedCourseViewData.courseTeachingClass.teaching_term}";

			if (ctrlteachingtermSelectControl.options.length > 0) {

				for (var i = 0; i < ctrlteachingtermSelectControl.options.length; i++)
					if (ctrlteachingtermSelectControl.options[i].value == (teaching_term))
						index = i;
				ctrlteachingtermSelectControl.selectedIndex = index;

			}

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

		function AddTeacherRow() {
			var table = document.getElementById("teachertable");
			var trCnt = table.tBodies[0].rows.length;
			var row = table.tBodies[0].insertRow(trCnt);

			var td0 = row.insertCell(0);
			var td1 = row.insertCell(1);
			var td2 = row.insertCell(2);
			var td3 = row.insertCell(3);
			var td4 = row.insertCell(4);

			var teachingtype = $("#teachingtypeContainer").html();

			var schoolctl = document.getElementById("SchoolSelectControlAdd");
			var teacher = document.getElementById("teacherSelectControlAdd");

			td1.innerHTML = "<input  type='text' class='form-control' value='"+schoolctl.options[schoolctl.selectedIndex].text+"' readonly/>";
			td2.innerHTML = "<input name='teacherid' value='"+teacher.value+"' type='hidden' /> <input  type='text' class='form-control' value='"+teacher.options[teacher.selectedIndex].text+"' readonly/>";

			td3.innerHTML = teachingtype;

			td4.innerHTML = "<button type='button' class='btn btn-default btn-sm'	onclick='DeleteTeacherRow("
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

		function OnTeachingYearBeginChange() {
			var ctr = document.getElementById("teaching_year_begin").value;
			var endctr = document.getElementById("teaching_year_end");

			endctr.value = (parseInt(ctr) + 1);

			//	alert(endctr.value);

			//	endctr.text=ctr+1;

			setTeachingClassName();

		}

		function setTeachingClassName() {
			var coursename = document.getElementById("courseSelectControl");
			var beginctr = document.getElementById("teaching_year_begin");
			var endctr = document.getElementById("teaching_year_end");
			var term = document.getElementById("teachingtermSelectControl");
			var teachingclassname = document
					.getElementById("teachingclassname");

			teachingclassname.value = beginctr.value + "-" + endctr.value
					+ "学年第" + term.value + "学期"
					+ coursename.options[coursename.selectedIndex].text + "课程";

		}
	</script>

	<script>
		/**
		选择课程名称
		 */
		function clearCourseId() {
			$("#courseError").empty();//清除错误提示
			$(".list-group-item").removeClass('active');
			$("#courseName").empty();
			$("#t_course_id").val("");

		}

		function addCourseClick() {
			var t_course_id = $("#t_course_id").val();

			if (t_course_id == null || t_course_id.length == 0) {

				$("#courseError").text("请选择课程");
				return false;
			}
			$('#courseModal').modal('hide');
			return true;

		}

		function changeCourse(e, id, course_name, course_num, courseTypeId,
				courseStyleId) {
			clearCourseId();

			var s = course_name + "(" + course_num + ")";

			$("#courseName").val(s);

			$("#course_num").val(course_num);
			$("#course_name").val(course_name);

			$(e).addClass('active');
			$("#t_course_id").val(id);

			setCourseTeachingClassName();

		}

		function showCourse() {
			$("#courseContainer").empty();
			var url = "<c:url value='/course/getAllPage.json'/>";

			$.get(url, function(data, status) {
				if (status == "success") {

					$("#courseContainer").empty();

					for (var i = 0; i < data.result.length; i++) {
						var s = "<a onclick='changeCourse(this,\""
								+ data.result[i].course.id + "\",\""
								+ data.result[i].course.name + "\",\""
								+ data.result[i].course.num + "\",\""
								+ data.result[i].course.courseTypeId + "\",\""
								+ data.result[i].course.courseStyleId
								+ "\")' class='list-group-item'>"
								+ data.result[i].course.name + "("
								+ data.result[i].course.num + ")" + "</a>";

						$("#courseContainer").append(s);

					}

				}
			});
		}
		function onShowCourseBtnClick() {

			$('#courseModal').find('.modal-footer #btnAddUser').attr("onclick",
					"addCourseClick()");

			showCourse();

			$('#courseModal').modal('show');
		}
	</script>


	<script>
		/*
		选择学年-学期
		 */
		function clearTermId() {
			$("#termError").empty();//清除错误提示
			$(".list-group-item").removeClass('active');
			$("#courseTeachingTerm").empty();
			$("#course_teaching_class_term_id").val("");

		}

		function addTermClick() {
			var course_teaching_class_term_id = $(
					"#course_teaching_class_term_id").val();

			if (course_teaching_class_term_id == null
					|| course_teaching_class_term_id.length == 0) {

				$("#termError").text("请选择学年-学期");
				return false;
			}
			$('#termModal').modal('hide');
			return true;

		}

		function changeTerm(e, id, teaching_year_begin, teaching_year_end,
				teaching_term, weeks) {
			clearTermId();

			var s = teaching_year_begin + "-" + teaching_year_end + "学年第"
					+ teaching_term + "学期";

			$("#courseTeachingTerm").val(s);
			$(e).addClass('active');
			$("#course_teaching_class_term_id").val(id);

			$("#teaching_year_begin").val(teaching_year_begin);
			$("#teaching_year_end").val(teaching_year_end);
			$("#teaching_term").val(teaching_term);
			$("#weeks").val(weeks);

			setCourseTeachingClassName();
		}

		function setCourseTeachingClassName() {
			var course_name = $("#course_name").val();
			var course_num = $("#course_num").val();
			var teaching_year_begin = $("#teaching_year_begin").val();
			var teaching_year_end = $("#teaching_year_end").val();
			var teaching_term = $("#teaching_term").val();
			var weeks = $("#weeks").val();

			if (course_name == null || course_name.length == 0)
				return;
			if (course_num == null || course_num.length == 0)
				return;
			if (teaching_year_begin == null || teaching_year_begin.length == 0)
				return;
			if (teaching_year_end == null || teaching_year_end.length == 0)
				return;
			if (teaching_term == null || teaching_term.length == 0)
				return;

			$("#teachingclassname").val(
					"(" + teaching_year_begin + "-" + teaching_year_end
							+ teaching_term + "-" + ")-" + course_name + "("
							+ course_num + ")");

		}

		function showTerm() {
			$("#termsContainer").empty();
			var url = "<c:url value='/courseteachingterm/getall.json'/>";

			$.get(url, function(data, status) {
				if (status == "success") {

					$("#termsContainer").empty();

					for (var i = 0; i < data.length; i++) {
						var s = "<a onclick='changeTerm(this,\"" + data[i].id
								+ "\"," + data[i].teachingYearBegin + ","
								+ data[i].teachingYearEnd + ","
								+ data[i].teachingTerm + "," + data[i].weeks
								+ ")' class='list-group-item'>"
								+ data[i].teachingYearBegin + "-"
								+ data[i].teachingYearEnd + "学年第"
								+ data[i].teachingTerm + "学期" + "</a>";

						$("#termsContainer").append(s);

					}

				}
			});
		}
		function onShowTermBtnClick() {

			$('#termModal').find('.modal-footer #btnAddUser').attr("onclick",
					"addTermClick()");

			showTerm();

			$('#termModal').modal('show');
		}
	</script>


	<script type="text/javascript">
		$(function() {
			$("#SchoolSelectControlAdd")
					.change(
							function() {
								var selt_school_id = $(
										"#SchoolSelectControlAdd").val();
								var url = "<c:url value="/teacher/"/>"

								url = url + "selectbyschool-" + selt_school_id
										+ ".json";

								var departmentjson;
								$("#teacherSelectControlAdd").empty();
								$
										.get(
												url,
												function(data, status) {
													if (status == "success") {

														for (var i = 0; i < data.length; i++) {

															$(
																	"#teacherSelectControlAdd")
																	.append(
																			"<option value='"+data[i].teacher.id+"'>"
																					+ data[i].userbasicinfo.userBasicInfoName
																					+ "("
																					+ data[i].teacher.teacherNum
																					+ ","
																					+ data[i].department.name
																					+ ")</option>");
														}
													}
												});

							});

		});
	</script>


</body>
</html>

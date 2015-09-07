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




			<div class="CourseContentHeader">增加教学班</div>

			<div class="CourseContentHeaderSeparatorLine"></div>





			<div style="display: none;" id="teachingtypeContainer">
				<select id="teachingtypeSelectControl" class="form-control input-sm"
					name="teachingtypetypeId">
					<c:forEach var="t" items="${pagedTeachingType.result}">
						<option value="${t.id}">${t.name}</option>
					</c:forEach>
				</select>
			</div>


			<form class="form-horizontal" style="overflow: hidden;"
				action="<c:url value="/teachingclass/addteachingclass.html"/>"
				method="post">

				<div class="form-group">



					<label for="course_num" class="col-sm-1 control-label">课程名称</label>

					<div class="col-md-3">
						<select id="courseSelectControl" class="form-control input-sm"
							name="courseId" onchange="OnSelectControlChange(this)">
							<c:forEach var="course" items="${pagedCourse.result}">
								<option value="${course.id}">${course.name}</option>
							</c:forEach>
						</select>
					</div>

				</div>
				<div class="form-group">




					<label for="course_num" class="col-sm-1 control-label">学年</label>

					<div class="col-md-1">
						<select id="teaching_year_begin" name="teaching_year_begin"
							class="form-control input-sm"
							onchange="OnTeachingYearBeginChange()">
							<option value="2015">2015</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
							<option value="2019">2019</option>
							<option value="2020">2020</option>

						</select>



					</div>


					<div class="col-sm-1"
						style="margin-left: 0px; margin-right: 0px; width: 5px;">
						<span>-</span>
					</div>


					<div class="col-md-1">
						<input name="teaching_year_end" class="form-control input-sm"
							id="teaching_year_end" autocomplete="off" value="2016"
							readonly />
					</div>


					<label for="teaching_term" class="col-sm-1 control-label">学期</label>

					<div class="col-md-1">
						<select id="teachingtermSelectControl" name="teaching_term"
							class="form-control input-sm" onchange="setTeachingClassName()">
							<option value="1">1</option>
							<option value="2">2</option>
						</select>
					</div>


				</div>
				<div class="form-group">



					<label for="teachingclassname" class="col-sm-1 control-label">教学班名称</label>
					<div class="col-md-5">
						<input id="teachingclassname" name="teachingclass_name"
							class="form-control placeholder-no-fix" autocomplete="off"
							placeholder="教学班名称" />
					</div>
				</div>


				<div class="form-group">


					<label for="teachingclass_teacher" class="col-sm-1 control-label">授课教师</label>
					<div class="col-md-6">
						<div class="btn-group" role="group" aria-label="...">
							<button type="button" class="btn btn-default btn-sm"
								onclick="window.location.href='<c:url value="/teacher/add.html"/>'">新建教师</button>

							<button type="button" class="btn btn-default btn-sm"
								data-toggle="modal" data-target="#addModal">为课程增加授课教师</button>


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
										<option value="${t.teacher.id}">${t.userbasicinfo.user_basic_info_name}(${t.teacher.teacher_num},${t.department.name})</option>
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
					<button type="submit" class="btn btn-primary"
						onclick="AddTeacherRow()">添加</button>
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
		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onUpdate(id, name, note) {
			var url = "location='update-" + id + ".html'";
			$('#updateModal').find('.modal-body #inputid').val(id);
			$('#updateModal').find('.modal-body #inputname').val(name);
			$('#updateModal').find('.modal-body #inputnote').val(note);
			$('#updateModal').find('.modal-footer #updatebtn').attr("onclick",
					url);

			$('#updateModal').modal('show');

		}

		function onDelete(t_department_id, departmentname) {
			var t_school_id = gett_school_id();
			var url = "location='DELETE-" + t_school_id + "-" + t_department_id
					+ ".html'";

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

			endctr.value =  (parseInt(ctr) + 1);

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

			teachingclassname.value = beginctr.value + "-"+endctr.value + "学年第"
					+ term.value + "学期"
					+ coursename.options[coursename.selectedIndex].text + "课程";

		}
	</script>

	<script>
		$(function() {

			setTeachingClassName();

		});
	</script>

	<script type="text/javascript">
		$(function() {
			$("#courseSelectControl").change(function() {
				setTeachingClassName();

			});

		});
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
													+ data[i].userbasicinfo.user_basic_info_name
													 +"(" + data[i].teacher.teacher_num
													+ "," + data[i].department.name
													 +")</option>");
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

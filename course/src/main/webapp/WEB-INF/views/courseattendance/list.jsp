<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "courseattendance");
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
<body class="home" onLoad="ShowErrMsg()">

	<%@ include file="../../shared/pageHeader.jsp"%>
	<%@ include file="../../shared/CourseTeachingClassInfo.jsp"%>
	<div id="DocumentPageTopSeparatorLine"></div>

	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="../CourseContentNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">



			<ol class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li><a href="#">Library</a></li>
				<li class="active">Data</li>
			</ol>

			<div class="CourseContentHeader">${selectedCourseAttendanceTypeData.name}管理</div>

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
								data-toggle="modal" data-target="#addModal">增加</button>

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






			<div class="Course-Table">

				<div class="container-fluid" style="overflow: hidden;">
					<div class="gridseparator"></div>
					<div class="row show-grid">
						<div class="col-md-1">
							<strong>#</strong>
						</div>
						<div class="col-md-2">
							<strong>考勤日期</strong>
						</div>

						<div class="col-md-2">
							<strong>操作</strong>
						</div>
					</div>

					<div class="gridseparator"></div>


					<c:set var="index" value="0"></c:set>
					<c:forEach var="data" items="${pagedAttendanceViewData.result}">
						<div class="row show-grid">
							<div class="col-md-1">
								<input type="checkbox" value="">
								${pagedAttendanceViewData.totalCount-(pagedAttendanceViewData.currentPageNo-1) * pagedAttendanceViewData.pageSize - index}
							</div>

							<div class="col-md-2">
								<a
									href="<c:url value="/courseattendance/studentattendance-${selectedCourseTeachingClassID}-${selectedCourseAttendanceTypeData.id}-${data.attendance.id}.html"/>">
									<fmt:formatDate value="${data.attendance.begin_datetime}"
										pattern="yyyy-MM-dd HH:mm" /> - <fmt:formatDate
										value="${data.attendance.end_datetime}"
										pattern="yyyy-MM-dd HH:mm" />
								</a>

							</div>
							<div class="col-md-2">
								<button type="button" class="btn btn-default btn-xs"
									onclick="onUpdate('${data.attendance.id}','${data.attendance.begin_datetime}','${data.attendance.end_datetime}')">修改...</button>
								<button type="button" class="btn btn-default btn-xs"
									onclick="onDelete('${data.attendance.id}')">删除</button>

								<c:choose>
									<c:when test="${data.hasCheckin}">
										<button type="button" class="btn btn-success btn-xs"
											onclick="location='<c:url value="/courseattendance/studentattendance-${selectedCourseTeachingClassID}-${selectedCourseAttendanceTypeData.id}-${data.attendance.id}.html"/>'">修改数据</button>

										<button type="button" class="btn btn-default btn-xs"
											onclick="location='<c:url value="/courseattendance/statistics-${selectedCourseTeachingClassID}-${selectedCourseAttendanceTypeData.id}-${data.attendance.id}.html"/>'">统计</button>

									</c:when>
									<c:otherwise>
										<button type="button" class="btn btn-danger btn-xs"
											onclick="location='<c:url value="/courseattendance/studentattendance-${selectedCourseTeachingClassID}-${selectedCourseAttendanceTypeData.id}-${data.attendance.id}.html"/>'">提交数据</button>
									</c:otherwise>
								</c:choose>




							</div>
						</div>
						<c:set var="index" value="${index + 1}"></c:set>
					</c:forEach>

					<c:if test="${pagedAttendanceViewData.totalCount>0}">
						<div class="gridseparator"></div>
					</c:if>

				</div>




				<mathtop:PageBar pageUrl="/school/list.html"
					pageAttrKey="pagedSchool" />

			</div>

		</div>
	</div>


	<!-- 添加对话框 -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加考勤</h4>
				</div>

				<form class="form-horizontal"
					action="<c:url value="/courseattendance/add-${selectedCourseTeachingClassID}-${selectedCourseAttendanceTypeData.id}.html"/>"
					method="post">

					<div class="modal-body">

						<div class="form-group">
							<label for="begin_datetime" class="col-sm-2 control-label">开始时间</label>
							<div class="col-md-5">
								<div class="input-group date form_date" data-date=""
									data-date-format="yyyy-mm-dd hh:ii"
									data-link-field="dtp_input1"
									data-link-format="yyyy-mm-dd hh:ii">
									<input class="form-control" size="16" type="text"
										id="begin_datetime" value="" name="begin_datetime" readonly
										required> <span class="input-group-addon"><span
										class="glyphicon glyphicon-remove"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<input type="hidden" id="dtp_input1" value="" /><br />
							</div>
						</div>

						<div class="form-group">
							<label for="end_datetime" class="col-sm-2 control-label">结束时间</label>
							<div class="col-md-5">
								<div class="input-group date form_date" data-date=""
									data-date-format="yyyy-mm-dd hh:ii "
									data-link-field="dtp_input2"
									data-link-format="yyyy-mm-dd hh:ii">
									<input class="form-control" size="16" type="text"
										id="end_datetime" value="" name="end_datetime" readonly
										required> <span class="input-group-addon"><span
										class="glyphicon glyphicon-remove"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<input type="hidden" id="dtp_input2" value="" /><br />
							</div>
						</div>


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
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改课程通知</h4>
				</div>

				<form class="form-horizontal"
					action="<c:url value="/courseattendance/update-${selectedCourseTeachingClassID}-${selectedCourseAttendanceTypeData.id}.html"/>"
					method="post">

					<div class="modal-body">
						<input type="hidden" value="" id="updateattendanceid"
							name="updateattendanceid" />

						<div class="form-group">
							<label for="update_begin_datetime" class="col-sm-2 control-label">开始时间</label>
							<div class="col-md-5">
								<div class="input-group date form_date" data-date=""
									data-date-format="yyyy-mm-dd hh:ii"
									data-link-field="dtp_input3"
									data-link-format="yyyy-mm-dd hh:ii">
									<input class="form-control" size="16" type="text"
										id="update_begin_datetime" value=""
										name="update_begin_datetime" readonly required> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-remove"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<input type="hidden" id="dtp_input3" value="" /><br />
							</div>
						</div>

						<div class="form-group">
							<label for="update_end_datetime" class="col-sm-2 control-label">结束时间</label>
							<div class="col-md-5">
								<div class="input-group date form_date" data-date=""
									data-date-format="yyyy-mm-dd hh:ii "
									data-link-field="dtp_input4"
									data-link-format="yyyy-mm-dd hh:ii">
									<input class="form-control" size="16" type="text"
										id="update_end_datetime" value="" name="update_end_datetime"
										readonly required> <span class="input-group-addon"><span
										class="glyphicon glyphicon-remove"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<input type="hidden" id="dtp_input4" value="" /><br />
							</div>
						</div>



					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" onclick="onAdd()">确定</button>
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


	<script type="text/javascript">
		$("#collapseAtendance").addClass("in");
		$("#attendancetype${selectedCourseAttendanceTypeData.id}").addClass(
				"active");
	</script>

	<script>
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 0,
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

		function onUpdate(updateattendanceid, begin_datetime, end_datetime) {

			$('#updateModal').find('.modal-body #updateattendanceid').val(
					updateattendanceid);
			$('#updateModal').find('.modal-body #update_begin_datetime').val(
					begin_datetime);
			$('#updateModal').find('.modal-body #update_end_datetime').val(
					end_datetime);

			$('#updateModal').modal('show');

		}

		function onDelete(id) {
			var url = "location='courseattendance/DELETE-${selectedCourseTeachingClassID}-${selectedCourseAttendanceTypeData.id}-"
					+ id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "teachingterm");
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




			<div class="CourseContentHeader">教学学期管理</div>

			<div class="CourseContentHeaderSeparatorLine"></div>



			<table>
				<tr>
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
							<button type="button" class="btn btn-default btn-sm" data-toggle="modal"
								data-target="#addModal">增加</button>

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
				</tr>
			</table>


			<c:choose>
				<c:when test="${pagedCourseTeachingTerm.totalCount >0}">





					<div class="Course-Table">
						<div class="gridseparator"></div>

						<div class="container-fluid">
							<div class="row">
								<div class="col-md-1">
									<strong>#</strong>
								</div>
								<div class="col-md-2">
									<strong>学年开始</strong>
								</div>
								<div class="col-md-2">
									<strong>学年结束</strong>
								</div>
								<div class="col-md-1">
									<strong>学期</strong>
								</div>
								<div class="col-md-2">
									<strong>教学周数</strong>
								</div>
								<div class="col-md-2">
									<strong>第一周开始日期</strong>
								</div>
								<div class="col-md-2">
									<strong>操作</strong>
								</div>
							</div>
							<div class="gridseparator"></div>

							<c:set var="index" value="1"></c:set>
							<c:forEach var="dataitem" items="${pagedCourseTeachingTerm.result}">
								<div class="row">
									<div class="col-md-1">
										<input type="checkbox" value="">
										${(pagedCourseTeachingTerm.currentPageNo-1) * pagedCourseTeachingTerm.pageSize +index}
									</div>
									<div class="col-md-2">${dataitem.teachingYearBegin }</div>
									<div class="col-md-2">${dataitem.teachingYearEnd }</div>
									<div class="col-md-2">${dataitem.teachingTerm }</div>
									<div class="col-md-1">${dataitem.weeks }</div>
									<div class="col-md-2">
										<fmt:formatDate value="${dataitem.weekBegin}" pattern="yyyy-MM-dd" />
									</div>


									<div class="col-md-2">
										<button type="button" class="btn btn-default btn-xs"
											onclick="onUpdate('${dataitem.id}','${dataitem.teachingYearBegin}','${dataitem.teachingYearEnd}','${dataitem.teachingTerm}','${dataitem.weeks}','${dataitem.weekBegin}')">修改...</button>
										<button type="button" class="btn btn-default btn-xs"
											onclick="onDelete('${dataitem.id}','${dataitem.teachingYearBegin}','${dataitem.teachingYearEnd}','${dataitem.teachingTerm}','${dataitem.weeks}','${dataitem.weekBegin}')">删除</button>
									</div>
								</div>
								<c:set var="index" value="${index + 1}"></c:set>
							</c:forEach>
							<div class="gridseparator"></div>

						</div>



						<mathtop:PageBar pageUrl="/courseteachingterm/list.html" pageAttrKey="pagedCourseTeachingTerm" />

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
							<a href="<c:url value="/teachingclass/add.html"/>" class="list-group-item">添加教学班班</a>
							<a href="<c:url value="/course/add.html"/>" class="list-group-item">添加课程</a>
							<a href="<c:url value="/teacher/add.html"/>" class="list-group-item">添加教师</a>
							<a href="<c:url value="/naturalclass/list.html"/>" class="list-group-item">添加自然班</a>
							<a href="<c:url value="/school/list.html"/>" class="list-group-item">添加学院</a>
							<a href="<c:url value="/department/list.html"/>" class="list-group-item">添加系部</a>

						</div>

					</div>
				</c:otherwise>
			</c:choose>

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
					<h4 class="modal-title" id="myModalLabel">添加教学学期</h4>
				</div>



				<form class="form-horizontal" action="<c:url value="/courseteachingterm/add.html"/>"
					method="post">


					<div class="modal-body">
						<h4 class="form-signin-heading">请输入新加教学学期</h4>

						<div class="form-group">
							<label for="teaching_year_begin" class="col-sm-4 control-label">学年开始</label>
							<div class="col-sm-8">
								<input type="text" id="teaching_year_begin" class="form-control" name="teaching_year_begin"
									placeholder="学年开始" required autofocus>
							</div>
						</div>

						<div class="form-group">
							<label for="teaching_year_end" class="col-sm-4 control-label">学年结束</label>
							<div class="col-sm-8">
								<input type="text" id="teaching_year_end" class="form-control" name="teaching_year_end"
									placeholder="学年结束">
							</div>
						</div>

						<div class="form-group">
							<label for="teaching_term" class="col-sm-4 control-label">学期</label>
							<div class="col-sm-8">
								<input type="text" id="teaching_term" class="form-control" name="teaching_term"
									placeholder="学期">
							</div>
						</div>

						<div class="form-group">
							<label for="weeks" class="col-sm-4 control-label">教学周数</label>
							<div class="col-sm-8">
								<input type="text" id="weeks" class="form-control" name="weeks" value="20"
									placeholder="教学周数">
							</div>
						</div>

						<div class="form-group">
							<label for="week_begin" class="col-sm-4 control-label">第一周开始日期</label>


							<div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
								data-link-field="dtp_input2" data-link-format="yyyy-mm-dd hh:ii">
								<input class="form-control" size="16" type="text" id="week_begin" value="" name="week_begin"
									readonly required>
								<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
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
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改教学学期</h4>
				</div>

				<form class="form-signin" action="<c:url value='/courseteachingterm/update.html'/>" method="post">


					<div class="modal-body">
					<input type="hidden" id="inputid" name="id">
						<h4 class="form-signin-heading">请修改教学学期属性</h4>
						<div class="form-group">
							<label for="teaching_year_begin" class="col-sm-4 control-label">学年开始</label>
							<div class="col-sm-8">
								<input type="text" id="teaching_year_begin" class="form-control" name="teaching_year_begin"
									placeholder="学年开始" required autofocus>
							</div>
						</div>

						<div class="form-group">
							<label for="teaching_year_end" class="col-sm-4 control-label">学年结束</label>
							<div class="col-sm-8">
								<input type="text" id="teaching_year_end" class="form-control" name="teaching_year_end"
									placeholder="学年结束">
							</div>
						</div>

						<div class="form-group">
							<label for="teaching_term" class="col-sm-4 control-label">学期</label>
							<div class="col-sm-8">
								<input type="text" id="teaching_term" class="form-control" name="teaching_term"
									placeholder="学期">
							</div>
						</div>

						<div class="form-group">
							<label for="weeks" class="col-sm-4 control-label">教学周数</label>
							<div class="col-sm-8">
								<input type="text" id="weeks" class="form-control" name="weeks" value="20"
									placeholder="教学周数">
							</div>
						</div>

						<div class="form-group">
							<label for="week_begin" class="col-sm-4 control-label">第一周开始日期</label>


							<div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
								data-link-field="dtp_input2" data-link-format="yyyy-mm-dd hh:ii">
								<input class="form-control" size="16" type="text" id="week_begin" value="" name="week_begin"
									readonly required>
								<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>



						</div>


					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">修改</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>

				</form>
			</div>
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
			autoclose : true,
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
				var url = "${pagedURI}/select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onUpdate(id, begin_year, end_year, term, weeks, week_begin) {
			var url = "location='<c:url value="/courseteachingterm/update-"/>"
					+ id + ".html'";
			$('#updateModal').find('.modal-body #inputid').val(id);
			$('#updateModal').find('.modal-body #teaching_year_begin').val(begin_year);
			$('#updateModal').find('.modal-body #teaching_year_end').val(end_year);
			$('#updateModal').find('.modal-body #teaching_term').val(term);
			$('#updateModal').find('.modal-body #weeks').val(weeks);
			$('#updateModal').find('.modal-body #week_begin').val(week_begin);			
			$('#updateModal').find('.modal-footer #updatebtn').attr("onclick",
					url);

			$('#updateModal').modal('show');

		}

		function onDelete(id, begin_year, end_year, term, weeks, week_begin) {
			var url = "location='<c:url value="/courseteachingterm/delete-"/>"
					+ id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(
					begin_year + "-" + end_year + "学年第" + term + "学期");
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}
	</script>





</body>
</html>

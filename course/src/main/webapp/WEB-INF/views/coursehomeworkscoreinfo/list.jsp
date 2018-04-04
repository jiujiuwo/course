<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "courseexperiment");
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
	<%@ include file="../../shared/CourseTeachingClassInfo.jsp"%>


	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="../CourseContentNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">



			<ol class="breadcrumb">
				<li><a href="#">课程系统</a></li>
				<li><a
						href="<c:url value="/coursecontent/index-${selectedCourseTeachingClassViewData.courseTeachingClass.id}.html"/>">${selectedCourseTeachingClassViewData.course.name}</a></li>
				<li class="active">${selectedCourseHomeworkTypeData.name}</li>
			</ol>

			<div class="CourseContentHeader">${selectedCourseHomeworkTypeData.name}成绩设置管理</div>

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
							<button type="button" class="btn btn-default btn-sm"
									onclick="onUpdateAllSettings()">修改...</button>
						</div></td>
					<td style="width: 10px;"></td>
					<td><div class="input-group input-group-sm">
							<input type="text" class="form-control" id="SearchText" placeholder="Search for..." required>
							<span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="onSearch()">搜索</button>
							</span>
						</div></td>

					<c:if
						test="${pagedCourseTeachingClassHomeworkScoreinfoViewData!=null and pagedCourseTeachingClassHomeworkScoreinfoViewData.totalCount>10}">



						<td style="width: 10px;"></td>
						<td><div class="btn-group" role="group" aria-label="...">
								<!-- Split button -->
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-sm">每页大小</button>
									<button type="button" class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown"
										aria-expanded="false">
										<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li><a
												href="coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=10">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==10}">
													<i class="icon-ok"></i>
												</c:if>
												默认(10)
											</a></li>
										<li class="divider"></li>
										<li><a
												href="coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=20">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==20}">
													<i class="icon-ok"></i>
												</c:if>
												20
											</a></li>
										<li><a
												href="coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=30">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==30}">
													<i class="icon-ok"></i>
												</c:if>
												30
											</a></li>
										<li><a
												href="coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=40">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==40}">
													<i class="icon-ok"></i>
												</c:if>
												40
											</a></li>
										<li><a
												href="coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=50">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==50}">
													<i class="icon-ok"></i>
												</c:if>
												50
											</a></li>
										<li><a
												href="coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=60">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==60}">
													<i class="icon-ok"></i>
												</c:if>
												60
											</a></li>
										<li><a
												href="coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=70">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==70}">
													<i class="icon-ok"></i>
												</c:if>
												70
											</a></li>
										<li><a
												href="coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=80">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==80}">
													<i class="icon-ok"></i>
												</c:if>
												80
											</a></li>
										<li><a
												href="coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=90">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==90}">
													<i class="icon-ok"></i>
												</c:if>
												90
											</a></li>
										<li><a
												href="coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=100">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==100}">
													<i class="icon-ok"></i>
												</c:if>
												100
											</a></li>
										<li class="divider"></li>
										<li><a
												href="coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=1000">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize>100}">
													<i class="icon-ok"></i>
												</c:if>
												不分页
											</a></li>
									</ul>
								</div>
							</div></td>

					</c:if>

				</tr>
			</table>

			<c:choose>
				<c:when test="${pagedCourseTeachingClassHomeworkScoreinfoViewData.totalCount >0}">





					<div class="Course-Table">
						<div class="gridseparator"></div>

						<div class="container-fluid">
							<div class="row">
								<div class="col-md-1">
									<strong>#</strong>
								</div>
								<div class="col-md-3">
									<strong>作业名称</strong>
								</div>
								<div class="col-md-2">
									<strong>分值类型</strong>
								</div>
								
								<div class="col-md-2">
									<strong>显示类型</strong>
								</div>
								
								<div class="col-md-3">
									<strong>操作</strong>
								</div>
							</div>
							<div class="gridseparator"></div>

							<c:set var="index" value="1"></c:set>
							<c:forEach var="dataitem" items="${pagedCourseTeachingClassHomeworkScoreinfoViewData.result}">
								<div class="row">
									<div class="col-md-1">
										<input type="checkbox" value="">
										${(pagedCourseTeachingClassHomeworkScoreinfoViewData.currentPageNo-1) * pagedCourseTeachingClassHomeworkScoreinfoViewData.pageSize +index}
									</div>
									<div class="col-md-3">${dataitem.homeworkBaseinfo.title }</div>
									<div class="col-md-2">${dataitem.scoreMarkingType.name }</div>
									<div class="col-md-2">${dataitem.scoreShowType.name }</div>
									

									<div class="col-md-3">
										<button type="button" class="btn btn-default btn-xs"
											onclick="onUpdate('${dataitem.scoreInfo.id}','${dataitem.homeworkBaseinfo.title}')">修改...</button>
										
									</div>
								</div>
								<c:set var="index" value="${index + 1}"></c:set>
							</c:forEach>
							<div class="gridseparator"></div>

						</div>



						<mathtop:PageBar pageUrl="/coursehomeworkscoreinfo/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html" pageAttrKey="pagedCourseTeachingClassHomeworkScoreinfoViewData" />

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
						<h3>没有找到记录</h3>

						

					</div>
				</c:otherwise>
			</c:choose>






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
					<h4 class="modal-title" id="myModalLabel">修改${selectedCourseHomeworkTypeData.name}成绩设置管理</h4>
				</div>

				<form class="form-horizontal" action="<c:url value='/coursehomeworkscoreinfo/updateall-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html'/>"
					method="post">


					<div class="modal-body">
						<input type="hidden" id="id" name="id">
						<h4 class="form-signin-heading">请修改${selectedCourseHomeworkTypeData.name}成绩设置属性</h4>
						<div class="form-group">
							<label for="t_score_marking_type_id" class="col-sm-4 control-label">分值类型</label>
							<div class="col-sm-8">
								<select id="t_score_marking_type_id" name="t_score_marking_type_id" class="form-control input-sm">
								<c:forEach var="d" items="${lstCourseTeachingClassScoreMarkingType}">
									<option value="${d.id}">${d.name}</option>
								</c:forEach>
								<option value="">NULL</option>

							</select>
							</div>
						</div>

						<div class="form-group">
							<label for="t_score_show_type_id" class="col-sm-4 control-label">显示类型</label>
							<div class="col-sm-8">
								<select id="t_score_show_type_id" name="t_score_show_type_id" class="form-control input-sm">
								<c:forEach var="d" items="${lstCourseTeachingClassScoreShowType}">
									<option value="${d.id}">${d.name}</option>
								</c:forEach>
								<option value="">NULL</option>

							</select>
							</div>
						</div>





					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>

				</form>
			</div>
		</div>
	</div>







	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/sysLastInclude.jsp"%>

	<script type="text/javascript">
		$("#collapseHomeworkScoreInfoManager").addClass("in");
		$("#homeworkscoreinfo${selectedCourseHomeworkTypeData.id}").addClass(
				"active");
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

		function onUpdateAllSettings() {
			
			
			$('#updateModal').find('.modal-body #id').val(id);
			
			

			$('#updateModal').modal('show');

		}

	

		
	</script>




</body>
</html>

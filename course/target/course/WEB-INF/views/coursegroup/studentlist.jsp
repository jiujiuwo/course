<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "studentgroup");
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

			<div class="CourseContentHeader">${selectedCourseTeachingClassViewData.course.name}${selectedCourseTeachingClassStudentGroupViewData.group.name }小组</div>

			<div class="CourseContentHeaderSeparatorLine"></div>





			<c:choose>

				<c:when test="${empty selectedCourseTeachingClassStudentGroupViewData}">

					<div class="alert alert-warning alert-dismissible fade in" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>暂未分组</strong>

					</div>

				</c:when>

				<c:otherwise>

					<div class="Course-Table" style="overflow: hidden;">
					
					<div class="container-fluid" style="overflow: hidden;">

								<div class="row show-grid">
									<div class="col-md-1 text-right">
										<strong>小组名称</strong>
									</div>
									<div class="col-md-10">
									${selectedCourseTeachingClassStudentGroupViewData.group.name }
									</div>
								</div>
								</div>
								
						<div class="gridseparator"></div>




						<div class="container-fluid">
							<div class="row">
								<div class="col-md-1">
									<strong>#</strong>
								</div>
								<div class="col-md-2">
									<strong>姓名</strong>
								</div>
								<div class="col-md-2">
									<strong>学号</strong>
								</div>
								<div class="col-md-2">
									<strong>班级</strong>
								</div>
								<div class="col-md-2">
									<strong>角色</strong>
								</div>

							</div>
						</div>

						<div class="gridseparator"></div>









						<div class="container-fluid">
							<c:set var="stuindex" value="1"></c:set>
							<c:forEach var="stu"
								items="${selectedCourseTeachingClassStudentGroupViewData.studentViewDatas}">
								<div class="row">
									<div class="col-md-1">${stuindex }</div>
									<div class="col-md-2">${stu.userbasicinfo.userBasicInfoName }</div>
									<div class="col-md-2">${stu.student.studentNum }</div>
									<div class="col-md-2">${stu.naturalclass.name }</div>
									<div class="col-md-2">
										
									</div>

								</div>


								<c:set var="stuindex" value="${stuindex + 1}"></c:set>
							</c:forEach>
						</div>










						<div class="gridseparator"></div>

					</div>


				</c:otherwise>
			</c:choose>






		</div>
	</div>





	<!-- 简单修改对话框 -->
	<div class="modal fade" id="autoGroupModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">为${selectedCourseTeachingClassViewData.courseTeachingClass.name}设置分组要求</h4>
				</div>

				<form class="form-horizontal" action="<c:url value="/school/update.html"/>" method="post">


					<div class="form-group">
						<label for="numpergroup" class="col-sm-2 control-label">每组人数</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="numpergroup" name="numpergroup"
								placeholder="每组人数" value="2">
						</div>
					</div>

					<div class="form-group">
						<label for="numpergroup" class="col-sm-2 control-label">每组人数</label>
						<div class="col-sm-9">

							<div class="radio">
								<label>
									<input type="radio" name="sexconfig" id="optionsRadios1" value="0" checked>
									分组时不考虑男女
								</label>
							</div>
							<div class="radio">
								<label>
									<input type="radio" name="sexconfig" id="optionsRadios2" value="1">
									分组时考虑男女，严格男女分组。如果男/女出现落单时，则将其加入到其它男/女组中
								</label>
							</div>

							<div class="radio">
								<label>
									<input type="radio" name="sexconfig" id="optionsRadios2" value="2">
									分组时考虑男女，尽量男女各自分配。如果男/女出现落单时，则将其合并为一组（该组为男女组合）
								</label>
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






	<script>
		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onDelete(id) {
			var url = "location='<c:url value="coursehomework/DELETE-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"/>"
					+ id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}
	</script>




</body>
</html>

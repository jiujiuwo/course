<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "courseforum");
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
<%@ include file="../../shared/importJs.jsp"%>

<link href="<c:url value='/css/pages/index.css'/>" rel="stylesheet"
	type="text/css" />
<link
	href="<c:url value='/plugins/summernote-master/dist/summernote.css'/>"
	rel="stylesheet" type="text/css" />
<script
	src="<c:url value='/plugins/summernote-master/dist/summernote.js'/>"></script>

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
				<li><a href="<c:url value="/coursecontent/index-${selectedCourseTeachingClassViewData.courseTeachingClass.id}.html"/>">${selectedCourseTeachingClassViewData.course.name}</a></li>
				<li class="active">课程论坛</li>
			</ol>

			<div class="CourseContentHeader">${selectedCourseTeachingClassViewData.courseTeachingClass.name}论坛</div>

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
								onclick="location='<c:url value="/courseforum/addtopic-${selectedCourseTeachingClassID}.html"/>'">增加</button>

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
						<div class="col-md-4">
							<strong>主题</strong>
						</div>
						<div class="col-md-2">
							<strong>发布日期</strong>
						</div>
						<div class="col-md-2">
							<strong>最后修改日期</strong>
						</div>
						<div class="col-md-1">
							<strong>浏览次数</strong>
						</div>
						<div class="col-md-1">
							<strong>操作</strong>
						</div>
					</div>

					<div class="gridseparator"></div>


					<c:set var="index" value="0"></c:set>
					<c:forEach var="data" items="${pagedForumTopicViewData.result}">
						<div class="row show-grid">
							<div class="col-md-1">

								${pagedForumTopicViewData.totalCount-(pagedForumTopicViewData.currentPageNo-1) * pagedForumTopicViewData.pageSize -index}
							</div>
							<div class="col-md-4">
								<a
									href="<c:url value="/courseforumreply/list-${selectedCourseTeachingClassID}-${data.forumtopic.id}.html"/>">${data.forumtopic.title}</a>
							</div>
							<div class="col-md-2">
								<fmt:formatDate value="${data.forumtopic.createdDate}"
									pattern="yyyy-MM-dd HH:mm:ss" />

							</div>
							<div class="col-md-2">
								<fmt:formatDate value="${data.forumtopic.lastModifiedDate}"
									pattern="yyyy-MM-dd HH:mm:ss" />

							</div>
							<div class="col-md-1">${data.forumtopic.viewCount}</div>
							<div class="col-md-1">

								<c:if
									test="${data.forumtopic.userId==sessionScope.USER_CONTEXT.user.id or sessionScope.USER_CONTEXT.teacher!=null}">

									<button type="button" class="btn btn-default btn-xs"
										onclick="location='<c:url value="/courseforum/updatetopic-${selectedCourseTeachingClassID}-${data.forumtopic.id}.html"/>'">修改...</button>



									<button type="button" class="btn btn-default btn-xs"
										onclick="onDelete('${data.forumtopic.id}')">删除</button>
								</c:if>
							</div>
						</div>
						<c:set var="index" value="${index + 1}"></c:set>
					</c:forEach>

					<c:if test="${pagedForumTopicViewData.totalCount>0}">
						<div class="gridseparator"></div>
					</c:if>
					
					
				</div>




				<c:if
							test="${not empty selectedCourseTeachingClassID}">
							<mathtop:PageBar
								pageUrl="/courseforum/list-${selectedCourseTeachingClassID}.html"
								pageAttrKey="pagedForumTopicViewData" />        
						</c:if>


			</div>

		</div>
	</div>


	


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
			var url = "location='<c:url value="/courseforum/DELETE-${selectedCourseTeachingClassID}-"/>"
					+ id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}
	</script>

</body>
</html>

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

			<div class="CourseContentHeader">同主题阅读:${selectedCourseForumTopicViewData.forumtopic.title}</div>

			<div class="CourseContentHeaderSeparatorLine"></div>


			<div class="Course-Table">

				<div class="container-fluid" style="overflow: hidden;">



					<c:if test="${pagedForumReplyViewData.currentPageNo==1}">

						<div class="row">
							<div class="col-md-12">


								<div class="container-fluid" style="overflow: hidden;">

									<div class="row" style="background-color: #4169E1;">

										<div class="col-md-9">
											标题:<strong>${selectedCourseForumTopicViewData.forumtopic.title}</strong>
										</div>

										<div class="col-md-2">
											<button type="button" class="btn btn-default btn-xs"
												onclick="location='<c:url value="/courseforumreply/addreply-${selectedCourseTeachingClassID}-${selectedCourseForumTopicViewData.forumtopic.id}.html"/>'">回复</button>


											<c:if
												test="${selectedCourseForumTopicViewData.forumtopic.t_user_id==sessionScope.USER_CONTEXT.user.id or sessionScope.USER_CONTEXT.teacher!=null}">

												<button type="button" class="btn btn-default btn-xs"
													onclick="location='<c:url value="/courseforum/updatetopic-${selectedCourseTeachingClassID}-${selectedCourseForumTopicViewData.forumtopic.id}.html"/>'">修改</button>




											</c:if>
										</div>

										<div class="col-md-1">
											<p class="text-right">0</p>
										</div>

									</div>
									<div class="row"
										style="border-style: none solid solid solid; border-width: 1px; border-color: grey; background-color: #F5F5F5;">

										<div class="col-md-12">
											<p>
												作者:${selectedCourseForumTopicViewData.userbasicinfoviewdata.userbasicinfo.user_basic_info_name}
												(
												<fmt:formatDate
													value="${selectedCourseForumTopicViewData.forumtopic.created_date}"
													pattern="yyyy-MM-dd HH:mm:ss" />
												)
												
												<c:if test="${selectedCourseForumTopicViewData.userbasicinfoviewdata.user.id!=sessionScope.USER_CONTEXT.user.id}">
												<button type="button" class="btn btn-default btn-xs"
													onclick="location='<c:url value="/mailbox/newmailtoother-${selectedCourseTeachingClassID}-${selectedCourseForumTopicViewData.userbasicinfoviewdata.user.id}.html"/>'">回信</button>

												</c:if>
											</p>
											${selectedCourseForumTopicViewData.forumtopic.content}
											<c:if
												test="${fn:length(selectedCourseForumTopicViewData.topicFileList)>0}">

												<div class="gridseparator"></div>
												<c:choose>

													<c:when
														test="${fn:length(selectedCourseForumTopicViewData.topicFileList)==1}">
														<a
															href="<c:url value="/courseforumtopicfile/download-${selectedCourseForumTopicViewData.topicFileList[0].id}.html"/>">
															${selectedCourseForumTopicViewData.topicFileList[0].filename}</a>
													</c:when>

													<c:otherwise>
														<ul>
															<c:forEach var="datafile"
																items="${selectedCourseForumTopicViewData.topicFileList}">
																<li><a
																	href="<c:url value="/courseforumtopicfile/download-${datafile.id}.html"/>">
																		${datafile.filename}</a></li>
															</c:forEach>
														</ul>
													</c:otherwise>
												</c:choose>
											</c:if>
										</div>
									</div>
								</div>

							</div>
						</div>
					</c:if>



					<c:set var="index" value="1"></c:set>
					<c:forEach var="data" items="${pagedForumReplyViewData.result}">




						<div class="row">
							<div class="col-md-12">


								<div class="container-fluid" style="overflow: hidden;">

									<div class="row" style="background-color: #6495ED;">

										<div class="col-md-9">
											标题:<strong>${data.forumreply.title}</strong>
										</div>

										<div class="col-md-2">
											<button type="button" class="btn btn-default btn-xs"
												onclick="location='<c:url value="/courseforumreply/addreply-${selectedCourseTeachingClassID}-${selectedCourseForumTopicViewData.forumtopic.id}.html"/>'">回复</button>


											<c:if
												test="${selectedCourseForumTopicViewData.forumtopic.t_user_id==sessionScope.USER_CONTEXT.user.id or sessionScope.USER_CONTEXT.teacher!=null}">
												<button type="button" class="btn btn-default btn-xs"
													onclick="location='<c:url value="/courseforumreply/updatereply-${selectedCourseTeachingClassID}-${selectedCourseForumTopicViewData.forumtopic.id}-${data.forumreply.id}.html"/>'">修改</button>

												<button type="button" class="btn btn-default btn-xs"
													onclick="onDelete('${data.forumreply.id}')">删除</button>

											</c:if>
										</div>

										<div class="col-md-1">
											<p class="text-right">${(pagedForumReplyViewData.currentPageNo-1) * pagedForumReplyViewData.pageSize +index}</p>
										</div>

									</div>
									<div class="row"
										style="border-style: none solid solid solid; border-width: 1px; border-color: grey; background-color: #F5F5F5;">

										<div class="col-md-12">
											<p>
												作者:${data.userbasicinfoviewdata.userbasicinfo.user_basic_info_name}
												(
												<fmt:formatDate value="${data.forumreply.created_date}"
													pattern="yyyy-MM-dd HH:mm:ss" />
												)
											</p>
											<div id="reply${data.forumreply.id}">
												${data.forumreply.content}</div>


											<c:if test="${fn:length(data.replyFileList)>0}">
												<div class="gridseparator"></div>
												<c:choose>

													<c:when test="${fn:length(data.replyFileList)==1}">
														<a
															href="<c:url value="/courseforumreplyfile/download-${data.replyFileList[0].id}.html"/>">
															${data.replyFileList[0].filename}</a>
													</c:when>

													<c:otherwise>
														<ul>
															<c:forEach var="datafile" items="${data.replyFileList}">
																<li><a
																	href="<c:url value="/courseforumreplyfile/download-${datafile.id}.html"/>">
																		${datafile.filename}</a></li>
															</c:forEach>
														</ul>
													</c:otherwise>
												</c:choose>
											</c:if>

										</div>

									</div>
								</div>
							</div>
						</div>


						<c:set var="index" value="${index + 1}"></c:set>
					</c:forEach>



				</div>




				<c:if
					test="${not empty selectedCourseTeachingClassID and not empty selectedCourseForumTopicID }">
					<mathtop:PageBar
						pageUrl="/courseforumreply/list-${selectedCourseTeachingClassID}-${selectedCourseForumTopicID}.html"
						pageAttrKey="pagedForumReplyViewData" />
				</c:if>

			</div>

		</div>
	</div>




	<%@ include file="../../shared/dialog.jsp"%>

	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>


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
			var url = "location='<c:url value="/courseforumreply/DELETE-${selectedCourseTeachingClassID}-${selectedCourseForumTopicID}-"/>"
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

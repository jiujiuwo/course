<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@page import="java.util.*,java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "coursereference");
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

			<div class="CourseContentHeader">${selectedCourseReferenceTypeData.name}
				<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">
			管理
			</c:if>
			</div>

			<div class="CourseContentHeaderSeparatorLine"></div>


			<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">
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
									onclick="location='<c:url value="/coursereference/addreference-${selectedCourseTeachingClassID}-${selectedCourseReferenceTypeData.id}.html"/>'">增加</button>


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
			</c:if>



			<c:choose>

				<c:when
					test="${pagedCourseTeachingClassReferenceViewData.totalCount==0}">

					<div class="alert alert-warning alert-dismissible fade in"
						role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>暂未有${selectedCourseReferenceTypeData.name}资料</strong>

						<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">
							<p class="text-info">
								请为<em>${selectedCourseReferenceTypeData.name}</em>布置作业
							</p>
						</c:if>
					</div>

				</c:when>

				<c:otherwise>


					<%
						//显示具体内容
					%>

					<div class="Course-Table">

						<div class="container-fluid" style="overflow: hidden;">
							<div class="gridseparator"></div>
							<div class="row show-grid">
								<div class="col-md-1">
									<strong>#</strong>
								</div>
								<div class="col-md-3">
									<strong>标题</strong>
								</div>

								<div class="col-md-4">
									<strong>附件</strong>
								</div>


								<div class="col-md-1">
									<strong>发布日期</strong>
								</div>

								<div class="col-md-1">
									<strong>修改日期</strong>
								</div>
								<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">
									<div class="col-md-1">
										<strong>操作</strong>
									</div>
								</c:if>

							</div>

							<div class="gridseparator"></div>


							<c:set var="index" value="0"></c:set>
							<c:forEach var="data"
								items="${pagedCourseTeachingClassReferenceViewData.result}">
								<div class="row show-grid">
									<div class="col-md-1">
										<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">
											<input type="checkbox" value="">
										</c:if>
										${pagedCourseTeachingClassReferenceViewData.totalCount-(pagedCourseTeachingClassReferenceViewData.currentPageNo-1) * pagedCourseTeachingClassReferenceViewData.pageSize -index}
									</div>


									<div class="col-md-3">
										<a
											href="<c:url value="/coursereference/content-${selectedCourseTeachingClassID}-${selectedCourseReferenceTypeData.id}-${data.reference.id}.html"/>">${data.reference.title}</a>
									</div>

									<div class="col-md-4">
										<%
											//附件
										%>



										<c:choose>

											<c:when test="${fn:length(data.fileList)==1}">
												<a
													href="<c:url value="/coursereferencefile/download-${data.fileList[0].id}.html"/>">
													<span class="glyphicon glyphicon-download" aria-hidden="true"></span>${data.fileList[0].filename}</a>(${data.fileList[0].filelength})
											</c:when>

											<c:otherwise>
												<ul>
													<c:forEach var="datafile" items="${data.fileList}">
														<li><a
															href="<c:url value="/coursereferencefile/download-${datafile.id}.html"/>">
																<span class="glyphicon glyphicon-download" aria-hidden="true"></span>${datafile.filename}</a>(${datafile.filelength})</li>
													</c:forEach>
												</ul>
											</c:otherwise>
										</c:choose>



									</div>




									<div class="col-md-1">
										<fmt:formatDate value="${data.reference.pubdate}"
											pattern="yyyy-MM-dd HH:mm" />
									</div>

									<div class="col-md-1">
										<fmt:formatDate value="${data.reference.modifieddate}"
											pattern="yyyy-MM-dd HH:mm" />
									</div>


									<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">

										<div class="col-md-1">
											<button type="button" class="btn btn-default btn-xs"
												onclick="location='<c:url value="/coursereference/updatereference-${selectedCourseTeachingClassID}-${selectedCourseReferenceTypeData.id}-${data.reference.id}.html"/>'">修改...</button>



											<button type="button" class="btn btn-default btn-xs"
												onclick="onDelete('${data.reference.id}','${data.reference.title}')">删除</button>
										</div>
									</c:if>

								</div>
								<c:set var="index" value="${index + 1}"></c:set>
							</c:forEach>

							<c:if
								test="${pagedCourseTeachingClassReferenceViewData.totalCount>0}">
								<div class="gridseparator"></div>
							</c:if>

						</div>




						<mathtop:PageBar
							pageUrl="/coursehomework/add-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html"
							pageAttrKey="pagedCourseTeachingClassReferenceViewData" />

					</div>

				</c:otherwise>
			</c:choose>

		</div>
	</div>


	<%@ include file="../../shared/dialog.jsp"%>

	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>


	<script type="text/javascript">
		$("#collapseReference").addClass("in");
		$("#referencetype${selectedCourseReferenceTypeData.id}").addClass(
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

		function onDelete(id, name) {
			var url = "location='<c:url value="coursereference/DELETE-${selectedCourseTeachingClassID}-${selectedCourseReferenceTypeData.id}-"/>"
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

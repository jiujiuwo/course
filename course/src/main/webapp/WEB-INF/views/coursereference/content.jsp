<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
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
<%@ include file="../../shared/importJs.jsp"%>

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
	<div id="ClassInfo">
		<%@ include file="../../shared/CourseTeachingClassInfo.jsp"%>


	</div>
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

			<div class="CourseContentHeader">${selectedCourseTeachingClassViewData.teachingclass.name}-${selectedCourseReferenceTypeData.name}-${selectedCourseReferenceViewData.reference.title }</div>

			<div class="CourseContentHeaderSeparatorLine"></div>




			<div class="Course-Table">




				<div class="modal-body">



					<div class="form-group">
						<label for="name" class="control-label">标题</label>
						<div>${selectedCourseReferenceViewData.reference.title}</div>

					</div>


					<div class="form-group">
						<label for="note" class="control-label">内容</label>
						<div>${selectedCourseReferenceViewData.reference.content }</div>
					</div>

					<div class="form-group">

						<label for="exampleInputFile" class=" control-label">附件</label>
						<div>
							<c:choose>
							
							<c:when
									test="${fn:length(selectedCourseReferenceViewData.fileList)==0}">
									无
								</c:when>

								<c:when
									test="${fn:length(selectedCourseReferenceViewData.fileList)==1}">
									<a
										href="<c:url value="/coursereferencefile/download-${selectedCourseReferenceViewData.fileList[0].id}.html"/>">
										${selectedCourseReferenceViewData.fileList[0].filename}</a>
								</c:when>

								<c:otherwise>
									<ul>
										<c:forEach var="datafile"
											items="${selectedCourseReferenceViewData.fileList}">
											<li><a
												href="<c:url value="/coursereferencefile/download-${datafile.id}.html"/>">
													${datafile.filename}</a></li>
										</c:forEach>
									</ul>
								</c:otherwise>
							</c:choose>
						</div>


					</div>

				</div>


			</div>

		</div>
	</div>






	<%@ include file="../../shared/dialog.jsp"%>

	<%@ include file="../../shared/pageFooter.jsp"%>




	<script type="text/javascript">
		$("#collapseReference").addClass("in");
		$("#referencetype${selectedCourseReferenceTypeData.id}").addClass(
				"active");
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

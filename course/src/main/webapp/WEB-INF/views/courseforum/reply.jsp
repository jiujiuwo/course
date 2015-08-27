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
<script
	src="<c:url value='/plugins/summernote-master/lang/summernote-zh-CN.js'/>"></script>

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

			<div class="CourseContentHeader">
				回复${selectedCourseTeachingClassViewData.teachingclass.name}论坛
				<blockquote>${selectedCourseForumTopicViewData.forumtopic.title}</blockquote>
			</div>

			<div class="CourseContentHeaderSeparatorLine"></div>




			<div class="Course-Table">


				<form
					action="<c:url value="/courseforumreply/add-${selectedCourseTeachingClassID}-${selectedCourseForumTopicViewData.forumtopic.id}.html"/>"
					enctype="multipart/form-data" method="post">

					<div class="modal-body">



						<div class="form-group">
							<label for="name" class="control-label">标题</label> <input
								type="text" id="name" class="form-control" name="title"
								value="Re:${selectedCourseForumTopicViewData.forumtopic.title}"
								placeholder="标题" required>
						</div>


						<div class="form-group">
							<label for="note" class="control-label">内容</label>
							<div class="summernote" id="addcontentDiv"></div>
							<textarea class="form-control" rows="5" name="content"
								id="addcontentTextArea" style="display: none;"></textarea>
						</div>
						
						<div class="form-group">

							<label for="exampleInputFile" class=" control-label">附件</label>
							<p class="help-block">任意文件类型均可。</p>
							<input type="file" multiple="multiple" id="exampleInputFile"
								name="file" />

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






	<%@ include file="../../shared/dialog.jsp"%>

	<%@ include file="../../shared/pageFooter.jsp"%>




	<script type="text/javascript">
		$('.summernote').summernote({
			height : 300, // set editor height
			lang : 'zh-CN' // default: 'en-US'
		//  minHeight: null,             // set minimum height of editor
		//  maxHeight: null,             // set maximum height of editor

		//  focus: true,                 // set focus to editable area after initializing summernote
		});
	</script>



	<script>
		function onAdd() {
			var sHTML = $('#addcontentDiv').code();
			$('#addcontentTextArea').text(sHTML);

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

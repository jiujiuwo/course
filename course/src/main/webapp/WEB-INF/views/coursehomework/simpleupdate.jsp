<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@page import="java.util.*,java.text.*"%>
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
				<li><a href="#">课程系统</a></li>
				<li><a href="<c:url value="/coursecontent/index-${selectedCourseTeachingClassViewData.courseteachingclass.id}.html"/>">${selectedCourseTeachingClassViewData.course.name}</a></li>
				<li class="active">${selectedCourseHomeworkTypeData.name}</li>
			</ol>

			<div class="CourseContentHeader">修改${selectedCourseTeachingClassViewData.teachingclass.name}-${selectedCourseHomeworkTypeData.name}</div>

			<div class="CourseContentHeaderSeparatorLine"></div>


			<form class="form-horizontal" id="myUpdateForm" name="myUpdateForm"
			action="<c:url value="/coursehomework/updatesimple-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}.html"/>"
				enctype="multipart/form-data" method="post">

					<div class="modal-body"
						style="margin-left: 10px; margin-right: 10px; overflow: hidden;">

						<div class="form-group">
							<label for="name" class=" control-label">标题</label> <input
								type="text" id="inputtitle" class="form-control"
								name="updatetitle" value="" placeholder="标题" required>
						</div>


						<div class="form-group">
						<label for="note" class="control-label">内容</label>
						<div class="summernote"></div>
						<textarea class="form-control" rows="5" name="updatecontent"
							id="addcontentTextArea" style="display: none;"></textarea>
					</div>
			
						<div class="form-group">
							<label for="enddate" class="control-label">截止时间</label>

							<div class="input-group date form_date col-sm-5" data-date=""
								data-date-format="yyyy-mm-dd hh:ii "
								data-link-field="dtp_input2" data-link-format="yyyy-mm-dd hh:ii">
								<input class="form-control" size="16" type="text" id="enddate"
									value='<fmt:formatDate value="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.enddate}"
														pattern="yyyy-MM-dd HH:mm" />'
														 name="enddate" readonly required>
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>

						</div>						

					</div>

					<div class="modal-footer">
						<button type="submit" id="updatebtn" class="btn btn-primary" onclick="onAdd()">修改</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</form>


		</div>
	</div>





	<%@ include file="../../shared/dialog.jsp"%>

	<%@ include file="../../shared/pageFooter.jsp"%>


	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>


	<script type="text/javascript">
		$("#collapseHomework").addClass("in");
		$("#homeworktype${selectedCourseHomeworkTypeData.id}").addClass(
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

		$('.summernote').summernote({
			height : 300, // set editor height
			lang : 'zh-CN' // default: 'en-US'
		//  minHeight: null,             // set minimum height of editor
		//  maxHeight: null,             // set maximum height of editor

		//  focus: true,                 // set focus to editable area after initializing summernote
		});
	</script>
	
	<script type="text/javascript">
	$('#inputtitle')
	.val(
			'${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}');

		$('.summernote').code(
				'${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.content}');
		//	$("#contentDiv").code("adf");
	</script>

	<script>
		function onAdd() {

			var sHTML = $('.summernote').code();
			$('#addcontentTextArea').text(sHTML);

		}

		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onfiletyperadio(type) {
			if (type == 0) {
				//具体类型
				$('#filetypepanel0').show();
				$('#filetypepanel1').hide();
			} else {
				//不限制类型
				$('#filetypepanel1').show();
				$('#filetypepanel0').hide();
			}
		}

		function onfilenameformatradio(type) {
			if (type == 0) {
				//具体类型
				$('#filenameformatpanel0').show();
				$('#filenameformatpanel1').hide();
			} else {
				//自定义类型
				$('#filenameformatpanel1').show();
				$('#filenameformatpanel0').hide();
			}
		}

		function onfilecountradio(type) {
			if (type == 0) {
				//具体类型
				$('#filetypegroup').hide();
				$('#filenameformatgroup').hide();
			} else {
				//自定义类型
				$('#filetypegroup').show();
				$('#filenameformatgroup').show();
			}
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

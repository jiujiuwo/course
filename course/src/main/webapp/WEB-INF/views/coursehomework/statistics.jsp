<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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


			<div class="CourseContentHeader">${selectedCourseHomeworkTypeData.name}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}提交管理</div>


			<div class="CourseContentHeaderSeparatorLine"></div>

			<div class="panel-group" id="accordion" role="tablist"
				style="margin-right: 5px;" aria-multiselectable="true">

				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						<h4 class="panel-title">
							<a role="button" data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" aria-expanded="true"
								aria-controls="collapseOne">
								${selectedCourseHomeworkTypeData.name}-<strong>${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}</strong>具体内容
							</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in"
						style="overflow: hidden;" role="tabpanel"
						aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="Course-Table">

								<div class="container-fluid" style="overflow: hidden;">

									<div class="row show-grid">

										<div class="col-md-2">
											<strong>内容</strong>
										</div>

										<div class="col-md-2">
											<strong>附件</strong>
										</div>

										<div class="col-md-2">
											<strong>发布日期</strong>
										</div>

										<div class="col-md-2">
											<strong>结束日期</strong>
										</div>
										<div class="col-md-2">
											<strong>下载</strong>
										</div>


									</div>


									<div class="row show-grid">


										<div class="col-md-2">

											<p>${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.content}</p>
										</div>

										<div class="col-md-2">


											<c:choose>

												<c:when
													test="${fn:length(selectedCourseHomeworkBasicInfoViewData.homeworkFileList)==1}">
													<a
														href="<c:url value="/coursehomeworkfile/download-${selectedCourseHomeworkBasicInfoViewData.homeworkFileList[0].id}.html"/>">
														${selectedCourseHomeworkBasicInfoViewData.homeworkFileList[0].filename}</a>
												</c:when>

												<c:otherwise>
													<ul>
														<c:forEach var="datafile"
															items="${selectedCourseHomeworkBasicInfoViewData.homeworkFileList}">
															<li><a
																href="<c:url value="/coursehomeworksubmitfile/download-${datafile.id}.html"/>">
																	${datafile.filename}</a></li>
														</c:forEach>
													</ul>
												</c:otherwise>
											</c:choose>

										</div>

										<div class="col-md-2">
											<fmt:formatDate
												value="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.pubdate}"
												pattern="yyyy-MM-dd HH:mm" />
										</div>

										<div class="col-md-2">
											<fmt:formatDate
												value="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.enddate}"
												pattern="yyyy-MM-dd HH:mm" />
										</div>
										<div class="col-md-2">
											<a
												href="<c:url value="/coursehomeworksubmitfile/downloadall-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}.html"/>">
												全体学生实验报告</a> <a
												href="<c:url value="/coursehomeworkfile/download-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}.html"/>">
												全体学生实验批复</a>
										</div>

									</div>
								</div>
							</div>



						</div>
					</div>
				</div>


				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingTwo">
						<h4 class="panel-title">
							<a role="button" data-toggle="collapse" data-parent="#accordion"
								href="#collapseTwo" aria-expanded="true"
								aria-controls="collapseTwo"> 图表 </a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse in"
						style="overflow: hidden;" role="tabpanel"
						aria-labelledby="headingTwo">
						<div class="panel-body">

							<canvas id="myChart" width="400" height="400"></canvas>


						</div>
					</div>
				</div>


				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingThree">
						<h4 class="panel-title">
							<a role="button" data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree" aria-expanded="true"
								aria-controls="collapseThree"> 人数统计 </a>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse in"
						style="overflow: hidden;" role="tabpanel"
						aria-labelledby="headingThree">
						<div class="panel-body">
							<div class="container-fluid" style="overflow: hidden;">
								<div class="gridseparator"></div>
								<div class="row show-grid">
									<div class="col-md-1">
										<strong>总共人数</strong>
									</div>
									<div class="col-md-1">
										<strong>已交作业人数</strong>
									</div>
									<div class="col-md-1">
										<strong>未交作业人数</strong>
									</div>
									<div class="col-md-1">
										<strong>已批复作业人数</strong>
									</div>
								</div>

								<div class="row show-grid">
									<div class="col-md-1">
										${selectedCourseHomeworkStatisticsData.totalCount }</div>
									<div class="col-md-1">
										${selectedCourseHomeworkStatisticsData.submitCount }</div>
									<div class="col-md-1">
										${selectedCourseHomeworkStatisticsData.totalCount -selectedCourseHomeworkStatisticsData.submitCount}
									</div>
									<div class="col-md-1">
										${selectedCourseHomeworkStatisticsData.replyCount }</div>
								</div>


							</div>


						</div>
					</div>
				</div>

				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingFour">
						<h4 class="panel-title">
							<a role="button" data-toggle="collapse" data-parent="#accordion"
								href="#collapseFour" aria-expanded="true"
								aria-controls="collapseFour"> 名单 </a>
						</h4>
					</div>
					<div id="collapseFour" class="panel-collapse collapse"
						style="overflow: hidden;" role="tabpanel"
						aria-labelledby="headingFour">
						<div class="panel-body">
							<div class="container-fluid" style="overflow: hidden;">
								<div class="gridseparator"></div>
								<div class="row show-grid">
									<div class="col-md-1">
										<strong>#</strong>
									</div>
									<div class="col-md-1">
										<strong>班级</strong>
									</div>
									<div class="col-md-1">
										<strong>学号</strong>
									</div>
									<div class="col-md-1">
										<strong>姓名</strong>
									</div>
									<div class="col-md-1">
										<strong>作业已交</strong>
									</div>
									<div class="col-md-1">
										<strong>作业已批复</strong>
									</div>
								</div>


								<c:set var="index" value="1"></c:set>
								<c:forEach var="data"
									items="${pagedCourseTeachingClassHomeworkStatisticsViewData.result}">
									<div class="row show-grid">
										<div class="col-md-1">

											${(pagedCourseTeachingClassHomeworkStatisticsViewData.currentPageNo-1) * pagedCourseTeachingClassHomeworkStatisticsViewData.pageSize +index}
										</div>

										<div class="col-md-1">${data.student.naturalclass.name}
										</div>

										<div class="col-md-1">
											${data.student.student.student_num}</div>

										<div class="col-md-1">
											${data.student.userbasicinfo.user_basic_info_name}</div>

										<div class="col-md-1">
											<c:choose>

												<c:when test="${fn:length(data.homeworksubmit)==0}">
													<p class="text-danger">否</p>
												</c:when>

												<c:otherwise>
													<p class="text-success">
														是
														<button type="button" class="btn btn-default btn-xs"
															onclick="location='<c:url value="/coursehomework/reply-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.homeworkbaseinfo.id}-${data.student.student.id}.html"/>'">查看</button>
													</p>

												</c:otherwise>
											</c:choose>

										</div>


										<%
											//作业已批复
										%>
										<div class="col-md-1">
											<c:choose>
												<c:when test="${fn:length(data.homeworkreply)==0}">
													<p class="text-danger">否</p>
												</c:when>

												<c:otherwise>
													<p class="text-success">
														是

														<button type="button" class="btn btn-default btn-xs"
															onclick="location='<c:url value="/coursehomework/reply-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.homeworkbaseinfo.id}-${data.student.student.id}.html"/>'">查看</button>


													</p>
												</c:otherwise>
											</c:choose>
										</div>



									</div>
									<c:set var="index" value="${index + 1}"></c:set>
								</c:forEach>




							</div>


						</div>
					</div>
				</div>



			</div>

		</div>
	</div>




	<%@ include file="../../shared/dialog.jsp"%>

	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>

	<script src="<c:url value='/plugins/Chart.min.js'/>"
		type="text/javascript"></script>



	<script type="text/javascript">
		$("#collapseHomework").addClass("in");
		$("#homeworktype${selectedCourseHomeworkTypeData.id}").addClass(
				"active");
	</script>

	<script type="text/javascript">
		$(function() {

			var url = "<c:url value='/coursehomework/getchartdatavalue-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}.json'/>";

			$.get(url, function(data, status) {
				if (status == "success") {

					//Get context with jQuery - using jQuery's .get() method.
					var ctx = $("#myChart").get(0).getContext("2d");

					var myNewChart = new Chart(ctx).Pie(data);
				}
			});

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

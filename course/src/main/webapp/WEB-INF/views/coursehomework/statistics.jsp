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
				<li><a href="#">课程系统</a></li>
				<li><a
					href="<c:url value="/coursecontent/index-${selectedCourseTeachingClassViewData.courseteachingclass.id}.html"/>">${selectedCourseTeachingClassViewData.course.name}</a></li>
				<li class="active">${selectedCourseHomeworkTypeData.name}</li>
			</ol>


			<div class="CourseContentHeader">${selectedCourseHomeworkTypeData.name}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}统计</div>


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
										<div class="col-md-1 text-right">
											<strong>内容</strong>
										</div>
										<div class="col-md-10">${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.content}</div>
									</div>


									<div class="row show-grid">
										<div class="col-md-1 text-right">
											<strong>附件</strong>
										</div>
										<div class="col-md-10">
											<c:choose>

												<c:when
													test="${fn:length(selectedCourseHomeworkBasicInfoViewData.homeworkFileList)==0}">
													无
												</c:when>

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
																href="<c:url value="/coursehomeworkfile/download-${datafile.id}.html"/>">
																	${datafile.filename}</a></li>
														</c:forEach>
													</ul>
												</c:otherwise>
											</c:choose>
										</div>
									</div>

									<div class="row show-grid">
										<div class="col-md-1 text-right">
											<strong>发布日期</strong>
										</div>
										<div class="col-md-10">
											<fmt:formatDate
												value="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.pubdate}"
												pattern="yyyy-MM-dd HH:mm" />
										</div>
									</div>

									<div class="row show-grid">
										<div class="col-md-1 text-right">
											<strong>截止日期</strong>
										</div>
										<div class="col-md-10">
											<fmt:formatDate
												value="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.enddate}"
												pattern="yyyy-MM-dd HH:mm" />
										</div>
									</div>


									<div class="row show-grid">
										<div class="col-md-1 text-right">
											<strong>提交文件要求</strong>
										</div>
										<div class="col-md-10">
											<c:choose>

												<c:when
													test="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.filecount==0}">
													不需要提交文件
												</c:when>
												<c:when
													test="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.filecount==-1}">
													提交多个文件（数目不限制）<br>文件类型:${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.filetype}<br>文件名称格式:${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.filenameformat}
												</c:when>

												<c:otherwise>
													提交${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.filecount}个文件<br>文件类型:${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.filetype}<br>文件名称格式:${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.filenameformat}		
												</c:otherwise>
											</c:choose>
										</div>
									</div>

									<div class="row show-grid">
										<div class="col-md-1 text-right">
											<strong>操作</strong>
										</div>
										<div class="col-md-10">
											<c:if
												test="${selectedCourseHomeworkStatisticsData.submitCount>0}">



												<a
													href="<c:url value="/coursehomeworksubmitfile/downloadall-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}.html"/>">
													下载全部学生的<strong>${selectedCourseHomeworkTypeData.name}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}</strong>的作业
												</a>
												<p />
												<a
													href="<c:url value="/coursehomeworkreplyfile/download-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}.html"/>">
													下载全部学生的<strong>${selectedCourseHomeworkTypeData.name}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}</strong>的批复
												</a>

											</c:if>
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
									<div class="col-md-3">
										<strong>总共人数</strong>
									</div>
									<div class="col-md-3">
										<strong>已交作业人数</strong>
									</div>
									<div class="col-md-3">
										<strong>未交作业人数</strong>
									</div>
									<div class="col-md-3">
										<strong>已批复作业人数</strong>
									</div>
								</div>

								<div class="row show-grid">
									<div class="col-md-3">
										${selectedCourseHomeworkStatisticsData.totalCount }</div>
									<div class="col-md-3">
										${selectedCourseHomeworkStatisticsData.submitCount }</div>
									<div class="col-md-3">
										${selectedCourseHomeworkStatisticsData.totalCount -selectedCourseHomeworkStatisticsData.submitCount}
									</div>
									<div class="col-md-3">
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
									<div class="col-md-2">
										<strong>班级</strong>
									</div>
									<div class="col-md-2">
										<strong>学号</strong>
									</div>
									<div class="col-md-2">
										<strong>姓名</strong>
									</div>
									<div class="col-md-2">
										<strong>作业已交</strong>
									</div>
									<div class="col-md-1">
										<strong>迟交</strong>
									</div>
									<div class="col-md-2">
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

										<div class="col-md-2">${data.student.naturalclass.name}
										</div>

										<div class="col-md-2">
											${data.student.student.student_num}</div>

										<div class="col-md-2">
											${data.student.userbasicinfo.user_basic_info_name}</div>

										<div class="col-md-2">
											<c:choose>

												<c:when test="${fn:length(data.homeworksubmit)==0}">
													<p class="text-danger">否</p>
												</c:when>

												<c:otherwise>
													<p class="text-success">
														是

														<button class="btn btn-default btn-xs" type="button"
															data-toggle="collapse"
															data-target="#collapse${data.student.student.id}"
															aria-expanded="false"
															aria-controls="collapse${data.student.student.id}">
															查看</button>
													</p>







												</c:otherwise>
											</c:choose>

										</div>

										<div class="col-md-1">
											<c:choose>

												<c:when test="${data.delayedSubmit}">
													<p class="text-danger">是</p>
												</c:when>

												<c:otherwise>

													<c:choose>

														<c:when test="${fn:length(data.homeworksubmit)>0}">
															<p class="text-success">否</p>
														</c:when>

														<c:otherwise>
															<p class="text-danger">-</p>

														</c:otherwise>
													</c:choose>



												</c:otherwise>
											</c:choose>
										</div>


										<%
											//作业已批复
										%>
										<div class="col-md-2">
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

									<div class="collapse" id="collapse${data.student.student.id}">
										<div class="row show-grid">
											<div class="col-md-5"></div>
											<div class="col-md-7">
												<div class="well">

													<c:forEach var="datasubmit" items="${data.homeworksubmit}">
														<c:choose>

															<c:when
																test="${fn:length(datasubmit.homeworksubmitFileList)==1}">


																<a
																	href='<c:url value="/coursehomeworksubmitfile/download-${datasubmit.homeworksubmitFileList[0].id}.html"/>'>${datasubmit.homeworksubmitFileList[0].filename}</a>


															</c:when>

															<c:otherwise>
																<ul>
																	<c:forEach var="datafile"
																		items="${datasubmit.homeworksubmitFileList}">
																		<li><a
																			href="<c:url value="/coursehomeworksubmitfile/download-${datafile.id}.html"/>">
																				${datafile.filename}</a></li>
																	</c:forEach>
																</ul>
															</c:otherwise>
														</c:choose>
													</c:forEach>

												</div>
											</div>
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

	<script src="<c:url value='/plugins/Chart.js-master/Chart.min.js'/>"
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

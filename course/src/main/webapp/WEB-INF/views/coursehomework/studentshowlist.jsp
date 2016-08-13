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

			<div class="CourseContentHeader">${selectedCourseHomeworkTypeData.name}</div>

			<div class="CourseContentHeaderSeparatorLine"></div>


			<c:choose>

				<c:when test="${pagedCourseTeachingClassHomeworkBaseinfoViewData.totalCount==0}">

					<div class="alert alert-warning alert-dismissible fade in" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>暂未有${selectedCourseHomeworkTypeData.name}作业</strong>
						<p class="text-info">
							授课教师尚未留<em>${selectedCourseHomeworkTypeData.name}</em>作业
						</p>
					</div>

				</c:when>

				<c:otherwise>

					<div class="panel-group" id="accordion" role="tablist" style="margin-right: 5px;"
						aria-multiselectable="true">



						<c:set var="index" value="0"></c:set>
						<c:forEach var="data" items="${pagedCourseTeachingClassHomeworkBaseinfoViewData.result}">


							<div
								class="panel 
								
								
								<c:choose>													
													<c:when test="${data.homeworkbaseinfo.canReply}">
														panel-default
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${data.studentSubmitted}">
																panel-success
															</c:when>
															<c:when test="${data.homeworkbaseinfo.canStudentSubmit}">
																panel-primary
															</c:when>
															<c:otherwise>
															panel-warning
															</c:otherwise>
															
														</c:choose>
													</c:otherwise>
												</c:choose>
												
												
					
					
					">
								<div class="panel-heading" role="tab" id="heading${data.homeworkbaseinfo.id}">
									<h4 class="panel-title">
										<a role="button" data-toggle="collapse" data-parent="#accordion"
											href="#collapse${data.homeworkbaseinfo.id}" aria-expanded="true"
											aria-controls="collapse${data.homeworkbaseinfo.id}">
											${pagedCourseTeachingClassHomeworkBaseinfoViewData.totalCount-(pagedCourseTeachingClassHomeworkBaseinfoViewData.currentPageNo-1) * pagedCourseTeachingClassHomeworkBaseinfoViewData.pageSize -index}：${selectedCourseHomeworkTypeData.name}-${data.homeworkbaseinfo.title}-具体内容
										</a>
									</h4>
								</div>
								<div id="collapse${data.homeworkbaseinfo.id}"
									class="panel-collapse collapse
						<c:choose>													
													<c:when test="${data.homeworkbaseinfo.canReply}">
														
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${data.studentSubmitted}">
																
															</c:when>
															<c:when test="${data.homeworkbaseinfo.canStudentSubmit}">
																in
															</c:when>
															
														</c:choose>
													</c:otherwise>
												</c:choose>
						"
									style="overflow: hidden;" role="tabpanel"
									aria-labelledby="heading${data.homeworkbaseinfo.id}">
									<div class="panel-body">


										<div class="container-fluid" style="overflow: hidden;">

											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>内容</strong>
												</div>
												<div class="col-md-11">
													<div class="panel panel-default">
														<div class="panel-heading">
															<h3 class="panel-title">${data.homeworkbaseinfo.title}</h3>
														</div>
														<div class="panel-body">${data.homeworkbaseinfo.content}</div>
													</div>
												</div>
											</div>

											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>作业类型</strong>
												</div>
												<div class="col-md-10">
													<c:choose>
														<c:when test="${data.homeworkbaseinfo.flagGroup}">
															<i class="icon-group"></i>小组作业<a href="coursegroup/studentlist-${selectedCourseTeachingClassID}.html">小组信息</a></c:when>
														<c:otherwise>
															<i class="icon-user"></i>个人作业</c:otherwise>
													</c:choose>
												</div>
											</div>



											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>附件</strong>
												</div>
												<div class="col-md-10">
													<c:choose>

														<c:when test="${fn:length(data.homeworkFileList)==0}">
													无
												</c:when>

														<c:when test="${fn:length(data.homeworkFileList)==1}">
															<a
																href="<c:url value="/coursehomeworkfile/download-${data.homeworkFileList[0].id}.html"/>">
																${data.homeworkFileList[0].filename}</a>
														</c:when>

														<c:otherwise>
															<ul>
																<c:forEach var="datafile" items="${data.homeworkFileList}">
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
													<fmt:formatDate value="${data.homeworkbaseinfo.pubdate}" pattern="yyyy-MM-dd HH:mm" />
												</div>
											</div>

											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>截止日期</strong>
												</div>
												<div class="col-md-10">
													<fmt:formatDate value="${data.homeworkbaseinfo.enddate}" pattern="yyyy-MM-dd HH:mm" />
												</div>
											</div>


											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>提交文件要求</strong>
												</div>
												<div class="col-md-10">
													<c:choose>
														<c:when test="${data.homeworkbaseinfo.fileRequirement.size==0}">
															不需要提交文件
														</c:when>

														<c:otherwise>
															<ul class="list-group">
																<c:forEach var="dataNode" items="${data.homeworkbaseinfo.fileRequirement.array}">

																	<a href="javascript:void(0);" class="list-group-item">
																		<div style="margin-left:${dataNode.level*50}px;">

																			<c:choose>
																				<c:when test="${dataNode.data.fileType==2}">
																					<h4>目录</h4>
																				</c:when>
																				<c:otherwise>
																					<h4>文件</h4>
																				</c:otherwise>
																			</c:choose>


																			<c:if test="${dataNode.data.fileType!=2}">
																				<div>
																					<span><strong>文件类型:</strong></span>${dataNode.data.fileTypeDescription}</div>
																				<c:choose>
																					<c:when test="${dataNode.data.fileCount==-1}">
																						<div>
																							<span><strong>文件个数:</strong></span>不限制个数
																						</div>
																					</c:when>
																					<c:otherwise>文件</c:otherwise>
																				</c:choose>
																				<div>
																					<span><strong>文件个数:</strong></span>${dataNode.data.fileCount}</div>


																			</c:if>

																			<div>
																				<span><strong>文件名称格式:</strong></span> ${dataNode.data.filenameRequirementVal}
																			</div>

																			<div>
																				<button type="button" class="btn btn-default btn-xs"
																					onclick="onGetExample('${data.homeworkbaseinfo.id}','${ dataNode.data.nodeID}')">取得合法文件名示例</button>
																				<ul class="list-group" id="examplefilenames${data.homeworkbaseinfo.id}${ dataNode.data.nodeID}">

																				</ul>
																			</div>

																		</div>
																	</a>

																</c:forEach>
															</ul>
														</c:otherwise>
													</c:choose>





												</div>
											</div>


										</div>


										<%
											//迟交作业
										%>
										<c:if test="${fn:length(data.homeworkDelayedList)>0 and not data.studentSubmitted}">

											<div class="row show-grid">

												<div class="col-md-1 text-right">
													<strong>迟交作业</strong>
												</div>
												<div class="col-md-10">

													<div class="panel-group" id="accordion" role="tablist"
														style="margin-right: 5px; margin-top: 10px;" aria-multiselectable="true">



														<c:set var="delayindex" value="1"></c:set>

														<c:forEach var="dataDelay" items="${data.homeworkDelayedList}">
															<div
																class="panel 
					<c:if test="${dataDelay.canStudentSubmit==true }"> 
						panel-primary
						</c:if>
						
						<c:if test="${dataDelay.canStudentSubmit==false }"> 
						panel-default
						</c:if>
					
					">

																<div class="panel-heading" role="tab" id="heading${dataDelay.id}">
																	<h4 class="panel-title">
																		<a role="button" data-toggle="collapse" data-parent="#accordion"
																			href="#collapse${dataDelay.id}" aria-expanded="true"
																			aria-controls="collapse${dataDelay.id}">
																			迟交${delayindex}：${selectedCourseHomeworkTypeData.name}-${data.homeworkbaseinfo.title}
																		</a>
																	</h4>
																</div>
																<div id="collapse${dataDelay.id}"
																	class="panel-collapse collapse
						<c:if test="${data.homeworkbaseinfo.canStudentSubmit==true }"> 
						in
						</c:if>
						"
																	style="overflow: hidden;" role="tabpanel" aria-labelledby="heading${dataDelay.id}">
																	<div class="panel-body">

																		<div class="container-fluid" style="overflow: hidden;">
																			<div class="row show-grid">
																				<div class="col-md-1 text-right">
																					<strong>发布日期</strong>
																				</div>
																				<div class="col-md-10">
																					<fmt:formatDate value="${dataDelay.pubdate}" pattern="yyyy-MM-dd HH:mm" />
																				</div>
																			</div>

																			<div class="row show-grid">
																				<div class="col-md-1 text-right">
																					<strong>截止日期</strong>
																				</div>
																				<div class="col-md-10">
																					<fmt:formatDate value="${dataDelay.enddate}" pattern="yyyy-MM-dd HH:mm" />
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>



														</c:forEach>

													</div>
												</div>
											</div>
										</c:if>



										<div class="row show-grid">
											<div class="col-md-1 text-right">
												<strong>作业提交</strong>
											</div>
											<div class="col-md-10">


												<c:choose>
													<c:when test="${data.canStudentSubmit }">


														<button type="button" class="btn btn-default btn-xs"
															onclick="location='<c:url value="/coursehomeworksubmit/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.id}.html'"/>">

															<c:choose>
																<c:when test="${data.homeworkbaseinfo.canReply}">
																	<c:choose>
																		<c:when test="${data.studentSubmitted}">
																查看已提交作业
															</c:when>
																		<c:when test="${data.homeworkbaseinfo.canStudentSubmit}">
																提交作业
															</c:when>
																		<c:when test="${data.canStudentSubmit}">
																提交迟交作业
															</c:when>
																		<c:otherwise>
															未提交作业，但作业已经过期，不能提交
															</c:otherwise>

																	</c:choose>


																</c:when>
																<c:otherwise>
																	<c:choose>
																		<c:when test="${data.studentSubmitted}">
																查看或修改已提交作业
															</c:when>
																		<c:when test="${data.homeworkbaseinfo.canStudentSubmit}">
																提交作业
															</c:when>
																		<c:when test="${data.canStudentSubmit}">
																提交迟交作业
															</c:when>

																	</c:choose>
																</c:otherwise>
															</c:choose>
														</button>



													</c:when>
													<c:otherwise>

														<button type="button" class="btn btn-default btn-xs"
															onclick="location='<c:url value="/coursehomeworksubmit/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.id}.html'"/>">

															<c:choose>
																<c:when test="${data.homeworkbaseinfo.canReply}">
																	<c:choose>
																		<c:when test="${data.studentSubmitted}">
																查看已提交作业
															</c:when>
																		<c:when test="${data.canStudentSubmit}">
																提交作业
															</c:when>
																		<c:otherwise>
															未提交作业，但作业已经过期，不能提交
															</c:otherwise>

																	</c:choose>


																</c:when>
																<c:otherwise>
																	<c:choose>
																		<c:when test="${data.studentSubmitted}">
																查看或修改已提交作业
															</c:when>
																		<c:when test="${data.canStudentSubmit}">
																提交作业
															</c:when>

																	</c:choose>
																</c:otherwise>
															</c:choose>
														</button>

													</c:otherwise>
												</c:choose>

											</div>
										</div>







									</div>
								</div>
							</div>


							<c:set var="index" value="${index + 1}"></c:set>
						</c:forEach>

					</div>
					<mathtop:PageBar
						pageUrl="/coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html"
						pageAttrKey="pagedCourseTeachingClassHomeworkBaseinfoViewData" />

				</c:otherwise>
			</c:choose>



		</div>
	</div>





	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/sysLastInclude.jsp"%>


	<script type="text/javascript">
		$("#collapseHomework").addClass("in");
		$("#homeworktype${selectedCourseHomeworkTypeData.id}").addClass(
				"active");
	</script>




	<script>
	//取得合法文件名称示例
		function onGetExample(t_homework_baseinfo_id,nodeID) {

			var url = "<c:url value='/coursehomework/getRightExampleFileName-'/>"
					+ t_homework_baseinfo_id +"-"+nodeID+ ".json";

			$("#examplefilenames" + t_homework_baseinfo_id+nodeID).empty();

			$.get(url, function(data, status) {
				if (status == "success") {

					for (var i = 0; i < data.length; i++) {
						$("#examplefilenames" + t_homework_baseinfo_id+nodeID).append(
								"<li  class='list-group-item'>" + data[i]
										+ "</li>");
					}
				}
			});

		}
		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}
	</script>


</body>
</html>

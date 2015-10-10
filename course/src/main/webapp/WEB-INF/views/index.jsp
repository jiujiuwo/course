<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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

<%@ include file="../shared/importCss.jsp"%>


<link href="<c:url value='/css/pages/index.css'/>" rel="stylesheet"
	type="text/css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="home">

	<%@ include file="../shared/pageHeader.jsp"%>


	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="CourseNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">


			<div class="Course-Table">


				<c:choose>

					<c:when
						test="${pagedMailBoxReceivedViewData!=null and pagedMailBoxReceivedViewData.totalCount>0}">



						<div class="panel-group" id="accordion" role="tablist"
							aria-multiselectable="true">




							<div class="panel panel-info">
								<div class="panel-heading" role="tab"
									id="headingMailBoxReceived">
									<h4 class="panel-title">
										<a role="button" data-toggle="collapse"
											data-parent="#accordion" href="#collapseMailBoxReceived"
											aria-expanded="true" aria-controls="collapseMailBoxReceived">
											未读邮件 </a>
									</h4>
								</div>
								<div id="collapseMailBoxReceived"
									class="panel-collapse collapse in" role="tabpanel"
									aria-labelledby="headingMailBoxReceived">
									<div class="panel-body">



										<div class="container-fluid" style="overflow: hidden;">
											<div class="gridseparator"></div>
											<div class="row show-grid">
												<div class="col-md-1">
													<strong>#</strong>
												</div>
												<div class="col-md-2">
													<strong>发件人</strong>
												</div>
												<div class="col-md-4">
													<strong>主题</strong>
												</div>
												<div class="col-md-2">
													<strong>发送日期</strong>
												</div>

												<div class="col-md-1">
													<strong>操作</strong>
												</div>
											</div>

											<div class="gridseparator"></div>


											<c:set var="index" value="0"></c:set>
											<c:forEach var="data"
												items="${pagedMailBoxReceivedViewData.result}">
												<div class="row show-grid">
													<div class="col-md-1">

														${pagedMailBoxReceivedViewData.totalCount-(pagedMailBoxReceivedViewData.currentPageNo-1) * pagedMailBoxReceivedViewData.pageSize -index}
													</div>

													<div class="col-md-2">
														<c:if test="${data.userFrom.student!=null}">
							${data.userFrom.student.student_num}-
							</c:if>
														${data.userFrom.userbasicinfo.user_basic_info_name}
													</div>

													<div class="col-md-4">
														<c:choose>
															<c:when test="${selectedCourseTeachingClassID!=null}">
																<c:choose>
																	<c:when test="${data.received.hasRead}">
																		<a
																			href="<c:url value="/mailbox/receivedcontentwithcourse-${selectedCourseTeachingClassID}-${data.received.id}.html"/>"><span
																			class="text-success">${data.received.subject}</span></a>
																	</c:when>
																	<c:otherwise>
																		<strong><a
																			href="<c:url value="/mailbox/receivedcontentwithcourse-${selectedCourseTeachingClassID}-${data.received.id}.html"/>"><span
																				class="text-info">${data.received.subject}</span></a></strong>
																	</c:otherwise>
																</c:choose>
															</c:when>
															<c:otherwise>
																<c:choose>
																	<c:when test="${data.received.hasRead}">
																		<a
																			href="<c:url value="/mailbox/receivedcontent-${data.received.id}.html"/>"><span
																			class="text-success">${data.received.subject}</span></a>
																	</c:when>
																	<c:otherwise>
																		<strong><a
																			href="<c:url value="/mailbox/receivedcontent-${data.received.id}.html"/>"><span
																				class="text-info">${data.received.subject}</span></a></strong>
																	</c:otherwise>
																</c:choose>
															</c:otherwise>
														</c:choose>


													</div>
													<div class="col-md-2">
														<fmt:formatDate value="${data.received.senddate}"
															pattern="yyyy-MM-dd HH:mm:ss" />

													</div>


													<div class="col-md-1">


														<button type="button" class="btn btn-default btn-xs"
															onclick="onDelete('${data.received.id}','${data.received.subject}')">删除</button>

													</div>
												</div>
												<c:set var="index" value="${index + 1}"></c:set>
											</c:forEach>

											<c:if test="${pagedMailBoxReceivedViewData.totalCount>0}">
												<div class="gridseparator"></div>
											</c:if>

										</div>




										<c:if test="${not empty selectedCourseTeachingClassID  }">
											<mathtop:PageBar
												pageUrl="/courseforum/list-{selectedCourseTeachingClassID}.html"
												pageAttrKey="pagedMailBoxReceivedViewData" />
										</c:if>



									</div>
								</div>
							</div>


						</div>





					</c:when>
					<c:otherwise>
						<div class="alert alert-warning alert-dismissible fade in"
							role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>请选择左侧菜单栏相应命令</strong>
							<p class="text-info">请在左侧菜单栏中选择相应命令。</p>
						</div>
					</c:otherwise>
				</c:choose>


			</div>
		</div>
	</div>



	<%@ include file="../shared/pageFooter.jsp"%>

	<%@ include file="../shared/importJs.jsp"%>
</body>
</html>

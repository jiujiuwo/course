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

			<div class="CourseContentHeader">${selectedCourseHomeworkTypeData.name}管理</div>

			<div class="CourseContentHeaderSeparatorLine"></div>



			<table>
				<tr>
					<td><div class="btn-group" role="group" aria-label="...">
							<!-- Split button -->
							<div class="btn-group">
								<button type="button" class="btn btn-default btn-sm">选择</button>
								<button type="button" class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown"
									aria-expanded="false">
									<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
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
							<c:choose>
								<c:when test="${selectedCourseTeachingClassStudentGroupStatisticsViewData.nGroupCount>0 }">
									<button type="button" class="btn btn-default btn-sm"
										onclick="location='<c:url value="/coursehomework/addhomework-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-1.html"/>'">为小组增加作业</button>

									<button type="button" class="btn btn-default btn-sm"
										onclick="location='<c:url value="/coursehomework/addhomework-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-0.html"/>'">为每个学生增加作业</button>

								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-default btn-sm"
										onclick="location='<c:url value="/coursehomework/addhomework-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html"/>'">增加</button>

								</c:otherwise>

							</c:choose>





						</div></td>
					<td style="width: 10px;"></td>
					<td><div class="input-group input-group-sm">
							<input type="text" class="form-control" id="SearchText" placeholder="Search for..." required>
							<span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="onSearch()">搜索</button>
							</span>
						</div></td>
				</tr>
			</table>

			<c:choose>

				<c:when test="${pagedCourseTeachingClassHomeworkBaseinfoViewData.totalCount==0}">

					<div class="alert alert-warning alert-dismissible fade in" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>暂未有${selectedCourseHomeworkTypeData.name}作业</strong>
						<p class="text-info">
							请为<em>${selectedCourseHomeworkTypeData.name}</em>布置作业
						</p>
					</div>

				</c:when>

				<c:otherwise>

					<div class="panel-group" id="accordion" role="tablist"
						style="margin-right: 5px; margin-top: 10px;" aria-multiselectable="true">



						<c:set var="index" value="0"></c:set>
						<c:forEach var="data" items="${pagedCourseTeachingClassHomeworkBaseinfoViewData.result}">


							<div
								class="panel 
					<c:if test="${data.homeworkbaseinfo.canStudentSubmit==true }"> 
						panel-primary
						</c:if>
						
						<c:if test="${data.homeworkbaseinfo.canStudentSubmit==false }"> 
						panel-default
						</c:if>
					
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
						<c:if test="${data.homeworkbaseinfo.canStudentSubmit==true }"> 
						in
						</c:if>
						"
									style="overflow: hidden;" role="tabpanel"
									aria-labelledby="heading${data.homeworkbaseinfo.id}">
									<div class="panel-body">


										<div class="container-fluid" style="overflow: hidden;">

											<%
												//操作
											%>
											<div class="row show-grid">
												<div class="col-md-12">
													<button type="button" class="btn btn-default btn-xs"
														onclick="location='<c:url value="/coursehomework/updatehomework-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.id}.html"/>'">修改</button>

													<button type="button" class="btn btn-default btn-xs"
														onclick="location='<c:url value="/coursehomework/simpleupdatehomework-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.id}.html"/>'">简单修改</button>

													<button type="button" class="btn btn-default btn-xs"
														onclick="onDelete('${data.homeworkbaseinfo.id}')">删除</button>



													<c:if test="${data.homeworkbaseinfo.canStudentSubmit==true }">
														<button type="button" class="btn btn-default btn-xs"
															onclick="location='<c:url value="/coursehomework/replylist-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.id}.html"/>'">查看</button>
													</c:if>

													<c:if test="${data.homeworkbaseinfo.canReply==true }">
														<button type="button" class="btn btn-default btn-xs"
															onclick="location='<c:url value="/coursehomework/statistics-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.id}.html"/>'">统计</button>
														<button type="button" class="btn btn-default btn-xs"
															onclick="location='<c:url value="/coursehomework/replylist-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.id}.html"/>'">批复</button>

														<button type="button" class="btn btn-default btn-xs"
															onclick="location='<c:url value="/coursehomework/newdelayhomework-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.id}.html"/>'">布置迟交作业</button>
													</c:if>
												</div>


											</div>


											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>标题</strong>
												</div>
												<div class="col-md-10">
													<strong>${data.homeworkbaseinfo.title}</strong>
												</div>
											</div>

											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>内容</strong>
												</div>
												<div class="col-md-10">${data.homeworkbaseinfo.content}</div>
											</div>

											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>作业类型</strong>
												</div>
												<div class="col-md-10">
													<c:choose>
														<c:when test="${data.homeworkbaseinfo.flagGroup}"><i class="icon-group"></i>小组作业</c:when>
														<c:otherwise><i class="icon-user"></i>个人作业</c:otherwise>
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
																href="<c:url value="coursehomeworkfile/download-${data.homeworkFileList[0].id}.html"/>">
																${data.homeworkFileList[0].filename}</a>
														</c:when>

														<c:otherwise>
															<ul>
																<c:forEach var="datafile" items="${data.homeworkFileList}">
																	<li><a href="<c:url value="coursehomeworkfile/download-${datafile.id}.html"/>">
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

																			</div>
																		</a>

																</c:forEach>
															</ul>
														</c:otherwise>
													</c:choose>

													<c:if test="${sessionScope.USER_CONTEXT.student!=null}">
														<c:if test="${data.homeworkbaseinfo.fileRequirement.size()!=0}">
															<br>
															<button type="button" class="btn btn-default btn-xs"
																onclick="ongetexample('${data.homeworkbaseinfo.id}')">取得合法文件名示例</button>
															<ul class="list-group" id="examplefilenames${data.homeworkbaseinfo.id}">

															</ul>
														</c:if>
													</c:if>


												</div>
											</div>






											<c:if test="${fn:length(data.homeworkDelayedList)>0}">

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





										</div>


									</div>
								</div>
							</div>


							<c:set var="index" value="${index + 1}"></c:set>
						</c:forEach>

					</div>
					<c:if test="${not empty selectedCourseTeachingClassID}">
						<mathtop:PageBar
							pageUrl="/coursehomework/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html"
							pageAttrKey="pagedCourseTeachingClassHomeworkBaseinfoViewData" />
					</c:if>

				</c:otherwise>
			</c:choose>





		</div>
	</div>





	<!-- 简单修改对话框 -->
	<div class="modal fade" id="updateSimpleModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改${selectedCourseHomeworkTypeData.name}要求</h4>
				</div>





			</div>
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
		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onDelete(id) {
			var url = "location='<c:url value="coursehomework/DELETE-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"/>"
					+ id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

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




</body>
</html>

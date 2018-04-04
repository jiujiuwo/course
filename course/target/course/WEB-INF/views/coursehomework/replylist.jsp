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
<%@ include file="../../shared/importdatetimepickercss.jsp"%>


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



			<div class="panel-group" id="accordion" role="tablist" style="margin-right: 5px;"
				aria-multiselectable="true">

				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						<h4 class="panel-title">
							<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
								aria-expanded="true" aria-controls="collapseOne">
								${selectedCourseHomeworkTypeData.name}-<strong>${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}</strong>具体内容
							</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in" style="overflow: hidden;"
						role="tabpanel" aria-labelledby="headingOne">
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
											<strong>作业类型</strong>
										</div>
										<div class="col-md-10">
											<c:choose>
												<c:when test="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.flagGroup}">
													<i class="icon-group"></i>小组作业</c:when>
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

												<c:when test="${fn:length(selectedCourseHomeworkBasicInfoViewData.homeworkFileList)==0}">
													无
												</c:when>

												<c:when test="${fn:length(selectedCourseHomeworkBasicInfoViewData.homeworkFileList)==1}">
													<a
														href="<c:url value="/coursehomeworkfile/download-${selectedCourseHomeworkBasicInfoViewData.homeworkFileList[0].id}.html"/>">
														${selectedCourseHomeworkBasicInfoViewData.homeworkFileList[0].filename}</a>
												</c:when>

												<c:otherwise>
													<ul>
														<c:forEach var="datafile"
															items="${selectedCourseHomeworkBasicInfoViewData.homeworkFileList}">
															<li><a href="<c:url value="/coursehomeworkfile/download-${datafile.id}.html"/>">
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
													test="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.fileRequirement.size==0}">
															不需要提交文件
														</c:when>

												<c:otherwise>
													<ul class="list-group">
														<c:forEach var="dataNode"
															items="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.fileRequirement.array}">

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
										</div>
									</div>

									<div class="row show-grid">
										<div class="col-md-1 text-right">
											<strong>操作</strong>
										</div>
										<div class="col-md-10">
											<c:if test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount>0}">

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


			</div>

			<c:choose>

				<c:when test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount==0}">

					<div class="alert alert-warning alert-dismissible fade in" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>暂时还没有学生提交${selectedCourseHomeworkTypeData.name}</strong>
						<p class="text-info">暂时还没有学生提交${selectedCourseHomeworkTypeData.name},请等待一段时间再进行查看。</p>
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


								<div class="col-md-2">
									<c:choose>
										<c:when test="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.flagGroup}">
											<strong>小组</strong>
										</c:when>
										<c:otherwise>
											<strong>姓名</strong>
										</c:otherwise>

									</c:choose>
								</div>

								<div class="col-md-2">
									<strong>标题</strong>
								</div>

								<div class="col-md-2">
									<strong>附件</strong>
								</div>

								<div class="col-md-2">
									<strong>最初提交日期</strong>
								</div>

								<div class="col-md-2">
									<strong>最后修改日期</strong>
								</div>

								<div class="col-md-1">
									<strong>操作</strong>
								</div>


							</div>

							<div class="gridseparator"></div>


							<c:set var="index" value="1"></c:set>
							<c:forEach var="data"
								items="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.result}">
								<div class="row show-grid">
									<div class="col-md-1">
										<input type="checkbox" value="">
										${(pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.currentPageNo-1) * pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.pageSize +index}
									</div>
									<div class="col-md-2">

										<c:choose>
											<c:when test="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.flagGroup}">
											<strong>${data.studentGroupViewData.group.name}</strong>
												<c:forEach var="stu" items="${data.studentGroupViewData.studentViewDatas}">
													<p>${stu.userbasicinfo.userBasicInfoName }(${stu.student.studentNum })</p>
												</c:forEach>
											</c:when>
											<c:otherwise>
												${data.student.student.studentNum}-${data.student.userbasicinfo.userBasicInfoName}
												<button type="button" class="btn btn-default btn-xs"
													onclick="location='<c:url value="/mailbox/newmailtoother-${selectedCourseTeachingClassID}-${data.student.user.id}.html"/>'">回信</button>

											</c:otherwise>

										</c:choose>



									</div>

									<div class="col-md-2">
										<strong>${data.homeworksubmitbaseinfo.title}</strong>
										<p>${data.homeworksubmitbaseinfo.content}</p>
									</div>

									<div class="col-md-2">



										<c:choose>

											<c:when test="${fn:length(data.homeworksubmitFileList)==1}">
												<a
													href="<c:url value="/coursehomeworksubmitfile/download-${data.homeworksubmitFileList[0].id}.html"/>">
													${data.homeworksubmitFileList[0].filename}</a>
											</c:when>

											<c:otherwise>
												<ul>
													<c:forEach var="datafile" items="${data.homeworksubmitFileList}">
														<li><a
																href="<c:url value="/coursehomeworksubmitfile/download-${datafile.id}.html"/>">
																${datafile.filename}</a></li>
													</c:forEach>
												</ul>
											</c:otherwise>
										</c:choose>



									</div>

									<div class="col-md-2">
										<fmt:formatDate value="${data.homeworksubmitbaseinfo.submitdate}"
											pattern="yyyy-MM-dd HH:mm" />
									</div>

									<div class="col-md-2">
										<fmt:formatDate value="${data.homeworksubmitbaseinfo.modifieddate}"
											pattern="yyyy-MM-dd HH:mm" />
									</div>






									<div class="col-md-1">

										<button type="button" class="btn btn-default btn-xs"
											onclick="location='<c:url value="/coursehomeworksubmit/deletestudenthomework-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworksubmitbaseinfo.id}.html"/>'">删除</button>



										<c:choose>
											<c:when test="${data.hasReply==true}">
															已经回复
							<button type="button" class="btn btn-default btn-xs"
													onclick="location='<c:url value="/coursehomework/reply-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfo.id}-${data.student.student.id}.html"/>'">查看</button>

											</c:when>

											<c:otherwise>
												<c:if test="${data.homeworkbaseinfoViewData.homeworkbaseinfo.canReply==true }">
													<button type="button" class="btn btn-default btn-xs"
														onclick="location='<c:url value="/coursehomework/reply-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${data.homeworkbaseinfoViewData.homeworkbaseinfo.id}-${data.student.student.id}.html"/>'">批复</button>
												</c:if>
											</c:otherwise>
										</c:choose>



									</div>
								</div>
								<c:set var="index" value="${index + 1}"></c:set>
							</c:forEach>

							<c:if test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount>0}">
								<div class="gridseparator"></div>
							</c:if>

						</div>




						<c:if test="${not empty selectedCourseTeachingClassID}">
							<mathtop:PageBar
								pageUrl="/coursehomework/replylist-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}.html"
								pageAttrKey="pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData" />
						</c:if>

					</div>

				</c:otherwise>
			</c:choose>





		</div>
	</div>


	<!-- 添加对话框 -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加${selectedCourseHomeworkTypeData.name}要求</h4>
				</div>



				<form class="form-horizontal "
					action="<c:url value="/coursehomework/add-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html"/>"
					enctype="multipart/form-data" method="post">

					<div class="modal-body" style="margin-left: 10px; margin-right: 10px;">

						<div class="form-group">
							<label for="name" class=" control-label">标题</label>
							<input type="text" id="name" class="form-control" name="title" value="" placeholder="标题"
								required>
						</div>


						<div class="form-group">
							<label for="note" class=" control-label">内容</label>
							<textarea class="form-control" rows="5" name="content"></textarea>
						</div>

						<div class="form-group">
							<label for="enddate" class="control-label">截止时间</label>

							<div class="input-group date form_date col-sm-5" data-date=""
								data-date-format="yyyy-mm-dd hh:ii " data-link-field="dtp_input2"
								data-link-format="yyyy-mm-dd hh:ii">
								<input class="form-control" size="16" type="text" id="enddate" value="" name="enddate"
									readonly required>
								<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="hidden" id="dtp_input2" value="" />

						</div>



						<div class="form-group">

							<label for="exampleInputFile" class=" control-label">附件</label>
							<p class="help-block">任意文件类型均可。</p>
							<input type="file" multiple="multiple" id="exampleInputFile" name="file" />

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


	<!-- 修改对话框 -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改${selectedCourseHomeworkTypeData.name}要求</h4>
				</div>



				<form class="form-horizontal" id="myUpdateForm" name="myUpdateForm"
					enctype="multipart/form-data" method="post">

					<div class="modal-body" style="margin-left: 10px; margin-right: 10px;">

						<div class="form-group">
							<label for="name" class=" control-label">标题</label>
							<input type="text" id="inputtitle" class="form-control" name="updatetitle" value=""
								placeholder="标题" required>
						</div>


						<div class="form-group">
							<label for="note" class=" control-label">内容</label>
							<textarea class="form-control" id="inputcontent" rows="5" name="updatecontent"></textarea>
						</div>

						<div class="form-group">
							<label for="enddate" class="control-label">截止时间</label>

							<div class="input-group date form_date col-sm-5" data-date=""
								data-date-format="yyyy-mm-dd hh:ii " data-link-field="dtp_input2"
								data-link-format="yyyy-mm-dd hh:ii">
								<input class="form-control" size="16" type="text" id="inputenddate" value="" name="enddate"
									readonly required>
								<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="hidden" id="dtp_input2" value="" />

						</div>



						<div class="form-group">

							<label for="exampleInputFile" class=" control-label">附件</label>
							<p class="help-block">任意文件类型均可。</p>
							<input type="file" multiple="multiple" id="exampleInputFile" name="file" />

						</div>


					</div>

					<div class="modal-footer">
						<button type="submit" id="updatebtn" class="btn btn-primary">修改</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</form>

			</div>
		</div>
	</div>




	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>
	<%@ include file="../../shared/sysLastInclude.jsp"%>

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
			var url = "location='<c:url value="/coursehomework/DELETE-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"/>"
					+ id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}

		function onUpdate(id, title, content, enddate) {
			var url = "/coursehomework/update-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"
					+ id + ".html";
			$('#updateModal').find('.modal-body #inputid').val(id);
			$('#updateModal').find('.modal-body #inputtitle').val(title);
			$('#updateModal').find('.modal-body #inputcontent').val(content);
			$('#updateModal').find('.modal-body #inputenddate').val(enddate);

			$("#myUpdateForm").attr("action", url);

			$('#updateModal').modal('show');

		}
	</script>



</body>
</html>

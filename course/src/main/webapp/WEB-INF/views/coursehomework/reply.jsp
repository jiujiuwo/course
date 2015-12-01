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
				<li><a href="#">课程系统</a></li>
				<li><a href="<c:url value="/coursecontent/index-${selectedCourseTeachingClassViewData.courseteachingclass.id}.html"/>">${selectedCourseTeachingClassViewData.course.name}</a></li>
				<li class="active">${selectedCourseHomeworkTypeData.name}</li>
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
								${selectedCourseHomeworkTypeData.name}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}-具体内容
							</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse "
						style="overflow: hidden;" role="tabpanel"
						aria-labelledby="headingOne">
						<div class="panel-body">


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


										<div class="panel panel-default" style="margin-bottom: 10px;">
											<div class="panel-heading">合法的文件名(下面文件名均为合法文件名)</div>
											<div class="panel-body">
												<ul class="list-group" id="examplefilenames">

												</ul>
											</div>
										</div>




									</div>
								</div>


							</div>


						</div>
					</div>
				</div>



				<div
					class="panel  					
					<c:choose>
						<c:when
								test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount==0}">
							panel-danger
						</c:when>
	
						<c:otherwise>
							panel-success
						</c:otherwise>
					</c:choose>
					">
					
					<div class="panel-heading" role="tab" id="headingSubmit">
						<h4 class="panel-title">

							<a class="collapsed" role="button" data-toggle="collapse"
								data-parent="#accordion" href="#collapseSubmit"
								aria-expanded="false" aria-controls="collapseSubmit"> <c:choose>
									<c:when
										test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount==0}">
										作业尚未提交
									</c:when>

									<c:otherwise>
										作业已经提交
									</c:otherwise>
								</c:choose>
							</a>
						</h4>
					</div>
					
					<div id="collapseSubmit" class="panel-collapse collapse in"
						style="overflow: hidden;" role="tabpanel"
						aria-labelledby="headingSubmit">
						<div class="panel-body">

							<c:if
								test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount==0}">
								未提交作业
							</c:if>
							<c:if
								test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount>0}">

								<div class="Course-Table">

									<div class="container-fluid" style="overflow: hidden;">

										<c:set var="index" value="1"></c:set>
										<c:forEach var="data"
											items="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.result}">

											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>学生信息</strong>
												</div>

												<div class="col-md-8">
													${data.student.userbasicinfo.user_basic_info_name}(${data.student.naturalclass.name},${data.student.student.student_num})
												</div>

											</div>



											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>标题</strong>
												</div>

												<div class="col-md-8">
													<strong>${data.homeworksubmitbaseinfo.title}</strong>
													<p>${data.homeworksubmitbaseinfo.content}</p>
												</div>

											</div>
											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>附件</strong>
												</div>

												<div class="col-md-8">

													<c:choose>

														<c:when
															test="${fn:length(data.homeworksubmitFileList)==1}">
															<a
																href="<c:url value="/coursehomeworksubmitfile/download-${data.homeworksubmitFileList[0].id}.html"/>">
																${data.homeworksubmitFileList[0].filename}</a>
														</c:when>

														<c:otherwise>
															<ul>
																<c:forEach var="datafile"
																	items="${data.homeworksubmitFileList}">
																	<li><a
																		href="<c:url value="/coursehomeworksubmitfile/download-${datafile.id}.html"/>">
																			${datafile.filename}</a></li>
																</c:forEach>
															</ul>
														</c:otherwise>
													</c:choose>



												</div>
											</div>

											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>提交日期</strong>
												</div>

												<div class="col-md-2">
													<fmt:formatDate
														value="${data.homeworksubmitbaseinfo.submitdate}"
														pattern="yyyy-MM-dd HH:mm" />
												</div>
											</div>

											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>修改日期</strong>
												</div>

												<div class="col-md-2">
													<fmt:formatDate
														value="${data.homeworksubmitbaseinfo.modifieddate}"
														pattern="yyyy-MM-dd HH:mm" />
												</div>
											</div>


											<div class="row show-grid">
												<div class="col-md-1 text-right">
													<strong>操作</strong>
												</div>

												<div class="col-md-2">


													<c:if
														test="${data.homeworkbaseinfo.canStudentSubmit==true }">
														<button type="button" class="btn btn-default btn-xs"
															onclick="onUpdate('${data.homeworksubmitbaseinfo.id}','${data.homeworksubmitbaseinfo.title}','${data.homeworksubmitbaseinfo.content}')">修改...</button>
														<button type="button" class="btn btn-default btn-xs"
															onclick="onDelete('${data.homeworksubmitbaseinfo.id}')">删除</button>
													</c:if>
												</div>
											</div>
											<c:set var="index" value="${index + 1}"></c:set>
										</c:forEach>

										<c:if
											test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount>0}">
											<div class="gridseparator"></div>
										</c:if>

									</div>




									<mathtop:PageBar
										pageUrl="/coursehomework/add-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html"
										pageAttrKey="pagedCourseTeachingClassHomeworkBaseinfoViewData" />

								</div>

							</c:if>

						</div>
					</div>
				</div>
				<div
					class="panel  					
					<c:choose>
					<c:when
										test="${pagedCourseTeachingClassHomeworkReplyBaseinfoViewData.totalCount==0}">
															panel-danger
														</c:when>

									<c:otherwise>
															panel-success
														</c:otherwise>
								</c:choose>
								
				
">
					<div class="panel-heading" role="tab" id="headingReply">
						<h4 class="panel-title">

							<a class="collapsed" role="button" data-toggle="collapse"
								data-parent="#accordion" href="#collapseReply"
								aria-expanded="false" aria-controls="collapseReply"> <c:choose>
									<c:when
										test="${pagedCourseTeachingClassHomeworkReplyBaseinfoViewData.totalCount==0}">
															作业尚未批复
														</c:when>

									<c:otherwise>
															作业已经批复
														</c:otherwise>
								</c:choose>




							</a>

						</h4>
					</div>
					<div id="collapseReply" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingReply">
						<div class="panel-body">

							<c:if
								test="${pagedCourseTeachingClassHomeworkReplyBaseinfoViewData.totalCount==0}">

								<c:if
									test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount>0}">
									<button type="button" class="btn btn-default btn-sm"
										style="margin: 10px;" data-toggle="modal"
										data-target="#addModal">提交批复</button>
								</c:if>
							</c:if>


							<c:if
								test="${pagedCourseTeachingClassHomeworkReplyBaseinfoViewData.totalCount>0}">

								<div class="Course-Table">

									<div class="container-fluid" style="overflow: hidden;">
										<div class="gridseparator"></div>
										<div class="row show-grid">

											<div class="col-md-2">
												<strong>标题</strong>
											</div>

											<div class="col-md-2">
												<strong>附件</strong>
											</div>

											<div class="col-md-2">
												<strong>提交日期</strong>
											</div>

											<div class="col-md-2">
												<strong>修改日期</strong>
											</div>

											<div class="col-md-2">
												<strong>操作</strong>
											</div>


										</div>

										<div class="gridseparator"></div>


										<c:set var="index" value="1"></c:set>
										<c:forEach var="data"
											items="${pagedCourseTeachingClassHomeworkReplyBaseinfoViewData.result}">
											<div class="row show-grid">



												<div class="col-md-2">
													<strong>${data.reply.title}</strong>
													<p>${data.reply.content}</p>
												</div>

												<div class="col-md-2">

													<c:choose>

														<c:when test="${fn:length(data.repplyFileList)==1}">
															<a
																href="<c:url value="/coursehomeworkreplyfile/download-${data.repplyFileList[0].id}.html"/>">
																${data.repplyFileList[0].filename}</a>
														</c:when>

														<c:otherwise>
															<ul>
																<c:forEach var="datafile" items="${data.repplyFileList}">
																	<li><a
																		href="<c:url value="/coursehomeworkreplyfile/download-${datafile.id}.html"/>">
																			${datafile.filename}</a></li>
																</c:forEach>
															</ul>
														</c:otherwise>
													</c:choose>



												</div>

												<div class="col-md-2">
													<fmt:formatDate value="${data.reply.submitdate}"
														pattern="yyyy-MM-dd HH:mm" />
												</div>

												<div class="col-md-2">
													<fmt:formatDate value="${data.reply.modifieddate}"
														pattern="yyyy-MM-dd HH:mm" />
												</div>

												<div class="col-md-2">
													<button type="button" class="btn btn-default btn-xs"
														onclick="onUpdate('${data.reply.id}','${data.reply.title}','${data.reply.content}')">修改...</button>
													<button type="button" class="btn btn-default btn-xs"
														onclick="onDelete('${data.reply.id}')">删除</button>

												</div>



											</div>
											<c:set var="index" value="${index + 1}"></c:set>
										</c:forEach>

										<c:if
											test="${pagedCourseTeachingClassHomeworkReplyBaseinfoViewData.totalCount>0}">
											<div class="gridseparator"></div>
										</c:if>

									</div>

									<mathtop:PageBar
										pageUrl="/coursehomeworksubmit/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html"
										pageAttrKey="pagedCourseTeachingClassHomeworkReplyBaseinfoViewData" />

								</div>

							</c:if>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 添加对话框 -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加${selectedCourseHomeworkTypeData.name}</h4>
				</div>



				<form class="form-horizontal "
					action="<c:url value="/coursehomework/replyadd-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.result[0].homeworksubmitbaseinfo.id}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}-${t_student_id}.html"/>"
					enctype="multipart/form-data" method="post">

					<div class="modal-body"
						style="margin-left: 10px; margin-right: 10px;">

						<div class="form-group">
							<label for="name" class=" control-label">标题</label> <input
								type="text" id="name" class="form-control" name="title" value=""
								placeholder="标题" required>
						</div>


						<div class="form-group">
							<label for="note" class=" control-label">内容</label>
							<textarea class="form-control" rows="5" name="content"></textarea>
						</div>

						<%
							//是否需要文件提交
						%>


						<div class="form-group">

							<label for="exampleInputFile" class=" control-label">附件</label>
							<p class="help-block">注意文件类型。</p>
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


	<!-- 修改对话框 -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改${selectedCourseHomeworkTypeData.name}</h4>
				</div>



				<form class="form-horizontal" id="myUpdateForm" name="myUpdateForm"
					action="<c:url value="/coursehomework/replyupdate-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}-${t_student_id}.html"/>"
					enctype="multipart/form-data" method="post">

					<div class="modal-body"
						style="margin-left: 10px; margin-right: 10px;">
						<input type="text" id="inputid" class="form-control" name="id"
							value="" placeholder="id" style="display: none;">


						<div class="form-group">
							<label for="name" class=" control-label">标题</label> <input
								type="text" id="inputtitle" class="form-control"
								name="updatetitle" value="" placeholder="标题" required>
						</div>


						<div class="form-group">
							<label for="note" class=" control-label">内容</label>
							<textarea class="form-control" id="inputcontent" rows="5"
								name="updatecontent"></textarea>
						</div>





						<div class="form-group">

							<label for="exampleInputFile" class=" control-label">附件</label>
							<p class="help-block">注意文件类型。</p>
							<input type="file" multiple="multiple" id="exampleInputFile"
								name="file" />

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


	<%@ include file="../../shared/dialog.jsp"%>

	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>



	<script type="text/javascript">
		$("#collapseHomework").addClass("in");
		$("#homeworktype${selectedCourseHomeworkTypeData.id}").addClass(
				"active");
	</script>

	<script type="text/javascript">
		$(function() {
			ongetexample(
					"${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}",
					"${t_student_id}");
		});
	</script>


	<script>
		function ongetexample(t_homework_baseinfo_id, t_student_id) {

			var url = "<c:url value='/coursehomework/getRightExampleFileNameEx-'/>"
					+ t_homework_baseinfo_id + "-" + t_student_id + ".json";

			$("#examplefilenames").empty();

			$.get(url, function(data, status) {
				if (status == "success") {

					for (var i = 0; i < data.length; i++) {
						$("#examplefilenames").append(
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

		function onDelete(id) {
			var url = "location='<c:url value="/coursehomework/replydelete-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"/>"
					+ id
					+ "-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}-${t_student_id}.html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}

		function onUpdate(id, title, content) {
			var url = "/coursehomework/replyupdate-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"
					+ id
					+ "-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}-${t_student_id}.html";
			$('#updateModal').find('.modal-body #inputid').val(id);
			$('#updateModal').find('.modal-body #inputtitle').val(title);
			$('#updateModal').find('.modal-body #inputcontent').val(content);

			//	$("#myUpdateForm").attr("action", url);

			$('#updateModal').modal('show');

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

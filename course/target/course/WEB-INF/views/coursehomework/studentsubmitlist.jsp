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
<%@ include file="../../shared/importJs.jsp"%>
<link href="<c:url value='/plugins/summernote-master/dist/summernote.css'/>" rel="stylesheet"
	type="text/css" />
<script src="<c:url value='/plugins/summernote-master/dist/summernote.js'/>"></script>
<script src="<c:url value='/plugins/summernote-master/lang/summernote-zh-CN.js'/>"></script>



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


			<div class="CourseContentHeader">${selectedCourseHomeworkTypeData.name}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}</div>


			<div class="CourseContentHeaderSeparatorLine"></div>

			<div class="panel-group" id="accordion" role="tablist" style="margin-right: 5px;"
				aria-multiselectable="true">

				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						<h4 class="panel-title">
							<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
								aria-expanded="true" aria-controls="collapseOne">
								${selectedCourseHomeworkTypeData.name}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}-具体内容
							</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in" style="overflow: hidden;"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">


							<div class="container-fluid" style="overflow: hidden;">

								<div class="row show-grid">
									<div class="col-md-1 text-right">
										<strong>内容</strong>
									</div>
									<div class="col-md-10">
									<div class="panel panel-default">
														<div class="panel-heading">
															<h3 class="panel-title">${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}</h3>
														</div>
														<div class="panel-body">${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.content}</div>
													</div>
									</div>
								</div>

								<div class="row show-grid">
									<div class="col-md-1 text-right">
										<strong>作业类型</strong>
									</div>
									<div class="col-md-10">
										<c:choose>
											<c:when test="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.flagGroup}">
												<i class="icon-group"></i>小组作业
												<a href="coursegroup/studentlist-${selectedCourseTeachingClassID}.html">小组信息</a>
												</c:when>
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

														<li class="list-group-item">
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
																		onclick="onGetExample('${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}','${ dataNode.data.nodeID}')">取得合法文件名示例</button>
																	<ul class="list-group"
																		id="examplefilenames${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}${ dataNode.data.nodeID}">

																	</ul>
																</div>

															</div>
														</li>

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
					<div class="panel-heading" role="tab" id="headingTwo">
						<h4 class="panel-title">

							<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
								href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
								<c:choose>
									<c:when test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount==0}">
															作业尚未提交
														</c:when>

									<c:otherwise>
															作业已经提交
														</c:otherwise>
								</c:choose>




							</a>

						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse in" style="overflow: hidden;"
						role="tabpanel" aria-labelledby="headingTwo">
						<div class="panel-body">

							<c:if test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount==0}">

								<c:choose>
									<c:when
										test="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.canStudentSubmit }">
										<button type="button" class="btn btn-default btn-sm" style="margin: 10px;"
											onclick="onHomeworkSubmit()">提交作业</button>
									</c:when>

									<c:when test="${selectedCourseHomeworkBasicInfoViewData.canStudentSubmit }">
										<button type="button" class="btn btn-default btn-sm" style="margin: 10px;"
											data-toggle="modal" data-target="#addModal">提交迟交作业</button>
									</c:when>

									<c:otherwise>
										<p class="text-danger">作业已经过期，不能提交。</p>
									</c:otherwise>
								</c:choose>


							</c:if>
							<c:if test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount>0}">

								<div class="Course-Table">

									<div class="container-fluid" style="overflow: hidden;">
										<div class="gridseparator"></div>
										<div class="row show-grid">

											<div class="col-md-4">
												<strong>标题与内容</strong>
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
											items="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.result}">
											<div class="row show-grid">



												<div class="col-md-4">
												
													
													<div class="panel panel-default">
														<div class="panel-heading">
															<h3 class="panel-title">${data.homeworksubmitbaseinfo.title}</h3>
														</div>
														<div class="panel-body">${data.homeworksubmitbaseinfo.content}</div>
													</div>
													
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




												<div class="col-md-2">

													<c:if test="${data.homeworkbaseinfoViewData.canStudentSubmit }">
														<button type="button" class="btn btn-default btn-xs"
															onclick="onUpdate('${data.homeworksubmitbaseinfo.id}','${data.homeworksubmitbaseinfo.title}')">修改...</button>
														<button type="button" class="btn btn-default btn-xs"
															onclick="onDelete('${data.homeworksubmitbaseinfo.id}')">删除</button>
													</c:if>
												</div>
											</div>
											<c:set var="index" value="${index + 1}"></c:set>
										</c:forEach>

										<c:if test="${pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.totalCount>0}">
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
										test="${pagedCourseTeachingClassHomeworkReplyBaseinfoViewData==null or pagedCourseTeachingClassHomeworkReplyBaseinfoViewData.totalCount==0}">
															panel-danger
														</c:when>

									<c:otherwise>
															panel-success
														</c:otherwise>
								</c:choose>
								
				
">
					<div class="panel-heading" role="tab" id="headingReply">
						<h4 class="panel-title">

							<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
								href="#collapseReply" aria-expanded="false" aria-controls="collapseReply">
								<c:choose>
									<c:when
										test="${pagedCourseTeachingClassHomeworkReplyBaseinfoViewData==null or pagedCourseTeachingClassHomeworkReplyBaseinfoViewData.totalCount==0}">
															作业尚未批复
														</c:when>

									<c:otherwise>
															作业已经批复
														</c:otherwise>
								</c:choose>




							</a>

						</h4>
					</div>
					<div id="collapseReply" class="panel-collapse collapse in" role="tabpanel"
						aria-labelledby="headingReply">
						<div class="panel-body">


							<c:if test="${pagedCourseTeachingClassHomeworkReplyBaseinfoViewData.totalCount>0}">

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
													<fmt:formatDate value="${data.reply.submitdate}" pattern="yyyy-MM-dd HH:mm" />
												</div>

												<div class="col-md-2">
													<fmt:formatDate value="${data.reply.modifieddate}" pattern="yyyy-MM-dd HH:mm" />
												</div>



											</div>
											<c:set var="index" value="${index + 1}"></c:set>
										</c:forEach>

										<c:if test="${pagedCourseTeachingClassHomeworkReplyBaseinfoViewData.totalCount>0}">
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
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-upload" aria-hidden="true"></span>
						提交${selectedCourseHomeworkTypeData.name}
					</h4>
				</div>



				<form class="form-horizontal " id="myform"
					action="<c:url value="/coursehomeworksubmit/add-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}.html"/>"
					enctype="multipart/form-data" method="post">

					<div class="modal-body" style="margin-left: 10px; margin-right: 10px;">
					<input type="hidden" id="filesProperty" name="filesProperty" value="">
					<input type="hidden" id="filesProperty" name="filesProperty" value="">
					

						<div class="form-group">
							<label for="name" class=" control-label">标题</label>
							<c:choose>
								<c:when test="${sessionScope.USER_CONTEXT.student!=null}">
									<c:if test="${sessionScope.USER_CONTEXT.userbasicinfo.userBasicInfoName!=null}">
										<input type="text" id="title" class="form-control" name="title"
											value="${sessionScope.USER_CONTEXT.userbasicinfo.userBasicInfoName}(${sessionScope.USER_CONTEXT.user.userName})-${selectedCourseHomeworkTypeData.name}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}"
											placeholder="标题" required />

									</c:if>
								</c:when>
								<c:otherwise>
									<input type="text" id="title" class="form-control" name="title"
										value="${selectedCourseHomeworkTypeData.name}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.title}"
										placeholder="标题" required />
								</c:otherwise>
							</c:choose>

						</div>


						<div class="form-group">
							<label for="note" class=" control-label">内容</label>
							<div class="summernote" id="addcontentDiv"></div>
						<textarea class="form-control" rows="5" name="content" id="addcontentTextArea"
							style="display: none;"></textarea>
						</div>

						<%
							//是否需要文件提交
						%>
						<c:if
							test="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.fileRequirement.size!=0}">

							<div class="form-group">

								<label for="exampleInputFile" class=" control-label">附件</label>


								<c:choose>
									<c:when
										test="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.fileRequirement.size==0}">
															不需要提交文件
														</c:when>

									<c:otherwise>
										<ul class="list-group">
											<c:forEach var="dataNode"
												items="${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.fileRequirement.array}">

												<li class="list-group-item">
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
															<button type="button" class="btn btn-default btn-xs"
																onclick="onDlgGetExample('${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}','${ dataNode.data.nodeID}')">取得合法文件名示例</button>
														</div>

														<div>

															<ul class="list-group"
																id="dlgexamplefilenames${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}${ dataNode.data.nodeID}">

															</ul>
														</div>

														<div>
														<label class="btn btn-default btn-xs">上传文件...
															<input class="myfilecontainercls" type="file" style="position:absolute;clip:rect(0 0 0 0);" onChange="insertTitle(this,'${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}','${ dataNode.data.nodeID}');"
																<c:if
							test="${dataNode.data.fileCount>1}">
								 multiple="multiple"
								  </c:if>
																id="exampleInputFile${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}${ dataNode.data.nodeID}" name="file" /></label>
																
																<div class="list-group myfilecontainercls"
																id="dlguploadfilenames${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}${ dataNode.data.nodeID}">

															</div>
														</div>

													</div>
													<hr>
												</li>

											</c:forEach>
										</ul>
									</c:otherwise>
								</c:choose>



								<p class="help-block">注意文件类型。</p>


							</div>
						</c:if>


					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" onclick="onAdd()">添加</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</form>

			</div>
		</div>
	</div>



	
	<%@ include file="../../shared/sysLastInclude.jsp"%>


	<script type="text/javascript">
		$("#collapseHomework").addClass("in");
		$("#homeworktype${selectedCourseHomeworkTypeData.id}").addClass(
				"active");
	</script>

	<script>
	

	$('.summernote').summernote({
		height : 300, // set editor height
		lang : 'zh-CN' // default: 'en-US'
	//  minHeight: null,             // set minimum height of editor
	//  maxHeight: null,             // set maximum height of editor

	//  focus: true,                 // set focus to editable area after initializing summernote
	});
</script>


	<script>
	
		var NodePropertyArray = [];
		
		/**
		添加节点
		 */
		function addNode(obj) {
			NodePropertyArray.push(obj);
		}

		function deleteNode(nodeID) {
			for (var i = 0; i < NodePropertyArray.length; i++) {
				if (NodePropertyArray[i].nodeID == nodeID) {
					NodePropertyArray.splice(i);
					return;
				}
			}
		}
	
		//取得合法文件名称示例
		function onGetExample(t_homework_baseinfo_id, nodeID) {

			var url = "<c:url value='/coursehomework/getRightExampleFileName-'/>"
					+ t_homework_baseinfo_id + "-" + nodeID + ".json";

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

		//取得合法文件名称示例
		function onDlgGetExample(t_homework_baseinfo_id, nodeID) {

			var url = "<c:url value='/coursehomework/getRightExampleFileName-'/>"
					+ t_homework_baseinfo_id + "-" + nodeID + ".json";

			$("#dlgexamplefilenames" + t_homework_baseinfo_id+nodeID).empty();

			$.get(url, function(data, status) {
				if (status == "success") {

					for (var i = 0; i < data.length; i++) {
						$("#dlgexamplefilenames" + t_homework_baseinfo_id+nodeID)
								.append(
										"<li  class='list-group-item'>"
												+ data[i] + "</li>");
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
			var url = "location='<c:url value="/coursehomeworksubmit/DELETE-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"/>"
					+ id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}

		function onUpdate(id, title) {
			
			var url = "<c:url value='/coursehomeworksubmit/getHomeworkById-"+id+".json'/>";
			$.get(url, function(data, status) {
				if (status == "success") {

					NodePropertyArray.length=0;//清空数组
					$('#addModal').find('.modal-body #filesProperty').val("");
					$('#addModal').find('.modal-body input .myfilecontainercls')
					$('#addModal').find('.modal-body input .myfilecontainercls').each(function(){
						$(this).val("");
					});
					$('#addModal').find('.modal-body .myfilecontainercls').each(function(){
						$(this).empty();
					});
					
				
					
				
					var url = "<c:url value="/coursehomeworksubmit/update-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"/>"
							+ id + ".html";

					
					$('#addModal').find('.modal-body input[name="title"]').val(title);
					
					
					var sHTML = $('.summernote').summernote('code',data.content);			
					
					

					$('#addModal').find("#myform").attr("action", url);
					$('#addModal').find(".modal-footer :submit").text("修改");

					$('#addModal').modal('show');

				}
			});
		
			
			

		}
		
		function onAdd(){
			
			var sHTML = $('.summernote').summernote('code');			
			$('#addModal').find('.modal-body #addcontentTextArea').text(sHTML);

			
		
			$('#addModal').find('.modal-body div .myfilecontainercls input[name="nodeID"]').each(
					function (){
						var s=$(this).val();
						var words = s.split(';')
						var obj = {
								"nodeID" : words[0],
								"filesCount" : words[1]					
							};
						deleteNode(words[0]);
						addNode(obj);
					}
					);
			
			
			
			
			$('#addModal').find('.modal-body #filesProperty').val(JSON.stringify(NodePropertyArray));
			
			
		}
		
		function onHomeworkSubmit(){
			NodePropertyArray.length=0;//清空数组
			$('#addModal').find('.modal-body #filesProperty').val("");
			$('#addModal').find('.modal-body input .myfilecontainercls')
			$('#addModal').find('.modal-body input .myfilecontainercls').each(function(){
				$(this).val("");
			});
			$('#addModal').find('.modal-body .myfilecontainercls').each(function(){
				$(this).empty();
			});
			
			var url = "<c:url value='/coursehomeworksubmit/add-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${selectedCourseHomeworkBasicInfoViewData.homeworkbaseinfo.id}.html'/>";
			
			$('#addModal').find(".modal-footer :submit").text("添加");
			$('#addModal').modal('show');
		}
		
		function insertTitle(e,homeworkbaseinfoid,nodeID){
			var files=document.getElementById("exampleInputFile"+ homeworkbaseinfoid+nodeID).files
			var filesCount=files.length;

			
				
			$("#dlguploadfilenames" + homeworkbaseinfoid+nodeID).empty();
			for(var i=0;i<filesCount;i++){
				var tValue=files[i].name;				
			  
				   $("#dlguploadfilenames" + homeworkbaseinfoid+nodeID).append(
							"<a  class='list-group-item'>" +"[" +i+"]"+tValue
							+"<input type='hidden' name='nodeID' value='"+nodeID+";"+filesCount+"'>"							
									+ "</a>");
			    
			}
			}
	</script>



</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "newmail");
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
<body>

	<%@ include file="../../shared/pageHeader.jsp"%>
	<c:if test="${selectedCourseTeachingClassID!=null}">
		
			<%@ include file="../../shared/CourseTeachingClassInfo.jsp"%>
		
		
	</c:if>

	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">

			<%
				//包含左侧工具菜单
			%>

			<c:choose>
				<c:when test="${selectedCourseTeachingClassID!=null}">
					<%@ include file="../CourseContentNav.jsp"%>
				</c:when>
				<c:otherwise>
					<%@ include file="../CourseNav.jsp"%>
				</c:otherwise>

			</c:choose>


		</div>

		<div class="DocumentPageRightArea">



			<ol class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li><a href="#">Library</a></li>
				<li class="active">Data</li>
			</ol>

			<div class="CourseContentHeader">
			
				<c:choose>
					<c:when test="${selectedMailBoxNewReceiver==null}">
						新邮件
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${selectedMailBoxNewReceiver.teacher!=null}">
								向<span class="text-info"><i class="fa fa-user"></i>${selectedMailBoxNewReceiver.userbasicinfo.userBasicInfoName}</span>发送新邮件
							</c:when>
							<c:when test="${selectedMailBoxNewReceiver.student!=null}">
								向<span class="text-info"><i class="fa fa-user"></i>${selectedMailBoxNewReceiver.student.studentNum}-${selectedMailBoxNewReceiver.userbasicinfo.userBasicInfoName}</span>发送新邮件
							</c:when>
							<c:otherwise>
								向<span class="text-info"><i class="fa fa-user"></i>${selectedMailBoxNewReceiver.user.userName}</span>发送新邮件
							</c:otherwise>
						</c:choose>
					
					</c:otherwise>
				</c:choose>
				
			</div>

			<div class="CourseContentHeaderSeparatorLine"></div>





			<div class="Course-Table">


				<form
					<c:choose>
						<c:when test="${selectedCourseTeachingClassID!=null}">
							action="<c:url value="/mailbox/addwithcourse-${selectedCourseTeachingClassID}.html"/>"
						</c:when>
						<c:otherwise>
							action="<c:url value="/mailbox/add.html"/>"
						</c:otherwise>
					</c:choose>
					enctype="multipart/form-data" method="post">

					<div class="modal-body">
						<input type="text" id="receivers" class="form-control"
									name="receivers" value="${selectedMailBoxNewReceiver.user.id}" style="display: none;">
						<c:if test="${selectedMailBoxNewReceiver==null}">
							<div class="form-group">
								<label for="name" class="control-label">收件人</label>
								<button type="button" class="btn btn-default btn-sm"
									data-toggle="modal" data-target="#addModal">增加</button>
								

								<textarea rows="1" id="receiversdetails" class="form-control"
									name="receiversdetails" readonly></textarea>

							</div>
						</c:if>

						<div class="form-group">
							<label for="name" class="control-label">标题</label> <input
								type="text" id="name" class="form-control" name="title" value=""
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
						<button type="submit" class="btn btn-primary" onclick="onAdd()">发送</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</form>




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
					<h4 class="modal-title" id="myModalLabel">添加收件人</h4>
				</div>



				<div class="modal-body">

					<h4 class="modal-title">教师</h4>


					<div class="container-fluid">

						<c:set var="index" value="0"></c:set>
						<c:forEach var="dataitem" items="${pagedTeacherViewData.result}">
							<c:if test="${index%4==0}">
								<div class="row show-grid">
							</c:if>

							<div class="col-md-3">
								<input type="checkbox" name="receiverUser"
									value="${dataitem.user.id}" />${dataitem.userbasicinfo.userBasicInfoName}
								<input type="text" class="form-control" style="display: none;"
									id="user${dataitem.user.id}"
									value="${dataitem.userbasicinfo.userBasicInfoName}" />
							</div>

							<c:if test="${index%4==3}">
					</div>
					</c:if>


					<c:set var="index" value="${index + 1}"></c:set>
					</c:forEach>

					<c:if test="${index%4!=0}">
				</div>
				</c:if>
			</div>




			<div class="gridseparator"></div>

			<h4 class="modal-title">学生</h4>
			<button type="button" class="btn btn-default btn-xs"
				onclick="onSelectAllStudent()">全选</button>
			<button type="button" class="btn btn-default btn-xs"
				onclick="onDeleteSelectAllStudent()">全部不选</button>
			<button type="button" class="btn btn-default btn-xs"
				onclick="onAntiSelectAllStudent()">反选</button>
			<div class="container-fluid">

				<c:set var="index" value="0"></c:set>
				<c:forEach var="dataitem" items="${pagedStudentViewData.result}">
					<c:if test="${index%4==0}">
						<div class="row show-grid">
					</c:if>

					<div class="col-md-3">
						<input type="checkbox" name="receiverStudent"
							value="${dataitem.user.id}" />${dataitem.student.studentNum}-${dataitem.userbasicinfo.userBasicInfoName}
						<input type="text" class="form-control" style="display: none;"
							id="user${dataitem.user.id}"
							value="${dataitem.student.studentNum}-${dataitem.userbasicinfo.userBasicInfoName}" />
					</div>

					<c:if test="${index%4==3}">
			</div>
			</c:if>


			<c:set var="index" value="${index + 1}"></c:set>
			</c:forEach>

			<c:if test="${index%4!=0}">
		</div>
		</c:if>
	</div>

	</div>

	<div class="modal-footer">
		<button type="submit" class="btn btn-primary"
			onclick="onAddReceiver()">添加</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	</div>


	</div>
	</div>
	</div>




	<%@ include file="../../shared/sysLastInclude.jsp"%>




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
			var receivers = $('#receivers').val();
			if (receivers == "") {
				ShowInfoMsg("提示", "请选择收件人");
				return;
			}
			
			var sHTML = $('.summernote').summernote('code');
			$('#addcontentTextArea').text(sHTML);

		}

		function addreceivers() {

			$('#addModal').modal('show');

		}

		function onAddReceiver() {
			$('#receivers').val("");
			$('#receiversdetails').text("");

			$("input[name='receiverUser']:checked")
					.each(
							function() {

								if ($('#receivers').val() != "") {
									$('#receiversdetails')
											.text(
													$('#receiversdetails')
															.text()
															+ ";"
															+ $(
																	"#user"
																			+ $(
																					this)
																					.val())
																	.val());
									$('#receivers').val(
											$('#receivers').val() + ";"
													+ $(this).val());
								} else {

									$('#receiversdetails').text(
											$("#user" + $(this).val()).val());
									$('#receivers').val($(this).val());
								}

							});
			
			
			$("input[name='receiverStudent']:checked")
			.each(
					function() {

						if ($('#receivers').val() != "") {
							$('#receiversdetails')
									.text(
											$('#receiversdetails')
													.text()
													+ ";"
													+ $(
															"#user"
																	+ $(
																			this)
																			.val())
															.val());
							$('#receivers').val(
									$('#receivers').val() + ";"
											+ $(this).val());
						} else {

							$('#receiversdetails').text(
									$("#user" + $(this).val()).val());
							$('#receivers').val($(this).val());
						}

					});
			
			$('#addModal').modal('hide');

		}
	<%//选择全部学生%>
		function onSelectAllStudent() {
			$("input[name='receiverStudent']").each(function() {
				$(this).prop("checked", true);
			});
		}
	<%//全部学生不选择%>
		function onDeleteSelectAllStudent() {
			$("input[name='receiverStudent']").each(function() {
				$(this).prop("checked", false);
			});
		}
	<%//反选学生%>
		function onAntiSelectAllStudent() {
			$("input[name='receiverStudent']").each(function() {
				$(this).prop("checked", !this.checked);
			});
		}
	</script>



</body>
</html>

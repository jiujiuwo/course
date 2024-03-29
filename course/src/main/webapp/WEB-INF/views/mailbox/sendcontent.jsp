<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "sendmail");
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

			<div class="CourseContentHeader">${selectedMailBoxSendViewData.send.subject}</div>

			<div class="CourseContentHeaderSeparatorLine"></div>

			<table>
				<tr>
					<td><div class="btn-group" role="group" aria-label="...">
							<c:choose>
								<c:when test="${selectedCourseTeachingClassID==null}">
									<button type="button" class="btn btn-default btn-sm"
										onclick="location='<c:url value="/mailbox/newMailToOtherWithoutCourse-${selectedMailBoxSendViewData.userFrom.user.id}.html"/>'">回信</button>

								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-default btn-sm"
										onclick="location='<c:url value="/mailbox/newmailtoother-${selectedCourseTeachingClassID}-${selectedMailBoxSendViewData.userFrom.user.id}.html"/>'">回信</button>

								</c:otherwise>
							</c:choose>

							<button type="button" class="btn btn-default btn-sm"
								onclick="onDelete('${selectedMailBoxSendViewData.send.id}','${selectedMailBoxSendViewData.send.subject}')">删除</button>


						</div></td>

				</tr>
			</table>


			<div class="Course-Table">

				<div class="container-fluid bg-info" style="overflow: hidden;">

					<div class="row show-grid">
						<div class="col-md-1">
							<strong>发件人</strong>
						</div>
						<div class="col-md-10">
							<c:if test="${selectedMailBoxSendViewData.userTo.student!=null}">
							${selectedMailBoxSendViewData.userTo.student.studentNum}-
							</c:if>
							${selectedMailBoxSendViewData.userTo.userbasicinfo.userBasicInfoName}
						</div>

					</div>

					<div class="row show-grid">
						<div class="col-md-1">
							<strong>收件人</strong>
						</div>
						<div class="col-md-10">
							<c:if
								test="${selectedMailBoxSendViewData.userFrom.student!=null}">
							${selectedMailBoxSendViewData.userFrom.student.studentNum}-
							</c:if>
							${selectedMailBoxSendViewData.userFrom.userbasicinfo.userBasicInfoName}
						</div>

					</div>

					<div class="row show-grid">
						<div class="col-md-1">
							<strong>时间</strong>
						</div>
						<div class="col-md-10">
							<fmt:formatDate
								value="${selectedMailBoxSendViewData.send.senddate}"
								pattern="yyyy-MM-dd HH:mm:ss" />

						</div>
					</div>


					<div class="row show-grid">
						<div class="col-md-1">
							<strong>附件</strong>
						</div>
						<div class="col-md-10">
							<c:choose>

								<c:when
									test="${fn:length(selectedMailBoxSendViewData.sendfile)==1}">
									<a
										href="<c:url value="/mailboxfile/downloadreceived-${selectedMailBoxSendViewData.sendfile[0].id}.html"/>">
										${selectedMailBoxSendViewData.sendfile[0].filename}</a>
								</c:when>

								<c:otherwise>
									<ul>
										<c:forEach var="datafile"
											items="${selectedMailBoxSendViewData.sendfile}">
											<li><a
												href="<c:url value="/mailboxfile/downloadreceived-${datafile.id}.html"/>">
													${datafile.filename}</a></li>
										</c:forEach>
									</ul>
								</c:otherwise>
							</c:choose>


						</div>
					</div>


				</div>

				<div class="gridseparator"></div>

				<div class="container-fluid" style="overflow: hidden;">
					<div class="row show-grid">
						<div class="col-md-12">
							${selectedMailBoxSendViewData.send.content}</div>


					</div>
				</div>


				<div class="gridseparator"></div>




			</div>
		</div>

	</div>



	<%@ include file="../../shared/sysLastInclude.jsp"%>

	<script>
		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onDelete(id,name) {
			
			<c:choose>
			<c:when test="${selectedCourseTeachingClassID!=null}">
			var url = "location='<c:url value="/mailbox/deletesendwithcourse-${selectedCourseTeachingClassID}-"/>"
				+ id + ".html'";
			</c:when>
			<c:otherwise>
			var url = "location='<c:url value="/mailbox/deletesend-"/>"
				+ id + ".html'";
			</c:otherwise>
		</c:choose>
			
			

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}
	</script>

</body>
</html>

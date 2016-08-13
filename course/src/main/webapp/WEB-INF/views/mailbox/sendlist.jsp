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

			<div class="CourseContentHeader">发件箱</div>

			<div class="CourseContentHeaderSeparatorLine"></div>



			<table>
				<tr>
					<td><div class="btn-group" role="group" aria-label="...">
							<!-- Split button -->
							<div class="btn-group">
								<button type="button" class="btn btn-default btn-sm">选择</button>
								<button type="button"
									class="btn btn-default dropdown-toggle btn-sm"
									data-toggle="dropdown" aria-expanded="false">
									<span class="caret"></span> <span class="sr-only">Toggle
										Dropdown</span>
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

							<button type="button" class="btn btn-default btn-sm"
								data-toggle="modal" data-target="#myModal">删除</button>


						</div></td>
					<td style="width: 10px;"></td>
					<td><div class="input-group input-group-sm">
							<input type="text" class="form-control" id="SearchText"
								placeholder="Search for..." required> <span
								class="input-group-btn">
								<button class="btn btn-default" type="button"
									onclick="onSearch()">搜索</button>
							</span>
						</div></td>
				</tr>
			</table>






			<div class="Course-Table">

				<div class="container-fluid" style="overflow: hidden;">
					<div class="gridseparator"></div>
					<div class="row show-grid">
						<div class="col-md-1">
							<strong>#</strong>
						</div>
						<div class="col-md-2">
							<strong>收件人</strong>
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
					<c:forEach var="data" items="${pagedMailBoxSendViewData.result}">
						<div class="row show-grid">
							<div class="col-md-1">

								${pagedMailBoxSendViewData.totalCount-(pagedMailBoxSendViewData.currentPageNo-1) * pagedMailBoxSendViewData.pageSize -index}
							</div>

							<div class="col-md-2">
								<c:if test="${data.userTo.student!=null}">
							${data.userTo.student.studentNum}-
							</c:if>
								${data.userTo.userbasicinfo.userBasicInfoName}
							</div>

							<div class="col-md-4">
								<c:choose>
									<c:when test="${selectedCourseTeachingClassID!=null}">
										<a
											href="<c:url value="/mailbox/sendcontentwithcourse-${selectedCourseTeachingClassID}-${data.send.id}.html"/>"><span class="text-success">${data.send.subject}</span></a>
										

									</c:when>
									<c:otherwise>
										<a
											href="<c:url value="/mailbox/sendcontent-${data.send.id}.html"/>"><span class="text-success">${data.send.subject}</span></a>
									</c:otherwise>
								</c:choose>


							</div>
							<div class="col-md-2">
								<fmt:formatDate value="${data.send.senddate}"
									pattern="yyyy-MM-dd HH:mm:ss" />

							</div>


							<div class="col-md-1">


								<button type="button" class="btn btn-default btn-xs"
									onclick="onDelete('${data.send.id}','${data.send.subject}')">删除</button>

							</div>
						</div>
						<c:set var="index" value="${index + 1}"></c:set>
					</c:forEach>

					<c:if test="${pagedMailBoxSendViewData.totalCount>0}">
						<div class="gridseparator"></div>
					</c:if>

				</div>




				<c:choose>
					<c:when test="${not empty selectedCourseTeachingClassID}">
						<mathtop:PageBar
							pageUrl="/mailbox/sendlistwithcourse-${selectedCourseTeachingClassID}.html"
							pageAttrKey="pagedMailBoxSendViewData" />
					</c:when>
					<c:otherwise>
						<mathtop:PageBar
							pageUrl="/mailbox/sendlist.html"
							pageAttrKey="pagedMailBoxSendViewData" />
					</c:otherwise>
				</c:choose>

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

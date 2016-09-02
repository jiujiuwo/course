<%@ page contentType="text/html; charset=UTF-8"%>


<%!String coursenav(String s, String n) {

		if (s != null && s.equals(n))
			return " active";
		return "";
	}

	String iscourseCollapse(String s, String list) {
		String[] t = list.split(",");

		for (String a : t) {
			if (a.equals(s))
				return " in";
		}

		return "";
	}%>

<style>
.panel-body {
	padding-left: 0px;
	padding-right: 0px;
	padding-top: 0px;
	padding-bottom: 0px;
}

.list-group {
	margin: 0px;
}

.list-group-item {
	border: none;
	padding-left: 2em;
}
</style>


<div class="NavPanel-Group" id="accordion">


	<div class="NavPanel">

		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">



			<c:if
				test="${(sessionScope.USER_CONTEXT.student!=null and pagedReferenceTypeData.totalCount>0) or sessionScope.USER_CONTEXT.teacher!=null}">

				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingReference">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse" data-parent="#accordion"
								href="#collapseReference" aria-expanded="false" aria-controls="collapseReference"> 课程资料</a>
						</h4>
					</div>
					<div id="collapseReference"
						class="panel-collapse collapse <%=iscourseCollapse((String) session.getAttribute("nav"), "coursereferencetype")%>"
						role="tabpanel" aria-labelledby="headingReference">
						<div class="panel-body">
							<div class="list-group">

								<%
									//只有具有作业管理权限的人（一般是该课授课教师）才能够管理
								%>
								<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">
									<a href="coursereferencetype/list-${selectedCourseTeachingClassID}.html"
										class="list-group-item <%=coursenav((String) session.getAttribute("nav"),
					"coursereferencetype")%>">资料类型管理</a>
								</c:if>


								<c:forEach var="data" items="${pagedReferenceTypeData.result}">


									<a id="referencetype${data.id}"
										href="coursereference/list-${selectedCourseTeachingClassID}-${data.id}.html"
										class="list-group-item ">${data.name}</a>

								</c:forEach>


							</div>
						</div>
					</div>
				</div>
			</c:if>


			<c:if
				test="${(sessionScope.USER_CONTEXT.student!=null and pagedHomeworkTypeData.totalCount>0) or sessionScope.USER_CONTEXT.teacher!=null}">

				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingHomework">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseHomework"
								aria-expanded="false" aria-controls="collapseHomework"> 课程作业</a>
						</h4>
					</div>
					<div id="collapseHomework"
						class="panel-collapse collapse <%=iscourseCollapse((String) session.getAttribute("nav"), "coursehomeworktype")%>"
						role="tabpanel" aria-labelledby="headingHomework">
						<div class="panel-body">
							<div class="list-group">

								<%
									//只有具有作业管理权限的人（一般是该课授课教师）才能够管理
								%>
								<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">
									<a href="coursehomeworktype/list-${selectedCourseTeachingClassID}.html"
										class="list-group-item <%=coursenav((String) session.getAttribute("nav"),
					"coursehomeworktype")%>">作业类型管理</a>
								</c:if>


								<c:forEach var="data" items="${pagedHomeworkTypeData.result}">


									<a id="homeworktype${data.id}"
										href="coursehomework/list-${selectedCourseTeachingClassID}-${data.id}.html"
										class="list-group-item ">${data.name}</a>

								</c:forEach>


							</div>
						</div>
					</div>
				</div>
			</c:if>


			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingDiscuss">
					<h4 class="panel-title">
						<span class="glyphicon glyphicon-globe" aria-hidden="true"></span>
						<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseDiscuss"
							aria-expanded="false" aria-controls="collapseDiscuss"> 课程讨论</a>
					</h4>
				</div>
				<div id="collapseDiscuss"
					class="panel-collapse collapse <%=iscourseCollapse((String) session.getAttribute("nav"), "coursefaq,courseforum")%>"
					role="tabpanel" aria-labelledby="headingDiscuss">
					<div class="panel-body">
						<div class="list-group">


							<a href="courseforum/list-${selectedCourseTeachingClassID}.html"
								class="list-group-item <%=coursenav((String) session.getAttribute("nav"),
					"courseforum")%>">课程论坛</a>

						</div>
					</div>
				</div>
			</div>

			<c:if
				test="${(sessionScope.USER_CONTEXT.student!=null and pagedAttendanceTypeData.totalCount>0) or sessionScope.USER_CONTEXT.teacher!=null}">

				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingAtendance">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse" data-parent="#accordion"
								href="#collapseAtendance" aria-expanded="false" aria-controls="collapseAtendance"> 课程考勤</a>
						</h4>
					</div>
					<div id="collapseAtendance"
						class="panel-collapse collapse <%=iscourseCollapse((String) session.getAttribute("nav"),
						"courseattendancetype,coursestudentlist")%>"
						role="tabpanel" aria-labelledby="headingAtendance">
						<div class="panel-body">
							<div class="list-group">


								<%
									//只有具有考勤管理权限的人（一般是该课授课教师）才能够管理
								%>
								<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">
									<a href="courseattendance/student-${selectedCourseTeachingClassID}.html"
										class="list-group-item <%=coursenav((String) session.getAttribute("nav"),
					"coursestudentlist")%>">花名册</a>
									<a href="courseattendancetype/list-${selectedCourseTeachingClassID}.html"
										class="list-group-item <%=coursenav((String) session.getAttribute("nav"),
					"courseattendancetype")%>">考勤类型管理</a>
								</c:if>


								<c:forEach var="data" items="${pagedAttendanceTypeData.result}">


									<a id="attendancetype${data.id}"
										href="/courseattendance/list-${selectedCourseTeachingClassID}-${data.id}.html"
										class="list-group-item ">${data.name}</a>

								</c:forEach>


							</div>
						</div>
					</div>
				</div>
			</c:if>

			<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingStudentGroup">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse" data-parent="#accordion"
								href="#collapseStudentGroup" aria-expanded="false" aria-controls="collapseStudentGroup">
								课程分组</a>
						</h4>
					</div>
					<div id="collapseStudentGroup"
						class="panel-collapse collapse <%=iscourseCollapse((String) session.getAttribute("nav"), "studentgroup")%>"
						role="tabpanel" aria-labelledby="headingStudentGroup">
						<div class="panel-body">
							<div class="list-group">

								<a href="coursegroup/list-${selectedCourseTeachingClassID}.html"
									class="list-group-item <%=coursenav((String) session.getAttribute("nav"), "studentgroup")%>">分组管理</a>

							</div>
						</div>
					</div>
				</div>
			</c:if>
			
			<c:if test="${sessionScope.USER_CONTEXT.student!=null and selectedCourseTeachingClassStudentGroupStatisticsViewData.nGroupCount>0 }">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingStudentGroup">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse" data-parent="#accordion"
								href="#collapseStudentGroup" aria-expanded="false" aria-controls="collapseStudentGroup">
								课程小组</a>
						</h4>
					</div>
					<div id="collapseStudentGroup"
						class="panel-collapse collapse <%=iscourseCollapse((String) session.getAttribute("nav"), "studentgroup")%>"
						role="tabpanel" aria-labelledby="headingStudentGroup">
						<div class="panel-body">
							<div class="list-group">

								<a href="coursegroup/studentlist-${selectedCourseTeachingClassID}.html"
									class="list-group-item <%=coursenav((String) session.getAttribute("nav"), "studentgroup")%>">小组信息</a>

							</div>
						</div>
					</div>
				</div>
			</c:if>

			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingMailBox">
					<h4 class="panel-title">
						<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
						<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseMailBox"
							aria-expanded="false" aria-controls="collapseMailBox"> 站内信箱</a>
					</h4>
				</div>
				<div id="collapseMailBox"
					class="panel-collapse collapse <%=iscourseCollapse((String) session.getAttribute("nav"), "sendmail,receivedmail,newmail")%>"
					role="tabpanel" aria-labelledby="headingMailBox">
					<div class="panel-body">
						<div class="list-group">
							<a href="mailbox/receivedlistwithcourse-${selectedCourseTeachingClassID}.html"
								class="list-group-item <%=coursenav((String) session.getAttribute("nav"), "receivedmail")%>">收件箱</a>
							<a href="mailbox/sendlistwithcourse-${selectedCourseTeachingClassID}.html"
								class="list-group-item <%=coursenav((String) session.getAttribute("nav"), "sendmail")%>">发件箱</a>
							<a href="mailbox/newmailwithcourse-${selectedCourseTeachingClassID}.html"
								class="list-group-item <%=coursenav((String) session.getAttribute("nav"), "newmail")%>">写信</a>
						</div>
					</div>
				</div>
			</div>



		</div>
	</div>

</div>


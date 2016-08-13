<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<%!String nav(String s, String n) {

		if (s != null && s.equals(n))
			return " active";
		return "";
	}

	String isCollapse(String s, String list) {
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





<c:set var="flag" value=""></c:set>
<c:forEach var="dataitem"
	items="${sessionScope.USER_CONTEXT.permissionviewdata}">
	<c:set var="flag" value="${flag},${dataitem.resource.uri}" />
</c:forEach>

<div class="NavPanel-Group" id="accordion">


	<div class="NavPanel">

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">


			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingAccount">
					<h4 class="panel-title">
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						<a class="collapsed" data-toggle="collapse"
							data-parent="#accordion" href="#collapseAccount"
							aria-expanded="false" aria-controls="collapseAccount"> 个人账户 </a>
					</h4>
				</div>
				<div id="collapseAccount"
					class="panel-collapse collapse <%=isCollapse((String) session.getAttribute("nav"), "info,pwd")%>"
					role="tabpanel" aria-labelledby="headingAccount">
					<div class="panel-body">
						<div class="list-group">

							<c:if
								test="${sessionScope.USER_CONTEXT.teacher!=null or sessionScope.USER_CONTEXT.student!=null}">
								<a
									href=<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">
											"<c:url value='/teacher/info-${sessionScope.USER_CONTEXT.user.id}.html'/>"
											</c:if>
									<c:if test="${sessionScope.USER_CONTEXT.student!=null}">
											"<c:url value='/student/info-${sessionScope.USER_CONTEXT.user.id}.html'/>"
											</c:if>
									class="list-group-item <%=nav((String) session.getAttribute("nav"),
						"info")%>"><i
									class="fa fa-user fa-fw"></i>个人信息</a>
							</c:if>
							<a href="account/pwd.html"
								class="list-group-item <%=nav((String) session.getAttribute("nav"), "pwd")%>"><i
								class="fa fa-key fa-fw"></i>账户安全</a>
						</div>
					</div>
				</div>
			</div>




			<c:if
				test="${fn:contains(flag,'school') or fn:contains(flag,'department') or fn:contains(flag,'naturalclass')}">

				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" aria-expanded="true"
								aria-controls="collapseOne"> 院系管理</a>
						</h4>
					</div>
					<div id="collapseOne"
						class="panel-collapse collapse <%=isCollapse((String) session.getAttribute("nav"), "school,department,naturalclass")%>"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="list-group">

								<c:if
									test="${fn:contains(flag,'school') or fn:contains(flag,'department') or fn:contains(flag,'naturalclass')}">

									<a
										class="list-group-item <%=nav((String) session.getAttribute("nav"), "school")%>"
										href="school/list.html">学院管理</a>
								</c:if>

								<c:if
									test="${fn:contains(flag,'school') or fn:contains(flag,'department') or fn:contains(flag,'naturalclass')}">

									<a
										class="list-group-item <%=nav((String) session.getAttribute("nav"), "department")%>"
										href="department/list.html">系部管理</a>
								</c:if>

								<c:if
									test="${fn:contains(flag,'school') or fn:contains(flag,'department') or fn:contains(flag,'naturalclass')}">
									<a
										class="list-group-item <%=nav((String) session.getAttribute("nav"), "naturalclass")%>"
										href="naturalclass/list.html">自然班管理</a>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</c:if>

			<c:if
				test="${fn:contains(flag,'attendancestate') or fn:contains(flag,'attendancemode') }">


				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingAttendance">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseAttendance"
								aria-expanded="false" aria-controls="collapseAttendance">
								考勤管理 </a>
						</h4>
					</div>
					<div id="collapseAttendance"
						class="panel-collapse collapse <%=isCollapse((String) session.getAttribute("nav"), "attendancestate,attendancemode")%>"
						role="tabpanel" aria-labelledby="headingAttendance">
						<div class="panel-body">
							<div class="list-group">
								<a href="attendancestate/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "attendancestate")%>">考勤状况管理</a>
								<a href="attendancemode/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "attendancemode")%>">签到方式管理</a>

							</div>
						</div>
					</div>
				</div>
			</c:if>


			<c:if
				test="${fn:contains(flag,'coursetype') or fn:contains(flag,'coursestyle') or fn:contains(flag,'teachingtype')or fn:contains(flag,'course')or fn:contains(flag,'teachingclass')}">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingCourse">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseCourse"
								aria-expanded="false" aria-controls="collapseCourse"> 课程管理 </a>
						</h4>
					</div>
					<div id="collapseCourse"
						class="panel-collapse collapse <%=isCollapse((String) session.getAttribute("nav"), "coursetype,coursestyle,teachingtype,course,teachingclass,teachingterm")%>"
						role="tabpanel" aria-labelledby="headingCourse">
						<div class="panel-body">
							<div class="list-group">
								<a href="coursetype/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "coursetype")%>">课程性质</a>
								<a href="coursestyle/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "coursestyle")%>">课程类别</a>


								<a href="teachingtype/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "teachingtype")%>">教师授课类型</a>

								<a href="course/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "course")%>">课程</a>

								<a href="teachingclass/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "teachingclass")%>">教学班</a>
									<a href="courseteachingterm/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "teachingterm")%>">教学学期</a>
							</div>
						</div>
					</div>
				</div>
			</c:if>


			<c:if
				test="${fn:contains(flag,'teacher') or fn:contains(flag,'student') or fn:contains(flag,'contacttype')}">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingPeople">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapsePeople"
								aria-expanded="false" aria-controls="collapsePeople"> 人员管理</a>
						</h4>
					</div>
					<div id="collapsePeople"
						class="panel-collapse collapse <%=isCollapse((String) session.getAttribute("nav"), "teacher,student,contacttype")%>"
						role="tabpanel" aria-labelledby="headingPeople">
						<div class="panel-body">
							<div class="list-group">
								<a href="teacher/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "teacher")%>">教师管理</a>
								<a href="student/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "student")%>">学生管理</a>

								<a href="contacttype/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "contacttype")%>">联系类型管理</a>


							</div>
						</div>
					</div>
				</div>

			</c:if>


			<c:if test="${fn:contains(flag,'permission') }">
			
				
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingSystemManage">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseSystemManage"
								aria-expanded="false" aria-controls="collapseSystemManage">
								系统管理</a>
						</h4>
					</div>
					<div id="collapseSystemManage"
						class="panel-collapse collapse <%=isCollapse((String) session.getAttribute("nav"), "permission,systemResetMail")%>"
						role="tabpanel" aria-labelledby="headingSystemManage">
						<div class="panel-body">
							<div class="list-group">
								<a href="permission/group/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "systemResetMail")%>">重置密码邮箱</a>



							</div>
							<div class="list-group">
								<a href="permission/group/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "permission")%>">权限管理</a>



							</div>
						</div>
					</div>
				</div>
			</c:if>


			<c:if
				test="${fn:contains(flag,'database') or fn:contains(flag,'databaseloggingtype') or fn:contains(flag,'itemkinds')or fn:contains(flag,'itemtype')or fn:contains(flag,'itemdifficulty')}">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingDatabase">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-book" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseDatabase"
								aria-expanded="false" aria-controls="collapseDatabase"> 题库管理</a>
						</h4>
					</div>
					<div id="collapseDatabase"
						class="panel-collapse collapse <%=isCollapse((String) session.getAttribute("nav"), "database,databaseloggingtype,itemkinds,itemtype,itemdifficulty")%>"
						role="tabpanel" aria-labelledby="headingDatabase">
						<div class="panel-body">
							<div class="list-group">
								<a href="database/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "database")%>">题库管理</a>


								<a href="databaseloggingtype/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "databaseloggingtype")%>">题库日志类型管理</a>

								<a href="itemkinds/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "itemkinds")%>">作业类型管理</a>


								<a href="itemtype/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "itemtype")%>">题目类型管理</a>


								<a href="itemdifficulty/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "itemdifficulty")%>">题目难度管理</a>



							</div>
						</div>
					</div>
				</div>
			</c:if>

			<c:if
				test="${fn:contains(flag,'examinationtype')  or fn:contains(flag,'examination')}">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingExamination">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseExamination"
								aria-expanded="false" aria-controls="collapseExamination">
								考试管理</a>
						</h4>
					</div>
					<div id="collapseExamination"
						class="panel-collapse collapse <%=isCollapse((String) session.getAttribute("nav"), "examinationtype,examination")%>"
						role="tabpanel" aria-labelledby="headingExamination">
						<div class="panel-body">
							<div class="list-group">
								<a href="examinationtype/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "examinationtype")%>">考试类型管理</a>

								<a href="examination/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "examination")%>">考试管理</a>


							</div>
						</div>
					</div>
				</div>
			</c:if>

			<c:if test="${fn:contains(flag,'score')}">

				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingScore">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-certificate" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseScore"
								aria-expanded="false" aria-controls="collapseScore"> 成绩管理</a>
						</h4>
					</div>
					<div id="collapseScore"
						class="panel-collapse collapse <%=isCollapse((String) session.getAttribute("nav"), "score")%>"
						role="tabpanel" aria-labelledby="headingScore">
						<div class="panel-body">
							<div class="list-group">
								<a href="score/list.html"
									class="list-group-item <%=nav((String) session.getAttribute("nav"), "score")%>">成绩管理</a>



							</div>
						</div>
					</div>
				</div>

			</c:if>


			<c:if
				test="${sessionScope.USER_CONTEXT.teachingclassviewdataSize>0}">

				<div class="panel panel-default">
					<div class="panel-heading" role="tab"
						id="headingCourseTeachingClass">
						<h4 class="panel-title">
							<span class="glyphicon glyphicon-education" aria-hidden="true"></span>
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseCourseTeachingClass"
								aria-expanded="false"
								aria-controls="collapseCourseTeachingClass"> 课程列表</a>
						</h4>
					</div>
					<div id="collapseCourseTeachingClass"
						class="panel-collapse collapse <%=isCollapse((String) session.getAttribute("nav"), "CourseTeachingClass")%>"
						role="tabpanel" aria-labelledby="headingCourseTeachingClass">
						<div class="panel-body">
							<div class="list-group">

								<c:forEach var="dataitem"
									items="${sessionScope.USER_CONTEXT.teachingclassviewdata}">
									<a
										class="list-group-item <%=nav((String) session.getAttribute("nav"), "CourseTeachingClass")%>"
										href="<c:url value="/coursecontent/index-${dataitem.courseTeachingClass.id}.html"/>">${dataitem.course.name }</a>
								</c:forEach>





							</div>
						</div>
					</div>
				</div>
			</c:if>


			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingMailBox">
					<h4 class="panel-title">
						<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
						<a class="collapsed" data-toggle="collapse"
							data-parent="#accordion" href="#collapseMailBox"
							aria-expanded="false" aria-controls="collapseMailBox"> 站内信箱</a>
					</h4>
				</div>
				<div id="collapseMailBox"
					class="panel-collapse collapse <%=isCollapse((String) session.getAttribute("nav"), "sendmail,receivedmail,newmail")%>"
					role="tabpanel" aria-labelledby="headingMailBox">
					<div class="panel-body">
						<div class="list-group">
							<a href="mailbox/receivedlist.html"
								class="list-group-item <%=nav((String) session.getAttribute("nav"), "receivedmail")%>">收件箱</a>
							<a href="mailbox/sendlist.html"
								class="list-group-item <%=nav((String) session.getAttribute("nav"), "sendmail")%>">发件箱</a>
							<a href="mailbox/newmail.html"
								class="list-group-item <%=nav((String) session.getAttribute("nav"), "newmail")%>">写信</a>
						</div>
					</div>
				</div>
			</div>



		</div>
	</div>
</div>



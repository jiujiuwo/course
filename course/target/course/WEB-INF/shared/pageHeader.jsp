<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="PageTopBar">


	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">

			<div class="navbar-header">
				<h3 class="navbar-text" style="margin-bottom: 0px;">
					<a href='<c:url value="/account/doLogin.html"/>'>课程系统</a>
				</h3>

			</div>



			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


				<ul class="nav navbar-nav navbar-right">


					<li><a
							href=<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">
											"<c:url value="/teacher/info-${sessionScope.USER_CONTEXT.user.id}.html"/>"
											</c:if>
							<c:if test="${sessionScope.USER_CONTEXT.student!=null}">
											"<c:url value="/student/info-${sessionScope.USER_CONTEXT.user.id}.html"/>"
											</c:if>>

							${sessionScope.USER_CONTEXT.user.userName}
							<c:if test="${sessionScope.USER_CONTEXT.userbasicinfo.userBasicInfoName!=null}">
							(${sessionScope.USER_CONTEXT.userbasicinfo.userBasicInfoName})
							</c:if>
						</a></li>

					<c:if test="${sessionScope.USER_CONTEXT.teachingclassviewdataSize>0}">
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
								role="button" aria-expanded="false">
								课程 <span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<c:forEach var="dataitem" items="${sessionScope.USER_CONTEXT.teachingclassviewdata}">
									<li><a
											href="<c:url value="/coursecontent/index-${dataitem.courseTeachingClass.id}.html"/>">${dataitem.course.name }(${dataitem.term.teachingYearBegin }-${dataitem.term.teachingYearEnd }-${dataitem.term.teachingTerm })</a></li>
								</c:forEach>
							</ul></li>
					</c:if>



					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-expanded="false">
							设置 <span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu">

							<li><a
									href=<c:if test="${sessionScope.USER_CONTEXT.teacher!=null}">
											"<c:url value="/teacher/info-${sessionScope.USER_CONTEXT.user.id}.html"/>"
											</c:if>
									<c:if test="${sessionScope.USER_CONTEXT.student!=null}">
											"<c:url value="/student/info-${sessionScope.USER_CONTEXT.user.id}.html"/>"
											</c:if>>

									<i class="fa fa-user fa-fw"></i>${sessionScope.USER_CONTEXT.user.userName}<c:if
										test="${sessionScope.USER_CONTEXT.userbasicinfo.userBasicInfoName!=null}">
							(${sessionScope.USER_CONTEXT.userbasicinfo.userBasicInfoName})
							</c:if>
									个人信息
								</a></li>

							<li><a href="account/pwd.html">
									<i class="fa fa-key fa-fw"></i>账户安全
								</a></li>



						</ul></li>

<!-- 频繁访问时不能退出  -->
					<c:if test="${sessionScope.AccessCount!=-1}">
						<li><a href="<c:url value="/account/doLogout.html"/>">退出</a></li>
					</c:if>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>



</div>




<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container-fluid">
	<div class="row">



		<div class="col-md-3">

			<p class="text-muted">


				<strong>课程名称：</strong>${selectedCourseTeachingClassViewData.course.name}
				
			</p>


		</div>

		<div class="col-md-2">
			<p class="text-muted">
				<strong>授课教师：</strong>

				<c:forEach var="t"
					items="${selectedCourseTeachingClassViewData.teacher}"
					varStatus="status">
							${t.userbasicinfo.userBasicInfoName}(${selectedCourseTeachingClassViewData.teachingtype[status.index].name})
							</c:forEach>
			</p>


		</div>

		<div class="col-md-2">
			<p class="text-muted">
				<strong>授课学期：</strong>${selectedCourseTeachingClassViewData.term.term}
			</p>

		</div>
	</div>
</div>


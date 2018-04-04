<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

<link href="<c:url value='/css/pages/index.css'/>" rel="stylesheet" type="text/css" />
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
<body style="overflow: scroll;">

	<%@ include file="../../shared/pageHeader.jsp"%>
	<%@ include file="../../shared/CourseTeachingClassInfo.jsp"%>


	<div class="DocumentPage" style="max-width: none; width: auto; display: inline;">
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

			<div class="CourseContentHeader">${selectedCourseHomeworkTypeData.name}成绩管理</div>

			<div class="CourseContentHeaderSeparatorLine"></div>



			<table>
				<tr>
					<td><div class="btn-group" role="group" aria-label="...">
							<!-- Split button -->
							<div class="btn-group">
								<button type="button" class="btn btn-default btn-sm">选择</button>
								<button type="button" class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown"
									aria-expanded="false">
									<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
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
					<td><div class="btn-group" role="group" aria-label="..."></div></td>
					<td style="width: 10px;"></td>
					<td><div class="input-group input-group-sm">
							<input type="text" class="form-control" id="SearchText" placeholder="Search for..." required>
							<span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="onSearch()">搜索</button>
							</span>
						</div></td>
					<td style="width: 10px;"></td>
					<td><div class="input-group input-group-sm">

							<div class="dropdown">
								<button class="btn btn-default btn-sm dropdown-toggle" type="button" id="dropdownMenu1"
									data-toggle="dropdown">
									作业排序 <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
									<li role="presentation"><a
											href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?homeworkShowOrder=1&pageNo=${selectedpageNo}">
											<c:if test="${selectedCourseTeachingClassHomeworkShowOrder==1}">
												<i class="icon-ok"></i>
											</c:if>
											递增
										</a></li>
									<li role="presentation"><a
											href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?homeworkShowOrder=0&pageNo=${selectedpageNo}">
											<c:if test="${selectedCourseTeachingClassHomeworkShowOrder==0}">
												<i class="icon-ok"></i>
											</c:if>
											递减
										</a></li>
								</ul>
							</div>
						</div></td>

					<c:if
						test="${pagedCourseTeachingClassHomeworkStudentScoresViewData!=null and pagedCourseTeachingClassHomeworkStudentScoresViewData.totalCount>10}">



						<td style="width: 10px;"></td>
						<td><div class="btn-group" role="group" aria-label="...">
								<!-- Split button -->
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-sm">每页大小</button>
									<button type="button" class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown"
										aria-expanded="false">
										<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li><a
												href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=10">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==10}">
													<i class="icon-ok"></i>
												</c:if>
												默认(10)
											</a></li>
										<li class="divider"></li>
										<li><a
												href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=20">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==20}">
													<i class="icon-ok"></i>
												</c:if>
												20
											</a></li>
										<li><a
												href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=30">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==30}">
													<i class="icon-ok"></i>
												</c:if>
												30
											</a></li>
										<li><a
												href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=40">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==40}">
													<i class="icon-ok"></i>
												</c:if>
												40
											</a></li>
										<li><a
												href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=50">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==50}">
													<i class="icon-ok"></i>
												</c:if>
												50
											</a></li>
										<li><a
												href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=60">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==60}">
													<i class="icon-ok"></i>
												</c:if>
												60
											</a></li>
										<li><a
												href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=70">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==70}">
													<i class="icon-ok"></i>
												</c:if>
												70
											</a></li>
										<li><a
												href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=80">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==80}">
													<i class="icon-ok"></i>
												</c:if>
												80
											</a></li>
										<li><a
												href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=90">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==90}">
													<i class="icon-ok"></i>
												</c:if>
												90
											</a></li>
										<li><a
												href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=100">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize==100}">
													<i class="icon-ok"></i>
												</c:if>
												100
											</a></li>
										<li class="divider"></li>
										<li><a
												href="coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?pageSize=1000">
												<c:if test="${sessionScope.USER_CONTEXT.pageSize>100}">
													<i class="icon-ok"></i>
												</c:if>
												不分页
											</a></li>
									</ul>
								</div>
							</div></td>

					</c:if>

				</tr>
			</table>

			<c:choose>

				<c:when test="${pagedCourseTeachingClassHomeworkStudentScoresViewData.totalCount >0}">
					<div class="Course-Table" style="display: inline;">
						<table class="table table-bordered table-hover table-condensed ">
							<thead>
								<tr>
									<th rowspan="2" style="width: 20px;">#</th>
									<th rowspan="2" style="width: 100px;">班级</th>
									<th rowspan="2" style="width: 100px;">学号</th>
									<th rowspan="2" style="width: 100px;">姓名</th>
									<th colspan="${fn:length(lstCourseTeachingClassHomeworkScoreInfoViewData)}">作业成绩</th>
								</tr>

								<tr>
									<c:set var="homeworkindex" value="1"></c:set>
									<c:forEach var="d" items="${lstCourseTeachingClassHomeworkScoreInfoViewData}">
										<th><c:choose>
												<c:when test="${selectedCourseTeachingClassHomeworkShowOrder==1}">
										${homeworkindex}.${d.homeworkBaseinfo.title }
										</c:when>
												<c:when test="${selectedCourseTeachingClassHomeworkShowOrder==0}">
										${fn:length(lstCourseTeachingClassHomeworkScoreInfoViewData) -homeworkindex+1}.${d.homeworkBaseinfo.title }
										</c:when>
											</c:choose></th>
										<c:set var="homeworkindex" value="${homeworkindex + 1}"></c:set>
									</c:forEach>
								</tr>

							</thead>
							<tbody>
								<c:set var="index" value="1"></c:set>
								<c:forEach var="dataitem"
									items="${pagedCourseTeachingClassHomeworkStudentScoresViewData.result}">
									<tr>
										<td rowspan="4" style="width: 20px;">${(pagedCourseTeachingClassHomeworkStudentScoresViewData.currentPageNo-1) * pagedCourseTeachingClassHomeworkStudentScoresViewData.pageSize +index}
										</td>
										<td rowspan="4" style="width: 100px;">${dataitem.studentView.naturalclass.name}</td>
										<td rowspan="4" style="width: 100px;"><a target="_blank"
												href="coursehomeworkscore/studentscore-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${dataitem.studentView.student.id}.html?homeworkShowOrder=${selectedCourseTeachingClassHomeworkShowOrder}">${dataitem.studentView.student.studentNum}</a></td>
										<td rowspan="4" style="width: 100px;"><a target="_blank"
												href="coursehomeworkscore/studentscore-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-${dataitem.studentView.student.id}.html?homeworkShowOrder=${selectedCourseTeachingClassHomeworkShowOrder}">${dataitem.studentView.userbasicinfo.userBasicInfoName}</a></td>



										<c:forEach var="i" begin="1" end="4">
											<c:if test="${i!=1 }">
												<tr>
											</c:if>

											<c:set var="homeworkindex" value="1"></c:set>
											<c:forEach var="d" items="${dataitem.lstScore}">
												<c:choose>
													<c:when test="${i==1 }">
														<td id="score${dataitem.studentView.student.id}${d.scoreInfo.scoreInfo.id }"
															<c:if test="${empty d.score }">style="background-color:red;"</c:if>>${d.score.score }</td>
													</c:when>
													<c:when test="${i==2 }">
														<td id="description${dataitem.studentView.student.id}${d.scoreInfo.scoreInfo.id }"
															<c:if test="${empty d.score }">style="background-color:red;"</c:if>>${d.score.description }</td>
													</c:when>
													<c:when test="${i==3 }">
														<td id="note${dataitem.studentView.student.id}${d.scoreInfo.scoreInfo.id }"
															<c:if test="${empty d.score }">style="background-color:red;"</c:if>>${d.score.note }</td>
													</c:when>
													<c:when test="${i==4 }">

														<td>

															<button id="btnUpdate${dataitem.studentView.student.id}${d.scoreInfo.scoreInfo.id }"
																type="button" class="btn btn-default btn-xs"
																<c:if test="${empty d.score }">style="display:none;"</c:if>
																onclick="onUpdateStudentScore('${dataitem.studentView.student.id }','${dataitem.studentView.userbasicinfo.userBasicInfoName }','${d.scoreInfo.scoreInfo.id }','${d.scoreInfo.homeworkBaseinfo.title }','${d.score.score }','${d.score.description }','${d.score.note }')">${homeworkindex}.修改</button>

															<button id="btnDelete${dataitem.studentView.student.id}${d.scoreInfo.scoreInfo.id }"
																type="button" class="btn btn-default btn-xs"
																<c:if test="${empty d.score }">style="display:none;"</c:if>
																onclick="onDeleteStudentScore('${d.score.id }','${dataitem.studentView.student.id }','${dataitem.studentView.userbasicinfo.userBasicInfoName }','${d.scoreInfo.scoreInfo.id }','${d.scoreInfo.homeworkBaseinfo.title }','${d.score.score }','${d.score.description }','${d.score.note }')">${homeworkindex}.删除</button>


															<button id="btnAdd${dataitem.studentView.student.id}${d.scoreInfo.scoreInfo.id }"
																type="button" class="btn btn-default btn-xs"
																<c:if test="${not empty d.score }">style="display:none;"</c:if>
																onclick="onUpdateStudentScore('${dataitem.studentView.student.id }','${dataitem.studentView.userbasicinfo.userBasicInfoName }','${d.scoreInfo.scoreInfo.id }','${d.scoreInfo.homeworkBaseinfo.title }','${d.score.score }','${d.score.description }','${d.score.note }')">${homeworkindex}.增加</button>


														</td>
													</c:when>
												</c:choose>
												<c:set var="homeworkindex" value="${homeworkindex + 1}"></c:set>

											</c:forEach>
									</tr>
								</c:forEach>


								<c:set var="index" value="${index + 1}"></c:set>
								</c:forEach>

							</tbody>
						</table>








						<c:if test="${not empty selectedCourseTeachingClassID}">
							<mathtop:PageBar
								pageUrl="/coursehomeworkscore/list-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}.html?homeworkShowOrder=${selectedCourseTeachingClassHomeworkShowOrder}"
								pageAttrKey="pagedCourseTeachingClassHomeworkStudentScoresViewData" />
						</c:if>



					</div>

				</c:when>

				<c:otherwise>
					<%
						//没有找到记录
					%>
					<div class="alert alert-warning alert-dismissible fade in" role="alert" style="margin: 10px;">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3>没有找到记录,请选择下列操作之一：</h3>

						<div class="list-group">


							<a href="<c:url value="/teachingclass/add.html"/>" class="list-group-item">添加教学班班</a>
							<a href="<c:url value="/course/add.html"/>" class="list-group-item">添加课程</a>

							<a href="<c:url value="/teacher/add.html"/>" class="list-group-item">添加教师</a>
							<a href="<c:url value="/naturalclass/list.html"/>" class="list-group-item">添加自然班</a>
							<a href="<c:url value="/school/list.html"/>" class="list-group-item">添加学院</a>
							<a href="<c:url value="/department/list.html"/>" class="list-group-item">添加系部</a>

						</div>

					</div>
				</c:otherwise>
			</c:choose>






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
					<h4 class="modal-title" id="myModalLabel">
						<span id="student_name" class="text-warning"></span>${selectedCourseHomeworkTypeData.name}成绩</h4>
				</div>


				<input type="hidden" name="pageNo" value="${selectedpageNo}">
				<input type="hidden" name="homeworkShowOrder"
					value="${selectedCourseTeachingClassHomeworkShowOrder}">




				<div class="modal-body">
					<input type="hidden" id="id" name="id">
					<input type="hidden" id="t_student_id" value="">
					<input type="hidden" id="t_score_info_id" value="">

					<h4 class="form-signin-heading">
						请<span id="homework_title" class="text-warning"></span>成绩设置属性
					</h4>
					<div class="form-group">
						<label for="t_score_marking_type_id" class="col-sm-2 control-label">成绩</label>
						<div class="col-sm-10">
							<input type="text" id="score" class="form-control" name="score" value="" placeholder="成绩">
						</div>
					</div>

					<div class="form-group">
						<label for="t_score_show_type_id" class="col-sm-2 control-label">描述</label>
						<div class="col-sm-10">
							<div class="summernote" id="description"></div>
							<textarea class="form-control" rows="5" name="description" id="textAreadescription"
								style="display: none;"></textarea>
						</div>
					</div>

					<div class="form-group">
						<label for="t_score_show_type_id" class="col-sm-2 control-label">备注</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="2" name="note" id="textAreanote"></textarea>

						</div>
					</div>





				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" onclick="onAdd()">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>


			</div>
		</div>
	</div>








	<%@ include file="../../shared/sysLastInclude.jsp"%>


	<script type="text/javascript">
		$("#collapseHomeworkScoreManager").addClass("in");
		$("#coursehomeworkscore${selectedCourseHomeworkTypeData.id}").addClass(
				"active");
	</script>

	<script type="text/javascript">
		
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

		function onAdd() {

			var sHTML = $('.summernote').summernote('code');
			$('#updateModal').find('.modal-body #textAreadescription').text(
					sHTML);

			var json = {
				id : null,
				courseTeachingClassHomeworkScoreInfoId : $('#updateModal')
						.find('.modal-body #t_score_info_id').val(),
				studentId : $('#updateModal').find('.modal-body #t_student_id')
						.val(),
				teacherId : null,
				score : $('#updateModal').find('.modal-body #score').val(),
				recordDate : null,
				description : sHTML,
				note : $('#updateModal').find('.modal-body #textAreanote')
						.val()
			};

			$
					.ajax({
						url : "<c:url value='/coursehomeworkscore/updatebyjson.json'/>",
						type : 'POST',
						data : JSON.stringify(json),
						contentType : "application/json",
						dataType : 'json',
						crossdomain : true,
						complete : function() {
							//发送完成
						},
						success : function(msg) {
							//发送成功
							if (msg == 0) {
								$(
										"#score"
												+ json.studentId
												+ json.courseTeachingClassHomeworkScoreInfoId)
										.html(json.score);
								$(
										"#description"
												+ json.studentId
												+ json.courseTeachingClassHomeworkScoreInfoId)
										.html(json.description);
								$(
										"#note"
												+ json.studentId
												+ json.courseTeachingClassHomeworkScoreInfoId)
										.html(json.note);

								$(
										"#score"
												+ json.studentId
												+ json.courseTeachingClassHomeworkScoreInfoId)
										.css("background-color", "");
								$(
										"#description"
												+ json.studentId
												+ json.courseTeachingClassHomeworkScoreInfoId)
										.css("background-color", "");
								$(
										"#note"
												+ json.studentId
												+ json.courseTeachingClassHomeworkScoreInfoId)
										.css("background-color", "");

								$(
										"#btnAdd"
												+ json.studentId
												+ json.courseTeachingClassHomeworkScoreInfoId)
										.hide();
								$(
										"#btnUpdate"
												+ json.studentId
												+ json.courseTeachingClassHomeworkScoreInfoId)
										.show();
								$(
										"#btnDelete"
												+ json.studentId
												+ json.courseTeachingClassHomeworkScoreInfoId)
										.show();

								$('#updateModal').modal('hide');
							} else {
								$("#result").text("失败:" + msg);
							}
						},
						error : function(xhr, error, exception) {
							alert("操作失败");

						}
					});

		}

		function onUpdateStudentScore(t_student_id, t_student_name,
				t_score_info_id, homework_title, t_student_score_score,
				t_student_score_description, t_student_score_note) {

			$('#updateModal').find('.modal-body #t_score_info_id').val(
					t_score_info_id);
			$('#updateModal').find('.modal-body #t_student_id').val(
					t_student_id);

			$('#updateModal').find('.modal-header #student_name').text(
					t_student_name);
			$('#updateModal').find('.modal-body #homework_title').text(
					homework_title);

			$('#updateModal').find('.summernote').summernote({
				height : 300, // set editor height
				lang : 'zh-CN' // default: 'en-US'
			//  minHeight: null,             // set minimum height of editor
			//  maxHeight: null,             // set maximum height of editor

			//  focus: true,                 // set focus to editable area after initializing summernote
			});

			$('#updateModal').find('.modal-body #score').val(
					t_student_score_score);

			$('#updateModal').find('.summernote').summernote('code',
					t_student_score_description);

			//	var url = "<c:url value='/coursehomeworkscore/update-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"+t_score_info_id+"-"+t_student_id+".html'/>"

			//	$('#updateModal').find('form').attr("action", url);

			$('#updateModal').modal('show');

		}

		function onDeleteStudentScore(t_score_id, t_student_id, t_student_name,
				t_score_info_id, homework_title, t_student_score_score,
				t_student_score_description, t_student_score_note) {

			var url = "location='<c:url value="/coursehomeworkscore/delete-${selectedCourseTeachingClassID}-${selectedCourseHomeworkTypeData.id}-"/>"
					+ t_score_id
					+ ".html?pageNo=${selectedpageNo}&homeworkShowOrder=${selectedCourseTeachingClassHomeworkShowOrder}'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(
					t_student_name + "(" + homework_title + ")");
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}
	</script>




</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "studentgroup");
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



<link href="<c:url value='/css/pages/index.css'/>" rel="stylesheet" type="text/css" />

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
						href="<c:url value="/coursecontent/index-${selectedCourseTeachingClassViewData.courseTeachingClass.id}.html"/>">${selectedCourseTeachingClassViewData.courseTeachingClass.name}</a></li>

			</ol>

			<div class="CourseContentHeader">人工分组管理</div>

			<div class="CourseContentHeaderSeparatorLine"></div>



			<table>
				<tr>

					<td><div class="btn-group" role="group" aria-label="...">

							<button type="button" class="btn btn-default btn-sm" data-toggle="modal"
								data-target="#addGroupModal">添加分组</button>


						</div></td>

				</tr>
			</table>

			<c:choose>

				<c:when test="${fn:length(lstCourseTeachingClassStudentGroupViewData)==0}">

					<div class="alert alert-warning alert-dismissible fade in" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>暂未分组</strong>
						<p class="text-info">
							请为<em>${selectedCourseTeachingClassViewData.courseTeachingClass.name}</em>分组
						</p>
					</div>

				</c:when>

				<c:otherwise>

					<div class="Course-Table">
						<div class="gridseparator"></div>

						<div class="container-fluid">
							<div class="row">
								<div class="col-md-1">
									<strong>#</strong>
								</div>
								<div class="col-md-2">
									<strong>小组</strong>
								</div>
								<div class="col-md-4">


									<div class="container-fluid">
										<div class="row">
											<div class="col-md-12">
												<strong>学生</strong>
											</div>
										</div>
										<div class="gridseparator"></div>
										<div class="row">
											<div class="col-md-1">
												<strong>#</strong>
											</div>
											<div class="col-md-2">
												<strong>姓名</strong>
											</div>
											<div class="col-md-2">
												<strong>学号</strong>
											</div>
											<div class="col-md-2">
												<strong>角色</strong>
											</div>
											<div class="col-md-2">
												<strong>操作</strong>
											</div>
										</div>
									</div>

								</div>
								<div class="col-md-3">
									<strong>操作</strong>
								</div>
							</div>
							<div class="gridseparator"></div>


							<c:set var="index" value="1"></c:set>
							<c:forEach var="data" items="${lstCourseTeachingClassStudentGroupViewData}">
								<div class="row <c:if test="${index%2==0 }">bg-info</c:if>">
									<div class="col-md-1">${index}</div>
									<div class="col-md-2">${data.group.name }</div>
									<div class="col-md-4">

										<div class="container-fluid">
											<c:set var="stuindex" value="1"></c:set>
											<c:forEach var="stu" items="${data.studentViewDatas}">
												<div class="row">
													<div class="col-md-1">${stuindex }</div>
													<div class="col-md-2">${stu.userbasicinfo.userBasicInfoName }</div>
													<div class="col-md-2">${stu.student.studentNum }</div>
													<div class="col-md-2">
														<strong>角色</strong>
													</div>
													<div class="col-md-2">
														<button type="button" class="btn btn-default btn-xs"
															onclick="onStudentDelete('${data.group.id}','${data.group.name}','${stu.user.id}','${stu.userbasicinfo.userBasicInfoName}')">删除</button>

													</div>
												</div>


												<c:set var="stuindex" value="${stuindex + 1}"></c:set>
											</c:forEach>
										</div>
									</div>





									<div class="col-md-3">
										<button type="button" class="btn btn-default btn-xs"
											onclick="onUpdate('${data.courseTeachingClassStudentGroup.id}','${data.group.id}','${data.group.name}','${data.group.note}')">修改...</button>
										<button type="button" class="btn btn-default btn-xs"
											onclick="onDelete('${data.courseTeachingClassStudentGroup.id}','${data.group.name}')">删除</button>

										<button type="button" class="btn btn-default btn-xs"
											onclick="location='<c:url value="/coursegroup/showindexmoveupatmannal-${selectedCourseTeachingClassViewData.courseTeachingClass.id}-${data.group.id}.html"/>'">
											<i class="icon-circle-arrow-up"></i>向上移动
										</button>

										<button type="button" class="btn btn-default btn-xs"
											onclick="location='<c:url value="/coursegroup/showindexmovedownatmannal-${selectedCourseTeachingClassViewData.courseTeachingClass.id}-${data.group.id}.html"/>'">
											<i class="icon-circle-arrow-down"></i>向下移动
										</button>
										<button type="button" class="btn btn-default btn-xs"
											onclick="onAddStudent('${data.courseTeachingClassStudentGroup.id}','${data.group.id}','${data.group.name}')">
											<i class="icon-user"></i>添加学生
										</button>

									</div>
								</div>
								<c:set var="index" value="${index + 1}"></c:set>
							</c:forEach>

							<div class="gridseparator"></div>

						</div>
					</div>


				</c:otherwise>
			</c:choose>





		</div>
	</div>





	<!-- 添加对话框 -->
	<div class="modal fade" id="addGroupModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">为${selectedCourseTeachingClassViewData.courseTeachingClass.name}添加分组</h4>
				</div>

				<form class="form-horizontal"
					action="<c:url value="/coursegroup/addgroup-${selectedCourseTeachingClassViewData.courseTeachingClass.id}.html"/>"
					method="post">
					<div class="modal-body">

						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">小组名称</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="name" name="name" placeholder="小组名称" value="">
							</div>
						</div>

						<div class="form-group">
							<label for="note" class="col-sm-2 control-label">小组说明</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="note" name="note" placeholder="小组说明" value="">
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">添加</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>

				</form>



			</div>
		</div>
	</div>

	<!-- 修改对话框 -->
	<div class="modal fade" id="updateGroupModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">为${selectedCourseTeachingClassViewData.courseTeachingClass.name}修改分组</h4>
				</div>

				<form class="form-horizontal"
					action="<c:url value="/coursegroup/updategroup-${selectedCourseTeachingClassViewData.courseTeachingClass.id}.html"/>"
					method="post">
					<div class="modal-body">

						<input type="hidden" id="t_group_id" name="t_group_id" />
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">小组名称</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="name" name="name" placeholder="小组名称" >
							</div>
						</div>

						<div class="form-group">
							<label for="note" class="col-sm-2 control-label">小组说明</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="note" name="note" placeholder="小组说明" >
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">修改</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>

				</form>



			</div>
		</div>
	</div>


	<!-- 添加学生对话框 -->
	<div class="modal fade" id="AddStudentModal" tabindex="-1" role="dialog"
		aria-labelledby="myStudentModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myStudentModalLabel">
						为${selectedCourseTeachingClassViewData.courseTeachingClass.name}<span id="group_name"></span>小组添加学生
					</h4>
					<p class="bg-danger" id="andStudentError"></p>
				</div>

				<form class="form-horizontal" onsubmit="return checkStudent();"
					action="<c:url value="/coursegroup/addstudent2group-${selectedCourseTeachingClassViewData.courseTeachingClass.id}.html"/>"
					method="post">
					<input type="hidden" id="t_group_id" name="t_group_id" /> <input type="hidden" id="userids"
						name="userids" />

					<div class="modal-body">
						<div class="list-group" id="studentContainer"></div>

					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" onclick="addStudent()">添加</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>

				</form>



			</div>
		</div>
	</div>


	

	<%@ include file="../../shared/importJs.jsp"%>
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
		
		function onStudentDelete(t_group_id,group_name,t_user_id,student_name) {
			var url = "location='<c:url value="coursegroup/deletestudentfromgroup-${selectedCourseTeachingClassID}-"/>"
					+ t_group_id+"-"+t_user_id + ".html'";

			var	s = "组" + group_name + "中的"+student_name;
			$('#deleteModal').find('.modal-body #deleteinfo').text(s);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');
		}

		function onDelete(id, name) {
			var url = "location='<c:url value="coursegroup/deletegroup-${selectedCourseTeachingClassID}-"/>"
					+ id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}

		function onUpdate(id, t_group_id, name, note) {
			
			
		
			var url = "location='<c:url value="coursegroup/updategroup-${selectedCourseTeachingClassID}-"/>"
					+ id + ".html'";
			

			$('#updateGroupModal').find('.modal-body #t_group_id').val(
					t_group_id);
			$('#updateGroupModal').find('.modal-body #name').val(name);
			$('#updateGroupModal').find('.modal-body #note').val(note);

			$('#updateGroupModal').modal('show');

		}

		function checkStudent() {
			var ids = $("#userids").val();
			if (ids == null || ids.length == 0) {
				ShowErrMsg("学生不能为空");
				return false;
			}
			return true;
		}

		function addStudent() {

		}

		function clearStudent() {
			$("#studentContainer").empty();
			$("#andStudentError").empty();//清除错误提示
			$(".list-group-item").removeClass('active');
			$("#userids").val("");

		}
		function changeStudent(t_user_id, e) {

			$(e).toggleClass('active');

			var ids = $("#userids").val();
			if ($(e).hasClass('active')) {
				if (ids.length > 0)
					ids += ";";
				ids += t_user_id;
			} else {
				//找到的话，则删除它
				
				if(ids.indexOf(t_user_id)>0){					
					ids=ids.replace(t_user_id,"");					
				}
				
				
			}
			
			ids=ids.trim();
			if(ids.length<32)//t_user_id为32位长度
				ids="";
			else {
				
				if (ids.match(";;") != null)
					ids.replace(";;", ";");	
			
				if(ids.charAt(0)==';'){
					ids=ids.substring(1,ids.length-1);
				}
				if(ids.charAt(ids.length-1)==';'){
					ids=ids.substring(0,ids.length-2);
				}
				
			}

			
			
			

			$("#userids").val(ids);

		}

		function getNoteGroupedStudent() {

			clearStudent();
			var url = "<c:url value='/coursegroup/selectnotgrouped-${selectedCourseTeachingClassID}.json'/>";
			$.get(url, function(data, status) {
				if (status == "success") {

					for (var i = 0; i < data.length; i++) {
						var s = "<a onclick='changeStudent(\""
								+ data[i].user.id
								+ "\",this)' class='list-group-item'>"
								+ data[i].userbasicinfo.userBasicInfoName + "("
								+ data[i].student.studentNum + ")</a>";

						$("#studentContainer").append(s);

					}

					$("#spmsPageBar").html(pageBar(data));

				}
			});
		}

		function onAddStudent(id, t_group_id, group_name) {
			$('#AddStudentModal').find('#t_group_id').val(t_group_id);
			$('#AddStudentModal').find('#group_name').text(group_name);

			getNoteGroupedStudent();

			$('#AddStudentModal').modal('show');
		}
	</script>





</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "info");
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
<%@ include file="../../shared/importdatetimepickercss.jsp"%>

<link href="<c:url value='/css/pages/index.css'/>" rel="stylesheet"
	type="text/css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="home">

	<%@ include file="../../shared/pageHeader.jsp"%>
	

	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="../CourseNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">




			<div class="CourseContentHeader">学生信息</div>

			<div class="CourseContentHeaderSeparatorLine"></div>

			<div style="display: none;" id="contactinfoContainer">
				<select id="contactinfoSelectControl" class="form-control input-sm"
					name="contacttypeId">
					<c:forEach var="contacttype" items="${pagedContactType.result}">
						<option value="${contacttype.id}">${contacttype.name}</option>
					</c:forEach>
				</select>
			</div>




			<form class="form-horizontal" style="overflow: hidden;"
				action="<c:url value="/student/infoupdate.html"/>" method="post">

				<input type="hidden" name="t_user_id"
					value="${selectedStudentViewData.user.id }">



				<div class="form-group">
					<label for="SchoolSelectControl" class="col-sm-1 control-label">学院</label>
					<div class="col-md-3">
						<p class="form-control-static">${selectedStudentViewData.school.name }</p>

					</div>

					<label for="departmentSelectControl" class="col-sm-1 control-label">系部</label>
					<div class="col-md-3">
						<p class="form-control-static">${selectedStudentViewData.department.name}</p>

					</div>

					<label for="naturalclassSelectControl"
						class="col-sm-1 control-label">班级</label>
					<div class="col-md-3">
						<p class="form-control-static">${selectedStudentViewData.naturalclass.name}</p>

					</div>

				</div>
				<div class="form-group">
					<label for="student_num" class="col-sm-1 control-label">学号</label>
					<div class="col-md-3">
						<p class="form-control-static">${selectedStudentViewData.student.student_num}</p>

					</div>

					<label for="user_basic_info_name" class="col-sm-1 control-label">姓名</label>
					<div class="col-md-3">
						<p class="form-control-static">${selectedStudentViewData.userbasicinfo.user_basic_info_name}</p>

					</div>
				</div>

				<input type="hidden" id="user_basic_info_birthday_buf"
					value='<fmt:formatDate value="${selectedStudentViewData.userbasicinfo.user_basic_info_birthday}" pattern="yyyy-MM-dd"/>'
					readonly>




				<div class="form-group">
					<label for="user_basic_info_birthday"
						class="col-sm-1 control-label">生日</label>
					<div class="col-md-3">
						<div class="input-group date form_date" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="dtp_input2"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" size="16" type="text"
								id="user_basic_info_birthday" value=""
								name="user_basic_info_birthday" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<input type="hidden" id="dtp_input2" value="" /><br />
					</div>
				</div>
				
			

				<div class="form-group">
					<label for="user_basic_info_sex" class="col-sm-1 control-label">性别</label>
					<div class="col-md-3">
						<label class="radio-inline"> <input type="radio" value="0"
						<c:if test="${selectedStudentViewData.userbasicinfo.user_basic_info_sex<1}">
							checked="checked"
							</c:if>
							 name="user_basic_info_sex" id="inlineRadio1"
							value="option1"> 男
						</label> <label class="radio-inline"> <input type="radio"
						<c:if test="${selectedStudentViewData.userbasicinfo.user_basic_info_sex>0}">
							checked="checked"
							</c:if>
							value="1" name="user_basic_info_sex" id="inlineRadio2"
							value="option2"> 女
						</label>
					</div>
				</div>


				<div class="form-group">


					<label for="teacher_group" class="col-sm-1 control-label">组</label>
					<div class="col-md-6">



						<div class="Course-Table">


							<table id="studentgrouptable"
								class="table table-hover table-bordered">
								<thead>
									<tr>
										<th style="width: 10%;">#</th>
										<th style="width: 20%;">组</th>
									</tr>



								</thead>
								<tbody>
									<c:set var="index" value="1"></c:set>
									<c:forEach var="dataitem" items="${pagedGroup.result}">
										<tr>
											<th scope="row">${index}</th>
											<td>${dataitem.name}</td>

										</tr>
										<c:set var="index" value="${index + 1}"></c:set>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="contacttable" class="col-sm-1 control-label">联系方式</label>
					<div class="col-md-6">

						<div class="btn-group" role="group" aria-label="...">


							<button type="button" class="btn btn-default btn-sm"
								onclick="AddContactRow()">增加联系方式</button>


						</div>




						<div class="Course-Table">


							<table id="contacttable" class="table table-hover table-bordered">
								<thead>
									<tr>
										<th style="width: 10%;">#</th>
										<th style="width: 20%;">联系类型</th>
										<th style="width: 20%;">内容</th>
										<th style="width: 20%;">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="index" value="1"></c:set>
									<c:forEach var="dataitem"
										items="${selectedStudentViewData.usercontactinfoviewdata}">
										<tr id="contactrow${dataitem.usercontacttype.id}">
											<th scope="row">${index}</th>
											<td><input type="hidden" name="contacttypeId"
					value="${dataitem.usercontacttype.id }">${dataitem.usercontacttype.name}</td>
											<td><input type="text" name="user_contact_value" value="${dataitem.usercontactinfo.user_contact_value}"></td>
											<td><button type='button' class='btn btn-default btn-sm'
													onclick='DeleteContactRow("contactrow${dataitem.usercontacttype.id}")'>删除</button></td>
										</tr>
										<c:set var="index" value="${index + 1}"></c:set>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>






				<div class="modal-footer col-md-7">
					<button type="submit" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default"
						onclick="window.history.back()">取消</button>
				</div>

			</form>
		</div>
	</div>








	<%@ include file="../../shared/dialog.jsp"%>


	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>




	<script>
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});

		var s = $("#user_basic_info_birthday_buf").val();

		$("#user_basic_info_birthday").val(s);
	</script>



	<script>
		

		
		
		
		

		function AddContactRow() {
			var table = document.getElementById("contacttable");
			var trCnt = table.tBodies[0].rows.length;
			var row = table.tBodies[0].insertRow(trCnt);

			var td0 = row.insertCell(0);
			var td1 = row.insertCell(1);
			var td2 = row.insertCell(2);
			var td3 = row.insertCell(3);

			var cc = $("#contactinfoContainer").html();

			td1.innerHTML = cc;
			td2.innerHTML = "<input type='text' id='inputnote' class='form-control' name='user_contact_value' value='' placeholder='联系方式描述'>";
			td3.innerHTML = "<button type='button' class='btn btn-default btn-sm'	onclick='DeleteConcateRow("
					+ trCnt + ")'>删除</button>";

			trCnt = table.tBodies[0].rows.length;
			for (var i = 0; i < trCnt; i++) {
				table.tBodies[0].rows[i].cells[0].innerHTML = i + 1;
				table.tBodies[0].rows[i].id = "contactrow" + i;
			}

		}

		function DeleteConcateRow(i) {
			var table = document.getElementById("contacttable");
			var trCnt = table.tBodies[0].rows.length;
			var rowid = "contactrow" + i;
			for (var i = 0; i < trCnt; i++) {
				if (table.tBodies[0].rows[i].id == rowid)
					table.tBodies[0].deleteRow(i);
			}

		}
		
		
		function DeleteContactRow(rowid) {
			var table = document.getElementById("contacttable");
			var trCnt = table.tBodies[0].rows.length;
		
			for (var i = 0; i < trCnt; i++) {
				if (table.tBodies[0].rows[i].id == rowid)
					table.tBodies[0].deleteRow(i);
			}

		}
		
		
	</script>






	<c:if test="${!empty errorMsg}">
		<script>
			function ShowErrMsg() {
				ShowInfoMsg("${errorMsg}");

			}
		</script>

	</c:if>

	<c:set var="errorMsg" value="null" />

</body>
</html>

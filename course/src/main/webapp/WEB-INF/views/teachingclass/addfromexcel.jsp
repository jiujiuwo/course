<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "student");
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


	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="../CourseNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">


			<div class="CourseContentHeader">
				<span class="text-info"><strong>从Excel文件中向${selectedCourseTeachingClassViewData.courseTeachingClass.name}</strong></span>教学班批量增加学生
			</div>
			<p class="text-muted">
				<strong>课程名称：</strong>${selectedCourseTeachingClassViewData.course.name}</p>

			<p class="text-muted">
				<strong>授课教师：</strong>

				<c:forEach var="t" items="${selectedCourseTeachingClassViewData.teacher}" varStatus="status">
							${t.userbasicinfo.userBasicInfoName}(${selectedCourseTeachingClassViewData.teachingtype[status.index].name})
							</c:forEach>

			</p>

			<p class="text-muted">
				<strong>授课时间：</strong>${selectedCourseTeachingClassViewData.term.term}</p>
			<div class="CourseContentHeaderSeparatorLine"></div>






			<div class="container-fluid" style="overflow: hidden;">
				<div class="row">
					<div class="col-md-12">
						<strong>支持多种Excel文件格式</strong>
					</div>
				</div>
				<div class="row">

					<div class="col-md-6">
						<img alt="Excel文件格式" src="<c:url value="/image/addstufromexcel0.png"/>" />


					</div>
					<div class="col-md-6">

						<img alt="Excel文件格式" src="<c:url value="/image/addstufromexcel1.png"/>" />

					</div>



				</div>

				<div class="row">

					<div class="col-md-6">
						<img alt="Excel文件格式" src="<c:url value="/image/addstufromexcel2.png"/>" />


					</div>
					<div class="col-md-6">

						<img alt="Excel文件格式" src="<c:url value="/image/addstufromexcel3.png"/>" />

					</div>



				</div>
				<div class="row">
					<div class="col-sm-12">

						<div class="alert alert-warning alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>注意</strong>
							<ul class="list-group">
								<li>列的次序可以变化</li>
								<li>当不存在学院/系部/班级时，将键入默认学院/系部/班级中。</li>
								<li>“学院”列名可以为“学院”、“院”。</li>
								<li>“系”列名可以为“系”、“系部”、“部”等。</li>
								<li>“班级”列名可以为“班级”、“班”、“班号”等。</li>
								<li>可以是97-03格式(xls)，也可以是2007-2010格式(xlsx)</li>
							</ul>
						</div>


					</div>
				</div>
			</div>




			<form class="form-signin" action="<c:url value="/teachingclass/uploadexcel-${selectedCourseTeachingClassViewData.courseTeachingClass.id}.html"/>" method="post"
				enctype="multipart/form-data">




				<div class="form-group">
					<label for="exampleInputFile">请输入学生名单</label>
					<input type="file" id="exampleInputFile" name="file" accept=".xls,.xlsx">
					<p class="help-block">要求文件为excel文件。</p>
				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">添加</button>
					<button type="button" class="btn btn-default" onclick="window.history.back()">取消</button>
				</div>

			</form>
		</div>
	</div>



<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/sysLastInclude.jsp"%>

	
	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>

	<script>
		$(function() {

			var groupctrl = document.getElementById("groupSelectControl");

			index = 0;
			for (var i = 0; i < groupctrl.options.length; i++) {

				if (groupctrl.options[i].text.indexOf("学生") >= 0)
					index = i;
			}

			var groupctrlbyname = document.getElementsByName("groupId");
			for (var i = 0; i < groupctrlbyname.length; i++) {

				groupctrlbyname[i].selectedIndex = index;
			}

		});
	</script>


	<script type="text/javascript">
		function AddGroupRow() {
			var table = document.getElementById("studentgrouptable");
			var trCnt = table.tBodies[0].rows.length;
			var row = table.tBodies[0].insertRow(trCnt);

			var td0 = row.insertCell(0);
			var td1 = row.insertCell(1);
			var td2 = row.insertCell(2);

			var cc = $("#groupContainer").html();

			td1.innerHTML = cc;

			td2.innerHTML = "<button type='button' class='btn btn-default btn-sm'	onclick='DeleteGroupRow("
					+ trCnt + ")'>删除</button>";

			trCnt = table.tBodies[0].rows.length;
			for (var i = 0; i < trCnt; i++) {
				table.tBodies[0].rows[i].cells[0].innerHTML = i + 1;
				table.tBodies[0].rows[i].id = "grouprow" + i;
			}

		}

		function DeleteGroupRow(i) {
			var table = document.getElementById("studentgrouptable");
			var trCnt = table.tBodies[0].rows.length;
			var rowid = "grouprow" + i;
			for (var i = 0; i < trCnt; i++) {
				if (table.tBodies[0].rows[i].id == rowid)
					table.tBodies[0].deleteRow(i);
			}

		}
	</script>

	<script type="text/javascript">
		$(function() {
			$("#SchoolSelectControl").change(
					function() {
						var selt_school_id = $("#SchoolSelectControl").val();
						var url = "<c:url value="/department/"/>"

						url = url + "selectbyschool-" + selt_school_id
								+ ".json";

						var departmentjson;
						$("#departmentSelectControl").empty();
						$.get(url, function(data, status) {
							if (status == "success") {

								for (var i = 0; i < data.length; i++) {
									$("#departmentSelectControl").append(
											"<option value='"+data[i].id+"'>"
													+ data[i].name
													+ "</option>");
								}
							}
						});

					});

		});
	</script>


</body>
</html>

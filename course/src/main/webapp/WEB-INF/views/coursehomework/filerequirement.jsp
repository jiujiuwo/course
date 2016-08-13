<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 添加对话框 -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加作业要求</h4>
				</div>




				<div class="modal-body">
					<input type="hidden" id="parentID" value="-1" />
					<h4 class="form-signin-heading">请输入作业要求</h4>

					<div class="form-group" id="filetypegroup">
						<label for="name" class=" control-label">文件类型</label>
						<input type="radio" name="filetyperadio" checked="checked" value="0"
							onclick="onfiletyperadio(0)" />
						具体文件类型
						<input type="radio" value="1" name="filetyperadio" onclick="onfiletyperadio(1)" />
						不限制文件类型
						<input type="radio" value="2" name="filetyperadio" onclick="onfiletyperadio(2)" />
						目录



						<div class="panel panel-default" id="filetypepanel0">
							<div class="panel-heading">${selectedCourseHomeworkTypeData.name}文件类型</div>
							<div class="panel-body" style="margin: 10px;">
								<input type=checkbox name="filetype" value="0" checked="checked">
								word文件
								<input type=checkbox name="filetype" value="1">
								excel文件
								<input type=checkbox name="filetype" value="2">
								ppt文件
								<input type=checkbox name="filetype" value="3">
								文本文件(txt)
								<input type=checkbox name="filetype" value="4">
								pdf文件(pdf)
								<input type=checkbox name="filetype" value="5">
								c文件
								<input type=checkbox name="filetype" value="6">
								c++文件
								<input type=checkbox name="filetype" value="7">
								java文件
								<input type=checkbox name="filetype" value="8">
								zip文件
								<input type=checkbox name="filetype" value="9">
								rar文件
								<p />
								<label for="filetypecustom" class=" control-label">自定义类型(类型之间用分号隔开，例如“*.txt;*.dat”)</label>
								<input type="text" id="filetypecustom" class="form-control" name="filetypecustom" value=""
									placeholder="自定义类型">
							</div>
						</div>


						<div class="panel panel-default" id="filetypepanel1" style="display: none;">
							<div class="panel-heading">不限制${selectedCourseHomeworkTypeData.name}附件类型</div>
							<div class="panel-body">${selectedCourseHomeworkTypeData.name}附件可以为任意类型。</div>
						</div>

					</div>
					
					
					<div class="form-group" id="fileCountgroup">
						<label for="filecountPanel" class=" control-label">文件个数</label>
					<div class="panel panel-default" id="filecountPanel"">
							<div class="panel-heading">请定义文件个数</div>
							<div class="panel-body">

								<input type="radio" name="filecount" checked="checked" value="1"
									onclick="onfileCountradio(1)" />
								1
								<input type="radio" value="2" name="filecount" onclick="onfileCountradio(2)" />
								2
								<input type="radio" value="3" name="filecount" onclick="onfileCountradio(3)" />
								3
								<input type="radio" value="4" name="filecount" onclick="onfileCountradio(4)" />
								4
								<input type="radio" value="5" name="filecount" onclick="onfileCountradio(5)" />
								5
								<input type="radio" value="6" name="filecount" onclick="onfileCountradio(6)" />
								6
								<input type="radio" value="7" name="filecount" onclick="onfileCountradio(7)" />
								7
								<input type="radio" value="8" name="filecount" onclick="onfileCountradio(8)" />
								8
								<input type="radio" value="9" name="filecount" onclick="onfileCountradio(9)" />
								9
								<input type="radio" value="-1" name="filecount" onclick="onfileCountradio(-1)" />
								不限制

							</div>
						</div>
						</div>



					<div class="form-group" id="filenameformatgroup">
						<label for="filenameformatradio" class=" control-label">文件名称格式</label>
						<input type="radio" name="filenameformatradio" checked="checked" value="0"
							onclick="onfilenameformatradio(0)" />
						预定义格式
						<input type="radio" value="1" name="filenameformatradio" onclick="onfilenameformatradio(1)" />
						自定义格式
						<input type="radio" value="2" name="filenameformatradio" onclick="onfilenameformatradio(2)" />
						不限定格式



						<div class="panel panel-default" id="filenameformatpanel0">
							<div class="panel-heading">
								预定义格式(<span class="bg-info text-info">{}内的内容根据作业、学生姓名和所在班级不同而不同</span>)
							</div>
							<div class="panel-body">
								<input type="radio" name="filenameformat" value="0" checked="checked" />${selectedCourseHomeworkTypeData.name}_{作业名称}_{自然班}_{学号}_{姓名}
								<br>
								<input type="radio" name="filenameformat" value="1" />${selectedCourseHomeworkTypeData.name}_{作业名称}_{学号}_{姓名}
								<br>
								<input type="radio" name="filenameformat" value="2" />${selectedCourseHomeworkTypeData.name}_{自然班}_{学号}_{姓名}
								<br>
								<input type="radio" name="filenameformat" value="3" />
								{作业名称}_{自然班}_{学号}_{姓名}
							</div>
						</div>



						<div class="panel panel-default" id="filenameformatpanel1" style="display: none;">
							<div class="panel-heading">自定义格式</div>
							<div class="panel-body">
								<p>"{}"为格式控制符。含义如下：</p>

								<dl>
									<dt>{学院}</dt>
									<dd>学生所在的学院</dd>
									<dt>{系别}</dt>
									<dd>学生所在的系别</dd>
									<dt>{课程名}</dt>
									<dd>课程名称</dd>
									<dt>{教学学年}</dt>
									<dd>教学学年，例如：2015-2016学年</dd>
									<dt>{教学学期}</dt>
									<dd>教学学期，例如：第1学期</dd>
									<dt>{教学班授课教师}</dt>
									<dd></dd>
									<dt>{教学班}</dt>
									<dd>学生所在的教学班</dd>

									<dt>{自然班}</dt>
									<dd>学生所在的自然班（行政班）</dd>
									<dt>{学号}</dt>
									<dd>学生的学号</dd>
									<dt>{姓名}</dt>
									<dd>学生的姓名</dd>
									<dt>{作业类型}</dt>
									<dd>本次作业的类型（${selectedCourseHomeworkTypeData.name}）</dd>

									<dt>{作业名称}</dt>
									<dd>本次作业的名称</dd>

									<dt>{数字}</dt>
									<dd>数字，从0开始的整数，不能是负数或小数。</dd>

									<dt>{?}</dt>
									<dd>一个字符</dd>

									<dt>{*}</dt>
									<dd>任意长度的字符</dd>
								</dl>

								<input type="text" id="filenameformatcustom" class="form-control"
									name="filenameformatcustom" value="" placeholder="格式内容">
							</div>
						</div>


					</div>

				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" onclick="onFileRequirementAdd()">添加</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>


			</div>
		</div>
	</div>
	
<script>
	$('.form_date').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		forceParse : 0
	});

	$('.summernote').summernote({
		height : 300, // set editor height
		lang : 'zh-CN' // default: 'en-US'
	//  minHeight: null,             // set minimum height of editor
	//  maxHeight: null,             // set maximum height of editor

	//  focus: true,                 // set focus to editable area after initializing summernote
	});
</script>


<script type="text/javascript">
	var ID = 0;
	var NodeArray = [];

	function getNewID() {
		return ID++;
	}

	//取得层次
	function getLevel(parentNodeID) {

		if (parentNodeID == -1)
			return 0;

		var nLevel = 0;
		for (var i = 0; i < NodeArray.length; i++) {
			if (NodeArray[i].nodeID == parentNodeID)
				return 1 + getLevel(NodeArray[i].parentNodeID);
		}

		return 0;
	}

	function genNode(obj) {
		nodeID = obj.nodeID;
		parentNodeID = obj.parentNodeID;
		fileType = obj.fileType;
		fileTypeDescription = obj.fileTypeDescription;
		fileTypeData = obj.fileTypeData;
		fileFormat = obj.fileFormat;
		filenameRequirementVal = obj.filenameRequirementVal;
		fileCount = obj.fileCount;

		var s = "<a href='javascript:void(0);' class='list-group-item' onclick='onNodeClick(this)'><div id='FileNameContainer' style='margin-left:";

		var nLevel = getLevel(parentNodeID);

		s += nLevel * 50 + "px'><h4 class='list-group-item-heading'>";

		var isDirectory = fileType == '2';
		if (isDirectory) {
			s += "目录";
			s += "<button type='button' class='btn btn-default btn-xs' onclick='onAddChildNode(this)'>添加孩子</button>";
		} else {
			s += "文件";
		}

		s += "<button type='button' class='btn btn-default btn-xs' onclick='onDeleteNode(this)'>删除</button>";
		s += "</h4>";

		if (isDirectory == false) {
			s += "<div><span><strong>文件类型:</strong></span>"
					+ fileTypeDescription + "</div>";

			if (fileCount == -1) {
				s += "<div><span><strong>文件个数:</strong></span>不限制个数</div>";
			} else {
				s += "<div><span><strong>文件个数:</strong></span>" + fileCount
						+ "</div>";
			}
		}
		s += "<div><span><strong>文件名称格式:</strong></span>"
				+ filenameRequirementVal + "</div>";
		s += "<input type='hidden' name='fileTypeData' id='fileTypeData' value='"
					+ fileTypeData + "'/>";
		s += "<input type='hidden' name='nodeID' id='nodeID' value='"
					+ nodeID + "'/>";
		s += "<input type='hidden' name='parentNodeID' id='parentNodeID' value='"
				+ parentNodeID + "'/>";
		s += "</div></a>";

		if (parentNodeID == -1) {
			//添加在尾部即可
			$('#tree').append(s);
		} else {
			//插在父亲的节点位置
			var nodeIDs = $('#tree').find('input#nodeID');
			$(nodeIDs).each(function() {
				if ($(this).val() == parentNodeID) {
					var r = $(this).parent().parent();
					$(r).after(s);
				}
			});
		}
	}

	function genTree() {

		$('#tree').empty();

		for (var i = 0; i < NodeArray.length; i++) {
			genNode(NodeArray[i]);
		}
	}

	/**
	添加节点
	 */
	function addNode(obj) {

		NodeArray.push(obj);

		genTree();

	}

	//获得后代个数
	function getDescendantCount(nodeID) {
		var nCount = 0;
		for (var i = 0; i < NodeArray.length; i++) {
			if (NodeArray[i].parentNodeID == nodeID) {
				nCount += 1;
				nCount += getDescendantCount(NodeArray[i].nodeID);
			}
		}

		return nCount;
	}

	function deleteNode(nodeID) {
		for (var i = 0; i < NodeArray.length; i++) {
			if (NodeArray[i].nodeID == nodeID) {
				NodeArray.splice(i, 1 + getDescendantCount(nodeID));
				genTree();
				return;
			}
		}
	}

	//删除节点
	function onDeleteNode(e) {
		var rootA = $(e).parent().parent().parent();
		//nodeID
		var nodeID = $(rootA).find('input#nodeID').val();

		//parentNodeID
		var parentNodeID = $(rootA).find('input#parentNodeID').val();

		deleteNode(nodeID);

	}

	function onAddChildNode(e) {
		var rootA = $(e).parent().parent().parent();
		//nodeID
		var nodeID = $(rootA).find('input#nodeID').val();

		//parentNodeID
		var parentNodeID = $(rootA).find('input#parentNodeID').val();

		$('#addModal').find('.modal-body #parentID').val(nodeID);

		$('#addModal').modal('show');

	}

	function onAddRootNode() {

		$('#addModal').find('.modal-body #parentID').val(-1);

		$('#addModal').modal('show');

	}

	//节点点击
	function onNodeClick(e, nodeID) {
		$("#tree .list-group-item").removeClass('active');
		$(e).addClass('active');
	}
</script>

<script>
	function check() {

		var filerequirement_count = $(
				'input[name="filerequirement_count"]:checked').val();

		if (filerequirement_count == 0 && NodeArray.length == 0) {
			ShowErrMsg("请填写文件要求");
			return false;
		}

		return true;
	}
	function onAdd() {

		var sHTML = $('.summernote').summernote('code');
		
		$('#addcontentTextArea').text(sHTML);

		$('#FileRequirementData').val(JSON.stringify(NodeArray));

	}

	function FileType() {

	}

	function FileNameFormat() {

	}

	function onFileRequirementAdd() {

		//文件类型
		var filetype = $('#addModal').find(
				'.modal-body input[name="filetyperadio"]:checked').val();

		var fileCount = $('#addModal').find(
				'.modal-body input[name="filecount"]:checked').val();
		
	

		var fileTypeDescription = "";
		var fileTypeData = "";
		var filenameRequirementVal = "";
		var isDirectory = false;

		if (filetype == 0) {

			$('#addModal')
					.find('.modal-body input[name="filetype"]:checked')
					.each(
							function() {
								if ($(this).get(0).checked) {

									if (fileTypeDescription != "")
										fileTypeDescription += ";";
									if (fileTypeData != "")
										fileTypeData += ";";

									switch ($(this).val()) {
									case '0':
										fileTypeDescription += "word文件";
										fileTypeData += "*.doc;*.docx";
										break;
									case '1':
										fileTypeData += "*.xls;*.xlsx";
										fileTypeDescription += "excel文件";

										break;
									case '2':

										fileTypeData += "*.ppt;*.pptx";
										fileTypeDescription += "ppt文件";
										break;
									case '3':
										fileTypeData += "*.txt";
										fileTypeDescription += "文本文件(txt)";
										break;
									case '4':
										fileTypeData += "*.pdf";
										fileTypeDescription += "pdf文件(pdf)";
										break;
									case '5':
										fileTypeData += "*.c;*.h";
										fileTypeDescription += "c文件";
										break;
									case '6':
										fileTypeData += "*.cpp;*.c;*.h;*.cc;*.hh;*.hpp;*.hh";
										fileTypeDescription += "c++文件";
										break;
									case '7':
										fileTypeData += "*.java";
										fileTypeDescription += "java文件";
										break;
									case '8':
										fileTypeData += "*.zip";
										fileTypeDescription += "zip文件";
										break;
									case '9':
										fileTypeData += "*.rar";
										fileTypeDescription += "rar文件";
										break;

									}

									var s = $('#addModal').find(
											'.modal-body #filetypecustom')
											.val();

									if (s != "") {

										if (fileTypeData != "")
											fileTypeData += ";";

										fileTypeData += s;
										alert(fileTypeData);
									}

								}
							});

		} else if (filetype == 1) {
			fileTypeDescription = "不限制文件类型";
		} else {
			isDirectory = true;
		}

		//文件名称格式
		var fileformat = $('#addModal').find(
				'.modal-body input[name="filenameformatradio"]:checked').val();
		if (fileformat == 0) {
			var fileformatdefault = $('#addModal').find(
					'.modal-body input[name="filenameformat"]:checked').val();

			switch (fileformatdefault) {
			case '0':
				filenameRequirementVal = "{作业类型}_{作业名称}_{自然班}_{学号}_{姓名}";
				break;
			case '1':
				filenameRequirementVal = "{作业类型}_{作业名称}_{学号}_{姓名}";
				break;
			case '2':
				filenameRequirementVal = "{作业类型}_{自然班}_{学号}_{姓名}";
				break;
			case '3':
				filenameRequirementVal = "{作业名称}_{自然班}_{学号}_{姓名}";
				break;

			}
			//固定格式

		} else if (fileformat == 1) {
			//自定义格式
			filenameRequirementVal = $('#addModal').find(
					'.modal-body #filenameformatcustom').val();
		} else {
			//不限定格式
			filenameRequirementVal = "";
		}

		var parentID = $('#addModal').find('.modal-body #parentID').val();

		var obj = {
			"nodeID" : getNewID(),
			"parentNodeID" : parentID,
			"fileType" : filetype,
			"fileTypeDescription" : fileTypeDescription,
			"fileTypeData" : fileTypeData,
			"fileFormat" : fileformat,
			"filenameRequirementVal" : filenameRequirementVal,
			"fileCount" : fileCount
		};

		//添加项
		addNode(obj);

		//关闭对话框
		$('#addModal').modal('hide')

	}

	function onSearch() {
		var st = document.getElementById("SearchText").value;

		if (st != null && st.trim().length > 0) {
			var url = "select-" + st + ".html";

			window.location.href = url;
		} else
			ShowInfoMsg("搜索内容不能为空");

	}

	function onfiletyperadio(type) {
		if (type == 0) {
			//具体类型
			$('#addModal').find('.modal-body #filetypepanel0').show();
			$('#addModal').find('.modal-body #filetypepanel1').hide();
			$('#addModal').find('.modal-body #fileCountgroup').show();
		} else if (type == 1) {
			//不限制类型
			$('#addModal').find('.modal-body #filetypepanel1').show();
			$('#addModal').find('.modal-body #filetypepanel0').hide();
			$('#addModal').find('.modal-body #fileCountgroup').show();
		} else {
			//目录

			$('#addModal').find('.modal-body #filetypepanel1').hide();
			$('#addModal').find('.modal-body #filetypepanel0').hide();
			$('#addModal').find('.modal-body #fileCountgroup').hide();

		}
	}

	function onfilenameformatradio(type) {
		if (type == 0) {
			//具体类型
			$('#addModal').find('.modal-body #filenameformatpanel0').show();
			$('#addModal').find('.modal-body #filenameformatpanel1').hide();
		} else if (type == 1) {
			//自定义类型
			$('#addModal').find('.modal-body #filenameformatpanel1').show();
			$('#addModal').find('.modal-body #filenameformatpanel0').hide();
		} else {
			//不限制
			$('#addModal').find('.modal-body #filenameformatpanel1').hide();
			$('#addModal').find('.modal-body #filenameformatpanel0').hide();
		}
	}

	function onfilecountradio(type) {
		if (type == 0) {
			//具体类型
			$('#filerequirement_container').show();

		} else {
			//自定义类型
			$('#filerequirement_container').hide();

		}
	}
</script>
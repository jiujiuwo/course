<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 删除对话框 -->
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">询问</h4>
				</div>
				<div class="modal-body">

					<p class="text-info">
						是否真的删除<span class="text-danger" id="deleteinfo"></span>?
					</p>

				</div>
				<div class="modal-footer">
					<button id="deletebtn" onclick="" type="button"
						class="btn btn-default" data-dismiss="modal">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 错误信息对话框 -->
	<div class="modal fade" id="errorinfoModal" tabindex="-1" role="dialog"
		aria-labelledby="errorModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">错误</h4>
				</div>
				<div class="modal-body">
					<span class="text-danger" id="infomsg"></span>。
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 信息对话框 -->
	<div class="modal fade" id="infoModal" tabindex="-1" role="dialog"
		aria-labelledby="infoModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel"></h4>
				</div>
				<div class="modal-body">
					<span class="text-danger" id="infomsg"></span>。
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

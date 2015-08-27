function ShowInfoMsg(str) {	
	$('#errorinfoModal').find('.modal-body #infomsg').text(str);	
	$('#errorinfoModal').modal('show');
}

function ShowInfoMsg(title,str) {
	$('#infoModal').find('.modal-content #exampleModalLabel').text(title);
	$('#infoModal').find('.modal-body #infomsg').text(str);	
	$('#infoModal').modal('show');
}

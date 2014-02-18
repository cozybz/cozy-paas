<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
</head>
<body>
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Memory</th>
				<th>Status</th>
				<th>HostId</th>
				<th>UserId</th>
				<th>
				</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<script>
	
		$(function() {
			loadContainerList();
		});
		
		function loadContainerList(){
			$.ajax({
				type : 'POST',
				url : '${request.contextPath}/container/selectAll',
				data : null,
				dataType : 'json',
				success : function(data) {
					$('tbody').empty();
					$(data).each(function(j, item) {
						var $i = $('<button/>').attr('type','button').addClass('btn btn-xs btn-info').text('info');
						var $s = $('<button/>').attr('type','button').addClass('btn btn-xs btn-success');
						var $d = $('<button/>').attr('type','button').addClass('btn btn-xs btn-danger').text('delete');
						if(item.status==1){
							$s.text('start');
							$d.addClass('disabled');
						}else{
							$s.text('stop');
						}
						var $td = $('<td/>').append($i,' ',$d);
						$('<tr>').append($('<td/>').append($('<li/>').addClass('li-ellipsis').text(item.id)),
								$('<td/>').text(item.name),
								$('<td/>').text(item.memory),
								$('<td/>').append($s),
								$('<td/>').text(item.hostId),
								$('<td/>').text(item.userId),$td).appendTo($('tbody'));
					});
					
					$('button').click(function(){
						var id = $(this).parent().siblings().eq(0).text();
						switch($(this).text()){
						case 'info' :
							getContainerinfo(id);break;
						case 'delete' :
							deleteContainer(id);break;
						case 'start' :
							stopContainer(id);break;
						case 'stop' :
							startContainer(id);break;
						}
					});
				}
			});
		}
		
		function startContainer(id){
			$.ajax({
				type : 'POST',
				url : '${request.contextPath}/container/'+id+'/start',
				success : function(data) {
					loadContainerList();
				}
			});
		}
		function stopContainer(id){
			$.ajax({
				type : 'POST',
				url : '${request.contextPath}/container/'+id+'/stop',
				success : function(data) {
					loadContainerList();
				}
			});
		}
		function deleteContainer(id){
			$.ajax({
				type : 'POST',
				url : '${request.contextPath}/container/'+id+'/delete',
				success : function(data) {
					loadContainerList();
				}
			});
		}
		function getContainerinfo(c){
			
		}
	</script>
</body>
</html>
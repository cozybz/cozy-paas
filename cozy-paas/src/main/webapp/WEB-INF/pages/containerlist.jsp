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
				url : '${request.contextPath}/paas/container/selectAll',
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
						$('<tr>').append($('<td/>').attr('name',item.id).text(item.id.substr(0,25)+'...'),
								$('<td/>').text(item.name),
								$('<td/>').text(item.memory),
								$('<td/>').append($s),
								$('<td/>').text(item.hostId),
								$('<td/>').text(item.userId),$td).appendTo($('tbody'));
					});
					
					$('button').click(function(){
						var id = $(this).parent().siblings().eq(0).attr('name');
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
				url : '${request.contextPath}/paas/container/'+id+'/start',
				success : function(data) {
					loadContainerList();
				}
			});
		}
		function stopContainer(id){
			$.ajax({
				type : 'POST',
				url : '${request.contextPath}/paas/container/'+id+'/stop',
				success : function(data) {
					loadContainerList();
				}
			});
		}
		function deleteContainer(id){
			$.ajax({
				type : 'POST',
				url : '${request.contextPath}/paas/container/'+id+'/delete',
				success : function(data) {
					loadContainerList();
				}
			});
		}
		function getContainerinfo(id){
			$.ajax({
				type : 'POST',
				url : '${request.contextPath}/paas/container/'+id+'/info',
				success : function(data) {
					$('table').remove();
					
					var $ContainerID = $('<tr/>').append($('<td/>').text('ContainerID'),$('<td/>').text(data.ID.substr(0,25)+'...'));
					var $Image = $('<tr/>').append($('<td/>').text('Image'),$('<td/>').text(data.Config.Image));
					var $CPU = $('<tr/>').append($('<td/>').text('CPU'),$('<td/>').text(data.Config.CpuShares));
					var $Memory = $('<tr/>').append($('<td/>').text('Memory'),$('<td/>').text(data.Config.Memory));
					var $table1 = $('<div/>').addClass('col-md-6').append($('<table/>').addClass('table table-bordered table-hover').append($ContainerID,$Image,$CPU,$Memory));
					
					var $IPAddress = $('<tr/>').append($('<td/>').text('IPAddress'),$('<td/>').text(data.NetworkSettings.IPAddress));
					var $Hostname = $('<tr/>').append($('<td/>').text('Hostname'),$('<td/>').text(data.Config.Hostname));
					var $Gateway = $('<tr/>').append($('<td/>').text('Gateway'),$('<td/>').text(data.NetworkSettings.Gateway));
					var $Bridge = $('<tr/>').append($('<td/>').text('Bridge'),$('<td/>').text(data.NetworkSettings.Bridge));
					var $table2 = $('<div/>').addClass('col-md-6').append($('<table/>').addClass('table table-bordered table-hover').append($IPAddress,$Hostname,$Gateway,$Bridge));
					
					$('.main-content').append($table1,$table2);
					
					var Ports = data.NetworkSettings.Ports;
					var trs = new Array();
					for(key in Ports){
						for(i in Ports[key]){
							var $tr = $('<tr/>').append($('<td/>').text(key),$('<td/>').text(Ports[key][i].HostIp+':'+Ports[key][i].HostPort));
							trs.push($tr);
						}
					}
					var $table3 = $('<div/>').addClass('col-md-6').append($('<div/>').addClass('panel panel-info').append($('<div/>').addClass('panel-heading').text('Ports'),$('<div/>').addClass('panel-body').append($('<table/>').addClass('table table-hover').append($('<thead/>').append($('<tr/>').append($('<td/>').text('Port'),$('<td/>').text('Mapping'))),$('<tbody/>').append(trs)))));
					
					trs = [];
					for(i in data.Config.Env){
						var $tr = $('<tr/>').append($('<td/>').text(data.Config.Env[i].split('=')[0]),$('<td/>').attr('style','word-wrap:break-word; word-break:break-all;').text(data.Config.Env[i].split('=')[1]));
						trs.push($tr);
					}
					
					var $table4 = $('<div/>').addClass('col-md-6').append($('<div/>').addClass('panel panel-info').append($('<div/>').addClass('panel-heading').text('Environment'),$('<div/>').addClass('panel-body').append($('<table/>').addClass('table table-hover').append($('<thead/>').append($('<tr/>').append($('<td/>').text('Key'),$('<td/>').text('Value'))),$('<tbody/>').append(trs)))));
					
					$('.main-content').append($table1,$table2,$table3,$table4);
				}
			});
		}
	</script>
</body>
</html>
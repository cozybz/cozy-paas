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
				<th>IP</th>
				<th>
					<button type="button" class="btn btn-xs btn-primary" data-toggle="modal" data-target="#myModal">
						AddHost</button>
				</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel">Add Host</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal" role="form">
				<div class="form-group">
					<label for="inputHostIp" class="col-sm-2 control-label">HostIp</label>
					<div class="col-sm-10">
						<input name="ip" class="form-control"
							id="inputHostIp" placeholder="HostIp">
					</div>
				</div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">Save Host</button>
	      </div>
	    </div>
	  </div>
	</div>
	<script>
		$(function() {
			loadHostList();
		});
		
		function loadHostList(){
			$.ajax({
				type : 'POST',
				url : '${request.contextPath}/host/selectAll',
				data : null,
				dataType : 'json',
				success : function(data) {
					$('tbody').empty();
					$(data).each(function(j, item) {
						$('<tr>').append($('<td/>').text(item.id),
								$('<td/>').text(item.ip),'<td><button type="button" class="btn btn-xs btn-success">CreateTomcat</button> <button type="button" class="btn btn-xs btn-success">CreateMySQL</button> <button type="button" class="btn btn-xs btn-danger">delete</button></td>').appendTo($('tbody'));
					});
					
					$('button').click(function(){
						switch($(this).text()){
						case 'CreateTomcat' :
							var c = new Object();
							c.hostId = $(this).parent().siblings().eq(0).text();
							c.name='tomcat';
							c.memory=134217728;
							createContainer(c);break;
						case 'CreateMysql' :
							var c = new Object();
							c.hostId = $(this).parent().siblings().eq(0).text();
							c.name='mysql';
							c.memory=134217728;
							createContainer(c);break;
						case 'delete' :
							deleteHost($(this).parent().siblings().eq(0).text());break;
						case 'Save Host' :
							var c = new Object();
							c.ip = $('input').val();
							addHost(c);break;
						}
					});
				}
			});
		}
		
		function createContainer(c){
			$.ajax({
				type : 'POST',
				url : '${request.contextPath}/container/create',
				contentType: 'application/json',
			    mimeType: 'application/json',
				data : JSON.stringify(c),
				dataType : 'json',
				success : function(data) {
					loadHostList();
				}
			});
		}
		function addHost(c){
			$.ajax({
				type : 'POST',
				url : '${request.contextPath}/host/insert',
				contentType: 'application/json',
			    mimeType: 'application/json',
				data : JSON.stringify(c),
				dataType : 'json',
				success : function(data) {
					loadHostList();
					$('#myModal').modal('hide');
				}
			});
		}
		function deleteHost(id){
			$.ajax({
				type : 'POST',
				url : '${request.contextPath}/host/'+id+'/delete',
				success : function(data) {
					loadHostList();
					$('#myModal').modal('hide');
				}
			});
		}
	</script>
</body>
</html>
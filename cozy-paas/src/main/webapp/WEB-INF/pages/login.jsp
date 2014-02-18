<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>login</title>
<link rel="stylesheet" href="${request.contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${request.contextPath}/resources/css/cozy.css">
</head>
<body>

	<div class="container top-100">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title content-title">Login</h3>
				</div>
				<div class="panel-body top-20">
					<!-- content start-->
					<form action="${request.contextPath}/login" method="post"
						class="form-horizontal" role="form">
						<div class="form-group">
							<label for="inputEmail" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input name="name" type="email" class="form-control"
									id="inputEmail" placeholder="Email">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input name="password" type="password" class="form-control"
									id="inputPassword" placeholder="Password">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary">Sign in</button>
								<button type="reset" class="btn btn-default">Reset</button>
							</div>
						</div>
					</form>
					<!-- content end -->
				</div>
			</div>
		</div>
	</div>

	<script src="${request.contextPath}/resources/js/jquery-2.1.0.js"></script>
	<script src="${request.contextPath}/resources/js/bootstrap.js"></script>

</body>
</html>

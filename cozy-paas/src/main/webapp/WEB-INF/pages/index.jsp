<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>index</title>
<link rel="stylesheet" href="${request.contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${request.contextPath}/resources/css/cozy.css">
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">PAAS</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">${sessionScope.name}<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="logout">Log out</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="col-md-3">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Nav</h3>
				</div>
				<div class="panel-body">
					<ul class="nav nav-pills nav-stacked">
						<li><a href="#">Container</a></li>
						<li><a href="#">Host</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-md-9">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title content-title">Index</h3>
				</div>
				<div class="panel-body main-content">
					<!-- content start-->






					<!-- content end -->
				</div>
			</div>
		</div>
	</div>

	<script src="${request.contextPath}/resources/js/jquery-2.1.0.js"></script>
	<script src="${request.contextPath}/resources/js/bootstrap.js"></script>

	<script>
		$(function() {
			$(".nav-pills>li").click(function() {
				$(this).addClass("active").siblings().removeClass("active");
				$(".content-title").text($(this).children("a").text());
				$(".main-content").load('${request.contextPath}'+"/"+$(this).children("a").text())
			});
		});
	</script>

</body>
</html>

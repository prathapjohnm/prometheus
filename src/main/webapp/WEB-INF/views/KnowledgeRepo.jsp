<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Knowledge Repo</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container" ng-controller="searchController as ctrl">
		<hr />
		Showing Search Results:
		<hr />
		<div ng-repeat="s in ctrl.results">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<a href=s.url>
							<div class="panel-heading">
								<h3 class="panel-title">s.title</h3>
							</div>
							<div class="panel-body">s.description</div>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/searchService.js' />"></script>
	<script
		src="<c:url value='/static/js/controller/searchController.js' />"></script>
</body>
</html>
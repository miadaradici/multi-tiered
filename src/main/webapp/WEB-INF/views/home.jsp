<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html lang="en" ng-app="endavaQuizApp">
<head>
<title>Home</title>
<meta name="csrf-token" content="${_csrf.token}">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main.css" />
<link rel='stylesheet prefetch'
	href='http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css'>
<link href='http://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css' />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600'
	rel='stylesheet' type='text/css' />
<style>

</style>
</head>
<body>

	<div class="holder" id="main">
		<div class="header">
			<nav class="main-menu">
				<a class="main-btn" href="#quizzes"><i></i> Quizzes</a> <a
					class="main-btn" href="#evaluation"><i></i> Evaluation</a> <a
					class="main-btn"
					href="<c:url value="${pageContext.request.contextPath}/j_spring_security_logout" />">
					Logout</a>
			</nav>
		</div>
	</div>

	<div class="holder">
		<div class="content">
			<div>
				<ng-include
					src="'${pageContext.request.contextPath}/resources/app/views/main-content.html'">
				</ng-include>
				<toaster-container></toaster-container>
			</div>
		</div>

	</div>

	<div class="holder">
		<div class="page-footer">Multitired</div>
	</div>


</body>

<!-- Include scripts-->
<jsp:include page="scripts.jsp" />

</html>

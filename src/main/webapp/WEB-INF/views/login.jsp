<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html lang="en">
<head>
<title>Home</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link href='http://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600' rel='stylesheet' type='text/css'>
</head>
<body>
	<div style="width: 100%; margin: 0 padding: 0;">
		<div class="intro-section">
			<div id="popup" class="popup">
				<div class="login-form">
					<c:url value="/j_spring_security_check" var="loginUrl" />
					<form action="${loginUrl}" method="post"
						style="padding: 0; margin: 0;">
						<table
							style="margin: 0 auto; padding-top: 20px; padding-bottom: 20px;">
							<tr>
								<td colspan="3" valign="middle"><c:if
										test="${param.error != null}">
										<div class="alert alert-error">
											Failed to login.
											<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
												Reason: <c:out
													value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
											</c:if>
										</div>
									</c:if></td>
							</tr>
							<tr>
								<td valign="middle"><input type="text" placeholder="USER"
									id="username" name="j_username" /></td>

							</tr>
							<tr>
								<td valign="middle"><input type="password"
									placeholder="PASSWORD" id="password" name="j_password" /> <input
									type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" /></td>
							</tr>
						</table>
						<div class="login-footer">
							<input id="submit" class="btn" name="submit" type="submit"
								value="Login" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

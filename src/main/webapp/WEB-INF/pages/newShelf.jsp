<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Shelf</title>
</head>
<body>
<sec:authorize access="hasRole('ROLE_ADMIN')">
		<!-- For login user -->
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<h1>${message}</h1>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a href="admin"> Back </a> | <a
					href="javascript:formSubmit()"> Logout</a> 
			</h2>
		</c:if>
    	<div align="center">
	        <form:form action="saveShelf?ip=${ip}" method="post" modelAttribute="shelf">
	        <table>
	            <tr>
	                <td>No Of Ports:</td>
	                <td><form:input path="noOfPorts" /></td>
	            </tr>
	            <tr>
	                <td colspan="2" align="center"><input type="submit" value="Create Shelf"></td>
	                <td colspan="2" align="center"><input type="button" value="Back" onclick="javascript:history.go(-1)"></td>
	            </tr>
	        </table>
	        </form:form>
    	</div>
    </sec:authorize>
</body>
</html>
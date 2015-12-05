<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New User</title>
</head>
<body>
	<h1>Change Password</h1>
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
				Welcome : ${pageContext.request.userPrincipal.name}
				<sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin"/> 
				<c:choose>
  					 <c:when test="${isAdmin}"><a href="admin"> Back </a></c:when>
   					 <c:otherwise><a href="welcome"> Back </a></c:otherwise>
				</c:choose>
			</h2>
		</c:if>
    	<div align="center">
	        <form:form action="savePassword" method="post" modelAttribute="networkElement">
	        <table>
	            <tr>
	                <td>Password:</td>
	                <td><form:input path="password" type="password"/></td>
	            </tr>
	            <tr>
	                <td colspan="2" align="center"><input type="submit" value="Change Password"></td>
	            </tr>
	        </table>
	        </form:form>
    	</div>
</body>
</html>
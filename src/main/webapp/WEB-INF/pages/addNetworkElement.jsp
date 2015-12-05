<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add/Edit Network Element</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}
#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>

<body onload='document.neCreateForm.neId.focus();'>
	<h1>Create Network Element</h1>
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
			function createTextFields(){
				var noOfElements = document.getElementById("noOfShelfs").valueOf();
				var $holder = document.getElementById("tablePort");
				for (var shelfId = 1; shelfId <= noOfElements; shelfId++) {
					var $input = $('<input/>').attr({type: 'text', id: shelfId})
					$holder.append($input);
				}
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				Welcome : ${pageContext.request.userPrincipal.name} | <a href="admin"> Back </a>
			</h2>
		</c:if>
		
			 <div align="center">
					<c:if test="${error != null}">
						<div align="center">${error}</div>
					</c:if>
	        		<form:form name="neCreateForm" action="saveNetworkElement" method="post" modelAttribute="networkElement">
	        		<table>
	            		<form:hidden path="id"/>
	            		<tr>
	                		<td>Device IP:</td>
	                		<td><form:input name="neId" path="neId" /></td>
	            		</tr>
	            		<tr>
	                		<td colspan="2" align="center"><input type="submit" value="Save"></td>
	            		</tr>
	        		</table>
	        	</form:form>
    		</div>
    	</sec:authorize>
	</body>
</html>
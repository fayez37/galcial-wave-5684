<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>
<html>
<head>
	<title>Admin</title>
</head>
<body>
	<h2>${title}</h2>
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

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name}
			<a href="newUser">Create New User</a> | <a href="changePassword">Change Password</a> |
			<a href="javascript:formSubmit()"> Logout</a> </h3>
		</h2>
	</c:if>
	
	<div align="center">
            <table border="1">
            	<caption style="padding-bottom: 10px;"> <b>Complaint List </b> </caption>
            	<tr>
                <th>Id</th>
                <th>Created By</th>
                <th>Assigned To</th>
                <th>Status</th>
                </tr> 
                <c:forEach var="ticket" items="${ticketList}">
                <tr>
                    <td>${ticket.id}</td>
                    <td>${ticket.createdBy.emailId}</td>
                    <td>${ticket.assignedTo.emailId}</td>
                    <td>${ticket.status}</td>
                </tr>
                </c:forEach>             
            </table>
        </div>
	</body>
</html>
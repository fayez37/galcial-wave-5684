<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Welcome</title>
</head>
<body>
	<h2>${title}</h2>
	<sec:authorize access="hasRole('ROLE_USER')">
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

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} <a href="newTicket/0">Create New Ticket</a> |
				 <a href="changePassword"> Change Password</a> |
				 <a href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>
		<div align="center">
            <table border="1">
            	<caption style="padding-bottom: 10px;"> <b>My Issues</b> </caption>
            	<tr>
                <th>Id</th>
                <th>Created By</th>
                <th>Status</th>
                 </tr>
                <c:forEach var="ticket" items="${ticketList}">
                <tr>
                    <td>
                    	<a href="newTicket/${ticket.id}">${ticket.id}</a>
                    </td>
                    <td>${ticket.createdBy.emailId}</td>
                    <td>${ticket.status}</td>
                </tr>
                </c:forEach>             
            </table>
            <table border="1">
            	<caption style="padding-bottom: 10px;"> <b>Created Issues</b> </caption>
            	<tr>
                <th>Id</th>
                <th>Assigned To</th>
                <th>Status</th>
                 </tr>
                <c:forEach var="ticket" items="${createdticketList}">
                <tr>
                    <td>
                    	<a href="newTicket/${ticket.id}">${ticket.id}</a>
                    </td>
                    <td>${ticket.assignedTo.emailId}</td>
                    <td>${ticket.status}</td>
                </tr>
                </c:forEach>             
            </table>
        </div>
	</sec:authorize>
</body>
</html>
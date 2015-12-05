<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>
<html>
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
			
			<h3><a href="newNetworkElement">Add Network Element</a> |<a href="newUser">Create New User</a> | <a href="changePassword">Change Password</a> |
			<a href="javascript:formSubmit()"> Logout</a> </h3>
		</h2>
	</c:if>
	
	<div align="center">
            <table border="1">
            	<caption style="padding-bottom: 10px;"> <b>NETWORK ELEMENT </b> </caption>
                <th>Network Element IP</th>
                <th>Network Element Type</th>
                 <th>Network Element Version</th>
                <!-- <th>Network Element Name</th> -->
                <!-- <th>Managed By</th> -->
                <th>Action</th>
                 
                <c:forEach var="networkElement" items="${networkElementList}">
                <tr>
                    <td>
                    	<a href="viewShelfDetails?ip=${networkElement.neId}&neId=${networkElement.id}">${networkElement.neId}</a>
                    </td>
                    <%-- <td>${networkElement.neName}</td> --%>
                    <td>${networkElement.nodeType}</td>
                    <td>${networkElement.nodeVersion}</td>
                    <%-- <td>${networkElement.employee.emailId}</td> --%>
                    <td>
                        <a href="rescan?ip=${networkElement.neId}">Rescan</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteNetworkElement?neId=${networkElement.neId}">Delete</a>
                    </td>
                </tr>
                </c:forEach>             
            </table>
        </div>
	</body>
</html>
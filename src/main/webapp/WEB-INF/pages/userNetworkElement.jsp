<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
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
				User : ${pageContext.request.userPrincipal.name} | <a
					href="changePassword"> Change Password</a> |<a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>
		<div align="center">
            <table border="1">
            	<caption  style="padding-bottom: 10px;"> <b>NETWORK ELEMENT </b> </caption>
                <th>Network Element IP</th>
                 <th>Network Element Type</th>
                 <th>Network Element Version</th>
                <!-- <th>Network Element Name</th> -->
                <!-- <th>Managed By</th> -->
                <!-- <th>No Of Ports</th> -->
                <th>Action</th>
                 
                <c:forEach var="networkElement" items="${networkElementList}">
                <tr>
                    <td><a href="viewUserShelfDetails?ip=${networkElement.neId}&neId=${networkElement.id}">${networkElement.neId}</a></td>
                    <td>${networkElement.nodeType}</td>
                    <td>${networkElement.nodeVersion}</td>
                    <td> <a href="rescanUser?ip=${networkElement.neId}">Rescan</a> </td>
                    <%-- <td>${networkElement.employee.emailId}</td> --%>
                    <%-- <td>${networkElement.noOfPorts}</td> --%>
                    <%-- <td>
                    	<a href="viewUserShelfDetails?neId=${networkElement.id}&shelfId=1&noOfShelfs=${networkElement.noOfShelfs}&ip=${networkElement.neId}">View Shelf Details</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="unassignNetworkElement?neId=${networkElement.neId}">Un assign</a>
                    </td> --%>
                             
                </tr>
                </c:forEach>             
            </table>
        </div>
        
        <%-- <div align="center">
            <h1>All UnAssigned Network Element List</h1>
            <table border="1">
                <th>Network Element IP</th>
                <th>Network Element Name</th>
                <!-- <th>Managed By</th> -->
                <!-- <th>No Of Ports</th> -->
                <th>Action</th>
                 
                <c:forEach var="networkElement" items="${allNetworkElementList}">
                <tr>
                    <td>${networkElement.neId}</td>
                    <td>${networkElement.neName}</td>
                    <td>${networkElement.employee.emailId}</td>
                    <td>${networkElement.noOfPorts}</td>
                    <td>
                    	<a href="viewUserShelfDetails?neId=${networkElement.id}&shelfId=1&noOfShelfs=${networkElement.noOfShelfs}&ip=${networkElement.neId}">View Shelf Details</a>
                        <a href="assignNetworkElement?neId=${networkElement.neId}">Assign To Me</a>
                    </td>
                             
                </tr>
                </c:forEach>             
            </table>
        </div>
        
        <div align="center">
            <h1>All Used Network Element List</h1>
            <table border="1">
                <th>Network Element IP</th>
                <th>Network Element Name</th>
                <th>Managed By</th>
                <th>Action</th>
                <c:forEach var="networkElement" items="${allAssignedNetworkElementList}">
                <tr>
                    <td>${networkElement.neId}</td>
                    <td>${networkElement.neName}</td>
                    <td>${networkElement.employee.emailId}</td>
                    <td>
                    	<a href="viewUserShelfDetails?neId=${networkElement.id}&shelfId=1&noOfShelfs=${networkElement.noOfShelfs}&ip=${networkElement.neId}">View Shelf Details</a>
                    </td>
                </tr>
                </c:forEach>             
            </table>
        </div> --%>
	</sec:authorize>
</body>
</html>
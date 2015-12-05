<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
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

		<h2>${message}</h2>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				Welcome : ${pageContext.request.userPrincipal.name} | <a href="admin"> Back </a>
			</h2>
		</c:if>
		<div align="center">
			<table border="0">
				<caption>Shelfs</caption>
                <c:forEach var="shelfList" items="${shelfDetails}">
		            <th><a href="viewPortDetails?neId=${neId}&shelfId=${shelfList.id}&shelfDisplayName=${shelfList.shelfId}&ip=${ip}">${shelfList.shelfId}</a></th>
                </c:forEach>             
            </table>
        </div>
        <div align="center">
            <table border="1">
                <!-- <th>Shelf Id</th> -->
                <th>Port Name</th>
                <th>Managed By</th>
                <th>Assigned Time</th>
                <!-- <th>Action</th> -->
                 
                <c:forEach var="portList" items="${portDetails}">
                <tr>
                    <%-- <td>${portList.shelf.shelfId}</td> --%>
                    <td>${portList.portName}</td>
                    <td>${portList.employee.emailId}</td>
                    <td>${portList.portUseStartTime}</td>
                    <%-- <td>
                        <a href="unassignPortDetails?neId=${networkElement.neId}">Un assign</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </td> --%>
                             
                </tr>
                </c:forEach>             
            </table>

            <%-- <h1>All UnAssigned Network Element List</h1>
             <table border="1">
                <th>Shelf Id</th>
                <th>Port Name</th>
                <th>Managed By</th>
                <th>Action</th>
                 
                <c:forEach var="portList" items="${portList}">
                <tr>
                    <td>${portList.shelfId}</td>
                    <td>${portList.portName}</td>
                    <td>${portList.employee.emailId}</td>
                    <td>
                        <a href="assignNetworkElement?neId=${networkElement.neId}">Assign To Me</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                             
                </tr>
                </c:forEach>             
            </table>

            <h1>All Used Network Element List</h1>
             <table border="1">
                <th>Shelf Id</th>
                <th>Port Name</th>
                <th>Managed By</th>
                <c:forEach var="portList" items="${portList}">
                <tr>
                    <td>${portList.shelfId}</td>
                    <td>${portList.portName}</td>
                    <td>${portList.employee.emailId}</td>
                </c:forEach>             
            </table> --%>
        </div>
	</sec:authorize>
</body>
</html>
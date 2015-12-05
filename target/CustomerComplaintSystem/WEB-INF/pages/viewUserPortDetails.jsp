<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>
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

		<h2>${message}</h2>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a href="welcome"> Back </a>
			</h2>
		</c:if>
		<div align="center">
			<table border="0">
				<caption style="padding-bottom: 10px;"><b>Shelf</b></caption>
                <c:forEach var="shelfList" items="${shelfDetails}">
                    <th><a href="viewUserPortDetails?neId=${neId}&shelfId=${shelfList.id}&shelfDisplayName=${shelfList.shelfId}&ip=${ip}">${shelfList.shelfId}</a></th>
                </c:forEach>             
            </table>
         </div>
         
         <div style="padding-left: 100px">
            <table border="1" style="float: left">
            	<caption style="padding-bottom: 10px;"><b>Ports Used By Me </b></caption>
                <th>Port Name</th>
                <th>Assigned Time</th>
                <th>Action</th>
                <c:forEach var="portList" items="${portUsedByMe}">
                <tr>
                    <%-- <td>${portList.shelfId}</td> --%>
                    <td>${portList.portName}</td>
                    <td>${portList.portUseStartTime}</td>
                    <td>
                        <a href="unassignPort?portId=${portList.id}">Not Used</a>
                    </td>
                             
                </tr>
                </c:forEach>             
            </table>
          </div>
         
          <div style="padding-left: 500px">
             <table border="1" style="float: left">
             	<caption style="padding-bottom: 10px;"><b>Free Ports</b></caption>
                <!-- <th>Shelf Id</th> -->
                <th>Port Name</th>
                <!-- <th>Managed By</th> -->
                <th>Action</th>
                 
                <c:forEach var="portList" items="${allUnusedPorts}">
                <tr>
                    <%-- <td>${portList.shelfId}</td> --%>
                    <td>${portList.portName}</td>
                    <%-- <td>${portList.employee.emailId}</td> --%>
                    <td>
                        <a href="assignPort?portId=${portList.id}">Assign To Me</a>
                    </td>
                </tr>
                </c:forEach>             
            </table>
		</div>
		
		<div style="padding-left: 750px">
             <table border="1" style="float: left">
	             <caption style="padding-bottom: 10px;"><b>Used Ports</b></caption>
                <th>Port Name</th>
                <th>Managed By</th>
                <th>Assigned Time</th>
                <c:forEach var="portList" items="${allUsedPorts}">
                <tr>
                    <td>${portList.portName}</td>
                    <td>${portList.employee.emailId}</td>
                    <td>${portList.portUseStartTime}</td>
                </c:forEach>             
            </table>
        </div>
	</sec:authorize>
</body>
</html>
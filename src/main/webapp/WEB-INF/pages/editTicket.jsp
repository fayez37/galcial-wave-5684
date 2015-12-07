<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ticket Form</title>
</head>
<body>

<h1>Save/Update Ticket</h1>
<c:url var="saveUrl" value="/newTicket/${ticket.id}" />
<c:url var="saveComment" value="/newComment/${ticket.id}" />
<c:url var="welcome" value="/welcome" />
<form:form modelAttribute="ticket" method="POST" action="${saveUrl}">
	<div>
	<table>
		<tr>
			<td><form:label path="id">Id:</form:label></td>
			<td><form:input path="id" readonly="true"/></td>
		</tr>
		<tr>
			<td><form:label path="complaintArea">Complaint Area:</form:label></td>
			<td>
				<c:if test="${ticket.status == 'NEW'}">
					<form:select path="complaintArea" multiple="false">
						<form:options items="${complaintList}" />
					</form:select>
				</c:if> 
				<c:if test="${ticket.status != 'NEW'}">
					<form:input path="complaintArea" readonly="true"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td><form:label path="customer.emailId">Customer E-Mail Id:</form:label></td>
			<td>
				<c:if test="${ticket.status == 'NEW'}">
					<form:select path="customer.emailId" multiple="false">
						<form:options items="${customerEmailList}"/>
					</form:select>
				</c:if>
				<c:if test="${ticket.status != 'NEW'}">
					<form:input path="customer.emailId" readonly="true"/>
				</c:if>
			</td>
		</tr>
		<c:if test="${ticket.status != 'NEW'}">
			<tr>
				<td><form:label path="customer.name">Customer Name:</form:label></td>
			<td>
					<form:input path="customer.name" readonly="true"/>
			</td>
		</tr>
			<tr>
				<td><form:label path="customer.mobileNo">Customer Mobile No:</form:label></td>
				<td>
					<form:input path="customer.mobileNo" readonly="true"/>
				</td>
			</tr>
		</c:if>
		
		<tr>
			<td><form:label path="assignedTo.emailId">Assign To:</form:label></td>
			<td>
				<c:if test="${ticket.status != 'CLOSED'}">
					<form:select path="assignedTo.emailId" multiple="false">
						<c:forEach items="${employeeEmailList}" var="employeeEmailId">
        					<c:choose>
            					<c:when test="${employeeEmailId eq '${ticket.assignedTo.emailId}' }">
                					<form:option value="${employeeEmailId}" selected="true">${employeeEmailId} </form:option>
            					</c:when>
            					<c:otherwise>
                					<form:option value="${employeeEmailId}">${employeeEmailId}</form:option>
            					</c:otherwise>
        					</c:choose> 
    					</c:forEach>
						<%-- <form:options items="${employeeEmailList}" /> --%>
					</form:select>
				</c:if>
				<c:if test="${ticket.status == 'CLOSED'}">
					<form:input path="assignedTo.emailId" readonly="true"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td><form:label path="createdBy.emailId">Created By:</form:label></td>
			<td>
				<form:input path="createdBy.emailId" readonly="true"/>
			</td>
		</tr>
		<c:if test="${ticket.status != 'NEW'}">
			<tr>
			<td><form:label path="status">Status:</form:label></td>
			<td>
				<c:if test="${ticket.status != 'CLOSED'}">
				<form:select path="status" multiple="false">
					<form:options items="${statusList}" />
				</form:select>
				</c:if>
				<c:if test="${ticket.status == 'CLOSED'}">
					<form:input path="status" readonly="true"/>
				</c:if>
			</td>
		</tr>
		</c:if>
	</table>
	</div>
	<c:if test="${ticket.status != 'NEW'}">
	<div>
    	<table>
    		<caption style="padding-bottom: 10px;"><b>Comments</b></caption>
    		<c:forEach var="commentValue" items="${ticket.comments}">
    			<tr>
                    <td>Comment By : ${commentValue.employee.emailId}</td>
                    <td>Comment Time : ${commentValue.commentCreateTime}</td>
                </tr>
    			<tr>
                    <td>${commentValue.comments}</td>
                </tr>
                
    		</c:forEach>
        </table>
    </div>
    </c:if>
     <c:if test="${ticket.status != 'CLOSED'}">
    	<input type="submit" value="Save" />
    </c:if>
    <c:if test="${ticket.status == 'OPEN'}">
			<input type="button" value="Add Comment"  onclick="location.href='${saveComment}'"/>
		</c:if>
		<input type="button" value="Home" onclick="location.href='${welcome}'"/>
</form:form>
</body>
</html>
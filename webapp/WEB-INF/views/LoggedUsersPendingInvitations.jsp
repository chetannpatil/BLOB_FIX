<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ include file="Header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>lUSBPendingInvitations</title>
</head>
<body>

<h2 style="color: maroon;" align="center">${noPendingInvitationsMessage }</h2>
<h3 style="color: maroon;" align="center">${sentInvitationStatusMessage }</h3>
<h3 style="color: green;" align="center">${bfAcceptedMessage }</h3>
<c:if test="${!empty allPendingInvtSet }">

<table border='1' style="width:80%" align="center">
<tr>
<td style="color: green;">
Followings are your best friends requests.
</td>
</tr>
<c:forEach var="invitationBean" items="${allPendingInvtSet}">

<tr>
<td style="color: maroon;">
<s:form action="showOneUserProfile" >

<input type="hidden" name="oneUserEmail" value="${invitationBean.invitationFromUser.email }">
<input type="submit" value="${invitationBean.invitationFromUser.firstName }  ${invitationBean.invitationFromUser.lastName }"
 style="color: white;background-color: maroon;">
 <h3 style="color: maroon;">
${invitationBean.invitationFromUser.dob.toString().substring(0,10) } ${invitationBean.invitationFromUser.address.street } 
${invitationBean.invitationFromUser.address.city } 
${invitationBean.invitationFromUser.address.state } ${invitationBean.invitationFromUser.address.country } 
${invitationBean.invitationFromUser.email }
</h3>
</s:form>
</td>

<td width="75">
<form action="acceptAsBF" >
<input type="hidden" name="inviteId" value="${invitationBean.inviteId }">

<input type="submit" value="accept as BF" style="color: white;background-color: green;" >
</form>
</td>

<td width="75">
<form action="rejectBF">
<input type="hidden" name="inviteId" value="${invitationBean.inviteId }">
<input type="submit" value="reject" style="color: white;background-color: red">
</form>
</td>
</tr>
</c:forEach>
</table>
</c:if>



<c:if  test="${!empty allBFReqSentByLUSBSet }">
<hr >

<h3 style="color: maroon;" align="center">The BF Requests sent by you & needs receiver's approval</h3>
<table align="center" border="1">
<c:forEach var="invitationBean" items="${allBFReqSentByLUSBSet }">
<tr>
<td style="color: maroon;">
<s:form action="showOneUserProfile" >

<input type="hidden" name="oneUserEmail" value="${invitationBean.invitationToUser.email }">
<input type="submit" value="${invitationBean.invitationToUser.firstName }  ${invitationBean.invitationToUser.lastName }"
 style="color: white;background-color: maroon;">
</s:form>
</td>
<td>
<form action="cancellBFRequest" >
<input type="hidden" name="inviteId" value="${invitationBean.inviteId}">
<input type="submit" value="Take back BF proposal" style="color: white;background-color: red">
</form>
</td>
</tr>
</c:forEach>
</table>
</c:if>

</body>
</html>
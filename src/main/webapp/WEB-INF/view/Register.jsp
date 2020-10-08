<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BF REGISTERATION</title>
<style type="text/css">
#h1
{color: maroon;
}
</style>
</head>
<body>
<h1 align="center" class="h1" id="h1">Thanks for joining us </h1>
<pre>
<marquee >You just fill the following form 
& complete formal registration.
Then it is our responsible to find out your 
missed out best friends across oceans.
<h2>THANK YOU</h2>
</marquee>
</pre>


<%-- <s:form action="changeDp" > --%>
<!-- <input name="dp" type="file"/> -->
<!-- <input type="submit" value="UPLOAD DP"> -->
<%-- </s:form> --%>



<s:form action="register"  modelAttribute="userProfile">
<h1 align="center">ACCOUNT INFO :[MANDATORY FIELDS]</h1>
<table align="center" border="0" bordercolor="maroon">
<tr>
<td>
FIRST NAME
</td>
<td>
<s:input path="name"/> 
<s:errors path="name" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td colspan="2" align="center">
<input type="submit" value="sub mit details "/> 
</td>
</tr>


</table>
</s:form>


</body>
</html>
<%@ include file="Footer.jsp" %>
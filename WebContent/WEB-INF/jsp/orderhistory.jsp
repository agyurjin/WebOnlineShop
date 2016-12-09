<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Order History</h1>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<table>
	<tr>
		<th>NAME</th>
		<th>NUMBER</th>
		<th>STATUS</th>
	</tr>
<c:forEach items="${modellist}" var="modellist">
	<tr>
	   <th><c:out value="${modellist.name}"/></th>
   <th><c:out value="${modellist.number}"/></th>
   <th><c:out value="${modellist.status}"/></th>
   </tr>
</c:forEach>
</table>

<a href="/WebOnlineShop/user.do">
	<input type="button" 
			style="width:100px;height:35px;position:fixed;left:680px;top:10px" 
			value="Back" 
			name="logout"
	/>
</a>

<a href="/WebOnlineShop/registration.do">
	<input type="button" 
			style="width:100px;height:35px;position:fixed;left:800px;top:10px" 
			value="Log Out" 
			name="logout"
	/>
</a>

</body>
</html>
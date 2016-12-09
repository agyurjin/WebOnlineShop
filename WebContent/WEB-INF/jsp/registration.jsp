<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<form method="POST"> 
  Email Address:<br>
  <input type="text" name="emailaddress"><br>
  Password:<br>
  <input type="text" name="password"><br>
  
  <input type="submit" style="width:70px;height:35px;position:fixed;left:50px;top:95px" value="Log In" name="Submit"/>
</form>

<p style="position:fixed;left:55px;top:120px"><a href="/WebOnlineShop/form.do" >Sign Up</a></p>

</body>
</html>
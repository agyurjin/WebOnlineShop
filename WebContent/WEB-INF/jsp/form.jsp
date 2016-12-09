<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration form</title>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<form method="POST">
  First name:<br>
  <input type="text" name="firstName"><br>
  Last name:<br>
  <input type="text" name="lastName"><br>
  Email Address:<br>
  <input type="text" name="eMailAddress"><br>
  Password:<br>
  <input type="text" name="password"><br>
  Latitude:<br>
  <input type="text" name="latitude"><br>
  Longitude:<br>
  <input type="text" name="longitude"><br>
  Card Number:<br>
  <input type="text" name="cardNumber"><br>
  Card Balance:<br>
  <input type="text" name="balance"><br>
  
    <input type="submit" style="width:70px;height:35px;position:fixed;left:15px;top:325px" value="Log In" name="Submit"/>
  
</form>

<a href="/WebOnlineShop/registration.do">
	<input type="button" 
			style="width:70px;height:35px;position:fixed;left:95px;top:325px" 
			value="Back" 
			name="Submit"
	/>
</a>

</body>
</html>
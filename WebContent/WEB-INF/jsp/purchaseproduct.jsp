<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Purchase!</h1>

<form method="POST">
  Product id:<br>
  <input type="text" name="productid"><br>
  Quantity:<br>
  <input type="text" name="number"><br>

  <input type="submit" style="width:70px;height:35px;position:fixed;left:50px;top:165px" value="Order" name="Submit"/>
  
</form>

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
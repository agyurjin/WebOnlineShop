<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add product</title>
</head>
<body>
<form method="POST">
  Product name:<br>
  <input type="text" name="productName"><br>
  Quantity:<br>
  <input type="text" name="quantity"><br>
  Price:<br>
  <input type="text" name="price"><br>
  
    <input type="submit" style="width:70px;height:35px;position:fixed;left:50px;top:135px" value="Add" name="Submit"/>
</form>

<a href="/WebOnlineShop/admin.do">
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN</title>
</head>
<body>
<h1>Hello, admin!</h1>
<form action="/WebOnlineShop/admin/deliver.do">
	<input type="submit" 
			style="width:150px;height:35px;position:fixed;left:10px;top:70px" 
			value="Start delivering" 
			name="Submit"
	/>
</form>

<form action="/WebOnlineShop/admin/orderlist.do">
	<input type="submit" 
			style="width:150px;height:35px;position:fixed;left:10px;top:115px" 
			value="Orders to deliver" 
			name="submit"
	/>
</form>

<form action="/WebOnlineShop/admin/addproduct.do">
	<input type="submit" 
			style="width:150px;height:35px;position:fixed;left:10px;top:160px" 
			value="Add product" 
			name="submit"
	/>
</form>

<a href="/WebOnlineShop/registration.do">
	<input type="button" 
			style="width:100px;height:35px;position:fixed;left:800px;top:10px" 
			value="Log Out" 
			name="logout"
	/>
</a>

</body>
</html>
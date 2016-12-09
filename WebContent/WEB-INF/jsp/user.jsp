<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>USER</title>
</head>
<body>
<h1>Hello, ${model.message}!</h1>
<a href="/WebOnlineShop/user/purchase.do">
	<input type="submit" 
			style="width:150px;height:35px;position:fixed;left:10px;top:70px" 
			value="Purchase product" 
			name="submit"
	/>
</a>

<form action="/WebOnlineShop/user/productlist.do">
	<input type="submit" 
			style="width:150px;height:35px;position:fixed;left:10px;top:115px" 
			value="List of products" 
			name="submit"
	/>
</form>

<form action="/WebOnlineShop/user/history.do">
	<input type="submit" 
			style="width:150px;height:35px;position:fixed;left:10px;top:160px" 
			value="Order history" 
			name="submit"
	/>
</form>

<form action="/WebOnlineShop/user/delete.do">
	<input type="submit" 
			style="width:150px;height:35px;position:fixed;left:10px;top:205px" 
			value="Delete profile" 
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
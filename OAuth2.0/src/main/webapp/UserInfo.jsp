<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Info</title>
</head>
<body>
<h1>User Info From Facebook</h1>
<h3>First Name:</h3><p>${user.getFirst_name()}</p>
<h3>Last Name:</h3><p>${user.getLast_name()}</p>
<h3>Email:</h3><p>${user.getEmail()}</p>
<img alt="user Pic" src="${user.getUrl()}">
</body>
</html>
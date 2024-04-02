<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/homepage.css">
<title>Homepage</title>
</head>
<body>
    <header class="header">
		<h1 class="logo"><a href="#">secure-drive</a></h1>
      <ul class="main-nav">
          <li><a href="login.jsp">Login</a></li>
          <li><a href="#">Registration</a></li>
      </ul>
	</header> 
	<div class="container">
        <h1>Welcome to google drive clone</h1>
        <center><a href="<%= request.getContextPath() %>/FileController" class="upload-button" >Start browsing your files</a></center>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="welcome" method="post">
		<span>nombre del pais:</span> 
		<input type="text" name="countryname"> <br/>
		<span>nombre del idioma:</span> 
		<select name="lgname">
		<!--  repository.searchAllLanguages() -->
		</select><br/>
		<span>inserte idioma:</span> 
		<input type="text" name="lgname"> <br/>
		<input type="submit">
	</form>
	</body>
</html>
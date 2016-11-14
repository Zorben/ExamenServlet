<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.*,java.util.*,es.salesianos.model.*" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="" method="post">
	<input type="submit" value="ver listado">
</form>

<% 
 List<Idioma> idiomas = (List<Idioma>)request.getAttribute("listAllIdiomas");
 out.println(idiomas);
 pageContext.setAttribute("idiomas", idiomas);
%>
<br/>
<br/>
<br/>


</body>
</html>
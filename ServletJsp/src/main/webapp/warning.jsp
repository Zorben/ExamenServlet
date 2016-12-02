<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.*,java.util.*,es.salesianos.model.*,es.salesianos.repository.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aviso</title>
</head>
<body>
    
	<form action="delete" method="post">
	<p><h2> ¿SEGURO QUE QUIERE BORRAR EL IDIOMA? TAMBIÉN SE BORRARÁN SUS PAÍSES ASOCIADOS</h2></p>
	
	<input type="hidden" value="${param.id}" name="id2delete">
	<input type="submit" Value="Sí, quiero borrarlo">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" Value="Cancelar" onclick="window.location.href='/ServletJsp'">
	</form>



</body>
</html>
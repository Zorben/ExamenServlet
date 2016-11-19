<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,es.salesianos.model.*,es.salesianos.repository.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="" method="post"> <!-- NO servlet, fase 1: lista de idiomas pendiente  -->
		<span>nombre del pais:</span> 
		<input type="text" name="countryname"> <br/>
		<span>nombre del idioma:</span> 
		<select name="list">
		<option value=""></option>
		
		</select><br/>
		<span>inserte idioma:</span> 
		<input type="text" name="lgname"> <br/>
		<input type="submit">
	</form>
	<% 
		/* ~~PRUEBAS~~ 
		Repository repo = new Repository();
		List<Idioma> listaIdiomas = repo.listarIdiomas();
		Idioma lang = repo.buscarIdioma("Español");
		if(lang==null)
			out.println("idioma null");
		else
			out.println("ID: "+ lang.getId()+ "Nombre: "+ lang.getName());
		if(listaIdiomas.isEmpty())
			out.println("<a>La lista está vacía</a>");
		else
			out.println("<a>La lista está llena</a>"); 
		*/
		%>
	</body>
</html>
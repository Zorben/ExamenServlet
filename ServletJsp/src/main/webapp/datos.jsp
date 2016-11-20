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
	<form action="" method="post">
		<span><h2>INSERCIÓN DE PAISES</h2></span><hr>
		<span>Nombre del pais:</span> 
		<input type="text" name="countryname"> <br/>
		<span>Nombre del idioma:</span> 
		<select name="list">
		<option value="" selected></option>
		
			<% //LISTA IDIOMAS DE LA BD
				Repository repo = new Repository();
				List<Idioma> listaIdiomas = repo.listarIdiomas();
				if(null != listaIdiomas && !listaIdiomas.isEmpty()){
					for (Idioma idioma : listaIdiomas) {
						out.println("<option value="+idioma.getId()+">");
						out.println(idioma.getName());
						out.println("</option>");
					}
				}
			%>
		
		</select><br/>
		<span>Inserte idioma (Si no lo encuentra en la lista):</span> 
		<input type="text" name="lgname"> <br/>
		<input type="submit">
	</form>
	

	</body>
</html>
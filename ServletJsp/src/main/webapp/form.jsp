<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,es.salesianos.model.*,es.salesianos.service.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario</title>
</head>
<body>
	<form action="insert" method="post">
		<span><h2>INSERCIÓN DE PAISES</h2></span><hr>
		<span>Nombre del pais:</span> 
		<input type="text" name="countryname"> <br/>
		<span>Nombre del idioma:</span> 
		<select name="selectedlanguage">
		<option value="" selected></option>
		
			<% //LISTA IDIOMAS DE LA BD
				Service servicio = new Service();
				List<Language> listLangs = servicio.listAllLangs();
				if(null != listLangs && !listLangs.isEmpty()){
					for (Language lang : listLangs) {
						out.println("<option value="+lang.getLanguageName()+">");
						out.println(lang.getLanguageName());
						out.println("</option>");
					}
				}
			%>
		
		</select><br/>
		<span>Inserte idioma (Si no lo encuentra en la lista):</span> 
		<input type="text" name="writtenlanguage"> <br/>
		<input type="submit">
	</form>
	

	</body>
</html>
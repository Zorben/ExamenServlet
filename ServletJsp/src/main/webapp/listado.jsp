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

<% 
 List<Idioma> idiomas = (List<Idioma>)request.getAttribute("listAllLangs");
 pageContext.setAttribute("idiomas", idiomas);
%>



<table border="1">
<thead>
	<tr>
		<td><a>Borrar</a></td>
		<th>Idiomas</th>
		<th>País(es)</th>
	</tr>
</thead>
<tbody>
<%

if(null != idiomas && !idiomas.isEmpty()){
	Repository repo = new Repository();
	for (Idioma idioma1 : idiomas) {
		out.println("<tr>");
		out.println("<td style='text-align:center'>");
		out.println("<a href='aviso.jsp?id="+idioma1.getId()+"'>x</a>");
		out.println("</td>");
		out.println("<td>");
		out.println(idioma1.getName());
		out.println("</td>");
		out.println("<td>");
		out.println(repo.listaPaisesAString(repo.buscarPaisesPorIdioma(idioma1)));
		out.println("</td>");
		out.println("</tr>");
	}	
}
%>
</tbody>
</table>
</body>
</html>
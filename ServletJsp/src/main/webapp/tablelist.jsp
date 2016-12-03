<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.*,java.util.*,es.salesianos.model.*,es.salesianos.service.*,es.salesianos.utils.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

<% 
 List<Language> languages = (List<Language>)request.getAttribute("listAllLangs");
 pageContext.setAttribute("languages", languages);
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

if(null != languages && !languages.isEmpty()){
	Service service = new Service();
	Utilities utilidades;
	for (Language lang : languages) {
		out.println("<tr>");
		out.println("<td style='text-align:center'>");
		out.println("<a href='warning.jsp?id="+lang.getId()+"'>x</a>");
		out.println("</td>");
		out.println("<td>");
		out.println(lang.getLanguageName());
		out.println("</td>");
		out.println("<td>");
		out.println(utilidades.listCountriesToString(service.searchCountriesByLang(lang)));
		out.println("</td>");
		out.println("</tr>");
	}	
}
%>
</tbody>
</table>
</body>
</html>
package es.salesianos.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Country;
import es.salesianos.model.Language;
import es.salesianos.service.Service;
import es.salesianos.utils.*;


public class InsertServlet extends HttpServlet{
	
	
	private Service service = new Service();
	private Utilities utilidades = new Utilities();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Country countryForm = null;
		Language languageForm;
		
		String name_country = utilidades.formatString(req.getParameter("countryname"));
		String name_language_selected = req.getParameter("selectedlanguage");
		String name_language_written = utilidades.formatString(req.getParameter("writtenlanguage"));
		
		
		// Comprueba si los campos están correctamente rellenados y redirecciona si no es el caso
		check_errors(name_country,name_language_selected,name_language_written, req, resp);
		
		// Busqueda de campos proporcionados
			
			countryForm = service.searchCountry(name_country);
			if(countryForm.getcountryName() != null) // existe en la bd
				errorRedirection("El pais introducido ya existe, introduzca otro", req, resp);
			
			else{ // no existe en la db y se puede insertar
				
				if(name_language_written.isEmpty()){ // Si no se ha introducido idioma manualmente, se busca desplegable
					languageForm = service.searchLangById(Integer.parseInt(name_language_selected));
				}
				
				else{ // desplegable vacio, se busca idioma desde el campo manual
					languageForm = service.searchLangByString(name_language_written);
					if(languageForm.getlanguageName()!=null){
						errorRedirection("El idioma introducido ya existe, por favor utilice el desplegable.", req, resp);
					}
					else{
						//Idioma es valido y no existe en la bd
						service.insertLangByString(name_language_written);
						languageForm=service.searchLangByString(name_language_written);
					}
				}
				// PAIS E IDIOMA CORRECTOS
				countryForm = new Country();
				countryForm.setcountryName(name_country);
				countryForm.setLanguage(languageForm);
				service.insertCountry(countryForm);
				req.setAttribute("listAllLangs", service.listAllLangs());
				redirect("/tablelist.jsp", req, resp);
			}
			
	}
		

	protected void redirect(String destino, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destino);
		dispatcher.forward(req,resp);
	}
	
	
	/*
	 * PRE: 'campo' es un string que identifica el campo afectado: 'campo_pais', 'campo_idioma' o 'campo_ambos'
	 * POST: Informa al usuario del error cometido e inserta un boton que redirige al formulario inicial
	 */
	public void errorRedirection(String messaje, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		PrintWriter pw = resp.getWriter();
		pw.println("<br><br><a>" + messaje + "</a> &nbsp&nbsp&nbsp <input type='button' value='Volver' onclick=window.location.href='/ServletJsp'>");
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	// METODO QUE REDIRECCIONA EN LOS CASOS DE ERRORES DE CAMPOS VACIOS EN EL FORMULARIO
	public void check_errors(String a, String b, String c, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		if(a=="" && b=="" && c==""){
			errorRedirection("No ha introducido ningún país ni idioma.", req, resp);
			}
		else if(a=="" && (b!="" || c!="")){
			errorRedirection("No ha introducido ningún país.", req, resp);
		}
		else if(a!="" && b=="" && c==""){
			errorRedirection("No ha introducido ningún idioma.", req, resp);
		}
	}
	
	
	
}

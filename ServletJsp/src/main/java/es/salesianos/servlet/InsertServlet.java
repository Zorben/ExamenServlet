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
		
		// Captura de los parametros enviados por request
		Country countryForm;
		Language languageForm;
		String name_country = utilidades.formatString(req.getParameter("countryname"));
		String name_language_selected = req.getParameter("selectedlanguage");
		String name_language_written = utilidades.formatString(req.getParameter("writtenlanguage"));
		
		
		// Comprobación de errores en los campos introducidos por el request
		checkErrors(name_country,name_language_selected,name_language_written, req, resp);
		
		
		// Campos validos, consultas a la bd

		countryForm = service.searchCountry(name_country);
		
		// PAIS YA EXISTE
		if(countryForm.getCountryName() != null){
			errorRedirection("El pais introducido ya existe, introduzca otro", req, resp);
		}
		
		// PAIS NO EXISTE
		else{
			languageForm = checkLanguage(req, resp, name_language_selected, name_language_written);
			
			// PAIS E IDIOMA CORRECTOS, se procede a insertar el pais en la base de datos
			countryForm.setCountryName(name_country);
			countryForm.setLanguage(languageForm);
			service.insertCountry(countryForm);
			
			// atributo del request y redireccion
			req.setAttribute("listAllLangs", service.listAllLangs());
			redirect("/tablelist.jsp", req, resp);
		}
			
	} // end doPost


	
	
	
	/**
	 * PRE:
	 * 			Como mucho uno de los parametros String pueden estar vacio
	 * 			@param name_language_selected   -> nombre idioma desplegable del formulario de request
	 * 			@param name_language_written	-> nombre idioma textbox del formulario de request
	 * 			@param req
	 * 			@param resp
	 * 
	 * POST:
	 * 			Busca cual de ellos se puede utilizar para instanciar un objeto de la clase Language
	 * 			Realiza la insercion del idioma en la bdd en caso necesario
	 * 			
	 * 			@return LanguageForm			-> objeto de la clase Language
	 * 
	 */
	private Language checkLanguage(HttpServletRequest req, HttpServletResponse resp, String name_language_selected,
			String name_language_written) throws NumberFormatException, IOException {
		Language languageForm;
		
		// El nombre del idioma escrito en el campo tiene prioridad, se ignora desplegable
		if(name_language_written!=""){
			languageForm = service.searchLangByString(name_language_written);
			
			// Si no existe, hay que insertarlo y reasignarlo
			if(languageForm.getlanguageName()==null){
				service.insertLangByString(name_language_written);
				languageForm=service.searchLangByString(name_language_written);
			}
		}
		// Si no se ha introducido idioma manualmente, se busca desplegable
		else{ 
			languageForm = service.searchLangByString(name_language_selected);
		}
		return languageForm;
	}
		

	protected void redirect(String destino, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destino);
		dispatcher.forward(req,resp);
	}
	
	/**
	 * PRE: a, b y c son Strings que contienen lo enviado en el formulario por el request
	 * 		@param a  -> corresponde a lo introducido en el campo de introduccion de nombre del pais
	 * 		@param b  -> corresponde a lo que hay seleccionado en el desplegable del idioma
	 * 		@param c  -> corresponde a lo que hay introducido en el campo de introduccion de nombre de idioma
	 * 
	 * 
	 * POS: Si no se proporcionan los datos necesarios para realizar la insercion, se informa de ello
	 * 		redireccionando y permitiendo volver al formulario inicial
	 * 		Si todo esta correcto, no realiza nada
	 */
	public void checkErrors(String a, String b, String c, HttpServletRequest req, HttpServletResponse resp) throws IOException{
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
	
	
	/**
	 *  PRE:
	 * 		@param messaje -> mensaje que describe el error del usuario al enviar su formulario
	 * 	POST: Informa al usuario del error cometido e inserta un boton que redirige al formulario inicial
	 */
	public void errorRedirection(String messaje, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		PrintWriter pw = resp.getWriter();
		pw.println("<br><br><a>" + messaje + "</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "<input type='button' value='Volver' onclick=window.location.href='/ServletJsp'>");
	}
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	
}

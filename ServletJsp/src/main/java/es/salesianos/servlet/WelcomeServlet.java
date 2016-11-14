package es.salesianos.servlet;

import java.io.IOException;
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
import es.salesianos.model.Pais;
import es.salesianos.model.Idioma;
import es.salesianos.repository.Repository;


public class WelcomeServlet extends HttpServlet{
	
	Repository repository = new Repository();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Pais pais = new Pais();
		Idioma idioma = new Idioma();
		String nombrepais = req.getParameter("countryname");
		String nombreidioma = req.getParameter("lgname");
		String nombreidioma2 = req.getParameter("lgname2");
		List<Idioma> listaIdiomas = repository.searchAllLanguages();
		pais.setName("nombrepais");
		req.setAttribute("listAllIdiomas", listaIdiomas);
		
		if (repository.searchCountry(pais)==null){ // no existe el pais
			repository.insertPais(pais);
		}
		
		
		if (repository.searchLanguage(nombreidioma)!=null){ // existe el idioma
			req.setAttribute("pais", nombrepais);
			req.setAttribute("idioma", nombreidioma);
		}
			
		else{
			repository.insertIdioma(nombreidioma2); // inserta el idioma
		}
			
		redirect(req,resp);
	}

	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listado.jsp");
		dispatcher.forward(req,resp);
	}
	
	
	
	
}

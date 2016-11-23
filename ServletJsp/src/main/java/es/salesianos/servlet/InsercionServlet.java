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
import es.salesianos.model.Pais;
import es.salesianos.model.Idioma;
import es.salesianos.repository.Repository;


public class InsercionServlet extends HttpServlet{
	
	
	Repository repository = new Repository();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listAllLangs", repository.listarIdiomas());
		Pais paisFormulario = null;
		Idioma idiomaFormulario;
		
		String nombre_pais = repository.formatString(req.getParameter("countryname"));
		String nombre_idioma = req.getParameter("selectedlanguage");
		String nombre_idioma2 = repository.formatString(req.getParameter("writtenlanguage"));
		
		//CASUISTICA DE ERRORES DE CAMPOS VACIOS
		if(nombre_pais=="" && nombre_idioma=="" && nombre_idioma2=="")
			mensajeError("No ha introducido ningún país ni idioma.", req, resp);
		else if(nombre_pais=="" && (nombre_idioma!="" || nombre_idioma2!=""))
			mensajeError("No ha introducido ningún país.", req, resp);
		else if(nombre_pais!="" && nombre_idioma=="" && nombre_idioma2=="")
			mensajeError("No ha introducido ningún idioma.", req, resp);
		
		
		else{ /* Busqueda de campos proporcionados */
			
			paisFormulario = Repository.buscarPais(nombre_pais);
			if(paisFormulario.getName() != null) // existe en la bd
				mensajeError("El pais introducido ya existe, introduzca otro", req, resp);
			
			else{ // no existe en la db y se puede insertar
				
				if(nombre_idioma2.isEmpty()) // Si no se ha introducido idioma manualmente, se busca desplegable
					idiomaFormulario = Repository.buscarIdiomaPorId(Integer.parseInt(nombre_idioma));
				
				else{ // desplegable vacio, se busca idioma desde el campo manual
					idiomaFormulario = Repository.buscarIdioma(nombre_idioma2);
					if(idiomaFormulario.getName()!=null)
						mensajeError("El idioma introducido ya existe, por favor utilice el desplegable.", req, resp);
					
					//Idioma es valido y no existe en la bd
					repository.insertarIdioma(nombre_idioma2);
					idiomaFormulario=repository.buscarIdioma(nombre_idioma2);
				}
				// PAIS E IDIOMA CORRECTOS
				paisFormulario = new Pais();
				paisFormulario.setName(nombre_pais);
				paisFormulario.setIdioma(idiomaFormulario);
				repository.insertarPais(paisFormulario);
			}
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
	public void mensajeError(String mensaje, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		PrintWriter pw = resp.getWriter();
		pw.println("<br><br><a>" + mensaje + "</a> &nbsp&nbsp&nbsp <input type='button' value='Volver' onclick=window.location.href='/ServletJsp'>");
	}
	
	
	
	
	
}

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


public class BorradoServlet extends HttpServlet{
	
	
	Repository repository = new Repository();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		repository.borrarIdiomaPorId(Integer.parseInt(req.getParameter("idborrar")));
		redirect("/datos.jsp", req, resp);
		
		
	}

	protected void redirect(String destino, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destino);
		dispatcher.forward(req,resp);
	}
	
}

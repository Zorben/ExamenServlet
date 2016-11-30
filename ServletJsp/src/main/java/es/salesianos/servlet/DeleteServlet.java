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


public class DeleteServlet extends HttpServlet{
	
	
	Service service = new Service();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Service.deleteLangById(Integer.parseInt(req.getParameter("idtodelete")));
		redirect("/form.jsp", req, resp);
		
		
	}

	protected void redirect(String destino, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destino);
		dispatcher.forward(req,resp);
	}
	
}

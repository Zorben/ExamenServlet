package es.salesianos.service;

import es.salesianos.repository.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.salesianos.model.*;

public class Service {
	
	private static Repository repository = new Repository();

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	
	public static Pais searchCountry(String countryForm) {
		return repository.buscarPais(countryForm);
	}
	
	public static Idioma searchLangById(int id) {
		return repository.buscarIdiomaPorId(id);
	}
	
	public static void deleteLangById(int id) {
		repository.borrarIdiomaPorId(id);
	}
	
	public static Idioma searchLangByString(String LangName) {
		return repository.buscarIdioma(LangName);
	}
	
	public static void insertCountry(Pais countryForm) {
		repository.insertarPais(countryForm);
	}
	public static void insertLang(Idioma langForm) {
		repository.insertarIdioma(langForm);
	}
	
	
	public static void insertLangByString(String langName) {
		repository.insertarIdioma(langName);
	}

	public static List<Idioma> listAllLangs() {
		return repository.listarIdiomas();
	}
	
	public static List<Pais> searchCountriesByLang(Idioma language) {
		return repository.buscarPaisesPorIdioma(language);
	}
	
	public static List<Pais> searchCountriesByLangName(String language) {
		return repository.buscarPaisesPorIdioma(language);
	}
}

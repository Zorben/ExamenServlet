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
	
	public static Country searchCountry(String countryForm) {
		return repository.searchCountry(countryForm);
	}
	
	public static Language searchLangById(int id) {
		return repository.searchLanguageById(id);
	}
	
	public static void deleteLangById(int id) {
		repository.deleteLangById(id);
	}
	
	public static Language searchLangByString(String LangName) {
		return repository.searchLanguage(LangName);
	}
	
	public static void insertCountry(Country countryForm) {
		repository.insertCountry(countryForm);
	}
	public static void insertLang(Language langForm) {
		repository.insertLang(langForm);
	}
	
	
	public static void insertLangByString(String langName) {
		repository.insertLangByString(langName);
	}

	public static List<Language> listAllLangs() {
		return repository.listLangs();
	}
	
	public static List<Country> searchCountriesByLang(Language language) {
		return repository.searchCountriesByLang(language);
	}
	
	public static List<Country> searchCountriesByLangName(String language) {
		return repository.buscarPaisesPorIdioma(language);
	}
}

package es.salesianos.model;


public class Country{

	private int idCountry;
	private String countryName;
	private Language language;

	
	public int getId(){
		return idCountry;
	}
	public void setId(int id) {
		this.idCountry = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String name) {
		this.countryName = name;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	

}

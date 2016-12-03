package es.salesianos.model;


public class Language {

	private int idLanguage;
	private String languageName;

	public int getId(){
		return idLanguage;
	}
	public void setId(int id){
		this.idLanguage=id;
	}
	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String name) {
		this.languageName = name;
	}

	

}

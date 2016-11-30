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
	public String getName() {
		return languageName;
	}

	public void setName(String name) {
		this.languageName = name;
	}

	

}

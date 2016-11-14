package es.salesianos.model;


public class Pais{

	private int idpais;
	private String nombrepais;
	private Idioma idioma;

	
	public int getId(){
		return idpais;
	}
	public void setId(int id) {
		this.idpais = id;
	}

	public String getName() {
		return nombrepais;
	}

	public void setName(String name) {
		this.nombrepais = name;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	

}

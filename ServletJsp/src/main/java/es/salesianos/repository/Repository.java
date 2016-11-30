package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.h2.util.StringUtils;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Language;
import es.salesianos.model.Country;

public class Repository {
	
	// DEFINE LA RUTA A LA BASE DE DATOS DESDE EN EL PROYECTO
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/examenDB";
	
	static ConnectionManager manager = new ConnectionH2();
	

	/*	PRE: countryForm es un string con el nombre de un pais a buscar en la bd
	 *  POST: si countryForm existe en la bd, devuelve ese pais en forma de objeto de la clase PAIS
	 */ 
	public static Country searchCountry(String countryForm) {
		Country countryInDatabase= new Country();
		Language langInDatabase= new Language();
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM PAISES WHERE nombrePais = ?");
			prepareStatement.setString(1, countryForm);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				countryInDatabase = new Country();
				langInDatabase = new Language();
				countryInDatabase.setId(resultSet.getInt(1));
				countryInDatabase.setName(resultSet.getString(2));
				langInDatabase = searchLanguageById(resultSet.getInt(3));
				countryInDatabase.setLanguage(langInDatabase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
			
		}
		manager.close(conn);
		return countryInDatabase;
	}
	
	
	
	
	/*	PRE: id es la id del idioma a buscar en la bd
	 *  POST: si existe esa id en la tabla de idiomas de la bd, lo devuelve como objeto de la clase Language
	 */ 
	public static Language searchLanguageById(int id) {
		Language langInDatabase= new Language();
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM IDIOMAS WHERE idIdioma = ?");
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				langInDatabase.setId(resultSet.getInt(1));
				langInDatabase.setName(resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
			
		}
		manager.close(conn);
		return langInDatabase;
	}
	
	/*	PRE: id es la id del idioma a borrar de la bd
	 *  POST: si existe esa id en la tabla de idiomas de la bd, lo borra junto a sus paises asociados
	 */ 
	public static void deleteLangById(int id) {
		PreparedStatement prepareStatement = null;
		PreparedStatement prepareStatement2 = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("DELETE FROM PAISES WHERE idIdioma = ?");
			prepareStatement.setInt(1, id);
			prepareStatement2 = conn.prepareStatement("DELETE FROM IDIOMAS WHERE idIdioma = ?");
			prepareStatement2.setInt(1, id);
			prepareStatement.execute();
			prepareStatement2.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(prepareStatement);
			close(prepareStatement2);
			
		}
		manager.close(conn);
	}
	
	
	/*	PRE: langname es un string con el nombre del idioma a buscar en la bd
	 *  POST: si existe ese nombre en la tabla de idiomas de la bd, lo devuelve como objeto de la clase Language
	 */
	public static Language searchLanguage(String langname) {
		Language langInDatabase=new Language();
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM IDIOMAS WHERE nombreIdioma = ?");
			prepareStatement.setString(1, langname);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				langInDatabase.setId(resultSet.getInt(1));
				langInDatabase.setName(resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
			
		}
		manager.close(conn);
		return langInDatabase;
	}
	
	
	/*	PRE: countryForm es un objeto de la clase Pais a insertar en la bd
	 *  POST: se inserta un nuevo registro con los datos de countryForm en la tabla PAISES de la bd 
	 */
	public void insertCountry(Country countryForm) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO PAISES(nombrePais,idIdioma)" +
					"VALUES (?, ?)");
			preparedStatement.setString(1, countryForm.getName());
			preparedStatement.setInt(2, countryForm.getLanguage().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		manager.close(conn);
	}
	
	
	/*	PRE: lang es un objeto de la clase Idioma
	 *  POST: se inserta un nuevo registro con los datos de lang en la tabla Idiomas de la bd 
	 */
	public static void insertLang(Language lang) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO IDIOMAS(nombreIdioma)" +
					"VALUES (?)");
			preparedStatement.setString(1, lang.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		manager.close(conn);
	}
	
	/*	PRE: lang es un string con el nombre de un idioma
	 *  POST: se inserta un nuevo registro con el nombre lang en la tabla Idiomas de la bd 
	 */
	public static void insertLangByString(String lang) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO IDIOMAS(nombreIdioma)" +
					"VALUES (?)");
			preparedStatement.setString(1, lang);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		manager.close(conn);
	}

	/*
	 *  Devuelve una lista de objetos de la clase Language de todos los idiomas registrados en la bd
	 */
	public static List<Language> listLangs() {
		List<Language> listOfLangs= new ArrayList<Language>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM IDIOMAS");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Language IdiomaInDatabase = new Language();
				IdiomaInDatabase.setId(resultSet.getInt(1));
				IdiomaInDatabase.setName(resultSet.getString(2));
				listOfLangs.add(IdiomaInDatabase);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		
		
		manager.close(conn);
		return listOfLangs;
	}
	
	
	/*
	 * PRE:	language es un objeto de la clase Language
	 * POST: Devuelve una lista de objetos de la clase Country cuyo idioma coincida con language en la bd
	 */
	public static List<Country> searchCountriesByLang(Language language) {
		List<Country> listOfCountries= new ArrayList<Country>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM PAISES WHERE idIdioma=?");
			prepareStatement.setInt(1, language.getId());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Country countryInDatabase = new Country();
				countryInDatabase.setId(resultSet.getInt(1));
				countryInDatabase.setName(resultSet.getString(2));
				countryInDatabase.setLanguage(language);
				listOfCountries.add(countryInDatabase);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		
		manager.close(conn);
		return listOfCountries;
	}
	
	/*
	 *	PRE: language es un string con el nombre de un idioma
	 *	POST: Devuelve una lista de objetos de la clase Country cuyo idioma coincida con language en la bd
	 */
	public static List<Country> buscarPaisesPorIdioma(String language) {
		List<Country> listOfCountries= new ArrayList<Country>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM PAISES WHERE idIdioma=?");
			Language langInDatabase = searchLanguage(language);
			prepareStatement.setInt(1, langInDatabase.getId());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Country countryInDatabase = new Country();
				countryInDatabase.setId(resultSet.getInt(1));
				countryInDatabase.setName(resultSet.getString(2));
				countryInDatabase.setLanguage(langInDatabase);
				listOfCountries.add(countryInDatabase);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		
		manager.close(conn);
		return listOfCountries;
	}
	
	
	// FUNCION QUE CIERRA LA CONSULTA SQL
		private static void close(PreparedStatement prepareStatement) {
			try {
				prepareStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	// FUNCION QUE CIERRA LA TABLA QUE RECIBE EL RESULTADO DE LA CONSULTA SQL
		private static void close(ResultSet resultSet) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

}

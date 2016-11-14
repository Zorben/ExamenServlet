package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Idioma;
import es.salesianos.model.Pais;

public class Repository {
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/examenDB";
	ConnectionManager manager = new ConnectionH2();

	public Pais searchCountry(Pais paisFormulario) {
		Pais paisInDatabase= null;
		Idioma idiomaInDatabase=null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM PAISES WHERE nombrePais = ?");
			prepareStatement.setString(1, paisFormulario.getName());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				paisInDatabase = new Pais();
				idiomaInDatabase = new Idioma();
				paisInDatabase.setId(resultSet.getInt(0));
				paisInDatabase.setName(resultSet.getString(1));
				idiomaInDatabase = searchLanguageById(resultSet.getInt(2));
				paisInDatabase.setIdioma(idiomaInDatabase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
			
		}
		manager.close(conn);
		return paisInDatabase;
	}
	public Idioma searchLanguageById(int id) {
		Idioma idiomaInDatabase=null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM IDIOMAS WHERE idIdioma = ?");
			prepareStatement.setInt(1, idiomaInDatabase.getId());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				idiomaInDatabase.setId(resultSet.getInt(0));
				idiomaInDatabase.setName(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
			
		}
		manager.close(conn);
		return idiomaInDatabase;
	}
	public Idioma searchLanguage(String nombreidioma) {
		Idioma idiomaInDatabase=null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM IDIOMAS WHERE nombreIdioma = ?");
			prepareStatement.setString(1, nombreidioma);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				idiomaInDatabase.setId(resultSet.getInt(0));
				idiomaInDatabase.setName(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
			
		}
		manager.close(conn);
		return idiomaInDatabase;
	}
	

	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void insertPais(Pais paisFormulario) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO PAISES(nombrePais,idIdioma)" +
					"VALUES (?, ?)");
			preparedStatement.setString(1, paisFormulario.getName());
			preparedStatement.setInt(2, paisFormulario.getIdioma().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		manager.close(conn);
	}
	public void insertIdioma(String idiomaNombre) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO IDIOMAS(nombreIdioma)" +
					"VALUES (?)");
			preparedStatement.setString(1, idiomaNombre);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		manager.close(conn);
	}


	public List<Idioma> searchAllLanguages() {
		List<Idioma> listIdiomas= new ArrayList<Idioma>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM IDIOMAS");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Idioma IdiomaInDatabase = new Idioma();
				IdiomaInDatabase.setId(resultSet.getInt(0));
				IdiomaInDatabase.setName(resultSet.getString(1));
				listIdiomas.add(IdiomaInDatabase);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		
		
		manager.close(conn);
		return listIdiomas;
	}
	
	public List<Pais> searchCountriesByLanguage(Idioma language) {
		List<Pais> listPaises= new ArrayList<Pais>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM PAISES WHERE idIdioma=?");
			prepareStatement.setInt(1, language.getId());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Pais paisInDatabase = new Pais();
				paisInDatabase.setId(resultSet.getInt(0));
				paisInDatabase.setName(resultSet.getString(1));
				paisInDatabase.setIdioma(language);
				listPaises.add(paisInDatabase);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		
		manager.close(conn);
		return listPaises;
	}


}

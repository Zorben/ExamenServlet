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
	
	// DEFINE LA RUTA A LA BASE DE DATOS DESDE EN EL PROYECTO
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/examenDB";
	
	ConnectionManager manager = new ConnectionH2();
	

	/* 	PRE: paisFormulario es un string con el nombre de un pais a buscar en la bd
	 *  POST: si paisFormulario existe en la bd, devuelve ese pais en forma de objeto de la clase PAIS
	 */ 
	public Pais buscarPais(String paisFormulario) {
		Pais paisInDatabase= null;
		Idioma idiomaInDatabase=null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM PAISES WHERE nombrePais = ?");
			prepareStatement.setString(1, paisFormulario);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				paisInDatabase = new Pais();
				idiomaInDatabase = new Idioma();
				paisInDatabase.setId(resultSet.getInt(0));
				paisInDatabase.setName(resultSet.getString(1));
				idiomaInDatabase = buscarIdiomaPorId(resultSet.getInt(2));
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
	
	
	/* 	PRE: id es la id del idioma a buscar en la bd
	 *  POST: si existe esa id en la tabla de idiomas de la bd, lo devuelve como objeto de la clase idioma
	 */ 
	public Idioma buscarIdiomaPorId(int id) {
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
	
	
	/* 	PRE: nombreidioma es un string con el nombre del idioma a buscar en la bd
	 *  POST: si existe ese nombre en la tabla de idiomas de la bd, lo devuelve como objeto de la clase Idioma
	 */
	public Idioma buscarIdioma(String nombreidioma) {
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
	
	// FUNCION QUE CIERRA LA CONSULTA SQL
	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	// FUNCION QUE CIERRA EL TABLA QUE RECIBE EL RESULTADO DE LA CONSULTA SQL
	private void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	/* 	PRE: paisFormulario es un objeto de la clase Pais a insertar en la bd
	 *  POST: se inserta un nuevo registro con los datos de paisFormulario en la tabla PAISES de la bd 
	 */
	public void insertarPais(Pais paisFormulario) {
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
	
	
	/* 	PRE: idiomaNombre es un string con el nombre de un idioma
	 *  POST: se inserta un nuevo registro con el nombre idiomaNombre en la tabla Idiomas de la bd 
	 */
	public void insertarIdioma(String idiomaNombre) {
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
	
	/* 	PRE: lang es un objeto de la clase Idioma
	 *  POST: se inserta un nuevo registro con los datos de lang en la tabla Idiomas de la bd 
	 */
	public void insertarIdioma(Idioma lang) {
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

	/*
	 *  Devuelve una lista de objetos de la clase Idioma de todos los idiomas registrados en la bd
	 */
	public List<Idioma> listarIdiomas() {
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
	
	
	/*
	 * PRE: language es un objeto de la clase Idioma
	 * POST: Devuelve una lista de objetos de la clase Pais cuyo idioma coincida con language en la bd
	 */
	public List<Pais> buscarPaisesPorIdioma(Idioma language) {
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
	
	/*
	 * PRE: language es un string con el nombre de un idioma
	 * POST: Devuelve una lista de objetos de la clase Pais cuyo idioma coincida con language en la bd
	 */
	public List<Pais> buscarPaisesPorIdioma(String language) {
		List<Pais> listPaises= new ArrayList<Pais>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM PAISES WHERE idIdioma=?");
			Idioma idiomaInDatabase = buscarIdioma(language);
			prepareStatement.setInt(1, idiomaInDatabase.getId());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Pais paisInDatabase = new Pais();
				paisInDatabase.setId(resultSet.getInt(0));
				paisInDatabase.setName(resultSet.getString(1));
				paisInDatabase.setIdioma(idiomaInDatabase);
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

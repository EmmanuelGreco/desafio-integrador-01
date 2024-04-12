package com.educacionit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.dao.ds.ConnectionDB;
import com.educacionit.dao.model.Genero;
import com.educacionit.dao.model.Pelicula;

public class PeliculaDAOImpl implements PeliculaDAO, ConnectionDB {

	private static final String SELECT_BY_TITLE = "SELECT codigo, titulo FROM peliculas WHERE titulo LIKE ?";
	private static final String GET_BY_TITLE = "SELECT * FROM peliculas WHERE titulo LIKE ?";
	private static final String SELECT_ALL_MOVIES = "SELECT codigo, titulo FROM peliculas";
	private static final String GET_ALL_MOVIES = "SELECT * FROM peliculas";
	private static final String SHOW_DETAILS = "SELECT * FROM peliculas WHERE codigo = ?";
	
	@Override
	public void buscarPeliculasPorTitulo(String titulo) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_TITLE);
		preparedStatement.setString(1, "%" + titulo + "%");
		ResultSet resultSet = preparedStatement.executeQuery();
		Boolean hayRegistros = resultSet.next();
			
		if (hayRegistros == false) {
			System.out.println("\nNo se pudo encontrar la/s película/s que incluyen: " + titulo);
		} else {
			System.out.println("\nListado de película/s encontrada/s:");
			System.out.println("\n-----------------------------------------------");
			System.out.println("Código"	+ "	" + "| Título");
			System.out.println("-----------------------------------------------");
				
			// Se utiliza un DO-WHILE y no un while (resultSet.next());
			// ya que la variable hayRegistros corre el puntero un lugar al usar la misma función.
			do {
				Integer codigoBuscado = resultSet.getInt(1);
				String tituloBuscado = resultSet.getString(2);
				System.out.println(codigoBuscado + "	| " + tituloBuscado);				
			} while (resultSet.next());

		System.out.println("-----------------------------------------------");
		}
	}
	
	@Override
	public List<Pelicula> obtenerPeliculasPorTitulo(String titulo) throws SQLException {
		List<Pelicula> peliculas = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_TITLE);
		preparedStatement.setString(1, "%" + titulo + "%");
		ResultSet resultSet = preparedStatement.executeQuery();
			
		while (resultSet.next()) {				
			Pelicula pelicula = resultSetToPeliculaObtener(resultSet);
			peliculas.add(pelicula);
			}

		return peliculas;
	}
	
	@Override
	public void buscarTodasPeliculas() throws SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MOVIES);
		ResultSet resultSet = preparedStatement.executeQuery();			
		Boolean hayRegistros = resultSet.next();
			
		if (hayRegistros == false) {
			System.out.println("\nNo se pudo encontrar la/s película/s");
		} else {
			System.out.println("\nListado de película/s encontrada/s:");
			System.out.println("\n-----------------------------------------------");
			System.out.println("Código"	+ "	" + "| Título");
			System.out.println("-----------------------------------------------");
				
			// Se utiliza un DO-WHILE y no un while (resultSet.next());
			// ya que la variable hayRegistros corre el puntero un lugar al usar la misma función.
			do {
				Integer codigoBuscado = resultSet.getInt(1);
				String tituloBuscado = resultSet.getString(2);
				System.out.println(codigoBuscado + "	| " + tituloBuscado);				
			} while (resultSet.next());

		System.out.println("-----------------------------------------------");
		}
	}
	
	@Override
	public List<Pelicula> obtenerTodasPeliculas() throws SQLException {
		List<Pelicula> peliculas = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MOVIES);
		ResultSet resultSet = preparedStatement.executeQuery();
			
		while (resultSet.next()) {				
			Pelicula pelicula = resultSetToPeliculaObtener(resultSet);
			peliculas.add(pelicula);
		}

		return peliculas;
	}
	
	@Override
	public Pelicula mostrarDetallePelicula(int codigo) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SHOW_DETAILS);
		preparedStatement.setInt(1, codigo);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSetToPeliculaObtener(resultSet);
		}

		return null;
	}
	
	private Pelicula resultSetToPeliculaObtener(ResultSet resultSet) throws SQLException {
		Pelicula pelicula = new Pelicula();
		pelicula.setCodigo(resultSet.getInt("codigo"));
		pelicula.setTitulo(resultSet.getString("titulo"));
		pelicula.setDirector(resultSet.getString("director"));
		pelicula.setUrl(resultSet.getString("url"));
		pelicula.setImagen(resultSet.getBytes("imagen"));

		return pelicula;
	}
	
	@Override
	public void agregarPelicula(Pelicula pelicula) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		connection = getConnection();
		String queryP = "INSERT INTO peliculas (titulo, director, url, imagen) VALUES (?, ?, ?, ?)";
		statement = connection.prepareStatement(queryP, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1, pelicula.getTitulo());
		statement.setString(2, pelicula.getDirector());
		statement.setString(3, pelicula.getUrl());
		statement.setBytes(4, pelicula.getImagen());
		int rowsAffectedP = statement.executeUpdate();
		
		if (rowsAffectedP == 0) {
			System.out.println("No se pudo agregar la película: " + pelicula);
		}
			
		// Obtener el ID de la película recién insertada
		resultSet = statement.getGeneratedKeys();
		int peliculaId = -1;
		if (resultSet.next()) {
			peliculaId = resultSet.getInt(1);
		}

		// Insertar los géneros de la película en la tabla de películas_generos
		if (peliculaId != -1) {
			for (Genero genero : pelicula.getGeneros()) {
				String queryPG = "INSERT INTO peliculas_generos (codigoPelicula, idGenero) VALUES (?, ?)";
				statement = connection.prepareStatement(queryPG);
				statement.setInt(1, peliculaId);
				statement.setInt(2, genero.getId());
				int rowsAffectedPG = statement.executeUpdate();
				
				if (rowsAffectedPG == 0) {
					System.out.println("No se pudo agregar la relación película-géneros de la película: " + pelicula);
				}
			}
		}
	}
	
	@Override
	public void eliminarPelicula(int codigo) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
	
		connection = getConnection();
		// Eliminar primero las filas relacionadas en peliculas_generos
		String queryPG = "DELETE FROM peliculas_generos WHERE codigoPelicula = ?";
		statement = connection.prepareStatement(queryPG);
		statement.setInt(1, codigo);
		int rowsAffectedPG = statement.executeUpdate();

		if (rowsAffectedPG == 0) {
			System.out.println("No se pudo eliminar la relación película-géneros de la película con código: " + codigo);
		}

		// Luego eliminar la película en la tabla peliculas
		String queryP = "DELETE FROM peliculas WHERE codigo = ?";
		statement = connection.prepareStatement(queryP);
		statement.setInt(1, codigo);
		int rowsAffectedP = statement.executeUpdate();
		
		if (rowsAffectedP == 0) {
			System.out.println("No se pudo eliminar la película con código: " + codigo);
		}
	}
	
	@Override
	public void modificarPelicula(Pelicula pelicula) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;

		connection = getConnection();
		String queryP = "UPDATE peliculas SET titulo = ?, director = ?, url = ?, imagen = ? WHERE codigo = ?";
		statement = connection.prepareStatement(queryP);
		statement.setString(1, pelicula.getTitulo());
		statement.setString(2, pelicula.getDirector());
		statement.setString(3, pelicula.getUrl());
		statement.setBytes(4, pelicula.getImagen());
		statement.setInt(5, pelicula.getCodigo());
		int rowsAffectedP = statement.executeUpdate();
		
		if (rowsAffectedP == 0) {
			System.out.println("No se pudo modificar la película: " + pelicula);
		}

		// Eliminar los géneros de la película en la tabla de películas_generos
		String queryPG = "DELETE FROM peliculas_generos WHERE codigoPelicula = ?";
		statement = connection.prepareStatement(queryPG);
		statement.setInt(1, pelicula.getCodigo());
		int rowsAffectedPG = statement.executeUpdate();
		
		if (rowsAffectedPG == 0) {
			System.out.println("No se pudo modificar la relación película-géneros de la película: " + pelicula);
		}

		// Insertar los nuevos géneros de la película en la tabla de películas_generos
		for (Genero genero : pelicula.getGeneros()) {
			queryPG = "INSERT INTO peliculas_generos (codigoPelicula, idGenero) VALUES (?, ?)";
			statement = connection.prepareStatement(queryPG);
			statement.setInt(1, pelicula.getCodigo());
			statement.setInt(2, genero.getId());
			statement.executeUpdate();
				
			if (rowsAffectedPG == 0) {
				System.out.println("No se pudo modificar la relación película-géneros de la película: " + pelicula);
			}
		}
	}
}

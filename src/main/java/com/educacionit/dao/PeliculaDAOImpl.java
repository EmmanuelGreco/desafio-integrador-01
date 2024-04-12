package com.educacionit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.dao.ds.ConnectionDB;
import com.educacionit.dao.model.Pelicula;

public class PeliculaDAOImpl implements PeliculaDAO, ConnectionDB {

	private static final String SELECT_BY_TITLE = "SELECT codigo, titulo FROM peliculas WHERE titulo LIKE ?";
	private static final String GET_BY_TITLE = "SELECT * FROM peliculas WHERE titulo LIKE ?";
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
}

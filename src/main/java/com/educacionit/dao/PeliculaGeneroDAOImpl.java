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
import com.educacionit.exceptions.DBManagerException;

public class PeliculaGeneroDAOImpl implements PeliculaGeneroDAO, ConnectionDB {
	
	private static final String SELECT_BY_GENRE = "SELECT p.* FROM peliculas p INNER JOIN peliculas_generos pg ON p.codigo = pg.codigoPelicula INNER JOIN generos g ON pg.idGenero = g.id WHERE g.nombre LIKE ? order by 1";
    private static final String SELECT_GENRES_BY_MOVIEID = "SELECT g.* FROM generos g INNER JOIN peliculas_generos pg ON g.id = pg.idGenero WHERE pg.codigoPelicula = ?";
    
	@Override
	public void buscarPeliculasPorGenero(String genero) throws DBManagerException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_GENRE)) {
			preparedStatement.setString(1, "%" + genero + "%");
			ResultSet resultSet = preparedStatement.executeQuery();			
			Boolean hayRegistros = resultSet.next();
			
			if (hayRegistros == false) {
				System.out.println("\nNo se pudo encontrar la/s película/s que incluyen: " + genero);
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
		} catch (SQLException sqlExcex) {
			throw new DBManagerException(DBManagerException.ERROR_4,
					"No fue posible listar la/s película/s por género debido a la siguiente causa: " + sqlExcex.getMessage(), sqlExcex);
		}
	}
	
	@Override
	public List<Pelicula> obtenerPeliculasPorGenero(String genero) throws DBManagerException {
		List<Pelicula> peliculas = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_GENRE)) {
			preparedStatement.setString(1, "%" + genero + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {				
				Pelicula pelicula = resultSetToPeliculaObtener(resultSet);
				peliculas.add(pelicula);
			}
		} catch (SQLException sqlExce) {
			throw new DBManagerException(DBManagerException.ERROR_5,
					"No fue posible obtener la/s película/s por género debido a la siguiente causa: " + sqlExce.getMessage(), sqlExce);
		}
		return peliculas;
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
    public List<Genero> obtenerGenerosPorPeliculaCodigo(int codigo) throws DBManagerException {
        List<Genero> generos = new ArrayList<>();
        try (Connection connection = getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GENRES_BY_MOVIEID)) {
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
            	Genero genero = new Genero();
            	genero.setId(resultSet.getInt("id"));
            	genero.setNombre(resultSet.getString("nombre"));
            	generos.add(genero);
            }
        } catch (SQLException sqlEcc) {
        	throw new DBManagerException(DBManagerException.ERROR_13,
					"No fue posible obtener el/los género/s por la siguiente causa: " + sqlEcc.getMessage(), sqlEcc);
        }
        return generos;
    }
}

package com.educacionit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.dao.ds.ConnectionDB;
import com.educacionit.dao.model.Genero;

public class PeliculaGeneroDAOImpl implements PeliculaGeneroDAO, ConnectionDB {
	
	private static final String SELECT_GENRES_BY_MOVIEID = "SELECT g.* FROM generos g INNER JOIN peliculas_generos pg ON g.id = pg.idGenero WHERE pg.codigoPelicula = ?";
	
    @Override
    public List<Genero> obtenerGenerosPorPeliculaCodigo(int codigo) throws SQLException {
        List<Genero> generos = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GENRES_BY_MOVIEID);
        preparedStatement.setInt(1, codigo);
        ResultSet resultSet = preparedStatement.executeQuery();
            
        while (resultSet.next()) {
          	Genero genero = new Genero();
           	genero.setId(resultSet.getInt("id"));
           	genero.setNombre(resultSet.getString("nombre"));
           	generos.add(genero);
            }

        return generos;
    }
}

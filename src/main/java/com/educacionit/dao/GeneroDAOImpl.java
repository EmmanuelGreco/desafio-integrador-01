package com.educacionit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.dao.ds.ConnectionDB;
import com.educacionit.dao.model.Genero;
import com.educacionit.exceptions.DBManagerException;

public class GeneroDAOImpl implements GeneroDAO, ConnectionDB {
	
    private static final String SELECT_ALL_GENRES = "SELECT * FROM generos";
    
    @Override
    public List<Genero> obtenerTodosGeneros() throws DBManagerException {
        List<Genero> generos = new ArrayList<>();
        try (Connection connection = getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GENRES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
            	Genero genero = new Genero();
                genero.setId(resultSet.getInt("id"));
                genero.setNombre(resultSet.getString("nombre"));
                generos.add(genero);
            }
        } catch (SQLException sqlExx) {
        	throw new DBManagerException(DBManagerException.ERROR_12,
					"No fue posible obtener todos los géneros por la siguiente causa: " + sqlExx.getMessage(), sqlExx);
        }
        return generos;
    }
}

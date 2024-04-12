package com.educacionit.dao;

import java.sql.SQLException;
import java.util.List;

import com.educacionit.dao.model.Genero;
import com.educacionit.dao.model.Pelicula;

public interface PeliculaGeneroDAO {
	
	public void buscarPeliculasPorGenero(String genero) throws SQLException;
	List<Pelicula> obtenerPeliculasPorGenero(String genero) throws SQLException;
	List<Genero> obtenerGenerosPorPeliculaCodigo(int codigo) throws SQLException;

}

package com.educacionit.dao;

import java.sql.SQLException;
import java.util.List;

import com.educacionit.dao.model.Pelicula;

public interface PeliculaDAO {
	
	public void buscarPeliculasPorTitulo(String titulo) throws SQLException;
	List<Pelicula> obtenerPeliculasPorTitulo(String titulo) throws SQLException;	
	Pelicula mostrarDetallePelicula(int codigo) throws SQLException;

}
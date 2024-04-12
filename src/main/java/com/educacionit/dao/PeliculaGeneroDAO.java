package com.educacionit.dao;

import java.util.List;

import com.educacionit.dao.model.Genero;
import com.educacionit.dao.model.Pelicula;
import com.educacionit.exceptions.DBManagerException;

public interface PeliculaGeneroDAO {
	
	public void buscarPeliculasPorGenero(String genero) throws DBManagerException;
	List<Pelicula> obtenerPeliculasPorGenero(String genero) throws DBManagerException;
	List<Genero> obtenerGenerosPorPeliculaCodigo(int codigo) throws DBManagerException;
}

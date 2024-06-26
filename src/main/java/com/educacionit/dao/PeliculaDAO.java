package com.educacionit.dao;

import java.util.List;

import com.educacionit.dao.model.Pelicula;
import com.educacionit.exceptions.DBManagerException;

public interface PeliculaDAO {
	
	public void buscarPeliculasPorTitulo(String titulo) throws DBManagerException;
	List<Pelicula> obtenerPeliculasPorTitulo(String titulo) throws DBManagerException;
	void buscarTodasPeliculas() throws DBManagerException;
	List<Pelicula> obtenerTodasPeliculas() throws DBManagerException;
	//Pelicula mostrarDetallePelicula(int codigo) throws DBManagerException;
	// Este método implementado permitía mostrar el detalle de películas incluso no filtradas, 
	// pero se grababa en una lista que ya habia sido grabada anteriormente (mal optimizado).
	void agregarPelicula(Pelicula pelicula) throws DBManagerException;
	void eliminarPelicula(int codigo) throws DBManagerException;
	void modificarPelicula(Pelicula pelicula) throws DBManagerException;
}

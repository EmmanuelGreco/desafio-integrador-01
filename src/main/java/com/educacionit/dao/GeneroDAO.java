package com.educacionit.dao;

import java.util.List;

import com.educacionit.dao.model.Genero;
import com.educacionit.exceptions.DBManagerException;

public interface GeneroDAO {
	
	List<Genero> obtenerTodosGeneros() throws DBManagerException;
}

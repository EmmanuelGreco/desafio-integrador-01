package com.educacionit.dao;

import java.sql.SQLException;
import java.util.List;

import com.educacionit.dao.model.Genero;

public interface GeneroDAO {
	
	List<Genero> obtenerTodosGeneros() throws SQLException;
}

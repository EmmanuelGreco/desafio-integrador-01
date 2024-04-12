package com.educacionit.exceptions;

public class DBManagerException extends Exception {
	
	private static final long serialVersionUID = 1L;
	public static final int ERROR_1 = 1;
	public static final int ERROR_2 = 2;
	public static final int ERROR_3 = 3;
	public static final int ERROR_4 = 4;
	public static final int ERROR_5 = 5;
	public static final int ERROR_6 = 6;
	public static final int ERROR_7 = 7;
	public static final int ERROR_8 = 8;
	public static final int ERROR_9 = 9;
	public static final int ERROR_10 = 10;
	public static final int ERROR_11 = 11;
	public static final int ERROR_12 = 12;
	public static final int ERROR_13 = 13;

	private Integer error;

	public DBManagerException (Integer error, String message) {
		super(message);
		this.error = error;
	}

	/*public DBManagerException (Integer error, Throwable cause) {
		super(cause);
		this.error = error;
	}*/

	public DBManagerException (Integer error, String message, Throwable cause) {
		super(message, cause);
		this.error = error;
	}
	
	/*
	 * Los errores siguen el orden de las funciones y métodos del MAIN.
	 * 
	 * Error 1: conectar a la DB
	 * Error 2: buscarPeliculasPorTitulo
	 * Error 3: obtenerPeliculasPorTitulo 
	 * Error 4: buscarPeliculasPorGenero
	 * Error 5: obtenerPeliculasPorGenero
	 * Error 6: buscarTodasPeliculas
	 * Error 7: obtenerTodasPeliculas
	 * Error 8: mostrarDetallePelicula
	 * Error 9: agregarPelicula
	 * Error 10: eliminarPelicula
	 * Error 11: modificarPelicula
	 * Error 12: obtenerTodosGeneros
	 * Error 13: obtenerGenerosPorPeliculaCodigo
	 */	

	@Override
	public String getMessage() {
		switch (error) {
		case ERROR_1:
			return "Se produjo un error al conectar con la base de datos MySQL. Error: " + super.getMessage();
		case ERROR_2:
			return "Se produjo un error al buscar película/s por título. Error: " + super.getMessage();
		case ERROR_3:
			return "Se produjo un error al obtener película/s por título. Error: " + super.getMessage();
		case ERROR_4:
			return "Se produjo un error al buscar película/s por género. Error: " + super.getMessage();
		case ERROR_5:
			return "Se produjo un error al obtener película/s por género. Error: " + super.getMessage();
		case ERROR_6:
			return "Se produjo un error al buscar todas las películas. Error: " + super.getMessage();
		case ERROR_7:
			return "Se produjo un error al obtener todas las películas. Error: " + super.getMessage();
		case ERROR_8:
			return "Se produjo un error al mostrar el detalle de la película. Error: " + super.getMessage();
		case ERROR_9:
			return "Se produjo un error al agregar unapelícula. Error: " + super.getMessage();
		case ERROR_10:
			return "Se produjo un error al eliminar una película. Error: " + super.getMessage();
		case ERROR_11:
			return "Se produjo un error al modificar una película. Error: " + super.getMessage();
		case ERROR_12:
			return "Se produjo un error al obtener todos los géneros. Error: " + super.getMessage();
		case ERROR_13:
			return "Se produjo un error al obtener el/los género/s. Error: " + super.getMessage();
		default:
			return super.getMessage();
		}
	}
}

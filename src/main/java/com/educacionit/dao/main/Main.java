package com.educacionit.dao.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.educacionit.dao.GeneroDAO;
import com.educacionit.dao.GeneroDAOImpl;
import com.educacionit.dao.PeliculaDAO;
import com.educacionit.dao.PeliculaDAOImpl;
import com.educacionit.dao.PeliculaGeneroDAO;
import com.educacionit.dao.PeliculaGeneroDAOImpl;
import com.educacionit.dao.model.Genero;
import com.educacionit.dao.model.Pelicula;
import com.educacionit.exceptions.DBManagerException;

public class Main {
	
	private static final String CREADOR = 
			  "\n                                       By Emmanuel Greco®                                       ";
	private static final String BIENVENIDO = 
			    "                  ============================================================                  \n"
			+ "\n                         Bienvenido al software de gestión de películas!                        ";
	private static final String HOME =
			  "\n                  ============================================================                  \n"
			+ "\n¿Qué desea hacer? (Elija una opción numérica):"
			+ "\n--------------------------------------------------------------------------------------------------"
			+ "\n1) Buscar película/s por título."
			+ "\n2) Obtener película/s por título (permite ver información detallada de la película a seleccionar)."
			+ "\n3) Buscar película/s por género."
			+ "\n4) Obtener película/s por género (permite ver información detallada de la película a seleccionar)."
			+ "\n5) Buscar todas las películas."
			+ "\n6) Obtener todas las películas (permite ver información detallada de la película a seleccionar)."
			+ "\n7) Agregar película."
			+ "\n8) Eliminar película."
			+ "\n9) Modificar película."
			+ "\n10) Salir."
			+ "\n--------------------------------------------------------------------------------------------------";
	private static final String VOLVERHOME = "\n¡Volvemos al HOME!";
	private static final PeliculaDAO peliculaDAO = new PeliculaDAOImpl();
	private static final GeneroDAO generoDAO = new GeneroDAOImpl();
	private static final PeliculaGeneroDAO peliculaGeneroDAO = new PeliculaGeneroDAOImpl();
	private static final Scanner scanner = new Scanner(System.in);
	private static final String HASTA_LUEGO =
			  "\n=======================================  ¡Hasta luego!  ========================================"
			+ "\n                                       By Emmanuel Greco®                                       ";
			
	public static void main(String[] args) throws DBManagerException {
		System.out.println(CREADOR);
		System.out.println(BIENVENIDO);
		Integer opcion;
		Boolean opcionValida = false;
		boolean continuar = true;
		
		do {
			System.out.println(HOME);
			opcion = validarNumero("Seleccione índice del 1 al 10. Opción elegida: ");
			opcionValida = (opcion >=1 && opcion <=10);

			switch (opcion) {
				case 1:
					buscarPeliculasPorTitulo();
					break;
				case 2:
					obtenerPeliculasPorTitulo();
					break;
				case 3:
					buscarPeliculasPorGenero();
					break;
				case 4:
					obtenerPeliculasPorGenero();
					break;
				case 5:
					buscarTodasPeliculas();
					break;
				case 6:
					obtenerTodasPeliculas();
					break;
	            case 7:
	                agregarPelicula();
	                break;
	            case 8:
	                eliminarPelicula();
	                break;
	            case 9:
	                modificarPelicula();
	                break;
	            case 10:
	                System.out.print(HASTA_LUEGO);
	                continuar = false;
	                break;
				default:
					System.out.println("\nError! Opción no válida. Por favor, seleccione una opción válida.\n");
			}
		} while (!opcionValida || continuar);
		scanner.close();
	}
	
	private static void buscarPeliculasPorTitulo() throws DBManagerException {
		System.out.print("\nIngrese el título (o parte) de la/s película/s que desea buscar: ");
		String titulo = scanner.nextLine();
		peliculaDAO.buscarPeliculasPorTitulo(titulo);
		System.out.println(VOLVERHOME);
	}
	
	private static void obtenerPeliculasPorTitulo() throws DBManagerException {
		System.out.print("\nIngrese el título (o parte) de la/s película/s que desea obtener: ");
		String titulo = scanner.nextLine();
		List<Pelicula> peliculasEncontradas = peliculaDAO.obtenerPeliculasPorTitulo(titulo);
		mostrarPeliculasObtenidas(peliculasEncontradas);
	}
	
	private static void buscarPeliculasPorGenero() throws DBManagerException {
		System.out.print("\nIngrese el género (o parte) de la/s película/s que desea buscar: ");
		String genero = scanner.nextLine();
		peliculaGeneroDAO.buscarPeliculasPorGenero(genero);
		System.out.println(VOLVERHOME);
	}

	private static void obtenerPeliculasPorGenero() throws DBManagerException {
		System.out.print("\nIngrese el género (o parte) de la/s película/s que desea obtener: ");
		String genero = scanner.nextLine();
		List<Pelicula> peliculasEncontradas = peliculaGeneroDAO.obtenerPeliculasPorGenero(genero);
		mostrarPeliculasObtenidas(peliculasEncontradas);
	}
	
	private static void buscarTodasPeliculas() throws DBManagerException {
		peliculaDAO.buscarTodasPeliculas();
		System.out.println(VOLVERHOME);
	}
	
	private static void obtenerTodasPeliculas() throws DBManagerException {
		List<Pelicula> peliculasEncontradas = peliculaDAO.obtenerTodasPeliculas();
		mostrarPeliculasObtenidas(peliculasEncontradas);
	}
	
	private static void mostrarPeliculasObtenidas(List<Pelicula> peliculas) throws DBManagerException {
		if (peliculas.isEmpty()) {
			System.out.println("\nNo se pudo encontrar la/s película/s!");
			System.out.println(VOLVERHOME);
			return;
		}

		System.out.println("\nListado de película/s obtenida/s:");
		System.out.println("\n-----------------------------------------------");
		System.out.println("Código"	+ "	" + "| Título");
		System.out.println("-----------------------------------------------");
		for (Pelicula pelicula : peliculas) {			
			System.out.println(pelicula.getCodigo() + "	| " + pelicula.getTitulo());
		}

		System.out.println("-----------------------------------------------");
		System.out.print("\n¿Desea ver los detalles de alguna película? (S para continuar / N para volver al HOME): ");
		String opcion;
		
		do {
			opcion = scanner.nextLine();

			if (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N")) {
				System.out.print("Entrada inválida! Por favor, ingrese (S para continuar / N para volver al HOME): ");
			 } 
		} while (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N"));
	          
		Integer codigo;
		Boolean opcionValida = false;
		
		if (opcion.equalsIgnoreCase("S")) {
			do {
				codigo = validarNumero("\nIngrese el código de la película que desea ver, aunque no se haya filtrado (0 para cancelar): ");
				opcionValida = true;
			} while (!opcionValida);
			
			if (codigo == 0) {
				System.out.println(VOLVERHOME);
				return;
			}
			mostrarDetallePelicula(codigo);
		}
		System.out.println(VOLVERHOME);
	}

	private static void mostrarDetallePelicula(int codigo) throws DBManagerException {
		Pelicula pelicula = peliculaDAO.mostrarDetallePelicula(codigo);
		if (pelicula == null) {
			System.out.println("\nLa película con código: " + codigo + " no existe en la base de datos MySQL!");
			return;
		}

		System.out.println("\nDetalles de la película seleccionada: ");
		System.out.println("---------------------------------------------------------------------");
		System.out.println("Código: " + pelicula.getCodigo());
		System.out.println("Título: " + pelicula.getTitulo());
		System.out.println("Director: " + pelicula.getDirector());
		System.out.println("URL: " + pelicula.getUrl());		
		//System.out.println("Imagen: " + Arrays.toString(pelicula.getImagen()));
		// El profesor pidió que no mostremos la imagen por consola.		
		System.out.println("Género/s listado/s:");
		List<Genero> generos = peliculaGeneroDAO.obtenerGenerosPorPeliculaCodigo(codigo);
		for (Genero genero : generos) {
			System.out.println("- " + genero.getNombre());
		}
		System.out.println("---------------------------------------------------------------------");			
	}
		
	private static void agregarPelicula() throws DBManagerException {
	    System.out.print("\nIngrese el título de la película: ");
	    String titulo = scanner.nextLine();
	    System.out.print("Ingrese el/la director/a de la película: ");
	    String director = scanner.nextLine();
	    System.out.print("Ingrese la URL de la película: ");
	    String url = scanner.nextLine();
	    System.out.print("¿Desea agregar la imagen de la película ahora? (S para SI / N para NO): ");
	    byte[] imagen = obtenerImagenConsulta(scanner);
      
	    // Obtener los géneros disponibles en la base de datos, mostrarlos y luego seleccionarlos
	    List<Genero> generosDisponibles = generoDAO.obtenerTodosGeneros();
	    List<Genero> generosSeleccionados = generosDisponiblesYgenerosSeleccionados(generosDisponibles);
	    
	    // Luego, utiliza el método addMovie de MovieDAO para agregar la película a la base de datos
	    Pelicula nuevaPelicula = new Pelicula(titulo, director, url, imagen, generosSeleccionados);
	    peliculaDAO.agregarPelicula(nuevaPelicula);
	    System.out.println("\nPelícula agregada correctamente!");
	    System.out.println(VOLVERHOME);
	    return;
	}
  
	private static void eliminarPelicula() throws DBManagerException  {
		System.out.println("\nListado de todas las películas:");
		System.out.println("\nCódigo"	+ "	" + "| Título");
		System.out.println("-----------------------------------------------");
		List<Pelicula> peliculas = peliculaDAO.obtenerTodasPeliculas();
		for (Pelicula pelicula : peliculas) {
			System.out.println(pelicula.getCodigo() + "	| " + pelicula.getTitulo());
		}
		
		Integer codigo;
		Boolean opcionValida = false;
		
		do {
			codigo = validarNumero("\nIngrese el código de la película que desea eliminar (0 para cancelar): ");
			opcionValida = true;
		} while (!opcionValida);

		if (codigo == 0) {
			System.out.println(VOLVERHOME);
			return;
		}
		Pelicula peliculaSeleccionada = peliculaDAO.mostrarDetallePelicula(codigo);
		if (peliculaSeleccionada == null) {
			System.out.println("No se encontró la película con el código " + codigo + "!");
			System.out.println(VOLVERHOME);
			return;
		}
		
		// Utiliza el método deleteMovie de MovieDAO para eliminar la película de la base de datos
		peliculaDAO.eliminarPelicula(codigo);
		System.out.println("\nPelícula eliminada correctamente!");
		System.out.println(VOLVERHOME);
		return;
	}

	private static void modificarPelicula() throws DBManagerException  {
		System.out.println("\nListado de todas las películas:");
		System.out.println("\nCódigo"	+ "	" + "| Título");
		System.out.println("-----------------------------------------------");
		List<Pelicula> peliculas = peliculaDAO.obtenerTodasPeliculas();
		for (Pelicula pelicula : peliculas) {
			System.out.println(pelicula.getCodigo() + "	| " + pelicula.getTitulo());
		}

		Integer codigo;
		Boolean opcionValida = false;
		
		do {
			codigo = validarNumero("\nIngrese el código de la película que desea modificar (0 para cancelar): ");
			opcionValida = true;
		} while (!opcionValida);
		
		if (codigo == 0) {
			System.out.println(VOLVERHOME);
			return;
		}
		// Obtener la película actual para mostrar sus detalles
		Pelicula peliculaSeleccionada = peliculaDAO.mostrarDetallePelicula(codigo);
		if (peliculaSeleccionada == null) {
			System.out.println("No se encontró la película con el código " + codigo + "!");
			System.out.println(VOLVERHOME);
			return;
		}

		System.out.print("Ingrese el nuevo título de la película: ");
		String nuevoTitulo = scanner.nextLine();
		System.out.print("Ingrese el/la nuevo/a director/a de la película: ");
		String nuevoDirector = scanner.nextLine();
		System.out.print("Ingrese la nueva URL de la película: ");
		String nuevaUrl = scanner.nextLine();
		System.out.print("¿Desea modificar la imagen de la película ahora? (S para SI / N para NO): ");
		byte[] nuevaImagen = obtenerImagenConsulta(scanner);

		// Obtener los géneros disponibles en la base de datos, mostrarlos y luego seleccionarlos
		List<Genero> generosDisponibles = generoDAO.obtenerTodosGeneros();
		List<Genero> generosSeleccionados = generosDisponiblesYgenerosSeleccionados(generosDisponibles);

		// Modificada la película con los datos facilitados
		Pelicula peliculaModificada = new Pelicula(codigo, nuevoTitulo, nuevoDirector, nuevaUrl, nuevaImagen, generosSeleccionados);
		peliculaDAO.modificarPelicula(peliculaModificada);
		System.out.println("\nPelícula modificada correctamente!");
		System.out.println(VOLVERHOME);
		return;
	}
  
	private static List<Genero> generosDisponiblesYgenerosSeleccionados(List<Genero> generosDisponibles) {
		System.out.println("Géneros disponibles:");
		for (int i = 0; i < generosDisponibles.size(); i++) {
			System.out.println((i + 1) + "- " + generosDisponibles.get(i).getNombre());
		}

		List<Genero> generosSeleccionados = new ArrayList<>();
		boolean entradaValida;

		do {
	        System.out.print("Seleccione el/los género/s para la película (numéricos, separados por coma): ");
	        String[] seleccion = scanner.nextLine().split(",");
	        entradaValida = true;
	        generosSeleccionados.clear();
      
	        for (String selec : seleccion) {
	        	try {
	        		int index = Integer.parseInt(selec.trim()) - 1;
	        		if (index >= 0 && index < generosDisponibles.size()) {
	        			generosSeleccionados.add(generosDisponibles.get(index));
	        		} else {
	        			System.out.println("Número fuera de rango. Por favor, seleccione números del 1 al 15!");
	        			entradaValida = false;
	        			break;
	        		}
	        	} catch (NumberFormatException NumExcep) {
	        		System.out.println("Entrada inválida. Por favor, ingrese números separados por coma!");
	        		entradaValida = false;
	        		break;
	        	}
	        }
		} while (!entradaValida);
		return generosSeleccionados;
	}
  
	private static byte[] obtenerImagenConsulta(Scanner scanner) {
		String opcionImagen;
		
		do {
			opcionImagen = scanner.nextLine();            
			if (!opcionImagen.equalsIgnoreCase("S") && !opcionImagen.equalsIgnoreCase("N")) {
				System.out.print("Entrada inválida! Por favor, ingrese (S para SI / N para NO):");
			}
		} while (!opcionImagen.equalsIgnoreCase("S") && !opcionImagen.equalsIgnoreCase("N"));

		byte[] imagen;

		if (opcionImagen.equalsIgnoreCase("S")) {
			System.out.print("Ingrese la ruta del archivo de imagen: ");
			String rutaImagen = scanner.nextLine();
			imagen = leerArchivoComoBytes(rutaImagen);
		} else {
			imagen = null;
		}
		return imagen;
	}
	
	private static byte[] leerArchivoComoBytes(String rutaArchivo) {
		try {
			Path archivo = Paths.get(rutaArchivo);
			return Files.readAllBytes(archivo);
		} catch (IOException IOExcep) {
			System.out.println("No se pudo cargar la imagen.");
			return null;
		}
	}

	public static Integer validarNumero(String mensaje) {
		boolean numeroValido = false;
		Integer numero = 0;

		while (!numeroValido) {
			System.out.print(mensaje);

			if (scanner.hasNextInt()) {
				numero = scanner.nextInt();
				numeroValido = true;
			} else {
				System.out.print("Error! Opción no válida. Por favor, seleccione una opción válida.\n");
				scanner.next();
			}
		}
		scanner.nextLine();
		return numero;
	}
}

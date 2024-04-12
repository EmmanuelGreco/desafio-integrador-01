package com.educacionit.dao.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.educacionit.dao.PeliculaDAO;
import com.educacionit.dao.PeliculaDAOImpl;
import com.educacionit.dao.PeliculaGeneroDAO;
import com.educacionit.dao.PeliculaGeneroDAOImpl;
import com.educacionit.dao.model.Genero;
import com.educacionit.dao.model.Pelicula;

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
	private static final PeliculaGeneroDAO peliculaGeneroDAO = new PeliculaGeneroDAOImpl();
	private static final Scanner scanner = new Scanner(System.in);
	private static final String HASTA_LUEGO =
			  "\n=======================================  ¡Hasta luego!  ========================================"
			+ "\n                                       By Emmanuel Greco®                                       ";
			
	public static void main(String[] args) throws SQLException {
		System.out.println(CREADOR);
		System.out.println(BIENVENIDO);
		boolean continuar = true;
		while (continuar) {
			System.out.println(HOME);
            System.out.print("Opción elegida: ");
			int opcion = scanner.nextInt();
			scanner.nextLine();

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
		}
		scanner.close();		
	}
	
	private static void buscarPeliculasPorTitulo() throws SQLException {
		System.out.print("\nIngrese el título (o parte) de la/s película/s que desea buscar: ");
		String titulo = scanner.nextLine();
		peliculaDAO.buscarPeliculasPorTitulo(titulo);
		System.out.println(VOLVERHOME);
	}
	
	private static void obtenerPeliculasPorTitulo() throws SQLException {
		System.out.print("\nIngrese el título (o parte) de la/s película/s que desea obtener: ");
		String titulo = scanner.nextLine();
		List<Pelicula> peliculasEncontradas = peliculaDAO.obtenerPeliculasPorTitulo(titulo);
		mostrarPeliculasObtenidas(peliculasEncontradas);
	}
	
	private static void buscarPeliculasPorGenero() {

	}
	
	private static void obtenerPeliculasPorGenero() {

	}
	
	private static void buscarTodasPeliculas() {

	}
	
	private static void obtenerTodasPeliculas() {

	}
	
	private static void mostrarPeliculasObtenidas(List<Pelicula> peliculas) throws SQLException {
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
		
		if (opcion.equalsIgnoreCase("S")) {
			System.out.print("Ingrese el código de la película que desea ver, aunque no se haya filtrado (0 para cancelar): ");
			int codigo = scanner.nextInt();
			scanner.nextLine();
			
			if (codigo == 0) {
				System.out.println(VOLVERHOME);
				return;
			}
			mostrarDetallePelicula(codigo);
		}
		System.out.println(VOLVERHOME);
	}

	private static void mostrarDetallePelicula(int codigo) throws SQLException {
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
	
	private static void agregarPelicula() {		
		
	}
	
	private static void eliminarPelicula() {
		
	}
	
	private static void modificarPelicula() {
		
	}
}

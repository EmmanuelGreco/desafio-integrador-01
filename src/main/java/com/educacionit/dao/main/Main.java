package com.educacionit.dao.main;

import java.util.Scanner;

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
	private static final Scanner scanner = new Scanner(System.in);
	private static final String HASTA_LUEGO =
			  "\n=======================================  ¡Hasta luego!  ========================================"
			+ "\n                                       By Emmanuel Greco®                                       ";
			
	public static void main(String[] args) {
		System.out.println(CREADOR);
		System.out.println(BIENVENIDO);
		boolean continuar = true;
		
		System.out.println(HOME);
		System.out.println("Seleccione índice del 1 al 10. Opción elegida: ");
		Integer opcion = scanner.nextInt();

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
		scanner.close();		
	}
	
	private static void buscarPeliculasPorTitulo() {
		
	}
	
	private static void obtenerPeliculasPorTitulo() {

	}
	
	private static void buscarPeliculasPorGenero() {

	}
	
	private static void obtenerPeliculasPorGenero() {

	}
	
	private static void buscarTodasPeliculas() {

	}
	
	private static void obtenerTodasPeliculas() {

	}
	
	private static void agregarPelicula() {		
		
	}
	
	private static void eliminarPelicula() {
		
	}
	
	private static void modificarPelicula() {
		
	}
}

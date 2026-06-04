package Taller3;
// Alvaro Elgueta Munoz - 21806097-8 - ICCI

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static Scanner s;
	private static SistemaMagia sistema;

	public static void main(String[] args) {
		s = new Scanner(System.in);
		sistema = new SistemaMagia();

		try {
			sistema.cargarDatos();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se encontro un archivo necesario.");
			s.close();
			return;
		}

		menuPrincipal();
		s.close();
	}

	// muestra el menu principal
	private static void menuPrincipal() {
		boolean salir = false;

		do {
			System.out.println();
			System.out.println("===== MENU PRINCIPAL =====");
			System.out.println("1) Panel Administrador");
			System.out.println("2) Panel Analista");
			System.out.println("3) Salir");

			int opcion = leerEnteroEnRango("Ingrese opcion: ", 1, 3);

			switch (opcion) {
			case 1:
				menuAdministrador();
				break;

			case 2:
				menuAnalista();
				break;

			case 3:
				salir = true;
				System.out.println("Hasta luego.");
				break;
			}
		} while (!salir);
	}

	// muestra el panel administrador
	private static void menuAdministrador() {
		boolean salir = false;

		do {
			System.out.println();
			System.out.println("===== PANEL ADMINISTRADOR =====");
			System.out.println("1) Agregar Mago");
			System.out.println("2) Modificar Mago");
			System.out.println("3) Eliminar Mago");
			System.out.println("4) Agregar Hechizo");
			System.out.println("5) Modificar Hechizo");
			System.out.println("6) Eliminar Hechizo");
			System.out.println("7) Volver");

			int opcion = leerEnteroEnRango("Ingrese opcion: ", 1, 7);

			switch (opcion) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				mostrarAdministradorPendiente();
				break;

			case 7:
				salir = true;
				break;
			}
		} while (!salir);
	}

	// muestra el panel analista
	private static void menuAnalista() {
		boolean salir = false;

		do {
			System.out.println();
			System.out.println("===== PANEL ANALISTA =====");
			System.out.println("1) Top 10 Mejores Hechizos");
			System.out.println("2) Top 3 Mejores Magos");
			System.out.println("3) Mostrar todos los Hechizos");
			System.out.println("4) Mostrar todos los Magos");
			System.out.println("5) Mostrar todos los Hechizos junto a su puntuacion");
			System.out.println("6) Mostrar todos los Magos junto a su puntuacion");
			System.out.println("7) Volver");

			int opcion = leerEnteroEnRango("Ingrese opcion: ", 1, 7);

			switch (opcion) {
			case 1:
				mostrarTopHechizos();
				break;

			case 2:
				mostrarTopMagos();
				break;

			case 3:
				mostrarHechizos();
				break;

			case 4:
				mostrarMagos();
				break;

			case 5:
				mostrarHechizosConPuntaje();
				break;

			case 6:
				mostrarMagosConPuntaje();
				break;

			case 7:
				salir = true;
				break;
			}
		} while (!salir);
	}

	// muestra el top de hechizos
	private static void mostrarTopHechizos() {
		ArrayList<Hechizo> topHechizos = sistema.getTopHechizos(10);

		System.out.println("===== TOP 10 HECHIZOS =====");

		for (int i = 0; i < topHechizos.size(); i++) {
			Hechizo hechizo = topHechizos.get(i);
			System.out.println((i + 1) + ") " + hechizo.getNombre() + " -> " + hechizo.calcularPuntaje());
		}
	}

	// muestra el top de magos
	private static void mostrarTopMagos() {
		ArrayList<Mago> topMagos = sistema.getTopMagos(3);

		System.out.println("===== TOP 3 MAGOS =====");

		for (int i = 0; i < topMagos.size(); i++) {
			Mago mago = topMagos.get(i);
			System.out.println((i + 1) + ") " + mago.getNombre() + " -> " + mago.calcularPuntaje());
		}
	}

	// muestra que el panel sigue pendiente
	private static void mostrarAdministradorPendiente() {
		System.out.println("Estas opciones se implementaran en el siguiente avance.");
	}

	// muestra los hechizos
	private static void mostrarHechizos() {
		System.out.println("===== HECHIZOS =====");

		for (int i = 0; i < sistema.getHechizos().size(); i++) {
			System.out.println((i + 1) + ") " + sistema.getHechizos().get(i));
		}
	}

	// muestra los magos
	private static void mostrarMagos() {
		System.out.println("===== MAGOS =====");

		for (int i = 0; i < sistema.getMagos().size(); i++) {
			System.out.println((i + 1) + ") " + sistema.getMagos().get(i));
		}
	}

	// muestra hechizos con puntaje
	private static void mostrarHechizosConPuntaje() {
		System.out.println("===== HECHIZOS CON PUNTAJE =====");

		for (int i = 0; i < sistema.getHechizos().size(); i++) {
			Hechizo hechizo = sistema.getHechizos().get(i);
			System.out.println((i + 1) + ") " + hechizo.getNombre() + " -> " + hechizo.calcularPuntaje());
		}
	}

	// muestra magos con puntaje
	private static void mostrarMagosConPuntaje() {
		System.out.println("===== MAGOS CON PUNTAJE =====");

		for (int i = 0; i < sistema.getMagos().size(); i++) {
			Mago mago = sistema.getMagos().get(i);
			System.out.println((i + 1) + ") " + mago.getNombre() + " -> " + mago.calcularPuntaje());
		}
	}

	// lee un numero del menu
	private static int leerEnteroEnRango(String mensaje, int min, int max) {
		while (true) {
			System.out.print(mensaje);
			String texto = s.nextLine();

			try {
				int numero = Integer.parseInt(texto);

				if (numero >= min && numero <= max) {
					return numero;
				}

				System.out.println("ERROR: Debe ingresar una opcion valida.");
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Debe ingresar un numero.");
			}
		}
	}
}

package Taller3;
// Alvaro Elgueta Munoz - 21806097-8 - ICCI

import java.io.FileNotFoundException;
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
			System.out.println("===== MENU =====");
			System.out.println("1) Mostrar todos los hechizos");
			System.out.println("2) Mostrar todos los magos");
			System.out.println("3) Mostrar hechizos con puntuacion");
			System.out.println("4) Mostrar magos con puntuacion");
			System.out.println("5) Salir");

			int opcion = leerEnteroEnRango("Ingrese opcion: ", 1, 5);

			switch (opcion) {
			case 1:
				mostrarHechizos();
				break;

			case 2:
				mostrarMagos();
				break;

			case 3:
				mostrarHechizosConPuntaje();
				break;

			case 4:
				mostrarMagosConPuntaje();
				break;

			case 5:
				salir = true;
				System.out.println("Hasta luego.");
				break;
			}
		} while (!salir);
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

package Taller3;
// Alvaro Elgueta Munoz - 21806097-8 - ICCI

import java.io.FileNotFoundException;
import java.io.IOException;
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
				agregarMago();
				break;

			case 2:
				modificarMago();
				break;

			case 3:
				eliminarMago();
				break;

			case 4:
				agregarHechizo();
				break;

			case 5:
				modificarHechizo();
				break;

			case 6:
				eliminarHechizo();
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

	// agrega un mago
	private static void agregarMago() {
		if (sistema.getHechizos().size() == 0) {
			System.out.println("ERROR: No hay hechizos disponibles.");
			return;
		}

		String nombre = leerTextoNoVacio("Ingrese nombre del mago: ");

		if (sistema.buscarMago(nombre) != null) {
			System.out.println("ERROR: Ya existe un mago con ese nombre.");
			return;
		}

		ArrayList<Hechizo> hechizosMago = leerHechizosParaMago();

		if (sistema.agregarMago(nombre, hechizosMago)) {
			guardarCambios();
			System.out.println("Mago agregado con exito.");
		} else {
			System.out.println("ERROR: No se pudo agregar el mago.");
		}
	}

	// modifica un mago
	private static void modificarMago() {
		String nombreActual = leerTextoNoVacio("Ingrese nombre del mago a modificar: ");
		Mago mago = sistema.buscarMago(nombreActual);

		if (mago == null) {
			System.out.println("ERROR: No existe ese mago.");
			return;
		}

		String nuevoNombre = leerTextoNoVacio("Ingrese nuevo nombre del mago: ");
		ArrayList<Hechizo> hechizosMago = leerHechizosParaMago();

		if (sistema.modificarMago(nombreActual, nuevoNombre, hechizosMago)) {
			guardarCambios();
			System.out.println("Mago modificado con exito.");
		} else {
			System.out.println("ERROR: No se pudo modificar el mago.");
		}
	}

	// elimina un mago
	private static void eliminarMago() {
		String nombre = leerTextoNoVacio("Ingrese nombre del mago a eliminar: ");

		if (sistema.eliminarMago(nombre)) {
			guardarCambios();
			System.out.println("Mago eliminado con exito.");
		} else {
			System.out.println("ERROR: No existe ese mago.");
		}
	}

	// agrega un hechizo
	private static void agregarHechizo() {
		String nombre = leerTextoNoVacio("Ingrese nombre del hechizo: ");

		if (sistema.buscarHechizo(nombre) != null) {
			System.out.println("ERROR: Ya existe un hechizo con ese nombre.");
			return;
		}

		Hechizo hechizo = leerDatosHechizo(nombre);

		if (sistema.agregarHechizo(hechizo)) {
			guardarCambios();
			System.out.println("Hechizo agregado con exito.");
		} else {
			System.out.println("ERROR: No se pudo agregar el hechizo.");
		}
	}

	// modifica un hechizo
	private static void modificarHechizo() {
		String nombreActual = leerTextoNoVacio("Ingrese nombre del hechizo a modificar: ");
		Hechizo hechizoActual = sistema.buscarHechizo(nombreActual);

		if (hechizoActual == null) {
			System.out.println("ERROR: No existe ese hechizo.");
			return;
		}

		String nuevoNombre = leerTextoNoVacio("Ingrese nuevo nombre del hechizo: ");
		Hechizo nuevoHechizo = leerDatosHechizo(nuevoNombre);

		if (sistema.modificarHechizo(nombreActual, nuevoHechizo)) {
			guardarCambios();
			System.out.println("Hechizo modificado con exito.");
		} else {
			System.out.println("ERROR: No se pudo modificar el hechizo.");
		}
	}

	// elimina un hechizo
	private static void eliminarHechizo() {
		String nombre = leerTextoNoVacio("Ingrese nombre del hechizo a eliminar: ");
		Hechizo hechizo = sistema.buscarHechizo(nombre);

		if (hechizo == null) {
			System.out.println("ERROR: No existe ese hechizo.");
			return;
		}

		if (sistema.eliminarHechizo(nombre)) {
			guardarCambios();
			System.out.println("Hechizo eliminado con exito.");
		} else {
			System.out.println("ERROR: No se puede eliminar ese hechizo porque dejaria a un mago sin hechizos.");
		}
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

	// guarda los archivos
	private static void guardarCambios() {
		try {
			sistema.guardarDatos();
		} catch (IOException e) {
			System.out.println("ERROR: No se pudieron guardar los cambios.");
		}
	}

	// lee los hechizos de un mago
	private static ArrayList<Hechizo> leerHechizosParaMago() {
		while (true) {
			mostrarHechizosDisponibles();
			String texto = leerTextoNoVacio("Ingrese hechizos separados por |: ");
			String nombres[] = texto.split("\\|");
			ArrayList<Hechizo> lista = new ArrayList<Hechizo>();
			boolean valido = true;

			for (int i = 0; i < nombres.length; i++) {
				Hechizo hechizo = sistema.buscarHechizo(nombres[i].trim());

				if (hechizo == null || lista.contains(hechizo)) {
					valido = false;
					break;
				}

				lista.add(hechizo);
			}

			if (valido && lista.size() > 0) {
				return lista;
			}

			System.out.println("ERROR: Debe ingresar hechizos validos y sin repetir.");
		}
	}

	// muestra los hechizos disponibles
	private static void mostrarHechizosDisponibles() {
		System.out.println("Hechizos disponibles:");

		for (int i = 0; i < sistema.getHechizos().size(); i++) {
			System.out.println("- " + sistema.getHechizos().get(i).getNombre());
		}
	}

	// lee los datos de un hechizo
	private static Hechizo leerDatosHechizo(String nombre) {
		String tipo = leerTipoHechizo();
		int danio = leerEnteroMinimo("Ingrese danio: ", 0);

		if (tipo.equalsIgnoreCase("Fuego")) {
			int duracion = leerEnteroMinimo("Ingrese duracion de quemadura: ", 0);
			return FactoryHechizos.crearHechizo(nombre, tipo, danio, String.valueOf(duracion));
		}

		if (tipo.equalsIgnoreCase("Tierra")) {
			int defensa = leerEnteroMinimo("Ingrese mejora de defensa: ", 0);
			return FactoryHechizos.crearHechizo(nombre, tipo, danio, String.valueOf(defensa));
		}

		if (tipo.equalsIgnoreCase("Planta")) {
			int stun = leerEnteroMinimo("Ingrese duracion de stun: ", 0);
			int plantas = leerEnteroMinimo("Ingrese cantidad de plantas: ", 0);
			return FactoryHechizos.crearHechizo(nombre, tipo, danio, stun + "," + plantas);
		}

		int heal = leerEnteroMinimo("Ingrese cantidad de heal: ", 0);
		int presion = leerEnteroMinimo("Ingrese presion del agua: ", 0);
		return FactoryHechizos.crearHechizo(nombre, tipo, danio, heal + "," + presion);
	}

	// lee el tipo del hechizo
	private static String leerTipoHechizo() {
		System.out.println("1) Fuego");
		System.out.println("2) Tierra");
		System.out.println("3) Planta");
		System.out.println("4) Agua");

		int opcion = leerEnteroEnRango("Ingrese tipo: ", 1, 4);

		switch (opcion) {
		case 1:
			return "Fuego";

		case 2:
			return "Tierra";

		case 3:
			return "Planta";

		default:
			return "Agua";
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

	// lee un numero minimo
	private static int leerEnteroMinimo(String mensaje, int minimo) {
		while (true) {
			System.out.print(mensaje);
			String texto = s.nextLine();

			try {
				int numero = Integer.parseInt(texto);

				if (numero >= minimo) {
					return numero;
				}

				System.out.println("ERROR: Debe ingresar un numero valido.");
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Debe ingresar un numero.");
			}
		}
	}

	// lee un texto
	private static String leerTextoNoVacio(String mensaje) {
		while (true) {
			System.out.print(mensaje);
			String texto = s.nextLine().trim();

			if (!texto.equals("")) {
				return texto;
			}

			System.out.println("ERROR: No puede quedar vacio.");
		}
	}
}

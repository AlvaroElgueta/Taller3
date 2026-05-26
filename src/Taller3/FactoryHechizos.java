package Taller3;

// crea hechizos segun su tipo
public class FactoryHechizos {

	// crea un hechizo desde una linea
	public static Hechizo crearDesdeArchivo(String linea) {
		String datos[] = linea.split(";");

		if (datos.length != 4) {
			return null;
		}

		String nombre = datos[0].trim();
		String tipo = datos[1].trim();
		int danio = Integer.parseInt(datos[2].trim());
		String extra = datos[3].trim();

		return crearHechizo(nombre, tipo, danio, extra);
	}

	// crea un hechizo con sus datos
	public static Hechizo crearHechizo(String nombre, String tipo, int danio, String extra) {
		if (tipo.equalsIgnoreCase("Fuego")) {
			int duracionQuemadura = Integer.parseInt(extra);
			return new HechizoFuego(nombre, danio, duracionQuemadura);
		}

		if (tipo.equalsIgnoreCase("Tierra") || tipo.equalsIgnoreCase("Roca")) {
			int mejoraDefensa = Integer.parseInt(extra);
			return new HechizoRoca(nombre, danio, mejoraDefensa);
		}

		if (tipo.equalsIgnoreCase("Planta")) {
			String datos[] = extra.split(",");
			int duracionStun = Integer.parseInt(datos[0].trim());
			int cantidadPlantas = Integer.parseInt(datos[1].trim());
			return new HechizoPlanta(nombre, danio, duracionStun, cantidadPlantas);
		}

		if (tipo.equalsIgnoreCase("Agua")) {
			String datos[] = extra.split(",");
			int cantidadHeal = Integer.parseInt(datos[0].trim());
			int presionAgua = Integer.parseInt(datos[1].trim());
			return new HechizoAgua(nombre, danio, cantidadHeal, presionAgua);
		}

		return null;
	}
}

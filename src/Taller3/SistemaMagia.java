package Taller3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// maneja la carga y las consultas
public class SistemaMagia {
	private ArrayList<Mago> magos;
	private ArrayList<Hechizo> hechizos;

	public SistemaMagia() {
		magos = new ArrayList<Mago>();
		hechizos = new ArrayList<Hechizo>();
	}

	// carga todos los datos
	public void cargarDatos() throws FileNotFoundException {
		cargarHechizos();
		cargarMagos();
	}

	// devuelve los magos
	public ArrayList<Mago> getMagos() {
		return magos;
	}

	// devuelve los hechizos
	public ArrayList<Hechizo> getHechizos() {
		return hechizos;
	}

	// busca un hechizo por nombre
	public Hechizo buscarHechizo(String nombre) {
		for (int i = 0; i < hechizos.size(); i++) {
			if (hechizos.get(i).getNombre().equalsIgnoreCase(nombre)) {
				return hechizos.get(i);
			}
		}

		return null;
	}

	// carga los hechizos
	private void cargarHechizos() throws FileNotFoundException {
		Scanner archivo = new Scanner(new File("Hechizos.txt"));
		hechizos.clear();

		while (archivo.hasNextLine()) {
			String linea = archivo.nextLine().trim();

			if (!linea.equals("")) {
				try {
					Hechizo hechizo = FactoryHechizos.crearDesdeArchivo(linea);

					if (hechizo != null) {
						hechizos.add(hechizo);
					}
				} catch (NumberFormatException e) {
					System.out.println("ERROR: Hay un hechizo con datos invalidos.");
				}
			}
		}

		archivo.close();
	}

	// carga los magos
	private void cargarMagos() throws FileNotFoundException {
		Scanner archivo = new Scanner(new File("Magos.txt"));
		magos.clear();

		while (archivo.hasNextLine()) {
			String linea = archivo.nextLine().trim();

			if (!linea.equals("")) {
				String datos[] = linea.split(";");

				if (datos.length == 2) {
					Mago mago = new Mago(datos[0].trim());
					String nombresHechizos[] = datos[1].split("\\|");

					for (int i = 0; i < nombresHechizos.length; i++) {
						Hechizo hechizo = buscarHechizo(nombresHechizos[i].trim());

						if (hechizo != null) {
							mago.agregarHechizo(hechizo);
						}
					}

					magos.add(mago);
				}
			}
		}

		archivo.close();
	}
}

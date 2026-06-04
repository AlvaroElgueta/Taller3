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

	// devuelve el top de hechizos
	public ArrayList<Hechizo> getTopHechizos(int cantidad) {
		ArrayList<Hechizo> copia = copiarHechizos();
		ordenarHechizos(copia);

		while (copia.size() > cantidad) {
			copia.remove(copia.size() - 1);
		}

		return copia;
	}

	// devuelve el top de magos
	public ArrayList<Mago> getTopMagos(int cantidad) {
		ArrayList<Mago> copia = copiarMagos();
		ordenarMagos(copia);

		while (copia.size() > cantidad) {
			copia.remove(copia.size() - 1);
		}

		return copia;
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

	// copia los hechizos
	private ArrayList<Hechizo> copiarHechizos() {
		ArrayList<Hechizo> copia = new ArrayList<Hechizo>();

		for (int i = 0; i < hechizos.size(); i++) {
			copia.add(hechizos.get(i));
		}

		return copia;
	}

	// copia los magos
	private ArrayList<Mago> copiarMagos() {
		ArrayList<Mago> copia = new ArrayList<Mago>();

		for (int i = 0; i < magos.size(); i++) {
			copia.add(magos.get(i));
		}

		return copia;
	}

	// ordena los hechizos por puntaje
	private void ordenarHechizos(ArrayList<Hechizo> lista) {
		for (int i = 0; i < lista.size(); i++) {
			for (int j = i + 1; j < lista.size(); j++) {
				boolean cambiar = false;

				if (lista.get(j).calcularPuntaje() > lista.get(i).calcularPuntaje()) {
					cambiar = true;
				} else if (lista.get(j).calcularPuntaje() == lista.get(i).calcularPuntaje()
						&& lista.get(j).getNombre().compareToIgnoreCase(lista.get(i).getNombre()) < 0) {
					cambiar = true;
				}

				if (cambiar) {
					Hechizo aux = lista.get(i);
					lista.set(i, lista.get(j));
					lista.set(j, aux);
				}
			}
		}
	}

	// ordena los magos por puntaje
	private void ordenarMagos(ArrayList<Mago> lista) {
		for (int i = 0; i < lista.size(); i++) {
			for (int j = i + 1; j < lista.size(); j++) {
				boolean cambiar = false;

				if (lista.get(j).calcularPuntaje() > lista.get(i).calcularPuntaje()) {
					cambiar = true;
				} else if (lista.get(j).calcularPuntaje() == lista.get(i).calcularPuntaje()
						&& lista.get(j).getNombre().compareToIgnoreCase(lista.get(i).getNombre()) < 0) {
					cambiar = true;
				}

				if (cambiar) {
					Mago aux = lista.get(i);
					lista.set(i, lista.get(j));
					lista.set(j, aux);
				}
			}
		}
	}
}

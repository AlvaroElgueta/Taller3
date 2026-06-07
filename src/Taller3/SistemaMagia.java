package Taller3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

	// busca un mago por nombre
	public Mago buscarMago(String nombre) {
		for (int i = 0; i < magos.size(); i++) {
			if (magos.get(i).getNombre().equalsIgnoreCase(nombre)) {
				return magos.get(i);
			}
		}

		return null;
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

	// agrega un mago
	public boolean agregarMago(String nombre, ArrayList<Hechizo> nuevosHechizos) {
		if (buscarMago(nombre) != null || nuevosHechizos.size() == 0) {
			return false;
		}

		Mago mago = new Mago(nombre);
		mago.setHechizos(nuevosHechizos);
		magos.add(mago);
		return true;
	}

	// modifica un mago
	public boolean modificarMago(String nombreActual, String nuevoNombre, ArrayList<Hechizo> nuevosHechizos) {
		Mago mago = buscarMago(nombreActual);

		if (mago == null || nuevosHechizos.size() == 0) {
			return false;
		}

		Mago repetido = buscarMago(nuevoNombre);

		if (repetido != null && repetido != mago) {
			return false;
		}

		mago.setNombre(nuevoNombre);
		mago.setHechizos(nuevosHechizos);
		return true;
	}

	// elimina un mago
	public boolean eliminarMago(String nombre) {
		Mago mago = buscarMago(nombre);

		if (mago == null) {
			return false;
		}

		magos.remove(mago);
		return true;
	}

	// agrega un hechizo
	public boolean agregarHechizo(Hechizo hechizo) {
		if (hechizo == null || buscarHechizo(hechizo.getNombre()) != null) {
			return false;
		}

		hechizos.add(hechizo);
		return true;
	}

	// modifica un hechizo
	public boolean modificarHechizo(String nombreActual, Hechizo nuevoHechizo) {
		Hechizo hechizoActual = buscarHechizo(nombreActual);

		if (hechizoActual == null || nuevoHechizo == null) {
			return false;
		}

		Hechizo repetido = buscarHechizo(nuevoHechizo.getNombre());

		if (repetido != null && repetido != hechizoActual) {
			return false;
		}

		int posicion = hechizos.indexOf(hechizoActual);
		hechizos.set(posicion, nuevoHechizo);
		reemplazarHechizoEnMagos(hechizoActual, nuevoHechizo);
		return true;
	}

	// elimina un hechizo
	public boolean eliminarHechizo(String nombre) {
		Hechizo hechizo = buscarHechizo(nombre);

		if (hechizo == null || !puedeEliminarHechizo(hechizo)) {
			return false;
		}

		for (int i = 0; i < magos.size(); i++) {
			magos.get(i).eliminarHechizo(hechizo);
		}

		hechizos.remove(hechizo);
		return true;
	}

	// guarda todos los cambios
	public void guardarDatos() throws IOException {
		guardarHechizos();
		guardarMagos();
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

	// reemplaza un hechizo en todos los magos
	private void reemplazarHechizoEnMagos(Hechizo anterior, Hechizo nuevo) {
		for (int i = 0; i < magos.size(); i++) {
			ArrayList<Hechizo> nuevosHechizos = new ArrayList<Hechizo>();
			ArrayList<Hechizo> hechizosActuales = magos.get(i).getHechizos();

			for (int j = 0; j < hechizosActuales.size(); j++) {
				if (hechizosActuales.get(j) == anterior) {
					nuevosHechizos.add(nuevo);
				} else {
					nuevosHechizos.add(hechizosActuales.get(j));
				}
			}

			magos.get(i).setHechizos(nuevosHechizos);
		}
	}

	// revisa si se puede eliminar un hechizo
	private boolean puedeEliminarHechizo(Hechizo hechizo) {
		for (int i = 0; i < magos.size(); i++) {
			if (magos.get(i).getHechizos().contains(hechizo) && magos.get(i).getHechizos().size() == 1) {
				return false;
			}
		}

		return true;
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

	// guarda los hechizos
	private void guardarHechizos() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("Hechizos.txt"));

		for (int i = 0; i < hechizos.size(); i++) {
			bw.write(hechizos.get(i).toArchivo());

			if (i < hechizos.size() - 1) {
				bw.newLine();
			}
		}

		bw.close();
	}

	// guarda los magos
	private void guardarMagos() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("Magos.txt"));

		for (int i = 0; i < magos.size(); i++) {
			bw.write(magos.get(i).toArchivo());

			if (i < magos.size() - 1) {
				bw.newLine();
			}
		}

		bw.close();
	}
}

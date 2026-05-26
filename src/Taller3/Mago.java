package Taller3;

import java.util.ArrayList;

// guarda los datos de un mago
public class Mago implements Puntuable {
	private String nombre;
	private ArrayList<Hechizo> hechizos;

	public Mago(String nombre) {
		this.nombre = nombre;
		this.hechizos = new ArrayList<Hechizo>();
	}

	// devuelve el nombre
	public String getNombre() {
		return nombre;
	}

	// cambia el nombre
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// devuelve los hechizos
	public ArrayList<Hechizo> getHechizos() {
		return hechizos;
	}

	// agrega un hechizo
	public void agregarHechizo(Hechizo hechizo) {
		if (!hechizos.contains(hechizo)) {
			hechizos.add(hechizo);
		}
	}

	// elimina un hechizo
	public void eliminarHechizo(Hechizo hechizo) {
		hechizos.remove(hechizo);
	}

	// cambia todos los hechizos
	public void setHechizos(ArrayList<Hechizo> nuevosHechizos) {
		hechizos.clear();

		for (int i = 0; i < nuevosHechizos.size(); i++) {
			agregarHechizo(nuevosHechizos.get(i));
		}
	}

	@Override
	public double calcularPuntaje() {
		double total = 0;

		for (int i = 0; i < hechizos.size(); i++) {
			total += hechizos.get(i).calcularPuntaje();
		}

		return total;
	}

	// arma la linea para guardar
	public String toArchivo() {
		String linea = nombre + ";";

		for (int i = 0; i < hechizos.size(); i++) {
			linea += hechizos.get(i).getNombre();

			if (i < hechizos.size() - 1) {
				linea += "|";
			}
		}

		return linea;
	}

	@Override
	public String toString() {
		String texto = "Mago: " + nombre + " | Hechizos: ";

		for (int i = 0; i < hechizos.size(); i++) {
			texto += hechizos.get(i).getNombre();

			if (i < hechizos.size() - 1) {
				texto += ", ";
			}
		}

		return texto;
	}
}

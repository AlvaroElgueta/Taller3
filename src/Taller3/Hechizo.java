package Taller3;

// clase base de los hechizos
public abstract class Hechizo implements Puntuable {
	private String nombre;
	private String tipo;
	private int danio;

	public Hechizo(String nombre, String tipo, int danio) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.danio = danio;
	}

	// devuelve el nombre
	public String getNombre() {
		return nombre;
	}

	// cambia el nombre
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// devuelve el tipo
	public String getTipo() {
		return tipo;
	}

	// cambia el tipo
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	// devuelve el danio
	public int getDanio() {
		return danio;
	}

	// cambia el danio
	public void setDanio(int danio) {
		this.danio = danio;
	}

	// devuelve el dato extra para el archivo
	public abstract String getParametroArchivo();

	// devuelve el detalle extra del hechizo
	public abstract String getDetalleExtra();

	// arma la linea para guardar
	public String toArchivo() {
		return nombre + ";" + tipo + ";" + danio + ";" + getParametroArchivo();
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " | Tipo: " + tipo + " | Danio: " + danio + " | " + getDetalleExtra();
	}
}

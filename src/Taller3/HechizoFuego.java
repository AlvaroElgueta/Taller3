package Taller3;

// hechizo de fuego
public class HechizoFuego extends Hechizo {
	private int duracionQuemadura;

	public HechizoFuego(String nombre, int danio, int duracionQuemadura) {
		super(nombre, "Fuego", danio);
		this.duracionQuemadura = duracionQuemadura;
	}

	// devuelve la quemadura
	public int getDuracionQuemadura() {
		return duracionQuemadura;
	}

	// cambia la quemadura
	public void setDuracionQuemadura(int duracionQuemadura) {
		this.duracionQuemadura = duracionQuemadura;
	}

	@Override
	public double calcularPuntaje() {
		return getDanio() * duracionQuemadura;
	}

	@Override
	public String getParametroArchivo() {
		return String.valueOf(duracionQuemadura);
	}

	@Override
	public String getDetalleExtra() {
		return "Duracion quemadura: " + duracionQuemadura;
	}
}

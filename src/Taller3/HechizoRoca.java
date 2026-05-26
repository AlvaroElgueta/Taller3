package Taller3;

// hechizo de roca
public class HechizoRoca extends Hechizo {
	private int mejoraDefensa;

	public HechizoRoca(String nombre, int danio, int mejoraDefensa) {
		super(nombre, "Tierra", danio);
		this.mejoraDefensa = mejoraDefensa;
	}

	// devuelve la defensa extra
	public int getMejoraDefensa() {
		return mejoraDefensa;
	}

	// cambia la defensa extra
	public void setMejoraDefensa(int mejoraDefensa) {
		this.mejoraDefensa = mejoraDefensa;
	}

	@Override
	public double calcularPuntaje() {
		return (getDanio() * mejoraDefensa) / 2.0;
	}

	@Override
	public String getParametroArchivo() {
		return String.valueOf(mejoraDefensa);
	}

	@Override
	public String getDetalleExtra() {
		return "Mejora defensa: " + mejoraDefensa;
	}
}

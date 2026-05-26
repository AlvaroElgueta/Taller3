package Taller3;

// hechizo de planta
public class HechizoPlanta extends Hechizo {
	private int duracionStun;
	private int cantidadPlantas;

	public HechizoPlanta(String nombre, int danio, int duracionStun, int cantidadPlantas) {
		super(nombre, "Planta", danio);
		this.duracionStun = duracionStun;
		this.cantidadPlantas = cantidadPlantas;
	}

	// devuelve el stun
	public int getDuracionStun() {
		return duracionStun;
	}

	// cambia el stun
	public void setDuracionStun(int duracionStun) {
		this.duracionStun = duracionStun;
	}

	// devuelve la cantidad de plantas
	public int getCantidadPlantas() {
		return cantidadPlantas;
	}

	// cambia la cantidad de plantas
	public void setCantidadPlantas(int cantidadPlantas) {
		this.cantidadPlantas = cantidadPlantas;
	}

	@Override
	public double calcularPuntaje() {
		return getDanio() + (duracionStun * cantidadPlantas);
	}

	@Override
	public String getParametroArchivo() {
		return duracionStun + "," + cantidadPlantas;
	}

	@Override
	public String getDetalleExtra() {
		return "Duracion stun: " + duracionStun + " | Cantidad plantas: " + cantidadPlantas;
	}
}

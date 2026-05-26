package Taller3;

// hechizo de agua
public class HechizoAgua extends Hechizo {
	private int cantidadHeal;
	private int presionAgua;

	public HechizoAgua(String nombre, int danio, int cantidadHeal, int presionAgua) {
		super(nombre, "Agua", danio);
		this.cantidadHeal = cantidadHeal;
		this.presionAgua = presionAgua;
	}

	// devuelve el heal
	public int getCantidadHeal() {
		return cantidadHeal;
	}

	// cambia el heal
	public void setCantidadHeal(int cantidadHeal) {
		this.cantidadHeal = cantidadHeal;
	}

	// devuelve la presion
	public int getPresionAgua() {
		return presionAgua;
	}

	// cambia la presion
	public void setPresionAgua(int presionAgua) {
		this.presionAgua = presionAgua;
	}

	@Override
	public double calcularPuntaje() {
		return (getDanio() + cantidadHeal + presionAgua) * 2.0;
	}

	@Override
	public String getParametroArchivo() {
		return cantidadHeal + "," + presionAgua;
	}

	@Override
	public String getDetalleExtra() {
		return "Heal: " + cantidadHeal + " | Presion agua: " + presionAgua;
	}
}

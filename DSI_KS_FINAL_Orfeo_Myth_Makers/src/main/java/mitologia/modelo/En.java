package mitologia.modelo;

public class En {
	private String personaje;
	private String lugar;

	public En(String personaje, String lugar) {
		this.personaje = personaje;
		this.lugar = lugar;
	}

	public String getPersonaje() {
		return personaje;
	}

	public void setPersonaje(String personaje) {
		this.personaje = personaje;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	@Override
	public String toString() {
		return "En(" + personaje + ", " + lugar + ")";
	}
}

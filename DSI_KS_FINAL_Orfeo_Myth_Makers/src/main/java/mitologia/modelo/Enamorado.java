package mitologia.modelo;

public class Enamorado {
	private String persona;
	private String amado;

	public Enamorado(String persona, String amado) {
		this.persona = persona;
		this.amado = amado;
	}

	public String getPersona() {
		return persona;
	}

	public String getAmado() {
		return amado;
	}

	public void setAmado(String amado) {
		this.amado = amado;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Enamorado(" + persona + ", " + amado + ")";
	}
}

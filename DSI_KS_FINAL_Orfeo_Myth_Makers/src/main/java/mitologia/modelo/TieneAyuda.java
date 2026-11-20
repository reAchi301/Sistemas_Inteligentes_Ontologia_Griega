package mitologia.modelo;

//La clase tiene ayuda es el similar a la clase TieneFavor pero para Mortales
public class TieneAyuda {
	private String receptor;
	private String ayudante;

	public TieneAyuda(String receptor, String ayudante) {
		this.receptor = receptor;
		this.ayudante = ayudante;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getAyudante() {
		return ayudante;
	}

	public void setAyudante(String ayudante) {
		this.ayudante = ayudante;
	}

	@Override
	public String toString() {
		return "TieneAyuda(" + receptor + ", " + ayudante + ")";
	}
}

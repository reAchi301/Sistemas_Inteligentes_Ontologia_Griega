package mitologia.modelo;

public class Criatura extends Personaje {
	public Criatura(String nombre) {
		super(nombre);
	}

	public Criatura(String nombre, boolean libre) {
		super(nombre);
		setLibre(libre);
	}
}

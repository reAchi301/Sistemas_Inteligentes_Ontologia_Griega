package mitologia.modelo;

/** Slot localizaA: el personaje sabe donde esta el otro */
public class Localiza {
    private String personaje;
    private String localizado;

    public Localiza(String personaje, String localizado) {
        this.personaje = personaje;
        this.localizado = localizado;
    }

    public String getPersonaje() { return personaje; }
    public void setPersonaje(String personaje) { this.personaje = personaje; }
    public String getLocalizado() { return localizado; }
    public void setLocalizado(String localizado) { this.localizado = localizado; }

    @Override
    public String toString() { return "Localiza(" + personaje + ", " + localizado + ")"; }
}

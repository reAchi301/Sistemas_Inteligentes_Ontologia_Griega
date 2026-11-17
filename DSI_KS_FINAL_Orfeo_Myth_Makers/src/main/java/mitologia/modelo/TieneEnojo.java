package mitologia.modelo;

/** Slot tieneEnojo: el dios esta enojado con el personaje */
public class TieneEnojo {
    private String personaje;
    private String dios;

    public TieneEnojo(String personaje, String dios) {
        this.personaje = personaje;
        this.dios = dios;
    }

    public String getPersonaje() { return personaje; }
    public void setPersonaje(String personaje) { this.personaje = personaje; }
    public String getDios() { return dios; }
    public void setDios(String dios) { this.dios = dios; }

    @Override
    public String toString() { return "TieneEnojo(" + personaje + ", " + dios + ")"; }
}

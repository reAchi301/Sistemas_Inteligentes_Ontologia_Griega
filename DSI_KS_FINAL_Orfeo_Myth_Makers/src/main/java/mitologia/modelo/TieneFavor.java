package mitologia.modelo;

/** Slot tieneFavor: el dios favorece al personaje */
public class TieneFavor {
    private String personaje;
    private String dios;

    public TieneFavor(String personaje, String dios) {
        this.personaje = personaje;
        this.dios = dios;
    }

    public String getPersonaje() { return personaje; }
    public void setPersonaje(String personaje) { this.personaje = personaje; }
    public String getDios() { return dios; }
    public void setDios(String dios) { this.dios = dios; }

    @Override
    public String toString() { return "TieneFavor(" + personaje + ", " + dios + ")"; }
}

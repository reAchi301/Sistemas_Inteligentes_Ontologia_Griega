package mitologia.modelo;

/** Slot otorgaCapacidad: el objeto da esa capacidad a quien lo posea */
public class Otorga {
    private String objeto;
    private String capacidad;

    public Otorga(String objeto, String capacidad) {
        this.objeto = objeto;
        this.capacidad = capacidad;
    }

    public String getObjeto() { return objeto; }
    public void setObjeto(String objeto) { this.objeto = objeto; }
    public String getCapacidad() { return capacidad; }
    public void setCapacidad(String capacidad) { this.capacidad = capacidad; }

    @Override
    public String toString() { return "Otorga(" + objeto + ", " + capacidad + ")"; }
}

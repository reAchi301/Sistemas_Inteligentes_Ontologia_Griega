package mitologia.modelo;

/** Slot tieneCapacidad: capacidad activa del personaje */
public class TieneCapacidad {
    private String personaje;
    private String capacidad;

    public TieneCapacidad(String personaje, String capacidad) {
        this.personaje = personaje;
        this.capacidad = capacidad;
    }

    public String getPersonaje() { return personaje; }
    public void setPersonaje(String personaje) { this.personaje = personaje; }
    public String getCapacidad() { return capacidad; }
    public void setCapacidad(String capacidad) { this.capacidad = capacidad; }

    @Override
    public String toString() { return "TieneCapacidad(" + personaje + ", " + capacidad + ")"; }
}

package mitologia.modelo;

/** Raiz de la ontologia: cualquier cosa con nombre (personajes y objetos). */
public abstract class Entidad {
    private String nombre;

    public Entidad(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() { return nombre; }
}

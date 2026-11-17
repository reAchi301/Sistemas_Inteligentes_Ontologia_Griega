package mitologia.modelo;

public class Lugar {
    private String nombre;
    public Lugar(String nombre) { this.nombre = nombre; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    @Override
    public String toString() { return nombre; }
}

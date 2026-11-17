package mitologia.modelo;

/**
 * La pregunta del escenario. accion = liberar | matar | apresar | obtener | capacidad.
 * Ej: "Puede Perseo liberar a Andromeda?" -> Mision("liberar", "Perseo", "Andromeda")
 */
public class Mision {
    private String accion;
    private String sujeto;
    private String objetivo;

    public Mision(String accion, String sujeto, String objetivo) {
        this.accion = accion;
        this.sujeto = sujeto;
        this.objetivo = objetivo;
    }

    public String getAccion() { return accion; }
    public String getSujeto() { return sujeto; }
    public String getObjetivo() { return objetivo; }

    @Override
    public String toString() { return "Mision(" + accion + ", " + sujeto + ", " + objetivo + ")"; }
}

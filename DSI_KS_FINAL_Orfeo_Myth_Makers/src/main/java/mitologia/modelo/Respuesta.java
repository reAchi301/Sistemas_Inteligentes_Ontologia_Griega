package mitologia.modelo;

/** Conclusion final a la Mision: la inserta la agenda "respuesta". */
public class Respuesta {
    private boolean exito;
    private String texto;

    public Respuesta(boolean exito, String texto) {
        this.exito = exito;
        this.texto = texto;
    }

    public boolean isExito() { return exito; }
    public String getTexto() { return texto; }
}

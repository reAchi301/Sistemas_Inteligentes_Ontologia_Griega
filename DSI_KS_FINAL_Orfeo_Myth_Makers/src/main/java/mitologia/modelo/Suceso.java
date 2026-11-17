package mitologia.modelo;

/**
 * Una linea de la historia ("Perseo obtiene HozAcero debido a ...").
 * Las reglas solo hacen insert(new Suceso(...)); el orden se guarda para
 * poder escribir la respuesta en el mismo orden en que ocurrieron los hechos.
 */
public class Suceso {
    private static int contador = 0;

    private int orden;
    private String texto;

    public Suceso(String texto) {
        this.orden = ++contador;
        this.texto = texto;
    }

    /** Se llama desde Main antes de cada escenario. */
    public static void reiniciarContador() { contador = 0; }

    public int getOrden() { return orden; }
    public String getTexto() { return texto; }

    @Override
    public String toString() { return texto; }
}

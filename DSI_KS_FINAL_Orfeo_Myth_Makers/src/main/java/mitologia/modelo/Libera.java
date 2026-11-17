package mitologia.modelo;

/** Accion Liberar ya realizada (evento) */
public class Libera {
    private String liberador;
    private String liberado;

    public Libera(String liberador, String liberado) {
        this.liberador = liberador;
        this.liberado = liberado;
    }

    public String getLiberador() { return liberador; }
    public void setLiberador(String liberador) { this.liberador = liberador; }
    public String getLiberado() { return liberado; }
    public void setLiberado(String liberado) { this.liberado = liberado; }

    @Override
    public String toString() { return "Libera(" + liberador + ", " + liberado + ")"; }
}

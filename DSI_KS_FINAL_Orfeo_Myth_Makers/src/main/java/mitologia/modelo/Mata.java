package mitologia.modelo;

/** Accion Matar ya realizada (evento) */
public class Mata {
    private String asesino;
    private String victima;

    public Mata(String asesino, String victima) {
        this.asesino = asesino;
        this.victima = victima;
    }

    public String getAsesino() { return asesino; }
    public void setAsesino(String asesino) { this.asesino = asesino; }
    public String getVictima() { return victima; }
    public void setVictima(String victima) { this.victima = victima; }

    @Override
    public String toString() { return "Mata(" + asesino + ", " + victima + ")"; }
}

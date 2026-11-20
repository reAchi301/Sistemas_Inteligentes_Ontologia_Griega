package mitologia.modelo;

/** Actor del mito. Sus estados (estadoVivo, estadoLibre en Protege) son atributos. */
public abstract class Personaje extends Entidad {
    private boolean vivo = true;
    private boolean libre = true;

    public Personaje(String nombre) { super(nombre); }
    
    public boolean isVivo() { return vivo; }
    public void setVivo(boolean vivo) { this.vivo = vivo; }
    public boolean isLibre() { return libre; }
    public void setLibre(boolean libre) { this.libre = libre; }
}

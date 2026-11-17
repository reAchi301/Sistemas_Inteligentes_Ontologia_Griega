package mitologia.modelo;

/** Slot hijoDe: parentesco (necesario para la captura de Andromeda) */
public class HijoDe {
    private String hijo;
    private String progenitor;

    public HijoDe(String hijo, String progenitor) {
        this.hijo = hijo;
        this.progenitor = progenitor;
    }

    public String getHijo() { return hijo; }
    public void setHijo(String hijo) { this.hijo = hijo; }
    public String getProgenitor() { return progenitor; }
    public void setProgenitor(String progenitor) { this.progenitor = progenitor; }

    @Override
    public String toString() { return "HijoDe(" + hijo + ", " + progenitor + ")"; }
}

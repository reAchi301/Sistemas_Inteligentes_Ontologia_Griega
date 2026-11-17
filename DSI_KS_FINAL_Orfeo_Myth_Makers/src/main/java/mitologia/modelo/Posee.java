package mitologia.modelo;

/** Slot poseeObjeto / poseidoPor: inventario */
public class Posee {
    private String poseedor;
    private String objeto;

    public Posee(String poseedor, String objeto) {
        this.poseedor = poseedor;
        this.objeto = objeto;
    }

    public String getPoseedor() { return poseedor; }
    public void setPoseedor(String poseedor) { this.poseedor = poseedor; }
    public String getObjeto() { return objeto; }
    public void setObjeto(String objeto) { this.objeto = objeto; }

    @Override
    public String toString() { return "Posee(" + poseedor + ", " + objeto + ")"; }
}

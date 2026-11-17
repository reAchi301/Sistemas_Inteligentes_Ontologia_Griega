package mitologia.modelo;

/** Condicion inicial negativa de un escenario: X no tiene O (quita el hecho por defecto) */
public class NoPosee {
    private String poseedor;
    private String objeto;

    public NoPosee(String poseedor, String objeto) {
        this.poseedor = poseedor;
        this.objeto = objeto;
    }

    public String getPoseedor() { return poseedor; }
    public void setPoseedor(String poseedor) { this.poseedor = poseedor; }
    public String getObjeto() { return objeto; }
    public void setObjeto(String objeto) { this.objeto = objeto; }

    @Override
    public String toString() { return "NoPosee(" + poseedor + ", " + objeto + ")"; }
}

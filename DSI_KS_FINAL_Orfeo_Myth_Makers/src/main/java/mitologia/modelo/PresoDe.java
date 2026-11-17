package mitologia.modelo;

/** Slot presoDe / tienePreso */
public class PresoDe {
    private String preso;
    private String captor;

    public PresoDe(String preso, String captor) {
        this.preso = preso;
        this.captor = captor;
    }

    public String getPreso() { return preso; }
    public void setPreso(String preso) { this.preso = preso; }
    public String getCaptor() { return captor; }
    public void setCaptor(String captor) { this.captor = captor; }

    @Override
    public String toString() { return "PresoDe(" + preso + ", " + captor + ")"; }
}

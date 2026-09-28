public class Proyector extends Equipo {
    private int lumens;
    private boolean wireless;

    public Proyector(int code, String brand, String model, double dailyCost, int lumens, boolean wireless) {
        super(code, brand, model, dailyCost);
        this.lumens = lumens;
        this.wireless = wireless;
    }

    public int getLumens() { return lumens; }
    public void setLumens(int lumens) { this.lumens = lumens; }

    public boolean isWireless() { return wireless; }
    public void setWireless(boolean wireless) { this.wireless = wireless; }

    @Override
    public double calculateExtraCost(int days) {
        if (this.wireless) {
            return 50.0 * days;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Proyector | Lúmenes: " + lumens + " | Inalámbrico: " + (wireless ? "Sí" : "No");
    }
}
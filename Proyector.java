public class Proyector extends Equipo {
    private int lumens;
    private boolean es3D;

    public Proyector(String code, String brand, String model, double dailyCost, int lumens, boolean es3D) {
        super(code, brand, model, dailyCost);
        this.lumens = lumens;
        this.es3D = es3D;
    }

    public int getLumens() { return lumens; }
    public boolean isEs3D() { return es3D; }

    @Override
    protected double calculateExtraCost(int days) {
        double extra = 0.0;
        if (lumens > 3000) extra += 50.0 * days;
        if (es3D) extra += 30.0 * days;
        return extra;
    }

    @Override
    public String getDetails() {
        return "Lúmenes: " + lumens + " | 3D: " + (es3D ? "Sí" : "No");
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Proyector | " + getDetails();
    }
}
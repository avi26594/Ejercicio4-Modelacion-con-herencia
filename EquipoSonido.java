public class EquipoSonido extends Equipo {
    private double powerKW;

    public EquipoSonido(String code, String brand, String model, double dailyCost, double powerKW) {
        super(code, brand, model, dailyCost);
        this.powerKW = powerKW;
    }

    public double getPowerKW() {
        return powerKW;
    }

    public void setPowerKW(double powerKW) {
        this.powerKW = powerKW;
    }

    @Override
    protected double calculateExtraCost(int days) {
        return this.powerKW * 100.0 * days;
    }

    @Override
    public String getDetails() {
        return "Potencia: " + String.format("%.2f", powerKW) + " kW";
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Equipo de Sonido | " + getDetails();
    }
}
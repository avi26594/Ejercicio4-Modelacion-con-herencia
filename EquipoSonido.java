public class EquipoSonido extends Equipo {
    private double powerKw;

    public EquipoSonido(int code, String brand, String model, double dailyCost, double powerKw) {
        super(code, brand, model, dailyCost);
        this.powerKw = powerKw;
    }

    public double getPowerKw() { return powerKw; }
    public void setPowerKw(double powerKw) { this.powerKw = powerKw; }

    @Override
    protected double calculateExtraCost(int days) {
        // Recargo de Q100 por cada kW por cada día de alquiler
        return this.powerKw * 100.0 * days;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Equipo de Sonido | Potencia: " + String.format("%.2f", powerKw) + " kW";
    }
}
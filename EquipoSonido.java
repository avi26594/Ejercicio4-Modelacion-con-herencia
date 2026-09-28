public class EquipoSonido extends Equipo {
    private double powerWatts;

    public EquipoSonido(int code, String brand, String model, double dailyCost, double powerWatts) {
        super(code, brand, model, dailyCost);
        this.powerWatts = powerWatts;
    }

    public double getPowerWatts() { return powerWatts; }
    public void setPowerWatts(double powerWatts) { this.powerWatts = powerWatts; }

    @Override
    public double calculateExtraCost(int days) {
        if (this.powerWatts > 500.0) {
            return 20.0;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Equipo de Sonido | Potencia: " + String.format("%.1f", powerWatts) + "W";
    }
}
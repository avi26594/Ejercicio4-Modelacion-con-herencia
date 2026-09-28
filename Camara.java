public class Camara extends Equipo {
    private int resolucion;

    public Camara(String code, String brand, String model, double dailyCost, int resolucion) {
        super(code, brand, model, dailyCost);
        this.resolucion = resolucion;
    }

    public int getResolucion() { return resolucion; }

    @Override
    protected double calculateExtraCost(int days) {
        if (resolucion >= 2160) {
            return 75.0 * days;
        }
        return 0.0;
    }

    @Override
    public String getDetails() {
        return "Resolución: " + resolucion + "p";
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Cámara | " + getDetails();
    }
}
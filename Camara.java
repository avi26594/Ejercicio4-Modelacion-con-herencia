public class Camara extends Equipo {
    private int maxResolution;

    public Camara(int code, String brand, String model, double dailyCost, int maxResolution) {
        super(code, brand, model, dailyCost);
        this.maxResolution = maxResolution;
    }

    public int getMaxResolution() { 
        return maxResolution; 
    }

    public void setMaxResolution(int maxResolution) { 
        this.maxResolution = maxResolution; 
    }

    @Override
    protected double calculateExtraCost(int days) {
        // Recargo de Q75 tarifa plana si la resolución es mayor a 1080p
        if (this.maxResolution > 1080) {
            return 75.0;
        }
        return 0.0;
    }

    @Override
    public String getDetails() {
        return "Resolución máxima: " + maxResolution + "p";
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Cámara | " + getDetails();
    }
}
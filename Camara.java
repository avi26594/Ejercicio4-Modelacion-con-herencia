public class Camara extends Equipo {
    private int resolutionPx;

    public Camara(int code, String brand, String model, double dailyCost, int resolutionPx) {
        super(code, brand, model, dailyCost);
        this.resolutionPx = resolutionPx;
    }

    public int getResolutionPx() { return resolutionPx; }
    public void setResolutionPx(int resolutionPx) { this.resolutionPx = resolutionPx; }

    @Override
    protected double calculateExtraCost(int days) {
        // Recargo de Q75 por todo el alquiler si resolución > 1080p
        if (this.resolutionPx > 1080) {
            return 75.0;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Cámara | Resolución: " + resolutionPx + "p";
    }
}
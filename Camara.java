public class Camara extends Equipo {
    private int resolutionMpx;

    public Camara(int code, String brand, String model, double dailyCost, int resolutionMpx) {
        super(code, brand, model, dailyCost);
        this.resolutionMpx = resolutionMpx;
    }

    public int getResolutionMpx() { return resolutionMpx; }
    public void setResolutionMpx(int resolutionMpx) { this.resolutionMpx = resolutionMpx; }

    @Override
    public double calculateExtraCost(int days) {
        if (this.resolutionMpx > 20) {
            return 15.0;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Cámara | Resolución: " + resolutionMpx + " MP";
    }
}
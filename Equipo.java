public abstract class Equipo {
    private String code;
    private String brand;
    private String model;
    private double dailyCost;
    private boolean available;

    public Equipo(String code, String brand, String model, double dailyCost) {
        this.code = code;
        this.brand = brand;
        this.model = model;
        this.dailyCost = dailyCost;
        this.available = true;
    }

    public String getCode() {
        return code;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getDailyCost() {
        return dailyCost;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract String getDetails();

    protected abstract double calculateExtraCost(int days);

    public double calculateCost(int days) {
        if (days <= 0) return 0.0;
        return (dailyCost * days) + calculateExtraCost(days);
    }

    @Override
    public String toString() {
        String estado = available ? "Disponible" : "Alquilado";
        return "[" + code + "] " + brand + " " + model + " | Q" + String.format("%.2f", dailyCost) + "/día | Estado: " + estado;
    }
}
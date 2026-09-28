public abstract class Equipo {
    private int code;
    private String brand;
    private String model;
    private double dailyCost;
    private boolean available;

    public Equipo(int code, String brand, String model, double dailyCost) {
        this.code = code;
        this.brand = brand;
        this.model = model;
        this.dailyCost = dailyCost;
        this.available = true;
    }

    public Equipo(int code, String brand, String model, double dailyCost, boolean available) {
        this.code = code;
        this.brand = brand;
        this.model = model;
        this.dailyCost = dailyCost;
        this.available = available;
    }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public double getDailyCost() { return dailyCost; }
    public void setDailyCost(double dailyCost) { this.dailyCost = dailyCost; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    // Template Method
    public double calculateCost(int days) {
        return (dailyCost * days) + calculateExtraCost(days);
    }

    protected abstract double calculateExtraCost(int days);

    @Override
    public String toString() {
        return "Código: " + code +
               " | Marca: " + brand +
               " | Modelo: " + model +
               " | Tarifa diaria: Q" + String.format("%.2f", dailyCost) +
               " | Estado: " + (available ? "Disponible" : "Alquilado");
    }
}
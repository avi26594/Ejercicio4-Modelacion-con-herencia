public abstract class Equipo {
    protected int code;
    protected String brand;
    protected String model;
    protected double dailyCost;
    protected boolean available;

    // Constructor inicial (equipo recién registrado, disponible por defecto)
    public Equipo(int code, String brand, String model, double dailyCost) {
        this.code = code;
        this.brand = brand;
        this.model = model;
        this.dailyCost = dailyCost;
        this.available = true;
    }

    // Constructor completo
    public Equipo(int code, String brand, String model, double dailyCost, boolean available) {
        this.code = code;
        this.brand = brand;
        this.model = model;
        this.dailyCost = dailyCost;
        this.available = available;
    }

    // Getters y Setters alineados a los lineamientos de la guía
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(double dailyCost) {
        this.dailyCost = dailyCost;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Patrón Template Method: cálculo base + costo extra según la subclase
    public double calculateCost(int days) {
        return (dailyCost * days) + calculateExtraCost(days);
    }

    // Métodos abstractos para polimorfismo
    protected abstract double calculateExtraCost(int days);

    public abstract String getDetails();

    @Override
    public String toString() {
        return "Código: " + code + 
               " | Marca: " + brand + 
               " | Modelo: " + model + 
               " | Tarifa diaria: Q" + String.format("%.2f", dailyCost) + 
               " | Estado: " + (available ? "Disponible" : "Alquilado");
    }
}
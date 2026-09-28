import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario {
    // CÓDIGO CLASE POO: Estructura de Map para almacenar el inventario
    private final Map<Integer, Equipo> equipos;
    private double ingresosTotales; // AJUSTE: Acumulador de ingresos requeridos por la guía

    public Inventario() {
        this.equipos = new HashMap<>();
        this.ingresosTotales = 0.0;
        precargarDatosIniciales(); // AJUSTE: Cumple el requisito de iniciar con 2 equipos por categoría
    }

    // AJUSTE: Precarga automática exigida en el enunciado (2 de cada tipo)
    private void precargarDatosIniciales() {
        agregarEquipo(new Proyector(101, "Epson", "PowerLite E20", 25.00, 3400, true));
        agregarEquipo(new Proyector(102, "ViewSonic", "PA503S", 18.00, 2700, false));
        
        agregarEquipo(new Camara(201, "Canon", "EOS Rebel T7", 35.00, 24));
        agregarEquipo(new Camara(202, "Sony", "Alpha a6000", 30.00, 16));
        
        agregarEquipo(new EquipoSonido(301, "JBL", "EON615", 45.00, 1000.0));
        agregarEquipo(new EquipoSonido(302, "Yamaha", "StagePas 400BT", 32.00, 400.5));
    }

    public boolean agregarEquipo(Equipo equipo) {
        if (equipos.containsKey(equipo.getCode())) {
            return false;
        }
        equipos.put(equipo.getCode(), equipo);
        return true;
    }

    public Equipo buscarEquipo(int code) {
        return equipos.get(code);
    }

    public List<Equipo> obtenerTodos() {
        return new ArrayList<>(equipos.values());
    }

    // AJUSTE: Confirmar alquiler y registrar el ingreso generado
    public boolean confirmarAlquiler(int code, int dias) {
        Equipo eq = buscarEquipo(code);
        if (eq == null || !eq.isAvailable()) {
            return false; // Inconsistencia: no existe o ya está alquilado
        }
        double monto = eq.calculateCost(dias);
        eq.setAvailable(false);
        ingresosTotales += monto; // Modifica ingresos solo al confirmar
        return true;
    }

    // AJUSTE: Registrar devolución de equipo
    public boolean registrarDevolucion(int code) {
        Equipo eq = buscarEquipo(code);
        if (eq == null || eq.isAvailable()) {
            return false; // Inconsistencia: no existe o ya estaba disponible
        }
        eq.setAvailable(true);
        return true;
    }

    public double getIngresosTotales() {
        return ingresosTotales;
    }
}
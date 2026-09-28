import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario {
    private final Map<String, Equipo> equipos;
    private double ingresosTotales;

    public Inventario() {
        this.equipos = new HashMap<>();
        this.ingresosTotales = 0.0;
        precargarDatosIniciales();
    }

    private void precargarDatosIniciales() {
        agregarEquipo(new Proyector("101", "Epson", "PowerLite E20", 150.00, 3400, true));
        agregarEquipo(new Proyector("102", "ViewSonic", "PA503S", 100.00, 2700, false));

        agregarEquipo(new Camara("201", "Sony", "FX30", 200.00, 2160));
        agregarEquipo(new Camara("202", "Canon", "T7", 120.00, 1080));

        agregarEquipo(new EquipoSonido("301", "JBL", "EON615", 200.00, 1.5));
        agregarEquipo(new EquipoSonido("302", "Yamaha", "StagePas", 150.00, 0.4));
    }

    public boolean agregarEquipo(Equipo equipo) {
        if (equipo == null || equipo.getCode() == null || equipo.getCode().trim().isEmpty()) {
            return false;
        }
        if (equipos.containsKey(equipo.getCode())) {
            return false;
        }
        equipos.put(equipo.getCode(), equipo);
        return true;
    }

    public Equipo buscarEquipo(String code) {
        if (code == null || code.trim().isEmpty()) {
            return null;
        }
        return equipos.get(code);
    }

    public List<Equipo> obtenerTodos() {
        return new ArrayList<>(equipos.values());
    }

    public double cotizar(String code, int dias) {
        Equipo eq = buscarEquipo(code);
        if (eq == null || dias <= 0) {
            return -1.0;
        }
        return eq.calculateCost(dias);
    }

    public boolean confirmarAlquiler(String code, int dias) {
        Equipo eq = buscarEquipo(code);
        if (eq == null || !eq.isAvailable() || dias <= 0) {
            return false;
        }
        double monto = eq.calculateCost(dias);
        eq.setAvailable(false);
        ingresosTotales += monto;
        return true;
    }

    public boolean registrarDevolucion(String code) {
        Equipo eq = buscarEquipo(code);
        if (eq == null || eq.isAvailable()) {
            return false;
        }
        eq.setAvailable(true);
        return true;
    }

    public double getIngresosTotales() {
        return ingresosTotales;
    }

    public void mostrarReporteDetallado() {
        int projDisp = 0, projAlq = 0;
        int camDisp = 0, camAlq = 0;
        int sonDisp = 0, sonAlq = 0;

        for (Equipo eq : equipos.values()) {
            if (eq instanceof Proyector) {
                if (eq.isAvailable()) projDisp++; else projAlq++;
            } else if (eq instanceof Camara) {
                if (eq.isAvailable()) camDisp++; else camAlq++;
            } else if (eq instanceof EquipoSonido) {
                if (eq.isAvailable()) sonDisp++; else sonAlq++;
            }
        }

        System.out.println("\n=============================================");
        System.out.println("               REPORTE GENERAL               ");
        System.out.println("=============================================");
        System.out.println("Proyectores      : Total: " + (projDisp + projAlq) + " | Disponibles: " + projDisp + " | Alquilados: " + projAlq);
        System.out.println("Cámaras          : Total: " + (camDisp + camAlq) + " | Disponibles: " + camDisp + " | Alquilados: " + camAlq);
        System.out.println("Equipos de Sonido: Total: " + (sonDisp + sonAlq) + " | Disponibles: " + sonDisp + " | Alquilados: " + sonAlq);
        System.out.println("---------------------------------------------");
        System.out.println("TOTAL EQUIPOS REGISTRADOS: " + equipos.size());
        System.out.println("INGRESOS TOTALES ACUMULADOS: Q" + String.format("%.2f", ingresosTotales));
        System.out.println("=============================================");
    }
}
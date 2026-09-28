import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Inventario inventario = new Inventario();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEnteroPositivo("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarEquipo();
                case 2 -> consultarInventario();
                case 3 -> cotizarAlquiler();
                case 4 -> confirmarAlquiler();
                case 5 -> registrarDevolucion();
                case 6 -> mostrarReporteGeneral();
                case 7 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
            System.out.println();
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("===== SISTEMA DE GESTIÓN DE ALQUILERES =====");
        System.out.println("1. Registrar nuevo equipo");
        System.out.println("2. Consultar inventario");
        System.out.println("3. Cotizar alquiler");
        System.out.println("4. Confirmar alquiler");
        System.out.println("5. Registrar devolución");
        System.out.println("6. Obtener reporte general");
        System.out.println("7. Salir");
        System.out.println("===========================================");
    }

    private static void registrarEquipo() {
        System.out.println("\n--- Registro de Equipo ---");
        System.out.println("1. Proyector | 2. Cámara | 3. Equipo de Sonido");
        int tipo = leerEnteroPositivo("Seleccione la categoría: ");

        if (tipo < 1 || tipo > 3) {
            System.out.println("Categoría no válida.");
            return;
        }

        int code = leerEnteroPositivo("Ingrese el código único de inventario: ");
        if (inventario.buscarEquipo(code) != null) {
            System.out.println("Error: Ya existe un equipo con el código " + code + ". Registro rechazado.");
            return;
        }

        System.out.print("Ingrese la marca: ");
        String brand = scanner.nextLine().trim();
        while (brand.isEmpty()) {
            System.out.print("La marca no puede estar vacía. Ingrese marca: ");
            brand = scanner.nextLine().trim();
        }

        System.out.print("Ingrese el modelo: ");
        String model = scanner.nextLine().trim();
        while (model.isEmpty()) {
            System.out.print("El modelo no puede estar vacío. Ingrese modelo: ");
            model = scanner.nextLine().trim();
        }

        double dailyCost = leerDoublePositivo("Ingrese la tarifa diaria ($): ");

        Equipo nuevo;
        switch (tipo) {
            case 1 -> {
                int lumens = leerEnteroPositivo("Ingrese los lúmenes (entero mayor a 0): ");
                System.out.print("¿Es inalámbrico? (s/n): ");
                boolean wireless = scanner.nextLine().trim().equalsIgnoreCase("s");
                nuevo = new Proyector(code, brand, model, dailyCost, lumens, wireless);
            }
            case 2 -> {
                int res = leerEnteroPositivo("Ingrese la resolución en MP (entero mayor a 0): ");
                nuevo = new Camara(code, brand, model, dailyCost, res);
            }
            default -> {
                double power = leerDoublePositivo("Ingrese la potencia en Watts (mayor a 0): ");
                nuevo = new EquipoSonido(code, brand, model, dailyCost, power);
            }
        }

        if (inventario.agregarEquipo(nuevo)) {
            System.out.println("¡Equipo registrado con éxito!");
        }
    }

    private static void consultarInventario() {
        System.out.println("\n--- Inventario General ---");
        List<Equipo> lista = inventario.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Equipo eq : lista) {
                System.out.println(eq.toString());
            }
        }
    }

    private static void cotizarAlquiler() {
        System.out.println("\n--- Cotizar Alquiler ---");
        int code = leerEnteroPositivo("Ingrese el código del equipo: ");
        Equipo eq = inventario.buscarEquipo(code);

        if (eq == null) {
            System.out.println("Error: Código de inventario no encontrado.");
            return;
        }

        int dias = leerEnteroPositivo("Ingrese los días de alquiler: ");
        double base = eq.getDailyCost() * dias;
        double extra = eq.calculateExtraCost(dias);
        double total = eq.calculateCost(dias);

        System.out.println("\n--- RESULTADO DE LA COTIZACIÓN ---");
        System.out.println("Equipo: " + eq.getBrand() + " " + eq.getModel() + " (" + (eq.isAvailable() ? "Disponible" : "Alquilado") + ")");
        System.out.println("Tarifa base (" + dias + " días): $" + String.format("%.2f", base));
        System.out.println("Cobros adicionales: $" + String.format("%.2f", extra));
        System.out.println("MONTO TOTAL COTIZADO: $" + String.format("%.2f", total));
        System.out.println("(Nota: La cotización no altera los ingresos ni el estado del inventario)");
    }

    private static void confirmarAlquiler() {
        System.out.println("\n--- Confirmar Alquiler ---");
        int code = leerEnteroPositivo("Ingrese el código del equipo a alquilar: ");
        Equipo eq = inventario.buscarEquipo(code);

        if (eq == null) {
            System.out.println("Error: El código de equipo no existe.");
            return;
        }

        if (!eq.isAvailable()) {
            System.out.println("Error: El equipo con código " + code + " ya se encuentra ALQUILADO. Operación cancelada.");
            return;
        }

        int dias = leerEnteroPositivo("Ingrese el número de días de alquiler: ");
        double monto = eq.calculateCost(dias);

        if (inventario.confirmarAlquiler(code, dias)) {
            System.out.println("¡Alquiler confirmado exitosamente!");
            System.out.println("Monto cobrado e ingresado: $" + String.format("%.2f", monto));
        }
    }

    private static void registrarDevolucion() {
        System.out.println("\n--- Registrar Devolución ---");
        int code = leerEnteroPositivo("Ingrese el código del equipo a devolver: ");
        Equipo eq = inventario.buscarEquipo(code);

        if (eq == null) {
            System.out.println("Error: Código de inventario no encontrado.");
            return;
        }

        if (eq.isAvailable()) {
            System.out.println("Error: El equipo con código " + code + " ya está DISPONIBLE en el inventario. Devolución no válida.");
            return;
        }

        if (inventario.registrarDevolucion(code)) {
            System.out.println("¡Devolución registrada exitosamente! El equipo vuelve a estar disponible.");
        }
    }

    private static void mostrarReporteGeneral() {
        System.out.println("\n===========================================");
        System.out.println("             REPORTE GENERAL               ");
        System.out.println("===========================================");
        consultarInventario();
        System.out.println("-------------------------------------------");
        System.out.println("INGRESOS TOTALES ACUMULADOS: $" + String.format("%.2f", inventario.getIngresosTotales()));
        System.out.println("===========================================");
    }

    private static int leerEnteroPositivo(String mensaje) {
        int valor = -1;
        while (valor <= 0) {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor <= 0) {
                    System.out.println("Error: Debe ingresar un entero mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Formato incorrecto. Ingrese un entero válido.");
            }
        }
        return valor;
    }

    private static double leerDoublePositivo(String mensaje) {
        double valor = -1.0;
        while (valor <= 0) {
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine().trim());
                if (valor <= 0) {
                    System.out.println("Error: Debe ingresar un valor numérico mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Formato incorrecto. Ingrese un valor numérico válido.");
            }
        }
        return valor;
    }
}
import java.util.Scanner;

public class Main {
    private static final Inventario inventario = new Inventario();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarEquipo();
                    break;
                case 2:
                    consultarInventario();
                    break;
                case 3:
                    cotizarAlquiler();
                    break;
                case 4:
                    confirmarAlquiler();
                    break;
                case 5:
                    devolverEquipo();
                    break;
                case 6:
                    mostrarReporteGeneral();
                    break;
                case 7:
                    salir = true;
                    System.out.println("\nGracias por utilizar el sistema de EnEscena.");
                    break;
                default:
                    System.out.println("\n[Error] Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=============================================");
        System.out.println("       SISTEMA DE ALQUILER - ENESCENA        ");
        System.out.println("=============================================");
        System.out.println("1. Registrar nuevo equipo");
        System.out.println("2. Consultar inventario / equipo");
        System.out.println("3. Cotizar alquiler");
        System.out.println("4. Confirmar alquiler");
        System.out.println("5. Registrar devolución");
        System.out.println("6. Ver reporte general");
        System.out.println("7. Salir");
        System.out.println("=============================================");
    }

    private static void registrarEquipo() {
        System.out.println("\n--- REGISTRO DE EQUIPO ---");
        System.out.println("1. Proyector");
        System.out.println("2. Cámara de Video");
        System.out.println("3. Equipo de Sonido");
        int tipo = leerEntero("Seleccione el tipo de equipo: ");

        if (tipo < 1 || tipo > 3) {
            System.out.println("[Error] Tipo de equipo inválido.");
            return;
        }

        String codigo = leerTexto("Ingrese el código único del equipo: ");
        if (inventario.buscarEquipo(codigo) != null) {
            System.out.println("[Error] Ya existe un equipo registrado con el código " + codigo + ".");
            return;
        }

        String marca = leerTexto("Ingrese la marca: ");
        String modelo = leerTexto("Ingrese el modelo: ");
        double tarifaDiaria = leerDoublePositivo("Ingrese la tarifa diaria (Q): ");

        Equipo nuevoEquipo = null;

        switch (tipo) {
            case 1:
                int lumenes = leerEnteroPositivo("Ingrese la luminosidad en lúmenes (entero): ");
                boolean inalambrico = leerBooleano("¿Tiene conectividad inalámbrica? (s/n): ");
                nuevoEquipo = new Proyector(codigo, marca, modelo, tarifaDiaria, lumenes, inalambrico);
                break;

            case 2:
                int resolucion = leerEnteroPositivo("Ingrese la resolución máxima en píxeles verticales (ej. 1080, 2160): ");
                nuevoEquipo = new Camara(codigo, marca, modelo, tarifaDiaria, resolucion);
                break;

            case 3:
                double potencia = leerDoublePositivo("Ingrese la potencia nominal en kW (ej. 1.5): ");
                nuevoEquipo = new EquipoSonido(codigo, marca, modelo, tarifaDiaria, potencia);
                break;
        }

        if (inventario.agregarEquipo(nuevoEquipo)) {
            System.out.println("\n[Éxito] Equipo registrado correctamente y disponible para alquiler.");
        } else {
            System.out.println("\n[Error] No se pudo registrar el equipo.");
        }
    }

    private static void consultarInventario() {
        System.out.println("\n--- CONSULTA DE INVENTARIO ---");
        System.out.println("1. Consultar por código de equipo");
        System.out.println("2. Ver listado completo de equipos");
        int subOpcion = leerEntero("Seleccione una opción: ");

        if (subOpcion == 1) {
            String codigo = leerTexto("Ingrese el código a buscar: ");
            Equipo eq = inventario.buscarEquipo(codigo);
            if (eq != null) {
                System.out.println("\n[Información del Equipo]");
                System.out.println(eq);
            } else {
                System.out.println("\n[Error] No se encontró ningún equipo con el código " + codigo + ".");
            }
        } else if (subOpcion == 2) {
            System.out.println("\n=== LISTADO COMPLETO DE EQUIPOS ===");
            for (Equipo eq : inventario.obtenerTodos()) {
                System.out.println(eq);
            }
        } else {
            System.out.println("[Error] Opción no válida.");
        }
    }

    private static void cotizarAlquiler() {
        System.out.println("\n--- COTIZAR ALQUILER ---");
        String codigo = leerTexto("Ingrese el código del equipo a cotizar: ");
        Equipo eq = inventario.buscarEquipo(codigo);

        if (eq == null) {
            System.out.println("[Error] No existe un equipo con el código " + codigo + ".");
            return;
        }

        int dias = leerEnteroPositivo("Ingrese la cantidad de días de alquiler: ");
        double costoTotal = eq.calculateCost(dias);

        System.out.println("\n=============================================");
        System.out.println("             RESUMEN DE COTIZACIÓN           ");
        System.out.println("=============================================");
        System.out.println("Equipo: " + eq.getBrand() + " " + eq.getModel() + " (Código: " + eq.getCode() + ")");
        System.out.println("Estado actual: " + (eq.isAvailable() ? "Disponible" : "Ocupado / Alquilado"));
        System.out.println("Días solicitados: " + dias);
        System.out.println("Monto total estimado: Q" + String.format("%.2f", costoTotal));
        System.out.println("=============================================");
        System.out.println("(Nota: Esta cotización es informativa y no altera el inventario ni los ingresos)");
    }

    private static void confirmarAlquiler() {
        System.out.println("\n--- CONFIRMAR ALQUILER ---");
        String codigo = leerTexto("Ingrese el código del equipo a alquilar: ");
        Equipo eq = inventario.buscarEquipo(codigo);

        if (eq == null) {
            System.out.println("[Error] No existe un equipo con el código " + codigo + ".");
            return;
        }

        if (!eq.isAvailable()) {
            System.out.println("[Error] El equipo con código " + codigo + " ya se encuentra ALQUILADO.");
            return;
        }

        int dias = leerEnteroPositivo("Ingrese la cantidad de días de alquiler: ");
        double costoTotal = eq.calculateCost(dias);

        System.out.println("\nEquipo seleccionado: " + eq.getBrand() + " " + eq.getModel());
        System.out.println("Monto total a cobrar: Q" + String.format("%.2f", costoTotal));

        boolean aceptar = leerBooleano("¿Desea confirmar la operación y realizar el cobro? (s/n): ");

        if (aceptar) {
            if (inventario.confirmarAlquiler(codigo, dias)) {
                System.out.println("\n[Éxito] Alquiler confirmado. Se registró un ingreso de Q" + String.format("%.2f", costoTotal));
            } else {
                System.out.println("\n[Error] No se pudo procesar la confirmación.");
            }
        } else {
            System.out.println("\n[Cancelado] La operación fue cancelada por el usuario. No se realizaron cobros.");
        }
    }

    private static void devolverEquipo() {
        System.out.println("\n--- REGISTRAR DEVOLUCIÓN ---");
        String codigo = leerTexto("Ingrese el código del equipo devuelto: ");

        if (inventario.registrarDevolucion(codigo)) {
            System.out.println("\n[Éxito] Devolución registrada correctamente. El equipo vuelve a estar disponible.");
        } else {
            System.out.println("\n[Error] El equipo con código " + codigo + " ya está DISPONIBLE en el inventario o no existe.");
        }
    }

    private static void mostrarReporteGeneral() {
        inventario.mostrarReporteDetallado();
    }

    // --- MÉTODOS DE LECTURA Y VALIDACIÓN DE ENTRADAS ---

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String entrada = scanner.nextLine().trim();
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("[Error] Formato incorrecto. Ingrese un entero válido.");
            }
        }
    }

    private static int leerEnteroPositivo(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor > 0) {
                return valor;
            }
            System.out.println("[Error] El valor debe ser un entero estrictamente mayor a cero.");
        }
    }

    private static double leerDoublePositivo(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String entrada = scanner.nextLine().trim();
                double valor = Double.parseDouble(entrada);
                if (valor > 0) {
                    return valor;
                }
                System.out.println("[Error] El valor debe ser estrictamente mayor a cero.");
            } catch (NumberFormatException e) {
                System.out.println("[Error] Debe ingresar un valor numérico válido.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("[Error] El campo no puede estar vacío.");
        }
    }

    private static boolean leerBooleano(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim().toLowerCase();
            if (entrada.equals("s") || entrada.equals("si") || entrada.equals("sí")) {
                return true;
            } else if (entrada.equals("n") || entrada.equals("no")) {
                return false;
            }
            System.out.println("[Error] Ingrese 's' para Sí o 'n' para No.");
        }
    }
}
# Ejercicio 4

### Requisitos Funcionales

1. No pueden existir dos equipos con el mismo código en el inventario.
2. Al registrar un equipo, este queda inmediatamente disponible.
3. Poder buscar equipos por su código único de inventario.
4. Poder cotizar un alquiler en función del equipo y la duración en días.
5. Confirmar una cotización (confirmar alquiler), cambiando el estado a no disponible y acumulando los ingresos.
6. No se puede alquilar un equipo que ya se encuentra alquilado.
7. Registrar la devolución de un equipo, restaurando su disponibilidad.
8. Obtener el reporte general que muestre el estado del inventario y los ingresos totales acumulados.
9. Validar que las tarifas, luminosidad, resolución y potencia sean mayores a cero.
10. Asegurar que las cotizaciones y devoluciones no alteren indebidamente los ingresos.

---

### Estructura de Clases y Diseño UML

abstract class Equipo
- codigo: String
- marca: String
- modelo: String
- tarifaDiaria: double
- disponible: boolean
+ calcularCostoAlquiler(dias: int): double [abstracto]

Proyector extends Equipo
- lumenes: int
- inalambrico: boolean
+ calcularCostoAlquiler(dias: int): double

Camara extends Equipo
- resolucion: double
+ calcularCostoAlquiler(dias: int): double

EquipoSonido extends Equipo
- potencia: double
+ calcularCostoAlquiler(dias: int): double

Inventario
- equipos: Map<String, Equipo>
- ingresosTotales: double
+ registrarEquipo(equipo: Equipo): boolean
+ consultarEquipo(codigo: String): Equipo
+ cotizar(codigo: String, duracionDias: int): double
+ confirmarCotizacion(codigo: String, duracionDias: int): boolean
+ devolverEquipo(codigo: String): boolean
+ generarReporte(): void

# Sistema de Alquiler de Equipos Audiovisuales

Este repositorio contiene la solución desarrollada en Java para el Ejercicio 04 de la asignatura Programación Orientada a Objetos (CC2008). El sistema aplica los principios del paradigma orientado a objetos (Herencia, Polimorfismo, Encapsulamiento y Abstracción) para gestionar la cotización, alquiler y devolución de equipos audiovisuales.

---

## Requisitos del Sistema

* **Java Development Kit (JDK):** Versión 11 o superior.
* **Consola de Comandos / Terminal:** Git Bash, Command Prompt (cmd) o PowerShell.

---

## Instrucciones de Compilación y Ejecución

Sigue estos pasos en la terminal para compilar y ejecutar el programa:

1. **Clonar el repositorio** (si aún no lo has descargado):
   ```bash
   git clone [https://github.com/avi26594/Ejercicio4-Modelacion-con-herencia.git](https://github.com/avi26594/Ejercicio4-Modelacion-con-herencia.git)
   cd Ejercicio4-Modelacion-con-herencia
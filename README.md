# Ejercicio 4 - Sistema de Alquiler de Equipos Audiovisuales (EnEscena)

Este repositorio contiene la solución desarrollada en Java para el Ejercicio 04 de la asignatura Programación Orientada a Objetos (CC2008) de la Universidad del Valle de Guatemala. El sistema aplica los principios de la Programación Orientada a Objetos (Herencia, Polimorfismo, Encapsulamiento y Abstracción) para gestionar la cotización, alquiler y devolución de equipos audiovisuales.

---

### Requisitos Funcionales y Reglas de Negocio

1. **Unicidad de Código:** No pueden existir dos equipos con el mismo código único entero en el inventario.
2. **Disponibilidad Inicial:** Al registrar un equipo, este queda inmediatamente disponible para alquiler.
3. **Búsqueda y Consulta:** Permitir la búsqueda de equipos por su código único o consultar el listado completo.
4. **Cotización sin Modificación:** Calcular cotizaciones en función del equipo y días solicitados sin alterar el estado ni los ingresos.
5. **Confirmación de Alquiler:** Confirmar un alquiler cambiando el estado del equipo a no disponible y registrando el cobro.
6. **Control de Duplicidad de Alquiler:** Impedir el alquiler de equipos que ya se encuentran ocupados.
7. **Registro de Devolución:** Restaurar la disponibilidad de un equipo devuelto sin modificar los ingresos ya acumulados.
8. **Reporte General:** Mostrar el estado del inventario por categoría (totales, disponibles, alquilados) y el dinero total acumulado.
9. **Validaciones de Entrada:** Garantizar que tarifas, lúmenes, resoluciones, potencias y días de alquiler sean mayores a cero, evitando fallos por formatos inválidos.

---

### Estructura de Clases y Diseño UML

```text
abstract class Equipo
- code: int
- brand: String
- model: String
- dailyCost: double
- available: boolean
+ calculateCost(dias: int): double [abstracto]
+ getCode(): int
+ isAvailable(): boolean
+ setAvailable(available: boolean): void

Proyector extends Equipo
- lumens: int
- wireless: boolean
+ calculateCost(dias: int): double

Camara extends Equipo
- maxResolution: int
+ calculateCost(dias: int): double

EquipoSonido extends Equipo
- powerKW: double
+ calculateCost(dias: int): double

Inventario
- equipos: Map<Integer, Equipo>
- ingresosTotales: double
+ agregarEquipo(equipo: Equipo): boolean
+ buscarEquipo(code: int): Equipo
+ obtenerTodos(): List<Equipo>
+ cotizar(code: int, dias: int): double
+ confirmarAlquiler(code: int, dias: int): boolean
+ registrarDevolucion(code: int): boolean
+ mostrarReporteDetallado(): void
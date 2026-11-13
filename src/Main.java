import java.util.Arrays;

/**
 * Demostración de tres estrategias algorítmicas: divide y vencerás,
 * programación dinámica y algoritmo voraz.
 */
public class Main {
    public static void main(String[] args) {
        // Ejecuta una demostración de cada técnica incluida en el proyecto.
        demostrarInversiones();
        demostrarMochila();
        demostrarCambio();
    }

    private static void demostrarInversiones() {
        // En este arreglo hay tres pares fuera de orden: (2,1), (4,1) y (4,3).
        int[] datos = {2, 4, 1, 3, 5};
        // Se copia para conservar los datos originales, porque count ordena el arreglo.
        long cantidad = NumberOfInversions.count(Arrays.copyOf(datos, datos.length));
        System.out.println("=== Divide y vencerás: número de inversiones ===");
        System.out.println("Arreglo: " + Arrays.toString(datos));
        System.out.println("Inversiones: " + cantidad);
        System.out.println();
    }

    private static void demostrarMochila() {
        // Cada posición relaciona el peso y el valor del mismo objeto.
        int[] pesos = {2, 3, 4, 5};
        int[] valores = {3, 4, 5, 6};
        int capacidad = 5;
        Knapsack.Resultado resultado = Knapsack.solve(pesos, valores, capacidad);
        System.out.println("=== Programación dinámica: mochila 0/1 ===");
        System.out.println("Pesos: " + Arrays.toString(pesos));
        System.out.println("Valores: " + Arrays.toString(valores));
        System.out.println("Capacidad: " + capacidad);
        System.out.println("Valor máximo: " + resultado.valorMaximo());
        System.out.println("Índices de objetos elegidos (desde 0): " + resultado.indicesElegidos());
        System.out.println();
    }

    private static void demostrarCambio() {
        // El algoritmo procesa las monedas desde la denominación más alta a la más baja.
        int[] denominaciones = {25, 10, 5, 1};
        int monto = 63;
        GreedyCoinChange.Resultado resultado = GreedyCoinChange.solve(monto, denominaciones);
        System.out.println("=== Algoritmo voraz: cambio de monedas ===");
        System.out.println("Denominaciones: " + Arrays.toString(denominaciones));
        System.out.println("Monto: " + monto);
        System.out.println("Monedas utilizadas: " + resultado.monedas());
        System.out.println("Cantidad total de monedas: " + resultado.cantidad());
        System.out.println("El método voraz depende de las denominaciones; no siempre da el mínimo para cualquier sistema.");
    }
}

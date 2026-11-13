import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solución de mochila 0/1 mediante programación dinámica.
 * Cada objeto se puede incluir una vez como máximo. El tiempo y la memoria
 * utilizados son O(n * capacidad).
 */
public final class Knapsack {
    private Knapsack() { }

    public static Resultado solve(int[] pesos, int[] valores, int capacidad) {
        // Verifica que la entrada represente objetos válidos y una capacidad posible.
        if (pesos == null || valores == null || pesos.length != valores.length || capacidad < 0) {
            throw new IllegalArgumentException("Pesos y valores deben tener igual longitud y capacidad no negativa.");
        }
        for (int peso : pesos) {
            if (peso < 0) throw new IllegalArgumentException("Los pesos no pueden ser negativos.");
        }
        int n = pesos.length;
        // mejor[i][c] guarda el mayor valor usando los primeros i objetos y capacidad c.
        int[][] mejor = new int[n + 1][capacidad + 1];
        // Registra si la solución óptima de cada celda incluye el objeto i - 1.
        boolean[][] seToma = new boolean[n + 1][capacidad + 1];
        for (int i = 1; i <= n; i++) {
            for (int c = 0; c <= capacidad; c++) {
                // Caso inicial: no tomar este objeto conserva el mejor valor anterior.
                mejor[i][c] = mejor[i - 1][c];
                if (pesos[i - 1] <= c) {
                    // Compara el valor anterior con el resultado de incluir el objeto actual.
                    int conObjeto = mejor[i - 1][c - pesos[i - 1]] + valores[i - 1];
                    if (conObjeto > mejor[i][c]) {
                        mejor[i][c] = conObjeto;
                        seToma[i][c] = true;
                    }
                }
            }
        }
        List<Integer> elegidos = new ArrayList<>();
        int c = capacidad;
        // Recorre las decisiones hacia atrás para reconstruir qué objetos se escogieron.
        for (int i = n; i > 0; i--) {
            if (seToma[i][c]) {
                elegidos.add(i - 1);
                c -= pesos[i - 1];
            }
        }
        // La reconstrucción fue de atrás hacia adelante; se devuelve en orden original.
        Collections.reverse(elegidos);
        return new Resultado(mejor[n][capacidad], List.copyOf(elegidos));
    }

    public record Resultado(int valorMaximo, List<Integer> indicesElegidos) { }
}

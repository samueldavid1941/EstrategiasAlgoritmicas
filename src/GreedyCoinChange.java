import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cambio de monedas con estrategia voraz: usa primero la moneda de mayor valor.
 * Esta elección es óptima para algunos sistemas de monedas, pero no para todos.
 */
public final class GreedyCoinChange {
    private GreedyCoinChange() { }

    public static Resultado solve(int monto, int[] denominaciones) {
        // Evita entradas que no tienen sentido para el problema de cambio.
        if (monto < 0 || denominaciones == null) {
            throw new IllegalArgumentException("El monto debe ser no negativo y las denominaciones no pueden ser null.");
        }
        // Ordena una copia para no modificar el arreglo recibido por el usuario.
        int[] monedas = Arrays.copyOf(denominaciones, denominaciones.length);
        for (int moneda : monedas) {
            if (moneda <= 0) throw new IllegalArgumentException("Las denominaciones deben ser positivas.");
        }
        Arrays.sort(monedas);
        List<Integer> usadas = new ArrayList<>();
        int restante = monto;
        // Toma tantas monedas como quepan de cada denominación, de mayor a menor.
        for (int i = monedas.length - 1; i >= 0; i--) {
            int cantidad = restante / monedas[i];
            for (int j = 0; j < cantidad; j++) usadas.add(monedas[i]);
            restante %= monedas[i];
        }
        // Si queda saldo, ninguna combinación voraz encontrada forma exactamente el monto.
        if (restante != 0) {
            throw new IllegalArgumentException("No es posible formar el monto con estas denominaciones.");
        }
        return new Resultado(List.copyOf(usadas));
    }

    public record Resultado(List<Integer> monedas) {
        public int cantidad() { return monedas.size(); }
    }
}

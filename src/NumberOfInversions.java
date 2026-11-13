/**
 * Cuenta pares (i, j) tales que i < j y datos[i] > datos[j].
 * Divide el arreglo en mitades y aprovecha la mezcla de merge sort para
 * contar inversiones en O(n log n), en vez de comparar todos los pares.
 */
public final class NumberOfInversions {
    private NumberOfInversions() { }

    /** Ordena el arreglo recibido y devuelve la cantidad de inversiones. */
    public static long count(int[] datos) {
        if (datos == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null.");
        }
        // El arreglo auxiliar se reutiliza en todas las llamadas recursivas.
        return sortAndCount(datos, new int[datos.length], 0, datos.length);
    }

    private static long sortAndCount(int[] datos, int[] auxiliar, int inicio, int fin) {
        // Un segmento de cero o un elemento ya está ordenado y no tiene inversiones.
        if (fin - inicio <= 1) return 0;
        int medio = inicio + (fin - inicio) / 2;
        // Cuenta las inversiones que quedan dentro de cada mitad.
        long total = sortAndCount(datos, auxiliar, inicio, medio)
                + sortAndCount(datos, auxiliar, medio, fin);
        // Cuenta las que cruzan ambas mitades mientras las combina ordenadamente.
        total += mergeAndCount(datos, auxiliar, inicio, medio, fin);
        return total;
    }

    private static long mergeAndCount(int[] datos, int[] auxiliar, int inicio, int medio, int fin) {
        int izquierda = inicio;
        int derecha = medio;
        int destino = inicio;
        long cruces = 0;
        while (izquierda < medio && derecha < fin) {
            if (datos[izquierda] <= datos[derecha]) {
                auxiliar[destino++] = datos[izquierda++];
            } else {
                auxiliar[destino++] = datos[derecha++];
                // El valor derecho es menor que todos los elementos restantes a la izquierda.
                cruces += medio - izquierda;
            }
        }
        while (izquierda < medio) auxiliar[destino++] = datos[izquierda++];
        while (derecha < fin) auxiliar[destino++] = datos[derecha++];
        // Copia el segmento ya ordenado de vuelta al arreglo original.
        System.arraycopy(auxiliar, inicio, datos, inicio, fin - inicio);
        return cruces;
    }
}

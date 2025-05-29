package src.utils;

/**
 * Utilidades matemáticas y algoritmos personalizados
 * Reemplaza funcionalidades de Collections de Java
 */
public class UtilMatematicas {

    /**
     * Generador de números pseudoaleatorios simple
     * Implementación del algoritmo Linear Congruential Generator (LCG)
     */
    private static class GeneradorAleatorio {
        private long semilla;
        private static final long A = 1664525L;
        private static final long C = 1013904223L;
        private static final long M = 4294967296L; // 2^32

        public GeneradorAleatorio() {
            this.semilla = System.currentTimeMillis();
        }

        public GeneradorAleatorio(long semilla) {
            this.semilla = semilla;
        }

        /**
         * Genera el siguiente número aleatorio
         * 
         * @return un número aleatorio entre 0 y M-1
         */
        public long siguiente() {
            semilla = (A * semilla + C) % M;
            return semilla;
        }

        /**
         * Genera un número aleatorio en un rango específico
         * 
         * @param max el valor máximo (exclusivo)
         * @return un número aleatorio entre 0 y max-1
         */
        public int siguienteInt(int max) {
            if (max <= 0) {
                throw new IllegalArgumentException("El máximo debe ser positivo");
            }
            return (int) (siguiente() % max);
        }
    }

    private static final GeneradorAleatorio generador = new GeneradorAleatorio();

    /**
     * Mezcla un arreglo usando el algoritmo Fisher-Yates
     * Reemplaza Collections.shuffle() de Java
     * 
     * @param arreglo el arreglo a mezclar
     */
    public static <T> void mezclar(T[] arreglo) {
        if (arreglo == null || arreglo.length <= 1) {
            return;
        }

        // Algoritmo Fisher-Yates para mezcla aleatoria
        for (int i = arreglo.length - 1; i > 0; i--) {
            int j = generador.siguienteInt(i + 1);
            intercambiar(arreglo, i, j);
        }
    }

    /**
     * Mezcla una lista enlazada convirtiendo a arreglo temporalmente
     * 
     * @param lista la lista a mezclar
     */
    public static <T> void mezclar(ListaEnlazada<T> lista) {
        if (lista == null || lista.tamaño() <= 1) {
            return;
        }

        // Convertir a arreglo
        T[] arreglo = lista.aArreglo();

        // Mezclar el arreglo
        mezclar(arreglo);

        // Reconstruir la lista
        lista.limpiar();
        for (T elemento : arreglo) {
            if (elemento != null) {
                lista.agregar(elemento);
            }
        }
    }

    /**
     * Intercambia dos elementos en un arreglo
     * 
     * @param arreglo el arreglo donde intercambiar
     * @param i       índice del primer elemento
     * @param j       índice del segundo elemento
     */
    private static <T> void intercambiar(T[] arreglo, int i, int j) {
        T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }

    /**
     * Genera un número aleatorio en un rango
     * 
     * @param min valor mínimo (inclusivo)
     * @param max valor máximo (exclusivo)
     * @return un número aleatorio en el rango [min, max)
     */
    public static int aleatorio(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min debe ser menor que max");
        }
        return min + generador.siguienteInt(max - min);
    }

    /**
     * Genera un número aleatorio entre 0 y max-1
     * 
     * @param max valor máximo (exclusivo)
     * @return un número aleatorio entre 0 y max-1
     */
    public static int aleatorio(int max) {
        return generador.siguienteInt(max);
    }

    /**
     * Establece una nueva semilla para el generador
     * Útil para testing y reproducibilidad
     * 
     * @param semilla la nueva semilla
     */
    public static void establecerSemilla(long semilla) {
        generador.semilla = semilla;
    }

    /**
     * Busca un elemento en un arreglo usando búsqueda lineal
     * 
     * @param arreglo  el arreglo donde buscar
     * @param elemento el elemento a buscar
     * @return el índice del elemento o -1 si no se encuentra
     */
    public static <T> int buscar(T[] arreglo, T elemento) {
        if (arreglo == null || elemento == null) {
            return -1;
        }

        for (int i = 0; i < arreglo.length; i++) {
            if (elemento.equals(arreglo[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Ordena un arreglo usando ordenamiento burbuja
     * 
     * @param arreglo el arreglo a ordenar
     */
    public static <T extends Comparable<T>> void ordenar(T[] arreglo) {
        if (arreglo == null || arreglo.length <= 1) {
            return;
        }

        boolean intercambio;
        do {
            intercambio = false;
            for (int i = 0; i < arreglo.length - 1; i++) {
                if (arreglo[i].compareTo(arreglo[i + 1]) > 0) {
                    intercambiar(arreglo, i, i + 1);
                    intercambio = true;
                }
            }
        } while (intercambio);
    }

    /**
     * Calcula el valor absoluto de un número
     * 
     * @param numero el número
     * @return el valor absoluto
     */
    public static int abs(int numero) {
        return numero < 0 ? -numero : numero;
    }

    /**
     * Calcula el mínimo entre dos números
     * 
     * @param a primer número
     * @param b segundo número
     * @return el menor de los dos números
     */
    public static int min(int a, int b) {
        return a < b ? a : b;
    }

    /**
     * Calcula el máximo entre dos números
     * 
     * @param a primer número
     * @param b segundo número
     * @return el mayor de los dos números
     */
    public static int max(int a, int b) {
        return a > b ? a : b;
    }
}

package src.utils;

/**
 * Lista enlazada genérica para reemplazar ArrayList de Java
 * Implementa una estructura dinámica de datos con nodos enlazados
 */
public class ListaEnlazada<T> {
    private Nodo<T> cabeza;
    private int tamaño;

    /**
     * Constructor por defecto
     */
    public ListaEnlazada() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    /**
     * Verifica si la lista está vacía
     * 
     * @return true si la lista está vacía, false en caso contrario
     */
    public boolean esVacia() {
        return cabeza == null;
    }

    /**
     * Obtiene el tamaño actual de la lista
     * 
     * @return el número de elementos en la lista
     */
    public int tamaño() {
        return tamaño;
    }

    /**
     * Agrega un elemento al final de la lista
     * 
     * @param elemento el elemento a agregar
     */
    public void agregar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);

        if (esVacia()) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamaño++;
    }

    /**
     * Obtiene el elemento en la posición especificada
     * 
     * @param indice la posición del elemento (base 0)
     * @return el elemento en la posición especificada
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    /**
     * Remueve todos los elementos de la lista
     */
    public void limpiar() {
        cabeza = null;
        tamaño = 0;
    }

    /**
     * Verifica si la lista contiene el elemento especificado
     * 
     * @param elemento el elemento a buscar
     * @return true si el elemento está en la lista, false en caso contrario
     */
    public boolean contiene(T elemento) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(elemento)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /**
     * Crea una copia de la lista actual
     * 
     * @return una nueva lista con los mismos elementos
     */
    public ListaEnlazada<T> copia() {
        ListaEnlazada<T> nuevaLista = new ListaEnlazada<>();
        Nodo<T> actual = cabeza;
        while (actual != null) {
            nuevaLista.agregar(actual.getDato());
            actual = actual.getSiguiente();
        }
        return nuevaLista;
    }

    /**
     * Convierte la lista en un arreglo de elementos
     * 
     * @return un arreglo con todos los elementos de la lista
     */
    @SuppressWarnings("unchecked")
    public T[] aArreglo() {
        Object[] arreglo = new Object[tamaño];
        Nodo<T> actual = cabeza;
        int indice = 0;
        while (actual != null) {
            arreglo[indice] = actual.getDato();
            actual = actual.getSiguiente();
            indice++;
        }
        return (T[]) arreglo;
    }

    /**
     * Obtiene una representación en cadena de la lista
     * 
     * @return una cadena representando los elementos de la lista
     */
    @Override
    public String toString() {
        if (esVacia()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo<T> actual = cabeza;
        while (actual != null) {
            sb.append(actual.getDato());
            if (actual.getSiguiente() != null) {
                sb.append(", ");
            }
            actual = actual.getSiguiente();
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Permite iterar sobre los elementos de la lista usando for-each
     * 
     * @return un iterador para recorrer la lista
     */
    public IteradorLista<T> iterador() {
        return new IteradorLista<>(cabeza);
    }

    /**
     * Clase interna para implementar iteración sobre la lista
     */
    public static class IteradorLista<T> {
        private Nodo<T> actual;

        public IteradorLista(Nodo<T> cabeza) {
            this.actual = cabeza;
        }

        public boolean tieneProximo() {
            return actual != null;
        }

        public T proximo() {
            if (!tieneProximo()) {
                throw new RuntimeException("No hay más elementos");
            }
            T dato = actual.getDato();
            actual = actual.getSiguiente();
            return dato;
        }
    }
}

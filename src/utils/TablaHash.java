package utils;

/**
 * Implementación propia de Tabla Hash para reemplazar HashMap de Java
 * Utiliza encadenamiento para resolver colisiones
 * Permite acceso O(1) promedio para operaciones básicas
 */
public class TablaHash<K, V> {

    /**
     * Nodo específico para la tabla hash que almacena pares clave-valor
     */
    private static class NodoHash<K, V> {
        private K clave;
        private V valor;
        private NodoHash<K, V> siguiente;

        public NodoHash(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = null;
        }

        public K getClave() {
            return clave;
        }

        public V getValor() {
            return valor;
        }

        public void setValor(V valor) {
            this.valor = valor;
        }

        public NodoHash<K, V> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(NodoHash<K, V> siguiente) {
            this.siguiente = siguiente;
        }
    }

    private static final int TAMAÑO_INICIAL = 16;
    private static final double FACTOR_CARGA = 0.75;

    private NodoHash<K, V>[] tabla;
    private int tamaño;
    private int capacidad;

    /**
     * Constructor que inicializa la tabla hash con tamaño por defecto
     */
    @SuppressWarnings("unchecked")
    public TablaHash() {
        this.capacidad = TAMAÑO_INICIAL;
        this.tabla = new NodoHash[capacidad];
        this.tamaño = 0;
    }

    /**
     * Constructor que permite especificar la capacidad inicial
     * 
     * @param capacidadInicial la capacidad inicial de la tabla
     */
    @SuppressWarnings("unchecked")
    public TablaHash(int capacidadInicial) {
        this.capacidad = capacidadInicial;
        this.tabla = new NodoHash[capacidad];
        this.tamaño = 0;
    }

    /**
     * Función hash simple para obtener el índice de una clave
     * 
     * @param clave la clave a hashear
     * @return el índice en la tabla donde debe ir la clave
     */
    private int hash(K clave) {
        if (clave == null) {
            return 0;
        }
        return Math.abs(clave.hashCode()) % capacidad;
    }

    /**
     * Almacena un par clave-valor en la tabla hash
     * 
     * @param clave la clave del elemento
     * @param valor el valor asociado a la clave
     */
    public void poner(K clave, V valor) {
        int indice = hash(clave);
        NodoHash<K, V> nodo = tabla[indice];

        // Buscar si la clave ya existe
        while (nodo != null) {
            if (nodo.getClave().equals(clave)) {
                nodo.setValor(valor); // Actualizar valor existente
                return;
            }
            nodo = nodo.getSiguiente();
        }

        // Crear nuevo nodo y agregarlo al inicio de la cadena
        NodoHash<K, V> nuevoNodo = new NodoHash<>(clave, valor);
        nuevoNodo.setSiguiente(tabla[indice]);
        tabla[indice] = nuevoNodo;
        tamaño++;

        // Verificar si necesitamos redimensionar
        if ((double) tamaño / capacidad > FACTOR_CARGA) {
            redimensionar();
        }
    }

    /**
     * Obtiene el valor asociado a una clave
     * 
     * @param clave la clave a buscar
     * @return el valor asociado a la clave, o null si no existe
     */
    public V obtener(K clave) {
        int indice = hash(clave);
        NodoHash<K, V> nodo = tabla[indice];

        while (nodo != null) {
            if (nodo.getClave().equals(clave)) {
                return nodo.getValor();
            }
            nodo = nodo.getSiguiente();
        }
        return null;
    }

    /**
     * Verifica si la tabla contiene una clave específica
     * 
     * @param clave la clave a verificar
     * @return true si la clave existe, false en caso contrario
     */
    public boolean contieneClave(K clave) {
        return obtener(clave) != null;
    }

    /**
     * Remueve un elemento de la tabla hash
     * 
     * @param clave la clave del elemento a remover
     * @return el valor que fue removido, o null si no existía
     */
    public V remover(K clave) {
        int indice = hash(clave);
        NodoHash<K, V> nodo = tabla[indice];
        NodoHash<K, V> anterior = null;

        while (nodo != null) {
            if (nodo.getClave().equals(clave)) {
                if (anterior == null) {
                    tabla[indice] = nodo.getSiguiente();
                } else {
                    anterior.setSiguiente(nodo.getSiguiente());
                }
                tamaño--;
                return nodo.getValor();
            }
            anterior = nodo;
            nodo = nodo.getSiguiente();
        }
        return null;
    }

    /**
     * Obtiene todas las claves de la tabla hash
     * 
     * @return una lista enlazada con todas las claves
     */
    public ListaEnlazada<K> obtenerClaves() {
        ListaEnlazada<K> claves = new ListaEnlazada<>();

        for (int i = 0; i < capacidad; i++) {
            NodoHash<K, V> nodo = tabla[i];
            while (nodo != null) {
                claves.agregar(nodo.getClave());
                nodo = nodo.getSiguiente();
            }
        }
        return claves;
    }

    /**
     * Obtiene todos los valores de la tabla hash
     * 
     * @return una lista enlazada con todos los valores
     */
    public ListaEnlazada<V> obtenerValores() {
        ListaEnlazada<V> valores = new ListaEnlazada<>();

        for (int i = 0; i < capacidad; i++) {
            NodoHash<K, V> nodo = tabla[i];
            while (nodo != null) {
                valores.agregar(nodo.getValor());
                nodo = nodo.getSiguiente();
            }
        }
        return valores;
    }

    /**
     * Obtiene el número de elementos en la tabla
     * 
     * @return el tamaño actual de la tabla
     */
    public int tamaño() {
        return tamaño;
    }

    /**
     * Verifica si la tabla está vacía
     * 
     * @return true si la tabla está vacía, false en caso contrario
     */
    public boolean esVacia() {
        return tamaño == 0;
    }

    /**
     * Redimensiona la tabla hash cuando el factor de carga es muy alto
     */
    @SuppressWarnings("unchecked")
    private void redimensionar() {
        NodoHash<K, V>[] tablaVieja = tabla;
        int capacidadVieja = capacidad;

        capacidad *= 2;
        tabla = new NodoHash[capacidad];
        tamaño = 0;

        // Rehashear todos los elementos
        for (int i = 0; i < capacidadVieja; i++) {
            NodoHash<K, V> nodo = tablaVieja[i];
            while (nodo != null) {
                poner(nodo.getClave(), nodo.getValor());
                nodo = nodo.getSiguiente();
            }
        }
    }

    /**
     * Limpia toda la tabla hash
     */
    @SuppressWarnings("unchecked")
    public void limpiar() {
        tabla = new NodoHash[TAMAÑO_INICIAL];
        capacidad = TAMAÑO_INICIAL;
        tamaño = 0;
    }

    /**
     * Representación en cadena de la tabla hash para debugging
     * 
     * @return una cadena con la representación de la tabla
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean primero = true;
        for (int i = 0; i < capacidad; i++) {
            NodoHash<K, V> nodo = tabla[i];
            while (nodo != null) {
                if (!primero) {
                    sb.append(", ");
                }
                sb.append(nodo.getClave()).append("=").append(nodo.getValor());
                primero = false;
                nodo = nodo.getSiguiente();
            }
        }

        sb.append("}");
        return sb.toString();
    }
}

package dealer;

/**
 * Árbol binario de decisión para la lógica del dealer
 * Determina si el dealer debe pedir carta o plantarse basándose en su puntaje
 */
public class ArbolDecision {
    private NodoDecision raiz;

    public ArbolDecision() {
        construirArbol();
    }

    /**
     * Construye el árbol de decisión del dealer
     * Lógica estándar del Blackjack:
     * - Si puntaje < 17: pedir carta
     * - Si puntaje >= 17: plantarse
     * - Casos especiales para As suave
     */
    private void construirArbol() {
        // Nodos hoja (acciones finales)
        NodoDecision pedirCarta = new NodoDecision("PEDIR");
        NodoDecision plantarse = new NodoDecision("PLANTAR");

        // Nodos internos para diferentes rangos de puntaje
        NodoDecision decision17 = new NodoDecision("¿Puntaje >= 17?", pedirCarta, plantarse);

        // Raíz del árbol
        raiz = decision17;
    }

    /**
     * Toma una decisión basada en el puntaje del dealer
     * 
     * @param puntaje      Puntaje actual del dealer
     * @param tieneAsSuave Si el dealer tiene un As contado como 11
     * @return La acción a tomar ("PEDIR" o "PLANTAR")
     */
    public String tomarDecision(int puntaje, boolean tieneAsSuave) {
        return evaluarNodo(raiz, puntaje, tieneAsSuave);
    }

    /**
     * Evalúa recursivamente el árbol de decisión
     */
    private String evaluarNodo(NodoDecision nodo, int puntaje, boolean tieneAsSuave) {
        if (nodo.esHoja()) {
            return nodo.getAccion();
        }

        // Lógica de decisión basada en el puntaje
        if (puntaje < 17) {
            return evaluarNodo(nodo.getIzquierda(), puntaje, tieneAsSuave);
        } else if (puntaje == 17 && tieneAsSuave) {
            // As suave con 17 - dealer debe pedir (regla estándar)
            return evaluarNodo(nodo.getIzquierda(), puntaje, tieneAsSuave);
        } else {
            // Puntaje >= 17 (o 17 duro) - dealer se planta
            return evaluarNodo(nodo.getDerecha(), puntaje, tieneAsSuave);
        }
    }

    /**
     * Muestra la estructura del árbol (para debugging)
     */
    public void mostrarArbol() {
        System.out.println("Árbol de Decisión del Dealer:");
        mostrarNodo(raiz, "", true);
    }

    private void mostrarNodo(NodoDecision nodo, String prefijo, boolean esUltimo) {
        if (nodo != null) {
            System.out.println(prefijo + (esUltimo ? "└── " : "├── ") +
                    (nodo.esHoja() ? "ACCIÓN: " + nodo.getAccion() : nodo.getPregunta()));

            if (!nodo.esHoja()) {
                mostrarNodo(nodo.getIzquierda(), prefijo + (esUltimo ? "    " : "│   "), false);
                mostrarNodo(nodo.getDerecha(), prefijo + (esUltimo ? "    " : "│   "), true);
            }
        }
    }
}

package src.dealer;

/**
 * Nodo del árbol binario de decisión
 */
public class NodoDecision {
    private String pregunta;
    private String accion;
    private NodoDecision izquierda;
    private NodoDecision derecha;

    // Constructor para nodo hoja (con acción)
    public NodoDecision(String accion) {
        this.accion = accion;
        this.pregunta = null;
        this.izquierda = null;
        this.derecha = null;
    }

    // Constructor para nodo interno (con pregunta)
    public NodoDecision(String pregunta, NodoDecision izquierda, NodoDecision derecha) {
        this.pregunta = pregunta;
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.accion = null;
    }

    // Getters
    public String getPregunta() {
        return pregunta;
    }

    public String getAccion() {
        return accion;
    }

    public NodoDecision getIzquierda() {
        return izquierda;
    }

    public NodoDecision getDerecha() {
        return derecha;
    }

    // Verificar si es hoja
    public boolean esHoja() {
        return accion != null;
    }
}

package src.utils;

import src.card.Carta;

/**
 * Nodo genérico que puede ser usado por todas las estructuras de datos
 * del proyecto (lista enlazada, pila, cola)
 */
public class Nodo<T> {
    private T dato;
    private Nodo<T> siguiente;

    // Constructor por defecto
    public Nodo() {
        this.dato = null;
        this.siguiente = null;
    }

    // Constructor con dato
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    // Constructor completo
    public Nodo(T dato, Nodo<T> siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    // Getters y Setters
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    } // Métodos específicos para cartas (para retrocompatibilidad)

    public Carta getCarta() {
        if (dato instanceof Carta) {
            return (Carta) dato;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public void setCarta(Carta carta) {
        if (dato == null || dato instanceof Carta) {
            this.dato = (T) carta;
        }
    }
}

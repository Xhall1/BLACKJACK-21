package turnos;

import utils.Nodo;

/**
 * Cola (Queue) para gestionar los turnos en el juego de Blackjack
 * Implementa FIFO (First In, First Out)
 */
public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamaño;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    // Verifica si la cola está vacía
    public boolean esVacia() {
        return frente == null;
    }

    // Añadir elemento al final de la cola
    public void encolar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);

        if (esVacia()) {
            frente = fin = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo);
            fin = nuevoNodo;
        }
        tamaño++;
    }

    // Remover y devolver el elemento del frente de la cola
    public T desencolar() {
        if (esVacia()) {
            return null;
        }

        T elemento = frente.getDato();
        frente = frente.getSiguiente();

        if (frente == null) {
            fin = null;
        }

        tamaño--;
        return elemento;
    }

    // Ver el elemento del frente sin removerlo
    public T verFrente() {
        if (esVacia()) {
            return null;
        }
        return frente.getDato();
    }

    // Obtener el tamaño de la cola
    public int getTamaño() {
        return tamaño;
    }

    // Mostrar todos los elementos de la cola
    public void mostrarCola() {
        if (esVacia()) {
            System.out.println("La cola está vacía");
            return;
        }

        System.out.println("Elementos en la cola:");
        Nodo<T> actual = frente;
        int posicion = 1;

        while (actual != null) {
            System.out.println(posicion + ". " + actual.getDato().toString());
            actual = actual.getSiguiente();
            posicion++;
        }
    }
}

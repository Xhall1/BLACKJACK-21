package src.historialJugadas;

import src.card.Carta;
import src.game.Main;
import src.utils.Nodo;

public class Pila {
    private Nodo<Carta> inicio;

    public Pila() {
        this.inicio = null;
    }

    public boolean esVacia() {
        return inicio == null;
    }

    public void apilar(Carta carta) {
        Nodo<Carta> nuevo = new Nodo<>(carta);
        if (esVacia()) {
            inicio = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
    }

    public Carta desapilar() {
        if (!esVacia()) {
            Carta carta = inicio.getDato();
            inicio = inicio.getSiguiente();
            return carta;
        } else {
            return null;
        }
    }

    public void mostrarPila() {
        Nodo<Carta> aux = inicio;
        System.out.println("Estas son las cartas que se jugaron:");
        while (aux != null) {
            Main.imprimirCarta(aux.getDato());
            Main.separarSeccion();
            aux = aux.getSiguiente();
        }
    }
}

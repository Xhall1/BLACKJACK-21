package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Clase de la baraja como lista enlazada simple
public class Baraja {
    private Nodo cabeza; // Primer nodo en la lista la cuál dará el inicio de la baraja
    private int tamaño; // Cantidad total de cartas en la baraja

    public Baraja() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    // Inicializa la baraja estándar con 52 cartas
    public void inicializarBaraja() {
        // Palos disponibles
        String[] palos = {"♠", "♥", "♦", "♣"};

        // Valores de las cartas
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // Puntuación asociada a cada valor
        int[] puntos = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

        for (String palo : palos) {
            for (int i = 0; i < valores.length; i++) {
                agregarCarta(new Carta(palo, valores[i], puntos[i])); // Crear y agregar carta
            }
        }
    }

    // Agregar una carta al final de la lista enlazada
    public void agregarCarta(Carta carta) {
        Nodo nuevoNodo = new Nodo(carta);
        if (cabeza == null) {
            cabeza = nuevoNodo; // Si no hay cartas, entonces  el nuevo nodo es la cabeza
        } else {
            Nodo actual = cabeza; // Recorre hasta el último nodo
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo); // Agrega el nodo al final
        }
        tamaño++; // Incrementar el tamaño de la baraja
    }

    // Robar la primera carta de la baraja
    public Carta robarCarta() {
        if (cabeza == null) return null; // Si la baraja está vacía, este va a devolver null
        // Obtener la carta del primer nodo
        Carta cartaRobada = cabeza.getCarta();

        // Mueve la cabeza al siguiente nodo
        cabeza = cabeza.getSiguiente();

        // Reduce el tamaño de la baraja
        tamaño--;
        return cartaRobada; // Devuelve la carta robada
    }

    // Mezclar el mazo
    public void mezclarBaraja() {
        List<Carta> cartas = new ArrayList<>();
        while (tamaño > 0) { // Vaciar la baraja en una lista temporal
            cartas.add(robarCarta());
        }
        Collections.shuffle(cartas); // Mezclar las cartas
        for (Carta carta : cartas) { // Volver a añadir las cartas al mazo
            agregarCarta(carta);
        }
    }

    // Mostrar todas las cartas restantes en la baraja
    public void mostrarBaraja() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getCarta()); // Imprimir la carta actual
            actual = actual.getSiguiente(); // Avanzar al siguiente nodo
        }
    }

    // Aquí se obtendrá el tamaño actual de la baraja
    public int getTamaño() {
        return tamaño;
    }
}
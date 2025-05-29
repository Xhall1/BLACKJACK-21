package src.card;

import src.utils.Nodo;
import src.utils.ListaEnlazada;
import src.utils.UtilMatematicas;

/**
 * Clase de la baraja implementada como lista enlazada simple
 * Utiliza nuestras propias estructuras de datos sin dependencias de Java
 * Collections
 */
public class Baraja {
    private Nodo<Carta> cabeza; // Primer nodo en la lista la cuál dará el inicio de la baraja
    private int tamaño; // Cantidad total de cartas en la baraja

    /**
     * Constructor que inicializa una baraja vacía
     */
    public Baraja() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    /**
     * Inicializa la baraja estándar con 52 cartas
     * Crea una baraja inglesa completa con 4 palos y 13 valores por palo
     */
    public void inicializarBaraja() {
        // Palos disponibles
        String[] palos = { "♠", "♥", "♦", "♣" };

        // Valores de las cartas
        String[] valores = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

        // Puntuación asociada a cada valor
        int[] puntos = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

        for (String palo : palos) {
            for (int i = 0; i < valores.length; i++) {
                agregarCarta(new Carta(palo, valores[i], puntos[i])); // Crear y agregar carta
            }
        }
    }

    /**
     * Agregar una carta al final de la lista enlazada
     * 
     * @param carta la carta a agregar a la baraja
     */
    public void agregarCarta(Carta carta) {
        Nodo<Carta> nuevoNodo = new Nodo<>(carta);
        if (cabeza == null) {
            cabeza = nuevoNodo; // Si no hay cartas, entonces el nuevo nodo es la cabeza
        } else {
            Nodo<Carta> actual = cabeza; // Recorre hasta el último nodo
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo); // Agrega el nodo al final
        }
        tamaño++; // Incrementar el tamaño de la baraja
    }

    /**
     * Robar la primera carta de la baraja
     * 
     * @return la carta robada o null si la baraja está vacía
     */
    public Carta robarCarta() {
        if (cabeza == null)
            return null; // Si la baraja está vacía, este va a devolver null
        // Obtener la carta del primer nodo
        Carta cartaRobada = cabeza.getDato();

        // Mueve la cabeza al siguiente nodo
        cabeza = cabeza.getSiguiente();

        // Reduce el tamaño de la baraja
        tamaño--;
        return cartaRobada; // Devuelve la carta robada
    }

    /**
     * Mezcla el mazo usando algoritmo propio sin Collections.shuffle()
     * Utiliza nuestra implementación de algoritmos matemáticos
     */
    public void mezclarBaraja() {
        ListaEnlazada<Carta> cartas = new ListaEnlazada<>();
        while (tamaño > 0) { // Vaciar la baraja en una lista temporal
            cartas.agregar(robarCarta());
        }
        UtilMatematicas.mezclar(cartas); // Mezclar las cartas usando nuestro algoritmo

        // Volver a añadir las cartas al mazo usando iterador
        ListaEnlazada.IteradorLista<Carta> iterador = cartas.iterador();
        while (iterador.tieneProximo()) {
            agregarCarta(iterador.proximo());
        }
    }

    /**
     * Mostrar todas las cartas restantes en la baraja
     */
    public void mostrarBaraja() {
        Nodo<Carta> actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getDato()); // Imprimir la carta actual
            actual = actual.getSiguiente(); // Avanzar al siguiente nodo
        }
    }

    /**
     * Obtener el tamaño actual de la baraja
     * 
     * @return el número de cartas restantes en la baraja
     */
    public int getTamaño() {
        return tamaño;
    }

}
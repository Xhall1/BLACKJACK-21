package game;

import src.card.Carta;

/**
 * Clase principal para ejecutar el juego de Blackjack
 * Incluye métodos utilitarios para la presentación del juego
 */

public class Main {

    // Metodo para mostrar el título del juego de manera visual
    public void mostrarTitulo() {
        System.out.println("=======================================");
        System.out.println("          🎴 BLACKJACK 21 🎴         ");
        System.out.println("=======================================\n");
    }

    // Metodo para separar secciones en la consola
    public void separarSeccion() {
        System.out.println("\n---------------------------------------\n");
    }

    // Metodo para agregar un retardo (simula animación)
    public void retraso(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Metodo para imprimir una carta con ASCII Art
    public void imprimirCarta(Carta carta) {
        String valor = carta.getValor(); // Valor de la carta (A, 2, 3, K, etc.)
        String palo = carta.getPalo(); // Palo de la carta (♥, ♦, ♣, ♠)

        // Representación en ASCII Art
        System.out.println("+-----------+");
        System.out.printf("| %-2s        |\n", valor); // Valor en la esquina superior izquierda
        System.out.println("|           |");
        System.out.printf("|     %s     |\n", palo); // Palo en el centro
        System.out.println("|           |");
        System.out.printf("|        %-2s |\n", valor); // Valor en la esquina inferior derecha
        System.out.println("+-----------+");
    }

    // Metodo principal del juego
    public static void main(String[] args) {
        // Crear y ejecutar el motor del juego
        BlackjackEngine engine = new BlackjackEngine();
        engine.iniciarJuego();
    }
}

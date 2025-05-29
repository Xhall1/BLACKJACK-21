package game;

import java.util.Scanner;

import card.Baraja;
import card.Carta;
import historialJugadas.Pila;
import jugadores.Jugador;

/**
 * Este main es solo para las prueba de la lista enlazada, la idea es que se
 * haga toda la lógica en este archivo
 * y tengamos todo aquí.
 */

public class Main {

    // Metodo para mostrar el título del juego de manera visual
    public void mostrarTitulo() {
        System.out.println("=======================================");
        System.out.println("          🎴 ¡ BlackJack 21 ! 🎴         ");
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

        Main app = new Main();
        Scanner s = new Scanner(System.in);

        // Mostrar el título del juego
        app.mostrarTitulo();

        // Crea el usuario
        System.out.println("Ingrese su nombre: ");
        Jugador jugador = new Jugador(s.next());
        Jugador dealer = new Jugador("Dealer");

        int puntajeJugador = 0; // Guarda temporalmente el puntaje del jugador
        int puntajeDealer = 0; // Guarda temporalmente el puntaje del dealer
        Pila historialJugador = new Pila();
        Pila historialDealer = new Pila();
        Baraja baraja = new Baraja();

        baraja.inicializarBaraja();
        baraja.mezclarBaraja(); // Mezclar la baraja

        // Mensaje inicial
        System.out.println("🃏 ¡Bienvenido al juego! Aquí está la baraja inicial mezclada:");

        app.separarSeccion();

        // Reparte las cartas
        System.out.println("Turno de " + jugador.getNombre());
        System.out.println("🤞 Repartiendo cartas...");
        app.retraso(1500); // Añadir un retraso para simular suspenso
        // Primera carta robada
        Carta cartaRobada1 = baraja.robarCarta();
        historialJugador.apilar(cartaRobada1);

        // Segunda carta robada
        Carta cartaRobada2 = baraja.robarCarta();
        historialJugador.apilar(cartaRobada2);

        // Puntos y cartas en mano
        jugador.setPuntaje(cartaRobada1.getPuntos() + cartaRobada2.getPuntos()); // Guarda el valor del puntaje
        System.out.println("✨ Juan tiene:");
        historialJugador.mostrarPila();
        System.out.println("Puntaje actual: " + jugador.getPuntaje());

        app.separarSeccion();

        // Ciclo para robar o no mas cartas
        System.out.println("Desea robar otra carta? (s/n):");
        String desicion = s.next();
        while (desicion.equals("s")) {
            System.out.println("🤞 Robando otra carta...");
            app.retraso(1500);
            Carta cartaRobada = baraja.robarCarta();
            historialJugador.apilar(cartaRobada);
            jugador.setPuntaje(jugador.getPuntaje() + cartaRobada.getPuntos());
            System.out.println("✨ Juan tiene:");
            historialJugador.mostrarPila();
            System.out.println("Puntaje actual: " + jugador.getPuntaje());

            if (jugador.getPuntaje() >= 21) {
                break;
            } else {
                app.separarSeccion();
                System.out.println("Desea robar otra carta? (s/n):");
                desicion = s.next();
            }

        }

        jugador.setCartas(historialJugador);

        // Mensaje final
        System.out.println("🎉 ¡Gracias por jugar! Regresa pronto. 🎉");

    }
}

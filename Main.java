
import card.Baraja;
import card.Carta;

/** Este main es solo para las prueba de la lista enlazada, la idea es que se haga toda la lógica en este archivo
 * y tengamos todo aquí.
 */

public class Main {

    // Metodo para mostrar el título del juego de manera visual
    public static void mostrarTitulo() {
        System.out.println("=======================================");
        System.out.println("          🎴 ¡Juego de Cartas! 🎴         ");
        System.out.println("=======================================\n");
    }

    // Metodo para separar secciones en la consola
    public static void separarSeccion() {
        System.out.println("\n---------------------------------------\n");
    }

    // Metodo para agregar un retardo (simula animación)
    public static void retraso(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Metodo para imprimir una carta con ASCII Art
    public static void imprimirCarta(Carta carta) {
        String valor = carta.getValor(); // Valor de la carta (A, 2, 3, K, etc.)
        String palo = carta.getPalo();   // Palo de la carta (♥, ♦, ♣, ♠)

        // Representación en ASCII Art
        System.out.println("+-----------+");
        System.out.printf("| %-2s        |\n", valor); // Valor en la esquina superior izquierda
        System.out.println("|           |");
        System.out.printf("|     %s     |\n", palo);   // Palo en el centro
        System.out.println("|           |");
        System.out.printf("|        %-2s |\n", valor); // Valor en la esquina inferior derecha
        System.out.println("+-----------+");
    }

    // Metodo principal del juego
    public static void main(String[] args) {
        // Mostrar el título del juego
        mostrarTitulo();

        Baraja baraja = new Baraja();
        baraja.inicializarBaraja();
        baraja.mezclarBaraja(); // Mezclar la baraja

        // Mensaje inicial
        System.out.println("🃏 ¡Bienvenido al juego! Aquí está la baraja inicial mezclada:");

        separarSeccion();

        // Primera carta robada
        System.out.println("🤞 Robando una carta...");
        retraso(1500); // Añadir un retraso para simular suspenso
        Carta cartaRobada1 = baraja.robarCarta();
        System.out.println("✨ Has robado la siguiente carta:");
        imprimirCarta(cartaRobada1); // Imprime la carta en formato ASCII
        System.out.println("Cartas restantes en la baraja: " + baraja.getTamaño());

        separarSeccion();

        // Segunda carta robada
        System.out.println("🤞 Robando otra carta...");
        retraso(1500);
        Carta cartaRobada2 = baraja.robarCarta();
        System.out.println("✨ Has robado la siguiente carta:");
        imprimirCarta(cartaRobada2); // Imprime la carta en formato ASCII
        System.out.println("Cartas restantes en la baraja: " + baraja.getTamaño());

        separarSeccion();

        // Mensaje final
        System.out.println("🎉 ¡Gracias por jugar! Regresa pronto. 🎉");
    }
}

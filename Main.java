
import card.Baraja;
import card.Carta;

/** Este main es solo para las prueba de la lista enlazada, la idea es que se haga toda la lógica en este archivo
 * y tengamos todo aquí.
 */

public class Main {
    public static void main(String[] args) {
        Baraja baraja = new Baraja();
        baraja.inicializarBaraja(); // Inicializar la baraja
        baraja.mezclarBaraja(); // Mezclar la baraja

        System.out.println("La baraja inicial contiene " + baraja.getTamaño() + " cartas:");
        baraja.mostrarBaraja();

        System.out.println("\nRobar una carta: " + baraja.robarCarta());
        System.out.println("Ahora la baraja tiene " + baraja.getTamaño() + " cartas.");
    }
}
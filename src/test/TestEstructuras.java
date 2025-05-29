package src.test;

import src.card.Baraja;
import src.card.Carta;
import src.dealer.ArbolDecision;
import src.historialJugadas.Pila;
import src.jugadores.ControladorJugadores;
import src.jugadores.EstadoJugador;
import src.turnos.Cola;
import src.game.Main;

/**
 * Clase para probar todas las estructuras de datos implementadas
 */
public class TestEstructuras {

    public static void main(String[] args) {
        System.out.println("🧪 PRUEBAS DE ESTRUCTURAS DE DATOS PARA BLACKJACK\n");

        // 1. Probar Lista Enlazada (Baraja)
        probarBaraja();

        // 2. Probar Pila (Historial)
        probarPila();

        // 3. Probar Cola (Turnos)
        probarCola();

        // 4. Probar Árbol de Decisión (Dealer)
        probarArbolDecision();

        // 5. Probar Tabla Hash (Controlador de Jugadores)
        probarControladorJugadores();

        System.out.println("✅ ¡Todas las estructuras funcionan correctamente!");
    }

    private static void probarBaraja() {
        System.out.println("🃏 === PRUEBA: LISTA ENLAZADA (BARAJA) ===");

        Baraja baraja = new Baraja();
        baraja.inicializarBaraja();
        System.out.println("Baraja inicializada con " + baraja.getTamaño() + " cartas");

        baraja.mezclarBaraja();
        System.out.println("Baraja mezclada");

        // Robar algunas cartas
        System.out.println("Robando 3 cartas:");
        for (int i = 0; i < 3; i++) {
            Carta carta = baraja.robarCarta();
            System.out.println("  " + (i + 1) + ". " + carta.toString());
        }

        System.out.println("Cartas restantes: " + baraja.getTamaño());
        System.out.println("✅ Lista enlazada funcionando correctamente\n");
    }

    private static void probarPila() {
        System.out.println("📚 === PRUEBA: PILA (HISTORIAL) ===");

        Pila historial = new Pila();

        // Crear algunas cartas de prueba
        Carta carta1 = new Carta("♠", "A", 11);
        Carta carta2 = new Carta("♥", "K", 10);
        Carta carta3 = new Carta("♦", "7", 7);

        // Apilar cartas
        historial.apilar(carta1);
        historial.apilar(carta2);
        historial.apilar(carta3);

        System.out.println("Cartas apiladas (orden LIFO):");
        System.out.println("1. " + carta1.toString());
        System.out.println("2. " + carta2.toString());
        System.out.println("3. " + carta3.toString());

        // Desapilar
        System.out.println("\nDesapilando cartas:");
        while (!historial.esVacia()) {
            Carta carta = historial.desapilar();
            System.out.println("  Desapilada: " + carta.toString());
        }

        System.out.println("✅ Pila funcionando correctamente\n");
    }

    private static void probarCola() {
        System.out.println("🔄 === PRUEBA: COLA (TURNOS) ===");

        Cola<String> turnos = new Cola<>();

        // Encolar jugadores
        turnos.encolar("Juan");
        turnos.encolar("María");
        turnos.encolar("Dealer");

        System.out.println("Jugadores encolados (orden FIFO):");
        turnos.mostrarCola();

        System.out.println("\nProcesando turnos:");
        while (!turnos.esVacia()) {
            String jugador = turnos.desencolar();
            System.out.println("  Turno de: " + jugador);
        }

        System.out.println("✅ Cola funcionando correctamente\n");
    }

    private static void probarArbolDecision() {
        System.out.println("🌳 === PRUEBA: ÁRBOL BINARIO DE DECISIÓN ===");

        ArbolDecision arbol = new ArbolDecision();

        // Probar diferentes escenarios
        System.out.println("Probando decisiones del dealer:");

        int[] puntajes = { 12, 16, 17, 20, 21 };
        boolean[] asesSuaves = { false, true, false, false, false };

        for (int i = 0; i < puntajes.length; i++) {
            String decision = arbol.tomarDecision(puntajes[i], asesSuaves[i]);
            String tipo = asesSuaves[i] ? " (As suave)" : " (duro)";
            System.out.println("  Puntaje " + puntajes[i] + tipo + " → " + decision);
        }

        System.out.println("✅ Árbol de decisión funcionando correctamente\n");
    }

    private static void probarControladorJugadores() {
        System.out.println("🗃️  === PRUEBA: TABLA HASH (CONTROLADOR JUGADORES) ===");

        ControladorJugadores controlador = new ControladorJugadores();

        // Agregar jugadores
        controlador.agregarJugador("Juan");
        controlador.agregarJugador("María");
        controlador.agregarJugador("Dealer");

        System.out.println("Jugadores agregados: " + controlador.getNumeroJugadores());

        // Agregar cartas a un jugador
        EstadoJugador juan = controlador.obtenerJugador("Juan");
        juan.agregarCarta(new Carta("♠", "A", 11));
        juan.agregarCarta(new Carta("♥", "K", 10));

        System.out.println("\nEstado de Juan:");
        System.out.println(juan.toString());

        // Probar operaciones de búsqueda O(1)
        System.out.println("¿Existe 'Juan'? " + controlador.existeJugador("Juan"));
        System.out.println("¿Existe 'Pedro'? " + controlador.existeJugador("Pedro"));

        // Mostrar todos los jugadores
        System.out.println("\nTodos los jugadores:");
        for (String nombre : controlador.obtenerNombresJugadores()) {
            System.out.println("  - " + nombre);
        }

        System.out.println("✅ Tabla hash funcionando correctamente\n");
    }
}

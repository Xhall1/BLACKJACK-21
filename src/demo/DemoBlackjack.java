package src.demo;

import src.card.Baraja;
import src.card.Carta;
import src.dealer.ArbolDecision;
import src.historialJugadas.Pila;
import src.jugadores.ControladorJugadores;
import src.jugadores.EstadoJugador;
import src.game.Main;

/**
 * Demostración automática del juego de Blackjack
 * Muestra todas las estructuras de datos en acción sin intervención del usuario
 */
public class DemoBlackjack {

    public static void main(String[] args) {
        Main.mostrarTitulo();
        System.out.println("🎭 DEMOSTRACIÓN AUTOMÁTICA DEL BLACKJACK");
        System.out.println("Mostrando todas las estructuras de datos en acción\n");

        // Inicializar estructuras
        Baraja baraja = new Baraja();
        Pila historial = new Pila();
        ControladorJugadores controlador = new ControladorJugadores();
        ArbolDecision arbolDealer = new ArbolDecision();

        // Configurar jugadores
        controlador.agregarJugador("Ana");
        controlador.agregarJugador("Dealer");

        // Inicializar baraja
        baraja.inicializarBaraja();
        baraja.mezclarBaraja();
        System.out.println("🃏 Baraja inicializada y mezclada (" + baraja.getTamaño() + " cartas)");

        Main.separarSeccion();

        // === REPARTIR CARTAS INICIALES ===
        System.out.println("📋 REPARTIENDO CARTAS INICIALES");

        EstadoJugador ana = controlador.obtenerJugador("Ana");
        EstadoJugador dealer = controlador.obtenerJugador("Dealer");

        // Repartir 2 cartas a cada uno
        for (int i = 0; i < 2; i++) {
            Carta cartaAna = baraja.robarCarta();
            Carta cartaDealer = baraja.robarCarta();

            ana.agregarCarta(cartaAna);
            dealer.agregarCarta(cartaDealer);

            historial.apilar(cartaAna);
            historial.apilar(cartaDealer);
        }

        // Mostrar cartas iniciales
        System.out.println(ana.toString());
        System.out.println("Dealer tiene:");
        System.out.println("  " + dealer.getMano().obtener(0).toString());
        System.out.println("  [Carta oculta]");

        Main.separarSeccion();

        // === TURNO DE ANA (SIMULADO) ===
        System.out.println("🎯 TURNO DE ANA");

        // Simular decisiones de Ana basadas en estrategia básica
        while (!ana.seHaPlantado() && !ana.seHaPasado()) {
            System.out.println("Ana considera sus opciones...");
            Main.retraso(1000);

            if (ana.getPuntaje() < 17) {
                System.out.println("Ana decide pedir carta (puntaje bajo: " + ana.getPuntaje() + ")");
                Carta nuevaCarta = baraja.robarCarta();
                ana.agregarCarta(nuevaCarta);
                historial.apilar(nuevaCarta);

                System.out.println("🃏 Nueva carta: " + nuevaCarta.toString());
                System.out.println("Nuevo puntaje de Ana: " + ana.getPuntaje());

                if (ana.seHaPasado()) {
                    System.out.println("💥 ¡Ana se pasó de 21!");
                }
            } else {
                ana.plantarse();
                System.out.println("✋ Ana se planta con " + ana.getPuntaje() + " puntos");
            }

            if (!ana.seHaPlantado() && !ana.seHaPasado()) {
                Main.retraso(1500);
            }
        }

        Main.separarSeccion();

        // === TURNO DEL DEALER ===
        System.out.println("🤖 TURNO DEL DEALER");

        // Revelar carta oculta
        System.out.println("El dealer revela su carta oculta...");
        Main.retraso(1000);
        System.out.println(dealer.toString());

        // Usar árbol de decisión
        while (!dealer.seHaPlantado() && !dealer.seHaPasado()) {
            Main.retraso(1500);

            String decision = arbolDealer.tomarDecision(dealer.getPuntaje(), dealer.tieneAsSuave());

            if (decision.equals("PEDIR")) {
                System.out.println("🌳 Árbol de decisión: PEDIR CARTA (puntaje: " + dealer.getPuntaje() + ")");

                Carta nuevaCarta = baraja.robarCarta();
                dealer.agregarCarta(nuevaCarta);
                historial.apilar(nuevaCarta);

                System.out.println("🃏 Dealer toma: " + nuevaCarta.toString());
                System.out.println("Nuevo puntaje del dealer: " + dealer.getPuntaje());

                if (dealer.seHaPasado()) {
                    System.out.println("💥 ¡El dealer se pasó de 21!");
                }
            } else {
                dealer.plantarse();
                System.out.println("🌳 Árbol de decisión: PLANTARSE (puntaje: " + dealer.getPuntaje() + ")");
                System.out.println("✋ El dealer se planta");
            }
        }

        Main.separarSeccion();

        // === DETERMINACIÓN DEL GANADOR ===
        System.out.println("🏆 RESULTADOS FINALES");

        controlador.mostrarEstadoActual();

        // Lógica de ganador
        if (ana.seHaPasado()) {
            System.out.println("🤖 El dealer gana - Ana se pasó de 21");
        } else if (dealer.seHaPasado()) {
            System.out.println("🎉 Ana gana - El dealer se pasó de 21");
            ana.ganarPartida();
        } else if (ana.tieneBlackjack() && !dealer.tieneBlackjack()) {
            System.out.println("🎊 Ana gana con BLACKJACK!");
            ana.ganarPartida();
        } else if (dealer.tieneBlackjack() && !ana.tieneBlackjack()) {
            System.out.println("🤖 Dealer gana con BLACKJACK!");
        } else if (ana.getPuntaje() > dealer.getPuntaje()) {
            System.out.println("🎉 Ana gana! (" + ana.getPuntaje() + " vs " + dealer.getPuntaje() + ")");
            ana.ganarPartida();
        } else if (ana.getPuntaje() < dealer.getPuntaje()) {
            System.out.println("🤖 Dealer gana! (" + dealer.getPuntaje() + " vs " + ana.getPuntaje() + ")");
        } else {
            System.out.println("🤝 ¡Empate! (" + ana.getPuntaje() + " vs " + dealer.getPuntaje() + ")");
        }

        Main.separarSeccion();

        // === DEMOSTRACIÓN DEL HISTORIAL ===
        System.out.println("📚 HISTORIAL DE CARTAS JUGADAS (PILA - LIFO)");
        System.out.println("Mostrando las cartas en orden inverso al que se jugaron:");

        // Contar cartas en el historial
        int contadorCartas = 0;
        while (!historial.esVacia()) {
            Carta carta = historial.desapilar();
            contadorCartas++;
            System.out.println(contadorCartas + ". " + carta.toString());
        }

        Main.separarSeccion();

        // === ESTADÍSTICAS FINALES ===
        System.out.println("📊 ESTADÍSTICAS Y ESTADO FINAL");
        controlador.mostrarEstadisticas();

        System.out.println("Cartas restantes en la baraja: " + baraja.getTamaño());
        System.out.println("Total de cartas jugadas: " + contadorCartas);

        System.out.println("\n✅ DEMOSTRACIÓN COMPLETADA");
        System.out.println("Todas las estructuras de datos funcionaron correctamente:");
        System.out.println("  🟦 Lista Enlazada (Baraja) - Gestión dinámica de cartas");
        System.out.println("  🟨 Pila (Historial) - Registro LIFO de jugadas");
        System.out.println("  🟩 Cola (Turnos) - Orden FIFO de participantes");
        System.out.println("  🟫 Árbol Binario - Decisiones automáticas del dealer");
        System.out.println("  🟥 Tabla Hash - Gestión eficiente de jugadores");

        System.out.println("\n🎉 ¡Gracias por ver la demostración!");
    }
}

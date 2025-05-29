package game;

import card.Baraja;
import card.Carta;
import dealer.ArbolDecision;
import historialJugadas.Pila;
import jugadores.ControladorJugadores;
import jugadores.EstadoJugador;
import turnos.Cola;
import utils.ListaEnlazada;
import java.util.Scanner;

/**
 * Motor principal del juego de Blackjack
 * Coordina todas las estructuras de datos para implementar la lógica del juego
 */
public class BlackjackEngine {
    private Baraja baraja;
    private Pila historial;
    private Cola<String> turnos;
    private ControladorJugadores controlador;
    private ArbolDecision arbolDealer;
    private Scanner scanner;
    private EstadoJugador dealer;

    public BlackjackEngine() {
        this.baraja = new Baraja();
        this.historial = new Pila();
        this.turnos = new Cola<>();
        this.controlador = new ControladorJugadores();
        this.arbolDealer = new ArbolDecision();
        this.scanner = new Scanner(System.in);

        // Agregar dealer como jugador especial
        controlador.agregarJugador("Dealer");
        this.dealer = controlador.obtenerJugador("Dealer");
    }

    /**
     * Inicia una nueva partida completa de Blackjack
     */
    public void iniciarJuego() {
        Main.mostrarTitulo();
        System.out.println("🃏 ¡Bienvenido al Blackjack versión consola!");

        // Configurar jugadores
        configurarJugadores();

        // Inicializar y mezclar baraja
        baraja.inicializarBaraja();
        baraja.mezclarBaraja();

        boolean seguirJugando = true;

        while (seguirJugando) {
            jugarPartida();
            seguirJugando = preguntarSiContinuar();
            if (seguirJugando) {
                prepararNuevaPartida();
            }
        }

        // Mostrar estadísticas finales
        controlador.mostrarEstadisticas();
        System.out.println("🎉 ¡Gracias por jugar! 🎉");
    }

    /**
     * Configura los jugadores para el juego
     */
    private void configurarJugadores() {
        System.out.print("Ingrese su nombre: ");
        String nombreJugador = scanner.nextLine().trim();

        if (nombreJugador.isEmpty()) {
            nombreJugador = "Jugador";
        }

        controlador.agregarJugador(nombreJugador);

        // Configurar turnos (primero el jugador, luego el dealer)
        turnos.encolar(nombreJugador);
        turnos.encolar("Dealer");

        System.out.println("\\n¡Perfecto! " + nombreJugador + " vs Dealer");
        Main.separarSeccion();
    }

    /**
     * Juega una partida completa
     */
    private void jugarPartida() {
        System.out.println("🎮 ¡Nueva partida!");

        // Repartir cartas iniciales
        repartirCartasIniciales();

        // Mostrar cartas iniciales
        mostrarCartasIniciales();

        // Verificar blackjack natural
        if (verificarBlackjackNatural()) {
            return;
        }

        // Jugar turnos
        jugarTurnos();

        // Determinar ganador
        determinarGanador();

        // Mostrar historial
        System.out.println("\\n📚 Historial de cartas jugadas en esta partida:");
        historial.mostrarPila();
    }

    /**
     * Reparte 2 cartas iniciales a cada jugador
     */
    private void repartirCartasIniciales() {
        ListaEnlazada<String> nombres = controlador.obtenerNombresJugadores();
        for (int i = 0; i < nombres.tamaño(); i++) {
            String nombreJugador = nombres.obtener(i);
            EstadoJugador jugador = controlador.obtenerJugador(nombreJugador);

            // Repartir 2 cartas
            for (int j = 0; j < 2; j++) {
                Carta carta = baraja.robarCarta();
                jugador.agregarCarta(carta);
                historial.apilar(carta);
            }
        }
    }

    /**
     * Muestra las cartas iniciales (carta oculta del dealer)
     */
    private void mostrarCartasIniciales() {
        ListaEnlazada<String> nombres = controlador.obtenerNombresJugadores();
        for (int i = 0; i < nombres.tamaño(); i++) {
            String nombreJugador = nombres.obtener(i);
            EstadoJugador jugador = controlador.obtenerJugador(nombreJugador);

            if (nombreJugador.equals("Dealer")) {
                // Mostrar solo una carta del dealer
                System.out.println("Dealer tiene:");
                System.out.println("  " + jugador.getMano().obtener(0).toString());
                System.out.println("  [Carta oculta]");
            } else {
                System.out.println(jugador.toString());
            }
            System.out.println();
        }
    }

    /**
     * Verifica si hay blackjack natural (21 con 2 cartas)
     */
    private boolean verificarBlackjackNatural() {
        boolean hayBlackjack = false;

        ListaEnlazada<String> nombres = controlador.obtenerNombresJugadores();
        for (int i = 0; i < nombres.tamaño(); i++) {
            String nombreJugador = nombres.obtener(i);
            EstadoJugador jugador = controlador.obtenerJugador(nombreJugador);
            if (jugador.tieneBlackjack()) {
                System.out.println("🎊 " + nombreJugador + " tiene BLACKJACK! 🎊");
                hayBlackjack = true;
            }
        }

        if (hayBlackjack) {
            // Mostrar todas las cartas
            controlador.mostrarEstadoActual();
            return true;
        }

        return false;
    }

    /**
     * Ejecuta los turnos de todos los jugadores
     */
    private void jugarTurnos() {
        // Crear nueva cola de turnos para esta partida
        Cola<String> turnosPartida = new Cola<>();
        ListaEnlazada<String> nombres = controlador.obtenerNombresJugadores();
        for (int i = 0; i < nombres.tamaño(); i++) {
            String nombre = nombres.obtener(i);
            if (!nombre.equals("Dealer")) {
                turnosPartida.encolar(nombre);
            }
        }
        turnosPartida.encolar("Dealer");

        while (!turnosPartida.esVacia()) {
            String jugadorActual = turnosPartida.desencolar();
            EstadoJugador jugador = controlador.obtenerJugador(jugadorActual);

            if (jugadorActual.equals("Dealer")) {
                jugarTurnoDealer();
            } else {
                jugarTurnoJugador(jugador);
            }
        }
    }

    /**
     * Maneja el turno de un jugador humano
     */
    private void jugarTurnoJugador(EstadoJugador jugador) {
        System.out.println("\\n🎯 Turno de: " + jugador.getNombre());

        while (!jugador.seHaPlantado() && !jugador.seHaPasado()) {
            System.out.println(jugador.toString());

            if (jugador.getPuntaje() == 21) {
                System.out.println("🎉 ¡21 exacto! Te plantas automáticamente.");
                jugador.plantarse();
                break;
            }

            System.out.print("¿Desea otra carta? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();

            if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) {
                Carta nuevaCarta = baraja.robarCarta();
                jugador.agregarCarta(nuevaCarta);
                historial.apilar(nuevaCarta);

                System.out.println("\\n🃏 Nueva carta: " + nuevaCarta.toString());
                Main.retraso(1000);

                if (jugador.seHaPasado()) {
                    System.out.println("💥 ¡Te pasaste de 21! Pierdes este turno.");
                }
            } else {
                jugador.plantarse();
                System.out.println("✋ " + jugador.getNombre() + " se planta con " + jugador.getPuntaje() + " puntos.");
            }
        }

        Main.separarSeccion();
    }

    /**
     * Maneja el turno del dealer usando el árbol de decisión
     */
    private void jugarTurnoDealer() {
        System.out.println("\\n🤖 Turno del Dealer");

        // Revelar carta oculta
        System.out.println("El dealer revela su carta oculta...");
        System.out.println(dealer.toString());
        Main.retraso(1500);

        while (!dealer.seHaPlantado() && !dealer.seHaPasado()) {
            String decision = arbolDealer.tomarDecision(dealer.getPuntaje(), dealer.tieneAsSuave());

            if (decision.equals("PEDIR")) {
                System.out.println("El dealer toma una carta...");
                Main.retraso(1000);

                Carta nuevaCarta = baraja.robarCarta();
                dealer.agregarCarta(nuevaCarta);
                historial.apilar(nuevaCarta);

                System.out.println("🃏 Nueva carta del dealer: " + nuevaCarta.toString());
                System.out.println("Puntaje del dealer: " + dealer.getPuntaje());

                if (dealer.seHaPasado()) {
                    System.out.println("💥 ¡El dealer se pasó de 21!");
                }
            } else {
                dealer.plantarse();
                System.out.println("✋ El dealer se planta con " + dealer.getPuntaje() + " puntos.");
            }

            Main.retraso(1000);
        }

        Main.separarSeccion();
    }

    /**
     * Determina el ganador de la partida
     */
    private void determinarGanador() {
        System.out.println("\\n🏆 === RESULTADOS ===");

        // Mostrar estado final de todos
        controlador.mostrarEstadoActual();

        // Determinar ganadores
        ListaEnlazada<String> nombres = controlador.obtenerNombresJugadores();
        for (int i = 0; i < nombres.tamaño(); i++) {
            String nombreJugador = nombres.obtener(i);
            if (nombreJugador.equals("Dealer"))
                continue;

            EstadoJugador jugador = controlador.obtenerJugador(nombreJugador);

            if (jugador.seHaPasado()) {
                System.out.println("❌ " + nombreJugador + " se pasó. Dealer gana.");
            } else if (dealer.seHaPasado()) {
                System.out.println("🎉 " + nombreJugador + " gana! El dealer se pasó.");
                jugador.ganarPartida();
            } else if (jugador.tieneBlackjack() && !dealer.tieneBlackjack()) {
                System.out.println("🎊 " + nombreJugador + " gana con BLACKJACK!");
                jugador.ganarPartida();
            } else if (dealer.tieneBlackjack() && !jugador.tieneBlackjack()) {
                System.out.println("🤖 Dealer gana con BLACKJACK!");
            } else if (jugador.getPuntaje() > dealer.getPuntaje()) {
                System.out.println(
                        "🎉 " + nombreJugador + " gana! (" + jugador.getPuntaje() + " vs " + dealer.getPuntaje() + ")");
                jugador.ganarPartida();
            } else if (jugador.getPuntaje() < dealer.getPuntaje()) {
                System.out.println("🤖 Dealer gana! (" + dealer.getPuntaje() + " vs " + jugador.getPuntaje() + ")");
            } else {
                System.out.println("🤝 Empate! (" + jugador.getPuntaje() + " vs " + dealer.getPuntaje() + ")");
            }
        }

        System.out.println("===================\\n");
    }

    /**
     * Pregunta si el jugador quiere continuar
     */
    private boolean preguntarSiContinuar() {
        System.out.print("¿Desea jugar otra partida? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí");
    }

    /**
     * Prepara una nueva partida
     */
    private void prepararNuevaPartida() {
        // Reiniciar jugadores
        controlador.reiniciarPartidaTodos();

        // Limpiar historial
        while (!historial.esVacia()) {
            historial.desapilar();
        }

        // Si quedan pocas cartas, reinicializar baraja
        if (baraja.getTamaño() < 10) {
            System.out.println("🔄 Reinicializando baraja...");
            baraja = new Baraja();
            baraja.inicializarBaraja();
            baraja.mezclarBaraja();
        }

        Main.separarSeccion();
    }
}

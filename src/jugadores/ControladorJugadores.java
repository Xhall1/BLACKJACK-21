package src.jugadores;

import src.utils.TablaHash;
import src.utils.ListaEnlazada;

/**
 * Controlador de jugadores usando implementación propia de Tabla Hash
 * Permite acceso rápido O(1) a la información de cada jugador
 * Reemplaza HashMap de Java con nuestra implementación personalizada
 */
public class ControladorJugadores {
    private TablaHash<String, EstadoJugador> jugadores;

    /**
     * Constructor que inicializa la tabla hash de jugadores
     */
    public ControladorJugadores() {
        this.jugadores = new TablaHash<>();
    }

    /**
     * Agregar un nuevo jugador al controlador
     * 
     * @param nombre el nombre del jugador a agregar
     */
    public void agregarJugador(String nombre) {
        if (!jugadores.contieneClave(nombre)) {
            jugadores.poner(nombre, new EstadoJugador(nombre));
        }
    }

    /**
     * Obtener el estado de un jugador
     * 
     * @param nombre el nombre del jugador
     * @return el estado del jugador o null si no existe
     */
    public EstadoJugador obtenerJugador(String nombre) {
        return jugadores.obtener(nombre);
    }

    /**
     * Verificar si un jugador existe
     * 
     * @param nombre el nombre del jugador a verificar
     * @return true si el jugador existe, false en caso contrario
     */
    public boolean existeJugador(String nombre) {
        return jugadores.contieneClave(nombre);
    }

    /**
     * Remover un jugador del controlador
     * 
     * @param nombre el nombre del jugador a remover
     * @return true si se removió exitosamente, false si no existía
     */
    public boolean removerJugador(String nombre) {
        return jugadores.remover(nombre) != null;
    }

    /**
     * Obtener la lista de nombres de todos los jugadores
     * 
     * @return una ListaEnlazada con todos los nombres de jugadores
     */
    public ListaEnlazada<String> obtenerNombresJugadores() {
        return jugadores.obtenerClaves();
    }

    /**
     * Reiniciar la partida para todos los jugadores
     */
    public void reiniciarPartidaTodos() {
        ListaEnlazada<EstadoJugador> valores = jugadores.obtenerValores();
        ListaEnlazada.IteradorLista<EstadoJugador> iterador = valores.iterador();
        while (iterador.tieneProximo()) {
            EstadoJugador jugador = iterador.proximo();
            jugador.reiniciarPartida();
        }
    }

    /**
     * Obtener el número total de jugadores
     * 
     * @return el número de jugadores registrados
     */
    public int getNumeroJugadores() {
        return jugadores.tamaño();
    }

    /**
     * Mostrar estadísticas de todos los jugadores
     */
    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DE JUGADORES ===");
        ListaEnlazada<EstadoJugador> valores = jugadores.obtenerValores();
        ListaEnlazada.IteradorLista<EstadoJugador> iterador = valores.iterador();
        while (iterador.tieneProximo()) {
            EstadoJugador jugador = iterador.proximo();
            System.out.printf("%s: %d/%d victorias (%.1f%%)\n",
                    jugador.getNombre(),
                    jugador.getPartidasGanadas(),
                    jugador.getPartidasJugadas(),
                    jugador.getPorcentajeVictorias());
        }
        System.out.println("================================\n");
    }

    /**
     * Mostrar el estado actual de todos los jugadores
     */
    public void mostrarEstadoActual() {
        System.out.println("\n=== ESTADO ACTUAL DE JUGADORES ===");
        ListaEnlazada<EstadoJugador> valores = jugadores.obtenerValores();
        ListaEnlazada.IteradorLista<EstadoJugador> iterador = valores.iterador();
        while (iterador.tieneProximo()) {
            EstadoJugador jugador = iterador.proximo();
            System.out.println(jugador.toString());
            System.out.println();
        }
        System.out.println("==================================\n");
    }

    /**
     * Verificar si todos los jugadores han terminado su turno
     * 
     * @return true si todos han terminado, false en caso contrario
     */
    public boolean todosHanTerminado() {
        ListaEnlazada<EstadoJugador> valores = jugadores.obtenerValores();
        ListaEnlazada.IteradorLista<EstadoJugador> iterador = valores.iterador();
        while (iterador.tieneProximo()) {
            EstadoJugador jugador = iterador.proximo();
            if (!jugador.seHaPlantado() && !jugador.seHaPasado()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Obtener el ganador de la partida
     * 
     * @param puntajeDealer Puntaje del dealer
     * @param dealerSePaso  Si el dealer se pasó de 21
     * @return Nombre del ganador o null si hay empate
     */
    public String determinarGanador(int puntajeDealer, boolean dealerSePaso) {
        String mejorJugador = null;
        int mejorPuntaje = 0;

        ListaEnlazada<EstadoJugador> valores = jugadores.obtenerValores();
        ListaEnlazada.IteradorLista<EstadoJugador> iterador = valores.iterador();
        while (iterador.tieneProximo()) {
            EstadoJugador jugador = iterador.proximo();

            // Saltar jugadores que se pasaron
            if (jugador.seHaPasado()) {
                continue;
            }

            // Si el dealer se pasó, cualquier jugador que no se haya pasado gana
            if (dealerSePaso) {
                if (mejorJugador == null || jugador.getPuntaje() > mejorPuntaje) {
                    mejorJugador = jugador.getNombre();
                    mejorPuntaje = jugador.getPuntaje();
                }
            } else {
                // Comparar con el dealer
                if (jugador.getPuntaje() > puntajeDealer && jugador.getPuntaje() <= 21) {
                    if (mejorJugador == null || jugador.getPuntaje() > mejorPuntaje) {
                        mejorJugador = jugador.getNombre();
                        mejorPuntaje = jugador.getPuntaje();
                    }
                }
            }
        }

        return mejorJugador;
    }
}

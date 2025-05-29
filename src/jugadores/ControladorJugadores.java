package src.jugadores;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Tabla Hash para controlar el estado de todos los jugadores
 * Permite acceso rápido O(1) a la información de cada jugador
 */
public class ControladorJugadores {
    private Map<String, EstadoJugador> jugadores;

    public ControladorJugadores() {
        this.jugadores = new HashMap<>();
    }

    /**
     * Agregar un nuevo jugador al controlador
     */
    public void agregarJugador(String nombre) {
        if (!jugadores.containsKey(nombre)) {
            jugadores.put(nombre, new EstadoJugador(nombre));
        }
    }

    /**
     * Obtener el estado de un jugador
     */
    public EstadoJugador obtenerJugador(String nombre) {
        return jugadores.get(nombre);
    }

    /**
     * Verificar si un jugador existe
     */
    public boolean existeJugador(String nombre) {
        return jugadores.containsKey(nombre);
    }

    /**
     * Remover un jugador del controlador
     */
    public boolean removerJugador(String nombre) {
        return jugadores.remove(nombre) != null;
    }

    /**
     * Obtener la lista de nombres de todos los jugadores
     */
    public Set<String> obtenerNombresJugadores() {
        return jugadores.keySet();
    }

    /**
     * Reiniciar la partida para todos los jugadores
     */
    public void reiniciarPartidaTodos() {
        for (EstadoJugador jugador : jugadores.values()) {
            jugador.reiniciarPartida();
        }
    }

    /**
     * Obtener el número total de jugadores
     */
    public int getNumeroJugadores() {
        return jugadores.size();
    }

    /**
     * Mostrar estadísticas de todos los jugadores
     */
    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DE JUGADORES ===");
        for (EstadoJugador jugador : jugadores.values()) {
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
        for (EstadoJugador jugador : jugadores.values()) {
            System.out.println(jugador.toString());
            System.out.println();
        }
        System.out.println("==================================\n");
    }

    /**
     * Verificar si todos los jugadores han terminado su turno
     */
    public boolean todosHanTerminado() {
        for (EstadoJugador jugador : jugadores.values()) {
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
     * @return Nombre del ganador o null si hay empate
     */
    public String determinarGanador(int puntajeDealer, boolean dealerSePaso) {
        String mejorJugador = null;
        int mejorPuntaje = 0;

        for (EstadoJugador jugador : jugadores.values()) {
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

package src.jugadores;

import src.card.Carta;
import src.utils.ListaEnlazada;

/**
 * Representa el estado de un jugador en el juego de Blackjack
 * Utiliza estructura de datos propia (ListaEnlazada) en lugar de ArrayList de
 * Java
 */
public class EstadoJugador {
    private String nombre;
    private ListaEnlazada<Carta> mano;
    private int puntaje;
    private boolean seHaPlantado;
    private boolean seHaPasado;
    private int partidasGanadas;
    private int partidasJugadas;
    private boolean tieneBlackjack;

    /**
     * Constructor que inicializa el estado de un jugador
     * 
     * @param nombre el nombre del jugador
     */
    public EstadoJugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ListaEnlazada<>();
        this.puntaje = 0;
        this.seHaPlantado = false;
        this.seHaPasado = false;
        this.partidasGanadas = 0;
        this.partidasJugadas = 0;
        this.tieneBlackjack = false;
    }

    /**
     * Agrega una carta a la mano del jugador
     * 
     * @param carta la carta a agregar
     */
    public void agregarCarta(Carta carta) {
        mano.agregar(carta);
        calcularPuntaje();
    }

    /**
     * Calcula el puntaje considerando el manejo inteligente de los Ases
     * Los Ases pueden valer 11 o 1 según convenga para no pasarse de 21
     */
    private void calcularPuntaje() {
        int suma = 0;
        int ases = 0;

        // Contar puntos y ases usando iterador personalizado
        ListaEnlazada.IteradorLista<Carta> iterador = mano.iterador();
        while (iterador.tieneProximo()) {
            Carta carta = iterador.proximo();
            if (carta.getValor().equals("A")) {
                ases++;
                suma += 11; // Inicialmente contar As como 11
            } else {
                suma += carta.getPuntos();
            }
        }

        // Ajustar ases si es necesario para no pasarse de 21
        while (suma > 21 && ases > 0) {
            suma -= 10; // Cambiar As de 11 a 1
            ases--;
        }

        this.puntaje = suma;

        // Verificar si se ha pasado de 21
        if (puntaje > 21) {
            seHaPasado = true;
        }

        // Verificar blackjack (21 con exactamente 2 cartas)
        if (mano.tamaño() == 2 && puntaje == 21) {
            tieneBlackjack = true;
        }
    }

    /**
     * Verifica si el jugador tiene un As suave (As contado como 11)
     * 
     * @return true si tiene As suave, false en caso contrario
     */
    public boolean tieneAsSuave() {
        int suma = 0;
        boolean tieneAs = false;

        // Recorrer cartas para verificar As suave
        ListaEnlazada.IteradorLista<Carta> iterador = mano.iterador();
        while (iterador.tieneProximo()) {
            Carta carta = iterador.proximo();
            if (carta.getValor().equals("A")) {
                tieneAs = true;
            }
            suma += carta.getPuntos();
        }

        return tieneAs && suma <= 21 && puntaje == suma;
    }

    /**
     * Reinicia el estado del jugador para una nueva partida
     */
    public void reiniciarPartida() {
        mano.limpiar();
        puntaje = 0;
        seHaPlantado = false;
        seHaPasado = false;
        tieneBlackjack = false;
        partidasJugadas++;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene una copia de las cartas en la mano del jugador
     * 
     * @return una nueva ListaEnlazada con copia de las cartas
     */
    public ListaEnlazada<Carta> getMano() {
        return mano.copia(); // Devolver copia para evitar modificaciones externas
    }

    public int getPuntaje() {
        return puntaje;
    }

    public boolean seHaPlantado() {
        return seHaPlantado;
    }

    /**
     * Hace que el jugador se plante (no pida más cartas)
     */
    public void plantarse() {
        this.seHaPlantado = true;
    }

    public boolean seHaPasado() {
        return seHaPasado;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    /**
     * Incrementa el contador de partidas ganadas
     */
    public void ganarPartida() {
        this.partidasGanadas++;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public boolean tieneBlackjack() {
        return tieneBlackjack;
    }

    /**
     * Calcula el porcentaje de victorias del jugador
     * 
     * @return el porcentaje de victorias (0.0 si no ha jugado partidas)
     */
    public double getPorcentajeVictorias() {
        if (partidasJugadas == 0)
            return 0.0;
        return (double) partidasGanadas / partidasJugadas * 100;
    }

    /**
     * Representación en cadena del estado del jugador
     * Muestra las cartas, puntaje y estado especial (blackjack, pasado)
     * 
     * @return una cadena con la información del jugador
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(" tiene:\n");

        // Mostrar todas las cartas usando iterador
        ListaEnlazada.IteradorLista<Carta> iterador = mano.iterador();
        while (iterador.tieneProximo()) {
            Carta carta = iterador.proximo();
            sb.append("  ").append(carta.toString()).append("\n");
        }

        sb.append("Puntaje: ").append(puntaje);
        if (tieneBlackjack) {
            sb.append(" (¡BLACKJACK!)");
        } else if (seHaPasado) {
            sb.append(" (PASADO)");
        }
        return sb.toString();
    }
}

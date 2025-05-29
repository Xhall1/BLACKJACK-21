package src.jugadores;

import src.historialJugadas.Pila;

public class Jugador { // Clase jugador, toda la informacion del jugador
                       // Hace falta el estado del juego

    private String nombre;
    private Pila cartas;
    private int puntaje;
    private int partidasGanadas;
    private boolean estadoJuego; // True para saber que esta jugando, false para saber que dejo de jugar

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.cartas = null;
        this.puntaje = 0;
        this.partidasGanadas = 0;
        this.estadoJuego = true;
    }

    public Pila getCartas() {
        return cartas;
    }

    public void setCartas(Pila cartas) {
        this.cartas = cartas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public boolean getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(boolean estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

}

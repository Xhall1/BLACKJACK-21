package src.jugadores;

import src.card.Carta;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa el estado de un jugador en el juego de Blackjack
 */
public class EstadoJugador {
    private String nombre;
    private List<Carta> mano;
    private int puntaje;
    private boolean seHaPlantado;
    private boolean seHaPasado;
    private int partidasGanadas;
    private int partidasJugadas;
    private boolean tieneBlackjack;

    public EstadoJugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        this.puntaje = 0;
        this.seHaPlantado = false;
        this.seHaPasado = false;
        this.partidasGanadas = 0;
        this.partidasJugadas = 0;
        this.tieneBlackjack = false;
    }

    // Agregar carta a la mano
    public void agregarCarta(Carta carta) {
        mano.add(carta);
        calcularPuntaje();
    }

    // Calcular puntaje considerando los Ases
    private void calcularPuntaje() {
        int suma = 0;
        int ases = 0;

        // Contar puntos y ases
        for (Carta carta : mano) {
            if (carta.getValor().equals("A")) {
                ases++;
                suma += 11; // Inicialmente contar As como 11
            } else {
                suma += carta.getPuntos();
            }
        }

        // Ajustar ases si es necesario
        while (suma > 21 && ases > 0) {
            suma -= 10; // Cambiar As de 11 a 1
            ases--;
        }

        this.puntaje = suma;

        // Verificar si se ha pasado
        if (puntaje > 21) {
            seHaPasado = true;
        }

        // Verificar blackjack (21 con 2 cartas)
        if (mano.size() == 2 && puntaje == 21) {
            tieneBlackjack = true;
        }
    }

    // Verificar si tiene As suave (As contado como 11)
    public boolean tieneAsSuave() {
        int suma = 0;
        boolean tieneAs = false;

        for (Carta carta : mano) {
            if (carta.getValor().equals("A")) {
                tieneAs = true;
            }
            suma += carta.getPuntos();
        }

        return tieneAs && suma <= 21 && puntaje == suma;
    }

    // Reiniciar para nueva partida
    public void reiniciarPartida() {
        mano.clear();
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

    public List<Carta> getMano() {
        return new ArrayList<>(mano); // Devolver copia para evitar modificaciones externas
    }

    public int getPuntaje() {
        return puntaje;
    }

    public boolean seHaPlantado() {
        return seHaPlantado;
    }

    public void plantarse() {
        this.seHaPlantado = true;
    }

    public boolean seHaPasado() {
        return seHaPasado;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void ganarPartida() {
        this.partidasGanadas++;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public boolean tieneBlackjack() {
        return tieneBlackjack;
    }

    public double getPorcentajeVictorias() {
        if (partidasJugadas == 0)
            return 0.0;
        return (double) partidasGanadas / partidasJugadas * 100;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(" tiene:\n");
        for (Carta carta : mano) {
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

package src.card;

// Representa una carta en el mazo
public class Carta {
    private String palo; // Palo de la carta la cuál en este caso sería: ♠, ♥, ♦, ♣
    private String valor; // Valor de la carta cómo sería en este caso: A, 2-10, J, Q, K
    private int puntos; // Puntos asociados a la carta

    public Carta(String palo, String valor, int puntos) {
        this.palo = palo;
        this.valor = valor;
        this.puntos = puntos;
    }

    public String getPalo() {
        return palo;
    }

    public String getValor() {
        return valor;
    }

    public int getPuntos() {
        return puntos;
    }

    // Aquí se representa la carta como un texto
    @Override
    public String toString() {
        return valor + " de " + palo;
    }
}
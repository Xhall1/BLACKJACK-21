package src.historialJugadas;

import src.card.Carta;

public class Nodo {

    private Carta carta;
    private Nodo sig;  // Variable para enlazar los nodos

    // Constructor general
    public Nodo() {
        this.carta = null;
        this.sig = null;
    }

    
    // Getters y Setters
    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }

}

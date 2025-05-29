package historialJugadas;

import card.Carta;
import game.Main;
import card.Nodo;

public class Pila {
    private Nodo inicio;

    public Pila() {
        this.inicio = null;
    }

    public boolean esVacia(){
        return inicio == null;
    }

    public void apilar(Carta carta){
        Nodo nuevo = new Nodo();
        nuevo.setCarta(carta);
        if(esVacia()){
            inicio = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
    }

    public Carta desapilar(){
        if(!esVacia()){
            Carta carta = inicio.getCarta();
            inicio = inicio.getSiguiente(); // CORREGIDO
            return carta;
        } else {
            return null;
        }
    }

    public void mostrarPila(){
        Nodo aux = inicio;
        Main app = new Main();
        while(aux != null){
            app.imprimirCarta(aux.getCarta());
            app.separarSeccion();
            aux = aux.getSiguiente(); // CORREGIDO
        }
    }
}

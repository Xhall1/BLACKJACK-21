package historialJugadas;

import card.Carta;
import game.Main;

public class Pila {
    private Nodo inicio;

    public Pila() {
        this.inicio = null;
    }
    
    // Consulta si la pila esta vacia
    // Retorna true si el primer nodo (inicio), no apunta a otro nodo
    public boolean esVacia(){
        return inicio==null;
    }
    
    // Metodo de apilar o push para agregar un nuevo nod a la pila
    public void apilar(Carta carta){
        // Define un nuevo nodo
        Nodo nuevo = new Nodo();
        // Agregar el valor al nodo
        nuevo.setCarta(carta);
        if(esVacia()){
            // inicializa la pila con el nuevo valor
            inicio = nuevo;
        } else {
            // Agrega el nuevo nodo al inicio de la pila
            nuevo.setSig(inicio);
            inicio = nuevo;
        }
    }
    
    // Metodo de pop o desapilar, elimina el elemento del tope de la pila
    public Carta desapilar(){
        if(!esVacia()){
            Carta carta = inicio.getCarta();
            // asigna el primer nodo al siguiente de la pila
            inicio=inicio.getSig();
            return carta;
        } else {
            // devolver valor para indicar que la pila esta vacia
            return null;
        }
    }
    
    public void mostrarPila(){
        Nodo aux = inicio;
        System.out.println("Estas son las cartas que se jugaron:");
        while(aux!=null){
            Main.imprimirCarta(aux.getCarta());
            Main.separarSeccion();
            aux = aux.getSig();
        }
    }
}

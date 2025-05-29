package src.card;

// Esta clase representa un nodo en una lista enlazada
public class Nodo {

    private Carta carta; // Variable para guardar la carta
    private Nodo siguiente; // Atributo para guardar la referencia al siguiente nodo

    // Constructor por defecto
    public Nodo() {
        this.carta = null;
        this.siguiente = null;
    }

    // Constructor con parámetro para inicializar un nodo con una carta
    public Nodo(Carta carta) {
        this.carta = carta;
        this.siguiente = null;
    }

    // Métodos getter y setter
    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

/**
 public class Nodo {

 private int valor; //Variable o lista de variables para guardar datos
 private Nodo siguiente; //Atributo para guardar la referencia al siguiente nodo

 //Constructor por defecto
 public void Nodo() {
 this.valor = 0;
 this.siguiente = null;
 }
 //Metodos get y set
 public int getValor() {
 return valor;
 }
 public void setValor(int valor) {
 this.valor = valor;
 }
 public Nodo getSiguiente() {
 return siguiente;
 }
 public void setSiguiente(Nodo siguiente) {
 this.siguiente = siguiente;
 }
 } **/

